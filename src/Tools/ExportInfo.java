package Tools;

import Pojo.Info;
import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ExportInfo {
    public static int exportData(String filePath, JProgressBar jpb, ArrayList<Info> infoes) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();


        HSSFCell cell = row.createCell(0); //第一个单元格
        cell.setCellValue("日期");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("右眼");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("左眼");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("瞳距");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("镜架尺寸");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("镜片");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("店");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("验光师");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("电话");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("下加光");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("通道");
        cell.setCellStyle(style);

        cell = row.createCell(14);
        cell.setCellValue("瞳高");
        cell.setCellStyle(style);


        for (int i = 0; i < infoes.size(); i++) {
            //创建行
            row = sheet.createRow(i + 1);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(infoes.get(i).getDATA());
            row.createCell(1).setCellValue(infoes.get(i).getName());
            row.createCell(2).setCellValue(infoes.get(i).getRight_eye());
            row.createCell(3).setCellValue(infoes.get(i).getLeft_eye());
            row.createCell(4).setCellValue(infoes.get(i).getDistance());
            row.createCell(5).setCellValue(infoes.get(i).getSize());
            row.createCell(6).setCellValue(infoes.get(i).getLens());
            row.createCell(8).setCellValue(infoes.get(i).getStore());
            row.createCell(9).setCellValue(infoes.get(i).getPerson());
            row.createCell(10).setCellValue(infoes.get(i).getPhone());
            row.createCell(12).setCellValue(infoes.get(i).getXwjwgd());
            row.createCell(13).setCellValue(infoes.get(i).getPass());
            row.createCell(14).setCellValue(infoes.get(i).getHeight());

            Dimension d = jpb.getSize();
            Rectangle rect = new Rectangle(0, 0, d.width, d.height);
            jpb.setValue((int) (((double) (i+1) / infoes.size()) * 100));
            jpb.paintImmediately(rect);
            System.out.println(jpb.getValue());

        }

        try {
            FileOutputStream fout = new FileOutputStream(filePath+"\\数据导出"+System.currentTimeMillis()+".xls");
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
