import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServiceView {
    private ServiceController _controller;

    public ServiceView(ServiceController controller) {
        _controller = controller;
    }

    public void displayService(Service customer) {
        System.out.println("ID: " + customer.getId());
        System.out.println("ID автомобиля:" + customer.getCarID());
        System.out.println("Пробег:" + customer.getMileage());
        System.out.println("ID владельца:" + customer.getEmployeeID());
        System.out.println("Время работы:" + customer.getTimeWorked());
        System.out.println("Дата начала работы:" + customer.getStartDate());
        System.out.println("Дата окончания работы:" + customer.getEndDate());
    }

    public void displayAllServices(List<Service> services) {
        for (Service service : services) {
            displayService(service);
        }
    }

    public void addService() throws ClassNotFoundException {
        Connection connection = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2258_coursework",
                    "std_2258_coursework", "00000000");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CarDAO customersDAO = new CarDAOImpl(connection);
        CarController controller = new CarController(customersDAO);
        CarView view = new CarView(controller);

        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID услуги: ");
        int id = scanner1.nextInt();
        System.out.print("Введите ID автомобиля: ");
        int carId = scanner1.nextInt();
        Car car = controller.getCarById(carId);
        System.out.print("Введите пробег: ");
        int mileage = scanner1.nextInt();
        int employeeId = car.getOwnerID();
        System.out.print("Введите время работы: ");
        int timeWorked = scanner1.nextInt();
        System.out.print("Введите дату начала работы: ");
        String startDate  = scanner2.nextLine();
        System.out.print("Введите дату окончания работы: ");
        String endDate  = scanner2.nextLine();

        Service service = new Service(id, carId, mileage, employeeId, timeWorked, startDate, endDate);
        _controller.addService(service);
    }


    public void deleteService() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID услуги, которую требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deleteService(id);
    }
}