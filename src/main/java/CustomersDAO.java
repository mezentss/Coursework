import java.util.List;

public interface CustomersDAO {
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}