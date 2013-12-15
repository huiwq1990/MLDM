package coursera;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebIntelligenceAndBigData {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String courseName = CourseInfo.webIntelligenceAndBigData;
		
		createFile(courseName);
		download( courseName);
		
	}
	public static void download(String courseName) throws IOException{
		
		File input = new File("page/"+courseName+".htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Element videoListElement = doc.select("div.course-lectures-list").first();

	
		Elements courseResourse = videoListElement.select("div.course-lecture-item-resource");
		for (int j = 0; j < courseResourse.size()-2; j++) {
			
			Elements links = courseResourse.get(j).select("a");
			
//			if(links.size()>4){
//				Element pdf =links.get(links.size()-4);
//				System.out.println(pdf.attr("href"));
//
//			}
			
			
			Element srt =links.get(links.size()-2);
			if(srt.attr("title").equals("Subtitles (srt)")){				
				System.out.println(srt.attr("href"));
			}

//			Element mp4 = links.last();
//			System.out.println(mp4.attr("href"));
		}
	}
	public static void createFile(String courseName) throws IOException{
		
		File c = new File("course"+"/"+courseName);
		c.mkdir();
		
		File input = new File("page/"+courseName+".htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Element videoListElement = doc.select("div.course-lectures-list").first();
		Elements headers = videoListElement.select("div.course-item-list-header");
		for (int i=0; i<headers.size();i++) {
			String headerName = headers.get(i).getElementsByTag("h3").text();
			if(headerName.contains(":")){
				headerName = i+1+" "+headerName.substring(headerName.lastIndexOf(":")+1);
			}

			File folder = new File("course"+"/"+courseName+"/"+headerName);
			folder.mkdir();
		}
	}

}
