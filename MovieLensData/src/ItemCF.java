import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


public class ItemCF {
	
	private static int itemSize = 1682;

	

	// 处理后的文件夹
		private static String processFilePath = "D:/Data/MovieLens/process/";

	public static void main(String[] args) {
		List<String> ratingData = null;
		
		List<String> itemData = null;
		try {
			ratingData = FileUtils.readLines(new File(processFilePath+"u.data"));
			
			itemData =  FileUtils.readLines(new File(processFilePath+"u.item"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<Integer,ArrayList<Integer>> userItemMap = new HashMap<Integer,ArrayList<Integer>>();
		
		for(int i=0; i<ratingData.size(); i++){
			String[] rating = ratingData.get(i).split(",");
			
			if(userItemMap.containsKey(Integer.valueOf(rating[0]))){
				userItemMap.get(Integer.valueOf(rating[0])).add(Integer.valueOf(rating[1]));
			}else{
				userItemMap.put(Integer.valueOf(rating[0]), new ArrayList<Integer>());
				userItemMap.get(Integer.valueOf(rating[0])).add(Integer.valueOf(rating[1]));
			}
		}
		
		System.out.println(userItemMap.get(246));
		
		int C[][] = new int[itemSize+1][itemSize+1];
		int itemNum[] = new int[itemSize+1];
		
		Iterator<Integer> users= userItemMap.keySet().iterator();
		
		for (; users.hasNext();) {
			int uid = users.next();
			List<Integer> userLikeItems = userItemMap.get(uid);
			
			for(int i=0; i< userLikeItems.size();i++){	
				int itemA = userLikeItems.get(i);
				itemNum[itemA]++;
				
				for (int j = 0; j < userLikeItems.size(); j++) {
					int itemB = userLikeItems.get(j);
					
					if(itemA == itemB){
						continue;
					}
					C[itemA][itemB]++;
				}
			}
			
		}
		
		
		
		double W[][] = new double[itemSize+1][itemSize+1];
		for(int i=1; i<= itemSize ;i++){	
			
			for (int j = 1; j <= itemSize; j++) {
				 W[i][j] = C[i][j]/Math.sqrt(itemNum[i]*itemNum[j]);
			}
		}
		
//		for(int i =0;i < 1600;i++){
//			System.out.println(W[1][i]);
//		}
		
		
		testSim(W);
		recommendation(246, 5, W);
	}

	/**
	 * 推荐物品
	 */
	static void recommendation(int uId, int recommendNum, double [][] W){
	List<String> ratingData = null;
		
		List<String> itemData = null;
		
//		List<String> userLikeItems = new ArrayList<String>();
		try {
			ratingData = FileUtils.readLines(new File(processFilePath+"u.data"));
			
			itemData =  FileUtils.readLines(new File(processFilePath+"u.item"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double[] recommendVal = new double[itemSize+1] ;
		
		for (int i = 0; i < ratingData.size(); i++) {
			
			String[] lineInfo = ratingData.get(i).split(",");
			
			if(Integer.valueOf(lineInfo[0]) == uId){
				int itemId = Integer.valueOf(lineInfo[1]);
				int rating = Integer.valueOf(lineInfo[2]);
				
				
				for(int j=0; j<itemSize; j++){
					recommendVal[itemId] += rating * W[itemId][j];
				}
			}
			
		}
		
		double max = 0;
		int itemId = 0;
		for (int i = 0; i < recommendVal.length; i++) {


			if(recommendVal[i]>max){
				max = recommendVal[i];
				itemId = i;
			}
		}	
		
		System.out.println(max);
		System.out.println(itemId);
	}
	
	/**
	 * 测试连个物品之间的相似度
	 * @param W
	 */
	static void testSim(double [][] W){
		
		
		String movieA,movieB;
		movieA = "Toy Story";
		movieB = "Groundhog Day";
		
//		movieA = "Aladdin";
//		movieB = "Beauty and the Beast";
		
		System.out.println(W[findMovieId(movieA)][findMovieId(movieB)] );

	}
	
	/**
	 * 根据电影名称查找电影的Id
	 * @param movieName
	 * @return
	 */
	static int findMovieId(String movieName){
		List<String> itemData = null;
		try {		
			itemData =  FileUtils.readLines(new File(processFilePath+"u.item"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < itemData.size(); i++) {
			if(itemData.get(i).contains(movieName)){				
				return Integer.valueOf(itemData.get(i).split(",")[0]);
			}
		}
		return 0;
	}
}
