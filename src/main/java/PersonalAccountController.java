import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static Employee.EmployeeController._connection;

public class PersonalAccountController {
    private Stage mainStage;
    @FXML
    private TextField address, login, name, password, post;
    PreparedStatement pst = null;

    @FXML
    void initialize() {
        setEmployeeData();
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public void setEmployeeData() {
        try {
            String sql = "SELECT * FROM Employees WHERE ID = ?";
            pst = _connection.prepareStatement(sql);
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
}
