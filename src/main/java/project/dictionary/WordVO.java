package project.dictionary;

public class WordVO {

	//String word, String mean, int importance
	private int importance;
	private String word;
	private String mean;
	private String writer;
	
	public WordVO() {
		// TODO Auto-generated constructor stub
	}

	public WordVO(int importance, String word, String mean, String writer) {
		super();
		this.importance = importance;
		this.word = word;
		this.mean = mean;
		this.writer = writer;
	}

	
	

	@Override
	public String toString() {
		return "오답 > [레벨 : " + importance + ", 단어 : " + word + ", 뜻 :" + mean + ", 작성자 : " + writer + "]";
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	
}
