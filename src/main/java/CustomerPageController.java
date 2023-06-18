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
    private static Customer _customer;
    PreparedStatement pst = null;
    @FXML
    private Button MenuBotton;

    public CustomerPageController(Customer customer){_customer = customer;}
    public CustomerPageController(){}
    public void setMainStage(Stage primaryStage) {mainStage = primaryStage;}
    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
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
        int ID = Integer.parseInt(txt_ID.getText());
        String Name = txt_Name.getText();
        String Address = txt_Address.getText();
        String Phone = txt_Phone.getText();
        Customer customer = new Customer(ID, Name, Address, Phone);
        CustomerController.addCustomer(customer);
        list.add(customer);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Владелец добавлен");
    }
    public void Edit(){
        String Name = txt_Name.getText();
        String Address = txt_Address.getText();
        String Phone = txt_Phone.getText();
        int ID = Integer.parseInt(txt_ID.getText());
        Customer customer = new Customer(ID, Name, Address, Phone);
        CustomerController.updateCustomer(customer);
        list.set(index, customer);
        JOptionPane.showMessageDialog(null, "Информация обновлена");
    }
    public void Delete(){
        int id = Integer.parseInt(txt_ID.getText());
        CustomerController.deleteCustomer(id);
        list.remove(index);
        clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }
    private void clearFields() {
        txt_ID.setText("");
        txt_Name.setText("");
        txt_Address.setText("");
        txt_Phone.setText("");
    }
}
