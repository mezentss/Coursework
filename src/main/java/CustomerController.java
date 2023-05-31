import java.util.List;

public class CustomerController {
    private CustomersDAO _dao;

    public CustomerController(CustomersDAO dao) {
        _dao = dao;
    }

    public Customer getCustomerById(int id) {
        return _dao.getCustomerById(id);
    }

    public List<Customer> getAllCCustomers() {
        return _dao.getAllCustomers();
    }

    public void addCustomer(Customer customer) {
        _dao.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        _dao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        _dao.deleteCustomer(id);
    }
}