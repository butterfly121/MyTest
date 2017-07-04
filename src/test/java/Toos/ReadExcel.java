package Toos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.CellType;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

    //读取指定路径下的文件，具体要到某个文件(filePath)，指定sheet(sheet)中的excel表格中每一行，将放到数组中，返回
    public static String[][] f_readexcel(String filePath, String sheetname) throws IOException, JXLException{
        //输入流，声明读取变量is 用户读取excel表格      
            InputStream is=null;
        //构建Workbook对象
            Workbook workbook=null;
        //传入filepath中描述的路径，实例化is。
            is=new FileInputStream(filePath);
        //将is中读取到的excel表格赋值给workbook
            workbook=Workbook.getWorkbook(is);
        //声明变量sheet，将workbook中的sheet名为sheetname的表格读取到sheet中
            Sheet sheet=workbook.getSheet(sheetname);
        //声明一个数组，用于存放sheet中的值，长度根据sheet中的实际长度定义
            String cases[][] = new String[sheet.getRows()][sheet.getColumns()];
            int i;
            int j;
        //通过两层循环，将sheet中的数据读取到数组中
            //遍数组的历行
            for(i=1;i<sheet.getRows();i++){
                //遍数组的历列
                for(j=0;j<sheet.getColumns();j++){
                    //声明一个单元格变量，通过getCell(j,i)得到单元格中的值，读取每列每行，getCell(j,i)前面的j是列，i是行
                    Cell cellA1=sheet.getCell(j,i);
                    //如果当前单元格的格式是label(文本类型)类型的，就读取到数组中，需要保证excel表中数字类型的单元格手动调整成文本类型
                    if (cellA1.getType().equals(CellType.LABEL)){
                        //获取string类型单元格的数据
                        cases[i-1][j]=cellA1.getContents();
                    }
                }
            }
            //将excel表关闭
            workbook.close();
            //将输入流关闭
            is.close();
            //返回已经读取到sheet数据的数组
            return cases;
              }


}