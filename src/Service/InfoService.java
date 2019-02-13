package Service;

import DB.DBConnection;
import Dao.InfoDao;
import DaoImpl.InfoDaoImpl;
import Factory.DAOFactory;
import Pojo.Info;

import java.sql.SQLException;
import java.util.ArrayList;

public class InfoService implements InfoDao {
    private DBConnection dbconn;
    private InfoDao dao;
    public InfoService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new InfoDaoImpl(dbconn.getConnection());
    }

    @Override

    public boolean insertInfo(Info myinfo) throws SQLException {
        boolean flag;
        try{
            if(true){
                flag = dao.insertInfo(myinfo);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return flag;
    }

    @Override
    public ArrayList<Info> queryInfoByName(String name) throws SQLException {
        ArrayList<Info> infoes = null;
        try{
            if(true){
                infoes = dao.queryInfoByName(name);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return infoes;
    }

    @Override
    public Info queryInfoByID(int ID) throws SQLException {
        Info info;
        try{
            if(true){
                info = dao.queryInfoByID(ID);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return info;
    }

    @Override
    public boolean updateInfoByID(int ID, Info info) throws SQLException {
        boolean flag;
        try{
            if(true){
                flag = dao.updateInfoByID(ID,info);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return flag;
    }

    @Override
    public boolean deleteInfoByID(int ID) throws SQLException {
        boolean flag;
        try{
            if(true){
                flag = dao.deleteInfoByID(ID);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return flag;
    }

    @Override
    public int queryStatus() throws SQLException {
        int counts = -1;
        try{
            if(true){
                counts = dao.queryStatus();
            }
        }catch (Exception e){
            throw e;
        }finally {
            dbconn.close();
        }
        return counts;
    }

    public static void main(String[] args) {
        try {
            for (Info info:DAOFactory.getInfoDaoInstance().queryInfoByName("张")){
                System.out.println(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
