import Account.AccountController;
import Car.CarController;
import Customer.CustomerController;
import Employee.EmployeeController;
import Part.PartController;
import Sale.SaleController;
import Service.ServiceController;
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
            CarController carController = new CarController(connection);
            EmployeeController employeeController = new EmployeeController(connection);
            SaleController saleController = new SaleController(connection);
            CustomerController customerController = new CustomerController(connection);
            PartController partController = new PartController(connection);
            ServiceController serviceController = new ServiceController(connection);
            AccountController accountController = new AccountController(connection);
            launch(args);
                }
        catch(Exception e){
                    System.out.println(e);
                }
            }
        }





