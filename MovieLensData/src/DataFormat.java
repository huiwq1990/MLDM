import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class DataFormat {

	// 设读取文件的缓存为40MB
	static int bufferSize = 40 * 1024 * 1024;
	
	private static String filePath = "D:/Data/MovieLens/ml-100k/";

	// 处理后的文件夹
	private static String processFilePath = "D:/Data/MovieLens/process/";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		
		//|与空格都是特殊字符，Split时要特殊处理
		processFileDataFormat("u.item","\\|");
		processFileDataFormat("u.user","\\|");
		processFileDataFormat("u.data","\\s+");
	}

	/**
	 * 将文件的格式转换成以逗号分割
	 * @param fileName
	 */
	static void processFileDataFormat(String fileName,String split) {
		
		
		// 建立缓冲文本输入流
		File file = new File(filePath+fileName);
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader input = null;

		FileWriter output = null;
		String line = null;
		String newLine = null;
		try {
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			inputStreamReader = new InputStreamReader(bufferedInputStream);
			input = new BufferedReader(inputStreamReader, bufferSize);

			output = new FileWriter(processFilePath + fileName);

			
			while ((line = input.readLine()) != null) {
				String[] info = line.split(split);// 分割特殊字符串
				// String newLine =
				// info[0]+","+info[1]+","+info[2]+","+info[3]+",";

				newLine = info[0];

				for (int i = 1; i < info.length; i++) {
					newLine += "," + info[i];
				}
				
				output.write(newLine+"\n");
			}
			fileInputStream.close();
			bufferedInputStream.close();
			inputStreamReader.close();
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
