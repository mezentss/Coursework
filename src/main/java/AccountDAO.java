public interface AccountDAO {
    Account getAccountByLogin(String login, String password);
    void deleteAccount(String login);
}