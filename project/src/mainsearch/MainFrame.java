package mainsearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	Vector<String> rowData;
	private JTextField jtf;
	
	public MainFrame() {
	// 마이페이지 버튼 추가
	JButton btn_img;
	ImageIcon m_icon= new ImageIcon("m_icon.png");
	btn_img = new JButton(m_icon);
	btn_img.setPreferredSize(new Dimension(30,30));
	
	JPanel sp = new JPanel();
	JTextField jtf = new JTextField(10);
	JButton btn_search = new JButton("검색");
	sp.add(jtf);
	sp.add(btn_search);
	sp.add(btn_img);

	loadData();
	JList<String> jlist = new JList<String>(rowData);
	JScrollPane jsp = new JScrollPane(jlist);
	JPanel rp = new JPanel();
	rp.add(jsp);
	
	add(sp, BorderLayout.NORTH);
	add(rp, BorderLayout.CENTER);
	setTitle("ms 메인");
	setSize(600, 400);
	setVisible(true);
	}
	
	
	public void loadData(){
		rowData = new Vector<String>();
		MainDao dao = new MainDao();
		ArrayList<HashMap<String, Object>> list = dao.Ranking();
		for(HashMap<String, Object> map :list) {
			String result = map.get("rownum").toString()+"위    "+map.get("kr_name").toString();
			rowData.add(result);
		}
	}
	
	
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
