import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceController {
    private static Service _service;
    private ServicePageController _servicePageController;
    private static final String SELECT_ALL = "SELECT * FROM Services";
    private static final String INSERT = "INSERT INTO Services VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Services WHERE ID = ?";
    private static final String UPDATE = "UPDATE Services SET CarID = ?, Mileage = ?, EmployeeID  = ?,TimeWorked = ?, StartDate = ?, EndDate = ? WHERE ID = ? ";

    public ServiceController(Service service, ServicePageController servicePageController) {
        _service = service;
        _servicePageController = servicePageController;
    }

    public static ObservableList<Service> getServices() {
        ObservableList<Service> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _service.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Service(
                        rs.getInt("ID"),
                        rs.getInt("CarID"),
                        rs.getInt("Mileage"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("TimeWorked"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
        return list;
    }

    public static void addService(Service service) {
        try {
            PreparedStatement ps = _service.getConnection().prepareStatement(INSERT);
            ps.setInt(1, service.getID());
            ps.setInt(2, service.getCarID());
            ps.setInt(3, service.getMileage());
            ps.setInt(4, service.getEmployeeID());
            ps.setInt(5, service.getTimeWorked());
            ps.setString(6, service.getStartDate());
            String date[] = service.getStartDate().split("-");
            int end = (int) Math.ceil(service.getTimeWorked() / 24) + Integer.parseInt(date[2]);
            String endDate = date[0] + "-" + date[1] + "-" + end;
            ps.setString(7, endDate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteService(int id) {
        try {
            PreparedStatement ps = _service.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateService(Service service) {
        try {
            PreparedStatement ps = _service.getConnection().prepareStatement(UPDATE);
            System.out.println(service);
            ps.setInt(1, service.getID());
            ps.setInt(2, service.getCarID());
            ps.setInt(3, service.getMileage());
            ps.setInt(4, service.getEmployeeID());
            ps.setInt(5, service.getTimeWorked());
            ps.setString(6, service.getStartDate());
            ps.setString(7, service.getEndDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}