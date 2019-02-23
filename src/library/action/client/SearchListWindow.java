package library.action.client;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import library.dao.BookDAO;
import login.bean.MemberDTO;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchListWindow extends JFrame implements ListSelectionListener{
   private static final String String = null;
   private JComboBox<String> comboBox;
   private JButton searchB;
   private JTextField searchT;
   private JTable jtable;
   private ArrayList<BookDTO> list;
   private DefaultTableModel dtm;
   private String fieldName,value;
   private MemberDTO memberDTO; 

   public SearchListWindow(MemberDTO memberDTO, String fieldName, String keyword) {
      super("���� �˻�");
      this.memberDTO = memberDTO;
      
      JPanel jp = new JPanel();
      setContentPane(jp);
      Container con = this.getContentPane();
      con.setLayout(null);      
      setBounds(100, 100, 878, 640);
      

      JPanel panel = new JPanel(); //��ũ�� �ڸ�
      panel.setBounds(50, 40, 50, 50);
      panel.setBackground(new Color(130, 130, 250));
   
      comboBox = new JComboBox<String>();
      comboBox.setBounds(50, 115, 85, 36);
      comboBox.setModel(new DefaultComboBoxModel<String>(
            new String[] {"��ü", "������", "����", "���ǻ�", "�帣"}));

      searchT = new JTextField();
      searchT.setBounds(135, 115, 600, 36);
      searchT.setColumns(10);   
      
      searchB = new JButton("�˻�");
      searchB.setBounds(735, 115, 65, 36);
      searchB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(e.getSource()==searchB) search();
         }
      });
      
      String[] column = {"","","","","",""}; // Į���̸� ����
      dtm = new DefaultTableModel(column, 0) {
         public boolean isCellEditable(int row, int column) {
            //���̺��� ����Ŭ���Ҷ� �������� ����
            return false; //�� ���� ����(true), �Ұ���(false)
         }
      };
//      list = new ArrayList<BookDTO>();
      
      jtable = new JTable(dtm);
      jtable.setAutoCreateRowSorter(true);
      jtable.setCellSelectionEnabled(rootPaneCheckingEnabled);
      
      dtm.setColumnIdentifiers(
            new String[] {"��ȣ", "����","����","���ǻ�","�帣"});
      
      jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//��ũ�� ����
      jtable.getColumnModel().getColumn(0).setPreferredWidth(10);
      jtable.getColumnModel().getColumn(1).setPreferredWidth(200);
      jtable.getColumnModel().getColumn(2).setPreferredWidth(40);
      jtable.getColumnModel().getColumn(3).setPreferredWidth(50);
      jtable.getColumnModel().getColumn(4).setPreferredWidth(50);      
      jtable.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
      //jtable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      

      JScrollPane scrollPane = new JScrollPane(jtable);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBounds(50, 180, 760, 380);

      getContentPane().add(panel);   
      getContentPane().add(comboBox);
      getContentPane().add(searchT);
      getContentPane().add(searchB);      
      getContentPane().add(scrollPane);      
      
      setResizable(false);
      setVisible(true);
      
      comboBox.setSelectedItem(fieldName);
      searchT.setText(keyword);
      search();
      
      this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
      });
      
      
   }
   @Override
   public void valueChanged(ListSelectionEvent e) {
//      if(list.getSelectedIndex()== -1) {
//      return; 
//   }
//   
//   BookDTO bookDTO = list.getSelectedValue();
//    
   //å���� ���̾�α� �߱�
      
   }

   public void search() {
      //������
      fieldName = comboBox.getSelectedItem().toString();
      value = searchT.getText();
      
      //DB
      BookDAO bookDAO = BookDAO.getInstance();
      
      //�˻��� ���� ��
      if(value.trim().equals("")) {
         JOptionPane.showMessageDialog(
            null, "�˻��� �ܾ �Է��ϼ���", "�ȳ�", 
            JOptionPane.WARNING_MESSAGE);

      //�˻��� �Է½�   
      }else bookDAO.search(dtm, fieldName, value);   
   }
   
   public void insert() {
      //�� �׸��� ������ �ֱ�
      
   }
   
   public static void main(String[] args) {
	   MemberDTO memberDTO = new MemberDTO();
	   new SearchListWindow(memberDTO, "��ü", "");
   }
}