package movielens;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.io.FileUtils;


public class UserCF {
	
	List<String> ratingData = null, itemData = null,userData = null;

	int userSize = 0,itemSize = 0;

	static int ratingSize = 0;
	public UserCF(String model){
		String filePath = "";
		if(model.endsWith("test")){
			filePath = Constant.TestFilePath;
		}else{
			filePath = Constant.ProcessFilePath;
		}

		try {
			ratingData = FileUtils.readLines(new File(filePath+"u.data"));			
			itemData =  FileUtils.readLines(new File(filePath+"u.item"));
			userData =  FileUtils.readLines(new File(filePath+"u.user"));
			
			
			//���Constant������������������һ������ʡ�� userSize  = Constant.UserSize;
			userSize  = userData.size();
			itemSize = itemData.size();
			ratingSize = ratingData.size();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * ��Ʒ-�û����ű�
	 */
	public   void itemUserTable(){
		/*
		//�û�-��Ʒӳ�䣬ĳһ���û�������Щ��Ʒ
		//   �û�ID   �û�ϲ����Ʒ�б�
		Map<Integer,ArrayList<Integer>> userItemMap = new HashMap<Integer,ArrayList<Integer>>();
		
		for(int i=0; i< ratingSize; i++){
			//��������ݸ�ʽ user id,item id,rating,timestamp
			String[] rating = ratingData.get(i).split(",");
			Integer uId = Integer.valueOf(rating[0]);
			Integer itemId = Integer.valueOf(rating[1]);
			Integer score = Integer.valueOf(rating[2]);
			
			if(userItemMap.containsKey(uId)){
				userItemMap.get(uId).add( itemId );
			}else{
				userItemMap.put( uId , new ArrayList<Integer>());
				userItemMap.get( uId ).add( itemId );
			}
		}
		*/
		//��Ʒ�û�ӳ�䣬ĳһ����Ʒ����Щ�û�����
		//   �û�ID   �û�ϲ����Ʒ�б�
		Map<Integer,ArrayList<Integer>> itemUserMap = new HashMap<Integer,ArrayList<Integer>>();
		
		
		//�û��ۿ��ĵ�Ӱ����
		int[] userWatchMovieNum = new int[userSize + 1];
		
		
		for(int i=0; i< ratingSize; i++){
			//��������ݸ�ʽ user id,item id,rating,timestamp
			String[] rating = ratingData.get(i).split(",");
			Integer uId = Integer.valueOf(rating[0]);
			Integer itemId = Integer.valueOf(rating[1]);
			Integer score = Integer.valueOf(rating[2]);
			
			userWatchMovieNum[uId] += 1;
			
			if(itemUserMap.containsKey(itemId)){
				itemUserMap.get(itemId).add( uId );
			}else{
				itemUserMap.put( itemId , new ArrayList<Integer>());
				itemUserMap.get( itemId ).add( uId );
			}
		}
		 
		//�����û���ͬ�����ĵ�Ӱ��Ŀ
		int [][] userWatchCommonMovieNum = new int[userSize+1][userSize+1];
		
		
		//�����û����������е�Ӱ
		Iterator<Integer> itemKeys = itemUserMap.keySet().iterator();
		
		for( ; itemKeys.hasNext(); ){
			Integer key = itemKeys.next();
			//ĳ����ĳ����Ӱ�����û�
			List<Integer> userList = itemUserMap.get(key);
			
			for(int i = 0;i < userList.size() ; i++){				
				for (int j = i+1; j < userList.size(); j++) {				
					userWatchCommonMovieNum[userList.get(i)][userList.get(j)] +=1 ;
					userWatchCommonMovieNum[userList.get(j)][userList.get(i)] +=1 ;
				}
			}
		}
		
		
		//�û�֮���������
		double [][] userSimilarity = new double[userSize+1][userSize+1];
		for(int i = 1; i< userSimilarity.length; i++){
			for(int j = i+1; j < userSimilarity.length; j++){				
				userSimilarity[i][j] = userWatchCommonMovieNum[i][j]*1.0/Math.sqrt(userWatchMovieNum[i] * userWatchMovieNum[j]);
				userSimilarity[j][i] = userSimilarity[i][j];
			}
			
		}
		
		
//		for (int i = 1; i < userWatchMovieNum.length; i++) {
//			System.out.println(userWatchMovieNum[i]);
//			
//		}
//		printMatrix(userWatchCommonMovieNum);
		printMatrix(userSimilarity);
	}
	
	

	public static void main(String[] args) {
		UserCF uCf = new UserCF("test");
		uCf.itemUserTable();
		
		
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
