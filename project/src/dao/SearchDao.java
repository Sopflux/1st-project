package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectionProvider;
import movie_sketch.SearchVo;

public class SearchDao {
	
	public ArrayList<SearchVo> listCustomer(String title){
		ArrayList<SearchVo> list=new ArrayList<SearchVo>();
		String sql = "select distinct m.m_image, m.kr_name, to_char(m.m_date, 'yyyy'), m.m_genre, m.m_runtime, m.m_no "
	            + "FROM movie m JOIN staff s ON m.m_no = s.m_no JOIN staff_list sl ON s.sl_no = sl.sl_no "
	            + "WHERE m.kr_name LIKE '%" + title + "%' OR sl.sl_name LIKE '%" + title + "%' "
	            + "ORDER BY (SELECT COUNT(*) FROM likes l WHERE m.m_no = l.m_no) DESC, m.kr_name";
		try {
			Connection conn= ConnectionProvider.getConnection();
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
