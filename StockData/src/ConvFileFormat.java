import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ConvFileFormat {
	
public static void main(String[] args) {
		
		try {
			List<String> rows = FileUtils.readLines(new File("sh600611.xls"));
//			FileUtils.r
			String temp = rows.get(0);
			String[] s = temp.split("\\s+");
 			System.out.println(s[1]);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
