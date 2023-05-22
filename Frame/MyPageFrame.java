package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import dao.LoginAndRegisterDAO;

public class MyPageFrame extends JFrame {
	Vector<Vector<String>> rowData;
	Vector<String> colNames;
	JTable table;
	LoginFrame lf;
	RegisterFrame rf;
	UpdateAccountFrame uf;
	MyPageFrame myf;
	int a_no = LoginFrame.a_no;
	static String nick;
	LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
	ArrayList<String> genre = new ArrayList<>();
	JLabel jlb_top1;
	JLabel jlb_top2;
	JLabel jlb_top3;
	
	public MyPageFrame(int a_no) {
		
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		
		
		myf = this;
//		this.lf = lf;
		
		setLayout(new BorderLayout());
		
		JPanel p_mypage = new JPanel(new BorderLayout());
		JPanel p_profile = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p_genre_top = new JPanel();
		JPanel p_genre_center = new JPanel(new GridLayout(1,3));
		p_genre_top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p_genre_top1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p_genre_top2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p_genre_top3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p_genre = new JPanel(new GridLayout(2,1));
		JPanel p_table = new JPanel(new BorderLayout());
		JPanel p_top = new JPanel(new GridLayout(2,1));
		JPanel p_top_low = new JPanel(new BorderLayout());
		JPanel p_page = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		colNames = new Vector<String>();
		colNames.add("인기순");
		colNames.add("영화");
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		updateranking();
		
		ImageIcon icon = new ImageIcon("h_icon.png");
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon h_icon = new ImageIcon(image);
		JButton btn_home = new JButton(h_icon);
		btn_home.setBorder(null);

		
		JLabel jlb_nick = new JLabel();
		JButton btn_profile = new JButton("회원정보 설정");
		JButton btn_logout =  new JButton("로그아웃");
		jlb_top1 = new JLabel();
		jlb_top2 = new JLabel();
		jlb_top3 = new JLabel();
		nick = loadnick();
		JLabel jlb_mypage = new JLabel("My Page");
		jlb_mypage.setForeground(Color.white);
		JLabel jlb_genre = new JLabel("영화 선호 장르");
		JLabel jlb_like = new JLabel(nick+"님이 좋아요한 영화");
			
		
		Font f = new Font("SansSerif", Font.BOLD,30);
		Font f2 = new Font("SansSerif", Font.BOLD,20);
		Font f3 = new Font("SansSerif", Font.BOLD,15);
		
		loadgenre();
		
		jlb_nick.setText(nick+"님 환영합니다.");
		
		jlb_nick.setForeground(Color.white);
		jlb_mypage.setFont(f);
		jlb_genre.setFont(f2);
		jlb_top1.setFont(f3);
		jlb_top2.setFont(f3);
		jlb_top3.setFont(f3);
		jlb_nick.setFont(f3);
		jlb_like.setFont(f2);
		
		p_page.add(jlb_mypage);
		
		p_mypage.add(btn_home, BorderLayout.WEST);
		p_mypage.add(p_page, BorderLayout.CENTER);
		
		p_profile.add(jlb_nick);
		p_profile.add(btn_profile);
		p_profile.add(btn_logout);
		
		p_genre_top.add(jlb_genre);
		p_genre_top1.add(jlb_top1);
		p_genre_top2.add(jlb_top2);
		p_genre_top3.add(jlb_top3);
		p_genre_center.add(p_genre_top1);
		p_genre_center.add(p_genre_top2);
		p_genre_center.add(p_genre_top3);
		p_genre.add(p_genre_top);
		p_genre.add(p_genre_center);
		
		p_table.add(jlb_like, BorderLayout.NORTH);
		p_table.add(jsp, BorderLayout.CENTER);
		
		p_top.add(p_mypage);
		p_top.add(p_profile);
		
		ImageIcon icon2 = new ImageIcon("sorrow.png");
		Image image2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon sorrow = new ImageIcon(image2);
		
		p_genre_top1.setOpaque(true);
		p_genre_top2.setOpaque(true);
		p_genre_top3.setOpaque(true);
		p_profile.setOpaque(true);
		p_page.setOpaque(true);
		p_genre_top.setOpaque(true);
		btn_home.setOpaque(true);
		p_table.setOpaque(true);
		//변
		p_genre_top1.setBackground(new Color(200, 200, 200));
		p_genre_top2.setBackground(new Color(220, 220, 220));
		p_genre_top3.setBackground(new Color(240, 240, 240));
		p_page.setBackground(new Color(57, 72, 103));
		p_profile.setBackground(new Color(57, 72, 103));
		p_genre_top.setBackground(new Color(230,230,230));
		btn_home.setBackground(new Color(57,72,103));
		p_table.setBackground(Color.white);
		//변
		
		p_top_low.add(p_top, BorderLayout.NORTH);
		p_top_low.add(p_genre, BorderLayout.CENTER);
		
		add(p_top_low, BorderLayout.NORTH);
		add(p_table, BorderLayout.CENTER);
		
		setSize(500,400);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateAccountFrame updatef = new UpdateAccountFrame(myf);
				updatef.setVisible(true);
				myf.dispose();
			}
		});
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				ArrayList<Vector<String>> list = dao.getRanking(a_no);
				Vector<String> v = list.get(row);
				int m_no = Integer.parseInt(v.get(1));
				MovieTab movie = new MovieTab(m_no, a_no);
				movie.setVisible(true);
			}
		});
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame mf = new MainFrame(a_no);
				mf.setVisible(true);
				myf.setVisible(false);
			}
		});
				
		btn_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int re = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까?","로그아웃", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, sorrow);
				if(re==0) {
					myf.dispose();
					lf.jtf_id.setText("");
					lf.jtf_pwd.setText("");
					lf.setVisible(true);
					lf.invalidate();
					lf.validate();
					lf.repaint();
				}
			}
		});
	}
	public void updateranking() {
		rowData.clear();
		ArrayList<Vector<String>> list = dao.getRanking(a_no);
		for(Vector<String> b : list) {
			Vector<String> v = new Vector<String>();
			v.add(b.get(0)+"");
			v.add(b.get(2));
			rowData.add(v);
		}
		table.updateUI();
	}
	public void loadgenre() {
		//
		ArrayList<String> genre = dao.getGenre(a_no);
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		//변
		try {
			jlb_top1.setText(genre.get(0));
			jlb_top2.setText(genre.get(1));
			jlb_top3.setText(genre.get(2));
		//변	
		jlb_top1.setForeground(Color.black);
		jlb_top2.setForeground(Color.black);
		jlb_top3.setForeground(Color.black);
		//변
		} catch (Exception e) {
			System.out.println("loadgenre 예외발생:"+e.getMessage());
		}
	}
	public String loadnick() {
		String nick = dao.getNick(a_no);
		return nick;
	}
}