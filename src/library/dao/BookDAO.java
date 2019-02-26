package library.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import login.bean.MemberDTO;

public class BookDAO {
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String user = "java";
   private String password = "itbank";
   
   private DefaultTableModel dtm;
   private ResultSet rs;

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

   public DefaultTableModel searchElement(DefaultTableModel dtm, String fieldName, String value) {
	      this.dtm = dtm;
	      
	      //���� ����
	      Connection conn = getConnection();
	      String sql = null;
	      

	      PreparedStatement pstmt = null;
	      //ResultSet rs = null;
	      
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
	        	 String borrowed = null;
		         
		         if (rs.getString("st") == null) {
		        	 //borrowed = "�뿩 ����";
		         }else {
		        	 borrowed = "�뿩 �Ұ�";	        	 
		         }
		         
	              Object data[] = {Boolean.FALSE, rs.getInt(1), rs.getString(4), rs.getString(5),
	                             rs.getString(6), rs.getString(3), borrowed};
	                  
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
	      return dtm;


	   }// search()
   
   public ArrayList<BookDTO> searchElement(String fieldName, String value) {
	   ArrayList<BookDTO> list = new ArrayList<>();
	      //���� ����
	      Connection conn = getConnection();
	      String sql = null;

	      PreparedStatement pstmt = null;
	      //ResultSet rs = null;
	      
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
	         
	         while (rs.next()) {
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
	      return list;


	   }// ������ �� 1 �˻��� ��� 
   
   public ArrayList<BookDTO> cart() {
	      Connection conn = getConnection();
	      PreparedStatement pstmt = null;

	      
	      ArrayList<Integer> list = new ArrayList<Integer>();
	      //BookDTO bookDTOSeq = new BookDTO();
	      //System.out.println(dtm.getRowCount());
	      
	      for (int i = 0; i < dtm.getRowCount() ; i++) {
	         //System.out.println("1");
	         Boolean CartCheck = Boolean.valueOf(dtm.getValueAt(i, 0).toString());
	         int cartSeq = (int) dtm.getValueAt(i, 1);

	         if (CartCheck) {
	            //System.out.println(cartSeq);
	        	 
	        	 
	        	 // �뿩 �Ұ� �׸����� �Ǵ��ϱ�
	        	 if (searchByBookSeq(cartSeq).getSt() != null) {
	        		 JOptionPane.showMessageDialog(
	   	                  null, "���� �׸� �� �뿩�Ұ� �׸��� �ֽ��ϴ�.\nüũ�� �׸��� Ȯ�����ּ���.", "�ȳ�", 
	   	                  JOptionPane.WARNING_MESSAGE);
	        		 return null;
	        	 }else {
	        		 list.add(cartSeq);	        		 
	        	 }
	         }
	      }

	      
	      ArrayList<BookDTO> cartList = new ArrayList<BookDTO>();


	      for(int i = 0; i < list.size();i++) {

	         
	         try {
	            conn = getConnection();
	            pstmt = null;
	            
	            String sql = "select * from books where seq =?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, list.get(i));
	            
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	               BookDTO bookDTO = new BookDTO();   
	               bookDTO.setSeq(rs.getInt("seq"));
	               bookDTO.setImage(rs.getString("image"));
	               bookDTO.setCode(rs.getString("code"));
	               bookDTO.setWriter(rs.getString("writer"));
	               bookDTO.setBookName(rs.getString("bookName"));
	               bookDTO.setMemberId(rs.getString("memberId"));
	               bookDTO.setPublisher(rs.getString("publisher"));
	               bookDTO.setSt(rs.getString("st"));
	               bookDTO.setEn(rs.getString("en"));

	               cartList.add(bookDTO);
	            } // while

	         } catch (SQLException e) {
	            e.printStackTrace();
	            list = null;
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
	      }
	      return cartList;      
	   }
	   

   public BookDTO searchByBookSeq(int cartSeq) {
	   BookDTO bookDTO = null;
	   
	   Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from books where seq=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartSeq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setImage(rs.getString("image"));
				bookDTO.setCode(rs.getString("code"));
				bookDTO.setBookName(rs.getString("bookname"));
				bookDTO.setWriter(rs.getString("writer"));
				bookDTO.setPublisher(rs.getString("publisher"));
				bookDTO.setMemberId(rs.getString("memberid"));
				bookDTO.setSt(rs.getString("st"));
				bookDTO.setEn(rs.getString("en"));
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
	   
	   return bookDTO;
   }

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
   
   public void updatePartsOfBookInfo(BookDTO bookDTO) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
//     String sql = "update books set bookName=?" + ",image=?" + ",code=?" + ",writer=?" + ",publisher=?"
//           + ",memberId=?" + ",st=?" + ",en=? where seq=?";
		// �̹��� �� , en, �帣 ������,
		String sql = "update books set code=?, bookName=?, writer=?, publisher=? where seq=?";

		try {
			pstmt = conn.prepareStatement(sql);// ����
			pstmt.setString(1, bookDTO.getCode());
			pstmt.setString(2, bookDTO.getBookName());
			pstmt.setString(3, bookDTO.getWriter());
			pstmt.setString(4, bookDTO.getPublisher());
			pstmt.setInt(5, bookDTO.getSeq());

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

	public void updateBorrowInfo(int targetBookSeq, String targetID, String now, String until) {
		Connection conn = getConnection();
	      PreparedStatement pstmt = null;
	      String sql = "update books set memberId=?, st=?, en=? where seq=?";

	      try {
	         pstmt = conn.prepareStatement(sql);// ����
	         pstmt.setString(1, targetID);
	         pstmt.setString(2,  now);
	         pstmt.setString(3, until);
	         pstmt.setInt(4, targetBookSeq);

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

	public void updateReturnInfo(int targetBookSeq) {
		
		Connection conn = getConnection();
	      PreparedStatement pstmt = null;
	      String sql = "update books set memberId='', st='', en='' where seq=?";

	      try {
	         pstmt = conn.prepareStatement(sql);// ����
//	         pstmt.setString(1, targetID);
//	         pstmt.setString(2,  now);
//	         pstmt.setString(3, until);
	         pstmt.setInt(1, targetBookSeq);

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

	public ArrayList<BookDTO> searchBorrowListByToday() {
		ArrayList<BookDTO> list = new ArrayList<>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String now = formatter.format(new Date());
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from books where en=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, now);
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

	public ArrayList<BookDTO> searchBorrowListUntilYesterday() {
		ArrayList<BookDTO> list = new ArrayList<>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String now = formatter.format(new Date());
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from books where en is not NULL";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long diffDays = 0;
				try {
					Date beginDate = formatter.parse(rs.getString("en"));
					Date endDate = formatter.parse(now);
					
					// �ð����̸� �ð�,��,�ʸ� ���� ������ ������ �Ϸ� ������ ����
					long diff = endDate.getTime() - beginDate.getTime();
					diffDays = diff / (24 * 60 * 60 * 1000);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (diffDays >= 1) {
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

	public ArrayList<BookDTO> searchByID(String targetID) {
		ArrayList<BookDTO> list = new ArrayList<>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from books where memberid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, targetID);
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

	public void updateBorrowInfoWithList(ArrayList<Integer> seqList, String targetID, String now, String until) {
		for(int seq : seqList) { 
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
		    String sql = "update books set memberId=?, st=?, en=? where seq=?";
	
		    try {
		       pstmt = conn.prepareStatement(sql);// ����
		       pstmt.setString(1, targetID);
		       pstmt.setString(2,  now);
		       pstmt.setString(3, until);
		       pstmt.setInt(4, seq);
	
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
	}

	public void updateBorrowInfoWithNameList(ArrayList<String> nameList, String targetID, String now, String until) {
		for(String target : nameList) { 
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
		    String sql = "update books set memberId=?, st=?, en=? where bookName=?";
	
		    try {
		       pstmt = conn.prepareStatement(sql);// ����
		       pstmt.setString(1, targetID);
		       pstmt.setString(2,  now);
		       pstmt.setString(3, until);
		       pstmt.setString(4, target);
	
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
	}

	public void allBook(DefaultTableModel model) {
		String sql = "select * from books";
		ResultSet rs = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("seq");
				String bookname = rs.getString("bookname");
				String writer = rs.getString("writer");
				String publisher = rs.getString("publisher");
				String code = rs.getString("code");
				String st = rs.getString("st");

				// Object data[] = {code, bookname, writer, publisher, memberId, st};
				model.addRow(new Object[] { no, bookname, writer, publisher, code, st });
			}
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
	

   public BookDTO doubleCheck(int bookSeq) {
	      BookDTO bookDTO = new BookDTO();      
	      
	      Connection conn = getConnection();
	      PreparedStatement pstmt = null;
	      
	      String sql = "select * from books where seq =?";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, bookSeq);
	         rs = pstmt.executeQuery();
	         

	         while (rs.next()) {
	   
	            bookDTO.setSeq(rs.getInt("seq"));
	            bookDTO.setImage(rs.getString("image"));
	            bookDTO.setCode(rs.getString("code"));
	            bookDTO.setWriter(rs.getString("writer"));
	            bookDTO.setBookName(rs.getString("bookName"));
	            bookDTO.setMemberId(rs.getString("memberId"));
	            bookDTO.setPublisher(rs.getString("publisher"));
	            bookDTO.setSt(rs.getString("st"));
	            bookDTO.setEn(rs.getString("en"));
//		            System.out.println("cartList�� ���");
	            

	         } // while   
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return bookDTO;   
	   }   

}