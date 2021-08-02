package spell;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		String t= m.wordsuggestion("trisha");
		System.out.print(t);
	}
	
	public String wordsuggestion(String word) throws IOException{
		
		String dictionaryFileName = ("C:\\Users\\admin\\eclipse-workspace\\Search_Engine\\src\\spell\\dictionary.txt");
		String inputWord = word;
		ISpellCorrector corrector = new SpellCorrector();
		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		
		//System.out.println("Suggestion is: " + suggestion);
		return suggestion;
	}

}
