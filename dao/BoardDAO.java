
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import db.ConnectionProvider;
import vo.BoardVO;

public class BoardDAO {
	public ArrayList<HashMap<String, String>> getComment(int b_no){
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		String sql = "select a_nick, c_contents "
				+ "from comments c, account a, board b "
				+ "where a.a_no=c.a_no and c.b_no=b.b_no and c.b_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("닉네임", rs.getString(1));
				map.put("내용", rs.getString(2));
				list.add(map);
			}
			ConnectionProvider.close(rs, pstmt, conn);;
		} catch (Exception e) {
			System.out.println("getComment 예외발생:"+e.getMessage());
		}
		return list;
	}

	public int insertcomment(int a_no, int b_no, String comments) {
		int re = -1;
		String sql = "insert into comments (a_no, b_no, c_contents) values (?,?,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_no);
			pstmt.setInt(2, b_no);
			pstmt.setString(3, comments);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);;
		} catch (Exception e) {
			System.out.println("insertcomment 예외발생:"+e.getMessage());
		}
		return re;
	}

	 public ArrayList<HashMap<String, String>> getBoard(int b_no) {
	      ArrayList<HashMap<String, String>> list = new ArrayList<>();
	      String sql = "select b_title, b_contents from board where b_no=?";
	      try {
	         Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, b_no);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            HashMap<String, String> map = new HashMap<>();
	            map.put("제목", rs.getString(1));
	            map.put("내용", rs.getString(2));
	            list.add(map);
	         }
	         ConnectionProvider.close(rs, pstmt, conn);;
	      } catch (Exception e) {
	         System.out.println("getBoard 예외발생:"+e.getMessage());
	      }
	      return list;
	   }

	public int insertBoard(BoardVO b) {
		System.out.println(b.getA_no());
		int re=-1;
		String sql="insert into board values((select nvl(max(b_no),0)+1 from board),?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getA_no());
			pstmt.setString(2, b.getB_title());
			pstmt.setString(3, b.getB_contents());
			re= pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);;
			
		}catch(Exception e) {
			System.out.println("insertBoard 예외발생:"+e.getMessage());
		}
		return re;
	}

	public ArrayList<BoardVO> listBoard(){
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		String sql="select a_nick, b_title, b_date, b_no, a.a_no "
				+ "from board b, account a where a.a_no=b.a_no";
		try {
			Connection conn=ConnectionProvider.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String a_nick=rs.getString(1);
				String b_title=rs.getString(2);
				String b_date=rs.getString(3);
				int b_no=rs.getInt(4);
				int a_no=rs.getInt(5);
				
				BoardVO vo=new BoardVO(a_nick, b_title, b_date, b_no, a_no);
				list.add(vo);
			}
			ConnectionProvider.close(rs,stmt,conn);
		} catch (Exception e) {
			System.out.println("listBoard"+e.getMessage());
		}
		return list;
	}
	public ArrayList<BoardVO> searchtitle(String search_word){
		ArrayList<BoardVO> title_list=new ArrayList<BoardVO>();
		String sql="select a_nick, b_title, b_date, b_no, a.a_no from board b, account a "
		+ "where a.a_no=b.a_no and b_title like '%"+search_word+"%'";
		try {
		Connection conn=ConnectionProvider.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);

		while(rs.next()) {
		String a_nick=rs.getString(1);
		String b_title=rs.getString(2);
		String b_date=rs.getString(3);
		int b_no=rs.getInt(4);
		int a_no=rs.getInt(5);

		BoardVO vo=new BoardVO(a_nick, b_title, b_date, b_no, a_no);
		title_list.add(vo);

		}
		ConnectionProvider.close(rs,stmt,conn);
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}

		return title_list;
		}
	
	public ArrayList<BoardVO> searchnick(String search_word){
		ArrayList<BoardVO> nick_list=new ArrayList<BoardVO>();
		String sql="select a_nick, b_title, b_date from board b, account a "
				+ "where a.a_no=b.a_no and a_nick like '%"+search_word+"%'";
		try {
			Connection conn=ConnectionProvider.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String a_nick=rs.getString(1);
				String b_title=rs.getString(2);
				String b_date=rs.getString(3);
				
				BoardVO vo=new BoardVO(a_nick, b_title, b_date);
				nick_list.add(vo);
				
			}
			ConnectionProvider.close(rs,stmt,conn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return nick_list;
	}
}