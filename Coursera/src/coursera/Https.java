package coursera;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class Https {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		org.apache.http.client.HttpClient hc = new org.apache.http.impl.client.DefaultHttpClient();
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
       nvps.add(new BasicNameValuePair("uerName", "s"));
       nvps.add(new BasicNameValuePair("userCode", "s"));
       //请根据实际修改上送包xml数据,POST请求没有长度限制，get请求太长会报错,根据实际情况，一般的数据传送会要求进行签名、BASE64编码或者压缩等机制进行传输
//       nvps.add(new BasicNameValuePair("reqData",getrevFromBASE64(body.getBytes("GBK"))));  
       UrlEncodedFormEntity   urlEncodedFormEntity = new UrlEncodedFormEntity(nvps,"GBK");
       String url = "https://"+yourConnectIp+":"+yourConnectPort;
       //加载证书
       java.security.KeyStore trustStore = java.security.KeyStore.getInstance(java.security.KeyStore.getDefaultType());
       //"123456"为制作证书时的密码
       trustStore.load(new FileInputStream(new File("你的证书位置")), "123456".toCharArray());
       org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(trustStore);
       //不校验域名
       socketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
       //这个8446是和被访问端约定的端口，一般为443
       org.apache.http.conn.scheme.Scheme sch = new org.apache.http.conn.scheme.Scheme("https", socketFactory, 8446);
       hc.getConnectionManager().getSchemeRegistry().register(sch);
       org.apache.http.client.methods.HttpPost hr = new org.apache.http.client.methods.HttpPost(url);

       hr.setEntity(urlEncodedFormEntity);
       hr.setHeader("Content-Type", "application/x-www-form-urlencoded");
       org.apache.http.HttpResponse hres = hc.execute(hr);
       org.apache.http.HttpEntity entity = hres.getEntity();
       re_code = hres.getStatusLine().statusCode;
       if (re_code == 200) {
       //your successCode here
       String repMsg = org.apache.http.util.EntityUtils.toString(entity,"GBK");
       }else{
       //your failCode here
       }
       
//最后，根据实际需要，解析返回报文，决定是否去关闭连接 
	}

}
