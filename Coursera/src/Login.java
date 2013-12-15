

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.oauth.OAuthAccessor;
import net.oauth.client.httpclient4.OAuthCredentials;
import net.oauth.client.httpclient4.OAuthSchemeFactory;

import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Login {

	private final static DefaultHttpClient client = new DefaultHttpClient();//定义一个浏览器
	
	public static void main(String[] args) throws Exception{
//		 DefaultHttpClient httpclient = new DefaultHttpClient();
	      
	            KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
	            FileInputStream instream = new FileInputStream(new File("cacerts"));
	            try {
//	            	trustStore.
	                trustStore.load(instream, "changeit".toCharArray());
	            } finally {
	                try { instream.close(); } catch (Exception ignore) {}
	            }

	            SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
	            Scheme sch = new Scheme("https", 443, socketFactory);
	            client.getConnectionManager().getSchemeRegistry().register(sch);
	
	            
//	            
//	            Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
//	            HttpClient httpclient = new HttpClient();
//	            
//	            httpclient.getHostConfiguration().setHost("127.0.0.1", 9000, myhttps);
		/*AbstractHttpClient httpClient = new DefaultHttpClient();
		//register the OAuthScheme
		httpClient.getAuthSchemes().register(OAuthSchemeFactory.SCHEME_NAME, 
		new OAuthSchemeFactory()); 
		//get the OAuthAccessor object
		OAuthAccessor accessor = yourMethodToGetOAuthAccessor();
		//set credentials 
		httpClient.getCredentialsProvider().setCredentials(
		new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM,
		OAuthSchemeFactory.SCHEME_NAME),
		new OAuthCredentials(accessor));
		//Adjust the authentication scheme selection
		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute("http.auth.scheme-pref", Arrays.asList(new String[] {
		"oauth", "digest", "basic"
		}));
		
		*/
	
	
		login();
//		getWeb("https://eventing.coursera.org/info?key=%22api.error.401%22&value=%7B%22timing%22%3A637%2C%22url%22%3A%22%2Fmaestro%2Fapi%2Fuser%2Flogin%22%2C%22status%22%3A401%2C%22response%22%3A%22%7B%5C%22status%5C%22%3A%5C%22%5C%22%7D%22%7D&from=%22https%3A%2F%2Fwww.coursera.org%2F%23account%2Fsignin%22&session=%229951415323-1357737511294%22&client=%22home%22&url=%22https%3A%2F%2Fwww.coursera.org%2F%23account%2Fsignin%22&time=1357780901083");

		  List<Cookie> cookies = client.getCookieStore().getCookies();
          if (cookies.isEmpty()) {
              System.out.println("None");
          } else {
              for (int i = 0; i < cookies.size(); i++) {
                  System.out.println("- " + cookies.get(i).toString());
              }
          }
		
//		 getWeb("https://www.coursera.org/maestro/api/topic/list_my?user_id=559913");
//	getWeb(Constant.pgm);
		
	}

	public static void login(){
		HttpPost httpPost = new HttpPost("https://www.coursera.org/maestro/api/user/login");
//		httpPost.setHeader(	
//				"User-Agent",
//				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; qdesk 2.5.1277.202; EmbeddedWB 14.52 from: http://www.bsalsa.com/ EmbeddedWB 14.52; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; staticlogin:product=cboxf2010&act=login&info=ZmlsZW5hbWU9UG93ZXJXb3JkMjAxME94Zl9TcGVjaWFsLmV4ZSZtYWM9NjRGRUY5NzA5OUZENEZDNDhEN0JFNjVGRjFDMTkyQUImcGFzc3BvcnQ9JnZlcnNpb249MjAxMC42LjMuNS4yJmNyYXNodHlwZT0x&verify=f422d315013cc9b782866e1557834579; InfoPath.3; .NET4.0C; .NET4.0E)");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
//		
//				httpPost.setHeader("Accept-Charset","GBK,utf-8;q=0.7,*;q=0.3");
//		httpPost.setHeader("x-requested-with","www.coursera.org");
//		httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
//		httpPost.setHeader("x-csrftoken","wdbjDGTK3FUQP6zbdD2kgXOZ");
//		httpPost.setHeader("Accept","*/*");
//		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
//		httpPost.setHeader("Host","www.coursera.org");
//		httpPost.setHeader("Accept-Encoding","gzip, deflate");
//		httpPost.setHeader("Connection","Keep-Alive");
//		httpPost.setHeader("Referer", "https://www.coursera.org/#account/signin");
//		httpPost.setHeader("Cookie", "sessionid=48ac81be79f960fde71a67eac386bee3; __204u=6715416079-1357779420284; csrftoken=fgTMqrSpQJY7NFznhsvLud94; __utma=158142248.1072285273.1356586357.1357741675.1357779419.9; __utmb=158142248.7.10.1357779419; __utmc=158142248; __utmz=158142248.1357779419.9.9.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/");
		List<NameValuePair> loginParameters = new ArrayList<NameValuePair>();
	
		
		loginParameters.add(new BasicNameValuePair("email_address", "huiwq199@163.com"));

		loginParameters.add(new BasicNameValuePair("password", "753951"));
		
		
		try {
			UrlEncodedFormEntity params = new UrlEncodedFormEntity(loginParameters, "utf-8");
			httpPost.setEntity(params);

			// Execute the request
			HttpResponse response = client.execute(httpPost);

			// Examine the response status
			System.out.println("Login Status：" + response.getStatusLine());

//			System.out.println(response.);
			// Get hold of the response entity
			HttpEntity entity = response.getEntity();

//			System.out.println(EntityUtils.toString(entity,Consts.ISO_8859_1));
			FileUtils.writeStringToFile(new File("ss"), EntityUtils.toString(entity));
			
			EntityUtils.consume(entity);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void getWeb(String url){
		HttpGet get = new HttpGet(url);
		
//		get.setHeader(	
//				"User-Agent",
//				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; qdesk 2.5.1277.202; EmbeddedWB 14.52 from: http://www.bsalsa.com/ EmbeddedWB 14.52; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; staticlogin:product=cboxf2010&act=login&info=ZmlsZW5hbWU9UG93ZXJXb3JkMjAxME94Zl9TcGVjaWFsLmV4ZSZtYWM9NjRGRUY5NzA5OUZENEZDNDhEN0JFNjVGRjFDMTkyQUImcGFzc3BvcnQ9JnZlcnNpb249MjAxMC42LjMuNS4yJmNyYXNodHlwZT0x&verify=f422d315013cc9b782866e1557834579; InfoPath.3; .NET4.0C; .NET4.0E)");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
		get.setHeader("x-requested-with","www.coursera.org");
//		get.setHeader("Accept-Language","zh-cn");
		get.setHeader("x-csrftoken","wdbjDGTK3FUQP6zbdD2kgXOZ");
		get.setHeader("Accept","*/*");
		get.setHeader("Content-Type","application/x-www-form-urlencoded");
		get.setHeader("Host","www.coursera.org");
		get.setHeader("Accept-Encoding","gzip, deflate");
		get.setHeader("Connection","Keep-Alive");
		get.setHeader("Referer", "https://www.coursera.org/#account/signin");
		get.setHeader("Cookie", "sessionid=48ac81be79f960fde71a67eac386bee3; __204u=6715416079-1357779420284; csrftoken=fgTMqrSpQJY7NFznhsvLud94; __utma=158142248.1072285273.1356586357.1357741675.1357779419.9; __utmb=158142248.7.10.1357779419; __utmc=158142248; __utmz=158142248.1357779419.9.9.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/");
		
		
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if(entity==null)
				return;
			System.out.println(EntityUtils.toString(entity, Consts.UTF_8));
			
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	

}
