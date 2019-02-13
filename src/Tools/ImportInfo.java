package Tools;

import Factory.DAOFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Pojo.Info;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;


public class ImportInfo {

    public static int getDataFromExcel(String filePath,JProgressBar jpb) throws Exception {

        JProgressBar jProgressBar = jpb;
        //判断是否为excel类型文件
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
        }

        FileInputStream fis = null;
        Workbook wookbook = null;

        try {
            //获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿

        } catch (Exception ex) {
            //ex.printStackTrace();
            try {
                fis = new FileInputStream(filePath);
                //2007版本的excel，用.xlsx结尾
                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);

        //获得表头
        Row rowHead = sheet.getRow(0);

        //判断表头是否正确
//        if (rowHead.getPhysicalNumberOfCells() != 3) {
//            System.out.println("表头的数量不对!");
//        }

        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        System.out.println(totalRowNum);
        //要获得属性
        String name = "";


        //获得所有数据
        for (int i = 1; i <= totalRowNum; i++) {
            //获得第i行对象
            Row row = sheet.getRow(i);
            //获得获得第i行第0列的 String类型对象
            name = getCellValueFromRow(row,1);
            if(!name.equals("")) {
                Info myinfo = new Info();
                myinfo.setName(name);
                myinfo.setDATA(getCellValueFromRow(row,0));
                myinfo.setRight_eye(getCellValueFromRow(row,2));
                myinfo.setLeft_eye(getCellValueFromRow(row,3));
                try{
                    myinfo.setDistance(Integer.valueOf(getCellValueFromRow(row,4)));
                }catch (Exception e){
                    myinfo.setDistance(-1);
                }

                myinfo.setSize(getCellValueFromRow(row,5));
                myinfo.setLens(getCellValueFromRow(row,6));
                myinfo.setStore(getCellValueFromRow(row,8));
                myinfo.setPerson(getCellValueFromRow(row,9));
                myinfo.setPhone(getCellValueFromRow(row,10));
                myinfo.setXwjwgd(getCellValueFromRow(row,12));
                myinfo.setPass(getCellValueFromRow(row,13));
                myinfo.setHeight(getCellValueFromRow(row,14));
                //System.out.println(myinfo.toString());
                DAOFactory.getInfoDaoInstance().insertInfo(myinfo);

                Dimension d = jpb.getSize();
                Rectangle rect = new Rectangle(0, 0, d.width, d.height);
                jpb.setValue((int)(((double)i/totalRowNum)*100));
                jpb.paintImmediately(rect);
            }

        }
        return totalRowNum;
    }

    private static String getCellValueFromRow(Row row,int i){
        Cell cell = row.getCell(i);
        if(cell != null) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }else{
            return "";
        }
    }
    public static void main(String[] args) {

    }
}