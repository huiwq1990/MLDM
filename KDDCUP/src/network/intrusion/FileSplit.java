package network.intrusion;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSplit
{	
	public static void main(String[] args) throws IOException
	{
		long timer = System.currentTimeMillis();
		int bufferSize = 20 * 1024 * 1024;//���ȡ�ļ��Ļ���Ϊ20MB
		
		//���������ı�������
		File file = new File("/media/Data/��ҵ���/kdd cup/����/userid_profile.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
		BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);
		
		int splitNum = 112-1;//Ҫ�ָ�Ŀ�����һ
		int fileLines = 23669283;//�����ļ�������
		long perSplitLines = fileLines / splitNum;//ÿ���������
		for (int i = 0; i <= splitNum; ++i)
		{
			//�ָ�
			//ÿ���齨��һ�����
			FileWriter output = new FileWriter("/home/haoqiong/part" + i + ".txt");
			String line = null;
			//���ж�ȡ���������
			for (long lineCounter = 0; lineCounter < perSplitLines && (line = input.readLine()) != null; ++lineCounter)
			{
				output.append(line + "\r");
			}
			output.flush();
			output.close();
			output = null;
		}
		input.close();
		timer = System.currentTimeMillis() - timer;
		System.out.println("����ʱ�䣺" + timer);
	}
}
