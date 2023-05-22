package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.BoardDAO;
import vo.BoardVO;


public class InsertBoardFrame extends JFrame {
	
	JTextField jtf_title;
	JTextArea jta_content;
	JButton jb_insert;
	JButton jb_back;
	int a_no = LoginFrame.a_no;
	InsertBoardFrame inbf;
	
	public InsertBoardFrame() {
		
		Color co = new Color(57,72,103	);
		
		inbf = this;
		jtf_title = new JTextField(25);
		jta_content = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta_content);
		jb_insert = new JButton("등록");
		jb_back = new JButton("목록");
		
		
		JLabel la = new JLabel("제목");
		JPanel p_t = new JPanel();
		p_t.add(la);
		
		Font f = new Font("SansSerif", Font.BOLD,15);
		la.setFont(f);
		
		p_t.add(jtf_title);
		
		la.setForeground(Color.white)
		;
		
		p_t.setBackground(co);
		
		
		JPanel b = new JPanel();
		b.add(jb_back);
		b.add(new JLabel("                                                  "));
		b.add(jb_insert);
		b.setBackground(co);
		
		setLayout(new BorderLayout());
		add(p_t, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER); 
		add(b, BorderLayout.SOUTH);
		
		jb_insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = jtf_title.getText();
				String contents = jta_content.getText();
				BoardVO a = new BoardVO(0,104,title,contents,"");
				BoardDAO dao = new BoardDAO();
				if(title.length()==0) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요.");
					return;
				}
				
				int choice = JOptionPane.showConfirmDialog(null, "게시물을 등록하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
				if(choice==0) {
					int r = dao.insertBoard(a);
					if(r==1) { 
						JOptionPane.showMessageDialog(null, "글이 등록되었습니다.");
						
						setVisible(false);
						BoardFrame bf = new BoardFrame();
						bf.setVisible(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "등록실패.");
					}
				}
			}
		});
		
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BoardFrame bf = new BoardFrame();
				bf.setVisible(true);
			}
		});
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}