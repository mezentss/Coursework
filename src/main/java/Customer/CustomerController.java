package Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerController {
    private static final String SELECT_ALL = "SELECT * FROM Customers";
    public static Connection _connection;

    public CustomerController(Connection connection) {
        _connection = connection;
    }

    public static ObservableList<Customer> getCustomers(){
        ObservableList <Customer> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone")
                );
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}