

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AccountDAOImpl implements AccountDAO {
    private static final String SELECT_BY_LOGIN = "SELECT * FROM Accounts WHERE Login = ?";
    private static final String DELETE = "DELETE FROM Accounts WHERE Login = ?";

    private Connection _connection;

    public AccountDAOImpl(Connection connection) {
        _connection = connection;
    }

    @Override
    public Account getAccountByLogin(String login, String password) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getString("Login"),
                        rs.getString("Password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteAccount(String login) {
        try (PreparedStatement statement = _connection.prepareStatement(DELETE)) {
            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(String login, String password) {
        Account account = null;
        try {
            PreparedStatement stmt = _connection.prepareStatement("SELECT * FROM Accounts WHERE Login = ? AND Password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new Account(rs.getString("Login"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void close() {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
