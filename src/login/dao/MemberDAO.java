package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import library.bean.BookDTO;
import login.bean.MemberDTO;

public class MemberDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.51.87:1521:xe";
	private String user = "java";
	private String password = "itbank";
	private static MemberDAO instance;

	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int getSeq() {
		int seq = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select seq_members.nextval from dual";

		try {
			pstmt = conn.prepareStatement(sql);// 생성
			rs = pstmt.executeQuery();

			rs.next();
			seq = rs.getInt(1);// 시퀀스 객체는 무조건 1부터 시작. 0이 될 수 없음.
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return seq;
	}


	public static MemberDAO getInstance() {
		synchronized (MemberDAO.class) {
			if (instance == null) {
				instance = new MemberDAO();
			}
		}
		
		return instance;
	}
	
	public boolean checkID(String target_id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if (rs.getString("memberID").equals(target_id)) {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return true;
	}

	public void join(MemberDTO memberDTO) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setInt(1, memberDTO.getSeq());
			pstmt.setString(2, memberDTO.getMemberName());
			pstmt.setString(3, memberDTO.getMemberId());
			pstmt.setString(4, memberDTO.getPw());
			pstmt.setInt(5, memberDTO.getGender());
			pstmt.setString(6, memberDTO.getAddress());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getEmail());
			pstmt.setInt(11, memberDTO.getOverdue());

			pstmt.executeUpdate();// 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<MemberDTO> searchByName(String targetName) {
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where memberName like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + targetName + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO memberDTO = new MemberDTO();
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
				list.add(memberDTO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public MemberDTO searchByID(String memberId) {
		MemberDTO memberDTO = new MemberDTO();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where memberID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return memberDTO;
	}

	public ArrayList<MemberDTO> searchByBlackList() {
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where overdue >= 3";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO memberDTO = new MemberDTO();
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
				list.add(memberDTO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}   

}
