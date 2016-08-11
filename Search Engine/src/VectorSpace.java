import java.util.ArrayList;


public class VectorSpace {

	ArrayList<Word> query = new ArrayList<Word>();
	ArrayList<Document> docList = new ArrayList<Document>();

	public VectorSpace(ArrayList<Document> doclist, ArrayList<Word> query) {
		this.query = query;
		this.docList = doclist;
		System.out.println("corpustan alinan size:"+doclist.size());
		findVectorScores();
	}


	private void findVectorScores(){
		for(int i = 0; i < docList.size(); i++){
			double okapitfScore = 0;
			double okapitfIdfScore = 0;
			ArrayList<Word> documentWords = docList.get(i).getTermFreqVector();
			for(int j = 0; j < query.size(); j++){
				Word w = documentWords.get(j);

				double okapiTf = w.getOkapitf();
				double okapiTfIdf = w.getOkapitfIdf();
				okapitfScore = okapitfScore + (okapiTf * query.get(j).getOkapitf());
				okapitfIdfScore = okapitfIdfScore + (okapiTfIdf * query.get(j).getOkapitf());
			}
			docList.get(i).setOkapitfIdfScore(okapitfIdfScore);
			docList.get(i).setOkapitfScore(okapitfScore);
		}
	//	sortArray();
	}

	//sort wordProbList according to their frequencies
	public void sortArray(){
		int n = docList.size();
		System.out.println("sort yapiyor");
		//insertion sort
		for (int i = 1; i < n; i++){
			int j = i;
			Document B = docList.get(i);
			while ((j > 0) && (docList.get(j-1).getOkapitfScore() > B.getOkapitfScore())){
				docList.add(j, docList.get(j-1));
				docList.remove(j+1);
				j--;
			}
			docList.add(j, B);
			docList.remove(j+1);
		}
		ArrayList<Document> tempList = new ArrayList<Document>();
		int j = n - 1;
		while(j >= 0){
			tempList.add(docList.get(j));
			j--;
		}
		docList = tempList;
		System.out.println("sort bitti");
	}


	public ArrayList<Document> getDocList() {
		return docList;
	}


}
