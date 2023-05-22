package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.FIlmmoVO;
import vo.MovieVO;
import vo.Staff_listVO;

public class Staff_detailDAO {
	public ArrayList<FIlmmoVO> getFilmmo(int sl_no){
		 ArrayList<FIlmmoVO> list = new  ArrayList<FIlmmoVO>();
		 String sql = "Select to_char(m_date,'yyyy') mdate, m_image, kr_name, s_type, m.m_no from movie m, staff s \n"
		 		+ "where sl_no= ? and m.m_no = s.m_no\n"
		 		+ "order by m_date desc";
		 try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sl_no);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String mdate = rs.getString(1);
					String image = rs.getString(2);
					String name = rs.getString(3);
					String type = rs.getString(4);
					int m_no = rs.getInt(5);
					FIlmmoVO f = new FIlmmoVO(m_no, mdate, image, name, type);
					list.add(f);
				}
				ConnectionProvider.close(rs, pstmt, conn);
				
			} catch (Exception e) {
				System.out.println("예외발생 " + e.getMessage());
			}
		 return list;
	}
	
	public ArrayList<String> getType(int sl_no) {
		ArrayList<String> list= new ArrayList<String>();
		String sql = "SELECT nvl(s_type,'') FROM ( SELECT COUNT(s.sl_no), s_type FROM staff s, staff_list sl "
				+ "  WHERE sl.sl_no = s.sl_no AND s.sl_no = ?"
				+ "  GROUP BY s_type ORDER BY COUNT(s.sl_no))"
				+ "WHERE ROWNUM <= 3";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sl_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String type = rs.getString(1);
				list.add(type);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return list;
		
	}
	
	public ArrayList<Staff_listVO> getSDetail(int sl_no){
		ArrayList<Staff_listVO> list = new ArrayList<Staff_listVO>();
		String sql = "select sl_image, sl_name, sl_country, to_char(sl_birth,'yyyy-mm-dd') bdate "
				+ "from staff_list where sl_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sl_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String image = rs.getString(1);
				String name = rs.getString(2);
				String country = rs.getString(3);
				String bdate= rs.getString(4);
				Staff_listVO sl = new Staff_listVO(sl_no, name, bdate, country, image);
				list.add(sl);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		}
		return list;
	}
}
