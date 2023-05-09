package vo;

public class FIlmmoVO {
	private int m_no;
	private String mdate;
	private String image;
	private String name;
	private String s_type;
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public FIlmmoVO(int m_no, String mdate, String image, String name, String s_type) {
		super();
		this.m_no = m_no;
		this.mdate = mdate;
		this.image = image;
		this.name = name;
		this.s_type = s_type;
	}
	public FIlmmoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
