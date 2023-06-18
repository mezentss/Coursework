import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SaleController {
    private static  Sale _sale;
    private SalePageController _salePageController;
    private static final String SELECT_ALL = "SELECT * FROM Sales ";
    private static final String GET_SUM = "SELECT SUM(Price)\n" +
            "FROM Sales\n" +
            "JOIN Parts ON Sales.PartID = Parts.ID\n" +
            "WHERE Sales.ServiceID = ?";
    private static final String INSERT = "INSERT INTO Sales VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM Sales WHERE ID = ?";
    private static final String UPDATE = "UPDATE Sales SET ServiceID = ?, PartID = ? WHERE ID = ?";
    private static Connection _connection;

    public SaleController(Sale sale, SalePageController salesPageController){
        _sale = sale;
        _salePageController = salesPageController;
    }
    public static ObservableList<Sale> getSales(){
        ObservableList <Sale> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _sale.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Sale(
                        rs.getInt("ID"),
                        rs.getInt("ServiceID"),
                        rs.getInt("PartID")));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
        return list;
    }
    public static void addSale(Sale sale) {
        try {
            PreparedStatement ps = _sale.getConnection().prepareStatement(INSERT);
            ps.setInt(1, sale.getID());
            ps.setInt(2, sale.getServiceID());
            ps.setInt(3, sale.getPartID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteSale(int id) {
        try {
            PreparedStatement ps = _sale.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSale(Sale sale) {
        try {
            PreparedStatement ps = _sale.getConnection().prepareStatement(UPDATE);
            ps.setInt(1, sale.getServiceID());
            ps.setInt(2, sale.getPartID());
            ps.setInt(3, sale.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
