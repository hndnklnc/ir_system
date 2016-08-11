import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
 * Gets all necessary statistics from web
 * Then creates documents list with their information
 */
public class Corpus {

	private String invertedListlink;
	private ArrayList<Document> docList = new ArrayList<Document>();
	private ArrayList<Integer> readNumbers = new ArrayList<Integer>();
	private ArrayList<Word> query = new ArrayList<Word>();

	public static int NUM_DOCS;
	public static int NUM_TERMS;
	public static int NUM_UNIQUE_TERMS;
	public static int AVE_DOCLEN;

	public Corpus(ArrayList<Word> query1) throws IOException{
		invertedListlink = "http://fiji4.ccs.neu.edu/~zerg/lemurcgi/lemur.cgi?g=p&d="+Main.DATABASE;
		this.query = query1;
		//creates link to get inverted list for query terms
		for(int i = 0; i < query.size(); i++)
			invertedListlink = invertedListlink + "&v=" + query.get(i).getName();	
		System.out.println(invertedListlink);
		getDataBaseInfo();
		readURL();
		findModelScores();
	}




	private void readURL() throws IOException{
		URL url = null;
		try {
			url = new URL(invertedListlink);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not Conneted to Internet!!!!");
		}
		System.out.println(invertedListlink);
		// read text returned by server
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		String text = "";
		boolean isStart = false;
		//removes html tags and gets useful information
		while ((line = in.readLine()) != null) {
			if(isStart && line.equalsIgnoreCase("<HR>"))
				isStart = false;
			if(isStart)	
				text = text + line;
			if(line.equalsIgnoreCase("<BODY>"))
				isStart = true;
		}
		text = text + " ";
		in.close();	    
		setReadNumbers(text);
		setDocList();
	}

	//gets the numbers from the text that is read from server
	private void setReadNumbers(String text){
		String [] array = text.split(" ");
		//System.out.println("array size:"+array.length);
		for(int i = 0; i<array.length; i++){
			if(!array[i].equals("")){
				readNumbers.add(Integer.parseInt(array[i]));
			}
		}
	}

	private void setDocList(){
		int querySize = query.size();
		int readTextIdx = 0;
		for(int i = 0; i < querySize; i++){			
			int df = readNumbers.get(readTextIdx + 1);
			int ctf = readNumbers.get(readTextIdx);		
			query.get(i).setCtf(ctf);
			query.get(i).setDf(df);
			query.get(i).calculateModels(querySize, 12.53);
			int termInfoBegin = readTextIdx + 2;
			int termInfoEnd = readTextIdx + 2 + (df * 3);

			for(int j = termInfoBegin; j < termInfoEnd; j++){	
				//System.out.println(readTextIdx);
				Word w = new Word();
				w.setName(query.get(i).getName());
				w.setDf(df);
				w.setCtf(ctf);

				int docId = readNumbers.get(j);
				int docIdx = isDocInList(docId);
				if(isDocInList(docId) == -1){
					Document d = new Document();
					d.setDocId(readNumbers.get(j));
					j++;
					d.setDocLength(readNumbers.get(j));
					j++;
					w.setTf(readNumbers.get(j));
					d.addWord(w,i);
					docList.add(d);
				}else{
					Document d = docList.get(docIdx);
					j = j + 2;
					w.setTf(readNumbers.get(j));
					d.addWord(w,i);
				}	
				readTextIdx = j + 1;

			}
		}		
	}



	private void findModelScores(){		
		for(int i = 0; i < docList.size(); i++){
			Document d = docList.get(i);
			ModelScore scores = new ModelScore(d, query);
			d.setBm25Score(scores.getBM25Score());
			d.setJelinekScore(scores.getJelinekScore());
			d.setLaplaceScore(scores.getLaplaceScore());
		}
	}

	//If there is docid in the doc list  it returns the index else it returns -1
	private int isDocInList(int docId){
		int isIn = -1;
		for(int i = 0; i < docList.size(); i ++){
			if(docList.get(i).docId == docId ){
				isIn = i;
				break;
			}
		}		
		return isIn;
	}

	private void getDataBaseInfo() throws IOException{
		URL url = null;
		try {
			url = new URL("http://fiji4.ccs.neu.edu/~zerg/lemurcgi/lemur.cgi?g=p&d=?");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not Conneted to Internet!!!!");
		}
		// read text returned by server
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		String text = "";
		boolean isStart = false;
		int count = 0;
		//removes html tags and gets useful information
		while ((line = in.readLine()) != null) {
			if(isStart)
				text = text + line;
			if(line.equalsIgnoreCase("<BR>"))
				count++;
			if(count == 3)
				isStart = true;	    
			else if(count == 4)
				break;
		}
		text = text.replaceAll("\\D+"," ");
		String[] corpusStat = text.split(" ");
		NUM_DOCS = Integer.parseInt(corpusStat[2]);
		NUM_TERMS = Integer.parseInt(corpusStat[3]);
		NUM_UNIQUE_TERMS = Integer.parseInt(corpusStat[4]);
		AVE_DOCLEN = Integer.parseInt(corpusStat[5]);	    
	}

	public ArrayList<Document> getDocList() {
		return docList;
	}

	public ArrayList<Word> getQuery() {
		return query;
	}	
}
