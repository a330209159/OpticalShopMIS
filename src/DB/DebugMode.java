package DB;



import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DebugMode {
    private DBConnection dbconn;
    private PreparedStatement pstmt;
    private Connection conn;
    private JTextArea jta;
    public DebugMode(JTextArea jta) {
        this.jta = jta;
        try {
            this.dbconn = new DBConnection();
            conn = dbconn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void executeSQL(String sql){
        try {
            pstmt = this.conn.prepareStatement(sql);
            int counts = pstmt.executeUpdate();
            jta.setText(counts+"条数据受到影响");

        } catch (SQLException e) {
            jta.setText(e.getMessage());
        }finally {
            try {
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close() throws SQLException {
        if(pstmt!=null) {
            pstmt.close();
        }
        conn.close();
    }
}
