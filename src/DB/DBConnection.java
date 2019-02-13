package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String Drvier = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:data\\data.db";

    private Connection conn;

    public DBConnection() throws Exception {
        try{
            Class.forName(Drvier);
            this.conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw e;
        }
    }
    public Connection getConnection(){
        return this.conn;
    }
    public void close() throws SQLException {
        if(this.conn!=null){
            try{
                this.conn.close();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        try {
            new DBConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}