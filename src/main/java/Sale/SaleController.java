package Sale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SaleController {
    private static final String SELECT_ALL = "SELECT * FROM Sales ";
    private static final String GET_SUM = "SELECT SUM(Price)\n" +
            "FROM Sales\n" +
            "JOIN Parts ON Sales.PartID = Parts.ID\n" +
            "WHERE Sales.ServiceID = ?";

    private static Connection _connection;

    public SaleController(Connection connection) {
        _connection = connection;
    }
    public static ObservableList<Sale> getSales(){
        ObservableList <Sale> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Sale(
                        rs.getInt("ID"),
                        rs.getInt("ServiceID"),
                        rs.getInt("PartID")));
            }
        } catch (Exception e){

        }
        return list;
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
}