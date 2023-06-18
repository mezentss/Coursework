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

public class ServicePageController  {
    private static Service _service;
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
    public ServicePageController(Service service){_service = service;}
    public ServicePageController(){}

    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Service, Integer>("ID"));
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

    public void addService() {
        int ID = Integer.parseInt(txt_ID.getText());
        int CarID = Integer.parseInt(txt_CarID.getText());
        int Mileage = Integer.parseInt(txt_Mileage.getText());
        int EmployeeID = Integer.parseInt(txt_EmployeeID.getText());
        int TimeWorked = Integer.parseInt(txt_TimeWorked.getText());
        String StartDate = txt_StartDate.getText();
        String date[] = StartDate.split("-");
        int end = (int) Math.ceil(TimeWorked / 24) + Integer.parseInt(date[2]);
        String EndDate = date[0] + "-" + date[1] + "-" + end;
        Service service = new Service(ID, CarID, Mileage, EmployeeID, TimeWorked, StartDate, EndDate);
        ServiceController.addService(service);
        list.add(service);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Услуга добавлена");
    }
    public void Edit(){
        int ID = Integer.parseInt(txt_ID.getText());
        int CarID = Integer.parseInt(txt_CarID.getText());
        int Mileage = Integer.parseInt(txt_Mileage.getText());
        int EmployeeID = Integer.parseInt(txt_EmployeeID.getText());
        int TimeWorked = Integer.parseInt(txt_TimeWorked.getText());
        String StartDate = txt_StartDate.getText();
        String date[] = StartDate.split("-");
        int end = (int) Math.ceil(TimeWorked / 24) + Integer.parseInt(date[2]);
        String EndDate = date[0] + "-" + date[1] + "-" + end;
        Service service = new Service(ID, CarID, Mileage, EmployeeID, TimeWorked, StartDate, EndDate);
        ServiceController.updateService(service);
        list.set(index, service);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Информация обновлена");
    }
    public void Delete(){
        int id = Integer.parseInt(txt_ID.getText());
        ServiceController.deleteService(id);
        list.remove(index);
        clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }
    private void clearFields() {
        txt_ID.setText("");
        txt_CarID.setText("");
        txt_TimeWorked.setText("");
        txt_StartDate.setText("");
        txt_Mileage.setText("");
        txt_EmployeeID.setText("");
    }
}
