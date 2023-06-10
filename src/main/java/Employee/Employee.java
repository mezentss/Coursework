package Employee;

public class Employee {
    private int _id;
    private String _name;
    private String _address;
    private String _post;

    public Employee(int id, String name, String address, String post){
        _id = id;
        _name = name;
        _address = address;
        _post = post;
    }

    public int getId() {
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
}