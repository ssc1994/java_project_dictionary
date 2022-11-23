package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WordDAO {
	
	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호
	

	
	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수
	
	public static Connection getConnection() {

		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection
			con = DriverManager.getConnection(url, upw, uid);

		} catch (Exception e) {
			e.getStackTrace();
		} 
		return con;
	}

	public static void createTable() {

		Connection con = getConnection();
		String create;

		try {
			create = "Create Table Word  (W_No NUMBER(5) primary key,\r\n"
					+ "                W_Word varchar2(32)  not null,\r\n"
					+ "                W_Mean varchar2(200) not null,  \r\n"
					+ "                W_Level number(2) , \r\n"
					+ "                W_Writer varchar2(20))";
			pstmt = con.prepareStatement(create);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void insertWord(String word, String mean, int level) {
			Connection con = getConnection();
			String insert;
			String select;
			try {
				insert = "Insert Into Word Values(word_no_seq,"
						        + "'"+ word +"',"
								+ " '"+ mean +"',"
								+ "  "+ level +" ,"
								+ " '작성자' )";
				
				pstmt = con.prepareStatement(insert);
//				if(result)
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	
	public static void getData(String word) {//검색기능
		Connection con = getConnection();

		String select = "SELECT * FROM Word where W_WORD = '" +
		word + "'";

		try {
			pstmt = con.prepareStatement(select);
			result = pstmt.executeQuery();

			while(result.next()){ // 컬럼 이름
				String W_Word = result.getString("W_Word");
				String W_Mean = result.getString("W_Mean");
				int W_Level = result.getInt("W_Level");
				String W_Writer = result.getString("W_Writer");
				//단어의 정보 출력
				System.out.println("단어의 정보\n단어 : " + W_Word
						+ ", 뜻 : " + W_Mean + ", 난이도 : " + W_Level + ", 작성자 : " + W_Writer);
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
