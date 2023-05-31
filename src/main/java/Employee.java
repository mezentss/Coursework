public class Employee {
    private int _id;
    private String _name;
    private String _address;

    public Employee(int id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
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

    public void setName(String name) {
        _name = name;
    }

    public void setAddress(String address) {
        _address = address;
    }
}