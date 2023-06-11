package Part;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PartController {
    public static Connection conn;

    public PartController(Connection conn) {
        this.conn = conn;
    }
    private static final String SELECT_ALL = "SELECT * FROM Parts";
    public static ObservableList <Part> getParts(){
        ObservableList <Part> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
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
}