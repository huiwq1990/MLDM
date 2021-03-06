import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStream;  
  
import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.poifs.filesystem.POIFSFileSystem;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
public class POIExcelDemo {  
  
    /** 
     *  
     * @param fileName 文件路径 
     * @param flag 是2003还是2007 true：2003，false：2007 
     * @throws Exception 
     */  
    public static void read(String fileName,boolean flag) throws Exception {  
        Workbook wb = null;  
        if(flag){//2003  
            File f = new File(fileName);  
              
            FileInputStream is = new FileInputStream(f);  
            POIFSFileSystem fs = new POIFSFileSystem(is);     
            wb = new HSSFWorkbook(fs);  
            is.close();  
        }else{//2007  
            wb = new XSSFWorkbook(fileName);  
        }  
          
        read(wb);  
    }  
      
    /** 
     *  
     * @param is 输入流 
     * @param flag 是2003还是2007 true：2003，false：2007 
     * @throws Exception 
     */  
    public static void read(InputStream is,boolean flag) throws Exception {  
        Workbook wb = null;  
          
        if(flag){//2003  
            wb = new HSSFWorkbook(is);  
        }else{//2007  
            wb = new XSSFWorkbook(is);  
        }  
          
        read(wb);  
    }  
      
    /** 
     * 具体读取Excel 
     * @param wb 
     * @throws Exception 
     */  
    public static void read(Workbook wb) throws Exception {  
        try {  
              
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {  
  
                //sheet  
                Sheet sheet = wb.getSheetAt(k);  
//                System.out.println(sheet.get);
                int rows = sheet.getPhysicalNumberOfRows();  
                //对行遍历
                for (int r = 0; r < rows; r++) {  
                    // 定义 row  
                    Row row = sheet.getRow(r);  
                    if (row != null) {  
                        int cells = row.getPhysicalNumberOfCells();  
                        //对单元格遍历
                        for (short c = 0; c < cells; c++) {  
                            Cell cell = row.getCell(c);  
                            if (cell != null) {  
                                String value = null;  
  
                                switch (cell.getCellType()) {  
  
                                case Cell.CELL_TYPE_FORMULA:  
                                    value = "FORMULA value=" + cell.getCellFormula();  
                                    break;  
  
                                case Cell.CELL_TYPE_NUMERIC:  
                                    if(HSSFDateUtil.isCellDateFormatted(cell)){  
                                        value = "DATE value="  
                                            + cell.getDateCellValue();  
                                    }else{  
                                        value = "NUMERIC value="  
                                                + cell.getNumericCellValue();  
                                    }  
                                      
                                    break;  
  
                                case Cell.CELL_TYPE_STRING:  
                                    value = "STRING value="  
                                            + cell.getStringCellValue();  
                                    break;  
                                      
                                case Cell.CELL_TYPE_BOOLEAN:  
                                    value = "BOOLEAN value="  
                                            + cell.getBooleanCellValue();  
                                      
                                      
                                    cell.getDateCellValue();  
                                      
                                    break;  
  
                                default:  
                                }  
                                  
                                System.out.print(value+"   ");  
  
                            }  
                        }  
                    }  
                    System.out.println();
                }  
               
            }  
        } catch (Exception e) {  
  
            e.printStackTrace();  
        }  
  
    }  
  
    /** 
     * @param args 
     * @throws Exception  
     */  
    public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
  
        File f = new File("code.xlsx");  
          
        FileInputStream is = new FileInputStream(f);  
          
        System.out.println(f.getName());  
          
        read(is,false);  
          
          
    }  
  
}  