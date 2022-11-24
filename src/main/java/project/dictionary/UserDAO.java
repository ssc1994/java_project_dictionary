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
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet result = null;
	
	String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe"; //주소
	String uid = "com02"; //계정
	String upw = "com02";
	
	public UserDAO() {
	}
	
	public int login(String memberID, String memberPassword) {
		String sql = "select pw from members where m_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, uid, upw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			result = pstmt.executeQuery();
			while(result.next()) {
				if(result.getString(1).contentEquals(memberPassword)) {
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
				con.close();
				pstmt.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -2; //DB 오류
	}
	
	public MemberVO loginMember(String memberID, String memberPassword) {
		MemberVO currMember = new MemberVO();
		try {
			UserDAO dao = new UserDAO();
			if(dao.login(memberID, memberPassword) == 1) {
				currMember.setName(memberID);
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currMember;
	}
	
//	public MemberVO loginMember() {
//		MemberVO currMember = new MemberVO();
//		Scanner sc = new Scanner(System.in);
//		
//		try {
//			UserDAO dao = new UserDAO();
//			System.out.print(">");
//			String memberID = sc.next();
//			System.out.print(">");
//			String memberPassword = sc.next();
//			if(dao.login(memberID, memberPassword) == 1) {
//				currMember.setName(memberID);
//				System.out.println("로그인 성공");
//			} else {
//				System.out.println("실패");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return currMember;
//	}
	
}
