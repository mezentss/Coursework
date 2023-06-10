package Customer;

import Customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl {
    private static final String SELECT_BY_ID = "SELECT * FROM Customers WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Customers";
    private static final String INSERT = "INSERT INTO Customers VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Customers SET Name = ?, Address = ?, Phone = ?, WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Customers WHERE ID = ?";

    private Connection _connection;

    public CustomerDAOImpl(Connection connection) {
        _connection = connection;
    }

    public Customer getCustomerById(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement statement = _connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public void addCustomer(Customer customer) {
        try (PreparedStatement statement = _connection.prepareStatement(INSERT)) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try (PreparedStatement statement = _connection.prepareStatement(UPDATE)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}