import Employee.*;
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

public class EmployeePageController  {


    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button Update;
    @FXML
    private static Button MenuBotton;

    @FXML
    private static TableColumn<Employee, String> Name;
    @FXML
    private static TableColumn<Employee, String> Address;
    @FXML
    private static TableColumn<Employee, String> Post;

    @FXML
    private static  TableColumn<Employee, Integer> ID;

    @FXML
    private static TableView<Employee> Table;


    public static TextField txt_Name;
    public static TextField txt_Address;
    public static TextField txt_Post;
    public static TextField txt_ID;

    static ObservableList <Employee> list;
    int index = -1;
    static PreparedStatement pst = null;
    private static Stage mainStage;


    public static void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
        Post.setCellValueFactory(new PropertyValueFactory<Employee, String>("AccessLevel"));

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

    public static void getEmployees() {
        String sql = "INSERT INTO Employees values (?, ?, ?, ?, ?, ?)";
        try {
            pst = _connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));
            pst.setString(2, txt_Name.getText());
            pst.setString(3, txt_Address.getText());
            pst.setString(4, txt_Post.getText());


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
            String Name = txt_Name.getText();
            String Address = txt_Address.getText();
            String Post = txt_Post.getText();

            String sql = "UPDATE Employees SET Name = '" + Name +
                    "', Address = '" + Address +
                    "', AccessLevel = '" + Post +
                    "' WHERE ID = " + id + "; ";

            pst = _connection.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Информация обновлена");
            pst.executeUpdate();
            initialize();
        }catch (Exception e){
        }
    }

    public void Delete(){
        String sql = "DELETE FROM Employees WHERE ID = ?";
        try {
            pst = _connection.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Информация удалена");
            initialize();

        }
        catch (Exception e){

        }
    }
}