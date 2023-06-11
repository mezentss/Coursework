import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    private Button Cars, Customers, Details, Employees, Sales, Services;
    private Stage mainStage;

    public void setMainStage(Stage stage) {
        mainStage = stage;
        Cars.setOnAction(this::handleCarsButton);
        Employees.setOnAction(this::handleEmployeesButton);
        Sales.setOnAction(this::handleSalesButton);
        Customers.setOnAction(this::handleCustomersButton);
        Details.setOnAction(this::handlePartsButton);
        Services.setOnAction(this::handleServicesButton);
    }

    @FXML
    void handleCarsButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cars.fxml"));
            Parent root = loader.load();
            CarPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handlePartsButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Parts.fxml"));
            Parent root = loader.load();
            PartPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleEmployeesButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Employees.fxml"));
            Parent root = loader.load();
            EmployeePageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSalesButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sales.fxml"));
            Parent root = loader.load();
            SalesPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCustomersButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
            Parent root = loader.load();
            CustomerPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleServicesButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Services.fxml"));
            Parent root = loader.load();
            ServicePageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
