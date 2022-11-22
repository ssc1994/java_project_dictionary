package project.dictionary;

public class ins {
	
	
	public static void main(String[] args) {
		ddb data = new ddb();
		
		data.insertWord("name", "이름", 3);
		
		data.showTable();
	}
}
