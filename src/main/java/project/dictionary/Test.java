package project.dictionary;
import javax.swing.*;


import java.util.*;
import java.awt.event.*;
import java.awt.*;
class Word{
    private String english; // 영어 단어
    private String korean; // 영어 단어에 해당하는 한글 단어
    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }
    public String getEnglish() { return english; }
    public String getKorean() { return korean; }
}
 
public class Test extends JFrame{
    int example[];
    int answer1;
    int answerIndex;
    int cnt = 0;
    
    
    String eng;
    
    Vector<Word> v;
    JButton[] answer;
    
    
    
    JLabel Q1;
    JLabel CNT;
    
    
    //여기에 테이블에 있는 데이터와 조인하기.
    public Test() {
        Font f = new Font("맑은 고딕",Font.PLAIN,30);
        
        v = new Vector<Word>();
        TestMain t = new TestMain(); 
                
        setTitle("영어 단어 공부!");
        createMenu();
        
        
        Container c = getContentPane();
        c.setLayout(null);
        
        makeexam();
        makebutton();
        for(int i = 0; i < 4; i++)
            answer[i].addActionListener(new myactionlistener());
        
        answer[0].setLocation(20,80);
        answer[0].setSize(80,80);
        answer[1].setLocation(120,80);
        answer[1].setSize(80,80);
        answer[2].setLocation(220,80);
        answer[2].setSize(80,80);
        answer[3].setLocation(320,80);
        answer[3].setSize(80,80);
        
        JButton next = new JButton("next");
        next.setLocation(50,200);
        next.setSize(100,20);
        next.addActionListener(new myactionlistener());
        c.add(next);
        
        JButton Exit = new JButton("종료");
        Exit.setLocation(250,200);
        Exit.setSize(100,20);
        Exit.addActionListener(new myactionlistener());
        c.add(Exit);
        
        
        this.Q1 = new JLabel(eng);
        Q1.setLocation(150,10);
        Q1.setSize(200,50);
        Q1.setFont(f);
        c.add(Q1);
        
        this.CNT = new JLabel("맞은 횟수");
        CNT.setLocation(225,300);
        CNT.setSize(200,20);
        c.add(CNT);
        
        for(int i = 0; i < 4; i++)
            c.add(answer[i]);
        
        
        
        setSize(450,400);
        setVisible(true);
            
        
        
        
        
        
    }
    private int makeExample(int ex[], int answerIndex) {
        int n[] = {-1, -1, -1, -1}; // 
        int index;
        for(int i=0; i<n.length; i++) {
            do {
                index = (int)(Math.random()*v.size());
            } while(index == answerIndex || exists(n, index)); 
            n[i] = index;
        }
 
        for(int i=0; i<n.length; i++) ex[i] = n[i];
        return (int)(Math.random()*n.length); 
    }
    private boolean exists(int n[], int index) {
        for(int i=0; i<n.length; i++) {
            if(n[i] == index)
                return true;
        }
        return false;
    }
    private void makeexam() {
        this.answerIndex = (int)(Math.random()*v.size()); 
        this.eng = v.get(answerIndex).getEnglish();
        this.example = new int[4];
        this.answer1 = makeExample(example,answerIndex);
        example[answer1] = answerIndex;    
    }
    
    private void makebutton() {
        this.answer= new JButton[4];
        answer[0] = new JButton(v.get(example[0]).getKorean());
        answer[1] = new JButton(v.get(example[1]).getKorean());
        answer[2] = new JButton(v.get(example[2]).getKorean());
        answer[3] = new JButton(v.get(example[3]).getKorean());
        
    }
    
    private void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu screenMenu = new JMenu("만든분");
        screenMenu.addActionListener(new myactionlistener());
        mb.add(screenMenu);
        setJMenuBar(mb);
        
        
    }
    
    class myactionlistener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            
            JButton b = (JButton)e.getSource();
            for(int i = 0; i<4; i++) {
            if (b.getText().equals(v.get(example[answer1]).getKorean())) {
            b.setText("정답입니다.");
            cnt++;
            }
            }
            
            JButton N = (JButton)e.getSource();
            if(N.getText().equals("next")) {
                makeexam();
                Q1.setText(eng);
                for(int j = 0; j<4; j++) {
                answer[j].setText(v.get(example[j]).getKorean());}
                CNT.setText("맞은 횟수 "+Integer.toString(cnt));
                }
            
            JButton X = (JButton)e.getSource();
            if(X.getText().equals("종료"))
                CNT.setText(Integer.toString(cnt)+"번 시행하셨습니다. 수고하셨습니다.");
            
            String cmd = e.getActionCommand();
            if (cmd.equals("만든분"))
                CNT.setText("서성우");
            }
        }
        
        
    
    
    
    
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
         new Test();
        
    }
 
}
