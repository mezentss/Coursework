import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM Employees WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Employees";
    private static final String INSERT = "INSERT INTO Employees VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Employees SET Name = ?, Address = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Employees WHERE ID = ?";

    private Connection _connection;

    public EmployeeDAOImpl(Connection connection) {
        _connection = connection;
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = _connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        try (PreparedStatement statement = _connection.prepareStatement(INSERT)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (PreparedStatement statement = _connection.prepareStatement(UPDATE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getAddress());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}