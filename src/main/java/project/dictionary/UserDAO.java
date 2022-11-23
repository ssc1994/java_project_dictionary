package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO { //현재 로그인 멤버 구현
	
	private Connection conn1 = null;
	private PreparedStatement pstmt1 = null;
	private ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe"; //주소
	String uid = "com02"; //계정
	String upw = "com02";
	
	public UserDAO() {
	}
	
	public int login(String memberID, String memberPassword) {
		String sql = "select pw from members where m_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn1 = DriverManager.getConnection(url, uid, upw);
			pstmt1 = conn1.prepareStatement(sql);
			pstmt1.setString(1, memberID);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).contentEquals(memberPassword)) {
					return 1; //비밀번호 일치, 로그인 성공
				} else {
					return 0; //비밀번호 불일치
				}
			}
			return -1; //아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn1.close();
				pstmt1.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -2; //DB 오류
	}
	
	public MemberVO loginMember() {
		MemberVO currMember = new MemberVO();
		try {
			Scanner sc = new Scanner(System.in);
			UserDAO dao = new UserDAO();
			String memberID = sc.next();
			String memberPassword = sc.next();
			if(dao.login(memberID, memberPassword) == 1) {
				currMember.setName(memberID);
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currMember;
	}
	
}
