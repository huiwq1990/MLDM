package movielens;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.io.FileUtils;


public class CognitionCF {
	
	List<String> ratingData = null, itemData = null,userData = null;

	int userSize = 0,itemSize = 0, ratingSize = 0,genreSize = 0;
	public CognitionCF(String model){
		String filePath = "";
		filePath = Constant.RootFilePath+"/"+model+"/";
		try {
			System.out.println(filePath);
			ratingData = FileUtils.readLines(new File(filePath+"u.data"));			
			itemData =  FileUtils.readLines(new File(filePath+"u.item"));
			userData =  FileUtils.readLines(new File(filePath+"u.user"));
			
			
			//���Constant������������������һ������ʡ�� userSize  = Constant.UserSize;
			userSize  = userData.size();
			itemSize = itemData.size();
			ratingSize = ratingData.size();
			genreSize = Constant.GenreSize;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * �����û�����Ŀ�����֪��
	 * 
	 * �û�u����Ŀ��k����֪�ȵ���
	 * �û�u����Ŀ��k���ֵ���Ŀ��Ŀ ���� �û������ֵ���Ŀ����
	 */
	public void userGenreCognition(){
		
		//�û�����Ŀ�����֪��   �кű�ʾ�û�ID,u   �кű�ʾ��Ŀ��k.
		double [][] userItemGenreCognition = new double[userSize+1][genreSize+1];
		
		//   �û�ID   �û�ϲ����Ʒ�б�
		Map<Integer,ArrayList<Integer>> itemUserMap = new HashMap<Integer,ArrayList<Integer>>();
		
		//�û��ۿ�ÿ����Ŀ����
		int[][] userWatchGenreMovieNum = new int[userSize+1][genreSize+1];
		
		//�û��ۿ��ĵ�Ӱ����
		int[] userWatchMovieNum = new int[userSize + 1];
		
		
		for(int i=0; i< ratingSize; i++){
			//��������ݸ�ʽ user id,item id,rating,timestamp
			String[] rating = ratingData.get(i).split(",");
			Integer uId = Integer.valueOf(rating[0]);
			Integer itemId = Integer.valueOf(rating[1]);
			Integer score = Integer.valueOf(rating[2]);
			
			//�û�����Ӱ��Ŀ�Լ�
			userWatchMovieNum[uId] += 1;
			
			//��item�ļ���ȡ����Ϣ
			String[] item = itemData.get(itemId-1).split(",");
			

			if(!item[0].equals(itemId.toString())){
				System.out.println("��Ӱ��Id��"+ itemId+" ;��ϸ��Ϣ��"+itemData.get(itemId-1));
			}
			
			
			for (int j = 0 ; j < genreSize; j++) {
				//��������j�����ּ�¼�е�λ��
				int attrLoc = item.length-genreSize + j;
//				System.out.println(item.length);
				if(Integer.valueOf(item[attrLoc]) == 1){
					userWatchGenreMovieNum[uId][j+1] += 1;
				}
			}
			
		}
		 for (int i = 1; i < userSize +1 ; i++) {
			 if(userWatchMovieNum[i] == 0){
				 continue;
			 }
				for (int j = 1 ; j < genreSize + 1 ; j++) {
//					if()
//					System.out.println(i+"  "+ j);
					userItemGenreCognition[i][j] =  userWatchGenreMovieNum[i][j] *1.0/userWatchMovieNum[i];
				
			}
			
		}
		
		printMatrix(userItemGenreCognition);
	}
	
	

	public static void main(String[] args) {
		CognitionCF cogCf = new CognitionCF("cong");
		
		cogCf.userGenreCognition();
	}

	
	//��ӡ�����±��1��ʼ
	public static void printMatrix(int[][] m){		
		for(int i = 1; i< m.length; i++){
			for(int j = 1; j < m[i].length; j++){
				System.out.print(m[i][j] + "  ");
			}
			System.out.println();
		}
	}
	public static void printMatrix(double[][] m){		
		for(int i = 1; i< m.length; i++){
			for(int j = 1; j < m[i].length; j++){
				System.out.print(m[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
