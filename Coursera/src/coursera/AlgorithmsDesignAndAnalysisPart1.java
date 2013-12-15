package coursera;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AlgorithmsDesignAndAnalysisPart1 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String courseName = "Algorithms - Design and Analysis, Part 1";
		File c = new File("course" + "/" + courseName);
		c.mkdir();

		File input = new File("page/algorithms.htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Element videoListElement = doc.select("div.course-lectures-list")
				.first();

		Elements headers = videoListElement
				.select("div.course-item-list-header");
		for (int i = 0; i < headers.size(); i++) {
			String headerName = i + 1 + " "
					+ headers.get(i).getElementsByTag("h3").text().substring(1);
			if (headerName.contains(":")) {
				headerName = headerName.replace(":", " -");
			}
			File folder = new File("course" + "/" + courseName + "/"
					+ headerName);
			folder.mkdir();
		}

		// Elements courses =
		// videoListElement.select("ul.course-item-list-section-list");

		Elements courseResourse = videoListElement.select("div.course-lecture-item-resource");
		for (int j = 0; j < courseResourse.size(); j++) {
			Elements links = courseResourse.get(j).select("a");
			// Element srt =links.get(links.size()-2);
			// System.out.println(srt.attr("href"));

			// Element pdf =links.get(1);
			// System.out.println(pdf.attr("href"));

			Element mp4 = links.last();
			System.out.println(mp4.attr("href"));
		}
	}

	public static void createFile() {

	}

}
