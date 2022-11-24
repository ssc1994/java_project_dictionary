package project.swing;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.crypto.Data;

import project.dictionary.MainClass;
import project.dictionary.MemberVO;
import project.dictionary.WordDAO;
import project.dictionary.WordVO;

public class InsertGUI {
	private static String url = MainClass.url;	//주소
	private static String uid = MainClass.uid;	//계정
	private static String upw = MainClass.upw;	//비밀번호


	static Connection con = null;			//con 멤버변수
	static PreparedStatement pstmt = null;	//pstmt 멤버변수
	static ResultSet result = null;			//result 멤버변수
	

	JFrame jframe = new JFrame();
	JPanel jpanel = new JPanel();
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextArea ta = new JTextArea();
	JButton btn1, btn2, btn3, btn4, btn5;
	JLabel jl1 = new JLabel("단어  : ");
	JLabel jl2 = new JLabel("뜻  : ");
	JLabel jl3 = new JLabel("난이도  : ");
	JLabel jl4 = new JLabel("작성자  : ");

	public InsertGUI() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection(url, uid, upw);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insert();
	}

	public void insert() {
		
		
		// GUI 틀 만들기
		jframe.setSize(600,600);
		jframe.setLocationRelativeTo(null);

		//			jframe.setBounds(50, 50, 500, 330); // 전체 창 크기
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 버튼 누르면 꺼지게 설정
		jframe.setVisible(true);

		jpanel.setLayout(null);
		jframe.add(jpanel);

		// 입력 공간
		tf1.setBounds(80, 25, 70, 25);
		jpanel.add(tf1); // 이름 입력 공간
		tf2.setBounds(200, 25, 70, 25);
		jpanel.add(tf2); // 나이 입력 공간
		tf3.setBounds(350, 25, 70, 25);
		jpanel.add(tf3); // 이름 입력 공간
		tf4.setBounds(490, 25, 70, 25);
		jpanel.add(tf4); // 나이 입력 공간

		jl1.setBounds(37, 21, 70, 30);
		jpanel.add(jl1);  // 이름 라벨
		jl2.setBounds(170, 21, 70, 30);
		jpanel.add(jl2);  // 나이 라벵
		jl3.setBounds(290, 21, 70, 30);
		jpanel.add(jl3);  // 이름 라벨
		jl4.setBounds(436, 21, 70, 30);
		jpanel.add(jl4);  // 나이 라벵



		// 입력 버튼 - create
		jpanel.add(btn1=new JButton("입력"));
		btn1.setBounds(20, 70, 100, 30);

		// 출력 버튼 - read
		jpanel.add(btn2=new JButton("출력"));
		btn2.setBounds(130, 70, 100, 30);

		// 수정 버튼 - update
		jpanel.add(btn3=new JButton("수정"));
		btn3.setBounds(240, 70, 100, 30);

		// 삭제 버튼 - delete
		jpanel.add(btn4=new JButton("삭제"));
		btn4.setBounds(350, 70, 100, 30);

		// 종료 버튼
		jpanel.add(btn5=new JButton("종료"));
		btn5.setBounds(460, 70, 100, 30);

		// 입력한 글이 보이는 창
		JScrollPane jsp = new JScrollPane(ta); // 창 스크롤
		jsp.setBounds(20, 120, 550, 400);
		jpanel.add(jsp);

					WordDAO dao = new WordDAO();
		//
					// 입력 버튼 이벤트
					btn1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							ta.setText("");
							
							String word = tf1.getText();
							String mean = tf2.getText();
							int level = Integer.parseInt(tf3.getText());
							String writer = tf4.getText();
							
							dao.insertWord(MainClass.result);
							
							ta.append("입력 완료 \n");				
							
							tf1.setText(""); tf2.setText(""); tf3.setText(""); tf4.setText("");
						}
					});
		
					// 출력 버튼 이벤트
//					btn2.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent arg0) {
//							ta.setText("");
//							String sql = "select * from Word";
//							ArrayList<WordVO> list = new ArrayList<>();
//							pstmt = con.prepareStatement(sql);
//							result = pstmt.executeQuery();
//							
//							
//							ta.append("name"+"\t"+"age"+"\n");
//							ta.append("--------------------------\n");
//							
//							// 전체 출력
//							while(result.next()) { //다음 row가 있다면 true
//
//								//한 행에 대한 처리( getInt, getString, getDouble, getTimestamp, getDate )
//								int importance = result.getInt("W_LEVEL");
//								String word = result.getString("W_WORD"); //컬럼명
//								String mean = result.getString("W_MEAN");
//								String writer = result.getString("W_Writer");
//
//								//한번 회전할 때마다 하나씩 생성
//								//vo에 행 데이터 저장, vo를 리스트에 add를 이용해서 저장
//								WordVO vo = new WordVO(importance, word, mean, writer);
//								list.add(vo);
//
//								System.out.println("난이도 : " + importance + ", 단어 : " + word + ", 뜻 : " + mean + ", 작성자 :" + writer);
//							}
//						}
//					});
		//
		//			// 수정 버튼 이벤트
		//			btn3.addActionListener(new ActionListener() {
		//				@Override
		//				public void actionPerformed(ActionEvent arg0) {
		//					ta.setText(""); 
		//					
		//					String name = tf1.getText();
		//					int age = Integer.parseInt(tf2.getText());
		//
		//					dao.updateData(new Data(name, age));
		//					ta.append("수정 완료 \n");
		//					
		//					tf1.setText(""); tf2.setText("");
		//				}
		//			});
		//
		//			// 삭제 버튼 이벤트
		//			btn4.addActionListener(new ActionListener() {
		//				@Override
		//				public void actionPerformed(ActionEvent arg0) {
		//					ta.setText("");
		//					
		//					String name = tf1.getText();
		//					dao.deleteData(name);
		//					ta.append("삭제 완료 \n");
		//		
		//					tf1.setText(""); tf2.setText("");
		//				}
		//			});

		// 종료 버튼 이벤트
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

}
