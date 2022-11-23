package project.dictionary;

public class WordVO {

	//String word, String mean, int importance
	private int importance;
	private String word;
	private String mean;
	
	public WordVO() {
		// TODO Auto-generated constructor stub
	}

	public WordVO(int importance, String word, String mean) {
		super();
		this.importance = importance;
		this.word = word;
		this.mean = mean;
	}

	@Override
	public String toString() {
		return "WordVO [importance=" + importance + ", word=" + word + ", mean=" + mean + "]";
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}
	
	
	
}