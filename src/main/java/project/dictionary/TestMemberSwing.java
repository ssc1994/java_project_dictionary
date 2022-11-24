package project.dictionary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestMemberSwing extends JFrame {
	Image img = null;
	public TestMemberSwing() {
		JPanel p = new JPanel();
		Label idLabel = new Label("ID");
		Label pwLabel = new Label("PW");
		Label nameLabel = new Label("NAME");
		Label birthLabel = new Label("BIRTH");
		Label phoneLabel = new Label("PHONE");
		add(idLabel);
		add(pwLabel);
		add(nameLabel);
		add(birthLabel);
		add(phoneLabel);
		TextField idField = new TextField();
		TextField pwField = new TextField();
		TextField nameField = new TextField();
		TextField birthField = new TextField();
		TextField phoneField = new TextField();
		add(idField);
		add(pwField);
		add(nameField);
		add(birthField);
		add(phoneField);
		pwField.setEchoChar('*');
		JButton checkButton1 = new JButton("확인");
		JButton checkButton2 = new JButton("확인");
		JButton checkButton3 = new JButton("확인");
		JButton checkButton4 = new JButton("확인");
		JButton checkButton5 = new JButton("확인");
		JButton joinButton = new JButton("가입");
		JButton cancelButton = new JButton("취소");
		add(checkButton1);
		add(checkButton2);
		add(checkButton3);
		add(checkButton4);
		add(checkButton5);
		add(joinButton);
		add(cancelButton);
		idLabel.setBounds(40, 10, 40, 40);
		pwLabel.setBounds(40, 50, 40, 40);
		nameLabel.setBounds(40, 90, 60, 40);
		birthLabel.setBounds(40, 130, 40, 40);
		phoneLabel.setBounds(40, 170, 60, 40);
		idField.setBounds(120, 10, 200, 30);
		pwField.setBounds(120, 50, 200, 30);
		nameField.setBounds(120, 90, 200, 30);
		birthField.setBounds(120, 130, 200, 30);
		phoneField.setBounds(120, 170, 200, 30);
		checkButton1.setBounds(350, 10, 80, 30);
		checkButton2.setBounds(350, 50, 80, 30);
		checkButton3.setBounds(350, 90, 80, 30);
		checkButton4.setBounds(350, 130, 80, 30);
		checkButton5.setBounds(350, 170, 80, 30);
		joinButton.setBounds(125, 330, 80, 30);
		cancelButton.setBounds(240, 330, 80, 30);
		add(p);

		setTitle("회원 가입");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		MemberException me = new MemberException();
		
		dao.getConnection();

		checkButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //ID 체크
				boolean checkID;
				try {
					checkID = me.idCheck(idField.getText());
					if(checkID) {
						JOptionPane.showMessageDialog(null, "확인되었습니다");
						vo.setId(idField.getText());
						idField.setEditable(false);
					}
				} catch (AuthenException e1) {
					JOptionPane.showMessageDialog(null, "ID는 영문 포함, 6~12자리를 입력하세요"
							+ "\n *특수문자 불가");
				}
			}
		});

		checkButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //비번 체크
				boolean checkPW;
				try {
					checkPW = me.pwCheck1(pwField.getText());
					if(checkPW) {
						JOptionPane.showMessageDialog(null, "확인되었습니다");
						vo.setPw(pwField.getText());
						pwField.setEditable(false);
					}
				} catch (AuthenException e1) {
					JOptionPane.showMessageDialog(null, "비밀번호는 8~12자 사이, "
							+ "영문과 숫자, 특수문자를 포함해야 합니다");
				}
			}
		});

		checkButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //이름 체크
				boolean checkName;
				try {
					checkName = me.nameCheck(nameField.getText());
					if(checkName) {
						JOptionPane.showMessageDialog(null, "확인되었습니다");
						vo.setName(nameField.getText());
					}
				} catch (AuthenException e1) {
					JOptionPane.showMessageDialog(null, "이름은 한글만 입력해주세요");
				}
			}
		});

		checkButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //생일 체크
				boolean checkBirth;
				try {
					checkBirth = me.birthCheck(birthField.getText());
					if(checkBirth) {
						JOptionPane.showMessageDialog(null, "확인되었습니다");
						vo.setBirth(birthField.getText());
					}
				} catch (AuthenException e1) {
					JOptionPane.showMessageDialog(null, "생년월일은 숫자만 입력해주세요"
							+ "\n (YYYYMMdd)");			
				}
			}
		});
		
		checkButton5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //폰번호 체크
				boolean checkPhone;
				try {
					checkPhone = me.phoneCheck(phoneField.getText());
					if(checkPhone) {
						JOptionPane.showMessageDialog(null, "확인되었습니다");
						vo.setPhone(phoneField.getText());
					}
				} catch (AuthenException e1) {
					JOptionPane.showMessageDialog(null, "하이픈(-)을 제외한 13자리 숫자를 입력해주세요");
				}
			}
		});

		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //가입 데이터 저장
				try {
					if(dao.InsertData(vo) == 1) {
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
						new TestLoginScreen();
						dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다."
							+ "\n다시 시도해주세요");
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입을 취소합니다");
				new TestLoginScreen();
				dispose();
			}
		});
	}
}
