package movie_sketch;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LoginAndRegisterDAO;



public class DeleteReasonFrame extends JFrame {
	
	UpdateAccountFrame updatef;
	DeleteReasonFrame drf;
	LoginFrame lf;
	
	JButton jb_skip;
	JButton jb_submit;
	int a_no = LoginFrame.a_no;
	LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
	public DeleteReasonFrame(UpdateAccountFrame updatef, LoginFrame lf){
		
		drf = this;
		this.lf = lf;
		this.updatef = updatef;
		JComboBox<String> cb_reasons = new JComboBox<>();
		String[] reason = {"다른 사이트 사용", "더이상 사용하지 않음", "정보 부족", "이유 없음"};
		for(String r:reason) {
			cb_reasons.addItem(r);
		}
		jb_skip = new JButton("건너뛰기");
		jb_submit = new JButton("제출");
		
		JPanel t = new JPanel();
		t.add(cb_reasons);
		JPanel r = new JPanel();
		r.setLayout(new GridLayout(2,1));
		r.add(new JLabel("    회원탈퇴의 이유가 무엇입니까?"));
		r.add(t);
		
		JPanel b = new JPanel();
		b.add(jb_skip);
		b.add(jb_submit);
		setLayout(new BorderLayout());
		add(r, BorderLayout.CENTER);
		add(b, BorderLayout.SOUTH);
		
		setSize(400,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jb_skip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAndRegisterDAO dao = new LoginAndRegisterDAO();
				int r = dao.deleteAccount(a_no);
				if(r>0) {
					JOptionPane.showMessageDialog(null, "MS회원탈퇴를 완료하였습니다.");
					LoginFrame lf = new LoginFrame();
					drf.dispose();
					lf.jtf_id.setText("");
					lf.jtf_pwd.setText("");
					lf.setVisible(true);
					lf.invalidate();
					lf.validate();
					lf.repaint();
				}
			}
		});
		
		jb_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = dao.deleteAccount(a_no);
				if(r>0) {
					String reason = (String)cb_reasons.getSelectedItem();
					dao.insertReason(reason);
					JOptionPane.showMessageDialog(null, "MS회원탈퇴를 완료하였습니다.");
					LoginFrame lf = new LoginFrame();
					drf.dispose();
					lf.jtf_id.setText("");
					lf.jtf_pwd.setText("");
					lf.setVisible(true);
					lf.invalidate();
					lf.validate();
					lf.repaint();
				}
			}
		});
	}
}