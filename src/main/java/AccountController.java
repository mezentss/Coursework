import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountController {
    private static Account _account;
    private AccountPageController _accountPageController;
    private static final String SELECT = "SELECT * FROM Employees WHERE ID = ?";
    private static final String UPDATE = "UPDATE Employees SET Name = ?, Address = ?, AccessLevel = ?, Login = ?, Password = ? WHERE ID = " + SystemLoginController.ID + "; ";

    public AccountController(Account account, AccountPageController accountPageController) {
        _account = account;
        _accountPageController = accountPageController;
    }

    public Account getAccountByLogin(String login, String password) {
        try (PreparedStatement statement = _account.getConnection().prepareStatement(SELECT)) {
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
    public Account getAccount(String login, String password) {
        Account account = null;
        try {
            PreparedStatement stmt = _account.getConnection().prepareStatement("SELECT * FROM Accounts WHERE Login = ? AND Password = ?");
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
            _account.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
