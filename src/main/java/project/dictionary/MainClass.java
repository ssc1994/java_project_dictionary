package project.dictionary;

public class MainClass {
	public static void main(String[] args) {
		
		ddb d = new ddb();
		d.getConnection();
		d.createTables();
		
	}
	
}
