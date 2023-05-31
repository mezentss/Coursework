
import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}