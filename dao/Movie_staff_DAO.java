package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.MovieVO;
import vo.ReviewVO;
import vo.Staff_infoVO;
import vo.Staff_listVO;

public class Movie_staff_DAO {
	public int addreview(String contents, int rate,int m_no, int a_no) {
		int re = -1;
		String sql = "insert into review values((select max(r_no)+1 from review),?,?,sysdate,0,?,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contents);
			pstmt.setFloat(2, rate);
			pstmt.setInt(3, m_no);
			pstmt.setInt(4, a_no);
			re = pstmt.executeUpdate();
//			System.out.println("0");
			ConnectionProvider.close(pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
//			System.out.println("3");
		}
		return re;
	}
	
	
	public int checkLike(int a_no, int m_no) {
		int re = -1;
		String sql = "select * from likes where a_no = ? and m_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_no);
			pstmt.setInt(2, m_no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				re = 1;
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return re;
	}
	public int delLike(int a_no , int m_no) {
		int re = -1;
		String sql ="delete likes where a_no = ? and m_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_no);
			pstmt.setInt(2, m_no);
			re = pstmt.executeUpdate();
			System.out.println("1");
			ConnectionProvider.close(pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return re;
	}
	
	public int addLike(int a_no, int m_no) {
		int re = -1;
		String sql = "insert into likes values(?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_no);
			pstmt.setInt(2, m_no);
			re = pstmt.executeUpdate();
			System.out.println("0");
			ConnectionProvider.close(pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return re;
	}
	
	public int getLike(int m_no) {
		int likes = 0; 
		String sql = "select count(*) from likes where m_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				likes = rs.getInt(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return likes;
	}
	
	public String getStory(int m_no) {
		String story = "";
		String sql = "select m_story from movie where m_no = ? ";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				story = rs.getString(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		
		
		return story;
	}
	
	public ArrayList<MovieVO> getmoviedetail(int m_no){
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		
		String sql = "select m_image, kr_name, org_name, to_char(m_date,'yyyy') mdate,"
				+ "m_genre, m_country, m_runtime, m_company "
				+ " from movie where m_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String image = rs.getString(1);
				String kr_name = rs.getString(2);
				String org_name = rs.getString(3);
				String date= rs.getString(4);
				String genre = rs.getString(5);
				String country = rs.getString(6);
				int runtime = rs.getInt(7);
				String company = rs.getString(8);
				MovieVO m = new MovieVO(0, org_name, kr_name, date, genre, country, runtime, null, company, image);
				list.add(m);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		
		return list;
	}
	
	public ArrayList<ReviewVO> getreview(int m_no){
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "select a.a_id, r_review, r_rate, to_char(r_date,'yyyy-mm-dd'),r_count "
				+ "from review r , account a "
				+ "where r.a_no = a.a_no and r.m_no = ? ";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String review = rs.getString(2);
				int rate = rs.getInt(3);
				String date = rs.getString(4);
				int count = rs.getInt(5);
				ReviewVO rv = new ReviewVO(id, review, rate, date, count);
				list.add(rv);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		return list;
	}
	
	
	public ArrayList<Staff_infoVO> getstaff(int m_no){
		ArrayList<Staff_infoVO> list = new ArrayList<Staff_infoVO>();
		String sql = "select  distinct s_type, sl_name,  sl_image,s.sl_no "
				+ "from staff_list s , staff t "
				+ "where s.sl_no = t.sl_no and m_no = ? order by s_type,sl_name ";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String s_type = rs.getString(1);
				String sl_name = rs.getString(2);
				String sl_image = rs.getString(3);
				int sl_no = rs.getInt(4);
				
				Staff_infoVO ls = new Staff_infoVO(s_type, sl_name, sl_image,sl_no);
				list.add(ls);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		
		return list;
	}
}
