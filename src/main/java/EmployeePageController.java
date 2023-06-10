import Employee.Employee;
import Employee.EmployeeDAOImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Employee.EmployeeDAOImpl._connection;

public class EmployeePageController  {


    @FXML
    private Button Add, Delete, Update;

    @FXML
    private TableColumn<Employee, String> Name, Address, Post;

    @FXML
    private TableColumn<Employee, Integer> ID;

    @FXML
    private TableView<Employee> Table;


    public TextField txt_Name, txt_Address, txt_Post, txt_ID;

    ObservableList <Employee> list;
    int index = -1;
    PreparedStatement pst = null;
    private Stage mainStage;


    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
        Post.setCellValueFactory(new PropertyValueFactory<Employee, String>("Post"));

        list = EmployeeDAOImpl.getEmployees();
        Table.setItems(list);
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

    public void addCar() {
        String sql = "INSERT INTO Employees values (?, ?, ?, ?)";
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
                    "', Post = '" + Post +
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