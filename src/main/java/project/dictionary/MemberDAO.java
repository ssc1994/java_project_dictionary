package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDAO {
	
	private static String url = MemberMain.url;
	private static String uid = MemberMain.uid;
	private static String upw = MemberMain.upw;
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	
	//연결
	public static Connection getConnection() {
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection
			conn = DriverManager.getConnection(url, upw, uid);

		} catch (Exception e) {
			e.getStackTrace();
		} 
		return conn;
	}
	
	//테이블 생성
	public static void createMemberTable() {
		Connection conn = getConnection();
		String cr;
		
		try {
			cr = "create table members\r\n"
					+ "\r\n"
					+ "(m_id varchar2(15) primary key,\r\n"
					+ "\r\n"
					+ "pw varchar2(15) not null,\r\n"
					+ "\r\n"
					+ "m_name varchar2(20) not null,\r\n"
					+ "\r\n"
					+ "birth varchar2(10),\r\n"
					+ "\r\n"
					+ "phone varchar2(13))";
			pstmt = conn.prepareStatement(cr);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원정보 입력
	public int InsertData(MemberVO vo) {
		int result = 0;
		
		MemberMain.url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";
		MemberMain.uid = "com02";
		MemberMain.upw = "com02";
		
		//m_id, pw, m_name, birth, phone
		String sql = "insert into members values(?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
