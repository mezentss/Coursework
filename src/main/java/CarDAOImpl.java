import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarDAOImpl implements CarDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM Cars WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Cars";
    private static final String INSERT = "INSERT INTO Cars VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Cars SET LicensePlate = ?, Brand = ?, Model = ?, Color = ?, WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Cars WHERE ID = ?";

    private Connection conn;

    public CarDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Car getCarById(int id) {
        try (PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Car(
                        rs.getInt("ID"),
                        rs.getString("LicensePlate"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getString("Color"),
                        rs.getInt("OwnerID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("ID"),
                        rs.getString("LicensePlate"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getString("Color"),
                        rs.getInt("OwnerID")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public void addCar(Car car) {
        try (PreparedStatement statement = conn.prepareStatement(INSERT)) {
            statement.setInt(1, car.getId());
            statement.setString(2, car.getLicensePlate());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getModel());
            statement.setString(5, car.getColor());
            statement.setInt(6, car.getOwnerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car car) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE)) {
            statement.setString(1, car.getLicensePlate());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getColor());
            statement.setInt(5, car.getOwnerID());
            statement.setInt(6, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(int id) {
        try (PreparedStatement statement = conn.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}