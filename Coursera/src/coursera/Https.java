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
       //�����ʵ���޸����Ͱ�xml����,POST����û�г������ƣ�get����̫���ᱨ��,����ʵ�������һ������ݴ��ͻ�Ҫ�����ǩ����BASE64�������ѹ���Ȼ��ƽ��д���
//       nvps.add(new BasicNameValuePair("reqData",getrevFromBASE64(body.getBytes("GBK"))));  
       UrlEncodedFormEntity   urlEncodedFormEntity = new UrlEncodedFormEntity(nvps,"GBK");
       String url = "https://"+yourConnectIp+":"+yourConnectPort;
       //����֤��
       java.security.KeyStore trustStore = java.security.KeyStore.getInstance(java.security.KeyStore.getDefaultType());
       //"123456"Ϊ����֤��ʱ������
       trustStore.load(new FileInputStream(new File("���֤��λ��")), "123456".toCharArray());
       org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(trustStore);
       //��У������
       socketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
       //���8446�Ǻͱ����ʶ�Լ���Ķ˿ڣ�һ��Ϊ443
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
       
//��󣬸���ʵ����Ҫ���������ر��ģ������Ƿ�ȥ�ر����� 
	}

}
