import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();
        SystemLoginController controller = loader.getController();
        controller.setMainStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2258_coursework",
                    "std_2258_coursework", "00000000");

            SystemLoginController systemLoginController = new SystemLoginController(connection);

            Car car = new Car(connection);
            CarPageController carPageController = new CarPageController(car);
            CarController carController = new CarController(car, carPageController);

            Employee employee = new Employee(connection);
            EmployeePageController employeePageController = new EmployeePageController(employee);
            EmployeeController employeeController = new EmployeeController(employee, employeePageController);

            Sale sale = new Sale(connection);
            SalePageController salePageController = new SalePageController(sale);
            SaleController saleController = new SaleController(sale, salePageController);

            Customer customer = new Customer(connection);
            CustomerPageController customerPageController = new CustomerPageController(customer);
            CustomerController customerController = new CustomerController(customer, customerPageController);

            Part part = new Part(connection);
            PartPageController partPageController = new PartPageController(part);
            PartController partController = new PartController(part, partPageController);

            Service service = new Service(connection);
            ServicePageController servicePageController = new ServicePageController(service);
            ServiceController serviceController = new ServiceController(service, servicePageController);

            Account account = new Account(connection);
            AccountPageController accountPageController = new AccountPageController(account);
            AccountController accountController = new AccountController(account, accountPageController);
            launch(args);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}





