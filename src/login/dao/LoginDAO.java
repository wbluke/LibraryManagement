package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.bean.MemberDTO;

public class LoginDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.51.87:1521:xe";
	private String user = "java";
	private String password = "itbank";

	private static LoginDAO instance;

	public static LoginDAO getInstance() {
		synchronized (LoginDAO.class) {
			if(instance == null) {
				instance = new LoginDAO();
			}
		}
		return instance;
	}
	
	public LoginDAO() {	
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} //LoginDAO() 종료
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	} //getConnection() 종료
	
	//로그인 체크
	public MemberDTO loginCheck(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO memberDTO = null;
		
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT * FROM members WHERE memberId = '");
		sbSql.append(id);
		sbSql.append("' AND pw = '");
		sbSql.append(pw);
		sbSql.append("'");
		
		try {
			//System.out.println("SQL : " + sbSql.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setSeq(rs.getInt("seq"));
				memberDTO.setMemberName(rs.getString("memberName"));
				memberDTO.setMemberId(rs.getString("memberId"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setGender(rs.getInt("gender"));
				memberDTO.setAddress(rs.getString("address"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setOverdue(rs.getInt("overdue"));
				
				//System.out.println("memberId : " + rs.getString("memberId"));
				//System.out.println("pw : " + rs.getString("pw"));
				//break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println(ee.toString());
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	} //loginCheck 종료
	
	public MemberDTO loginCheckAdmin(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO memberDTO = null;
		
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT adminId, pw FROM admin WHERE adminId = '");
		sbSql.append(id);
		sbSql.append("' AND pw = '");
		sbSql.append(pw);
		sbSql.append("'");
		
		try {
			//System.out.println("SQL : " + sbSql.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setMemberId(rs.getString("adminId"));
				memberDTO.setPw(rs.getString("pw"));
				//System.out.println("memberId : " + rs.getString("memberId"));
				//System.out.println("pw : " + rs.getString("pw"));
				//break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println(ee.toString());
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	} //loginCheck 종료
	
	//아이디 찾기
	public String idFind(String name, String tel1, String tel2, String tel3) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id_Find = null;
		
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT memberId FROM members WHERE memberName = '");
		sbSql.append(name);
		sbSql.append("' AND tel1 = '");
		sbSql.append(tel1);
		sbSql.append("' AND tel2 = '");
		sbSql.append(tel2);
		sbSql.append("' AND tel3 = '");
		sbSql.append(tel3);
		sbSql.append("'");
		try {
			//System.out.println("SQL : " + sbSql.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id_Find = rs.getString("memberId");
				//System.out.println("memberID : " + id_Find);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println(ee.toString());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id_Find;
	}
	
	//비밀번호 찾기
	public String pwFind(String id, String name, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pw_Find = null;
		
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT pw FROM members WHERE memberId = '");
		sbSql.append(id);
		sbSql.append("' AND memberName = '");
		sbSql.append(name);
		sbSql.append("' AND email = '");
		sbSql.append(email);
		sbSql.append("'");
		
		try {
			//System.out.println("SQL : " + sbSql.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pw_Find = rs.getString("pw");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println(ee.toString());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pw_Find;
	}
}