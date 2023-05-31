import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private CustomerController _controller;

    public CustomerView(CustomerController controller) {
        _controller = controller;
    }

    public void displayCustomer(Customer customer) {
        System.out.println("ID: " + customer.getId());
        System.out.println("Клиент:" + customer.getName());
        System.out.println("Номер телефона:" + customer.getPhone());
        System.out.println("Адрес:" + customer.getAddress());
    }

    public void displayAllCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            displayCustomer(customer);
        }
    }

    public void addCustomer() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID клиента: ");
        int id = scanner1.nextInt();
        System.out.print("Введите ФИО клиента: ");
        String name = scanner2.nextLine();
        System.out.print("Введите мобильный номер клиента: ");
        String phone = scanner2.nextLine();
        System.out.print("Введите адрес клиента: ");
        String address = scanner2.nextLine();
        Customer customer = new Customer(id, name, phone, address);
        _controller.addCustomer(customer);
    }

    public void updateCustomer() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID клиента, данные о котором следует обновить: ");
        int id = scanner1.nextInt();
        Customer customer = _controller.getCustomerById(id);

        if (customer != null) {
            System.out.print("Введите ФИО клиента: ");
            String name = scanner2.nextLine();
            System.out.print("Введите мобильный номер клиента: ");
            String phone = scanner2.nextLine();
            System.out.print("Введите адрес клиента: ");
            String address = scanner2.nextLine();

            _controller.updateCustomer(customer);
        } else {
            System.out.println("Клиент с ID " + id + " не найден.");
        }
    }

    public void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID клиента, которого требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deleteCustomer(id);
    }
}