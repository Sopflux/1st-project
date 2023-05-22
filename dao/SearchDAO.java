package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.SearchVo;

public class SearchDAO {
	
	public ArrayList<SearchVo> listCustomer(String title){
		ArrayList<SearchVo> list=new ArrayList<SearchVo>();
		String sql="select distinct m.m_image, m.kr_name, to_char(m.m_date, 'yyyy'), m.m_genre, m.m_runtime, m.m_no "
				+ "from movie m left join staff s on m.m_no = s.m_no "
				+ "left join staff_list sl on s.sl_no =sl.sl_no "
				+ "where m.kr_name LIKE '%"+title+"%' or sl.sl_name LIKE '%"+title+"%'";
		try {
			Connection conn=ConnectionProvider.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String m_image=rs.getString(1);
				String kr_name=rs.getString(2);
				String m_date=rs.getString(3);
				String m_genre=rs.getString(4);
				int m_runtime=rs.getInt(5);
				int m_no=rs.getInt(6);
				
				SearchVo vo=new SearchVo(m_image, kr_name, m_date, m_genre, m_runtime, m_no);
				list.add(vo);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}