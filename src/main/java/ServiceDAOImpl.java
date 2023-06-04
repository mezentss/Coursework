import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceDAOImpl implements ServiceDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM Services WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Services";
    private static final String INSERT = "INSERT INTO Services VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Services SET ID = ?, CarID = ?, Mileage = ?, EmployeeID = ?, TimeWorked = ?, StartDate = ?,  EndDate = ?, WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Services WHERE ID = ?";

    private Connection _connection;

    public ServiceDAOImpl(Connection connection) {
        _connection = connection;
    }

    @Override
    public Service getServiceById(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Service(
                        rs.getInt("ID"),
                        rs.getInt("CarID"),
                        rs.getInt("Mileage"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("TimeWorked"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();

        try (PreparedStatement statement = _connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("ID"),
                        rs.getInt("CarID"),
                        rs.getInt("Mileage"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("TimeWorked"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public void addService(Service service) {
        try (PreparedStatement statement = _connection.prepareStatement(INSERT)) {
            statement.setInt(1, service.getId());
            statement.setInt(2, service.getCarID());
            statement.setInt(3, service.getMileage());
            statement.setInt(4, service.getEmployeeID());
            statement.setInt(5, service.getTimeWorked());
            statement.setString(6, service.getStartDate());
            statement.setString(7, service.getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(Service service) {
        try (PreparedStatement statement = _connection.prepareStatement(UPDATE)) {
            statement.setInt(1, service.getCarID());
            statement.setInt(2, service.getMileage());
            statement.setInt(3, service.getEmployeeID());
            statement.setInt(4, service.getTimeWorked());
            statement.setString(5, service.getStartDate());
            statement.setString(6, service.getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteService(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}