import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


/*
 * This program can be run database 3 or database 2
 * Since it gets information from web, it takes about 3 minutes
 * to run each query.
 */
public class Main {

	static ArrayList<Document> doclist1= new ArrayList<Document>(); 
	static ArrayList<Document> doclist2= new ArrayList<Document>();
	static ArrayList<Document> doclist3= new ArrayList<Document>();
	static ArrayList<Document> doclist4= new ArrayList<Document>();
	static ArrayList<Document> doclist5= new ArrayList<Document>();
	static public int DATABASE = 3;
	
	public static void main(final String[] args) throws IOException {
		ArrayList<String> queries = new ArrayList<String>();
		FileReader words = new FileReader("queries.txt");
		BufferedReader reader = new BufferedReader(words);
		String query;
		
		FileReader docs = new FileReader("doclist.txt");
		BufferedReader readerdoc = new BufferedReader(docs);
		ArrayList<String> docIds = new ArrayList<String>();
		docIds.add("start");
		
		String line;
		while((line = readerdoc.readLine()) != null){
			String [] lineSp = line.split("#");
			docIds.add(lineSp[1]);
		}		
		int j = 1;
		
		if(DATABASE == 0 || DATABASE == 1){
			
		}
	
//		String fileName1 = "okapitfchange";
//		String fileName2 = "okapitfIDFchange";
//		String fileName3 = "laplacechange";
//		String fileName4 = "jerinekchange";
//		String fileName5 = "BM25change";
		String Name1 = "okapitf.txt";
		String Name2 = "okapitfIDF.txt";
		String Name3 = "laplace.txt";
		String Name4 = "jerinek.txt";
		String Name5 = "BM25k2.txt";
		
		FileWriter okapitff = new FileWriter(Name1);
		FileWriter okapitfIDFf = new FileWriter(Name2);
		FileWriter laplacef = new FileWriter(Name3);
		FileWriter jerinekf = new FileWriter(Name4);
		FileWriter BM25f = new FileWriter(Name5);
		
		BufferedWriter w1 = new BufferedWriter(okapitff);	
		BufferedWriter w2 = new BufferedWriter(okapitfIDFf);		
		BufferedWriter w3 = new BufferedWriter(laplacef);	
		BufferedWriter w4 = new BufferedWriter(jerinekf);	
		BufferedWriter w5 = new BufferedWriter(BM25f);	

		while((query = reader.readLine()) != null){
//			fileName1 = "okapitf";
//			fileName2 = "okapitfIDF";
//			fileName3 = "laplace";
//			fileName4 = "jerinek";
//			fileName5 = "BM25";
			String [] qArray = query.split("#");			
			Query q = new Query(qArray[1]);
			int qNum = Integer.parseInt(qArray[0]);
			System.out.println(qNum);
//			
//			fileName1 = fileName1 + qNum + ".txt";
//			fileName2 = fileName2 + qNum + ".txt";
//			fileName3 = fileName3 + qNum + ".txt";
//			fileName4 = fileName4 + qNum + ".txt";
//			fileName5 = fileName5 + qNum + ".txt";
			
//			FileWriter okapitf = new FileWriter(fileName1);
//			FileWriter okapitfIDF = new FileWriter(fileName2);
//			FileWriter laplace = new FileWriter(fileName3);
//			FileWriter jerinek = new FileWriter(fileName4);
//			FileWriter BM25 = new FileWriter(fileName5);
			
			
//			BufferedWriter writer1 = new BufferedWriter(okapitf);	
//			BufferedWriter writer2 = new BufferedWriter(okapitfIDF);		
//			BufferedWriter writer3 = new BufferedWriter(laplace);	
//			BufferedWriter writer4 = new BufferedWriter(jerinek);	
//			BufferedWriter writer5 = new BufferedWriter(BM25);	
			
			
			Corpus c = new Corpus(q.getQueryWords());
			
			VectorSpace vectorModel = new VectorSpace(c.getDocList(), q.getQueryWords());
			doclist1 = vectorModel.getDocList();
			doclist2 = vectorModel.getDocList();
			doclist3 = c.getDocList();
			doclist4 = c.getDocList();
			doclist5 = c.getDocList();
			
			ArrayList<Document> doc10001 = new ArrayList<Document>();
			ArrayList<Document> doc10002 = new ArrayList<Document>();
			ArrayList<Document> doc10003 = new ArrayList<Document>();
			ArrayList<Document> doc10004 = new ArrayList<Document>();
			ArrayList<Document> doc10005 = new ArrayList<Document>();
			
			doc10001 = getFirst1000(doclist1, doc10001);
			doc10002 = getFirst1000(doclist2, doc10002);
			doc10003 = getFirst1000(doclist3, doc10003);
			doc10004 = getFirst1000(doclist4, doc10004);
			doc10005 = getFirst1000(doclist5, doc10005);
			
			doc10001 = sort1(doc10001);
			doc10002 = sort2(doc10002);
			doc10003 = sort3(doc10003);
			doc10004 = sort4(doc10004);
			doc10005 = sort5(doc10005);
			
			for(int i = 0; i < 1000; i++){
//				writer1.write(qNum+" Q0 "+docIds.get(doc10001.get(i).docId)+" "+(i+1)+" "+doc10001.get(i).getOkapitfScore()+" okapitfIdf");
//				writer2.write(qNum+" Q0 "+docIds.get(doc10002.get(i).docId)+" "+(i+1)+" "+doc10002.get(i).getOkapitfIdfScore()+" okapitfIdf");
//				writer3.write(qNum+" Q0 "+docIds.get(doc10003.get(i).docId)+" "+(i+1)+" "+doc10003.get(i).getLaplaceScore()+" laplace");
//				writer4.write(qNum+" Q0 "+docIds.get(doc10004.get(i).docId)+" "+(i+1)+" "+doc10004.get(i).getJelinekScore()+" jelinek");
//				writer5.write(qNum+" Q0 "+docIds.get(doc10005.get(i).docId)+" "+(i+1)+" "+doc10005.get(i).getBm25Score()+" BM25");
				w1.write(qNum+" Q0 "+docIds.get(doc10001.get(i).docId)+" "+(i+1)+" "+doc10001.get(i).getOkapitfScore()+" okapitfIdf");
				w2.write(qNum+" Q0 "+docIds.get(doc10002.get(i).docId)+" "+(i+1)+" "+doc10002.get(i).getOkapitfIdfScore()+" okapitfIdf");
				w3.write(qNum+" Q0 "+docIds.get(doc10003.get(i).docId)+" "+(i+1)+" "+doc10003.get(i).getLaplaceScore()+" laplace");
				w4.write(qNum+" Q0 "+docIds.get(doc10004.get(i).docId)+" "+(i+1)+" "+doc10004.get(i).getJelinekScore()+" jelinek");
				w5.write(qNum+" Q0 "+docIds.get(doc10005.get(i).docId)+" "+(i+1)+" "+doc10005.get(i).getBm25Score()+" BM25");
//				writer3.newLine();
//				writer4.newLine();
//				writer5.newLine();
//				writer2.newLine();
//				writer1.newLine();
				w3.newLine();
				w4.newLine();
				w5.newLine();
				w2.newLine();
				w1.newLine();
			}
			System.out.println((j)+". query finished");
			j++;
//			writer1.close();
//			writer2.close();
//			writer3.close();
//			writer4.close();
//			writer5.close();
			
		}
		w1.close();
		w2.close();
		w3.close();
		w4.close();
		w5.close();
		
	}
	
	public static ArrayList<Document> getFirst1000(ArrayList<Document> docList, ArrayList<Document> doc1000){
		for(int i = 0; i < 1000; i++)
			doc1000.add(docList.get(i));
		return doc1000;
	}

	public static ArrayList<Document> sort1(ArrayList<Document> doc1000) {
		doc1000 = insertionSort(doc1000, 1);
		for(int i = 1000; i < doclist1.size(); i++){
			for(int j = 0; j < 1000; j++){
				if(doc1000.get(j).getOkapitfScore() < doclist1.get(i).getOkapitfScore()){
					doc1000.add(j, doclist1.get(i));		
					break;
				}
			}
		}
		return doc1000;
	}
	
	public static ArrayList<Document> sort2(ArrayList<Document> doc1000) {
		doc1000 = insertionSort(doc1000, 2);
		for(int i = 1000; i < doclist2.size(); i++){
			for(int j = 0; j < 1000; j++){
				if(doc1000.get(j).getOkapitfIdfScore() < doclist2.get(i).getOkapitfIdfScore()){
					doc1000.add(j, doclist2.get(i));		
					break;
				}
			}
		}
		return doc1000;
	}
	
	public static ArrayList<Document> sort3(ArrayList<Document> doc1000) {
		doc1000 = insertionSort(doc1000, 3);
		for(int i = 1000; i < doclist3.size(); i++){
			for(int j = 0; j < 1000; j++){
				if(doc1000.get(j).getLaplaceScore() < doclist3.get(i).getLaplaceScore()){
					doc1000.add(j, doclist3.get(i));		
					break;
				}
			}
		}
		return doc1000;
	}
	
	public static ArrayList<Document> sort4(ArrayList<Document> doc1000) {
		doc1000 = insertionSort(doc1000, 4);
		for(int i = 1000; i < doclist4.size(); i++){
			for(int j = 0; j < 1000; j++){
				if(doc1000.get(j).getJelinekScore() < doclist4.get(i).getJelinekScore()){
					doc1000.add(j, doclist4.get(i));		
					break;
				}
			}
		}
		return doc1000;
	}
	public static ArrayList<Document> sort5(ArrayList<Document> doc1000) {
		doc1000 = insertionSort(doc1000, 5);
		for(int i = 1000; i < doclist5.size(); i++){
			for(int j = 0; j < 1000; j++){
				if(doc1000.get(j).getBm25Score() < doclist5.get(i).getBm25Score()){
					doc1000.add(j, doclist5.get(i));		
					break;
				}
			}
		}
		return doc1000;
	}
	
	public static ArrayList<Document> insertionSort(ArrayList<Document> doc1000, int key) {
		int n = doc1000.size();
		//insertion sort
		for (int i = 1; i < n; i++){
			int j = i;
			Document B = doc1000.get(i);
			switch (key) {
			case 1:
				while ((j > 0) && (doc1000.get(j-1).getOkapitfScore() > B.getOkapitfScore())){
					doc1000.add(j, doc1000.get(j-1));
					doc1000.remove(j+1);
					j--;
				}
				break;
			case 2:
				while ((j > 0) && (doc1000.get(j-1).getOkapitfIdfScore() > B.getOkapitfIdfScore())){
					doc1000.add(j, doc1000.get(j-1));
					doc1000.remove(j+1);
					j--;
				}
				break;
			case 3:
				while ((j > 0) && (doc1000.get(j-1).getLaplaceScore() > B.getLaplaceScore())){
					doc1000.add(j, doc1000.get(j-1));
					doc1000.remove(j+1);
					j--;
				}
				break;
			case 4:
				while ((j > 0) && (doc1000.get(j-1).getJelinekScore() > B.getJelinekScore())){
					doc1000.add(j, doc1000.get(j-1));
					doc1000.remove(j+1);
					j--;
				}
				break;
			case 5:
				while ((j > 0) && (doc1000.get(j-1).getBm25Score() > B.getBm25Score())){
					doc1000.add(j, doc1000.get(j-1));
					doc1000.remove(j+1);
					j--;
				}
				break;
			}
			
			doc1000.add(j, B);
			doc1000.remove(j+1);
		}
		ArrayList<Document> tempList = new ArrayList<Document>();
		int j = n - 1;
		while(j >= 0){
			tempList.add(doc1000.get(j));
			j--;
		}
		doc1000 = tempList;
		System.out.println("sort"+key+" finished");
		return doc1000;
	}
	
	
	
}
