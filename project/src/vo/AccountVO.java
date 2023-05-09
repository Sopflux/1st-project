package vo;

import java.sql.Date;

public class AccountVO {
	private int a_no;
	private String a_id;
	private int a_pwd;
	private String a_birth;
	private String a_email;
	private String a_name;
	private String a_nick;
	private String a_phone;
	
	
	public AccountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountVO(int a_no, String a_id, int a_pwd, String a_birth, String a_email, String a_name, String a_nick,
			String a_phone) {
		super();
		this.a_no = a_no;
		this.a_id = a_id;
		this.a_pwd = a_pwd;
		this.a_birth = a_birth;
		this.a_email = a_email;
		this.a_name = a_name;
		this.a_nick = a_nick;
		this.a_phone = a_phone;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public int getA_pwd() {
		return a_pwd;
	}
	public void setA_pwd(int a_pwd) {
		this.a_pwd = a_pwd;
	}
	public String getA_birth() {
		return a_birth;
	}
	public void setA_birth(String a_birth) {
		this.a_birth = a_birth;
	}
	public String getA_email() {
		return a_email;
	}
	public void setA_email(String a_email) {
		this.a_email = a_email;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_nick() {
		return a_nick;
	}
	public void setA_nick(String a_nick) {
		this.a_nick = a_nick;
	}
	public String getA_phone() {
		return a_phone;
	}
	public void setA_phone(String a_phone) {
		this.a_phone = a_phone;
	}
	
	

	
	

}