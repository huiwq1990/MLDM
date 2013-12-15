
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

				// ��ȡsheet
				Sheet sheet = wb.getSheetAt(k);
				
				File file = new File(companyFileName+"/"+k+".txt");
				if(file.exists()){
					file.delete();
					file.createNewFile();
				}else{
					file.createNewFile();
				}
				//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ� 
				FileWriter fw = new FileWriter(file,true);  
				//System.out.println(wb.getNameAt(k).getNameName());
				//sheet.g
				// ��ȡÿ��sheet����������
				int rows = sheet.getPhysicalNumberOfRows();
				// ���б���
				for (int r = 1; r < rows; r++) {
					// ���� row
					Row row = sheet.getRow(r);
					if (row != null) {

						// ��ȡÿһ�еĵڶ�����Ԫ��Ҳ���ǻ�ȡ���Ƽ����뵥Ԫ��
						Cell cell = row.getCell(1);
						// ��ȡ��Ԫ���ֵ
						String companyNameCode = cell.getStringCellValue();
						/*
						 * split�Ĳ����ǡ�String regex �������һ��������ʽ�� ����������е������ַ����Ͳ����ˡ�
						 * ���ž���������һ�������ַ���������[]������
						 */
						String[] info = companyNameCode.split("[(]");
						String name = info[0];
						// ��Ҫ��)ȥ��
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
