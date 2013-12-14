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
 * ��һ�ָ�ʽ������ļ�ϵͳת����һ�ָ�ʽ������ļ�ϵͳ
 */
public class CodingConv {

	public static void main(String[] args) throws Exception {
		convert("original", "after", "gbk", "UTF-8");
	}

	/**
	 * ��ĳ���ļ����µ��ļ��ı���ת��
	 */
	@SuppressWarnings("resource")
	public static void convert(String original, String after,
			String oldEncoding, String newEncoding) {

		File oriFile = new File(original);
		File afterFile = new File(after);

		File[] files = oriFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// ���ļ�����ĸ�ʽ���ļ�
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

				// ������ļ�
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