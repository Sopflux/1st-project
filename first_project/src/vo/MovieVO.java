package vo;

import java.sql.Date;

public class MovieVO {
	private int m_no;
	private String org_name;
	private String kr_name;
	private String m_date;
	private String m_genre;
	private String m_country;
	private int m_runtime;
	private String m_story;
	private String m_company;
	private String m_image;
	
	
	
	public MovieVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieVO(int m_no, String org_name, String kr_name, String m_date, String m_genre, String m_country,
			int m_runtime, String m_story, String m_company, String m_image) {
		super();
		this.m_no = m_no;
		this.org_name = org_name;
		this.kr_name = kr_name;
		this.m_date = m_date;
		this.m_genre = m_genre;
		this.m_country = m_country;
		this.m_runtime = m_runtime;
		this.m_story = m_story;
		this.m_company = m_company;
		this.m_image = m_image;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
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
	public String getM_country() {
		return m_country;
	}
	public void setM_country(String m_country) {
		this.m_country = m_country;
	}
	public int getM_runtime() {
		return m_runtime;
	}
	public void setM_runtime(int m_runtime) {
		this.m_runtime = m_runtime;
	}
	public String getM_story() {
		return m_story;
	}
	public void setM_story(String m_story) {
		this.m_story = m_story;
	}
	public String getM_company() {
		return m_company;
	}
	public void setM_company(String m_company) {
		this.m_company = m_company;
	}
	public String getM_image() {
		return m_image;
	}
	public void setM_image(String m_image) {
		this.m_image = m_image;
	}
	
	
}