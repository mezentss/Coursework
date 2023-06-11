package Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    private static final String SELECT_ALL = "SELECT * FROM Employees";
    public static Connection _connection;
    public EmployeeController(Connection connection) {
        _connection = connection;
    }

    public static ObservableList<Employee> getEmployees(){
        ObservableList <Employee> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Post")
                );
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}