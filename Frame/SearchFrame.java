package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.Movie_staff_DAO;
import dao.SearchDAO;
import vo.SearchVo;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.File;

public class SearchFrame extends JFrame {
	SearchFrame search;
	Movie_staff_DAO msd1;
	JTextField jtf_search;
	JButton btn_search;
	JComboBox<String> jcb_list;
	JLabel jl;
	JLabel posterLabel, titleLabel, dateLabel, genreLabel, runtimeLabel;
	SearchDAO d;
	JPanel center;
	JPanel jp_center;
	File imageDirectory;
	int a_no;
	
	public SearchFrame(String word, int a_no) {
		
		
		
		
		Color co = new Color(237,43,42);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(155,164,181);
		
		this.a_no = a_no;
		
		ImageIcon icon = new ImageIcon("m_icon.png");
		Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon m_icon = new ImageIcon(image);
		JButton btn_mypage = new JButton(m_icon);
		btn_mypage.setBorder(BorderFactory.createEmptyBorder());
		btn_mypage.setBackground(coc);
		btn_mypage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPageFrame my = new MyPageFrame(a_no);
				setVisible(false);
				my.setVisible(true);
				
			}
		});
		
		
		JPanel jp_top=new JPanel();
		jp_center=new JPanel();
		
		btn_mypage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyPageFrame my = new MyPageFrame(a_no);
				setVisible(false);
				my.setVisible(true);
			}
		});
		//System.out.println(MainFrame.movietitle);
		
		center=new JPanel();
		jl=new JLabel();
		
		jtf_search=new JTextField(word, 20);
		btn_search=new JButton("검색");
		jcb_list=new JComboBox<String>();
		jcb_list.addItem("좋아요순");
		
		jcb_list.setBorder(lb);

		jp_top.setLayout(new FlowLayout());
		jp_top.add(jtf_search);
		jp_top.add(btn_search);
		jp_top.add(jcb_list);
		jp_top.add(btn_mypage);
		
		String title=jtf_search.getText();
		d=new SearchDAO();
		ArrayList<SearchVo> list=d.listCustomer(title);

		jp_center.setLayout(new GridLayout(list.size(),1));
		
		// 파일 디렉토리에서 이미지를 불러오기 위한 변수 설정
		imageDirectory = new File("C:\\movieposeter");
		updateSearchResult();
		
		btn_search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				updateSearchResult();
			}
		});
		
		
		JScrollPane jsp = new JScrollPane(jp_center, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(jp_top,BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		
		jp_top.setBackground(coc);
		btn_search.setBorder(lb);
		jtf_search.setBorder(lb);
		
		
		
		setSize(600,800);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
    private void updateSearchResult() {
        
    	Font bod = new Font("궁서", Font.BOLD, 13);
    	Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
    	
    	
    	String title = jtf_search.getText();
        d = new SearchDAO();
        ArrayList<SearchVo> list = d.listCustomer(title);

        jp_center.removeAll();
        jp_center.setLayout(new GridLayout(list.size(), 1));

        for (SearchVo sv : list) {
            JPanel moviePanel = new JPanel(new GridLayout(1, 5));

            // 파일 디렉토리에서 해당하는 이미지를 불러와 ImageIcon으로 생성
            String imagePath = imageDirectory.getAbsolutePath() + File.separator + sv.getM_image();
            ImageIcon icon1 = new ImageIcon(imagePath);

            Image img = icon1.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            ImageIcon icon2 = new ImageIcon(img);

            posterLabel = new JLabel(icon2);
            titleLabel = new JLabel(sv.getKr_name());
            dateLabel = new JLabel(sv.getM_date() + "년");
            genreLabel = new JLabel(sv.getM_genre());
            runtimeLabel = new JLabel("" + sv.getM_runtime());
            
            
            //변경
            
           Color white = new Color(255,255,255);
            
            posterLabel.setFont(bod);
            posterLabel.setForeground(Color.white);
            titleLabel.setFont(bod);
            titleLabel.setForeground(Color.white);
            dateLabel.setFont(bod);
            dateLabel.setForeground(Color.white);
            genreLabel.setFont(bod);
            genreLabel.setForeground(Color.white);
            runtimeLabel.setFont(bod);
            runtimeLabel.setForeground(Color.white);
            //변경
            
            
            
            
            moviePanel.add(posterLabel);
            moviePanel.add(titleLabel);
            moviePanel.add(dateLabel);
            moviePanel.add(genreLabel);
            moviePanel.add(runtimeLabel);
            jp_center.add(moviePanel);
            
            moviePanel.setBackground(co);
            
            
            moviePanel.addMouseListener(new MouseListener() {
				
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
					int m_no=sv.getM_no();
					MovieTab mt = new MovieTab(m_no, a_no);
					mt.setVisible(true);
				}
			});
        }

        jp_center.revalidate();
        jp_center.repaint();
    }
    
}