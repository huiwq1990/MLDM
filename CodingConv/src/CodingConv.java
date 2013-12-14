import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 将一种格式编码的文件系统转成另一种格式编码的文件系统
 */
public class CodingConv {

	public static void main(String[] args) throws Exception {
		convert("original", "after", "gbk", "UTF-8");
	}

	/**
	 * 将某个文件夹下的文件的编码转换
	 */
	@SuppressWarnings("resource")
	public static void convert(String original, String after,
			String oldEncoding, String newEncoding) {

		File oriFile = new File(original);
		File afterFile = new File(after);

		File[] files = oriFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 以文件本身的格式打开文件
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(files[i]), oldEncoding));

				String temp = "";
				StringBuilder sb = new StringBuilder();
				while ((temp = br.readLine()) != null) {
					sb.append(temp).append("\n");
				}
				String result = sb.toString();

				br.close();

				String filePath = afterFile + "/" + files[i].getName();

				File backFile = new File(filePath);
				if (!backFile.exists()) {

					backFile.createNewFile();
				}

				// 输出到文件
				OutputStreamWriter bw = null;

				bw = new OutputStreamWriter(new FileOutputStream(filePath),
						newEncoding);

				bw.write(result);
				bw.flush();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}