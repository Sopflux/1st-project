package vo;

public class Staff_infoVO {
	private String s_type;
	private String sl_name;
	private String sl_image;
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public String getSl_name() {
		return sl_name;
	}
	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}
	public String getSl_image() {
		return sl_image;
	}
	public void setSl_image(String sl_image) {
		this.sl_image = sl_image;
	}
	public Staff_infoVO(String s_type, String sl_name, String sl_image) {
		super();
		this.s_type = s_type;
		this.sl_name = sl_name;
		this.sl_image = sl_image;
	}
	public Staff_infoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}