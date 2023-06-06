
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDAOImpl{
    private static final String SELECT_BY_ID = "SELECT * FROM Parts WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Parts";
    private static final String INSERT = "INSERT INTO Parts VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Parts SET Category = ?, Model = ?, SerialNumber = ?, Price = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Parts WHERE ID = ?";

    private Connection _connection;

    public PartDAOImpl(Connection connection) {
        _connection = connection;
    }

    public Part getPartById(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Part(
                        rs.getInt("ID"),
                        rs.getString("Category"),
                        rs.getString("Model"),
                        rs.getString("SerialNumber"),
                        rs.getInt("Price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Part> getAllParts() {
        List<Part> parts = new ArrayList<>();

        try (PreparedStatement statement = _connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Part part = new Part(
                        rs.getInt("ID"),
                        rs.getString("Category"),
                        rs.getString("Model"),
                        rs.getString("SerialNumber"),
                        rs.getInt("Price")
                );
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public void addPart(Part part) {
        try (PreparedStatement statement = _connection.prepareStatement(INSERT)) {
            statement.setInt(1, part.getId());
            statement.setString(2, part.getCategory());
            statement.setString(3, part.getModel());
            statement.setString(4, part.getSerialNumber());
            statement.setInt(5, part.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePart(Part part) {
        try (PreparedStatement statement = _connection.prepareStatement(UPDATE)) {
            statement.setString(1, part.getCategory());
            statement.setString(2, part.getModel());
            statement.setString(3, part.getSerialNumber());
            statement.setInt(4, part.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePart(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
