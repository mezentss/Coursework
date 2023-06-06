public class AccountController {
    private AccountDAO _dao;

    public AccountController(AccountDAO dao) {
        _dao = dao;
    }

    public Account getAccountByLogin(String login, String password) {
        return _dao.getAccountByLogin(login, password);
    }

    public void deleteAccount(String login) {
        _dao.deleteAccount(login);
    }
}

