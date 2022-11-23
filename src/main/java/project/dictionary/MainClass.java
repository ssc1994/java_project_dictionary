package project.dictionary;

public class MainClass {

	public static void main(String[] args) {
	
	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";

	public static void main(String[] args) {
		WordDAO wordDAO = new WordDAO();
//		wordDAO.getConnection();
//		wordDAO.createTable();				//테이블 생성
		wordDAO.insertWord("바보", "재입력", 2);		//row삽입
		
	}
}
