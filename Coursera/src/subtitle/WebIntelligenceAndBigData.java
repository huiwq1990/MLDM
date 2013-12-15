package subtitle;

import java.io.File;

public class WebIntelligenceAndBigData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		changeSrtFileName();
	}
	
	/**
	 * ������Ļ������
	 * 
	 * ��Ļԭ�������ƣ�2 - 1 - 1-1 Basic Indexing (7_22).srt
	 * 
	 * ��Ƶ�ļ������ƣ�2 - 1 - 1-1 Basic Indexing (722)
	 * 
	 * ����Ļ���Ƹĳ���Ӧ����Ƶ����
	 * 
	 */
	static void changeSrtFileName(){
		String filePath = "G:/course/Web Intelligence and Big Data/";
		
		File path = new File(filePath);
		
		
		File[] chapters = path.listFiles();
		
		for (int i = 0; i < chapters.length; i++) {
			
			File[] chapterFiles = chapters[i].listFiles();
			for (int j = 0; j < chapterFiles.length; j++) {
				File file = chapterFiles[j];
				String name = chapterFiles[j].getName();
				if(name.contains("_")){			
					int loc = file.getPath().indexOf("_");
					
					System.out.println(file.getPath().substring(0, loc)+file.getPath().substring(loc+1));
					file.renameTo(new File(file.getPath().substring(0, loc)+file.getPath().substring(loc+1)));
				}
				
			}
		}
		
	}

}
