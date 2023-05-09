package movie_sketch;

import java.awt.BorderLayout;
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

public class UpdatePwd extends JFrame{
	JTextField jtf_pw;
	JTextField jtf_pwc;
	JTextField jtf_pwc2;
	JButton btn_com;
	UpdateAccountFrame updatef;
	UpdatePwd updatep;
	int a_no = LoginFrame.a_no;
	
	public UpdatePwd(UpdateAccountFrame updatef) {
		updatep = this;
		this.updatef = updatef;
		jtf_pw = new JTextField(10);
		jtf_pwc = new JTextField(10);
		jtf_pwc2 = new JTextField(10);
		JPanel p_pw = new JPanel(new GridLayout(1,2));
		JPanel p_pwc = new JPanel(new GridLayout(1,2));
		JPanel p_pwc2 = new JPanel(new GridLayout(1,2));
		p_pw.add(new JLabel("현재비밀번호"));
		p_pw.add(jtf_pw);
		p_pwc.add(new JLabel("비밀번호 수정(숫자4자리)"));
		p_pwc.add(jtf_pwc);	
		p_pwc2.add(new JLabel("비밀번호 확인"));
		p_pwc2.add(jtf_pwc2);
		btn_com = new JButton("완료");
		
		JPanel p_center = new JPanel(new GridLayout(3,1));
		p_center.add(p_pw);
		p_center.add(p_pwc);
		p_center.add(p_pwc2);
		
		JPanel btn = new JPanel();
		btn.add(btn_com);
		
		setLayout(new BorderLayout());
		add(p_center,BorderLayout.CENTER);
		add(btn,BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,150);
		
		btn_com.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
				ArrayList<AccountVO> list = dao.getAccount(a_no);
				AccountVO a = list.get(0);
				int nowpw = a.getA_pwd();
				int pwd = Integer.parseInt(jtf_pw.getText());
				if(pwd!=nowpw) {
					JOptionPane.showMessageDialog(null, "입력하신 현재비밀번호와 계정의 비밀번호가 다릅니다.");
					return;
				}
				String pwcc2 = jtf_pwc2.getText();
				if(pwcc2.length()==0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
					return;
				}
				int pwc = 0000;
				int pwc2 = 0000;
				try {
					pwc = Integer.parseInt(jtf_pwc.getText());
					pwc2 = Integer.parseInt(jtf_pwc2.getText());
				} catch (Exception e2) {
					System.out.println("예외발생: "+e2.getMessage());
					JOptionPane.showMessageDialog(null, "비밀번호는 숫자 4자리입니다.");
					return;
				}
				
				if(jtf_pwc.getText().length()!=4) {
					JOptionPane.showMessageDialog(null, "비밀번호는 숫자 4자리입니다.");
					return;
				}
				if(pwc != pwc2) {
					JOptionPane.showMessageDialog(null, "수정한 비밀번호와 비밀번호 확인값이 다릅니다.");
					return;
				}
				
				if(pwc == pwd) {
					JOptionPane.showMessageDialog(null, "수정 비밀번호와 현재 비밀번호의 값이 동일합니다.");
					return;
				}
				int choice = JOptionPane.showConfirmDialog(null, "비밀번호를 수정하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(choice==0) {
					int r = dao.updatePwd(a_no, pwc);
					if(r>0) {
						JOptionPane.showMessageDialog(null, "비밀번호 수정을 완료하였습니다.");
					}
				}
				updatep.setVisible(false);
				updatef.setVisible(true);
			}
		});
	}
}