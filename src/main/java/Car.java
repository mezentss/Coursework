public class Car {
    private int _id, _ownerId;
    private String _licensePlate, _brand, _model, _color;

    public Car(int id, String licensePlate, String brand, String model, String color, int ownerId){
        _id = id;
        _licensePlate = licensePlate;
        _brand = brand;
        _model = model;
        _color = color;
        _ownerId = ownerId;

    }

    public int getId() {
        return _id;
    }

    public int getOwnerID() {
        return _ownerId;
    }

    public String getBrand() {
        return _brand;
    }

    public String getColor() {
        return _color;
    }

    public String getLicensePlate() {
        return _licensePlate;
    }

    public String getModel() {
        return _model;
    }

    public void setBrand(String brand) {
        _brand = brand;
    }

    public void setColor(String color) {
        _color = color;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setLicensePlate(String licensePlate) {
        _licensePlate = licensePlate;
    }

    public void setModel(String model) {
        _model = model;
    }

    public void setOwnerID(int ownerId) {
        _ownerId = ownerId;
    }
}
