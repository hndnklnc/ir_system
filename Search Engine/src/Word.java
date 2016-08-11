
public class Word {
	private int df;
	private int ctf;
	private int tf;
	private String name;
	private int qtf;

	private double okapitf; //first retrieval system
	private double okapitfIdf; //second retrieval system
	
	public int getTf() {
		return tf;
	}
	public void setTf(int tf) {
		this.tf = tf;
	}

	public double getOkapitf() {
		return okapitf;
	}
	public double getOkapitfIdf() {
		return okapitfIdf;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	public int getCtf() {
		return ctf;
	}
	public void setCtf(int ctf) {
		this.ctf = ctf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void calculateModels(int doclength, double avglength){
		double idf = 0;
		if(df != 0)
			idf = Math.log(Corpus.NUM_DOCS / df);
 		okapitf = (double)tf / ((double)tf + 0.5 + 1.5 * ((double)doclength / avglength));
		okapitfIdf = okapitf * idf;
	
	}

}
