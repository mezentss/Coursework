import java.sql.Connection;

public class Employee {
    private int _id;
    private Connection _connection;
    private String _name, _address, _post, _login, _password;
    public Employee(Connection connection){
        _connection = connection;
    }
    public Employee(int id, String name, String address, String post, String login, String password){
        _id = id;
        _name = name;
        _address = address;
        _post = post;
        _login = login;
        _password = password;
    }
    public Employee (int id, String name, String address, String post){
        _id = id;
        _name = name;
        _address = address;
        _post = post;
    }
    public Connection getConnection() {return _connection;}
    public int getID() {
        return _id;
    }
    public String getAddress() {
        return _address;
    }
    public String getName() {
        return _name;
    }
    public String getPost(){return  _post;}
    public void setId(int id){ _id = id; }
    public void setName(String name) {
        _name = name;
    }
    public void setAddress(String address) {
        _address = address;
    }
    public void setPost(String post){_post = post;}
    public String getLogin() {return _login;}
    public String getPassword() {
        return _password;
    }
}