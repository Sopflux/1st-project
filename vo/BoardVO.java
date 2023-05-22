package vo;

public class BoardVO {
	private int b_no;
	private int a_no;
	private String b_title;
	private String b_contents;
	private String b_date;
	private String a_nick;

	public BoardVO() {
		super();
	}
	public BoardVO(int b_no, int a_no, String b_title, String b_contents, String b_date) {
		super();
		this.b_no = b_no;
		this.a_no = a_no;
		this.b_title = b_title;
		this.b_contents = b_contents;
		this.b_date = b_date;
	}
	
	public BoardVO(String a_nick, String b_title, String b_date, int b_no, int a_no) {
		super();
		this.a_nick = a_nick;
		this.b_title = b_title;
		this.b_date = b_date;
		this.b_no=b_no;
		this.a_no=a_no;
	}
	public BoardVO(String a_nick, String b_title, String b_date) {
		super();
		this.a_nick = a_nick;
		this.b_title = b_title;
		this.b_date = b_date;

	}

	public String getA_nick() {
		return a_nick;
	}
	public void setA_nick(String a_nick) {
		this.a_nick = a_nick;
	}

	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_contents() {
		return b_contents;
	}
	public void setB_contents(String b_contents) {
		this.b_contents = b_contents;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	
}