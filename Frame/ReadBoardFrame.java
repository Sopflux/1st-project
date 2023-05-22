package movie_sketch;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import dao.BoardDAO;
import dao.LoginAndRegisterDAO;

public class ReadBoardFrame extends JFrame {
	JLabel jlb_title;
	JTextField jtf_comments;
	JTextArea jta_contents;
	JButton btn_list;
	JButton btn_update;
	
	JList<String> listbox;
	Vector<String> rowdata;
	LoginAndRegisterDAO dao;
	BoardDAO bdao;
	
	
	public ReadBoardFrame(int b_no, int a_no) {
		
		Color co = new Color(57,72,103);
		
		bdao = new BoardDAO();
		dao = new LoginAndRegisterDAO();
		
		ImageIcon icon10 = new ImageIcon("clipboard_w.png");
		Image image10 = icon10.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon clipboard = new ImageIcon(image10);
		btn_list = new JButton(clipboard);
		btn_list.setBorder(null);
		btn_update = new JButton("등록");
		btn_update.setBorder(null);
		jlb_title = new JLabel();
		jtf_comments = new JTextField(20);
		jta_contents = new JTextArea();
		rowdata = new Vector<String>();
		listbox = new JList<String>(rowdata);
		
		getcontent(b_no);
		
		JPanel p_n1 = new JPanel(new BorderLayout());
		JPanel p_n2 = new JPanel(new BorderLayout());
		JPanel p_center = new JPanel(new GridLayout(1,1));
		JPanel p_s1 = new JPanel();
		JPanel p_s2 = new JPanel(new GridLayout(1,1));
		JPanel p_n = new JPanel(new GridLayout(2,1));
		JPanel p_s = new JPanel(new BorderLayout());
		
		p_n1.setBackground(co);
		p_n2.setBackground(co);
		p_s1.setBackground(co);
		
		
		Font f2 = new Font("SansSerif", Font.BOLD,20);
		
		jlb_title.setFont(f2);
		btn_update.setFont(f2);
		btn_update.setForeground(Color.white);
		jtf_comments.setFont(f2);
		
		p_n1.add(btn_list, BorderLayout.WEST);
		p_n2.add(jlb_title);
		p_n.add(p_n1);
		p_n.add(p_n2);
		
		
		jlb_title.setForeground(Color.white);
		
		loadata(a_no, b_no);
		
		
		JScrollPane jsp = new JScrollPane(jta_contents);
		p_center.add(jsp);
		
		JScrollPane jsp2 = new JScrollPane(listbox);
		jsp2.getViewport().add(listbox);
		
		p_s1.add(jtf_comments);
		p_s1.add(btn_update);
		p_s2.add(jsp2);
		p_s.add(p_s1, BorderLayout.NORTH);
		p_s.add(p_s2, BorderLayout.CENTER);
		
		add(p_n, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		add(p_s, BorderLayout.SOUTH);
		
		setSize(500,700);
		setVisible(true);
		
		
		btn_list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BoardFrame boardf = new BoardFrame();
				boardf.setVisible(true);
				setVisible(false);
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
			      rowdata.clear();
			      String com = jtf_comments.getText();
			      int re = bdao.insertcomment(a_no, b_no, com);
			      if(re>0) {
			         ArrayList<HashMap<String, String>> list = bdao.getComment(b_no);
			         for(HashMap<String, String> map:list) {
			            String nickname = map.get("닉네임");
			            String con = map.get("내용");
			            String total = nickname+":"+con;
			            rowdata.add(total);
			         }
			         listbox.setListData(rowdata);
			         listbox.updateUI();
			      }
			   }
			});
	}
	public void loadata(int a_no, int b_no) {
		rowdata.clear();
		ArrayList<HashMap<String, String>> list = bdao.getComment(b_no);
		for(HashMap<String, String> map:list) {
			String nickname = map.get("닉네임");
			String con = map.get("내용");
			String total = nickname+":"+con;
			rowdata.add(total);
		}
		listbox.setListData(rowdata);
		listbox.updateUI();
		listbox.repaint();
	}
	 public void getcontent(int b_no) {
	      ArrayList<HashMap<String, String>> list = bdao.getBoard(b_no);
	      for(HashMap<String, String>map:list) {
	         jlb_title.setText(map.get("제목"));
	         jta_contents.setText(map.get("내용"));
	      }
	   }

	public static void main(String[] args) {
	}
}