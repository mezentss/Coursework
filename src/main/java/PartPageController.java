import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;

public class PartPageController  {
    private static Part _part;
    @FXML
    private Button Add, Delete, Update, MenuBotton;
    @FXML
    private TableColumn<Part, String> Model, Category, SerialNumber;
    @FXML
    private TableColumn<Part, Integer> ID, Price;
    @FXML
    private TableView<Part> Table;
    public TextField txt_Category, txt_SerialNumber, txt_Price, txt_Model, txt_ID;
    ObservableList <Part> list;
    int index = -1;
    PreparedStatement pst = null;
    private Stage mainStage;
    public PartPageController(){}
    public PartPageController(Part part){_part = part;}

    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("ID"));
        Category.setCellValueFactory(new PropertyValueFactory<Part, String>("Category"));
        Model.setCellValueFactory(new PropertyValueFactory<Part, String>("Model"));
        SerialNumber.setCellValueFactory(new PropertyValueFactory<Part, String>("SerialNumber"));
        Price.setCellValueFactory(new PropertyValueFactory<Part, Integer>("Price"));

        list = PartController.getParts();
        Table.setItems(list);

        MenuBotton.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = loader.load();
                MainPageController controller = loader.getController();
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void setMainStage(Stage primaryStage) {
        mainStage = primaryStage;
    }
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent){
        index = Table.getSelectionModel().getSelectedIndex();
        if (index <=-1){
            return;
        }
        txt_ID.setText(ID.getCellData(index).toString());
        txt_Category.setText(Category.getCellData(index));
        txt_Model.setText(Model.getCellData(index));
        txt_SerialNumber.setText(SerialNumber.getCellData(index));
        txt_Price.setText(Price.getCellData(index).toString());
    }
    public void addPart() {
        int ID = Integer.parseInt(txt_ID.getText());
        String Category = txt_Category.getText();
        String Model = txt_Model.getText();
        String SerialNumber = txt_SerialNumber.getText();
        int Price = Integer.parseInt(txt_Price.getText());
        Part part = new Part(ID, Category, Model, SerialNumber, Price);
        PartController.addPart(part);
        list.add(part);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Деталь добавлена");
    }
    public void Edit(){
        int ID = Integer.parseInt(txt_ID.getText());
        String Category = txt_Category.getText();
        String Model = txt_Model.getText();
        String SerialNumber = txt_SerialNumber.getText();
        int Price = Integer.parseInt(txt_Price.getText());
        Part part = new Part(ID, Category, Model, SerialNumber, Price);
        PartController.updatePart(part);
        list.set(index, part);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Информация обновлена");
    }
    public void Delete(){
        int id = Integer.parseInt(txt_ID.getText());
        PartController.deletePart(id);
        list.remove(index);
        clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }
    private void clearFields() {
        txt_ID.setText("");
        txt_Price.setText("");
        txt_SerialNumber.setText("");
        txt_Model.setText("");
        txt_Category.setText("");
    }
}
