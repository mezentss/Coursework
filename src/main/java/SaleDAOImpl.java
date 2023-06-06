import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;


public class SaleDAOImpl {
    private static final String SELECT_BY_ID = "SELECT * FROM Sales  WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Sales ";
    private static final String INSERT = "INSERT INTO Sales  VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM Sales  WHERE ID = ?";
    private static final String GET_SUM = "SELECT SUM(Price)\n" +
            "FROM Sales\n" +
            "JOIN Parts ON Sales.PartID = Parts.ID\n" +
            "WHERE Sales.ServiceID = ?";

    private Connection _connection;

    public SaleDAOImpl(Connection connection) {
        _connection = connection;
    }


    public Sale getSaleById(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Sale(
                        rs.getInt("ID"),
                        rs.getInt("ServiceID"),
                        rs.getInt("PartID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();

        try (PreparedStatement statement = _connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Sale sale = new Sale(
                        rs.getInt("ID"),
                        rs.getInt("ServiceID"),
                        rs.getInt("PartID")
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }


    public void addSale(Sale sale) {
        try (PreparedStatement statement = _connection.prepareStatement(INSERT)) {
            statement.setInt(1, sale.getID());
            statement.setInt(2, sale.getServiceID());
            statement.setInt(3, sale.getPartID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getSum(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(GET_SUM)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int sum = rs.getInt(1);
                System.out.println("Сумма для услуги с ID" + id + ": " + sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteSale(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}