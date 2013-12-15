package network.intrusion;

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

/**
 * 读取所有back类型攻击的数据
 * 
 * @author hg
 * 
 */
public class BackDataPreprocess {

	// 设读取文件的缓存为40MB
	static int bufferSize = 200 * 1024 * 1024;
	// 文件的根目录
	static String filePath = "D:/KDD/1999/";
	static String originalFile = filePath + "kddcup.data.corrected";
	static String backFile = filePath + "back.data";
	static String backRefineFile = filePath + "back.arff";
	public static void main(String[] args) {
		
		dataExtract();
		
		attributeDel();
	}

	public static void dataExtract() {

		// 建立缓冲文本输入流
		File file = new File(originalFile);
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader input = null;

		FileWriter output = null;
		try {
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			inputStreamReader = new InputStreamReader(bufferedInputStream);
			input = new BufferedReader(inputStreamReader, bufferSize);

			output = new FileWriter(backFile);

			String line = null;
			while ((line = input.readLine()) != null) {
				
//				line.split(",");
				
				if (line.contains("back")) {
					output.write(line + "\n");
				}
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
	
	/**
	 * 选择前13列
	 */
	static void attributeDel(){
		String[] attrs = "duration、protocol_type、service、flag、src_bytes、dst_bytes、land、wrong_fragment、urgent、hot、num_failed_logins、logged_in、num_compromised".split("、");
		
		String head = "@relation back\n";
		head += "@attribute "+ attrs[0] + " real"+"\n";				
		head += "@attribute "+ attrs[1] + " {tcp,udp,icmp}"+"\n";
		
				
		String attr3 = "’aol’, ‘auth’, ‘bgp’, ‘courier’, ‘csnet_ns’, ‘ctf’, ‘daytime’, ‘discard’," +
				" ‘domain’, ‘domain_u’, ‘echo’, ‘eco_i’, ‘ecr_i’, ‘efs’, ‘exec’, ‘finger’, ‘ftp’, " +
				"‘ftp_data’, ‘gopher’, ‘harvest’, ‘hostnames’, ‘http’, ‘http_2784′, ‘http_443′," +
				" ‘http_8001′, ‘imap4′, ‘IRC’, ‘iso_tsap’, ‘klogin’, ‘kshell’, ‘ldap’, ‘link’, " +
				"‘login’, ‘mtp’, ‘name’, ‘netbios_dgm’, ‘netbios_ns’, ‘netbios_ssn’, ‘netstat’, " +
				"‘nnsp’, ‘nntp’, ‘ntp_u’, ‘other’, ‘pm_dump’, ‘pop_2′, ‘pop_3′, ‘printer’, ‘private’, " +
				"‘red_i’, ‘remote_job’, ‘rje’, ‘shell’, ‘smtp’, ‘sql_net’, ‘ssh’, ‘sunrpc’, ‘supdup’, " +
				"‘systat’, ‘telnet’, ‘tftp_u’, ‘tim_i’, ‘time’, ‘urh_i’, ‘urp_i’, ‘uucp’, ‘uucp_path’, ‘vmnet’," +
				" ‘whois’, ‘X11′, ‘Z39_50′";
		String[] attr3Val = attr3.split(", ");
		
		String attr3New = attr3Val[0].substring(1, attr3Val[0].length()-1);
		for(int i =1;i<attr3Val.length;i++){
			attr3New += "," + attr3Val[i].substring(1, attr3Val[i].length()-1);
		}
		
		head += "@attribute "+ attrs[2] + " {"+attr3New +"}\n";
		
		String attr4 = "’OTH’, ‘REJ’, ‘RSTO’, ‘RSTOS0′, ‘RSTR’, ‘S0′, ‘S1′, ‘S2′, ‘S3′, ‘SF’, ‘SH’";
		String[] attr4Val = attr4.split(", ");
		
		String attr4New = attr4Val[0].substring(1, attr4Val[0].length()-1);
		for(int i =1;i<attr4Val.length;i++){
			attr4New += "," + attr4Val[i].substring(1, attr4Val[i].length()-1);
		}
		
		head += "@attribute "+ attrs[3] + " {"+attr4New +"}\n";
		
		for(int i= 4 ; i< 13; i++){
			head += "@attribute "+ attrs[i] + " real"+"\n";
		}
		
		
		head += "@attribute  class {back}\n";
		
		head += "@data\n";		
		
		
		
		try {
			List<String> lines = FileUtils.readLines(new File(backFile));
			String newLines = "";
			for(String line : lines){
				String[] vals = line.split(",");
				
				for(int i= 0;i < 13; i++){
					newLines+= vals[i]+",";
				}
				
//				newLines+= vals[41]+"\n";
				newLines+= "back"+"\n";
			}
			
			FileUtils.writeStringToFile(new File(backRefineFile), head +newLines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
