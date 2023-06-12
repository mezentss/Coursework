package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountController {
    private static final String SELECT_BY_LOGIN = "SELECT * FROM Accounts WHERE Login = ?";
    private static final String DELETE = "DELETE FROM Accounts WHERE Login = ?";

    public static Connection _connection;

    public AccountController(Connection connection) {
        _connection = connection;
    }

    public Account getAccountByLogin(String login, String password) {
        try (PreparedStatement statement = _connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getString("Login"),
                        rs.getString("Password"),
                        rs.getString("AccessLevel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

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
                account = new Account(rs.getString("Login"), rs.getString("Password"), rs.getString("AccessLevel"));
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
