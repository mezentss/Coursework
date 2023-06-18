import java.sql.Connection;

public class Service {
    private int _id, _carID,  _mileage, _employeeID, _timeWorked;
    private String _startDate, _endDate;
    private Connection _connection;
    public Service(int id, int carID, int mileage, int employeeID, int timeWorked, String startDate, String endDate){
        _id = id;
        _carID = carID;
        _mileage = mileage;
        _employeeID = employeeID;
        _timeWorked = timeWorked;
        _startDate = startDate;
        _endDate = endDate;
    }
    public Service(Connection connection){_connection = connection;}
    public Connection getConnection() {return _connection;}
    public int getID() {
        return _id;
    }
    public void setID(int id) {
        _id = id;
    }
    public int getCarID() {
        return _carID;
    }
    public void setCarID(int carID) {
        _carID = carID;
    }
    public int getMileage() {
        return _mileage;
    }
    public void setMileage(int mileage) {
        _mileage = mileage;
    }
    public int getEmployeeID() {
        return _employeeID;
    }
    public void setEmployeeID(int employeeID) {
        _employeeID = employeeID;
    }
    public int getTimeWorked() {
        return _timeWorked;
    }
    public void setTimeWorked(int timeWorked) {
        _timeWorked = timeWorked;
    }
    public String getStartDate() {
        return _startDate;
    }
    public void setStartDate(String startDate) {
        _startDate = startDate;
    }
    public String getEndDate() {
        return _endDate;
    }
    public void setEndDate(String endDate) {
        _endDate = endDate;
    }
}
