import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MainPageController {
    @FXML
    private Button Cars, Customers, Details, Employees, Sales, Services, exit, personalAccount;
    private Stage mainStage;

    public void setMainStage(Stage stage) {
        mainStage = stage;
        Customers.setOnAction(this::handleCustomersButton);
        Details.setOnAction(this::handlePartsButton);
        Employees.setOnAction(this::handleEmployeesButton);
        Cars.setOnAction(this::handleCarsButton);
        Sales.setOnAction(this::handleSalesButton);
        Services.setOnAction(this::handleServicesButton);
        exit.setOnAction(this::exitButton);
        personalAccount.setOnAction(this::accountButton);
    }

    @FXML
    void handleCarsButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Механик") || post.equals("Администратор") || post.equals("Владелец")) {
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
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }
    @FXML
    void handlePartsButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Механик") || post.equals("Администратор") || post.equals("Владелец")) {
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
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }
    @FXML
    void handleEmployeesButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Администратор") || post.equals("Владелец")) {
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
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }

    @FXML
    void handleSalesButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Администратор") || post.equals("Владелец")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sales.fxml"));
                Parent root = loader.load();
                SalePageController controller = loader.getController();
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }

    @FXML
    void handleCustomersButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Администратор") || post.equals("Владелец")) {
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
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }

    @FXML
    void handleServicesButton(ActionEvent actionEvent) {
        String post = SystemLoginController.accessLevel;
        if (post.equals("Механик") || post.equals("Администратор") || post.equals("Владелец")) {
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
        else {JOptionPane.showMessageDialog(null, "Вы не имеете доступа к данным.");}
    }

    @FXML
    void exitButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();
            SystemLoginController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void accountButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonalAccount.fxml"));
            Parent root = loader.load();
            AccountPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
