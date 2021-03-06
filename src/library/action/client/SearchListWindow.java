package library.action.client;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import library.bean.BookDTO;
import library.dao.BookDAO;
import login.bean.MemberDTO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class SearchListWindow extends JFrame implements ListSelectionListener, ActionListener{
   private static final String String = null;
   private JComboBox<String> comboBox;
   private JButton searchB, cartB, undoB;
   private JLabel chooseBookL,countItemL;
   private JTextField searchT,   bookCountT;;
   private JTable jtable;
   private ArrayList<BookDTO> list, doubleList;
   private DefaultTableModel dtm;
   private String fieldName,value;
   private MemberDTO memberDTO;
   private DefaultTableCellRenderer dtc; 
    private static final int CHECK_COL = 1;

   public SearchListWindow(MemberDTO memberDTO, String fieldName, String keyword) {
         super("도서 검색");
         
 		setSize(1100,800);
 		Dimension frameSize = this.getSize(); // 프레임 사이즈
 		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈	
 		this.setLocation((screenSize.width - frameSize.width)/2, 
 				(screenSize.height - frameSize.height)/2); // 화면 중앙
                  
         this.memberDTO = memberDTO;

         JPanel jp = new JPanel();
         setContentPane(jp);
         Container con = this.getContentPane();
         con.setLayout(null);      
 //      setBounds(100, 100, 878, 640);
 		 getContentPane().setBackground(new Color(240, 255, 255));
 		 
  		JLabel libMarkL = new JLabel();
	    String imagePath ="libMark.png";
		ImageIcon originIcon = new ImageIcon(imagePath);
	    Image originImg = originIcon.getImage();

	 
	    //이미지로 변환
	    Image changedImg = originImg.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	    ImageIcon Icon = new ImageIcon(changedImg);

	    libMarkL.setIcon(Icon);
		
	    libMarkL.setBounds(50, 10, 100, 100);
	    getContentPane().add(libMarkL); 				 
 		  
         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);

         comboBox = new JComboBox<String>();
         comboBox.setBounds(50, 115, 85, 45);
         comboBox.setModel(new DefaultComboBoxModel<String>(
               new String[] {"전체", "도서명", "저자", "출판사", "장르"}));

         searchT = new JTextField();
         searchT.setBounds(135, 115, 845, 45);
         searchT.setColumns(10);   
         
         searchB = new JButton("검색");
         searchB.setBounds(979, 115, 71, 45);
         
         cartB = new JButton("장바구니 담기");
         cartB.setBounds(781, 694, 117, 45);


         undoB = new JButton("나가기");
         undoB.setBounds(923, 694, 117, 45);
      

         chooseBookL = new JLabel("선택한 책  :");
         chooseBookL.setFont(new Font("굴림", Font.BOLD, 15));
         chooseBookL.setBounds(71, 694, 85, 45);

         
         bookCountT = new JTextField();
         bookCountT.setForeground(new Color(255, 102, 51));
         bookCountT.setFont(new Font("굴림", Font.BOLD, 20));
         bookCountT.setHorizontalAlignment(SwingConstants.RIGHT);
         bookCountT.setBounds(183, 692, 116, 45);
         bookCountT.setColumns(10);
         
         countItemL = new JLabel("권");
         countItemL.setFont(new Font("굴림", Font.BOLD, 15));
         countItemL.setBounds(311, 694, 57, 45);

         String[] column = {"","","","","",""}; // 칼럼이름 설정
         dtm = new DefaultTableModel(column, 0) {
            public boolean isCellEditable(int row, int column) {
               if (column == 0) return true;
               else return false;//셀 수정 가능(true), 불가능(false)
            }
         };
         dtm.setColumnIdentifiers(
               new String[] {"선택","번호", "제목","저자","출판사","장르", "대여 가능 여부"});      

         jtable = new JTable(dtm);
         JCheckBox checkBox = new JCheckBox();   
         
         dtc = new DefaultTableCellRenderer() {
             public Component getTableCellRendererComponent  
              (JTable table, Object value, boolean isSelected, 
              boolean hasFocus, int row, int column){
                
              JCheckBox box= new JCheckBox();
              box.setSelected(((Boolean)value).booleanValue());  
              box.setHorizontalAlignment(JLabel.CENTER);
              box.setBackground(new Color(250,200,230));
              
              return box;
             }
        };
         
         checkBox.setHorizontalAlignment(JLabel.CENTER); //체크박스 가운데 정렬   
         jtable.getColumn("선택").setCellRenderer(dtc); //관련 칼럼 속성 변경
         jtable.getColumn("선택").setCellEditor(new DefaultCellEditor(checkBox));//편집 가능하게
         jtable.getTableHeader().setReorderingAllowed(false); //칼럼이동 불가
         jtable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
         jtable.setAutoCreateRowSorter(true);
         jtable.setAutoResizeMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         
         jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//열크기 조절
         jtable.getColumnModel().getColumn(0).setPreferredWidth(5);
         jtable.getColumnModel().getColumn(1).setPreferredWidth(10);
         jtable.getColumnModel().getColumn(2).setPreferredWidth(200);
         jtable.getColumnModel().getColumn(3).setPreferredWidth(40);
         jtable.getColumnModel().getColumn(4).setPreferredWidth(50);
         jtable.getColumnModel().getColumn(5).setPreferredWidth(50);      
         jtable.getColumnModel().getColumn(6).setPreferredWidth(50);      
         
         jtable.setRowHeight(30);

         JScrollPane scrollPane = new JScrollPane(jtable);
         scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         scrollPane.setBounds(50, 201, 1000, 463);
 
         getContentPane().add(comboBox);
         getContentPane().add(searchT);
         getContentPane().add(searchB);   
         jp.add(cartB); //선택
         jp.add(undoB); //취소   
         getContentPane().add(scrollPane);   
         jp.add(chooseBookL);
         jp.add(bookCountT);
         jp.add(countItemL);

         //체크박스값
         jtable.getModel().addTableModelListener(new TableModelListener() { 
            @Override
            public void tableChanged(TableModelEvent e) {   
               int cnt = 0;
               for(int i=0;i<jtable.getModel().getRowCount();i++) { 
                  if ((Boolean) jtable.getModel().getValueAt(i,0)) cnt++;
               }
               bookCountT.setText(cnt+"");
            }
         });
         
         jtable.addMouseListener(new MouseAdapter() {
             @Override public void mouseClicked(MouseEvent e) {
             JTable t = (JTable)e.getSource();
                if(e.getClickCount()==2) {  //더블클릭
                         TableModel m = t.getModel();
                         Point pt = e.getPoint();
                         int i = t.rowAtPoint(pt);
                         if(i>=0) {
                             int row = t.convertRowIndexToModel(i);
                             
                             int bookSeq = (int) m.getValueAt(row,1);
                             
                           //DB
                           BookDAO bookDAO = BookDAO.getInstance();
                                                                    
                           BookDTO bookDTO = bookDAO.doubleCheck(bookSeq);   
                           
                           new BookInfoWindow(bookDTO);
                           
                         }
                 }
             }
          });
         
         cartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               BookDAO bookDAO = BookDAO.getInstance();
               
               ArrayList<Integer> list = new ArrayList<Integer>();
     	      //BookDTO bookDTOSeq = new BookDTO();
     	      //System.out.println(dtm.getRowCount());
     	      
     	      for (int i = 0; i < dtm.getRowCount() ; i++) {
     	         //System.out.println("1");
     	         Boolean CartCheck = Boolean.valueOf(dtm.getValueAt(i, 0).toString());
     	         int cartSeq = (int) dtm.getValueAt(i, 1);

     	         if (CartCheck) {
     	            //System.out.println(cartSeq);
     	        	 
     	        	 
     	        	 // 대여 불가 항목인지 판단하기
     	        	 if (!dtm.getValueAt(i, 6).equals("")) {
     	        		 JOptionPane.showMessageDialog(
     	   	                  null, "선택 항목 중 대여불가 항목이 있습니다.\n체크한 항목을 확인해주세요.", "안내", 
     	   	                  JOptionPane.WARNING_MESSAGE);
     	        		 return;
     	        	 }else {
     	        		 list.add(cartSeq);	        		 
     	        	 }
     	         }
     	      }
               
               //BookDAO의 cart함수 결과값인 cartList 가져와 저장
               ArrayList<BookDTO> cartList = bookDAO.cart(list); 
               //bookDAO.searchByBookSeq(cartSeq).getSt()
               
               if (cartList != null) {
            	   	ArrayList<BookDTO> cart = memberDTO.getBookCart();
	                for(BookDTO book : cartList) {
	                	Boolean check = true;
	                	for (BookDTO checkBook : cart) {
	                		if (checkBook.getSeq() == book.getSeq()) {
	                			check = false;
	                			break;
	                		}
	                	}
	                	if(check) {
	                		cart.add(book);
	                	}
	                }
               }
               JOptionPane.showMessageDialog(
                       null, "장바구니에 "+bookCountT.getText()+" 권을 담았습니다.", "안내", 
                       JOptionPane.WARNING_MESSAGE);
            }
         
         });
         undoB.addActionListener(this);
         searchB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(e.getSource()==searchB) search();
            }
         });
         
         
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         setResizable(false);
         setVisible(true);
         
         comboBox.setSelectedItem(fieldName);
         searchT.setText(keyword);
         search();
   }

   

   public void doubleCheckWindow(ArrayList<BookDTO> doubleList2) {
      JPanel doublePanel = new JPanel();
      
      setTitle("책정보");
      setContentPane(doublePanel);
      Container con = this.getContentPane();
      con.setLayout(null);      
      setBounds(100, 100, 878, 640);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      
      setResizable(false);
      setVisible(true);
      
   }

   public void search() {
      //데이터
      fieldName = comboBox.getSelectedItem().toString();
      value = searchT.getText().trim();
      //System.out.println("필드명"+ fieldName);
      
      //DB
      BookDAO bookDAO = BookDAO.getInstance();
      
      //검색어 유무
      if(value.equals("")) {
         JOptionPane.showMessageDialog(
            null, "검색할 단어를 입력하세요", "안내", 
            JOptionPane.WARNING_MESSAGE);
         return;
      }
      
      // DefaultTableModel에 있는 기존 데이터 지우기
      for (int i = 0; i < dtm.getRowCount();) {
    	  dtm.removeRow(0);
      }  
      
	  ArrayList<BookDTO> list = bookDAO.searchElement(fieldName, value);   
	  if (list.isEmpty()) {
		  JOptionPane.showMessageDialog(
                  null, "검색한 단어의 책이 없습니다. \n검색을 원하시면 다시 입력해주세요.", "안내", 
                  JOptionPane.WARNING_MESSAGE);
		  return;
	  }
	  
	  for (BookDTO book : list) {
		  String borrowed = "";
		  
	      if (book.getSt() == null) {
	    	  //borrowed = "";
	      }else {
	    	  borrowed = "대여 불가";	        	 
	      }
	         
          Object data[] = {Boolean.FALSE, book.getSeq(), book.getBookName(), book.getWriter(),
                          book.getPublisher(), book.getCode(), borrowed};
               
          dtm.addRow(data);
	  }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       if(e.getSource() == undoB){
          setVisible(false);          
       }
   }
   @Override
   public void valueChanged(ListSelectionEvent e) {
	   // TODO Auto-generated method stub
	   
   }
   
   public static void main(String[] args) {
      MemberDTO memberDTO = new MemberDTO();
      new SearchListWindow(memberDTO, "도서명", "");

   }
   

}