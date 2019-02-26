package library.bean;

import java.util.ArrayList;

public class BookDTO {
   private int seq;
   private String image;
   private String code ;
   private String bookName ;
   private String writer ;    
   private String publisher ; 
   private String memberId;
   private String st;
   private String en;
   private ArrayList<BookDTO> doubleListdoubleList;
   
   public ArrayList <BookDTO> getDoubleListdoubleList() {
	      return doubleListdoubleList;
   }
   
   public void setDoubleListdoubleList(ArrayList <BookDTO> doubleListdoubleList) {
	   this.doubleListdoubleList = doubleListdoubleList;
   }
   
   public int getSeq() {
      return seq;
   }
   public void setSeq(int seq) {
      this.seq = seq;
   }


   public String getImage() {
      return image;
   }
   public void setImage(String image) {
      this.image = image;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getMemberId() {
      return memberId;
   }

   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public String getSt() {
      return st;
   }
   
   public void setSt(String st) {
      this.st = st;
   }

   public String getEn() {
      return en;
   }
   
   public void setEn(String en) {
      this.en = en;
   }
   
   
   @Override
   public String toString() {
      return bookName;
   }
   
}