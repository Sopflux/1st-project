package movie_sketch;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import dao.SearchDAO;
import dao.Staff_detailDAO;
import vo.FIlmmoVO;
import vo.MovieVO;
import vo.SearchVo;
import vo.Staff_listVO;
class FilmoButton extends JButton{
	int m_no;
	public FilmoButton(String year,String m_name, String type,String m_image, int m_no) {
		this.m_no = m_no;
//		ImageIcon icon = new ImageIcon(path);
		
		ImageIcon icon = new ImageIcon();

	      // JLabel에 ImageIcon 추가
	    JLabel image1 = new JLabel(icon);
		
		String dir= "/Users/ahyeonlee/Desktop/ahyeon/sketchup/";
	      try {
	    	    Image image = ImageIO.read(new File(dir+m_image));
	    	    int width = 100;
	    	    int height = 80;
	    	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	    	    icon.setImage(scaledImage);
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
		
	      JPanel p = new JPanel();
	      JLabel jlb_year = new JLabel(year+"년");
	      JLabel jlb_name = new JLabel(m_name);
	      JLabel jlb_type = new JLabel(type);
	      
		this.setLayout(new FlowLayout());
		this.add(jlb_year);
		this.add(image1);
		this.add(jlb_type);
		this.add(jlb_name);
		

//		this.add(new JLabel(title));
		this.setBorder(null);
		this.setBackground(Color.white);
	}
}
public class Staff_frame extends JFrame {
	
	Color co = new Color(000,000,000);
	LineBorder lb = new LineBorder(Color.black);
	Color coc = new Color(57, 72, 103);
	
	Staff_detailDAO d;
	String type;
	int a_no;
	File imageDirectory;
	public Staff_frame(int sl_no,int a_no) {
		this.a_no = a_no;
		setTitle("인물상세정보");
		setLayout(new GridLayout(2,1));
		type = "";
		d = new Staff_detailDAO();
		JPanel detail = detailpanel(sl_no);
		JPanel filmo = filmoPan(sl_no);
		JScrollPane jsp = new JScrollPane(filmo);
		add(detail);
		add(jsp);
		setVisible(true);
		setSize(500,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public JPanel detailpanel(int sl_no) {
		
		//변경
		
		Color co = new Color(240,240,240);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		//변경
		
		ArrayList<Staff_listVO> list = d.getSDetail(sl_no);
		ArrayList<String> type_l = d.getType(sl_no);
		String image, name, country,bdate;
		for(int i = 0;i<type_l.size();i++) {
			if (i == type_l.size()){
				type += type_l.get(i);
			}
			type += type_l.get(i);
		}
		
		JPanel detail = new JPanel();
		for(Staff_listVO sl:list) {
			image = sl.getSl_image();
			name = sl.getSl_name();
			country = sl.getSl_country();
			bdate = sl.getSl_birth();
		
			//변경
			
			detail.setBackground(co);
			
		detail.setLayout(null);
		JLabel la_name= new JLabel(name);
		la_name.setForeground(Color.black);
		detail.add(la_name);
		JLabel la_bdate = new JLabel(bdate);
		la_bdate.setForeground(Color.black);
		detail.add(la_bdate);
		JLabel la_country  = new JLabel(country);
		la_country.setForeground(Color.black);
		detail.add(la_country);
		JLabel la_type  = new JLabel(type);
		la_type.setForeground(Color.black);
		detail.add(la_type);
		//변경
		
		try {
			
			Image img = ImageIO.read(new File("/Users/ahyeonlee/Desktop/ahyeon/sketchup/"+image));
			
			// 이미지 크기 조정
			int width = 250;
			int height = 357;
			Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			// ImageIcon 객체 생성
			ImageIcon Simage = new ImageIcon(resizedImg);
			JLabel jlb_Simage = new JLabel(Simage);
			jlb_Simage.setBounds(0, 88, 300, 300);
		
			detail.add(jlb_Simage);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 폰트 및크기 지정
		la_country.setFont(la_country.getFont().deriveFont(27.0f));
		la_bdate.setFont(la_bdate.getFont().deriveFont(27.0f));
		la_name.setFont(la_name.getFont().deriveFont(35.0f));
		la_type.setFont(la_type.getFont().deriveFont(27.0f));
		
		// 이미지 파일을 읽어와서 Image 객체 생성

		la_country.setBounds(300, 90, 300, 300);
		la_name.setBounds(300, -30, 300, 300);
		la_bdate.setBounds(300, 50, 300, 300);	
		la_type.setBounds(300, 10, 300, 300);	
		JButton btn_home = new JButton();
		JButton btn_my = new JButton();
		detail.add(btn_home);
		detail.add(btn_my);
		btn_home.setBounds(420, 10, 50, 50);
		btn_my.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame mf  = new MainFrame(a_no);
				mf.setVisible(true);
				setVisible(false);
			}
		});
		
		
		
		btn_my.setBounds(20, 10, 50, 50);
		
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPageFrame myf = new MyPageFrame(a_no);
				myf.setVisible(true);
				setVisible(false);
				
			}
		});
		}
		return detail;
	}
	
	public JPanel filmoPan(int sl_no) {
	//변경
		
		Color co = new Color(240,240,240);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(250, 250, 250);
		JPanel film = new JPanel();
		
	film.setBackground(coc);
	//변경
	
		
		
		imageDirectory = new File("/Users/ahyeonlee/Desktop/ahyeon/sketchup/");
//	        JLabel mdate, name, type,image;
	        ArrayList<FIlmmoVO> list = d.getFilmmo(sl_no);

	        film.setLayout(new GridLayout(list.size(), 1));

	        for (FIlmmoVO f : list) {
	        	
	        	String image = f.getImage();
	        	String year = f.getMdate();
	        	String m_name = f.getName();
	        	String type = f.getS_type();
	        	int m_no = f.getM_no();
	        	
		    	JButton fb = new FilmoButton(year, m_name, type, image,m_no);
		    	fb.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						FilmoButton fb =  (FilmoButton)e.getSource();
						int m_no = fb.m_no;
						MovieTab mt = new MovieTab(m_no,a_no);
						mt.setVisible(true);
						setVisible(false);
					}
				});
	        	
	        	film.add(fb);}

	        
		
		return film;
	}
	
//	public static void main(String[] args) {
//		new Staff_frame();

}

