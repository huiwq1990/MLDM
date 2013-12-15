import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;




/**
 * 将文件中的聚类值改成数值型的数据(原来是字符型)
 *  原来的数据：
 *  1695,0.2,0.5,0.3,cluster11
 *  1714,0.3,0.4,0.3,cluster11
 *  现在结果：
 *  1695,0.2,0.5,0.3,11
 *  1714,0.3,0.4,0.3,11
 * @author hg
 *
 */
public class ConvertCategoryValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "file/green-em.csv";
		String convFileName = "file/green-em-conv.csv";
		try {
			@SuppressWarnings("unchecked")
			List<String> rowList = FileUtils.readLines(new File(fileName));
			List<String> convRowList= new ArrayList<String>();
			
//			FileUtils.w
			for(int i=0;i<rowList.size();i++){
				String row = rowList.get(i);

				String category = row.substring(row.lastIndexOf(",")+1);
				String categoryNum = category.substring("cluster".length());
				
				String convRow = row.substring(0,row.lastIndexOf(",")+1)+categoryNum;
//				System.out.println(convRow);
				convRowList.add(convRow);
			}
			FileUtils.writeLines(new File(convFileName),convRowList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
