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


public class EmployeePageController  {
    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button Update;
    @FXML
    private  Button MenuBotton;

    @FXML
    private TableColumn<Employee, String> Name;
    @FXML
    private TableColumn<Employee, String> Address;
    @FXML
    private TableColumn<Employee, String> Post;
    @FXML
    private TableColumn<Employee, Integer> ID;

    @FXML
    private TableView<Employee> Table;
    public TextField txt_Name, txt_Address, txt_Post, txt_ID;
    ObservableList <Employee> list;
    int index = -1;
    private PreparedStatement pst = null;
    private static Employee _employee;
    private Stage mainStage;
    public EmployeePageController(){}
    public EmployeePageController(Employee employee){_employee = employee;}
    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
        Post.setCellValueFactory(new PropertyValueFactory<Employee, String>("Post"));

        list = EmployeeController.getEmployees();
        Table.setItems(list);

        MenuBotton.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(EmployeePageController.class.getResource("MainPage.fxml"));
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
        txt_Name.setText(Name.getCellData(index));
        txt_Address.setText(Address.getCellData(index));
        txt_Post.setText(Post.getCellData(index));

    }
    public void Edit(){
        String Name = txt_Name.getText();
        String Address = txt_Address.getText();
        String Post = txt_Post.getText();
        int ID = Integer.parseInt(txt_ID.getText());
        Employee employee = new Employee(ID, Name, Address, Post);
        EmployeeController.updateEmployee(employee);
        list.set(index, employee);
        JOptionPane.showMessageDialog(null, "Информация обновлена");
        //clearFields();
    }

    public void Delete(){
        int id = Integer.parseInt(txt_ID.getText());
        EmployeeController.deleteEmployee(id);
        list.remove(index);
        clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }
    private void clearFields() {
        txt_ID.setText("");
        txt_Post.setText("");
        txt_Address.setText("");
        txt_Name.setText("");
    }
}