package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestMain {

	public static String uid = "com02";
	public static String upw = "com02";		//아이디
	public static String url = "jdbc:oracle:thin:@172.30.1.32:1521:xe";

	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		EnglishTest et = new EnglishTest();
		et.getData();
	}

}
