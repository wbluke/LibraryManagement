package library.action.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import library.action.LibraryMain;
import library.bean.BookDTO;
import library.dao.BookDAO;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;


public class AdminInsert extends JFrame implements ActionListener {

   private JPanel jp, markP;
   private JLabel bookImagL, bookCategoryL, writerL, publisherL, bookNameL; 
   private JTextField imagePathT, writerT, publisherT, bookNameT;
   private JComboBox<String> comboBox;
   private JButton enrollB, cancelB, fileOpenB;
   private DefaultListModel<BookDTO> model;
   private JFileChooser chooser = new JFileChooser();
   private File file;

   public AdminInsert() {
      super("���� �߰�");
      setBounds(100, 100, 700, 514);
      
      jp = new JPanel();
      jp.setBackground(new Color(255, 245, 238));
      setContentPane(jp);
      getContentPane().setLayout(null);
      
      markP = new JPanel(); //��������ũ ���Ժκ�
      markP.setBackground(new Color(123, 104, 238));
      markP.setBounds(68, 37, 75, 75);

      bookImagL = new JLabel("�����̹���");
      bookCategoryL = new JLabel("�帣");      
      writerL = new JLabel("����");
      publisherL = new JLabel("���ǻ�");
      bookNameL = new JLabel("������");
      
      bookImagL.setFont(new Font("���� ���", Font.PLAIN, 18));
      bookImagL.setBounds(68, 152, 125, 32);
      
      bookCategoryL.setFont(new Font("���� ���", Font.PLAIN, 18));
      bookCategoryL.setBounds(68, 196, 125, 35);

      writerL.setFont(new Font("���� ���", Font.PLAIN, 18));
      writerL.setBounds(68, 241, 125, 35);

      publisherL.setFont(new Font("���� ���", Font.PLAIN, 18));
      publisherL.setBounds(68, 286, 125, 35);

      bookNameL.setFont(new Font("���� ���", Font.PLAIN, 18));
      bookNameL.setBounds(68, 331, 125, 35);

      /*
       * String [] categ = new String[]{"�Ҽ�", "��/������", "����/�濵", "�ڱ���", "�ι�", "����",
       * "����"};
       */
      comboBox = new JComboBox<String>();
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"�Ҽ�", "��/������", "����/�濵", "�ڱ���", "�ι�", "����", "����"}));
      comboBox.setFont(new Font("���� ���", Font.PLAIN, 18));
      comboBox.setBounds(205, 196, 337, 35);
      
      imagePathT = new JTextField();
      writerT = new JTextField();   
      publisherT = new JTextField();
      bookNameT = new JTextField();      
      
      imagePathT.setFont(new Font("���� ���", Font.PLAIN, 18));
      imagePathT.setBounds(205, 152, 337, 34);
      imagePathT.setColumns(10);

      writerT.setFont(new Font("���� ���", Font.PLAIN, 18));
      writerT.setBounds(205, 241, 337, 35);
      writerT.setColumns(10);
      
      publisherT.setFont(new Font("���� ���", Font.PLAIN, 18));
      publisherT.setBounds(205, 286, 337, 35);
      publisherT.setColumns(10);
      
      bookNameT.setFont(new Font("���� ���", Font.PLAIN, 18));
      bookNameT.setBounds(205, 331, 337, 35);
      bookNameT.setColumns(10);
      
      fileOpenB = new JButton("����");
      fileOpenB.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      fileOpenB.setFont(new Font("���� ���", Font.PLAIN, 18));
      fileOpenB.setBounds(554, 151, 53, 34);

      enrollB = new JButton("��   ��");
      cancelB = new JButton("��   ��");
      
      enrollB.setFont(new Font("����", Font.BOLD, 15));
      enrollB.setBounds(146, 395, 144, 41);
      
      cancelB.setFont(new Font("����", Font.BOLD, 15));
      cancelB.setBounds(418, 395, 144, 41);
      jp.setLayout(null);

      getContentPane().add(markP);
      
      getContentPane().add(bookImagL);
      getContentPane().add(bookCategoryL);   
      getContentPane().add(writerL);
      getContentPane().add(publisherL);
      getContentPane().add(bookNameL);
      
      getContentPane().add(imagePathT);
      getContentPane().add(fileOpenB);
      
      getContentPane().add(comboBox);
      getContentPane().add(writerT);
      getContentPane().add(publisherT);
      getContentPane().add(bookNameT);
      
      getContentPane().add(enrollB);
      getContentPane().add(cancelB);
      
      JLabel lblNewLabel = new JLabel("���� �߰�");
      lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 23));
      lblNewLabel.setBounds(438, 37, 144, 41);
      jp.add(lblNewLabel);
      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
      
   }
   public void event(){
      fileOpenB.addActionListener(this);
      enrollB.addActionListener(this);
      cancelB.addActionListener(this);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == fileOpenB) {
         int choose = chooser.showOpenDialog(AdminInsert.this);
         
         if(choose != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(AdminInsert.this, "���� ���� ����", "���", JOptionPane.WARNING_MESSAGE);
            return;
         }
         
         //������ �������� ��������
         file = chooser.getSelectedFile();
         imagePathT.setText(file.getName()); //�����̸�
//         imagePathT.setText( file.getPath());
         //String path = file.getPath(); ���� �̸� ������ ��ü ���
         
         
      }
      else if(e.getSource() == enrollB) {
          enroll();
      }
      
      else if (e.getSource() == cancelB) cancel();
      
   }
   private void cancel() {
      
   }

   private void enroll() {
      //������
      String image = imagePathT.getText();
      String code = comboBox.getSelectedItem().toString();  
      String writer = writerT.getText();
      String publisher = publisherT.getText();
      String bookname = bookNameT.getText();
      
      BookDTO bookDTO = new BookDTO();
      bookDTO.setImage(image);
      bookDTO.setCode(code);
      bookDTO.setWriter(writer);
      bookDTO.setPublisher(publisher);
      bookDTO.setBookName(bookname);

      
      //DB
      BookDAO bookDAO = BookDAO.getInstance();
      int seq = bookDAO.getSeq();

      bookDTO.setSeq(seq);
      
      bookDAO.insert(bookDTO);
      
      erase();//�ʱ�ȭ

      //����
      System.out.println("�����͸� ����Ͽ����ϴ�"); 
   //   model.addElement(bookDTO);//JList�� �߰�
   }

   private void erase() {
      
      
   }
   public static void main(String[] args) {
      new AdminInsert().event();
      
      //bookDAO insert�Լ�/ erase�Լ�
      //code���� ����!!
   }
}