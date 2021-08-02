package Engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	public static int pagecount = 0;
	public static int crawlcount = 0;
	static HashSet<String> uniqueLinks = new HashSet<String>();

	public static void webCrawl(String urlToCrawl, int maxLimit) {
		uniqueLinks.add(urlToCrawl);
		try {
			Document doc = Jsoup.connect(urlToCrawl).get();
			String pattern = ".*" + urlToCrawl.replaceAll("^(http|https)://", "") + ".*";
			System.out.println("-------------------------------------------------------");
			System.out.println("URL Pattern for Parsing: " + pattern);
			System.out.println("-------------------------------------------------------");
			System.out.println("CRAWLING............");

			Elements linksOnPage = doc.select("a[href]");
			String currentURL;
			for (Element page : linksOnPage) {
				currentURL = page.attr("abs:href");
				if (uniqueLinks.contains(currentURL)) {
				
				} else if (!Pattern.matches(pattern, currentURL)) {
					
				} else {
					uniqueLinks.add(page.attr("abs:href"));
					
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void htmlToText() {
		try {

			String txt, currentURL;
			String filePath = "C:\\Users\\admin\\eclipse-workspace\\Search_Engine\\textFiles";
			Iterator<String> itr = uniqueLinks.iterator();
			while (itr.hasNext()) {
				currentURL = itr.next();
				Document document = Jsoup.connect(currentURL).get();
				txt = document.text();
				String fileName = document.title().replaceAll("[^a-zA-Z0-9_-]", "") + ".txt";
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath + fileName, true));

				out.write(currentURL + " " + txt);
				out.close();

			}
			// System.out.println("Total Page Counting = " + crawlcount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void spider(String urlToCrawl) {
		int maxLimit = 1000;
		webCrawl(urlToCrawl, maxLimit);
		System.out.println("\nCONVERTING HTML TO TEXT FILES............");
		htmlToText();
		System.out.println("CONVERSION IS DONE...!!!");

	}

}
