package Dao;

import Pojo.Info;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoDao {
    boolean insertInfo(Info myinfo) throws SQLException;
    ArrayList<Info> queryInfoByName(String name) throws SQLException;
    Info queryInfoByID(int ID) throws SQLException;
    boolean updateInfoByID(int ID,Info info) throws SQLException;
    boolean deleteInfoByID(int ID) throws SQLException;
    int queryStatus() throws SQLException;
}
