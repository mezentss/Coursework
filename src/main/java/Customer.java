public class Customer {
    private int _id;
    private String _name, _address, _phone;

    public Customer(int id, String name, String address, String phone){
        _id = id;
        _name = name;
        _address = address;
        _phone = phone;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public String getPhone() {
        return _phone;
    }

    public void setPhone(String phone) {
        _phone = phone;
    }

}
