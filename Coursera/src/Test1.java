import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
/*
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File("cacerts"));
		try {
			// trustStore.
			trustStore.load(instream, "changeit".toCharArray());
		} finally {
			try {
				instream.close();
			} catch (Exception ignore) {
			}
		}
		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
		Scheme sch = new Scheme("https", 443, socketFactory);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		
		*/
		
	HttpGet get= new HttpGet("https://www.coursera.org");

		
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
//			get.setHeader("x-requested-with","www.coursera.org");
////			get.setHeader("Accept-Language","zh-cn");
//			get.setHeader("x-csrftoken","wdbjDGTK3FUQP6zbdD2kgXOZ");
//			get.setHeader("Accept","*/*");
//			get.setHeader("Content-Type","application/x-www-form-urlencoded");
//			get.setHeader("Host","www.coursera.org");
//			get.setHeader("Accept-Encoding","gzip, deflate");
//			get.setHeader("Connection","Keep-Alive");
//			get.setHeader("Referer", "https://www.coursera.org/#account/signin");
//			get.setHeader("Cookie", "sessionid=48ac81be79f960fde71a67eac386bee3; __204u=6715416079-1357779420284; csrftoken=fgTMqrSpQJY7NFznhsvLud94; __utma=158142248.1072285273.1356586357.1357741675.1357779419.9; __utmb=158142248.7.10.1357779419; __utmc=158142248; __utmz=158142248.1357779419.9.9.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/");
//			 get.setHeader("Referer","http://...");
//			
			HttpResponse response;
				response = httpclient.execute(get);
				HttpEntity entity = response.getEntity();
				System.out.println("Login Status£º" + response.getStatusLine());
				System.out.println(EntityUtils.toString(entity, Consts.UTF_8));
//				
				EntityUtils.consume(entity);
			
	}

}
