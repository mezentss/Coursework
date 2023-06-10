package Account;

import Account.Account;

public class AccountController {
    private AccountDAOImpl _dao;

    public AccountController(AccountDAOImpl dao) {
        _dao = dao;
    }

    public Account getAccountByLogin(String login, String password) {
        return _dao.getAccountByLogin(login, password);
    }

    public void deleteAccount(String login) {
        _dao.deleteAccount(login);
    }
}

