package project.dictionary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestLoginScreen extends JFrame {

	public TestLoginScreen() {

		setTitle("로그인");

		JPanel title = new JPanel();

		// title 컨테이너에 들어갈 컴포넌트를 만들어 보자.
		JLabel login = new JLabel("단어장 프로그램");

		// Font font = new Font("휴먼편지체", Font.BOLD, 25);

		login.setFont(new Font("고딕체", Font.BOLD, 20));

		// 컴포넌트를 title 컨테이너에 올려 주자.
		title.add(login);


		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(3, 2));

		JPanel idPanel = 
				new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlb1 = new JLabel("아이디 : ", JLabel.CENTER);

		idPanel.add(jlb1);

		JPanel idPanel2 = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtf1 = new JTextField(10);

		idPanel2.add(jtf1);

		jp1.add(idPanel); jp1.add(idPanel2);


		JPanel pwdPanel = 
				new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlb2 = new JLabel("비밀번호 : ", JLabel.CENTER);

		JPanel pwdPanel2 = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtf2 = new JPasswordField(10);

		pwdPanel.add(jlb2); pwdPanel2.add(jtf2);

		jp1.add(pwdPanel); jp1.add(pwdPanel2);


		JPanel loginPanel = 
				new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jLogin = new JButton("로그인");

		JPanel joinPanel = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton join = new JButton("회원가입");

		loginPanel.add(jLogin); joinPanel.add(join);

		jp1.add(loginPanel); jp1.add(joinPanel);


		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(jp1);

		setLayout(new BorderLayout());

		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);

		setBounds(200, 200, 300, 250);

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		setVisible(true);
		MemberDAO dao = new MemberDAO();
		UserDAO udao = new UserDAO();
		dao.getConnection();

		jLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if(udao.login(jtf1.getText(), jtf2.getText()) == 1) {
						MemberVO currLogin = new MemberVO();
						String curr = udao.loginMember
						(idPanel2.getName(), pwdPanel2.getName()).getName();
						currLogin.setName(curr);
						JOptionPane.showMessageDialog
						(null, "로그인이 완료되었습니다");
						new TestMenu();
						dispose();
					} else {
						JOptionPane.showMessageDialog
						(null, "아이디 또는 패스워드를 확인해주세요");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog
					(null, "아이디 또는 패스워드를 확인해주세요");
				}
			}
		});

		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new TestMemberSwing();
				dispose();  // 현재의 frame을 종료시키는 메서드.
			}
		});
	}
}