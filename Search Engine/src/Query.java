import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Query {
	
	private String query;
	private ArrayList<String> queryStrings = new ArrayList<String>(); //without stop words
	private ArrayList<Word> queryWords = new ArrayList<Word>(); //without stop words
	public static int querySize;
	
	public Query(String query) throws IOException{
		this.query = query.replaceAll("[^A-Za-z0-9 ]","").toLowerCase(); //because all words are stored lower case in the corpus
		setQueryWords();
	}
	
	
	//creates query word without stopping words
	private void setQueryWords() throws IOException{
		//System.out.println("query:"+query);
		String[] words = query.split(" ");
		for(int i = 0; i < words.length; i++)
			if(!words.equals("") && !words.equals(" ")&& !isStopWord(words[i]) && !words.equals(null))
				queryStrings.add(words[i]);
		for(int i = 0; i < queryStrings.size(); i++){
			Word w = new Word();
			w.setName(queryStrings.get(i));
			if(!isInList(w)){
				w.setTf(findTf(queryStrings.get(i)));			
				queryWords.add(w);
			}			
		}
		querySize = queryWords.size();
	}
	
	public ArrayList<Word> getQueryWords(){
		return queryWords;	
	}
		
	//checks if the word is stopping word or not.
	private boolean isStopWord(String word) throws IOException{
		boolean isStop = false;
		FileReader words = new FileReader("stoplist.txt");
		BufferedReader reader = new BufferedReader(words);
		String stopWord;
		while((stopWord = reader.readLine()) != null && !isStop)
			if(word.equals(stopWord))
				isStop = true;
		reader.close();
		return isStop;		
	}
	
	private boolean isInList(Word w){
		boolean isIn = false;
		for(int i = 0; i < queryWords.size(); i ++){
			if(queryWords.get(i).getName().equals(w.getName())){
					isIn = true;
					break;
			}
		}
		return isIn;
	}
	
	private int findTf(String word){
		int qtf = 0;
		for(int i = 0; i < queryStrings.size(); i++)
			if(queryStrings.get(i).equals(word))
					qtf++;
		return qtf;			
	}
}
