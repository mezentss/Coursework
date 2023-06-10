package Sale;

public class Sale {
    private  int _ID, _serviceID, _partID;

    public Sale(int ID, int serviceID, int partID){
        _ID = ID;
        _serviceID = serviceID;
        _partID = partID;
    }

    public int getServiceID() {
        return _serviceID;
    }

    public void setServiceID(int serviceID) {
        _serviceID = serviceID;
    }

    public int getPartID() {
        return _partID;
    }

    public void setPartID(int partID) {
        _partID = partID;
    }

    public int getID() {
        return _ID;
    }

    public void setID(int ID) {
        _ID = ID;
    }
}
