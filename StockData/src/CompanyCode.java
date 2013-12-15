
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CompanyCode {

	public static void main(String[] args) {
		
		String companyFileName = "company";
		File companyFile = new File(companyFileName);
		
		if(!companyFile.exists()){
			companyFile.mkdir();
		}
		
		File companyCodeFile = new File(companyFileName+"/code.xlsx");

		try {
			FileInputStream is = new FileInputStream(companyCodeFile);
			Workbook wb = new XSSFWorkbook(is);
			read(wb,companyFileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void read(Workbook wb,String companyFileName) throws Exception {
		
		try {

			for (int k = 0; k < wb.getNumberOfSheets(); k++) {

				// 获取sheet
				Sheet sheet = wb.getSheetAt(k);
				
				File file = new File(companyFileName+"/"+k+".txt");
				if(file.exists()){
					file.delete();
					file.createNewFile();
				}else{
					file.createNewFile();
				}
				//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件 
				FileWriter fw = new FileWriter(file,true);  
				//System.out.println(wb.getNameAt(k).getNameName());
				//sheet.g
				// 获取每个sheet中数据行数
				int rows = sheet.getPhysicalNumberOfRows();
				// 对行遍历
				for (int r = 1; r < rows; r++) {
					// 定义 row
					Row row = sheet.getRow(r);
					if (row != null) {

						// 获取每一行的第二个单元格，也就是获取名称及代码单元格
						Cell cell = row.getCell(1);
						// 获取单元格的值
						String companyNameCode = cell.getStringCellValue();
						/*
						 * split的参数是　String regex 代表的是一个正则表达式。 如果是正则中的特殊字符，就不能了。
						 * 括号就是正好是一个特殊字符。所以用[]括起来
						 */
						String[] info = companyNameCode.split("[(]");
						String name = info[0];
						// 需要将)去掉
						String code = info[1]
								.substring(0, info[1].length() - 1);
						//System.out.print(name + "  " + code);
						
						String stock = row.getCell(2).getStringCellValue();
					
						fw.write(name+","+code+","+stock+"\n");  
						fw.flush();  
						
						
					}	
					
					//System.out.println();
				}
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
