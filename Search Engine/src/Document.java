import java.util.ArrayList;


public class Document {
	int docId;
	int docLength;
	ArrayList<Word> termFreqVector = new ArrayList<Word>();	
	private double okapitfScore;
	private double okapitfIdfScore;
	private double laplaceScore; //third retrieval system
	private double jelinekScore; //fourth retrieval system
	private double bm25Score; //fifth retrieval system
	
	public Document(){
		int size = Query.querySize;
		for(int i = 0; i < size; i++){
			Word w = new Word();
			termFreqVector.add(w);
		}
	}
	
	public double getOkapitfScore() {
		return okapitfScore;		
	}
	public void setOkapitfScore(double okapitfScore) {
		this.okapitfScore = okapitfScore;
	}
	public double getOkapitfIdfScore() {
		return okapitfIdfScore;
	}
	public void setOkapitfIdfScore(double okapitfIdfScore) {
		this.okapitfIdfScore = okapitfIdfScore;
	}	
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public int getDocLength() {
		return docLength;
	}
	public void setDocLength(int docLength) {
		this.docLength = docLength;
	}
	public ArrayList<Word> getTermFreqVector() {
		return termFreqVector;
	}
	public void setTermFreqVector(ArrayList<Word> termFreqVector) {
		this.termFreqVector = termFreqVector;
	}
	public void addWord(Word w, int index){
		w.calculateModels(docLength, (double)Corpus.AVE_DOCLEN);
		termFreqVector.remove(index);
		termFreqVector.add(index, w);
	}

	public double getLaplaceScore() {
		return laplaceScore;
	}

	public void setLaplaceScore(double laplaceScore) {
		this.laplaceScore = laplaceScore;
	}

	public double getJelinekScore() {
		return jelinekScore;
	}

	public void setJelinekScore(double jelinekScore) {
		this.jelinekScore = jelinekScore;
	}

	public double getBm25Score() {
		return bm25Score;
	}

	public void setBm25Score(double bm25Score) {
		this.bm25Score = bm25Score;
	}

}
