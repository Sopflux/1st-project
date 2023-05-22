package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import db.ConnectionProvider;

public class MainDAO {
	
	// 영화 추천
	public ArrayList<HashMap<String,Object>> Recommendation(String nick){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select distinct m.m_no, m.m_image, m.kr_name\r\n"
				+ "from movie m, likes l where m.m_no = l.m_no and l.a_no in \r\n"
				+ "(select a_no from (select a_no, count(*) like_count from likes l\r\n"
				+ "where m_no in (select m.m_no from movie m, likes l, account a \r\n"
				+ "where m.m_no = l.m_no and a.a_no = l.a_no and a.a_nick = '덕수님')\r\n"
				+ "and a_no != (select a_no from account where a_nick = '덕수님')\r\n"
				+ "group by a_no order by like_count desc) \r\n"
				+ "where rownum <= 3) and l.a_no not in (\r\n"
				+ "select a.a_no from movie m, likes l, account a\r\n"
				+ "where m.m_no = l.m_no and a.a_no = l.a_no and a.a_nick = '덕수님') and rownum <= 5\r\n"
				+ "union\r\n"
				+ "select m_no, m_image, kr_name\r\n"
				+ "from (select m.m_no, m.m_image, m.kr_name, count(*) like_count\r\n"
				+ "from movie m, likes l where m.m_no = l.m_no\r\n"
				+ "group by m.m_no, m.m_image, m.kr_name\r\n"
				+ "order by like_count desc)\r\n"
				+ "where rownum <= 5";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("m_no",rs.getInt(1));
				map.put("m_image", rs.getString(2));
				map.put("kr_name", rs.getString(3));
				
				list.add(map);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	
	
	// 전체 랭킹
	public ArrayList<HashMap<String,Object>> AllRanking(){
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
	
	// 2000년대 랭킹
	public ArrayList<HashMap<String,Object>> get2000sRanking(){
	    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	    String sql = "select rownum, m_no, kr_name, year\r\n"
	    		+ "from ( \r\n"
	    		+ "select m.m_no, kr_name, to_char(m_date, 'yyyy') as year, count(*) as like_count\r\n"
	    		+ "from movie m, likes l\r\n"
	    		+ "where m.m_no = l.m_no and substr(to_char(m_date, 'yyyy'),1,3) = '200'\r\n"
	    		+ "group by m.m_no, kr_name, to_char(m_date, 'yyyy')\r\n"
	    		+ "order by like_count desc, kr_name, m.m_no\r\n"
	    		+ ") where rownum<=5";
	    try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
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
	
	// 2010년대 랭킹
	public ArrayList<HashMap<String,Object>> get2010sRanking(){
	    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	    String sql = "select rownum, m_no, kr_name, year\r\n"
	    		+ "from ( \r\n"
	    		+ "select m.m_no, kr_name, to_char(m_date, 'yyyy') as year, count(*) as like_count\r\n"
	    		+ "from movie m, likes l\r\n"
	    		+ "where m.m_no = l.m_no and substr(to_char(m_date, 'yyyy'),1,3) = '201'\r\n"
	    		+ "group by m.m_no, kr_name, to_char(m_date, 'yyyy')\r\n"
	    		+ "order by like_count desc, kr_name, m.m_no\r\n"
	    		+ ") where rownum<=5";
	    try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
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

	// 2020년대 랭킹
	public ArrayList<HashMap<String,Object>> get2020sRanking(){
	    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	    String sql = "select rownum, m_no, kr_name, year\r\n"
	    		+ "from ( \r\n"
	    		+ "select m.m_no, kr_name, to_char(m_date, 'yyyy') as year, count(*) as like_count\r\n"
	    		+ "from movie m, likes l\r\n"
	    		+ "where m.m_no = l.m_no and substr(to_char(m_date, 'yyyy'),1,3) = '202'\r\n"
	    		+ "group by m.m_no, kr_name, to_char(m_date, 'yyyy')\r\n"
	    		+ "order by like_count desc, kr_name, m.m_no\r\n"
	    		+ ") where rownum<=5";
	    try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
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