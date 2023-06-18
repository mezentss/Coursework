import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PartController {
    public static Connection conn;
    private static Part _part;
    private PartPageController _partPageController;
    public PartController(Part part, PartPageController partPageController){
        _part = part;
        _partPageController = partPageController;
    }
    private static final String SELECT_ALL = "SELECT * FROM Parts";
    private static final String INSERT = "INSERT INTO Parts VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Parts WHERE ID = ?";
    private static final String UPDATE = "UPDATE Parts SET Category = ?, Model = ?, SerialNumber = ?, Price = ? WHERE ID = ? ";
    public static ObservableList <Part> getParts(){
        ObservableList <Part> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = _part.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Part(
                        rs.getInt("ID"),
                        rs.getString("Category"),
                        rs.getString("Model"),
                        rs.getString("SerialNumber"),
                        rs.getInt("Price")));
            }
        } catch (Exception e){

        }
        return list;
    }
    public static void addPart(Part part) {
        try {
            PreparedStatement ps = _part.getConnection().prepareStatement(INSERT);
            ps.setInt(1, part.getID());
            ps.setString(2, part.getCategory());
            ps.setString(3, part.getModel());
            ps.setString(4, part.getSerialNumber());
            ps.setInt(5, part.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deletePart(int id) {
        try {
            PreparedStatement ps = _part.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updatePart(Part part) {
        try {
            PreparedStatement ps = _part.getConnection().prepareStatement(UPDATE);
            ps.setInt(5, part.getID());
            ps.setString(1, part.getCategory());
            ps.setString(2, part.getModel());
            ps.setString(3, part.getSerialNumber());
            ps.setInt(4, part.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}