package library.action.mypage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import library.action.LibraryMain;
import library.bean.BookDTO;
import library.dao.BookDAO;
import login.bean.MemberDTO;
import login.dao.MemberDAO;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyPage extends JFrame {

   private JPanel contentPane;
   private JTable table;
   private JTable table_1;
   private MemberDTO memberDTO;
   private DefaultTableModel dtm1;
   private DefaultTableModel dtm;
   private DefaultTableCellRenderer dtc;
   private AbstractButton memberDeleteB;
   private boolean isBookExist = false;
   private boolean isCartExist = false;
   private JButton selectRentB;
   private JButton allDeleteB;
   private JButton allRentB;
   private AbstractButton selectDeleteB;

   @SuppressWarnings("serial")
   public MyPage(MemberDTO memberDTO) {
      super("MyPage");
//      setBounds(400, 200, 842, 467);   
      setSize(1100,800);
      Dimension frameSize = this.getSize(); // 프레임 사이즈
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈   
      this.setLocation((screenSize.width - frameSize.width)/2, 
            (screenSize.height - frameSize.height)/2); // 화면 중앙

      
      this.memberDTO = memberDTO;
      
      isBookExist = false;
      isCartExist = false;
      
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      getContentPane().setBackground(new Color(245,255,245));   
      
      
      JLabel lblNewLabel_4 = new JLabel();
       String imagePath ="libMark.png";
      ImageIcon originIcon = new ImageIcon(imagePath);
       Image originImg = originIcon.getImage();

    
       //이미지로 변환
       Image changedImg = originImg.getScaledInstance(150,150,Image.SCALE_SMOOTH);
       ImageIcon Icon = new ImageIcon(changedImg);

       lblNewLabel_4.setIcon(Icon);
      
      lblNewLabel_4.setBounds(920, 0, 150, 150);
      contentPane.add(lblNewLabel_4);
      
      JButton infoChangeB = new JButton("회원정보 변경");
      infoChangeB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      infoChangeB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new ChangeInfo(memberDTO).event();
         }
      });
      infoChangeB.setBounds(81, 87, 120, 35);
      contentPane.add(infoChangeB);
      
      memberDeleteB = new JButton("회원 탈퇴");
      memberDeleteB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      memberDeleteB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] option = {"회원 탈퇴", "취 소"};
            int selected = JOptionPane.showOptionDialog(null, "정말 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "취 소");
            if(selected == JOptionPane.YES_OPTION) {
               if (isBookExist) {
                  JOptionPane.showMessageDialog(
                        null, "대여 중인 도서가 남아있을 경우 탈퇴 처리가 불가합니다.\n대여중인 도서를 반납해주세요.", "안내", 
                        JOptionPane.WARNING_MESSAGE);
                  return;
               }
               String id = memberDTO.getMemberId();
               MemberDAO memberDAO = MemberDAO.getInstance();
               memberDAO.memberSecession(id);
               JOptionPane.showMessageDialog(
                     null, "회원 탈퇴 처리되었습니다.\n프로그램이 종료됩니다.", "안내", 
                     JOptionPane.WARNING_MESSAGE);
               System.exit(0);
            }
         }
      });
      memberDeleteB.setBounds(225, 87, 120, 35);
      contentPane.add(memberDeleteB);
      
      JLabel RentL = new JLabel("대여 현황");
      RentL.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      RentL.setBounds(47, 157, 103, 41);
      contentPane.add(RentL);
      
      JLabel basketL = new JLabel("장바구니");
      basketL.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      basketL.setBounds(578, 157, 143, 41);
      contentPane.add(basketL);
      
      allRentB = new JButton("전부 대여");
      allRentB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] option = {"전부 대여", "취 소"};
            int selected = JOptionPane.showOptionDialog(null, "장바구니의 모든 항목을 대여하시겠습니까?", "전부 대여", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "취 소");
            if (selected == JOptionPane.YES_OPTION) {
               ArrayList<Integer> seqList = new ArrayList<>();
               for(BookDTO book : memberDTO.getBookCart()) {
                  seqList.add(book.getSeq());
               } // seq만 넣기
               
               BookDAO bookDAO = BookDAO.getInstance();
               // 현재 sysDate를 String으로 바꿔서 반납 날짜까지 계산해서 넣기
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String now = formatter.format(new Date());
               Calendar cal = Calendar.getInstance();
               String until = null;
               try {
                  cal.setTime(formatter.parse(now));
                  cal.add(Calendar.DATE, 14);
                  until = formatter.format(cal.getTime());
               } catch (ParseException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
               
               bookDAO.updateBorrowInfoWithList(seqList, memberDTO.getMemberId(), now, until);
               
               JOptionPane.showMessageDialog(
                     null, "장바구니의 모든 도서가 대여되었습니다.", "안내", 
                     JOptionPane.WARNING_MESSAGE);
               
               memberDTO.getBookCart().clear();
               // 대여현황
               callBorrowList();
               // 장바구니
               callBookCart();
               
            }
         }
      });
      allRentB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      allRentB.setBounds(831, 134, 110, 35);
      contentPane.add(allRentB);
      
      allDeleteB = new JButton("전부 비우기");
      allDeleteB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] option = {"전부 비우기", "취 소"};
            int selected = JOptionPane.showOptionDialog(null, "장바구니의 모든 항목을 비우시겠습니까?", "전부 비우기", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "취 소");
            if (selected == JOptionPane.YES_OPTION) {
               
               
               JOptionPane.showMessageDialog(
                     null, "장바구니가 초기화되었습니다.", "안내", 
                     JOptionPane.WARNING_MESSAGE);
               
               memberDTO.getBookCart().clear();
               // 장바구니
               callBookCart();
               
            }
         }
      });
      allDeleteB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      allDeleteB.setBounds(831, 178, 110, 35);
      contentPane.add(allDeleteB);
      
      selectRentB = new JButton("선택 대여");
      selectRentB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      selectRentB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] option = {"선택 대여", "취 소"};
            int selected = JOptionPane.showOptionDialog(null, "선택한 장바구니의 항목들을 대여하시겠습니까?", "선택 대여", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "취 소");
            if (selected == JOptionPane.YES_OPTION) {
               BookDAO bookDAO = BookDAO.getInstance();
               
               
               ArrayList<String> nameList = new ArrayList<>();
               
               for (int i = 0; i < dtm1.getRowCount() ; i++) {
                     //System.out.println("1");
                     Boolean CartCheck = Boolean.valueOf(dtm1.getValueAt(i, 0).toString());
                     String cartName = (String) dtm1.getValueAt(i, 1);

                     if (CartCheck) {
                        nameList.add(cartName);                  
                     }
                  }
               
               // 현재 sysDate를 String으로 바꿔서 반납 날짜까지 계산해서 넣기
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String now = formatter.format(new Date());
               Calendar cal = Calendar.getInstance();
               String until = null;
               try {
                  cal.setTime(formatter.parse(now));
                  cal.add(Calendar.DATE, 14);
                  until = formatter.format(cal.getTime());
               } catch (ParseException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
               
               bookDAO.updateBorrowInfoWithNameList(nameList, memberDTO.getMemberId(), now, until);
               
               JOptionPane.showMessageDialog(
                     null, "선택된 장바구니의 도서가 대여되었습니다.", "안내", 
                     JOptionPane.WARNING_MESSAGE);
               
               for (String name : nameList) {
                  Iterator<BookDTO> it = memberDTO.getBookCart().iterator();
                  while(it.hasNext()) {
                     if (it.next().getBookName().equals(name)) {
                        it.remove();
                     }
                  }
                  
               }
               // 대여현황
               callBorrowList();
               // 장바구니
               callBookCart();
               
            }
         }
      });
      selectRentB.setBounds(953, 134, 110, 35);
      contentPane.add(selectRentB);
      
      selectDeleteB = new JButton("선택 비우기");
      selectDeleteB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] option = {"선택 취소", "취 소"};
            int selected = JOptionPane.showOptionDialog(null, "선택한 장바구니의 항목들을 취소하시겠습니까?", "선택 취소", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "취 소");
            if (selected == JOptionPane.YES_OPTION) {
               BookDAO bookDAO = BookDAO.getInstance();
               
               
               ArrayList<String> nameList = new ArrayList<>();
               
               for (int i = 0; i < dtm1.getRowCount() ; i++) {
                     //System.out.println("1");
                     Boolean CartCheck = Boolean.valueOf(dtm1.getValueAt(i, 0).toString());
                     String cartName = (String) dtm1.getValueAt(i, 1);

                     if (CartCheck) {
                        nameList.add(cartName);                  
                     }
                  }
               
               JOptionPane.showMessageDialog(
                     null, "선택된 도서가 장바구니에서 취소되었습니다.", "안내", 
                     JOptionPane.WARNING_MESSAGE);
               
               for (String name : nameList) {
                  Iterator<BookDTO> it = memberDTO.getBookCart().iterator();
                  while(it.hasNext()) {
                     if (it.next().getBookName().equals(name)) {
                        it.remove();
                     }
                  }
                  
               }
               // 대여현황
               callBorrowList();
               // 장바구니
               callBookCart();
               
            }
         }
      });
      selectDeleteB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      selectDeleteB.setBounds(953, 178, 110, 35);
      contentPane.add(selectDeleteB);
      
      JLabel label = new JLabel("회원 정보");
      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label.setBounds(47, 39, 103, 23);
      contentPane.add(label);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(32, 237, 498, 470);
      contentPane.add(scrollPane);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      table = new JTable();
      dtm = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "도서명", "대여일", "반납일", "남은 날짜"
            }
         ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      table.setModel(dtm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      scrollPane.setColumnHeaderView(table);
      scrollPane.setViewportView(table);
      table.getColumnModel().getColumn(0).setPreferredWidth(300);
      table.getColumnModel().getColumn(1).setPreferredWidth(100);
      table.getColumnModel().getColumn(2).setPreferredWidth(100);
      table.getColumnModel().getColumn(3).setPreferredWidth(100);
      table.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
      table.setRowHeight(30);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(565, 237, 498, 470);
      contentPane.add(scrollPane_1);
      scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      table_1 = new JTable();
      dtm1 = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "선택", "도서명", "저자", "출판사"
            }
         ) {
         public boolean isCellEditable(int row, int column) {
            if (column == 0) return true;
            return false;
         }
      };
      table_1.setModel(dtm1);
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
        table_1.getColumn("선택").setCellRenderer(dtc); //관련 칼럼 속성 변경
        table_1.getColumn("선택").setCellEditor(new DefaultCellEditor(checkBox));//편집 가능하게
        table_1.getTableHeader().setReorderingAllowed(false); //칼럼이동 불가      
        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        table_1.setAutoCreateRowSorter(true);
        table_1.setAutoResizeMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
//      table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      scrollPane_1.setColumnHeaderView(table_1);
      scrollPane_1.setViewportView(table_1);
      table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
      table_1.getColumnModel().getColumn(1).setPreferredWidth(300);
      table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
      table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
      table_1.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
      table_1.setRowHeight(30);
      
      setResizable(false);
      setVisible(true);
      
      // 대여현황
      callBorrowList();
      
      // 장바구니
      callBookCart();
      
      if (isCartExist) {
         allRentB.setEnabled(true);
         allDeleteB.setEnabled(true);
         selectRentB.setEnabled(true);
         selectDeleteB.setEnabled(true);
      }else {
         allRentB.setEnabled(false);
         allDeleteB.setEnabled(false);
         selectRentB.setEnabled(false);
         selectDeleteB.setEnabled(false);
      }
      
      this.addWindowListener(new WindowAdapter(){
         public void   windowClosing(WindowEvent e){
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }
      });
   }


   public void callBorrowList() { // 대여현황
      BookDAO bookDAO = BookDAO.getInstance();
      ArrayList<BookDTO> list = bookDAO.searchByID(memberDTO.getMemberId());
      
      // DefaultTableModel에 있는 기존 데이터 지우기
      for (int i = 0; i < dtm.getRowCount();) {
         dtm.removeRow(0);
      }         

      if(!list.isEmpty()) {
         isBookExist = true;
         for(BookDTO book : list) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String start = book.getEn();
            String end = formatter.format(new Date());
            long diffDays = 1000;
            try {
               Date beginDate = formatter.parse(start);
               Date endDate = formatter.parse(end);
               // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
                 long diff = endDate.getTime() - beginDate.getTime();
                 diffDays = diff / (24 * 60 * 60 * 1000);
            } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
               
            String days = null;
            if (diffDays >= 1) {
               days = diffDays + "일 지남";
            }else if (diffDays == 0) {
               days = "오늘 반납";
            }else {
               days = (diffDays * -1) + "일";
            }
            
            
            Object data[] = { book.getBookName(), book.getSt(), book.getEn(), days};
            dtm.addRow(data);
         }
      }
   }
   
   public void callBookCart() {
      if (memberDTO.getBookCart().isEmpty()) {
         isCartExist  = false;
      }else {
         isCartExist  = true;         
      }
      
      if (isCartExist) {
         allRentB.setEnabled(true);
         allDeleteB.setEnabled(true);
         selectRentB.setEnabled(true);
         selectDeleteB.setEnabled(true);
      }else {
         allRentB.setEnabled(false);
         allDeleteB.setEnabled(false);
         selectRentB.setEnabled(false);
         selectDeleteB.setEnabled(false);
      }
      
      // DefaultTableModel에 있는 기존 데이터 지우기
        for (int i = 0; i < dtm1.getRowCount();) {
           dtm1.removeRow(0);
        }         
        
        for( BookDTO book : memberDTO.getBookCart()) {
             Object data[] = {Boolean.FALSE, book.getBookName(), book.getWriter(), book.getPublisher()};
                 
             dtm1.addRow(data);
        }
      
   }
   
   public static void main(String[] args) {
      BookDAO bookDAO = BookDAO.getInstance();
      MemberDTO memberDTO = new MemberDTO();
      memberDTO.setMemberId("ppp");
      
      ArrayList<BookDTO> list = bookDAO.searchByName("언");
      
      for(BookDTO book : list) {
         memberDTO.getBookCart().add(book);
      }
      
      new MyPage(memberDTO);
   }
   
   
   
}