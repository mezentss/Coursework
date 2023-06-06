public class Account {
    private String _login, _password;
    public Account(String login, String password){
        _login = login;
        _password = password;
    }
    public String getLogin() {return _login;}
    public String getPassword() {
        return _password;
    }
    protected void getAccount(){}
}