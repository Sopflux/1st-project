package movie_sketch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MypageMain extends JFrame{
    private DefaultListModel<String> resultListModel;
    private JList<String> resultList;
    private JButton prevButton;
    private JButton nextButton;
	public MypageMain() {
		setTitle("My Page");
		
		
		JPanel p_north = new JPanel();
		JPanel p_center = new JPanel();
		JPanel p_info = new JPanel();
		JButton btn_update = new JButton("프로필 수정");
		JLabel nickname = new JLabel("기부앤테크");
		JLabel title = new JLabel("MY PAGE");
		
		p_north.add(title);
		p_info.add(nickname);
		p_info.add(btn_update);
		p_center.add(p_info);
		
		
		
		
		
		add(p_north,BorderLayout.NORTH);
		add(p_center,BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new MypageMain();
	}

}
