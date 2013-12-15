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
			
			
			//如果Constant类中设置了数量，这一步可以省略 userSize  = Constant.UserSize;
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
	 * 计算用户对项目类的认知度
	 * 
	 * 用户u对项目类k的认知度等于
	 * 用户u对项目类k评分的项目数目 除以 用户评过分的项目总数
	 */
	public void userGenreCognition(){
		
		//用户对项目类的认知度   行号表示用户ID,u   列号表示项目类k.
		double [][] userItemGenreCognition = new double[userSize+1][genreSize+1];
		
		//   用户ID   用户喜欢物品列表
		Map<Integer,ArrayList<Integer>> itemUserMap = new HashMap<Integer,ArrayList<Integer>>();
		
		//用户观看每类项目数量
		int[][] userWatchGenreMovieNum = new int[userSize+1][genreSize+1];
		
		//用户观看的电影数量
		int[] userWatchMovieNum = new int[userSize + 1];
		
		
		for(int i=0; i< ratingSize; i++){
			//处理后数据格式 user id,item id,rating,timestamp
			String[] rating = ratingData.get(i).split(",");
			Integer uId = Integer.valueOf(rating[0]);
			Integer itemId = Integer.valueOf(rating[1]);
			Integer score = Integer.valueOf(rating[2]);
			
			//用户看电影数目自加
			userWatchMovieNum[uId] += 1;
			
			//从item文件中取出信息
			String[] item = itemData.get(itemId-1).split(",");
			

			if(!item[0].equals(itemId.toString())){
				System.out.println("电影的Id："+ itemId+" ;详细信息："+itemData.get(itemId-1));
			}
			
			
			for (int j = 0 ; j < genreSize; j++) {
				//分类属性j在评分记录中的位置
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

	
	//打印矩阵，下标从1开始
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
