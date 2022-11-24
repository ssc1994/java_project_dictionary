package project.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.dictionary.*;
import project.dictionary.MemberDAO;
import project.dictionary.UserDAO;

public class WordDAOSwing {
	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호

	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수

	String USER  = "작성자";
	String W_Word;
	String W_Field;
	int W_Level;
	String W_Writer;

	//			단어 등록/수정 |  label
	//		   추가할 단어명(textfield)
	//		   추가할 단어의 뜻(textfield)
	//		   추가할 단어의 난이도(textfield)
	//		   작성자 -(로그인한 아이디)
	public static void insert() { 
		con = getConnection();
		JFrame insFrame = new JFrame();
		insFrame.setTitle("단어 등록/수정");
		insFrame.setSize(600,600);
		insFrame.setLocationRelativeTo(null);
		insFrame.setLayout(new FlowLayout());
		insFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel row1 = new JPanel();
		row1.setPreferredSize(new Dimension(600,100));
		JPanel row2 = new JPanel();
		row2.setPreferredSize(new Dimension(600,100));
		JPanel row3 = new JPanel();
		row3.setPreferredSize(new Dimension(600,100));
		JPanel row4 = new JPanel();
		row4.setPreferredSize(new Dimension(600,100));
		JPanel row5 = new JPanel();
		row5.setPreferredSize(new Dimension(600,100));
		JPanel row6 = new JPanel();
		row6.setPreferredSize(new Dimension(600,100));

		JLabel row1l1 = new JLabel("단어 등록/수정");
		JLabel row2l1 = new JLabel("단어");
		JLabel row3l1 = new JLabel("뜻");
		JLabel row4l1 = new JLabel("난이도");
		JLabel row5l1 = new JLabel("작성자");

		row1.add(row1l1);
		row2.add(row2l1);
		row3.add(row3l1);
		row4.add(row4l1);
		row5.add(row5l1);

		insFrame.add(row1);
		insFrame.add(row2);
		insFrame.add(row3);
		insFrame.add(row4);
		insFrame.add(row5);
		insFrame.add(row6);


		insFrame.setVisible(true);



	}

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, upw, uid);

		} catch (Exception e) {
			e.getStackTrace();
		} 
		return con;
	}

	public static void main(String[] args) {
		InsertGUI swing = new InsertGUI();
		swing.insert();
	}


}


