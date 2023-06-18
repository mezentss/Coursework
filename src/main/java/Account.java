import java.sql.Connection;

public class Account {
    private String _login, _password, _accessLevel;
    private Connection _connection;
    public Account(String login, String password, String accessLevel){
        _login = login;
        _password = password;
        _accessLevel = accessLevel;
    }
    public Account(Connection connection){_connection = connection;}
    public Connection getConnection(){return _connection;}
    public String getLogin() {return _login;}
    public String getPassword() {return _password;}
    public String getAccessLevel(){ return  _accessLevel;}
    protected void getAccount(){}
}