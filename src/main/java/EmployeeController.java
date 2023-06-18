import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    private static final String SELECT_ALL = "SELECT * FROM Employees";
    private static final String DELETE = "DELETE FROM Employees WHERE ID = ?";
    private static final String UPDATE = "UPDATE Employees SET Name = ?, Address = ?, AccessLevel = ? WHERE ID = ?";
    private static Employee _employee;
    private EmployeePageController _employeePageController;
    public EmployeeController(Employee employee, EmployeePageController employeePageController) {
        _employee = employee;
        _employeePageController = employeePageController;
    }

    public static ObservableList<Employee> getEmployees(){
        ObservableList <Employee> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _employee.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("AccessLevel")
                );
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void updateEmployee(Employee employee) {
        try {
            PreparedStatement ps = _employee.getConnection().prepareStatement(UPDATE);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getPost());
            ps.setInt(4, employee.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteEmployee(int id) {
        try {
            PreparedStatement ps = _employee.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}