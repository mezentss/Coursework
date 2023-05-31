
public class Part {
    private int _id;
    private String _category;
    private String _model;
    private String _serialNumber;
    private int _price;

    public Part(int id, String category, String model, String serialNumber, int price ) {

        _id = id;
        _category = category;
        _model = model;
        _serialNumber = serialNumber;
        _price = price;
    }

    public int getId() {return _id;}
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

    public void setCategory(String category){_category = category;}
    public void setModel(String model){_model = model;}
    public void setSerialNumber(String serialNumber){_serialNumber = serialNumber;}
    public  void setPrice(int price){_price = price;}

}