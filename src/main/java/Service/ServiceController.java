package Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceController {
    public static Connection conn;

    public ServiceController(Connection conn) {
        this.conn = conn;
    }
    private static final String SELECT_ALL = "SELECT * FROM Services";
    public static ObservableList <Service> getServices(){
        ObservableList <Service> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Service(
                        rs.getInt("ID"),
                        rs.getInt("CarID"),
                        rs.getInt("Mileage"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("TimeWorked"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate")));
            }
        } catch (Exception e){

        }
        return list;
    }
}