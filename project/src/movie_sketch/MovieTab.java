package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import dao.Movie_staff_DAO;
import vo.MovieVO;
import vo.ReviewVO;
import vo.Staff_infoVO;


public class MovieTab extends JFrame {	
	Movie_staff_DAO d ;
	String s_type;
	String sl_name;
	String sl_image;
	JTabbedPane tabbedPane; 
	MainFrame main;
	MovieTab movie;
	
	public MovieTab(int m_no) {
		
//		setResizable(false); // 창크기 고정
	  d = new Movie_staff_DAO();
		
	  setTitle("영화상세정보");
	  setLayout(new GridLayout(2,1));

      // 탭 패널 생성
	  tabbedPane  = new JTabbedPane();

      // 첫 번째 탭
	  firsttab(m_no);

      // 두 번째 탭
      secondtab(m_no);

      // 세 번째 탭
      thirdtab(m_no);

      pack();
      setSize(800,900);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
	}
	
	
	// ---------------------------------------------------
	
	
	public void firsttab(int m_no) {
		 JPanel maininfo = new JPanel();
		 String story = d.getStory(m_no);
		 
	      JLabel label1 = new JLabel("영화정보");
	      label1.setVerticalAlignment(JLabel.CENTER);
	      JTextArea jta_story = new JTextArea(story);
	      jta_story.setLineWrap(true);
	      JScrollPane jsp_label = new JScrollPane(jta_story);
	      jta_story.setPreferredSize(new Dimension(400, 300));
	      getContentPane().add(jsp_label);
	      maininfo.setLayout(new BorderLayout());
	      maininfo.add(label1,BorderLayout.NORTH);
	      maininfo.add(jsp_label,BorderLayout.CENTER);
	      tabbedPane.addTab("주요 정보", maininfo);
	}
	
	public void secondtab(int m_no) {
	      
	      // 스태프 리스트 가져오기 
	      ArrayList<Staff_infoVO> list =  d.getstaff(m_no);
	      
	      JPanel staff = new JPanel();
	      JPanel person = new JPanel();
	      person.setLayout(new GridLayout(2,list.size()/2));
	      person.setSize(800,200);
//	      person.setBackground(Color.white);
	      

	      
	     for (int i=0 ;i< list.size();i++) {
	    	Staff_infoVO sl = list.get(i);
	    	s_type = sl.getS_type();
	    	sl_name = sl.getSl_name();
	    	sl_image = sl.getSl_image();
	    	
	      // 이미지 추가 
	      ImageIcon icon = new ImageIcon();

	      // JLabel에 ImageIcon 추가
	      JLabel image1 = new JLabel(icon);
	      person.add(image1);
	      String dir = "/Users/ahyeonlee/Desktop/ahyeon/sketchup/";

	      //이미지 파일을 나중에 지정
	      try {
	    	    Image image = ImageIO.read(new File(dir+sl_image));
	    	    int width = 100;
	    	    int height = 80;
	    	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	    	    icon.setImage(scaledImage);
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
	       // 출연 상세 

	      
	      JPanel txt1 = new JPanel();
//	      txt1.setBackground(Color.white);
	      txt1.setLayout(new GridLayout(2,1));
	      JLabel label2 = new JLabel(s_type);
	      JLabel labelname = new JLabel(sl_name);
	      txt1.add(label2);
	      txt1.add(labelname);
	      person.add(txt1);
	     }
	      
	      staff.add(person);
	      tabbedPane.addTab("출연/제작", staff);
	} //end secondtab
	
	public void thirdtab(int m_no) {
		ArrayList<ReviewVO> list = d.getreview(m_no);
		JPanel body = new JPanel();
//		JPanel review1 = new JPanel();
//		review1.setLayout(new GridLayout(list.size(),list.size()));
//		review1.setSize(200,100);
		body.setLayout(new GridLayout(list.size()/2,2));

		for (int i=0 ;i< list.size();i++) {
	    	ReviewVO rv = list.get(i);
	    	String id = rv.getId();
	    	String n_review = rv.getReveiw();
	    	String date = rv.getDate();
	    	int count = rv.getCount();
	    	float rate= rv.getRate();
	    	JPanel review = new JPanel();
	    	review.setLayout(new BorderLayout());
	    	JPanel north = new JPanel();
	    	JPanel center = new JPanel();
	    	
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

	    	center.add(jsp_review);
	 
		
		review.add(north,BorderLayout.NORTH);
		review.add(center,BorderLayout.CENTER);
		body.add(review);
		}
//		JScrollPane jsp_r = new JScrollPane(review1);
//		body.add(jsp_r);
	      tabbedPane.addTab("평점/리뷰", body);

	      // 프레임에 탭 패널 추가
	      JPanel d = detailpanel(m_no);
	      add(d);
	      add(tabbedPane);
	}
	
	public JPanel detailpanel(int m_no) {
		ArrayList<MovieVO> list = d.getmoviedetail(m_no);
		String kr,org,mdate,company ,country,genre,image,com;
		int runtime;
		int likes = d.getLike(13);
		JPanel detail = new JPanel();
		for(MovieVO m:list) {
			kr = m.getKr_name();
			org = m.getOrg_name();
			mdate = m.getM_date();
			runtime = m.getM_runtime();
			company = m.getM_company();
			country = m.getM_country();
			genre = m.getM_genre();
			image = m.getM_image();
			com = m.getM_company();
		
		
		detail.setLayout(null);
		JLabel la_date = new JLabel("개봉: "+mdate);
		detail.add(la_date);
		JLabel la_genre = new JLabel("장르: "+genre);
		detail.add(la_genre);
		JLabel la_country  = new JLabel("국가: "+country);
		detail.add(la_country);
		JLabel la_run = new JLabel("러닝타임: "+runtime+"분");
		detail.add(la_run);
		JLabel la_kr = new JLabel(kr);
		detail.add(la_kr);
		JLabel la_org = new JLabel(org+" , " +mdate);
		detail.add(la_org);
		JLabel la_likes = new JLabel(likes+"");
		detail.add(la_likes);
		JLabel la_com = new JLabel("제작 : "+com);
		detail.add(la_com);
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
		// 폰트 및크기 지
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
				JButton btn = new JButton();
				JButton btn1 = new JButton();
				JButton btn2 = new JButton();
				detail.add(btn);
				detail.add(btn1);
				detail.add(btn2);
				btn.setBounds(580, 20, 50, 50);
				btn1.setBounds(20, 20, 50, 50);
				btn2.setBounds(590, 350, 20, 20);
				}		
		return detail;
	}
	
	
}
