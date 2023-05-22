package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import db.ConnectionProvider;
import vo.AccountVO;

public class LoginAndRegisterDAO {
	public void insertReason(String reason) {
		String sql = "insert into reasons values(?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reason);
			ResultSet rs = pstmt.executeQuery();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("insertReason 예외발생"+e.getMessage());
		}
	}
	public int updatePwd(int no, int pwd) {
		int re=-1;
		String sql="update account set a_pwd=? where a_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,pwd);
			pstmt.setInt(2,no);
			re = pstmt.executeUpdate();
		ConnectionProvider.close(pstmt, conn);
			
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int updateAccount(int no, String email, String nick, String phone) {
		int re=-1;
		String sql="update account set a_email=?, a_nick=?, a_phone=? where a_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2,nick);
			pstmt.setString(3, phone);
			pstmt.setInt(4,no);
			re = pstmt.executeUpdate();
		ConnectionProvider.close(pstmt, conn);
			
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public ArrayList<Vector<String>> getRanking(int ano){
		ArrayList<Vector<String>> list = new ArrayList<>();
		String sql = "select rownum, a.m_no, kr_name \n"
				+ "from (select m_no from likes where a_no = ?) a,\n"
				+ " (select rownum r, m_no from (select m_no from likes \n"
				+ " group by m_no order by count(m_no) desc)) b , movie m \n"
				+ " where a.m_no = b.m_no and m.m_no = a.m_no order by b.r";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getInt(1)+"");
				v.add(rs.getInt(2)+"");
				v.add(rs.getString(3));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("getRanking 예외발생:"+e.getMessage());
		}
		return list;
	}
	public int updateAccount(int no, String email, String nick, int pwd, String phone) {
		int re=-1;
		String sql="update account set a_email=?, a_nick=?, a_pwd=?, a_phone=? where a_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2,nick);
			pstmt.setInt(3,pwd);
			pstmt.setString(4, phone);
			pstmt.setInt(5,no);
			re = pstmt.executeUpdate();
		ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int deleteAccount(int no) {
		int re=-1;
		String sql = "delete account where a_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public String getNick(int ano) {
		String nick = "";
		String sql = "select a_nick from account where a_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				nick = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("getNick 예외발생:"+e.getMessage());
		}
		return nick;
	}
	
	public ArrayList<String> getGenre(int ano) {
		ArrayList<String> genre = new ArrayList<>();
		String sql = "select m_genre from \n"
				+ "(select m_genre from likes l, movie m where m.m_no=l.m_no and a_no=? \n"
				+ "group by m_genre order by count(m_genre) desc) \n"
				+ "where rownum<=3";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				genre.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("getGenre 예외발생:"+e.getMessage());
		}
		return genre;
	}
	
	public int getAno(String id) {
		String sql = "select a_no from account where a_id=?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				re = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getAno 예외발생:"+e.getMessage());
		}
		return re;
	}
	public int getLogin(String id, int pwd) {
		String sql = "select a_pwd from account where a_id=?";
		int re = -1;			//아이디 없음
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)==pwd) {
					re = 1; 	//로그인 성공
				}else re = 0;	//비밀번호 불일치
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("Login 예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int idDoubleCheck(String id) {
		int re=-1;
		String sql ="select a_id from account where a_id=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(id)) {
					re=1;
				}
			}
		}catch(Exception e) {
			System.out.println("idDoubleCheck 예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int nickDoubleCheck(String nick) {
		int re=-1;
		String sql ="select a_nick from account where a_nick= ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(nick)) {
					re=1;
				}
			}
		}catch(Exception e) {
			System.out.println("nickDoubleCheck 예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int insertAccount(AccountVO a) {
		int re=-1;
		String sql ="insert into account values((select nvl(max(a_no),0)+1 from account),?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getA_id());
			pstmt.setInt(2, a.getA_pwd());
			pstmt.setString(3, a.getA_birth());
			pstmt.setString(4, a.getA_email());
			pstmt.setString(5, a.getA_name());
			pstmt.setString(6, a.getA_nick());
			pstmt.setString(7, a.getA_phone());
			re=pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
			
		}catch(Exception e) {
			System.out.println("insertAccount 예외발생:"+e.getMessage());
		}
		return re;
	}	
	
	public ArrayList<AccountVO> getAccount(int no) {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		String sql = "select * from account where a_no=?";
		try{ 
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next()) {
				
				String id = rs.getString(2);
				int pwd =rs.getInt(3);
				String birth = rs.getString(4);
				String email = rs.getString(5);
				String name = rs.getString(6);
				String nick = rs.getString(7);
				String phone = rs.getString(8);
				AccountVO a = new AccountVO(no,id,pwd,birth,email,name,nick,phone);
				list.add(a);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
}
