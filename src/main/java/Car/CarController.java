package Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarController {
    public static Connection conn;

    public CarController(Connection conn) {
        this.conn = conn;
    }
    private static final String SELECT_ALL = "SELECT * FROM Cars";
    public static ObservableList <Car> getCars(){
        ObservableList <Car> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
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

        }
        return list;
    }
}