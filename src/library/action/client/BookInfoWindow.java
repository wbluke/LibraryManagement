package library.action.client;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library.bean.BookDTO;

import java.awt.Color;

public class BookInfoWindow extends JFrame {

   private JPanel jp, panel2;
   
   private String bookInfoWindowImage;

   private BufferedImage img;

   public BookInfoWindow(BookDTO bookDTO) {
      
      setTitle("책정보");
      
      //panel 생성
      jp = new JPanel();
      panel2 = new JPanel(); // 정보 panel
      setBounds(100, 100, 450, 540);   

      jp.setBorder(new EmptyBorder(5, 5, 5, 5));
      jp.setLayout(null);
      getContentPane().add(jp);
      jp.add(panel2);
      panel2.setBounds(48,333,335,142);
      panel2.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("도  서  명 :");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel.setBounds(0, 0, 94, 29);
      panel2.add(lblNewLabel);
      
      JLabel label = new JLabel("장       르 :");
      label.setFont(new Font("굴림", Font.BOLD, 15));
      label.setBounds(0, 39, 94, 27);
      panel2.add(label);
      
      JLabel lblNewLabel_1 = new JLabel("저       자 :");
      lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel_1.setBounds(0, 76, 94, 26);
      panel2.add(lblNewLabel_1);
      
      JLabel lblNewLabel_2 = new JLabel("출  판  사 :");
      lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel_2.setBounds(0, 112, 94, 29);
      panel2.add(lblNewLabel_2);
      
      JLabel bookNameL = new JLabel("도서명");
      bookNameL.setForeground(Color.BLACK);
      bookNameL.setFont(new Font("굴림", Font.BOLD, 15));
      bookNameL.setBackground(new Color(240, 230, 140));
      bookNameL.setBounds(106,0, 193, 29);
      panel2.add(bookNameL);
      
      JLabel bookCodeL = new JLabel("장르");
      bookCodeL.setForeground(Color.BLACK);
      bookCodeL.setFont(new Font("굴림", Font.BOLD, 15));
      bookCodeL.setBackground(new Color(240, 230, 140));
      bookCodeL.setBounds(106, 38, 193, 29);
      panel2.add(bookCodeL);
      

      
      JLabel bookWriterL = new JLabel("저자");
      bookWriterL.setForeground(Color.BLACK);
      bookWriterL.setFont(new Font("굴림", Font.BOLD, 15));
      bookWriterL.setBackground(new Color(240, 230, 140));
      bookWriterL.setBounds(106, 75, 193, 29);
      panel2.add(bookWriterL);
      
      JLabel bookPublisherL = new JLabel("출판사");
      bookPublisherL.setForeground(Color.BLACK);
      bookPublisherL.setFont(new Font("굴림", Font.BOLD, 15));
      bookPublisherL.setBackground(new Color(240, 230, 140));
      bookPublisherL.setBounds(106, 112, 193, 29);
      panel2.add(bookPublisherL);
      
      bookInfoWindowImage =bookDTO.getImage();
      System.out.println(bookInfoWindowImage);
      
      bookNameL.setText("도서명");
      bookWriterL.setText("저자");
      
      bookNameL.setText(bookDTO.getBookName());
      bookCodeL.setText(bookDTO.getCode());
      bookWriterL.setText(bookDTO.getWriter());
      bookPublisherL.setText(bookDTO.getPublisher());

      JLabel lblNewLabel_3 = new JLabel();
      String imagePath = "D:\\LibraryImage\\"+bookInfoWindowImage;
      
      ImageIcon originIcon = new ImageIcon(imagePath);
      Image originImg = originIcon.getImage();
      
      //이미지로 변환
      Image changedImg = originImg.getScaledInstance(215,270,Image.SCALE_SMOOTH);
      ImageIcon Icon = new ImageIcon(changedImg);

      lblNewLabel_3.setIcon(Icon);
      lblNewLabel_3.setBounds(120, 40, 236, 274);

      jp.add(lblNewLabel_3);
      
      new JPanel(){
       @Override
       public void paint(Graphics g){  
          g.drawImage(img, 0, 0, this);
     
           }
       }; // 정보 panel
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setVisible(true);
   }   
   public static void main(String[] args) {
      BookDTO bookDTO = new BookDTO();

      new BookInfoWindow(bookDTO);
   }
   
   
}