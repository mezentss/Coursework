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

public class CarPageController {
    public CarPageController(Car car) {
        _car = car;
    }

    public CarPageController() {}

    private static Car _car;
    @FXML
    private Button Add, Delete, Update, MenuBotton;
    @FXML
    private TableColumn<Car, String> Model, LicensePlate, Color, Brand;
    @FXML
    private TableColumn<Car, Integer> ID, OwnerID;
    @FXML
    private TableView<Car> Table;
    public TextField txt_Brand, txt_Color, txt_LicensePlate, txt_Model, txt_OwnerId, txt_ID;
    ObservableList <Car> list;
    int index = -1;
    private Stage mainStage;

    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("ID"));
        LicensePlate.setCellValueFactory(new PropertyValueFactory<Car, String>("LicensePlate"));
        Brand.setCellValueFactory(new PropertyValueFactory<Car, String>("Brand"));
        Model.setCellValueFactory(new PropertyValueFactory<Car, String>("Model"));
        Color.setCellValueFactory(new PropertyValueFactory<Car, String>("Color"));
        OwnerID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("OwnerID"));

        list = CarController.getCars();
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

    public void addCar() {
        int ID = Integer.parseInt(txt_ID.getText());
        String licensePlate = txt_LicensePlate.getText();
        String brand = txt_Brand.getText();
        String model = txt_Model.getText();
        String color = txt_Color.getText();
        int ownerID = Integer.parseInt(txt_OwnerId.getText());
        Car car = new Car(ID, licensePlate, brand, model, color, ownerID);
        CarController.addCar(car);
        list.add(car);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Автомобиль добавлен");
    }

    public void Delete() {
        int id = Integer.parseInt(txt_ID.getText());
        CarController.deleteCar(id);
        list.remove(index);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }

    public void Edit() {
        String licensePlate = txt_LicensePlate.getText();
        String brand = txt_Brand.getText();
        String model = txt_Model.getText();
        String color = txt_Color.getText();
        int ownerID = Integer.parseInt(txt_OwnerId.getText());
        int id = Integer.parseInt(txt_ID.getText());
        Car car = new Car(id, licensePlate, brand, model, color, ownerID);
        CarController.updateCar(car);
        list.set(index, car);
        JOptionPane.showMessageDialog(null, "Информация обновлена");
        clearFields();
    }

    @FXML
    public void getSelected() {
        index = Table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_ID.setText(ID.getCellData(index).toString());
        txt_LicensePlate.setText(LicensePlate.getCellData(index));
        txt_Brand.setText(Brand.getCellData(index));
        txt_Model.setText(Model.getCellData(index));
        txt_Color.setText(Color.getCellData(index));
        txt_OwnerId.setText(OwnerID.getCellData(index).toString());
    }

    private void clearFields() {
        txt_ID.setText("");
        txt_LicensePlate.setText("");
        txt_Brand.setText("");
        txt_Model.setText("");
        txt_Color.setText("");
        txt_OwnerId.setText("");
    }
}