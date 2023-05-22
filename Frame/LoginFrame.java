package movie_sketch;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.LoginAndRegisterDAO;

public class LoginFrame extends JFrame {
	JTextField jtf_id;
	JTextField jtf_pwd;
	
	LoginFrame lf;
	MyPageFrame myf;
	
	LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
	public static int a_no;
	
	public LoginFrame() {
		
		//색상변경객
		Color co = new Color(000,000,000);
		LineBorder lb = new LineBorder(Color.black);
		Color coc = new Color(57, 72, 103);
		
		lf = this;
		setTitle("로그인");
		jtf_id = new JTextField(15);
		jtf_pwd = new JPasswordField(15);
		JButton btn_login = new JButton("로그인");
		JButton btn_register = new JButton("회원가입");
		JLabel jl_welcome = new JLabel("Movie Sketch");
		jl_welcome.setForeground(Color.white);
		setLayout(new GridLayout(4,1));
		
		JPanel p_welcome = new JPanel();
		
		//변
		p_welcome.setBackground(coc);
		//변
		JPanel p_id = new JPanel(new GridLayout(1,2));
		JPanel p_pwd = new JPanel(new GridLayout(1,2));
		JPanel p_btn = new JPanel(new FlowLayout());
		p_btn.setBackground(co);
		
		Font f = new Font("SansSerif", Font.BOLD,24);
		jl_welcome.setFont(f);
		//색상변
		jl_welcome.setForeground(Color.white);
		
		
		p_welcome.add(jl_welcome);
		
		//p_id.add(new JLabel("아이디"));
				JLabel a1 = new JLabel("아이디");
				a1.setForeground(Color.white);
				p_id.add(a1);
				p_id.add(jtf_id);
		
		
		//p_pwd.add(new JLabel("비밀번호(숫자 4자리)"));
		JLabel a2 = new JLabel("비밀번호(숫자 4자리)");
		a2.setForeground(Color.white);
		p_pwd.add(a2);
		p_pwd.add(jtf_pwd);
		p_id.setBackground(co);
		p_pwd.setBackground(co);
		//변
		
		p_btn.add(btn_login);
		p_btn.add(btn_register);
		
		add(p_welcome);
		add(p_id);
		add(p_pwd);
		add(p_btn);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = jtf_id.getText();
				int pwd = Integer.parseInt(jtf_pwd.getText());
				int re = dao.getLogin(id, pwd);
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
				}else if(re==0) {

					JOptionPane.showMessageDialog(null,  "비밀번호가 일치하지 않습니다.");
				
				}else {
					a_no = dao.getAno(id);
					String nick = dao.getNick(a_no);
					ImageIcon icon10 = new ImageIcon("hello.png");
					Image image10 = icon10.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					ImageIcon hello_icon = new ImageIcon(image10);
					JOptionPane.showMessageDialog(null, nick+"님 환영합니다.", "",  JOptionPane.INFORMATION_MESSAGE, hello_icon);
					MainFrame mf = new MainFrame(a_no);
					System.out.println(a_no);
					mf.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame rf = new RegisterFrame(lf);
				rf.setVisible(true);
				lf.setVisible(false);
			}
		});
	}
	public static void main(String[] args) {
		new LoginFrame();
	}
}