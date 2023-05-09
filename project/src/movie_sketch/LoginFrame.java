package movie_sketch;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	JTextField jtf_id;
	JTextField jtf_pwd;
	
	LoginFrame lf;
	
	LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
	static int a_no;
	
	public LoginFrame() {
		lf = this;
		setTitle("로그인");
		jtf_id = new JTextField(15);
		jtf_pwd = new JTextField(15);
		JButton btn_login = new JButton("로그인");
		JButton btn_register = new JButton("회원가입");
		JLabel jl_welcome = new JLabel("Movie Sketch");
		setLayout(new GridLayout(4,1));
		JPanel p_welcome = new JPanel();
		JPanel p_id = new JPanel(new GridLayout(1,2));
		JPanel p_pwd = new JPanel(new GridLayout(1,2));
		JPanel p_btn = new JPanel(new FlowLayout());
		
		Font f = new Font("SansSerif", Font.BOLD,24);
		jl_welcome.setFont(f);
		
		p_welcome.add(jl_welcome);
		
		p_id.add(new JLabel("아이디"));
		p_id.add(jtf_id);
		
		p_pwd.add(new JLabel("비밀번호(숫자 4자리)"));
		p_pwd.add(jtf_pwd);
		
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
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "환영합니다.");
					a_no = dao.getAno(id);
					MyPageFrame myf = new MyPageFrame(lf);
					myf.setVisible(true);
					lf.setVisible(false);
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