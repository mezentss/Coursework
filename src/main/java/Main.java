import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {

        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2258_coursework",
                    "std_2258_coursework", "00000000");

            EmployeeDAO dao = new EmployeeDAOImpl(connection);
            EmployeeController controller1 = new EmployeeController(dao);
            EmployeeView view1 = new EmployeeView(controller1);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println();
                System.out.println("1. Вывести все данные о сотрудниках");
                System.out.println("2. Вывести данные о сутруднике по ID");
                System.out.println("3. Добавить нового сотрудника");
                System.out.println("4. Обновить информацию о сотруднике");
                System.out.println("5. Удалить данные о сотруднике");
                System.out.println("6. Выйти");
                System.out.print("Выберите действие: ");

                int choice1 = scanner.nextInt();

                switch (choice1) {
                    case 1:
                        List<Employee> allEmployees = controller1.getAllEmployees();
                        view1.displayAllEmployees(allEmployees);
                        break;
                    case 2:
                        System.out.print("Введите ID сотрудника: ");
                        int id = scanner.nextInt();
                        Employee employee = controller1.getEmployeeById(id);
                        if (employee != null) {
                            view1.displayEmployee(employee);
                        } else {
                            System.out.println("Сотрудник с ID " + id + " не найден.");
                        }
                        break;
                    case 3:
                        view1.addEmployee();
                        break;
                    case 4:
                        view1.updateEmployee();
                        break;
                    case 5:
                        view1.deleteEmployee();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }

                System.out.println();
                System.out.println("1. Вывести все данные о деталях");
                System.out.println("2. Вывести данные о детали по ID");
                System.out.println("3. Добавить новую деталь");
                System.out.println("4. Обновить информацию о детали");
                System.out.println("5. Удалить данные о детали");
                System.out.println("6. Выйти");
                System.out.print("Выберите действие: ");

                PartDAO partDAO = new PartDAOImpl(connection);
                PartController controller2 = new PartController(partDAO);
                PartView view2 = new PartView(controller2);

                int choice2 = scanner.nextInt();

                switch (choice2) {
                    case 1:
                        List<Part> allParts = controller2.getAllParts();
                        view2.displayAllParts(allParts);
                        break;
                    case 2:
                        System.out.print("Введите ID детали: ");
                        int id = scanner.nextInt();
                        Part part = controller2.getPartById(id);
                        if (part != null) {
                            view2.displayPart(part);
                        } else {
                            System.out.println("Деталь с ID " + id + " не найдена.");
                        }
                        break;
                    case 3:
                        view2.addPart();
                        break;
                    case 4:
                        view2.updatePart();
                        break;
                    case 5:
                        view2.deletePart();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }

                System.out.println();
                System.out.println("1. Вывести все данные об автомобилях");
                System.out.println("2. Вывести данные об автомобиле по ID");
                System.out.println("3. Добавить новый автомобиль");
                System.out.println("4. Обновить информацию об автомобиле");
                System.out.println("5. Удалить данные об автомобиле");
                System.out.println("6. Выйти");
                System.out.print("Выберите действие: ");

                CarDAO carDAO = new CarDAOImpl(connection);
                CarController controller3 = new CarController(carDAO);
                CarView view3 = new CarView(controller3);

                int choice3 = scanner.nextInt();

                switch (choice3) {
                    case 1:
                        List<Car> allCars = controller3.getAllCars();
                        view3.displayAllCars(allCars);
                        break;
                    case 2:
                        System.out.print("Введите ID автомобиля: ");
                        int id = scanner.nextInt();
                        Car car = controller3.getCarById(id);
                        if (car != null) {
                            view3.displayCar(car);
                        } else {
                            System.out.println("Автомобиль с ID " + id + " не найден.");
                        }
                        break;
                    case 3:
                        view3.addCar();
                        break;
                    case 4:
                        view3.updateCar();
                        break;
                    case 5:
                        view3.deleteCar();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }

                System.out.println();
                System.out.println("1. Вывести все данные о клиентах");
                System.out.println("2. Вывести данные о клиенте по ID");
                System.out.println("3. Добавить нового клиента");
                System.out.println("4. Обновить информацию о клиенте");
                System.out.println("5. Удалить данные о клиенте");
                System.out.println("6. Выйти");
                System.out.print("Выберите действие: ");

                CustomersDAO customersDAO = new CustomerDAOImpl(connection);
                CustomerController controller4 = new CustomerController(customersDAO);
                CustomerView view4 = new CustomerView(controller4);

                int choice4 = scanner.nextInt();

                switch (choice4) {
                    case 1:
                        List<Customer> allCustomers = controller4.getAllCCustomers();
                        view4.displayAllCustomers(allCustomers);
                        break;
                    case 2:
                        System.out.print("Введите ID клиента: ");
                        int id = scanner.nextInt();
                        Customer customer = controller4.getCustomerById(id);
                        if (customer != null) {
                            view4.displayCustomer(customer);
                        } else {
                            System.out.println("Клиент с ID " + id + " не найден.");
                        }
                        break;
                    case 3:
                        view4.addCustomer();
                        break;
                    case 4:
                        view4.updateCustomer();
                        break;
                    case 5:
                        view4.deleteCustomer();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }

                ServiceDAO serviceDAO= new ServiceDAOImpl(connection);
                ServiceController controller5 = new ServiceController(serviceDAO);
                ServiceView view5 = new ServiceView(controller5);

                System.out.println();
                System.out.println("1. Вывести все данные об установке");
                System.out.println("2. Вывести данные об установке по ID");
                System.out.println("3. Добавить информацию об установке");
                System.out.println("4. Удалить данные об установке");
                System.out.println("5. Выйти");
                System.out.print("Выберите действие: ");

                int choice5 = scanner.nextInt();

                    switch (choice5) {
                        case 1:
                            List<Service> allServices = controller5.getAllServices();
                            view5.displayAllServices(allServices);
                            break;
                        case 2:
                            System.out.print("Введите ID услуги: ");
                            int id = scanner.nextInt();
                            Service service = controller5.getServiceById(id);
                            if (service != null) {
                                view5.displayService(service);
                            } else {
                                System.out.println("Услуга с ID " + id + " не найдена.");
                            }
                            break;
                        case 3:
                            view5.addService();
                            break;
                        case 4:
                            view5.deleteService();
                            break;
                        case 5:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Некорректный выбор.");
                            break;
                    }

                SaleDAO saleDAO= new SaleDAOImpl(connection);
                SaleController controller6 = new SaleController(saleDAO);
                SaleView view6 = new SaleView(controller6);

                System.out.println();
                System.out.println("1. Вывести все данные об услугах");
                System.out.println("2. Вывести данные об услуге по ID");
                System.out.println("3. Добавить информацию об услуге");
                System.out.println("4. Вывести суммарную стоимость всех установленных деталей");
                System.out.println("5. Удалить данные об услуге");
                System.out.println("6. Выйти");
                System.out.print("Выберите действие: ");

                int choice6 = scanner.nextInt();

                switch (choice6) {
                    case 1:
                        List<Sale> sales = controller6.getAllSales();
                        view6.displayAllSales(sales);
                        break;
                    case 2:
                        System.out.print("Введите ID услуги: ");
                        int id = scanner.nextInt();
                        Sale sale = controller6.getSaleById(id);
                        if (sale != null) {
                            view6.displaySale(sale);
                        } else {
                            System.out.println("Услуга с ID " + id + " не найден.");
                        }
                        break;
                    case 3:
                        view6.addSale();
                        break;
                    case 5:
                        view6.deleteSale();
                        break;
                    case 4:
                        view6.getSum();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный выбор.");
                        break;
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}



