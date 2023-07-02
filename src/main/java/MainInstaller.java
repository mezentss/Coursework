import java.sql.SQLException;

public class MainInstaller {
    public static void main(String[] args){
        try {
            Main.main(args);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
