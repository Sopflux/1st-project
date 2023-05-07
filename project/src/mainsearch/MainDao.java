package mainsearch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import db.ConnectionProvider;

public class MainDao {
	public ArrayList<HashMap<String,Object>> Ranking(){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select rownum, m_no, kr_name \r\n"
				+ "from (select kr_name, m.m_no,count(*) like_count \r\n"
				+ "from movie m, likes l where m.m_no = l.m_no \r\n"
				+ "group by kr_name, m.m_no order by like_count desc )\r\n"
				+ "where rownum<=20";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			// DB를 HashMap에 넣고, ArrayList로 저장하기
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("rownum",rs.getInt(1));
				map.put("m_no", rs.getInt(2));
				map.put("kr_name", rs.getString(3));
				
				list.add(map);
			}
			ConnectionProvider.close(rs, stmt, conn);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
}
