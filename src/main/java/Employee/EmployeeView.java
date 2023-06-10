package Employee;

import Employee.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeView {
    private EmployeeController _controller;

    public EmployeeView(EmployeeController controller) {
        _controller = controller;
    }

    public void displayEmployee(Employee employee) {
        System.out.println("ID: " + employee.getId());
        System.out.println("Имя: " + employee.getName());
        System.out.println("Адрес: " + employee.getAddress());
        System.out.println("Должность: " + employee.getPost());
    }

    public void displayAllEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            displayEmployee(employee);
        }
    }

    public void addEmployee() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID сотрудника: ");
        int id = scanner1.nextInt();
        System.out.print("Введите имя сотрудника: ");
        String name = scanner2.nextLine();
        System.out.print("Введите адрес сотрудника: ");
        String address = scanner2.nextLine();
        System.out.print("Введите должность сотрудника: ");
        String post = scanner2.nextLine();

        Employee employee = new Employee(id, name, address, post);
        _controller.addEmployee(employee);
    }

    public void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID сотрудника, данные о котором следует обновить: ");
        int id = scanner.nextInt();
        Employee employee = _controller.getEmployeeById(id);

        if (employee != null) {
            System.out.print("Введите новую должность сотрудника: ");
            String post = scanner.nextLine();
            System.out.print("Введите новый адрес сотрудника: ");
            String address = scanner.nextLine();
            employee.setPost(post);
            employee.setAddress(address);
            _controller.updateEmployee(employee);
        } else {
            System.out.println("Сотрудник с ID " + id + " не найден.");
        }
    }

    public void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID сотрудника, которого требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deleteEmployee(id);
    }
}