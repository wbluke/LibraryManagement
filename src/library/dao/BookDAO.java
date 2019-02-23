package library.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import login.bean.MemberDTO;

public class BookDAO {
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String user = "java";
   private String password = "itbank";

   private static BookDAO instance;

   //싱글톤 처리
   public static BookDAO getInstance() {
      synchronized (BookDAO.class) {
         if (instance == null) {
            instance = new BookDAO();
         }
      }
      return instance;
   }

   public BookDAO() {
      //driver loading
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   public Connection getConnection() {
      //connection
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
      String sql = "select seq_books.nextval from dual";

      //prepareStatement 생성
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         rs.next();
         seq = rs.getInt(1);

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

   public void search(DefaultTableModel dtm, String fieldName, String value) {
      //접속 연결
      Connection conn = getConnection();
      String sql = null;
      

      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      switch(fieldName) {
      
      case "전체" :
         sql = "select * from books where code like '%"
               +value+"%' or bookname like '%"
               +value+ "%' or writer like '%"
               +value+"%' or publisher like '%"
               +value+"%'";      
         break;
         
      case "도서명" :
         sql = "select * from books where bookname like ?";
         break;         
         
      case "저자" :
         sql = "select * from books where writer like ?";
         break;            

      case "출판사" :
         sql = "select * from books where publisher like ?";
         break;      
         
      case "장르" :
         sql = "select * from books where code like ?";
         break;   
         
      }
      
      try {
         pstmt = conn.prepareStatement(sql);

         if (fieldName != "전체" ) pstmt.setString(1, "%" + value.trim() + "%");
                  
         rs = pstmt.executeQuery();
         
         // DefaultTableModel에 있는 기존 데이터 지우기
         for (int i = 0; i < dtm.getRowCount();) {
               dtm.removeRow(0);
           }         
         int num = 0;
         
         while (rs.next()) {
              Object data[] = { rs.getInt(1), rs.getString(4), rs.getString(5),
                             rs.getString(6), rs.getString(3)};
              dtm.addRow(data);
              num ++;
         }
         
         if(num ==0) {
            JOptionPane.showMessageDialog(
                  null, "검색한 단어의 책이 없습니다. \n검색을 원하시면 다시 입력해주세요.", "안내", 
                  JOptionPane.WARNING_MESSAGE);
         }
      } catch (SQLException e) {
         System.out.println(e + "=> getUserSearch fail");
      } finally {
         try {
            if (rs != null) rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }// search()

   public void insert(BookDTO bookDTO) {
      Connection conn = getConnection();
      PreparedStatement pstmt = null;
      String sql = "insert into books values(?,?,?,?,?,?,?,?,?)";

      try {
         pstmt = conn.prepareStatement(sql);// 생성
         pstmt.setInt(1, bookDTO.getSeq());
         pstmt.setString(2, bookDTO.getImage());
         pstmt.setString(3, bookDTO.getCode());
         pstmt.setString(4, bookDTO.getBookName());
         pstmt.setString(5, bookDTO.getWriter());
         pstmt.setString(6, bookDTO.getPublisher());  
         pstmt.setString(7, ""); 
         pstmt.setString(8, ""); 
         pstmt.setString(9, "");
         
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
   
   public void update(BookDTO bookDTO) {
      Connection conn = getConnection();
      PreparedStatement pstmt = null;
      String sql = "update books set bookName=?" + ",image=?" + ",code=?" + ",writer=?" + ",publisher=?"
            + ",memberId=?" + ",st=?" + ",en=? where seq=?";

      try {
         pstmt = conn.prepareStatement(sql);// 생성
         pstmt.setInt(1, bookDTO.getSeq());
         pstmt.setString(2,  bookDTO.getImage());
         pstmt.setString(3,bookDTO.getCode());
         pstmt.setString(4, bookDTO.getBookName());
         pstmt.setString(5, bookDTO.getWriter());
         pstmt.setString(6, bookDTO.getPublisher());
         pstmt.setString(7, bookDTO.getMemberId());
         pstmt.setString(8, bookDTO.getSt());
         pstmt.setString(9, bookDTO.getEn());

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

   public void delete(int seq) {
      Connection conn = getConnection();
      PreparedStatement pstmt = null;
      String sql = "delete from books where seq=?";

      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, seq);
         pstmt.executeUpdate();

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

	public ArrayList<BookDTO> searchByName(String targetName) {
		ArrayList<BookDTO> list = new ArrayList<>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from books where bookName like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + targetName + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setImage(rs.getString("image"));
				bookDTO.setCode(rs.getString("code"));
				bookDTO.setBookName(rs.getString("bookname"));
				bookDTO.setWriter(rs.getString("writer"));
				bookDTO.setPublisher(rs.getString("publisher"));
				bookDTO.setMemberId(rs.getString("memberid"));
				bookDTO.setSt(rs.getString("st"));
				bookDTO.setEn(rs.getString("en"));
				list.add(bookDTO);
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