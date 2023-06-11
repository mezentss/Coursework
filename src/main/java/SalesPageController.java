import Sale.Sale;
import Sale.SaleController;
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

import static Employee.EmployeeController._connection;

public class SalesPageController  {
    @FXML
    private TableColumn<Sale, String>  ServiceID, PartID;
    @FXML
    private TableColumn<Sale, Integer> ID;
    @FXML
    private TableView<Sale> Table;
    @FXML
    private Button MenuBotton;
    public TextField txt_ServiceID, txt_PartID, txt_ID;
    ObservableList <Sale> list;
    int index = -1;
    private Stage mainStage;
    PreparedStatement pst = null;
    private static final String INSERT = "INSERT INTO Sales VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM Sales WHERE ID = ?";


    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ServiceID.setCellValueFactory(new PropertyValueFactory<>("ServiceID"));
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));

        list = SaleController.getSales();
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

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent){
        index = Table.getSelectionModel().getSelectedIndex();
        if (index <=-1){
            return;
        }
        txt_ID.setText(ID.getCellData(index).toString());
        txt_ServiceID.setText(ServiceID.getCellData(index));
        txt_PartID.setText(PartID.getCellData(index));

    }

    public void addSale() {
        try {
            pst = _connection.prepareStatement(INSERT);
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));
            pst.setString(2, txt_ServiceID.getText());
            pst.setString(3, txt_PartID.getText());


            JOptionPane.showMessageDialog(null, "Сотрудник успешно добавлен");
            pst.execute();
            initialize();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit(){
        try {
            String id = txt_ID.getText();
            String ServiceID = txt_ServiceID.getText();
            String PartID = txt_PartID.getText();

            String sql = "UPDATE Sales SET ServiceID = '" + ServiceID +
                    "', PartID = '" + PartID +
                    "' WHERE ID = " + id + "; ";

            pst = _connection.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Информация обновлена");
            pst.executeUpdate();
            initialize();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Информация не обновлена.");
        }
    }

    public void Delete(){
        try {
            pst = _connection.prepareStatement(DELETE);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Информация удалена");
            initialize();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Информация не удалена.");
        }
    }

    public void setMainStage(Stage primaryStage) {
        mainStage = primaryStage;
    }
}
