package Toos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ParseExcel {

    private Workbook book;
    private Sheet  sheet;
    private List<String> oneRowDataList;
    private List<List<String>> dataesList;
    
    /**
     * 构造同时加载excel
     * @param excelPath
     * @param sheetName
     */
    public ParseExcel(String excelPath,String sheetName){
        this.load(excelPath,sheetName);
    }
    
    /**
     * 加载文件
     */
    public void load(String excelPath,String sheetName){
        try {
//            File file = new File(excelPath);
            FileInputStream inputStream = new FileInputStream(excelPath);
            book = WorkbookFactory.create(inputStream);    
            sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            System.out.println("Excel文件没有找到！");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("读取Excel文件出错！");
            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        }
    }

    /**
     * 得到指定单元格的值，并转换成字符串
     * 在读取每个单元格值的时候，通过getCellType方法获得当前单元格类型
     * 在Excel中单元格有6种类型
     * 1 CELL_TYPE_BLANK        空值
     * 2 CELL_TYPE_BOOLEAN    布尔型
     * 3 CELL_TYPE_ERROR        错误
     * 4 CELL_TYPE_FORMULA    公式型
     * 5 CELL_TYPE_STRING        字符串型
     * 6 CELL_TYPE_NUMERIC     数值型
     * 如果单元格类型为CELL_TYPE_NUMERIC时，还需要进一步判断
     * 如果是Date类型，在Excel中Date类型是以Double类型数字存储的，表示当前时间与1900年1月1日相隔的天数
     * @param
     * @param
     */
    @SuppressWarnings("unused")
    public String getCellValue(Cell cell){
        if(cell == null){
            return "";
        }
        
        int dataType = cell.getCellType();
        String cellValue = "";
        //判断数据类型
        switch(dataType){
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());            
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = "";
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    cellValue = new DataFormatter().formatCellValue(cell);
                }else {
                    double dValue = cell.getNumericCellValue();
                    int iValue = (int)dValue;
                    cellValue = dValue - iValue == 0 ? String.valueOf(iValue) : String.valueOf(dValue);
                }
                break;
                default:
                    cellValue = cell.toString().trim();
                    break;
        }
        return cellValue.trim();
    }

    /**
     * 返回Object[][]
     */
    public Object[][] getParameter(){
        int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] param=new Object[sheet.getLastRowNum()][columnNum];
        int cellNum=sheet.getLastRowNum()+1;
        for (int i=0;i<cellNum;i++){
            for (int j=0;j<param[0].length;j++){
                Cell cell=sheet.getRow(i).getCell(j);
                if (i!=0){
                    param[i-1][j]=this.getCellValue(cell);
                }
            }
        }
        return param;
    }
    
}
