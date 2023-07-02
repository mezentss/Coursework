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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemLoginController {
    private static Connection _connection;
    public SystemLoginController (Connection connection){_connection = connection;}
    public SystemLoginController(){}
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
    public static String accessLevel;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private final String sqlAccess = "SELECT AccessLevel FROM Employees WHERE ID = ?";
    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    @FXML
    void initialize() {
        paneLogin.setVisible(true);
        paneSignUp.setVisible(false);
        typeUp.getItems().addAll( "Администратор", "Механик");
        type.getItems().addAll("Владелец", "Администратор", "Механик");
    }

    public void SignUpPageShow(){
        paneLogin.setVisible(false);
        paneSignUp.setVisible(true);
    }

    @FXML
    public void Login() throws Exception{
        String sql = "SELECT ID, Password FROM Employees WHERE Login = ? AND AccessLevel = ?";
        try {
            pst = _connection.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, type.getValue());
            rs = pst.executeQuery();
            if(rs.next()){
                String passwordHash = rs.getString("Password");
                if (checkPassword(txt_password.getText(), passwordHash)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    Parent root = loader.load();
                    MainPageController controller = loader.getController();
                    controller.setMainStage(mainStage);
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    ID = rs.getInt("ID");
                    try {
                        pst = _connection.prepareStatement(sqlAccess);
                        pst.setInt(1, ID);
                        rs = pst.executeQuery();
                        if(rs.next()){accessLevel = rs.getString(1);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
    }

    public void add(){
        String sql = "INSERT INTO Employees values ( ?, ?, ?, ?, ?, ?)";
        try {
            pst = _connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_ID.getText()));
            pst.setString(2, txt_name.getText());
            pst.setString(3, txt_address.getText());
            pst.setString(4, typeUp.getValue());
            pst.setString(5, txt_usernameUp.getText());
            pst.setString(6, hashPassword(txt_passwordUp.getText()));
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
        } catch (SQLException ex) {
            System.out.println(pst);
            JOptionPane.showMessageDialog(null, "Ошибка");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return bytesToHexString(hashBytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static boolean checkPassword(String password, String passwordHash) {
        return hashPassword(password).equals(passwordHash);
    }
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}