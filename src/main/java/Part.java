import java.sql.Connection;

public class Part {
    private Connection _connection;
    private int _id;
    private String _category;
    private String _model;
    private String _serialNumber;
    private int _price;
    public Part(Connection connection){_connection = connection;}
    public Part(int id, String category, String model, String serialNumber, int price ) {

        _id = id;
        _category = category;
        _model = model;
        _serialNumber = serialNumber;
        _price = price;
    }

    public int getID() {return _id;}
    public String getCategory() {return _category;}
    public String getModel() {
        return _model;
    }
    public String getSerialNumber() {
        return _serialNumber;
    }
    public int getPrice() {
        return _price;
    }
    public Connection getConnection() {return _connection;}
    public void setCategory(String category){_category = category;}
    public void setModel(String model){_model = model;}
    public void setSerialNumber(String serialNumber){_serialNumber = serialNumber;}
    public  void setPrice(int price){_price = price;}

}