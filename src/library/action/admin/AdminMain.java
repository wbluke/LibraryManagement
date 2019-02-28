package library.action.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import library.action.LibraryMain;
import library.bean.BookDTO;
import library.dao.BookDAO;
import login.bean.MemberDTO;
import login.dao.MemberDAO;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class AdminMain extends JFrame {

   private JPanel contentPane;
   private JTextField textField;
   private JTable table;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_3;
   private JTextField textField_7;
   private JTextField textField_8;
   private JTable table_1;
   private JTextField textField_9;
   private JTextField textField_10;
   private JTextField textField_11;
   private JTextField textField_12;
   private JTextField textField_13;
   private JTextField textField_14;
   private JTable table_2;
   private JTable table_3;
   private JTextField textField_15;
   private JTable table_4;
   private DefaultTableModel dtm2;
   private DefaultTableModel dtm3;
   
   private int table_2Checked = -1;
   private int table_3Checked = -1;
   private JButton button_5;
   private JButton button_7;
   private DefaultTableModel dtm4;
   private int table_4Checked = -1;
   private DefaultTableModel dtm;
   private JComboBox comboBox;
   private JComboBox comboBox_1;
   private DefaultTableModel dtm1;
   private JRadioButton rdbtnNewRadioButton;
   private JRadioButton radioButton;
   private JComboBox comboBox_2;

   @SuppressWarnings("serial")
   public AdminMain(MemberDTO memberDTO) {
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
//      setBounds(400, 200, 771, 606);
      
      setSize(1100,800);
      Dimension frameSize = this.getSize(); // ������ ������
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ����� ������   
      this.setLocation((screenSize.width - frameSize.width)/2, 
            (screenSize.height - frameSize.height)/2); // ȭ�� �߾�
      
      
      
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(0, 10, 1094, 756);
      contentPane.add(tabbedPane);
      getContentPane().setBackground(Color.WHITE);      
      dtm = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                  "��ȣ", "å����", "����", "���ǻ�", "�帣", "�뿩 ����"
            }
         ){
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      dtm1 = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "�̸�", "ȸ�� ID", "ȸ�� PW", "����", "�ּ�", "����ó", "�̸���"
            }
         ){
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      
      ButtonGroup group = new ButtonGroup();
            
            
            JPanel books = new JPanel();
            tabbedPane.addTab("���� ����", null, books, null);
            tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.light"));
            tabbedPane.setForegroundAt(0, Color.BLACK);
            books.setBackground(new Color(255, 255, 240));
            books.setLayout(null);
            
            JLabel label = new JLabel("���� ����");
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            label.setFont(new Font("���� ���", Font.BOLD, 23));
            label.setBounds(870, 29, 142, 45);
            books.add(label);
            
            JButton btnNewButton = new JButton("�˻�");
            btnNewButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
            	   textField_1.setText("");
                   // �帣
                   comboBox_1.setSelectedItem("�Ҽ�");
                   textField_5.setText("");
                   textField_2.setText("");// ���ǻ�
                   textField_4.setText("");
                   textField_6.setText("");// �뿩����
                   
                  if (textField.getText().trim().equals("")) {
                     JOptionPane.showMessageDialog(null, "�˻�� �Է����ּ���.");
                     return;
                  }
                  // DefaultTableModel�� �ִ� ���� ������ �����
                    for (int i = 0; i < dtm.getRowCount();) {
                       dtm.removeRow(0);
                    }
                    
                  BookDAO bookDAO = BookDAO.getInstance();
                  ArrayList<BookDTO> list = bookDAO.searchElement((String)comboBox.getSelectedItem(), textField.getText().trim());
                  
                  if (list.isEmpty()) {
                     JOptionPane.showMessageDialog(
                                 null, "�˻��� �ܾ��� å�� �����ϴ�. \n�˻��� ���Ͻø� �ٽ� �Է����ּ���.", "�ȳ�", 
                                 JOptionPane.WARNING_MESSAGE);
                  }else {
                     for(BookDTO book : list) {
                        String borrowed = null;
                        if (book.getSt() == null) {
                           borrowed = "";
                        }else {
                           borrowed = "�뿩��";
                        }
                        
                        Object data[] = { book.getSeq(), book.getBookName(), book.getWriter(), book.getPublisher(), book.getCode(), borrowed};
                        dtm.addRow(data);
                     }
                  }

               }
            });
            btnNewButton.setFont(new Font("���� ���", Font.BOLD, 14));
            btnNewButton.setBounds(934, 84, 78, 36);
            books.add(btnNewButton);
            
            textField = new JTextField();
            textField.setBounds(565, 84, 371, 34);
            books.add(textField);
            textField.setColumns(10);
            
            comboBox = new JComboBox();
            comboBox.setModel(new DefaultComboBoxModel(new String[] {"��ü", "������", "����", "���ǻ�", "�帣"}));
            comboBox.setBounds(492, 84, 73, 34);
            books.add(comboBox);
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(67, 189, 945, 355);
            books.add(scrollPane);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            table = new JTable();
            //      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                    table.setModel(dtm);
                    table.getColumnModel().getColumn(0).setPreferredWidth(50);
                    table.getColumnModel().getColumn(1).setPreferredWidth(300);
                    table.getColumnModel().getColumn(2).setPreferredWidth(100);
                    table.getColumnModel().getColumn(3).setPreferredWidth(100);
                    table.getColumnModel().getColumn(4).setPreferredWidth(100);
                    table.getColumnModel().getColumn(5).setPreferredWidth(100);
                    table.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
                    table.setBounds(48, 160, 644, 196);
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    table.setRowHeight(30);
                    scrollPane.setViewportView(table);
                    
                    JButton btnNewButton_1 = new JButton("����");
                    btnNewButton_1.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                          BookDAO bookDAO = new BookDAO();
                          int row = table.getSelectedRow();
                          int col = table.getSelectedColumn();
                          if (row == -1 || col == -1) {
                             JOptionPane.showMessageDialog(null, "������ �׸��� �������ּ���.");
                             return;
                          }
                          String value = "" + table.getValueAt(row, 0);

                          DefaultTableModel model2 = (DefaultTableModel) table.getModel();

                          int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "���� ����",
                                JOptionPane.YES_NO_OPTION);
                          if (result == 0) {
                             JOptionPane.showMessageDialog(null, "å ������ �����Ͽ����ϴ�.");
                             
                             bookDAO.delete(Integer.parseInt(value));
                          }
                          
                          //============================================================���ΰ�ħ(���� �˻�)
                          // DefaultTableModel�� �ִ� ���� ������ �����
                            for (int i = 0; i < dtm.getRowCount();) {
                               dtm.removeRow(0);
                            }
                            
                          ArrayList<BookDTO> list = bookDAO.searchElement((String)comboBox.getSelectedItem(), textField.getText().trim());
                          
                          if (list.isEmpty()) {
                             
                          }else {
                             for(BookDTO book : list) {
                                String borrowed = null;
                                if (book.getSt() == null) {
                                   borrowed = "";
                                }else {
                                   borrowed = "�뿩��";
                                }
                                
                                Object data[] = { book.getSeq(), book.getBookName(), book.getWriter(), book.getPublisher(), book.getCode(), borrowed};
                                dtm.addRow(data);
                             }
                          }
                          textField_1.setText("");// ���� ��ȣ
                          textField_4.setText("");// ������
                          textField_5.setText("");// ����
                          textField_2.setText("");// ���ǻ�
                          comboBox_1.setSelectedItem("�Ҽ�");// �帣
                          textField_6.setText("");// �뿩����
                          
                       }
                    });
                    btnNewButton_1.setFont(new Font("���� ���", Font.BOLD, 14));
                    btnNewButton_1.setBounds(934, 133, 78, 34);
                    books.add(btnNewButton_1);
                    
                    JButton button = new JButton("\uCD94\uAC00");
                    button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                          new AdminInsert().event();
                       }
                    });
                    button.setFont(new Font("���� ���", Font.BOLD, 14));
                    button.setBounds(838, 133, 84, 34);
                    books.add(button);
                    
                    JLabel lblNewLabel = new JLabel("���� ��ȣ");
                    lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
                    lblNewLabel.setBounds(67, 597, 67, 34);
                    books.add(lblNewLabel);
                    
                    textField_1 = new JTextField();
                    textField_1.setEnabled(false);
                    textField_1.setBounds(146, 597, 90, 36);
                    books.add(textField_1);
                    textField_1.setColumns(10);
                    
                    JLabel label_3 = new JLabel("���ǻ�");
                    label_3.setHorizontalAlignment(SwingConstants.RIGHT);
                    label_3.setFont(new Font("���� ���", Font.PLAIN, 14));
                    label_3.setBounds(248, 653, 67, 34);
                    books.add(label_3);
                    
                    textField_2 = new JTextField();
                    textField_2.setColumns(10);
                    textField_2.setBounds(327, 655, 238, 34);
                    books.add(textField_2);
                    
                    JLabel label_4 = new JLabel("�帣");
                    label_4.setHorizontalAlignment(SwingConstants.RIGHT);
                    label_4.setFont(new Font("���� ���", Font.PLAIN, 14));
                    label_4.setBounds(67, 653, 67, 34);
                    books.add(label_4);
                    
                    JLabel label_5 = new JLabel("������");
                    label_5.setHorizontalAlignment(SwingConstants.RIGHT);
                    label_5.setFont(new Font("���� ���", Font.PLAIN, 14));
                    label_5.setBounds(248, 597, 67, 34);
                    books.add(label_5);
                    
                    textField_4 = new JTextField();
                    textField_4.setColumns(10);
                    textField_4.setBounds(327, 599, 341, 34);
                    books.add(textField_4);
                    
                    JLabel label_6 = new JLabel("����");
                    label_6.setHorizontalAlignment(SwingConstants.RIGHT);
                    label_6.setFont(new Font("���� ���", Font.PLAIN, 14));
                    label_6.setBounds(680, 595, 67, 34);
                    books.add(label_6);
                    
                    textField_5 = new JTextField();
                    textField_5.setColumns(10);
                    textField_5.setBounds(765, 595, 116, 36);
                    books.add(textField_5);
                    
                    JLabel label_7 = new JLabel("�뿩 ����");
                    label_7.setHorizontalAlignment(SwingConstants.RIGHT);
                    label_7.setFont(new Font("���� ���", Font.PLAIN, 14));
                    label_7.setBounds(680, 651, 67, 34);
                    books.add(label_7);
                    
                    textField_6 = new JTextField();
                    textField_6.setEnabled(false);
                    textField_6.setColumns(10);
                    textField_6.setBounds(765, 653, 116, 34);
                    books.add(textField_6);
                    
                    comboBox_1 = new JComboBox();
                    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"�Ҽ�", "��/������", "����/�濵", "�ڱ���", "�ι�", "����", "����"}));
                    comboBox_1.setBounds(146, 653, 90, 34);
                    books.add(comboBox_1);
                    
                    JButton btnNewButton_2 = new JButton("����");
                    btnNewButton_2.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                          if (textField_1.getText().equals("")) {
                             JOptionPane.showMessageDialog(null, "������ ���� ������ �������ּ���.");
                             return;
                          }
                          BookDAO bookDAO = BookDAO.getInstance();
                          BookDTO bookDTO = new BookDTO();

                          bookDTO.setSeq(Integer.parseInt(textField_1.getText()));
                          // �帣
                          bookDTO.setCode((String)comboBox_1.getSelectedItem());
                          //System.out.println((String)comboBox_1.getSelectedItem());
                          bookDTO.setWriter(textField_5.getText());
                          bookDTO.setPublisher(textField_2.getText());// ���ǻ�
                          bookDTO.setBookName(textField_4.getText());
                          bookDTO.setSt(textField_6.getText());// �뿩����

                          int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "���� ����",
                                JOptionPane.YES_NO_OPTION);
                          if (result == 0) {
                             bookDAO.updatePartsOfBookInfo(bookDTO);

                             JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.");
                             
                             //============================================================���ΰ�ħ(���� �˻�)
                             // DefaultTableModel�� �ִ� ���� ������ �����
                             for (int i = 0; i < dtm.getRowCount();) {
                                dtm.removeRow(0);
                             }
                             
                             ArrayList<BookDTO> list = bookDAO.searchElement((String)comboBox.getSelectedItem(), textField.getText().trim());
                             
                             if (list.isEmpty()) {
                                
                             }else {
                                for(BookDTO book : list) {
                                   String borrowed = null;
                                   if (book.getSt() == null) {
                                      borrowed = "";
                                   }else {
                                      borrowed = "�뿩��";
                                   }
                                   
                                   Object data[] = { book.getSeq(), book.getBookName(), book.getWriter(), book.getPublisher(), book.getCode(), borrowed};
                                   dtm.addRow(data);
                                }
                             }
                          }
                          
                       }
                    });
                    btnNewButton_2.setFont(new Font("���� ���", Font.BOLD, 14));
                    btnNewButton_2.setBounds(904, 588, 108, 97);
                    books.add(btnNewButton_2);
                    
                    JButton button_11 = new JButton("��ü ���� ����");
                    button_11.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                    	   textField_1.setText("");
                           // �帣
                           comboBox_1.setSelectedItem("�Ҽ�");
                           textField_5.setText("");
                           textField_2.setText("");// ���ǻ�
                           textField_4.setText("");
                           textField_6.setText("");// �뿩����
                           
                          // DefaultTableModel�� �ִ� ���� ������ �����
                          for (int i = 0; i < dtm.getRowCount();) {
                             dtm.removeRow(0);
                          }
                          
                          BookDAO bookDAO = BookDAO.getInstance();
                          ArrayList<BookDTO> list = bookDAO.searchAllBooks();
                          if (list.isEmpty()) {
                             
                          }else {
                             for(BookDTO book : list) {
                                String borrowed = null;
                                if (book.getSt() == null) {
                                   borrowed = "";
                                }else {
                                   borrowed = "�뿩��";
                                }
                                
                                Object data[] = { book.getSeq(), book.getBookName(), book.getWriter(), book.getPublisher(), book.getCode(), borrowed};
                                dtm.addRow(data);
                             }
                          }
                       }
                    });
                    button_11.setFont(new Font("���� ���", Font.BOLD, 14));
                    button_11.setBounds(678, 133, 148, 34);
                    books.add(button_11);
                    
                    table.addMouseListener(new MouseAdapter() {
                       public void mousePressed(MouseEvent e) {
                          JTable table = (JTable) e.getSource();
                          int row = table.getSelectedRow();
                          int col = table.getSelectedColumn();
                          String value = "";

                          if (e.getButton() == 1) {
                             textField_1.setText("" + table.getValueAt(row, 0));// ���� ��ȣ
                             textField_4.setText("" + table.getValueAt(row, 1));// ������
                             textField_5.setText("" + table.getValueAt(row, 2));// ����
                             textField_2.setText("" + table.getValueAt(row, 3));// ���ǻ�
                             comboBox_1.setSelectedItem("" + table.getValueAt(row, 4));// �帣
                             textField_6.setText("" + table.getValueAt(row, 5));// �뿩����
                          }
                       }
                    });
      
      //------------------------------      
            JPanel members = new JPanel();
            tabbedPane.addTab("ȸ�� ����", null, members, null);
            members.setBackground(new Color(255, 240, 240));
            members.setLayout(null);
            
            JLabel label_1 = new JLabel("ȸ�� ����");
            label_1.setHorizontalAlignment(SwingConstants.RIGHT);
            label_1.setFont(new Font("���� ���", Font.BOLD, 23));
            label_1.setBounds(872, 26, 142, 45);
            members.add(label_1);
            
            textField_3 = new JTextField();
            textField_3.setColumns(10);
            textField_3.setBounds(678, 81, 236, 34);
            members.add(textField_3);
            
            JButton button_1 = new JButton("�̸� �˻�");
            button_1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
            	   textField_7.setText("");
                   textField_8.setText("");
                   textField_9.setText("");
                   textField_10.setText("");
                   rdbtnNewRadioButton.setSelected(true);
                   textField_11.setText("");
                   comboBox_2.setSelectedItem("010");
                   textField_12.setText("");
                   textField_13.setText("");
                   
                  if (textField_3.getText().trim().equals("")) {
                     JOptionPane.showMessageDialog(
                           null, "�˻�� �Է����ּ���.", "�ȳ�", 
                           JOptionPane.WARNING_MESSAGE);
                     return;
                  }
                  
                  for (int i = 0; i < dtm1.getRowCount();) {
                     dtm1.removeRow(0);
                  }
                  
                  String name = textField_3.getText().trim();
                  MemberDAO memberDAO = MemberDAO.getInstance();
                  
                  ArrayList<MemberDTO> list = memberDAO.searchByName(name);            
                  
                  if(list.isEmpty()) {
                     JOptionPane.showMessageDialog(
                        null, "�˻� ����� �����ϴ�.", "�ȳ�", 
                        JOptionPane.WARNING_MESSAGE);
                  } else {
                     String gender = null;
                     for(MemberDTO memberDTO : list) {
                        if (memberDTO.getGender() == 0) { 
                           gender = "��";
                        }else{
                           gender = "��";
                        }
                        
                        Object data[] = { 
                           memberDTO.getMemberName(), memberDTO.getMemberId(), 
                           memberDTO.getPw(), gender, memberDTO.getAddress(), 
                           memberDTO.getTel1() + "-" + memberDTO.getTel2() + "-" + memberDTO.getTel3(), memberDTO.getEmail()
                        };
                        dtm1.addRow(data);
                     }
                  }
               }
            });
            button_1.setFont(new Font("���� ���", Font.BOLD, 14));
            button_1.setBounds(911, 81, 103, 34);
            members.add(button_1);
            
            JButton button_3 = new JButton("����");
            button_3.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  int row = table_1.getSelectedRow();
                  int col = table_1.getSelectedColumn();
                  if (row == -1 || col == -1) {
                     JOptionPane.showMessageDialog(null, "������ �׸��� �������ּ���.");
                     return;
                  }
                  String targetID = (String) table_1.getValueAt(row, 1);
                  
                  DefaultTableModel model2 = (DefaultTableModel) table_1.getModel();

                  int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "ȸ�� ����",
                        JOptionPane.YES_NO_OPTION);
                  MemberDAO memberDAO = new MemberDAO();
                  if (result == 0) {
                     memberDAO.deleteByID(targetID);
                     JOptionPane.showMessageDialog(null, "ȸ�� ������ �����Ͽ����ϴ�.");
                  }
                  
                  // ============================================================ ���̺� ���ΰ�ħ
                  String nameWhoBorrowed = null;
                  ArrayList<MemberDTO> list = memberDAO.searchByName(textField_3.getText().trim());
                  
                  // DefaultTableModel�� �ִ� ���� ������ �����
                  for (int i = 0; i < dtm1.getRowCount();) {
                     dtm1.removeRow(0);
                  }         

                  if(list.isEmpty()) {
                     
                  } else {
                     String gender = null;
                     for(MemberDTO memberDTO : list) {
                        if (memberDTO.getGender() == 0) { 
                           gender = "��";
                        }else{
                           gender = "��";
                        }
                        
                        Object data[] = { 
                           memberDTO.getMemberName(), memberDTO.getMemberId(), 
                           memberDTO.getPw(), gender, memberDTO.getTel1() + "-" + memberDTO.getTel2() + "-" + memberDTO.getTel3(),
                           memberDTO.getAddress(), memberDTO.getEmail()
                        };
                        dtm1.addRow(data);
                     }
                  }
               }
            });
            button_3.setFont(new Font("���� ���", Font.BOLD, 14));
            button_3.setBounds(921, 133, 93, 34);
            members.add(button_3);
            
            JScrollPane scrollPane_1 = new JScrollPane();
            scrollPane_1.setBounds(69, 189, 945, 345);
            members.add(scrollPane_1);
            scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            table_1 = new JTable();
            table_1.setModel(dtm1);
            scrollPane_1.setColumnHeaderView(table_1);
            scrollPane_1.setViewportView(table_1);
            table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            table_1.setAutoCreateRowSorter(true);
            //table_1.setCellSelectionEnabled(rootPaneCheckingEnabled); //Ư���� Ŭ��
               table_1.setAutoResizeMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
               
      table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//��ũ�� ����
      table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
      table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
      table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
      table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
      table_1.getColumnModel().getColumn(4).setPreferredWidth(300);
      table_1.getColumnModel().getColumn(5).setPreferredWidth(300);
      table_1.getColumnModel().getColumn(6).setPreferredWidth(300);
      table_1.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
      table_1.setRowHeight(30);
      
      table_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            table_1 = (JTable)e.getSource();
            int row = table_1.getSelectedRow();
            int col = table_1.getSelectedColumn();
            
            textField_8.setText((String) table_1.getValueAt(row, 0));
            textField_7.setText((String) table_1.getValueAt(row, 1));
            textField_9.setText((String) table_1.getValueAt(row, 2));
            if (((String) table_1.getValueAt(row, 3)).equals("��")) {
               rdbtnNewRadioButton.setSelected(true);
            }else {
               radioButton.setSelected(true);
            }
            textField_10.setText((String) table_1.getValueAt(row, 4));
            String[] tel = ((String) table_1.getValueAt(row, 5)).split("-");
            comboBox_2.setSelectedItem(tel[0]);
            textField_12.setText(tel[1]);
            textField_13.setText(tel[2]);
            textField_11.setText((String) table_1.getValueAt(row, 6));
            
         }
      });
      
      JButton button_4 = new JButton("����");
      button_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (textField_7.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "������ ���� ������ �������ּ���.");
               return;
            }
            MemberDAO memberDAO = MemberDAO.getInstance();
            MemberDTO memberDTO = new MemberDTO();

            memberDTO.setMemberId(textField_7.getText());
            memberDTO.setMemberName(textField_8.getText());
            memberDTO.setPw(textField_9.getText());
            memberDTO.setAddress(textField_10.getText());
            if (rdbtnNewRadioButton.isSelected()) {
               memberDTO.setGender(0);
            }else {
               memberDTO.setGender(1);
            }
            memberDTO.setEmail(textField_11.getText());
            memberDTO.setTel1((String)comboBox_2.getSelectedItem());
            memberDTO.setTel2(textField_12.getText());
            memberDTO.setTel3(textField_13.getText());

            int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "ȸ�� ����",
                  JOptionPane.YES_NO_OPTION);
            if (result == 0) {
               memberDAO.updatePartsOfMemberInfo(memberDTO);

               JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.");
               
               //============================================================���ΰ�ħ(���� �˻�)
               for (int i = 0; i < dtm1.getRowCount();) {
                  dtm1.removeRow(0);
               }
               
               String name = textField_3.getText().trim();
               
               ArrayList<MemberDTO> list = memberDAO.searchByName(name);            
               
               if(list.isEmpty()) {
                  
               } else {
                  String gender = null;
                  for(MemberDTO member : list) {
                     if (member.getGender() == 0) { 
                        gender = "��";
                     }else{
                        gender = "��";
                     }
                     
                     Object data[] = { 
                           member.getMemberName(), member.getMemberId(), 
                           member.getPw(), gender, member.getAddress(), 
                           member.getTel1() + "-" + member.getTel2() + "-" + member.getTel3(), member.getEmail()
                     };
                     dtm1.addRow(data);
                  }
               }
            }
            
         }
      });
      button_4.setFont(new Font("���� ���", Font.PLAIN, 14));
      button_4.setBounds(936, 567, 78, 80);
      members.add(button_4);
      
      JLabel lblId = new JLabel("ȸ�� ID");
      lblId.setHorizontalAlignment(SwingConstants.RIGHT);
      lblId.setFont(new Font("���� ���", Font.PLAIN, 14));
      lblId.setBounds(69, 567, 67, 34);
      members.add(lblId);
      
      textField_7 = new JTextField();
      textField_7.setEnabled(false);
      textField_7.setColumns(10);
      textField_7.setBounds(148, 569, 142, 34);
      members.add(textField_7);
      
      JLabel label_9 = new JLabel("�̸�");
      label_9.setHorizontalAlignment(SwingConstants.RIGHT);
      label_9.setFont(new Font("���� ���", Font.PLAIN, 14));
      label_9.setBounds(69, 613, 67, 34);
      members.add(label_9);
      
      textField_8 = new JTextField();
      textField_8.setColumns(10);
      textField_8.setBounds(148, 615, 128, 34);
      members.add(textField_8);
      
      JLabel lblPw = new JLabel("ȸ�� PW");
      lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
      lblPw.setFont(new Font("���� ���", Font.PLAIN, 14));
      lblPw.setBounds(302, 567, 67, 34);
      members.add(lblPw);
      
      textField_9 = new JTextField();
      textField_9.setColumns(10);
      textField_9.setBounds(381, 569, 142, 34);
      members.add(textField_9);
      
      JLabel label_10 = new JLabel("����");
      label_10.setHorizontalAlignment(SwingConstants.RIGHT);
      label_10.setFont(new Font("���� ���", Font.PLAIN, 14));
      label_10.setBounds(302, 611, 67, 34);
      members.add(label_10);
      
      rdbtnNewRadioButton = new JRadioButton("��");
      rdbtnNewRadioButton.setSelected(true);
      rdbtnNewRadioButton.setBounds(381, 624, 45, 23);
      rdbtnNewRadioButton.setBackground(new Color(255, 240, 240));
      members.add(rdbtnNewRadioButton);
      
      radioButton = new JRadioButton("��");
      radioButton.setBounds(424, 624, 47, 23);
      radioButton.setBackground(new Color(255, 240, 240));
      members.add(radioButton);
      group.add(rdbtnNewRadioButton);
      group.add(radioButton);
      
      JLabel lblWnth = new JLabel("�ּ�");
      lblWnth.setHorizontalAlignment(SwingConstants.RIGHT);
      lblWnth.setFont(new Font("���� ���", Font.PLAIN, 14));
      lblWnth.setBounds(513, 567, 67, 34);
      members.add(lblWnth);
      
      textField_10 = new JTextField();
      textField_10.setColumns(10);
      textField_10.setBounds(592, 567, 322, 34);
      members.add(textField_10);
      
      JLabel lblDlapdlf = new JLabel("�̸���");
      lblDlapdlf.setHorizontalAlignment(SwingConstants.RIGHT);
      lblDlapdlf.setFont(new Font("���� ���", Font.PLAIN, 14));
      lblDlapdlf.setBounds(513, 613, 67, 34);
      members.add(lblDlapdlf);
      
      textField_11 = new JTextField();
      textField_11.setColumns(10);
      textField_11.setBounds(592, 613, 322, 34);
      members.add(textField_11);
      
      JLabel label_8 = new JLabel("����ó");
      label_8.setHorizontalAlignment(SwingConstants.RIGHT);
      label_8.setFont(new Font("���� ���", Font.PLAIN, 14));
      label_8.setBounds(69, 657, 67, 34);
      members.add(label_8);
      
      textField_12 = new JTextField();
      textField_12.setColumns(10);
      textField_12.setBounds(241, 659, 83, 34);
      members.add(textField_12);
      
      comboBox_2 = new JComboBox();
      comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "017", "019"}));
      comboBox_2.setBounds(148, 659, 67, 34);
      members.add(comboBox_2);
      
      textField_13 = new JTextField();
      textField_13.setColumns(10);
      textField_13.setBounds(343, 659, 83, 34);
      members.add(textField_13);
      
      JLabel lblNewLabel_1 = new JLabel("-");
      lblNewLabel_1.setBounds(227, 668, 14, 15);
      members.add(lblNewLabel_1);
      
      JLabel label_11 = new JLabel("-");
      label_11.setBounds(329, 668, 14, 15);
      members.add(label_11);
      
      JButton button_12 = new JButton("��ü ȸ�� ����");
      button_12.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 textField_7.setText("");
             textField_8.setText("");
             textField_9.setText("");
             textField_10.setText("");
             rdbtnNewRadioButton.setSelected(true);
             textField_11.setText("");
             comboBox_2.setSelectedItem("010");
             textField_12.setText("");
             textField_13.setText("");
             
            MemberDAO memberDAO = MemberDAO.getInstance();
            ArrayList<MemberDTO> list = memberDAO.searchAllMembers();
            
            for (int i = 0; i < dtm1.getRowCount();) {
               dtm1.removeRow(0);
            }
            
            if(list.isEmpty()) {
               
            } else {
               String gender = null;
               for(MemberDTO member : list) {
                  if (member.getGender() == 0) { 
                     gender = "��";
                  }else{
                     gender = "��";
                  }
                  
                  Object data[] = { 
                        member.getMemberName(), member.getMemberId(), 
                        member.getPw(), gender, member.getAddress(), 
                        member.getTel1() + "-" + member.getTel2() + "-" + member.getTel3(), member.getEmail()
                  };
                  dtm1.addRow(data);
               }
            }
         }
      });
      button_12.setFont(new Font("���� ���", Font.BOLD, 14));
      button_12.setBounds(766, 133, 137, 34);
      members.add(button_12);

//---------------------------------
      JPanel statement = new JPanel();
      tabbedPane.addTab("�뿩 ����", null, statement, null);
      statement.setLayout(null);
      statement.setBackground(new Color(240, 255, 240));
      
      JLabel label_2 = new JLabel("�뿩 ����");
      label_2.setBounds(872, 34, 142, 45);
      label_2.setHorizontalAlignment(SwingConstants.RIGHT);
      label_2.setFont(new Font("���� ���", Font.BOLD, 23));
      statement.add(label_2);
      
      JLabel label_13 = new JLabel("ȸ�� ���");
      label_13.setBounds(69, 34, 142, 30);
      label_13.setHorizontalAlignment(SwingConstants.LEFT);
      label_13.setFont(new Font("���� ���", Font.BOLD, 16));
      statement.add(label_13);
      
      JLabel label_14 = new JLabel("���� ����");
      label_14.setBounds(69, 372, 142, 30);
      label_14.setHorizontalAlignment(SwingConstants.LEFT);
      label_14.setFont(new Font("���� ���", Font.BOLD, 16));
      statement.add(label_14);
      
      textField_14 = new JTextField();
      textField_14.setBounds(68, 71, 218, 34);
      statement.add(textField_14);
      textField_14.setColumns(10);
      
      JButton btnNewButton_3 = new JButton("�̸� �˻�");
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 table_2Checked = -1;
        	 button_5.setEnabled(false);
        	 
            if (textField_14.getText().trim().equals("")) {
               JOptionPane.showMessageDialog(
                     null, "�˻�� �Է����ּ���.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
               return;
            }
            MemberDAO memberDAO = MemberDAO.getInstance();
            ArrayList<MemberDTO> list = memberDAO.searchByName(textField_14.getText().trim());
            
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm2.getRowCount();) {
               dtm2.removeRow(0);
            }         

            if(list.isEmpty()) {
               JOptionPane.showMessageDialog(
                     null, "�˻� ����� �����ϴ�.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
            }else {
               for(MemberDTO member : list) {
                  
                  Object data[] = { member.getMemberName(), member.getMemberId(), member.getAddress(), 
                        member.getTel1() + "-" + member.getTel2() + "-" + member.getTel3(), member.getEmail()};
                  dtm2.addRow(data);
               }
            }
         }
      });
      btnNewButton_3.setFont(new Font("���� ���", Font.BOLD, 12));
      btnNewButton_3.setBounds(285, 69, 99, 36);
      statement.add(btnNewButton_3);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.setBounds(69, 117, 945, 220);
      statement.add(scrollPane_2);
      scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      table_2 = new JTable();
      dtm2 = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "�̸�", "ȸ�� ID", "�ּ�", "����ó", "�̸���"
            }
         ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      table_2.setModel(dtm2);
//      table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table_2.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
      scrollPane_2.setColumnHeaderView(table_2);
      scrollPane_2.setViewportView(table_2);
      
      table_2.getColumnModel().getColumn(0).setPreferredWidth(100);
      table_2.getColumnModel().getColumn(1).setPreferredWidth(100);
      table_2.getColumnModel().getColumn(2).setPreferredWidth(300);
      table_2.getColumnModel().getColumn(3).setPreferredWidth(300);
      table_2.getColumnModel().getColumn(4).setPreferredWidth(300);
      table_2.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
      table_2.setRowHeight(30);
      
      table_2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            table_2Checked = table_2.getSelectedRow();
            setEnableBorrowB();
         }
      });
      
      JScrollPane scrollPane_3 = new JScrollPane();
      scrollPane_3.setBounds(69, 458, 938, 240);
      statement.add(scrollPane_3);
      scrollPane_3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      table_3 = new JTable();
      dtm3 = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                  "��ȣ", "å ����", "ȸ�� ID", "ȸ�� �̸�", "�뿩��", "�ݳ���"
            }
         ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      table_3.setModel(dtm3);
//      table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table_3.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
      
      table_3.getColumnModel().getColumn(0).setPreferredWidth(50);
      table_3.getColumnModel().getColumn(1).setPreferredWidth(300);
      table_3.getColumnModel().getColumn(2).setPreferredWidth(100);
      table_3.getColumnModel().getColumn(3).setPreferredWidth(100);
      table_3.getColumnModel().getColumn(4).setPreferredWidth(100);
      table_3.getColumnModel().getColumn(5).setPreferredWidth(100);
      table_3.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
      table_3.setBounds(48, 160, 644, 196);
      table_3.setRowHeight(30);
      
      scrollPane_3.setViewportView(table_3);
            
      table_3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            table_3Checked = table_3.getSelectedRow();
            setEnableBorrowB();
            setEnableReturnB();
         }
      });
      
      textField_15 = new JTextField();
      textField_15.setColumns(10);
      textField_15.setBounds(69, 412, 310, 36);
      statement.add(textField_15);
      
      JButton button_2 = new JButton("\uB3C4\uC11C\uBA85 \uAC80\uC0C9");
      button_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 table_3Checked = -1;
        	 button_5.setEnabled(false);
        	 button_7.setEnabled(false);
        	 
            if (textField_15.getText().trim().equals("")) {
               JOptionPane.showMessageDialog(
                     null, "�˻�� �Է����ּ���.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
               return;
            }
            BookDAO bookDAO = BookDAO.getInstance();
            MemberDAO memberDAO = MemberDAO.getInstance();
            String nameWhoBorrowed = null;
            ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
            
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm3.getRowCount();) {
               dtm3.removeRow(0);
            }         

            if(list.isEmpty()) {
               JOptionPane.showMessageDialog(
                     null, "�˻� ����� �����ϴ�.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
            }else {
               for(BookDTO book : list) {
                  if (book.getMemberId() != null) {
                     MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
                     nameWhoBorrowed = whoBorrowed.getMemberName();
                  }
                  
                  Object data[] = { 
                     book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
                  };
                  dtm3.addRow(data);
                  nameWhoBorrowed = null;
               }
            }
         }
      });
      button_2.setFont(new Font("���� ���", Font.BOLD, 12));
      button_2.setBounds(375, 412, 107, 36);
      statement.add(button_2);
      
      button_5 = new JButton("\uB300\uC5EC\uD558\uAE30");
      button_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 if (table_2Checked == -1 || table_3Checked == -1) {
        		 JOptionPane.showMessageDialog(null, "�ش� ������ �������ּ���.");
        		 return;
        	 }
        	 
            int result = JOptionPane.showConfirmDialog(null, "�뿩�Ͻðڽ��ϱ�?", "�뿩 Ȯ��", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
               // table_2 ���� memberID ��������
               String targetID = (String) table_2.getValueAt(table_2Checked, 1);
               
               // ������Ʈ���� üũ
               MemberDAO memberDAO = MemberDAO.getInstance();
               MemberDTO member = memberDAO.searchByID(targetID);
               if (member.getOverdue() >= 3) {
                  JOptionPane.showMessageDialog(
                        null, "�ش� ȸ���� ������Ʈ�� �뿩 �Ұ� ����Դϴ�.", "�ȳ�", 
                        JOptionPane.WARNING_MESSAGE);
                  return;
               }
               
               // table_3 ���� seq ��������
               int targetBookSeq = (int) table_3.getValueAt(table_3Checked, 0);

               // ���� sysDate�� String���� �ٲ㼭 �ݳ� ��¥���� ����ؼ� �ֱ�
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
               // DAO �۾�
               //System.out.println(now + until);
               BookDAO bookDAO = BookDAO.getInstance();
               bookDAO.updateBorrowInfo(targetBookSeq, targetID, now, until);
               
               // ============================================================ ���̺� ���ΰ�ħ
               String nameWhoBorrowed = null;
               ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
               
               // DefaultTableModel�� �ִ� ���� ������ �����
               for (int i = 0; i < dtm3.getRowCount();) {
                  dtm3.removeRow(0);
               }         

               if(list.isEmpty()) {
                  JOptionPane.showMessageDialog(
                        null, "�˻� ����� �����ϴ�.", "�ȳ�", 
                        JOptionPane.WARNING_MESSAGE);
               }else {
                  for(BookDTO book : list) {
                     if (book.getMemberId() != null) {
                        MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
                        nameWhoBorrowed = whoBorrowed.getMemberName();
                     }
                     
                     Object data[] = { 
                        book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
                     };
                     dtm3.addRow(data);
                     nameWhoBorrowed = null;
                  }
               }
            }
         }
      });
      
      button_5.setFont(new Font("���� ���", Font.BOLD, 12));
      button_5.setBounds(687, 412, 99, 36);
      statement.add(button_5);
      
      JButton button_6 = new JButton("\uC9C0\uC6B0\uAE30");
      button_6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 table_3Checked = -1;
        	 button_5.setEnabled(false);
        	 button_7.setEnabled(false);
        	 
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm3.getRowCount();) {
               dtm3.removeRow(0);
            } 
         }
      });
      button_6.setFont(new Font("���� ���", Font.BOLD, 12));
      button_6.setBounds(909, 412, 98, 36);
      statement.add(button_6);
      
      button_7 = new JButton("\uBC18\uB0A9\uD558\uAE30");
      button_7.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 if (table_2Checked == -1 || table_3Checked == -1) {
        		 JOptionPane.showMessageDialog(null, "�ش� ������ �������ּ���.");
        		 return;
        	 }
        	 
            int result = JOptionPane.showConfirmDialog(null, "�ݳ��Ͻðڽ��ϱ�?", "�ݳ� Ȯ��", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
               
               // table_3 ���� seq ��������
               int targetBookSeq = (int) table_3.getValueAt(table_3Checked, 0);

               // DAO �۾�
               //System.out.println(now + until);
               BookDAO bookDAO = BookDAO.getInstance();
               MemberDAO memberDAO = MemberDAO.getInstance();
               bookDAO.updateReturnInfo(targetBookSeq);
               
               // ============================================================ ���̺� ���ΰ�ħ : �Լ��� ����� �����ؼ� �ߺ��ڵ�
               String nameWhoBorrowed = null;
               ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
               
               // DefaultTableModel�� �ִ� ���� ������ �����
               for (int i = 0; i < dtm3.getRowCount();) {
                  dtm3.removeRow(0);
               }         

               if(list.isEmpty()) {
                  JOptionPane.showMessageDialog(
                        null, "�˻� ����� �����ϴ�.", "�ȳ�", 
                        JOptionPane.WARNING_MESSAGE);
               }else {
                  for(BookDTO book : list) {
                     if (book.getMemberId() != null) {
                        MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
                        nameWhoBorrowed = whoBorrowed.getMemberName();
                     }
                     
                     Object data[] = { 
                        book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
                     };
                     dtm3.addRow(data);
                     nameWhoBorrowed = null;
                  }
               }
            }
         }
      });
      button_7.setFont(new Font("���� ���", Font.BOLD, 12));
      button_7.setBounds(798, 412, 99, 36);
      statement.add(button_7);
      
//-----------------------------------------------      
      JPanel overdue = new JPanel();
      tabbedPane.addTab("��ü ����", null, overdue, null);
      overdue.setBackground(new Color(245, 245, 255));
      overdue.setLayout(null);
      
      JLabel label_12 = new JLabel("��ü ����");
      label_12.setHorizontalAlignment(SwingConstants.RIGHT);
      label_12.setFont(new Font("���� ���", Font.BOLD, 23));
      label_12.setBounds(870, 57, 142, 45);
      overdue.add(label_12);
      
      JButton button_8 = new JButton("�ݳ� ����");
      button_8.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm4.getRowCount();) {
               dtm4.removeRow(0);
            }  
            
            // ���� ��¥�� �ش�Ǵ� ������ ����
            BookDAO bookDAO = BookDAO.getInstance();
            MemberDAO memberDAO = MemberDAO.getInstance();
            ArrayList<BookDTO> list = bookDAO.searchBorrowListByToday();
            
            String memberName = null;
            int overdue = -1;
            String blackList = null;
            if(list.isEmpty()) {
               JOptionPane.showMessageDialog(
                     null, "���� �ݳ� ������ ȸ���� �����ϴ�.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
            }else {
               
               for(BookDTO book : list) {
                  MemberDTO member = memberDAO.searchByID(book.getMemberId());
                  memberName = member.getMemberName();
                  overdue = member.getOverdue();
                  if (overdue >= 3) {
                     blackList = "YES";
                  }else {
                     blackList = null;
                  }
                  
                  // ���̺� �Ѹ��� 
                  Object data[] = { 
                     book.getMemberId(), memberName, book.getBookName(), book.getSt(), book.getEn(), overdue, blackList
                  };
                  dtm4.addRow(data);
               }
            }
         }
      });
      button_8.setFont(new Font("���� ���", Font.BOLD, 12));
      button_8.setBounds(67, 115, 110, 36);
      overdue.add(button_8);
      
      JButton button_9 = new JButton("��ü�ڸ��");
      button_9.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm4.getRowCount();) {
               dtm4.removeRow(0);
            }  
            
            // ���� ��¥�� �ش�Ǵ� ������ ����
            BookDAO bookDAO = BookDAO.getInstance();
            MemberDAO memberDAO = MemberDAO.getInstance();
            ArrayList<BookDTO> list = bookDAO.searchBorrowListUntilYesterday();
            
            String memberName = null;
            int overdue = -1;
            String blackList = null;
            if(list.isEmpty()) {
               JOptionPane.showMessageDialog(
                     null, "���� ��ü ���� ȸ���� �����ϴ�.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
            }else {
               
               for(BookDTO book : list) {
                  MemberDTO member = memberDAO.searchByID(book.getMemberId());
                  memberName = member.getMemberName();
                  overdue = member.getOverdue();
                  if (overdue >= 3) {
                     blackList = "YES";
                  }else {
                     blackList = null;
                  }
                  
                  // ���̺� �Ѹ��� 
                  Object data[] = { 
                     book.getMemberId(), memberName, book.getBookName(), book.getSt(), book.getEn(), overdue, blackList
                  };
                  dtm4.addRow(data);
               }
            }
         }
      });
      button_9.setFont(new Font("���� ���", Font.BOLD, 12));
      button_9.setBounds(191, 115, 110, 36);
      overdue.add(button_9);
      
      JButton button_10 = new JButton("������Ʈ");
      button_10.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dtm4.getRowCount();) {
               dtm4.removeRow(0);
            } 
            
            // ���� ��¥�� �ش�Ǵ� ������ ����
            BookDAO bookDAO = BookDAO.getInstance();
            MemberDAO memberDAO = MemberDAO.getInstance();
            ArrayList<MemberDTO> list = memberDAO.searchByBlackList();
            
            int overdue = -1;
            String blackList = null;
            if(list.isEmpty()) {
               JOptionPane.showMessageDialog(
                     null, "���� ������Ʈ�� �����ϴ�.", "�ȳ�", 
                     JOptionPane.WARNING_MESSAGE);
            }else {
               
               for(MemberDTO member : list) {
                  overdue = member.getOverdue();
                  if (overdue >= 3) {
                     blackList = "YES";
                  }else {
                     blackList = null;
                  }
                  
                  // ���̺� �Ѹ��� 
                  Object data[] = { 
                        member.getMemberId(), member.getMemberName(), "", "", "", overdue, blackList
                  };
                  dtm4.addRow(data);
               }
            }
         }
      });
      button_10.setFont(new Font("���� ���", Font.BOLD, 12));
      button_10.setBounds(313, 115, 110, 36);
      overdue.add(button_10);
      
      JScrollPane scrollPane_4 = new JScrollPane();
      scrollPane_4.setBounds(67, 174, 945, 423);
      overdue.add(scrollPane_4);
      scrollPane_4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      table_4 = new JTable();
//      table_4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table_4.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
      
      dtm4 = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "���̵�", "ȸ�� �̸�", "������", "�뿩��", "�ݳ���", "��ü Ƚ��", "������Ʈ"
            }
         ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      table_4.setModel(dtm4);
      table_4.getColumnModel().getColumn(0).setPreferredWidth(100);
      table_4.getColumnModel().getColumn(1).setPreferredWidth(100);
      table_4.getColumnModel().getColumn(2).setPreferredWidth(300);
      table_4.getColumnModel().getColumn(3).setPreferredWidth(100);
      table_4.getColumnModel().getColumn(4).setPreferredWidth(100);
      table_4.getColumnModel().getColumn(5).setPreferredWidth(50);
      table_4.getColumnModel().getColumn(6).setPreferredWidth(50);
      table_4.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
      table_4.setBounds(48, 160, 644, 196);
      table_4.setRowHeight(30);
      scrollPane_4.setViewportView(table_4);
      
      table_4.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            table_4Checked = table_4.getSelectedRow();
         }
      });
      
      JButton btnNewButton_4 = new JButton("�̸��� ����");
      btnNewButton_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (table_4Checked == -1) {
               JOptionPane.showMessageDialog(null, "�̸����� ���� ȸ���� �����ϼ���.");
               return;
            }
            String targetID = (String) table_4.getValueAt(table_4Checked, 0);
            MemberDAO memberDAO = MemberDAO.getInstance();
            MemberDTO target = memberDAO.searchByID(targetID);
            
            // ���ο� â ����
            new SendEmailToBlack(target);
         }
      });
      btnNewButton_4.setFont(new Font("���� ���", Font.BOLD, 14));
      btnNewButton_4.setBounds(839, 627, 173, 52);
      overdue.add(btnNewButton_4);
      
      button_5.setEnabled(false); // �뿩�ϱ�         
      button_7.setEnabled(false);
      setVisible(true);
      
      this.addWindowListener(new WindowAdapter(){
         public void   windowClosing(WindowEvent e){
            new LibraryMain();
         }
      });
      
   }
   
   private void setEnableBorrowB() {
      if (table_2Checked != -1 && table_3Checked != -1 && (String)table_3.getValueAt(table_3Checked, 4) == null) {
         button_5.setEnabled(true);
      }else {
         button_5.setEnabled(false);
      }
   }
   
   private void setEnableReturnB() {
      if (table_3Checked != -1 && (String)table_3.getValueAt(table_3Checked, 4) != null) {
         button_7.setEnabled(true);
      }else {
         button_7.setEnabled(false);         
      }
   } 

   public static void main(String[] args) {
      new AdminMain(null); 
   }
}