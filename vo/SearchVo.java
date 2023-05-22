package vo;
public class SearchVo {
	private String m_image;
	private String kr_name;
	private String m_date;
	private String m_genre;
	private int m_runtime;
	private int m_no;
	
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_image() {
		return m_image;
	}
	public void setM_image(String m_image) {
		this.m_image = m_image;
	}
	public String getKr_name() {
		return kr_name;
	}
	public void setKr_name(String kr_name) {
		this.kr_name = kr_name;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public String getM_genre() {
		return m_genre;
	}
	public void setM_genre(String m_genre) {
		this.m_genre = m_genre;
	}
	public int getM_runtime() {
		return m_runtime;
	}
	public void setM_runtime(int m_runtime) {
		this.m_runtime = m_runtime;
	}
	public SearchVo(String m_image, String kr_name, String m_date, String m_genre, int m_runtime, int m_no) {
		super();
		this.m_image = m_image;
		this.kr_name = kr_name;
		this.m_date = m_date;
		this.m_genre = m_genre;
		this.m_runtime = m_runtime;
		this.m_no=m_no;
	}
	public SearchVo() {
		super();
	}
	
	
}