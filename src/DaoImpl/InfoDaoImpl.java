package DaoImpl;

import Dao.InfoDao;
import Pojo.Info;

import java.sql.*;
import java.util.ArrayList;

public class InfoDaoImpl implements InfoDao {
    private Connection conn;
    private PreparedStatement pstmt = null;

    public InfoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertInfo(Info myinfo) throws SQLException {
        boolean flag = false;
        String sql = "insert into myDB(Name,Right_eye," +
                "Left_eye,Distance,Size,Lens,Price,Store,Person,Phone," +
                "Xwjwgd,Height,Pass,DATA) " +
                "values(?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        setPstmt(myinfo);
        if(pstmt.executeUpdate()>0){
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public ArrayList<Info> queryInfoByName(String name) throws SQLException {
        ArrayList<Info> infoes = new ArrayList<>();
        String sql="select Name,Right_eye,Left_eye,Distance," +
                "Size,Lens,Price,Store," +
                "Person,Phone,Xwjwgd,Height," +
                "Pass,DATA,ID from myDB where Name LIKE ?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1,name+'%');
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()){
            Info info = rs2Info(rs);
            infoes.add(info);
        }
        return infoes;
    }

    @Override
    public Info queryInfoByID(int ID) throws SQLException {
        Info info = null;
        String sql="select Name,Right_eye,Left_eye,Distance," +
                "Size,Lens,Price,Store," +
                "Person,Phone,Xwjwgd,Height," +
                "Pass,DATA,ID from myDB where ID = ?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1,ID);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            info = rs2Info(rs);
        }
        return info;
    }

    @Override
    public boolean updateInfoByID(int ID,Info info) throws SQLException {
        boolean flag = false;
        String sql = "update myDB set Name=?,Right_eye=?," +
                "Left_eye=?,Distance=?,Size=?,Lens=?,Price=?,Store=?,Person=?,Phone=?," +
                "Xwjwgd=?,Height=?,Pass=?,DATA=? where ID = ?";
        this.pstmt=this.conn.prepareStatement(sql);
        setPstmt(info);
        pstmt.setInt(15,info.getID());
        if(pstmt.executeUpdate()>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteInfoByID(int ID) throws SQLException {
        boolean flag;
        String sql="delete from myDB where id = ?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1,ID);
        if(pstmt.executeUpdate()>0){
            flag=true;
        }else{
            flag=false;
        }
        return flag;
    }

    @Override
    public int queryStatus() throws SQLException {
        int counts = -1;
        String sql="select count(*) from myDB";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            counts = rs.getInt(1);
        }
        return counts;
    }

    private void setPstmt(Info myinfo) throws SQLException {
        pstmt.setString(1,myinfo.getName());
        pstmt.setString(2,myinfo.getRight_eye());
        pstmt.setString(3,myinfo.getLeft_eye());
        pstmt.setInt(4,myinfo.getDistance());
        pstmt.setString(5,myinfo.getSize());
        pstmt.setString(6,myinfo.getLens());
        pstmt.setString(7,myinfo.getPrice());
        pstmt.setString(8,myinfo.getStore());
        pstmt.setString(9,myinfo.getPerson());
        pstmt.setString(10,myinfo.getPhone());
        pstmt.setString(11,myinfo.getXwjwgd());
        pstmt.setString(12,myinfo.getHeight());
        pstmt.setString(13,myinfo.getPass());
        pstmt.setString(14,myinfo.getDATA());
    }


    private Info rs2Info(ResultSet rs) throws SQLException {
        Info info = new Info();
        info.setName(rs.getString(1));
        info.setRight_eye(rs.getString(2));
        info.setLeft_eye(rs.getString(3));
        info.setDistance(rs.getInt(4));

        info.setSize(rs.getString(5));
        info.setLens(rs.getString(6));
        info.setPrice(rs.getString(7));
        info.setStore(rs.getString(8));

        info.setPerson(rs.getString(9));
        info.setPhone(rs.getString(10));
        info.setXwjwgd(rs.getString(11));
        info.setHeight(rs.getString(12));

        info.setPass(rs.getString(13));
        info.setDATA(rs.getString(14));
        info.setID(rs.getInt(15));
        return info;
    }

}
