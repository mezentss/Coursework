import Part.*;
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
import java.sql.SQLException;

import static Car.CarController.conn;

public class PartPageController  {
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
    private static final String INSERT = "INSERT INTO Parts VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Parts WHERE ID = ?";

    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("Id"));
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
                //controller.setWelcomeText("Welcome, " + loginField.getText() + "!");
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
        try {
            pst = conn.prepareStatement(INSERT);
            pst.setString(2, txt_Category.getText());
            pst.setString(3, txt_Model.getText());
            pst.setString(4, txt_SerialNumber.getText());
            pst.setInt(5, Integer.parseInt(txt_Price.getText()));
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));


            JOptionPane.showMessageDialog(null, "Деталь успешно добавлена");
            pst.execute();
            initialize();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit(){
        try {
            String id = txt_ID.getText();
            String category = txt_Category.getText();
            String model = txt_Model.getText();
            String serialNumber = txt_SerialNumber.getText();
            String price = txt_Price.getText();

            String sql = "UPDATE Parts SET Category = '" + category +
                    "', Model = '" + model +
                    "', SerialNumber = '" + serialNumber +
                    "', Price = '" + price +
                    "' WHERE ID = " + id + "; ";

            pst = conn.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Информация обновлена");
            pst.executeUpdate();
            initialize();
        }catch (Exception e){
        }
    }
    public void Delete(){
        try {
            pst = conn.prepareStatement(DELETE);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Информация удалена");
            initialize();

        }
        catch (Exception e){

        }
    }
}
