package Factory;


import Dao.InfoDao;
import Service.InfoService;

public class DAOFactory {
    public static InfoDao getInfoDaoInstance() throws Exception {
        return new InfoService();
    }
}