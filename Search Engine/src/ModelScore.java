import java.util.ArrayList;


public class ModelScore {
	Document d;
	ArrayList<Word> queries = new ArrayList<Word>();


	public ModelScore(Document d, ArrayList<Word> queries){
		this.d = d;
		this.queries = queries;
	}

	public double getLaplaceScore(){
		ArrayList<Word> docWords = d.getTermFreqVector();
		double score = 0;
		for(int i = 0; i < docWords.size(); i++)
			for(int j = 0; j < queries.get(i).getTf();j++)
				score = score + Math.log10((double)(docWords.get(i).getTf() + 1)/ (double)((d.getDocLength() + Corpus.NUM_UNIQUE_TERMS)));		
		return score;
	}

	public double getJelinekScore(){
		ArrayList<Word> docWords = d.getTermFreqVector();
		double score = 0;
		for(int i = 0; i < docWords.size(); i++){
			for(int j = 0; j < queries.get(i).getTf(); j++)
				score = score + Math.log10((0.8 * (double)docWords.get(i).getTf() /(double) d.getDocLength()) +
						0.2 * ((double)queries.get(i).getCtf() / (double)Corpus.NUM_TERMS));
		}			
		return score;
	}

	public double getBM25Score(){
		ArrayList<Word> docWords = d.getTermFreqVector();
		double k1 = 1.2;
		double b = 1;
		double score = 0;
		for(int i = 0; i < docWords.size(); i++){
			double tf = docWords.get(i).getTf();
			double length = (double)d.getDocLength() / (double)Corpus.AVE_DOCLEN;
			double K = k1 * (1 - b + b * length);
			double qtf = queries.get(i).getTf();	
			double log = (Corpus.NUM_TERMS + 0.5) / (queries.get(i).getDf() + 0.5);
			for(int j = 0; j < queries.get(i).getTf(); j++)
				score = score + (Math.log(log)) * (((k1 + 1) * tf) / (K + tf)) * ((100 + 1) * qtf / (100 + qtf));
		}
		return score;		
	}
}
