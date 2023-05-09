package movie_sketch;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LoginAndRegisterDAO;
import vo.AccountVO;

public class RegisterFrame extends JFrame {
	JTextField jtf_id;
	JTextField jtf_name;
	JTextField jtf_pw;
	JTextField jtf_pw2;
	JTextField jtf_email;
	JTextField jtf_nick;
	JTextField jtf_phone;
	boolean flag_id=false;
	boolean flag_nick=false;
	JButton btn_reg;
	JComboBox<Integer> cb_year;
	JComboBox<Integer> cb_month;
	JComboBox<Integer> cb_date;
	
	LoginFrame lf;
	RegisterFrame rf;
	
	public RegisterFrame(LoginFrame lf) {
		rf = this;
		this.lf = lf;
		
		setTitle("MS 회원가입");
		int a_no = LoginFrame.a_no;
		
		jtf_id = new JTextField(10);
		jtf_name = new JTextField(10);
		jtf_pw = new JTextField(10);
		jtf_pw2 = new JTextField(10);
		jtf_email = new JTextField(10);
		jtf_nick = new JTextField(10);
		jtf_phone = new JTextField(10);
		JButton btn_idcheck = new JButton("중복확인");
		JButton btn_nickcheck = new JButton("중복확인");
		JPanel p_id = new JPanel(new GridLayout(1,2));
		JPanel p_name = new JPanel(new GridLayout(1,2));
		JPanel p_pw = new JPanel(new GridLayout(1,2));
		JPanel p_pw2 = new JPanel(new GridLayout(1,2));
		JPanel p_birth = new JPanel(new GridLayout(1,2));
		JPanel p_email = new JPanel(new GridLayout(1,2));
		JPanel p_nick = new JPanel(new GridLayout(1,2));
		JPanel p_id_sub = new JPanel();
		JPanel p_nick_sub = new JPanel();
		JPanel p_phone = new JPanel(new GridLayout(1,2));
		JPanel p_birthsum = new JPanel();
		
		cb_year = new JComboBox<Integer>();
		cb_month = new JComboBox<Integer>();
		cb_date = new JComboBox<Integer>();
		
		for(int i=1900;i<=2023;i++) {
			cb_year.addItem(i);
		}
		for(int i=1;i<=12;i++) {
			cb_month.addItem(i);
		}
		cb_year.addActionListener(e ->updatecb_date());
		cb_month.addActionListener(e ->updatecb_date());
		updatecb_date();
		
		p_birthsum.add(cb_year);
		p_birthsum.add(new JLabel("년"));
		p_birthsum.add(cb_month);
		p_birthsum.add(new JLabel("월"));
		p_birthsum.add(cb_date);
		p_birthsum.add(new JLabel("일"));
		
		p_id_sub.add(new JLabel("아이디"));
		p_id_sub.add(btn_idcheck);
		p_id_sub.setLayout(new FlowLayout(FlowLayout.LEFT));
		p_id.add(p_id_sub);
		p_id.add(jtf_id);
		p_name.add(new JLabel("이름"));
		p_name.add(jtf_name);
		p_pw.add(new JLabel("비밀번호(숫자4자리)"));
		p_pw.add(jtf_pw);
		p_pw2.add(new JLabel("비밀번호 확인"));
		p_pw2.add(jtf_pw2);
		p_email.add(new JLabel("이메일"));
		p_email.add(jtf_email);
		p_birth.add(new JLabel("생년월일"));
		p_birth.add(p_birthsum);
		p_nick_sub.add(new JLabel("닉네임"));
		p_nick_sub.setLayout(new FlowLayout(FlowLayout.LEFT));
		p_nick_sub.add(btn_nickcheck);
		p_nick.add(p_nick_sub);
		p_nick.add(jtf_nick);
		p_phone.add(new JLabel("전화번호"));
		p_phone.add(jtf_phone);
		
		JPanel p_btn = new JPanel();
		
		btn_reg = new JButton("회원가입");
		JButton btn_reset = new JButton("초기화");
		JButton btn_home = new JButton("로그인");
		
		btn_reg.setEnabled(false);
		
		p_btn.add(btn_reg);
		p_btn.add(btn_reset);
		p_btn.add(btn_home);
		
		JPanel p_center = new JPanel(new GridLayout(9,1));
		p_center.add(p_name);
		p_center.add(p_id);
		p_center.add(p_pw);
		p_center.add(p_pw2);
		p_center.add(p_birth);
		p_center.add(p_email);
		p_center.add(p_nick);
		p_center.add(p_phone);
		
		add(p_center,BorderLayout.CENTER);
		add(p_btn,BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(600,380);
		
		
		btn_reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pwd = 0000;
				int pwd2 = 1111;
				try {
					pwd = Integer.parseInt(jtf_pw.getText());
					pwd2 = Integer.parseInt(jtf_pw2.getText());
				} catch (Exception e2) {
					System.out.println("예외발생: "+e2.getMessage());
					JOptionPane.showMessageDialog(null, "비밀번호는 숫자 4자리입니다.");
					return;
				}
				if(jtf_pw.getText().length()!=4 && jtf_pw2.getText().length()!=4) {
					JOptionPane.showMessageDialog(null, "비밀번호는 숫자 4자리입니다.");
					return;
				}
				if(pwd==pwd2) {
				String id =jtf_id.getText();
				String birth = cb_year.getSelectedItem().toString()+"/"+cb_month.getSelectedItem().toString()+"/"+cb_date.getSelectedItem().toString();
				String email = jtf_email.getText();
				String name = jtf_name.getText();
				String nick = jtf_nick.getText();
				String phone = jtf_phone.getText();
				AccountVO a = new AccountVO(0,id, pwd, birth,email,name,nick,phone);
				LoginAndRegisterDAO dao= new LoginAndRegisterDAO();
				int re = dao.insertAccount(a);
					if(re==1) { 
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인 값이 다릅니다.");
				}
			}
		});
		
		btn_idcheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = jtf_id.getText();
				flag_id = false;
				if(id.length()==0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					return;
				}
				LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
				int r =dao.idDoubleCheck(id);
				if(r==-1) {
					JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디입니다.");
					flag_id = true;
					checkButtons();
				}
				else {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
				}
			}
		});
		btn_nickcheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nick = jtf_nick.getText();
				flag_nick = false;
				if(nick.length()==0) {
					JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요.");
					return;
				}
				LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
				int r = dao.nickDoubleCheck(nick);
				if(r==-1) {
					JOptionPane.showMessageDialog(null, "사용할 수 있는 닉네임입니다");
					flag_nick = true;
					checkButtons();
				}
				else {
					JOptionPane.showMessageDialog(null,"이미 사용중인 닉네임입니다." );
				}
			}
		});
		btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtf_id.setText("");
				jtf_name.setText("");
				jtf_pw.setText("");
				jtf_pw2.setText("");
				jtf_email.setText("");
				jtf_nick.setText("");
				jtf_phone.setText("");
			}
		});
		btn_home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lf.setVisible(true);
				rf.setVisible(false);
			}
		});
	}
	public void checkButtons() {
		if(flag_id && flag_nick) {
			btn_reg.setEnabled(true);
		}
	}
	public void updatecb_date() {
		int year = cb_year.getItemAt(cb_year.getSelectedIndex());
		int month = cb_month.getItemAt(cb_month.getSelectedIndex());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		cb_date.removeAllItems();
		for(int i=1;i<=maxday;i++) {
			cb_date.addItem(i);
		}
	}
}