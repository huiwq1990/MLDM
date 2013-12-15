import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class ass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	FileUtils.readLines(new File("D:/KDD/1999/kddcup.data.corrected"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
	}

}
