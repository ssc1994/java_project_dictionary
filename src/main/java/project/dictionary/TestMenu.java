package project.dictionary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestMenu extends JFrame {
	Container c;
	JButton button_list[] = new JButton[6];
	String str_list[] = {"단어 등록/수정", "단어목록 조회", 
			"단어 삭제", "단어 테스트", "오답노트", "로그아웃"};
	public TestMenu() {
		setButton();
		
		setTitle("메인 메뉴");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		button_list[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다");
				new TestLoginScreen();
				dispose();
			}
		});
		
	}
	
	public void setButton() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		JLabel jl = new JLabel();
		c.add(jl);
		for(int i = 0; i < 6; i++) {
			button_list[i] = new JButton(str_list[i]);
			button_list[i].setPreferredSize(new Dimension(300,80));
			c.add(button_list[i]);
		}
	}
	
	
	
}
