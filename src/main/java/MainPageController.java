import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    private Button Cars;

    @FXML
    private Button Customers;

    @FXML
    private Button Details;

    @FXML
    private Button Employees;

    @FXML
    private Button Sales;

    @FXML
    private Button Services;
    private Stage mainStage;

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }
    @FXML
    void initialize() {
        Cars.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Cars.fxml"));
                Parent root = loader.load();
                CarPageController controller = loader.getController();
                //controller.setWelcomeText("Welcome, " + loginField.getText() + "!");
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
