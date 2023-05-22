package movie_sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;


import dao.BoardDAO;
import movie_sketch.LoginFrame;
import movie_sketch.MainFrame;
import vo.BoardVO;

public class BoardFrame extends JFrame{
	JTable table;
	Vector<String> colName;
	Vector<Vector<String>> rowData;
	JLabel jl_title;
	JButton btn_registration;
	JButton btn_search;
	JButton btn_home;
	Timer timer;
	JTextField jtf_search;
	JComboBox<String> jcb;
	String search_word;
	int idx;
	JPanel p_btn;
	BoardFrame bf;
	BoardDAO dao=new BoardDAO();
	ArrayList<BoardVO> list;
	int a_no = LoginFrame.a_no;
	  
	public BoardFrame() {
		
		
		Color co = new Color(57, 72, 103);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		Color cocc = new Color(255,255,255);
		
		
		a_no = LoginFrame.a_no;
		dao = new BoardDAO();
		jl_title=new JLabel("Movie Sketch");
		jl_title.setHorizontalAlignment(JLabel.CENTER);
		jl_title.setFont(new Font("SansSerif", Font.BOLD,24));
		btn_registration=new JButton("글쓰기");
		jcb=new JComboBox<String>();
		jcb.addItem("제목");
		jcb.addItem("닉네임");
		JLabel jl_blank=new JLabel("                                         ");
		jtf_search=new JTextField(20);
		p_btn=new JPanel();
		p_btn.setLayout(new FlowLayout());
		p_btn.add(jl_blank);
		p_btn.add(btn_registration); 
		
		p_btn.setBackground(cocc);
		
		ImageIcon icon = new ImageIcon("h_icon_w.png");
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon h_icon = new ImageIcon(image);
		btn_home = new JButton(h_icon);
		btn_home.setBorder(null);
		
		colName=new Vector<String>();
		colName.add("닉네임");
		colName.add("제목");
		colName.add("작성 날짜");
		btn_search=new JButton("검색");
		
		rowData=new Vector<Vector<String>>();
		
		table=new JTable(rowData, colName);
		JScrollPane jsp=new JScrollPane(table);
		
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				BoardVO b = list.get(row);
				int b_no=b.getB_no();
				int a_no=b.getA_no();
				ReadBoardFrame readboard=new ReadBoardFrame(b_no, a_no);
				readboard.setVisible(true);
			}
		});
		
		JPanel p_search=new JPanel();
		p_search.setLayout(new FlowLayout());
		p_search.add(jcb);
		p_search.add(jtf_search);
		p_search.add(btn_search);
		
		p_search.setBackground(co);
		
		JPanel p_bottom=new JPanel();
		p_bottom.setLayout(new BorderLayout());
		p_bottom.add(p_search, BorderLayout.CENTER);
		p_bottom.add(p_btn, BorderLayout.SOUTH);
		
		p_bottom.setBackground(co);
		
		JPanel p = new JPanel();
		p.add(btn_home);
		p.add(jl_title);
		add(p, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(p_bottom, BorderLayout.SOUTH);
		
		p.setBackground(co);
		
		loadBoard();
		
		setSize(450,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer = new Timer(500, new ActionListener() {
			private boolean red = true;
			
			public void actionPerformed(ActionEvent e) {
				if (red) {
					jl_title.setForeground(Color.RED);
				} else {
					jl_title.setForeground(Color.BLACK);
				}
				red = !red;
			}
		});
		timer.start();
		
		jcb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				idx=jcb.getSelectedIndex();
				System.out.println(idx);
			}
		});
		
		btn_search.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        search_word = jtf_search.getText();
		        if (idx == 1) {
		            list = dao.searchnick(search_word);
		            
		        } else {
		            list = dao.searchtitle(search_word);
		           
		        }
		        rowData.clear();
	            for (BoardVO b : list) {
	                Vector<String> v = new Vector<String>();
	                v.add(b.getA_nick());
	                v.add(b.getB_title());
	                v.add(b.getB_date());
	                rowData.add(v);
	            }
		        table.updateUI();
//		        table.setModel(new DefaultTableModel(rowData, colName));
//		        TableColumnModel tcm = table.getColumnModel();
//		        tcm.getColumn(0).setPreferredWidth(15);
//		        tcm.getColumn(1).setPreferredWidth(250);
//		        tcm.getColumn(2).setPreferredWidth(40);
		    }
		});

		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame mainf = new MainFrame(a_no);
				mainf.setVisible(true);
				setVisible(false);
				
			}
		});
		
		btn_registration.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertBoardFrame inbf = new InsertBoardFrame();
				inbf.setVisible(true);
				setVisible(false);
//				bf.dispose();
			}
		});
		
	}

	public void loadBoard() {
	    BoardDAO dao = new BoardDAO();
	    list = dao.listBoard();
	    rowData.clear();
	    for(BoardVO b:list) {
	        Vector<String> v = new Vector<String>();
	        v.add(b.getA_nick());
	        v.add(b.getB_title());
	        v.add(b.getB_date());

	        rowData.add(v);
	    }

	    // 각 칼럼에 대해 폭을 지정
	    TableColumnModel tcm = table.getColumnModel();
	    tcm.getColumn(0).setPreferredWidth(15); // 닉네임 칼럼
	    tcm.getColumn(1).setPreferredWidth(250); // 제목 칼럼
	    tcm.getColumn(2).setPreferredWidth(40); // 작성 날짜 칼럼

	    table.updateUI();
	}
	public static void main(String[] args) {
		new BoardFrame();
	}
}