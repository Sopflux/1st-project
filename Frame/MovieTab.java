package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import dao.LoginAndRegisterDAO;
import dao.Movie_staff_DAO;
import vo.MovieVO;
import vo.ReviewVO;
import vo.Staff_infoVO;

class StaffButton extends JButton{
	int s_no;
	public StaffButton(String name,String type, String sl_image, int sl_no) {
		this.s_no = sl_no;
//		ImageIcon icon = new ImageIcon(path);
		
		ImageIcon icon = new ImageIcon();

	      // JLabel에 ImageIcon 추가
	    JLabel image1 = new JLabel(icon);
		
		String dir= "/Users/ahyeonlee/Desktop/ahyeon/sketchup/";
	      try {
	    	    Image image = ImageIO.read(new File(dir+sl_image));
	    	    int width = 100;
	    	    int height = 80;
	    	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	    	    icon.setImage(scaledImage);
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
		
	      JPanel p = new JPanel();
	      JLabel jlb_type = new JLabel(type);
	      JLabel jlb_name = new JLabel(name);
	      p.add(jlb_type);
	      p.add(jlb_name);
	      
		this.setLayout(new FlowLayout());
		this.add(image1);
		this.add(p);
//		this.add(new JLabel(title));
		this.setBorder(null);
		this.setBackground(Color.white);
	}
}

public class MovieTab extends JFrame {	
	Color co = new Color(240,240,240);
	LineBorder lb = new LineBorder(Color.black);
	Color coc = new Color(57, 72, 103);
	
	Movie_staff_DAO d ;
	String s_type;
	String sl_name;
	String sl_image;
//	JPanel review;
	int sl_no;
	JTabbedPane tabbedPane; 
	JPanel staff;
	int a_no;
	public MovieTab(int m_no, int a_no) {
		this.a_no = a_no;
//		setResizable(false); // 창크기 고정
	  d = new Movie_staff_DAO();
		
	  setTitle("영화상세정보");
	  setLayout(new GridLayout(2,1));

      // 탭 패널 생성
	  tabbedPane  = new JTabbedPane();
	  
	  tabbedPane.setBackground(coc);
	  
	  
      // 첫 번째 탭
	  firsttab(m_no);

      // 두 번째 탭
      secondtab(m_no);

      // 세 번째 탭
      thirdtab(m_no);

      pack();
      setSize(800,900);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
	}
	
	
	// ---------------------------------------------------
	public JPanel loadReview(int m_no) {
		
		
		
		JPanel review = new JPanel();
		ArrayList<ReviewVO> list = d.getreview(m_no);
		for (int i=0 ;i< list.size();i++) {
	    	ReviewVO rv = list.get(i);
	    	String id = rv.getId();
	    	String n_review = rv.getReveiw();
	    	String date = rv.getDate();
	    	int count = rv.getCount();
	    	float rate= rv.getRate();
	    	review.setLayout(new FlowLayout());
	    	JPanel north = new JPanel();
	    	JPanel r = new JPanel();
	    	
	    	r.setLayout(new BorderLayout());
	    	
	    	JLabel jlb_id = new JLabel(id);
	    	JLabel jlb_rate = new JLabel(rate+"");
	    	JLabel jlb_date = new JLabel(date);
	    	JLabel jlb_count = new JLabel(count+"");
	    	
	    	north.add(jlb_id);
	    	north.add(jlb_rate);
	    	north.add(jlb_count);
	    	north.add(jlb_date);
	    	
	    	JTextArea jta_review = new JTextArea(n_review);
	    	jta_review.setSize(200, 100);
	    	jta_review.setLineWrap(true);
	    	JScrollPane jsp_review = new JScrollPane(jta_review);
	    	jsp_review.setPreferredSize(new Dimension(200, 50));
	    	r.add(north,BorderLayout.NORTH);
	    	r.add(jsp_review,BorderLayout.CENTER);
	 
		
		review.add(r);
		
		}
		return review;
	}
	
	public void firsttab(int m_no) {
		
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		
		 JPanel maininfo = new JPanel();
		maininfo.setBackground(coc);
		 
		 String story = d.getStory(m_no);
		 
	      JLabel label1 = new JLabel("영화정보");
	      label1.setForeground(Color.white);
	      label1.setVerticalAlignment(JLabel.CENTER);
	      JTextArea jta_story = new JTextArea(story);
	      jta_story.setLineWrap(true);
	      JScrollPane jsp_label = new JScrollPane(jta_story);
	      jsp_label.setBackground(coc);
	      
	      
	      jta_story.setPreferredSize(new Dimension(400, 300));
	      getContentPane().add(jsp_label);
	      maininfo.setLayout(new BorderLayout());
	      maininfo.add(label1,BorderLayout.NORTH);
	      maininfo.add(jsp_label,BorderLayout.CENTER);
	      tabbedPane.addTab("주요 정보", maininfo);
	      
	      tabbedPane.setBackground(coc);
	}
	
	public void secondtab(int m_no) {
	      
		
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		Color cocc = new Color(255, 255, 255);
		
	      // 스태프 리스트 가져오기 
	      ArrayList<Staff_infoVO> list =  d.getstaff(m_no);
	      
	      JPanel staff = new JPanel();
	      staff.setBackground(cocc);
	      JPanel person = new JPanel();
	      person.setBackground(cocc);
	      
//	      staff.setLayout(new GridLayout(2,1));
	      person.setLayout(new GridLayout(list.size(),1));
//	      person.setSize(800,200);
	      staff.setBackground(Color.white);

	      

	      
	     for (int i=0 ;i< list.size();i++) {
	    	JPanel p = new JPanel();
	    	p.setBackground(Color.white);
	    	
	    	Staff_infoVO sl = list.get(i);
	    	s_type = sl.getS_type();
	    	sl_name = sl.getSl_name();
	    	sl_image = sl.getSl_image();
	    	sl_no = sl.getSl_no();
	    	
	    	JButton sb = new StaffButton(sl_name, s_type, sl_image, sl_no);
	    	sb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					StaffButton sb =  (StaffButton)e.getSource();
					int sl_no = sb.s_no;
					Staff_frame sf = new Staff_frame(sl_no,a_no);
					sf.setVisible(true);
					setVisible(false);
				}
			});

	     
	      staff.add(sb);
	     }
	      
	      JScrollPane jsp_pdetail = new JScrollPane(staff);
	      tabbedPane.addTab("출연/제작", jsp_pdetail);
	} //end secondtab
	
	public void thirdtab(int m_no) {
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		Color cocc = new Color(255, 255, 255);
		
		JPanel body = new JPanel();
		body.setBackground(cocc);
		
		
		
//		JPanel review1 = new JPanel();
//		review1.setLayout(new GridLayout(list.size(),list.size()));
//		review1.setSize(200,100);
		body.setLayout(new BorderLayout());
		
		
		JPanel review = loadReview(m_no);
		JScrollPane jsp_r = new JScrollPane(review);
		body.add(jsp_r,BorderLayout.CENTER);
		JTextField contents = new JTextField(20);
		contents.setPreferredSize(new Dimension(100, 100));
		
		review.setBackground(cocc);
		
	    JButton enroll = new JButton("등록");
	  
	    
	    String[] items = {"1", "2", "3", "4", "5"};
	    JComboBox<String> comboBox = new JComboBox<>(items);
	    comboBox.setSelectedIndex(4);
	    JPanel p_btn = new JPanel();
	    p_btn.add(comboBox);
	    p_btn.add(enroll);
	    
	    
	    enroll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String c = contents.getText();
				int idx = comboBox.getSelectedIndex();
				int rate = Integer.parseInt(items[idx]);
				int re = d.addreview(c, rate, m_no, a_no);
				if (re == 1) {
					JOptionPane.showMessageDialog(null, "등록성공!");
					JPanel review = loadReview(m_no);
					JScrollPane jsp_r = new JScrollPane(review);
					body.add(jsp_r,BorderLayout.CENTER);
					
				}else {
					JOptionPane.showMessageDialog(null, "등록실패!");
				}
			}
		});
	    
	    
	    JPanel regisReview = new JPanel();
	    regisReview.setLayout(new BorderLayout());
	    regisReview.add(contents, BorderLayout.CENTER);
	    regisReview.add(p_btn, BorderLayout.EAST);
	    
	  
	    
	    body.add(regisReview,BorderLayout.SOUTH);
	      tabbedPane.addTab("평점/리뷰", body);

	      // 프레임에 탭 패널 추가
	      JPanel d = detailpanel(m_no,a_no);
	      add(d);
	      add(tabbedPane);
	}
	
	public JPanel detailpanel(int m_no, int a_no) {
		
		Color co = new Color(240,240,240);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(238,241,255);
		
		
		ArrayList<MovieVO> list = d.getmoviedetail(m_no);
		String kr,org,mdate,country,genre,image,com;
		int runtime;
		int likes = d.getLike(m_no);
		JPanel detail = new JPanel();
		for(MovieVO m:list) {
			kr = m.getKr_name();
			org = m.getOrg_name();
			mdate = m.getM_date();
			runtime = m.getM_runtime();
			country = m.getM_country();
			genre = m.getM_genre();
			image = m.getM_image();
			com = m.getM_company();
		//변경
			
		detail.setBackground(co);
		detail.setLayout(null);
		JLabel la_date = new JLabel("개봉: "+mdate);
		la_date.setForeground(Color.black);
		detail.add(la_date);
		JLabel la_genre = new JLabel("장르: "+genre);
		la_genre.setForeground(Color.black);
		detail.add(la_genre);
		JLabel la_country  = new JLabel("국가: "+country);
		la_country.setForeground(Color.BLACK);
		detail.add(la_country);
		JLabel la_run = new JLabel("러닝타임: "+runtime+"분");
		la_run.setForeground(Color.BLACK);
		detail.add(la_run);
		JLabel la_kr = new JLabel(kr);
		la_kr.setForeground(Color.black);
		detail.add(la_kr);
		JLabel la_org = new JLabel(org+" , " +mdate);
		la_org.setForeground(Color.black);
		detail.add(la_org);
		JLabel la_likes = new JLabel(likes+"");
		la_likes.setForeground(Color.black);
		detail.add(la_likes);
		JLabel la_com = new JLabel("제작 : "+com);
		la_com.setForeground(Color.black);
		detail.add(la_com);
		//변경
		
		try {
			
			Image img = ImageIO.read(new File("/Users/ahyeonlee/Desktop/ahyeon/sketchup/"+image));
			
			// 이미지 크기 조정
			int width = 250;
			int height = 357;
			Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			// ImageIcon 객체 생성
			ImageIcon poster = new ImageIcon(resizedImg);
			JLabel jlb_poster = new JLabel(poster);
			jlb_poster.setBounds(0, 88, 300, 300);
		
			JLabel hu1 = new JLabel();
			detail.add(jlb_poster);
			hu1.setBounds(420, 270, 50, 50);	
			detail.add(hu1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 폰트 및크기 지정
				la_date.setFont(la_date.getFont().deriveFont(19.0f));
				la_genre.setFont(la_genre.getFont().deriveFont(19.0f));
				la_country.setFont(la_country.getFont().deriveFont(19.0f));
				la_run.setFont(la_run.getFont().deriveFont(19.0f));
				la_kr.setFont(la_kr.getFont().deriveFont(35.0f));
				la_org.setFont(la_org.getFont().deriveFont(27.0f));
				la_com.setFont(la_com.getFont().deriveFont(19.0f));
				
				// 이미지 파일을 읽어와서 Image 객체 생성

				la_likes.setBounds(595,190, 300, 300);
				la_date.setBounds(300, 60, 300, 300);
				la_genre.setBounds(300, 150, 300, 300);
				la_country.setBounds(300, 90, 300, 300);
				la_run.setBounds(300, 120, 300, 300);
				la_kr.setBounds(300, -41, 300, 300);
				la_org.setBounds(300, -5, 300, 300);	
				la_com.setBounds(300, 180, 300, 300);
				
				ImageIcon icon3 = new ImageIcon("m_icon.png");
				Image image3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon m_icon = new ImageIcon(image3);
				JButton btn_my = new JButton(m_icon);
				
				ImageIcon icon4 = new ImageIcon("h_icon.png");
				Image image4 = icon4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon h_icon = new ImageIcon(image4);
				JButton btn_home = new JButton(h_icon);
				
				btn_home.setBorder(null);
				btn_my.setBorder(null);
				
				JButton btn_likes = new JButton();
				
				
				
				detail.add(btn_my);
				detail.add(btn_home);
				detail.add(btn_likes);
				btn_my.setBounds(580, 20, 50, 50);
				btn_home.setBounds(20, 20, 50, 50);
				btn_likes.setBounds(590, 350, 20, 20);
				
				// 마이페이지버튼에 이미지 추가 
				
				// 좋아요 버튼에 추가할 아이콘 생성
				ImageIcon icon1 = new ImageIcon("like.png");
				Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				ImageIcon like = new ImageIcon(image1);
				
				ImageIcon icon2 = new ImageIcon("likex.png");
				Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				ImageIcon likeX = new ImageIcon(image2);
				btn_likes.setBorder(BorderFactory.createEmptyBorder());
				
				JLabel like_icon = new JLabel(like);
				JLabel likex_icon = new JLabel(likeX);
				btn_likes.setSize(30,30);
				btn_likes.setLayout(new FlowLayout(FlowLayout.CENTER));

				
				
				int check  = d.checkLike(a_no, m_no);
				if (check == 1 ) {
					btn_likes.add(like_icon);
				}else {
					btn_likes.add(likex_icon); 
				}
				btn_my.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MyPageFrame myf = new MyPageFrame(a_no);
						myf.setVisible(true);
						setVisible(false);
						
					}
				});
				
				
				
				
				btn_home.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainFrame mf  = new MainFrame(a_no);
						mf.setVisible(true);
						setVisible(false);
					}
				});
				
//				btn_home.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						MypageMain mypage = new MypageMain();
//						mypage.setVisible(true);
//						setVisible(false);
//					}
//				});
				
				btn_likes.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("좋아요에서 확인 회원번호 :"+a_no+" 영화번호 : "+m_no);
					// 찜에 추가했는지 확인
						int checkLike  = d.checkLike(a_no, m_no);
						if (checkLike == 1 ) {
							d.delLike(a_no, m_no);
							btn_likes.remove(likex_icon); 
							btn_likes.add(like_icon);
							btn_likes.updateUI();
							int likes = d.getLike(m_no);
							la_likes.setText(likes+"");
							la_likes.updateUI();
							return;
						}else {
							d.addLike(a_no, m_no);
							btn_likes.remove(like_icon); 
							btn_likes.add(likex_icon);
							btn_likes.updateUI();
							int likes = d.getLike(m_no);
							la_likes.setText(likes+"");
							la_likes.updateUI();
							return;
						}

					}
				});
				

				}		
		return detail;
	}
	
	
}

