package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			create = "Create Table Word  (W_Word varchar2(32)  primary key,\r\n"
					+ "                   W_Mean varchar2(200) not null,  \r\n"
					+ "                   W_Level number(2) , \r\n"
					+ "                   W_Writer varchar2(20))";
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
		String update;
		try {

			select = "SELECT * FROM WORD where W_Word = ?";

			pstmt=con.prepareStatement(select);
			pstmt.setString(1, word);
			result = pstmt.executeQuery();
			
			if (result.next() == false) {
				insert = "Insert Into Word Values(?, ?, ?,'작성자' )";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, word);
				pstmt.setString(2, mean);
				pstmt.setInt(3, level);
				pstmt.executeUpdate();
				System.out.println("입력완료");
				
			} else {
				update = "Update Word Set  W_mean = ?, W_Level = ? WHERE W_Word = ?";
				pstmt = con.prepareStatement(update);
				pstmt.setString(3, word);
				pstmt.setString(1, mean);
				pstmt.setInt(2, level);
				System.out.println("수정완료");
				
				
			}
			
			
			
			
			
//			insert = "Insert Into Word Values(?, ?, ?,'작성자' )";
//			pstmt = con.prepareStatement(insert);
//			pstmt.setString(1, word);
//			pstmt.setString(2, mean);
//			pstmt.setInt(3, level);
//			pstmt.executeUpdate();
//			System.out.println("입력");
				
				
				



		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

	}





}
