import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerController {
    private static Customer _customer;
    private CustomerPageController _customerPageController;
    private static final String SELECT_ALL = "SELECT * FROM Customers";
    private static final String INSERT = "INSERT INTO Customers VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Customers WHERE ID = ?";
    private static final String UPDATE = "UPDATE Customers SET Name = ?, Address = ?, Phone = ? WHERE ID = ? ";

    public CustomerController(Customer customer, CustomerPageController customerPageController) {
        _customer = customer;
        _customerPageController = customerPageController;
    }

    public static ObservableList<Customer> getCustomers(){
        ObservableList <Customer> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _customer.getConnection().prepareStatement(SELECT_ALL);
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

    public static void addCustomer(Customer customer) {
        try {
            PreparedStatement ps = _customer.getConnection().prepareStatement(INSERT);
            ps.setInt(1, customer.getID());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateCustomer(Customer customer) {
        try {
            PreparedStatement ps = _customer.getConnection().prepareStatement(UPDATE);
            ps.setInt(1, customer.getID());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteCustomer(int id) {
        try {
            PreparedStatement ps = _customer.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}