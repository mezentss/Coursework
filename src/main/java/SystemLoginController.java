import Employee.EmployeeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Account.AccountController._connection;

public class SystemLoginController {
    @FXML
    private Button loginButton, registrationButton, toRegistrationButton;

    @FXML
    private AnchorPane paneLogin, paneSignUp;
    @FXML
    private PasswordField txt_password, txt_passwordUp;
    @FXML
    private TextField txt_username, txt_usernameUp, txt_address, txt_name, txt_ID;

    @FXML
    private ComboBox<String> type, typeUp;
    private Stage mainStage;
    public static int ID;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    @FXML
    void initialize() {
        paneLogin.setVisible(true);
        paneSignUp.setVisible(false);
        typeUp.getItems().addAll("Владелец", "Администратор", "Механик");
        type.getItems().addAll("Владелец", "Администратор", "Механик");
    }

    public void LoginPageShow(){
       paneLogin.setVisible(true);
       paneSignUp.setVisible(false);
    }

    public void SignUpPageShow(){
        paneLogin.setVisible(false);
        paneSignUp.setVisible(true);
    }

    @FXML
    public void Login() throws Exception{
        String sql = "SELECT ID FROM Employees WHERE Login = ? AND Password = ? AND AccessLevel = ?";
        try {
            pst = _connection.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, type.getValue());
            rs = pst.executeQuery();
            if(rs.next()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = loader.load();
                MainPageController controller = loader.getController();
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                ID = rs.getInt(1);
                System.out.println(ID);
            }else {
                JOptionPane.showMessageDialog(null, "Данные некорректны");
            }
        }catch (Exception e){

            JOptionPane.showMessageDialog(null, "Ошибка");
        }
    }

    public void add(){
        String sql = "INSERT INTO Employees (Name, Address, AccessLevel, Login, Password, ID) values ( ?, ?, ?, ?, ?, ?)";
        try {
            pst = EmployeeController._connection.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_address.getText());
            pst.setString(3, typeUp.getValue());
            pst.setString(4, txt_username.getText());
            pst.setString(5, txt_password.getText());
            pst.setString(6, txt_ID.getText());
            ID = Integer.parseInt(txt_ID.getText());


            JOptionPane.showMessageDialog(null, "Сотрудник успешно добавлен");
            pst.execute();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            Parent root = loader.load();
            MainPageController controller = loader.getController();
            controller.setMainStage(mainStage);
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            initialize();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}