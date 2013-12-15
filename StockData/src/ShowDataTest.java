import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class ShowDataTest {

	private static HttpClient hc = new DefaultHttpClient();

	public static void main(String[] args) {
		
		String companyFileName = "company";
		String stockFileName = "stock";
		
		File stockFile = new File(stockFileName);
//		stockFile.i
		if(!stockFile.exists()){
			try {
				stockFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		for (int k = 0; k < 2; k++) {
			File file = new File(companyFileName+"/"+k + ".txt");
			String row = null;
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				while ((row = reader.readLine()) != null) {
					// row = reader.readLine();
					String companyInfo[] = row.split(",");
					
					String code = companyInfo[1];

					System.out.println("下载公司"+companyInfo[1]+"的信息");
					
					String url = "http://ichart.yahoo.com/table.csv?" + "s="
							+ code + ".SS&a=07&b=25&c=2010&d=09&e=8&f=2010&g=d";

					// Post请求
					HttpPost httppost = new HttpPost(url);
					// 设置参数
					// httppost.setEntity(new UrlEncodedFormEntity(params));
					// 发送请求
					HttpResponse httpresponse = hc.execute(httppost);
					// 获取返回数据
					HttpEntity entity = httpresponse.getEntity();
					String body = EntityUtils.toString(entity);
					// System.out.println(body);

					/*
					 * 将控制台的信息写入txt文件中
					 */
					File companyStockFile = new File(stockFileName+"/"+companyInfo[0]+".txt"); // 创建新的文件：
					
					
					if(companyStockFile.exists()){
						companyStockFile.delete();
						companyStockFile.createNewFile();
					}else{
						companyStockFile.createNewFile();
					}
					
					FileWriter fw = new FileWriter(companyStockFile, true);
					// BufferedWriter output = new BufferedWriter(new
					// FileWriter(file1));
					// String str= body;

					fw.write(body);
					fw.close();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
