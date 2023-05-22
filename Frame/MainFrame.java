package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import movie_sketch.BoardFrame;
import dao.LoginAndRegisterDAO;
import dao.MainDAO;

public class MainFrame extends JFrame {
	

	SearchFrame search;
	MainFrame main;
	MovieTab movie;
	MyPageFrame my;
	int a_no;
	String a_nick;
	
	JTabbedPane tab;
	Vector<String> rowData;
	Vector<String> data_2000;
	Vector<String> data_2010;
	Vector<String> data_2020;
	int cnt = 0;
	JTextField jtf;
	
	
	public MainFrame(int a_no) {
		//변경
		
		
		
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		Color cocc = new Color(255,255,255);
		//변경
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.a_no=a_no;
			
		// 마이페이지 버튼
		ImageIcon icon = new ImageIcon("m_icon_w.png");
		Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon m_icon = new ImageIcon(image);
		JButton btn_mypage = new JButton(m_icon);
		
		//게시판 버튼
		ImageIcon icon30 = new ImageIcon("clipboard_w.png");
		Image image30 = icon30.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon clipboard = new ImageIcon(image30);
		JButton btn_clip = new JButton(clipboard);
		
		// 메인창 상단
		JPanel sp = new JPanel(new BorderLayout());
		JPanel sp_up = new JPanel();
		JPanel sp_down = new JPanel(new FlowLayout());
		
		
		// 제목
		JLabel jl_welcome = new JLabel("Movie Sketch");
		
		jl_welcome.setForeground(Color.white);
		sp_up.add(jl_welcome);
		Font f = new Font("SansSerif", Font.BOLD,24);
		jl_welcome.setFont(f);
		
		// 검색창
		jtf = new JTextField(20);
		JButton btn_search = new JButton("검색");
		sp_down.add(jtf);
		sp_down.add(btn_search);
		sp_down.add(btn_clip);
		sp_down.add(btn_mypage);
		
		sp.add(sp_up, BorderLayout.NORTH);
		sp.add(sp_down, BorderLayout.CENTER);
	
		sp_down.setBackground(coc);
		sp_up.setBackground(coc);
		
		
	
	 // 추천 영화
	    JPanel rep = new JPanel(new BorderLayout());
//	    String nick = mainFrame.nick;
	    LoginAndRegisterDAO lad = new LoginAndRegisterDAO();
	    String nick = lad.getNick(a_no);
	    JLabel rep_title = new JLabel(nick+"께 추천하는 영화 top5");
	    rep_title.setForeground(Color.white);
	    rep.setBackground(co);
	    
	    Font f1 = new Font("SansSerif", Font.BOLD,12);
	    rep_title.setFont(f1);
	    
	  
	    
	    
	    MainDAO dao = new MainDAO();
	    ArrayList<HashMap<String, Object>> map = dao.Recommendation(a_nick);
	    rep.add(rep_title, BorderLayout.NORTH);
	
	    JPanel rbPanel = new JPanel(new GridLayout(0, 5, 5, 5));
	    for (HashMap<String,Object> h:map) {
	        String name= (String)h.get("kr_name");
	        String m_image= (String)h.get("m_image");
	        int m_no = (Integer)h.get("m_no");
	        RecommandButton rb = new RecommandButton(m_no, m_image, name);
	        rbPanel.add(rb);
	        //변경
	        
	        rbPanel.setBackground(coc);
	        //변경
	        
	        rb.addActionListener(new ActionListener() {
	        	
	        	
	        	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                RecommandButton rb = (RecommandButton)e.getSource();
	                int m_no = rb.m_no;
	                MovieTab movie = new MovieTab(m_no, a_no);
	                movie.setVisible(true);
	                setVisible(false);
	            }
	        });
	    }
	    rep.add(rbPanel, BorderLayout.CENTER);
	
	    // 전체 랭킹
        loadData();
        JPanel jp_ranking = new JPanel(new BorderLayout());
        JLabel jl_ranking = new JLabel("전체 랭킹");
        
        ImageIcon icon10 = new ImageIcon("arrowl.png");
		Image image10 = icon10.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon arrowl = new ImageIcon(image10);
		
		ImageIcon icon20 = new ImageIcon("arrowr.png");
		Image image20 = icon20.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon arrowr = new ImageIcon(image20);
		
        JButton btn_backward = new JButton(arrowl);
        JButton btn_forward = new JButton(arrowr);
        
        btn_backward.setBorder(null);
        btn_forward.setBorder(null);
        btn_mypage.setBorder(null);
        btn_clip.setBorder(null);
        
        btn_backward.setBackground(Color.white);
        btn_forward.setBackground(Color.white);
        btn_mypage.setBackground(coc);
        btn_clip.setBackground(coc);
        
	    
	    
        JList<String> jlist = new JList<String>(rowData);
        jlist.setPreferredSize(new Dimension(500, 300));
        JScrollPane jsp = new JScrollPane(jlist);
        jp_ranking.add(jl_ranking, BorderLayout.NORTH);
        jp_ranking.add(jsp, BorderLayout.CENTER);
        jp_ranking.add(btn_backward, BorderLayout.WEST);
        jp_ranking.add(btn_forward, BorderLayout.EAST);

        
     
        
        btn_forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                if (cnt == 1) {
                    firsttab();
                    jlist.setListData(data_2000);
                    jl_ranking.setText("2000's 랭킹");
                } else if (cnt == 2) {
                    secondtab();
                    jlist.setListData(data_2010);
                    jl_ranking.setText("2010's 랭킹");
                } else if (cnt == 3) {
                    thirdtab();
                    jlist.setListData(data_2020);
                    jl_ranking.setText("2020's 랭킹");
                } else {
                    cnt = 0;
                    loadData();
                    jlist.setListData(rowData);
                    jl_ranking.setText("전체 랭킹");
                }
            }
        });
              
     
        btn_backward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt--;
                if (cnt == -1) {
                    cnt = 3;
                    thirdtab();
                    jlist.setListData(data_2020);
                    jl_ranking.setText("2020's 랭킹");
                    
                } else if (cnt == 2) {
                    secondtab();
                    jlist.setListData(data_2010);
                    jl_ranking.setText("2010's 랭킹");
                } else if (cnt == 1) {
                    firsttab();
                    jlist.setListData(data_2000);
                    jl_ranking.setText("2010's 랭킹");
                } else {
                    loadData();
                    jlist.setListData(rowData);
                    jl_ranking.setText("전체 랭킹");
                }
            }
        });

        add(sp, BorderLayout.NORTH);
        add(rep, BorderLayout.CENTER);
        add(jp_ranking, BorderLayout.SOUTH);
	
	
	
	setTitle("ms 메인");
	setSize(700, 500);
	setVisible(true);
	
	//마이페이지로 이동
	btn_mypage.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MyPageFrame my = new MyPageFrame(a_no);
			main.setVisible(false);
			my.setVisible(true);
		}
	});
	
	//게시판 이동 
	btn_clip.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			BoardFrame boardf = new BoardFrame();
			boardf.setVisible(true);
			setVisible(false);
		}
	});
	
	// 검색창으로 이동
	btn_search.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String word = jtf.getText();
			SearchFrame search = new SearchFrame(word, a_no);
			
			// 메인창 close, 검색창 open
//			main.setVisible(false);
			search.setVisible(true);		
		}
	});
	
	// 영화 상세 페이지로 이동
	jlist.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			switch (cnt) {
		    case 1:
		        if (!e.getValueIsAdjusting()) {
		            MainDAO dao = new MainDAO();
		            ArrayList<HashMap<String, Object>> list = dao.get2000sRanking();
		            int idx = jlist.getSelectedIndex();
		            HashMap<String, Object> map = list.get(idx);
		            int m_no = (Integer) map.get("m_no");
		            MovieTab movie = new MovieTab(m_no, a_no);
		            movie.setVisible(true);
		        }
		        break;

		    case 2:
		        if (!e.getValueIsAdjusting()) {
		            MainDAO dao = new MainDAO();
		            ArrayList<HashMap<String, Object>> list = dao.get2010sRanking();
		            int idx = jlist.getSelectedIndex();
		            HashMap<String, Object> map = list.get(idx);
		            int m_no = (Integer) map.get("m_no");
		            MovieTab movie = new MovieTab(m_no, a_no);
		            movie.setVisible(true);
		        }
		        break;

		    case 3:
		        if (!e.getValueIsAdjusting()) {
		            MainDAO dao = new MainDAO();
		            ArrayList<HashMap<String, Object>> list = dao.get2020sRanking();
		            int idx = jlist.getSelectedIndex();
		            HashMap<String, Object> map = list.get(idx);
		            int m_no = (Integer) map.get("m_no");
		            MovieTab movie = new MovieTab(m_no, a_no);
		            movie.setVisible(true);
		        }
		        break;

		    default:
		        if (!e.getValueIsAdjusting()) {
		            MainDAO dao = new MainDAO();
		            ArrayList<HashMap<String, Object>> list = dao.AllRanking();
		            int idx = jlist.getSelectedIndex();
		            HashMap<String, Object> map = list.get(idx);
		            int m_no = (Integer) map.get("m_no");
		            MovieTab movie = new MovieTab(m_no, a_no);
		            movie.setVisible(true);
		        }
		        break;
		}
				
			
		}
	});	
	}
	
		
	public void loadData(){
		rowData = new Vector<String>();
		MainDAO dao = new MainDAO();
		ArrayList<HashMap<String, Object>> list = dao.AllRanking();
		for(HashMap<String, Object> map :list) {
			String result = map.get("rownum").toString()+"위    "+map.get("kr_name").toString();
			rowData.add(result);
		}
	}
	
	public void firsttab(){
		data_2000 = new Vector<String>();
		MainDAO dao = new MainDAO();
		ArrayList<HashMap<String, Object>> list = dao.get2000sRanking();
		for(HashMap<String, Object> map :list) {
			String result = map.get("rownum").toString()+"위    "+map.get("kr_name").toString();
			data_2000.add(result);
		}
	}
	
	public void secondtab(){
		data_2010 = new Vector<String>();
		MainDAO dao = new MainDAO();
		ArrayList<HashMap<String, Object>> list = dao.get2010sRanking();
		for(HashMap<String, Object> map :list) {
			String result = map.get("rownum").toString()+"위    "+map.get("kr_name").toString();
			data_2010.add(result);
		}
	}
	
	public void thirdtab(){
		data_2020 = new Vector<String>();
		MainDAO dao = new MainDAO();
		ArrayList<HashMap<String, Object>> list = dao.get2020sRanking();
		for(HashMap<String, Object> map :list) {
			String result = map.get("rownum").toString()+"위    "+map.get("kr_name").toString();
			data_2020.add(result);
		}
		
	}
	
	
	class RecommandButton extends JButton{
		int m_no;
		public RecommandButton(int m_no ,String m_image, String name) {
			this.m_no = m_no;
//			ImageIcon icon = new ImageIcon(path);
			
			ImageIcon icon = new ImageIcon();

		      // JLabel에 ImageIcon 추가
		    JLabel image1 = new JLabel(icon);
			
			String dir= "C:\\movieposeter\\";
		      try {
		    	    Image image = ImageIO.read(new File(dir+m_image));
		    	    int width = 80;
		    	    int height = 100;
		    	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		    	    icon.setImage(scaledImage);
		    	} catch (Exception e) {
		    	    e.printStackTrace();
		    	}
			
		      JPanel p = new JPanel();
		      JLabel jlb_name = new JLabel(name);
		      p.add(jlb_name);
		      
			this.setLayout(new GridLayout(2,1));
			this.add(image1);
			this.add(p);
//			this.add(new JLabel(title));
			this.setBorder(null);
			this.setBackground(Color.white);
		}
	}

	
	
}