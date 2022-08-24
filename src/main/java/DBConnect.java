import java.sql.*;

public class DBConnect {
    static String url = "jdbc:postgresql://db.stage.gcs.prodv.net:5432/axpl";
    static String name = "axpl";
    static String pass = "axpl2pass";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Class.forName("org.postgresql.Driver");
        Connection db = DriverManager.getConnection(url, name, pass);
    }
}
