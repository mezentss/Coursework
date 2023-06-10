import Car.Car;
import Car.CarDAOImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Car.CarDAOImpl.conn;

public class CarPageController  {

    @FXML
    private Button Add;

    @FXML
    private TableColumn<Car, String> Brand;

    @FXML
    private TableColumn<Car, String> Color;

    @FXML
    private Button Delete;

    @FXML
    private TableColumn<Car, Integer> ID;

    @FXML
    private TableColumn<Car, String> LicensePlate;

    @FXML
    private MenuButton Menu;

    @FXML
    private TableColumn<Car, String> Model;

    @FXML
    private TableColumn<Car, Integer> OwnerID;

    @FXML
    private Button Update;

    @FXML
    private TableView<Car> Table;


    public TextField txt_Brand;

    public TextField txt_Color;


    public TextField txt_LicensePlate;


    public TextField txt_Model;


    public TextField txt_OwnerId;
    public TextField txt_ID;

    ObservableList <Car> list;
    int index = -1;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Stage mainStage;


    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("ID"));
        LicensePlate.setCellValueFactory(new PropertyValueFactory<Car, String>("LicensePlate"));
        Brand.setCellValueFactory(new PropertyValueFactory<Car, String>("Brand"));
        Model.setCellValueFactory(new PropertyValueFactory<Car, String>("Model"));
        Color.setCellValueFactory(new PropertyValueFactory<Car, String>("Color"));
        OwnerID.setCellValueFactory(new PropertyValueFactory<Car, Integer>("OwnerID"));

        list = CarDAOImpl.getCars();
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
        txt_LicensePlate.setText(LicensePlate.getCellData(index));
        txt_Brand.setText(Brand.getCellData(index));
        txt_Model.setText(Model.getCellData(index));
        txt_Color.setText(Color.getCellData(index));
        txt_OwnerId.setText(OwnerID.getCellData(index).toString());
    }

   public void addCar() {
    String sql = "INSERT INTO Cars values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(2, txt_LicensePlate.getText());
            pst.setString(3, txt_Brand.getText());
            pst.setString(4, txt_Model.getText());
            pst.setString(5, txt_Color.getText());
            pst.setInt(6, Integer.parseInt(txt_OwnerId.getText()));
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));


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
            String licensePlate = txt_LicensePlate.getText();
            String brand = txt_Brand.getText();
            String model = txt_Model.getText();
            String color = txt_Color.getText();
            String ownerId = txt_OwnerId.getText();

            String sql = "UPDATE Cars SET LicensePlate = '" + licensePlate +
                    "', Brand = '" + brand +
                    "', Model = '" + model +
                    "', Color = '" + color +
                    "', OwnerID = '" + ownerId +
                    "' WHERE ID = " + id + "; ";

            pst = conn.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Информация обновлена");
            pst.executeUpdate();
            initialize();
        }catch (Exception e){
        }
    }

    public void Delete(){
        String sql = "DELETE FROM Cars WHERE ID = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Информация удалена");
            initialize();

        }
        catch (Exception e){

        }
    }
}
