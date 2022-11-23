package project.dictionary;

public class UserMain {
	
	
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";
	public static String uid = "com02";
	public static String upw = "com02";
	
	
	public static void main(String[] args) {
		
		UserDAO udao = new UserDAO();
		
		WordDAO wdao = new WordDAO();
		
//		String result = udao.loginMember().getName();
//		wdao.insertWord("tl", "qkf", 99, result);
		
		
	}
}
