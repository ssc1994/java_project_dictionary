package project.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import gui.StudyFrame;


public class Table{

	public static void main(String[] args) {
		
		Dimension dim = new Dimension(600,600);
		
		JFrame frame = new JFrame("영어단어 전체목록");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String header[] = {"레벨","단어","뜻","작성자"};
		
		String contents[][] = {
				{"1","word","세계","park"}
				
		};
		
		DefaultTableModel model = new DefaultTableModel(contents, header);
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		JTextField nameField = new JTextField(5);
		JTextField sbj1 = new JTextField(3);
		JTextField sbj2 = new JTextField(3);
		JTextField sbj3 = new JTextField(3);
		
		panel.add(nameField);
		panel.add(sbj1);
		panel.add(sbj2);
		panel.add(sbj3);
		
		JButton addBtn = new JButton("추가");
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputStr[] = new String[4];
				
				inputStr[0] = nameField.getText();
				inputStr[1] = sbj1.getText();
				inputStr[2] = sbj2.getText();
				inputStr[3] = sbj3.getText();
				
				model.addRow(inputStr);
				
				nameField.setText("");
				sbj1.setText("");
				sbj2.setText("");
				sbj3.setText("");
				
			}
		});// addActionListener
		
		
		JButton cancelBtn = new JButton("삭제");
		
		 cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() == -1) {
					return;
				}
				else {
					model.removeRow(table.getSelectedRow());
				}
				
			}
		});
		 
		 panel.add(addBtn);
		 panel.add(cancelBtn);
		 
		 frame.add(scrollpane, BorderLayout.CENTER);
		 frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		 frame.setVisible(true);
		 
		
		
		
	}
}//main
	
