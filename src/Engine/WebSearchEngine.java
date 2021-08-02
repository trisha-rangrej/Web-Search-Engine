package Engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import spell.Main;

public class WebSearchEngine {

	static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	static int n = 1;
	static Scanner sc = new Scanner(System.in);
	static int R;
	static int[] right;

	public WebSearchEngine() {

		System.out.println("********************WEB SEARCH ENGINE***************************");
	}

	// Using KMP for pattern searching pat = pattern || txt = word
	public static int search1(String pat, String txt) {
		KMP kmpobj = new KMP(pat);
		int offset = kmpobj.search(txt);
		return offset;
	}

	public static void searchEngine() {

		WebSearchEngine web = new WebSearchEngine();
		Main main = new Main();
		String urlToCrawl = "https://en.wikipedia.org/";
		Crawler.spider(urlToCrawl);
		System.out.println("\nCRAWLING DONE...!!!!!");

		/**
		 * Calculating occurs to find the work frequency in every text files
		 */
		Hashtable<String, Integer> occurrs = new Hashtable<String, Integer>();
		Scanner scan = new Scanner(System.in);
		String choice = "yes";

		do {
			try {

				System.out.println("--------------------------------------------------------");
				System.out.println("ENTER THE SEARCH WORD: ");
				String p = scan.nextLine();
				long fileNumber = 0;
				int occur = 0;
				int pg = 0;
				try {

					String spellCorrector = main.wordsuggestion(p);

					if (spellCorrector != p) {
						System.out.println("Spelling is wrong");
						p = spellCorrector;
						System.out.println("We are Seaching " + p+" for you");
						p = p.toLowerCase();
					} else {

					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					File dir = new File("C:\\Users\\admin\\eclipse-workspace\\Search_Engine\\textFiles");

					File[] fileArray = dir.listFiles();
					for (int i = 0; i < fileArray.length; i++) {
						occur = SearchWord.wordSearch(fileArray[i], p); // search word as per the user input
						occurrs.put(fileArray[i].getName(), occur);
						if (occur != 0)
							pg++;
						fileNumber++;
					}
					System.out.println("\n--------------------------------------------------------------");
					//System.out.println(
							//"The Entered word " + p + " found in total " + SearchWord.wordfilecount + " files.");
					System.out.println("---------------------------------------------------------------");

					if (pg == 0) {
						System.out.println("\n\n---------------------------------------------------");
						System.out.println("Given word not found!!");
						System.out.println("Looking for the similar words.....");
						SearchWord.altWord(p); // using regx for find similar pattern
					} else {
						Sorting.pageSort(occurrs, pg); // Merge Sort for web-page ranking
					}
					System.out.println("\n\n Do you want to continue(yes/no)??");
					choice = scan.nextLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (choice.equals("yes"));
		System.out.println("\n--------------------------------------------\n");
		System.out.println(":) THANK YOU FOR USING OUR SEARCH ENGINE :)");
		System.out.println("\n***************************************************\n");

	}

	public static void main(String[] args) { // Main Method
		WebSearchEngine.searchEngine();
	}
}
