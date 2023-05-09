package movie_sketch;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LoginAndRegisterDAO;
import vo.AccountVO;

public class UpdateAccountFrame extends JFrame {
	JLabel jl_id;
	JLabel jl_name;
	JTextField jtf_email;
	JTextField jtf_nick;
	JTextField jtf_phone;
	JButton btn_com;
	
	LoginFrame lf;
	UpdateAccountFrame updatef;
	MyPageFrame myf;
	DeleteReasonFrame drf;
	int a_no = LoginFrame.a_no;
	LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
	
	public UpdateAccountFrame(MyPageFrame myf) {
		updatef = this;
		this.myf = myf;
		
		setTitle("MS 회원정보 수정");
		jl_id = new JLabel();
		jl_name = new JLabel();
		jtf_email = new JTextField(10);
		jtf_nick = new JTextField(10);
		jtf_phone = new JTextField(10);
		JPanel p_name = new JPanel(new GridLayout(1,2));
		JPanel p_id = new JPanel(new GridLayout(1,2));
		JPanel p_nick = new JPanel(new GridLayout(1,2));
		JPanel p_email = new JPanel(new GridLayout(1,2));
		JPanel p_phone = new JPanel(new GridLayout(1,2));
		
		p_name.add(new JLabel("이름"));
		p_name.add(jl_name);
		p_id.add(new JLabel("아이디"));
		p_id.add(jl_id);
		p_email.add(new JLabel("이메일"));
		p_email.add(jtf_email);
		p_nick.add(new JLabel("닉네임"));
		p_nick.add(jtf_nick);
		p_phone.add(new JLabel("전화번호"));
		p_phone.add(jtf_phone);
		
		JPanel btn = new JPanel();
		btn_com = new JButton("완료");
		JButton btn_pwd = new JButton("비밀번호수정");
		JButton btn_delete = new JButton("회원탈퇴");
		btn.add(btn_com);
		btn.add(btn_pwd);
		btn.add(btn_delete);
		
		JPanel p_center = new JPanel(new GridLayout(6,1));
		p_center.add(p_name);
		p_center.add(p_id);
		p_center.add(p_nick);
		p_center.add(p_email);
		p_center.add(p_phone);
		
		add(p_center,BorderLayout.CENTER);
		add(btn,BorderLayout.SOUTH);
		
		ArrayList<AccountVO> list = dao.getAccount(a_no);
		AccountVO a = list.get(0);
		jl_name.setText(a.getA_name());
		jl_id.setText(a.getA_id());
		jtf_nick.setText(a.getA_nick());
		jtf_email.setText(a.getA_email());
		jtf_phone.setText(a.getA_phone());
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,250);
		btn_pwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdatePwd updatep = new UpdatePwd(updatef);
				updatep.setVisible(true);
				updatef.setVisible(false);
			}
		});
		
		
		btn_com.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nick = jtf_nick.getText();
				String email = jtf_email.getText();
				String phone = jtf_phone.getText();
				ArrayList<AccountVO> list = dao.getAccount(a_no);
				AccountVO a = list.get(0);
				String nownick = a.getA_nick();
				
				if(nick.length()==0) {
					JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요.");
					return;
				}
				
				if(!nick.equals(nownick)) {
					int r = dao.nickDoubleCheck(nick);
					if(r==1) {
						JOptionPane.showMessageDialog(null,"이미 사용중인 닉네임입니다." );
						return;
					}
				}
				int choice = JOptionPane.showConfirmDialog(null, "회원정보를 수정하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(choice==0) {
					int r = dao.updateAccount(a_no, email, nick, phone);
					if(r>0) {
						JOptionPane.showMessageDialog(null, "회원정보 수정을 완료하였습니다.");
					}
				}
				// 페이지 이동 넣어야함.
			}
		});
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int choice = JOptionPane.showConfirmDialog(null, "MS회원탈퇴를 하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(choice==0) {
					DeleteReasonFrame drf = new DeleteReasonFrame(updatef, lf);
					drf.setVisible(true);
					updatef.setVisible(false);
					
				}
			}
		});
	}
}