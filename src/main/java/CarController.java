import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarController {
    private static Car _car;
    private CarPageController _carPageController;
    public CarController(Car car, CarPageController carPageController){
        _car = car;
        _carPageController = carPageController;
    }
    private static final String SELECT_ALL = "SELECT * FROM Cars";
    private static final String INSERT = "INSERT INTO Cars VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Cars WHERE ID = ?";
    private static final String UPDATE = "UPDATE Cars SET LicensePlate = ?, Brand = ?, Model = ?, Color = ?, OwnerID = ? WHERE ID = ?";

    public static ObservableList <Car> getCars(){
        ObservableList <Car> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _car.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Car(
                        rs.getInt("ID"),
                        rs.getString("LicensePlate"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getString("Color"),
                        rs.getInt("OwnerID")));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
        return list;
    }

    public static void addCar(Car car) {
        try {
            PreparedStatement ps = _car.getConnection().prepareStatement(INSERT);
            ps.setInt(1, car.getID());
            ps.setString(2, car.getLicensePlate());
            ps.setString(3, car.getBrand());
            ps.setString(4, car.getModel());
            ps.setString(5, car.getColor());
            ps.setInt(6, car.getOwnerID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCar(int id) {
        try {
            PreparedStatement ps = _car.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCar(Car car) {
        try {
            PreparedStatement ps = _car.getConnection().prepareStatement(UPDATE);
            ps.setString(1, car.getLicensePlate());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getColor());
            ps.setInt(5, car.getOwnerID());
            ps.setInt(6, car.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
