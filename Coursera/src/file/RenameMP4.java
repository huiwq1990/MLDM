package file;

import java.io.File;

public class RenameMP4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String foldName = "C:/Users/hg/Desktop/Models/1";
		File fold = new File(foldName);
		String[] names= fold.list();
		
		for (int i = 0; i < names.length; i++) {
			String str = names[i];
			if(str.substring(str.indexOf("(")).contains("-")){
				continue;
			}
			int indexOf = str.indexOf("(")+3;
			String newName = str.substring(0,indexOf)+"-"+str.substring(indexOf);
			
			File f = new File(foldName+"/"+str);
			System.out.println(f.getName());

			f.renameTo(new File(foldName+"/"+newName));
			System.out.println(newName);
			
		}	
		
	}

}
