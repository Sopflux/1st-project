package vo;

public class ReviewVO {
	private String id;
	private String reveiw;
	private float rate;
	private String date;
	private int count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReveiw() {
		return reveiw;
	}
	public void setReveiw(String reveiw) {
		this.reveiw = reveiw;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ReviewVO(String id, String reveiw, float rate, String date, int count) {
		super();
		this.id = id;
		this.reveiw = reveiw;
		this.rate = rate;
		this.date = date;
		this.count = count;
	}
	public ReviewVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}