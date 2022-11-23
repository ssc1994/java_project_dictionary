package project.dictionary;

public class MainClass {
	
	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";

	public static void main(String[] args) {
		WordDAO wordDAO = new WordDAO();
		wordDAO.getConnection();
		wordDAO.createTable();
		
		
	}
}
