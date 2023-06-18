import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountPageController {
    private static Account _account;
    private Stage mainStage;
    @FXML
    private TextField address, login, name, password, post;
    @FXML
    private Button Change, mainPage;
    PreparedStatement pst = null;
    public AccountPageController(Account account){_account = account;}
    public AccountPageController(){}
    @FXML
    void initialize() {
        setEmployeeData();
        mainPage.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = loader.load();
                MainPageController controller = loader.getController();
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public void setEmployeeData() {
        try {
            String sql = "SELECT * FROM Employees WHERE ID = ?";
            pst = _account.getConnection().prepareStatement(sql);
            pst.setInt(1, SystemLoginController.ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                name.setText(rs.getString("Name"));
                address.setText(rs.getString("Address"));
                post.setText(rs.getString("AccessLevel"));
                login.setText(rs.getString("Login"));
                password.setText(rs.getString("Password"));
            }

        } catch (SQLException ex) {
            System.out.println("Ошибка при извлечении данных о сотруднике: " + ex.getMessage());
        }
    }

    public void Edit(){
        try {
            String Name = name.getText();
            String Address = address.getText();
            String Post = post.getText();
            String Login = login.getText();
            String Password = password.getText();
            System.out.println(SystemLoginController.ID);

            String sql = "UPDATE Employees SET Name = '" + Name +
                    "', Address = '" + Address +
                    "', AccessLevel = '" + Post +
                    "', Login = '" + Login +
                    "', Password = '" + Password +
                    "' WHERE ID = " + SystemLoginController.ID + "; ";
            System.out.println(sql);

            pst = _account.getConnection().prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Информация обновлена");
            pst.executeUpdate();
            initialize();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
    }
}
