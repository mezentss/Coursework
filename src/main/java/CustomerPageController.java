import Customer.*;
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

import static Customer.CustomerController._connection;

public class CustomerPageController  {
    @FXML
    private TableColumn<Customer, String> Name, Address, Phone;
    @FXML
    private TableColumn<Customer, Integer> ID;
    @FXML
    private TableView<Customer> Table;
    public TextField txt_Name, txt_Address, txt_Phone, txt_ID;
    ObservableList <Customer> list;
    int index = -1;
    private Stage mainStage;
    PreparedStatement pst = null;
    private static final String INSERT = "INSERT INTO Customers VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Customers WHERE ID = ?";
    @FXML
    private Button MenuBotton;


    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        list = CustomerController.getCustomers();
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
        txt_Name.setText(Name.getCellData(index));
        txt_Address.setText(Address.getCellData(index));
        txt_Phone.setText(Phone.getCellData(index));

    }

    public void addCustomer() {
        try {
            pst = _connection.prepareStatement(INSERT);
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));
            pst.setString(2, txt_Name.getText());
            pst.setString(3, txt_Address.getText());
            pst.setString(4, txt_Phone.getText());


            JOptionPane.showMessageDialog(null, "Клиент успешно добавлен");
            pst.execute();
            initialize();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit(){
        try {
            String id = txt_ID.getText();
            String Name = txt_Name.getText();
            String Address = txt_Address.getText();
            String Post = txt_Phone.getText();

            String sql = "UPDATE Customers SET Name = '" + Name +
                    "', Address = '" + Address +
                    "', Post = '" + Post +
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
