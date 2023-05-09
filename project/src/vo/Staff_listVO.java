package vo;

public class Staff_listVO {
	private int sl_no;
	private String sl_name;
	private int sl_birth;
	private String sl_country;
	private String sl_image;
	public int getSl_no() {
		return sl_no;
	}
	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}
	public String getSl_name() {
		return sl_name;
	}
	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}
	public int getSl_birth() {
		return sl_birth;
	}
	public void setSl_birth(int sl_birth) {
		this.sl_birth = sl_birth;
	}
	public String getSl_country() {
		return sl_country;
	}
	public void setSl_country(String sl_country) {
		this.sl_country = sl_country;
	}
	public String getSl_image() {
		return sl_image;
	}
	public void setSl_image(String sl_image) {
		this.sl_image = sl_image;
	}
	public Staff_listVO(int sl_no, String sl_name, int sl_birth, String sl_country, String sl_image) {
		super();
		this.sl_no = sl_no;
		this.sl_name = sl_name;
		this.sl_birth = sl_birth;
		this.sl_country = sl_country;
		this.sl_image = sl_image;
	}
	public Staff_listVO() {
		super();
	}
	
}