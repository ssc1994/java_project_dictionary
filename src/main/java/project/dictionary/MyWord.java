package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import project.dictionary.UserMain;

public class MyWord {

	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호


	public static void main(String[] args) {

		EnglishTest en = new EnglishTest();
		
		String sql = "select * from members";
		
		Connection con = null;			//con 멤버변수
		PreparedStatement pstmt = null;	//pstmt 멤버변수
		ResultSet result = null;		//result 멤버변수
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, uid, upw);
	    	  
			pstmt = con.prepareStatement(sql);
			
			
			result = pstmt.executeQuery();
			
			while(result.next()) {
				String M_id = result.getString("M_id");
				
				System.out.println("회원 : " + M_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
			try {
				con.close();
				pstmt.close();
				result.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("close 에러");
			}
		}
		
		
		
	}
}
