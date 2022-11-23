package project.dictionary;

public class MainClass {

	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";

	public static void main(String[] args) {
		WordDAO wordDAO = new WordDAO();
		wordDAO.getConnection();
//		wordDAO.createTable();
		
//		try {
//			wordDAO.insertWord(1, "안녕하세요", "인삿말",3,"작성자");
//			System.out.println("단어 삽입 완료");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		
//		wordDAO.showTable();
		
//		wordDAO.DeleteWord();
		
	
		wordDAO.selectEx();
	}
}
