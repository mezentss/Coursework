import Service.Service;
import Service.ServiceController;
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

public class ServicePageController  {
    @FXML
    private Button Add, Delete, Update, MenuBotton;
    @FXML
    private TableColumn<Service, String> StartDate, EndDate;
    @FXML
    private TableColumn<Service, Integer> ID, CarID, Mileage, EmployeeID, TimeWorked ;
    @FXML
    private TableView<Service> Table;
    public TextField txt_StartDate, txt_ID, txt_CarID, txt_Mileage, txt_EmployeeID, txt_TimeWorked;
    ObservableList <Service> list;
    int index = -1;
    PreparedStatement pst = null;
    private Stage mainStage;
    private static final String INSERT = "INSERT INTO Services VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Services WHERE ID = ?";

    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Service, Integer>("Id"));
        CarID.setCellValueFactory(new PropertyValueFactory<Service, Integer>("CarID"));
        TimeWorked.setCellValueFactory(new PropertyValueFactory<Service, Integer>("TimeWorked"));
        Mileage.setCellValueFactory(new PropertyValueFactory<Service, Integer>("Mileage"));
        EmployeeID.setCellValueFactory(new PropertyValueFactory<Service, Integer>("EmployeeID"));
        StartDate.setCellValueFactory(new PropertyValueFactory<Service, String>("StartDate"));
        EndDate.setCellValueFactory(new PropertyValueFactory<Service, String>("EndDate"));

        list = ServiceController.getServices();
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
        txt_CarID.setText(CarID.getCellData(index).toString());
        txt_Mileage.setText(Mileage.getCellData(index).toString());
        txt_EmployeeID.setText(EmployeeID.getCellData(index).toString());
        txt_TimeWorked.setText(TimeWorked.getCellData(index).toString());
        txt_StartDate.setText(StartDate.getCellData(index));
    }

    public void addCar() {
        try {
            pst = conn.prepareStatement(INSERT);
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));
            pst.setInt(2, Integer.parseInt(txt_CarID.getText()));
            pst.setInt(3, Integer.parseInt(txt_Mileage.getText()));
            pst.setInt(4, Integer.parseInt(txt_EmployeeID.getText()));
            pst.setInt(5, Integer.parseInt(txt_TimeWorked.getText()));
            pst.setString(6, txt_StartDate.getText());
            String date [] =txt_StartDate.getText().split("-");
            int end = (int) Math.ceil(Integer.parseInt(txt_TimeWorked.getText())/24)+ Integer.parseInt(date[2]);
            String endDate = date[0]+"-"+date[1]+"-"+end;
            pst.setString(7, endDate);

            JOptionPane.showMessageDialog(null, "Автомобиль успешно добавлен");
            pst.execute();
            initialize();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit(){
        try {
            String id = txt_ID.getText();
            String carID = txt_CarID.getText();
            String mileage = txt_Mileage.getText();
            String employeeID = txt_EmployeeID.getText();
            String timeWorked = txt_TimeWorked.getText();
            String startDate = txt_StartDate.getText();
            String date [] =txt_StartDate.getText().split("-");
            int end = (int) Math.ceil(Integer.parseInt(txt_TimeWorked.getText())/24)+ Integer.parseInt(date[2]);
            String endDate = date[0]+"-"+date[1]+"-"+end;

            String sql = "UPDATE Services SET CarID  = '" + carID +
                    "', Mileage = '" + mileage +
                    "', EmployeeID  = '" + employeeID +
                    "', TimeWorked = '" + timeWorked +
                    "', StartDate = '" + startDate +
                    "', EndDate = '" + endDate +
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
