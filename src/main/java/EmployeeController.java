

import java.util.List;

public class EmployeeController {
    private EmployeeDAO _dao;

    public EmployeeController(EmployeeDAO dao) {
        _dao = dao;
    }

    public Employee getEmployeeById(int id) {
        return _dao.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return _dao.getAllEmployees();
    }

    public void addEmployee(Employee employee) {
        _dao.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        _dao.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        _dao.deleteEmployee(id);
    }
}

