package https;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class qq {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//获得httpclient对象
        HttpClient httpclient = new DefaultHttpClient();
        //获得密匙库
        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File("d:/gen.cer"));
        //密匙库的密码
        trustStore.load(instream, "753951".toCharArray());
        //注册密匙库
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        //不校验域名
//        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme sch = new Scheme("https", 8443, socketFactory);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        //获得HttpPost对象
        HttpPost httpPost = null;
        httpPost = new HttpPost("https://www.coursera.org/maestro/api/user/login");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
//		
        //参数
        List<NameValuePair> loginParameters = new ArrayList<NameValuePair>();
	
		
		loginParameters.add(new BasicNameValuePair("email_address", "huiwq199@163.com"));

		loginParameters.add(new BasicNameValuePair("password", "753951"));
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(loginParameters, "utf-8");
		httpPost.setEntity(params);

		// Execute the request
		HttpResponse response = httpclient.execute(httpPost);

		// Examine the response status
		System.out.println("Login Status：" + response.getStatusLine());

		HttpEntity entity = response.getEntity();

		System.out.println(EntityUtils.toString(entity));
//		FileUtils.writeStringToFile(new File("ss"), EntityUtils.toString(entity));
		
		EntityUtils.consume(entity);
	}

}
