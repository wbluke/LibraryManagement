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

   //�̱��� ó��
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

      //prepareStatement ����
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
      //���� ����
      Connection conn = getConnection();
      String sql = null;
      

      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      switch(fieldName) {
      
      case "��ü" :
         sql = "select * from books where code like '%"
               +value+"%' or bookname like '%"
               +value+ "%' or writer like '%"
               +value+"%' or publisher like '%"
               +value+"%'";      
         break;
         
      case "������" :
         sql = "select * from books where bookname like ?";
         break;         
         
      case "����" :
         sql = "select * from books where writer like ?";
         break;            

      case "���ǻ�" :
         sql = "select * from books where publisher like ?";
         break;      
         
      case "�帣" :
         sql = "select * from books where code like ?";
         break;   
         
      }
      
      try {
         pstmt = conn.prepareStatement(sql);

         if (fieldName != "��ü" ) pstmt.setString(1, "%" + value.trim() + "%");
                  
         rs = pstmt.executeQuery();
         
         // DefaultTableModel�� �ִ� ���� ������ �����
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
                  null, "�˻��� �ܾ��� å�� �����ϴ�. \n�˻��� ���Ͻø� �ٽ� �Է����ּ���.", "�ȳ�", 
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
         pstmt = conn.prepareStatement(sql);// ����
         pstmt.setInt(1, bookDTO.getSeq());
         pstmt.setString(2, bookDTO.getImage());
         pstmt.setString(3, bookDTO.getCode());
         pstmt.setString(4, bookDTO.getBookName());
         pstmt.setString(5, bookDTO.getWriter());
         pstmt.setString(6, bookDTO.getPublisher());  
         pstmt.setString(7, ""); 
         pstmt.setString(8, ""); 
         pstmt.setString(9, "");
         
         pstmt.executeUpdate();// ����

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
         pstmt = conn.prepareStatement(sql);// ����
         pstmt.setInt(1, bookDTO.getSeq());
         pstmt.setString(2,  bookDTO.getImage());
         pstmt.setString(3,bookDTO.getCode());
         pstmt.setString(4, bookDTO.getBookName());
         pstmt.setString(5, bookDTO.getWriter());
         pstmt.setString(6, bookDTO.getPublisher());
         pstmt.setString(7, bookDTO.getMemberId());
         pstmt.setString(8, bookDTO.getSt());
         pstmt.setString(9, bookDTO.getEn());

         pstmt.executeUpdate();// ����

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