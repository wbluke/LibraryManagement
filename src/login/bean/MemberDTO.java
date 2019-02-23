package login.bean;

import java.util.ArrayList;

import library.bean.BookDTO;

public class MemberDTO {
   private int seq;
   private String memberName;
   private String memberId ;
   private String pw ;
   private int gender;
   private String address;
   private String tel1;
   private String tel2;
   private String tel3;
   private String email;
   private int overdue;
   private ArrayList<BookDTO> bookCart;
   
   public MemberDTO() {
	   setBookCart(new ArrayList<>());
   }
   
   
   public int getSeq() {
      return seq;
   }

   public void setSeq(int seq) {
      this.seq = seq;
   }

   public String getMemberName() {
      return memberName;
   }

   public void setMemberName(String memberName) {
      this.memberName = memberName;
   }

   public String getMemberId() {
      return memberId;
   }

   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }

   public String getPw() {
      return pw;
   }

   public void setPw(String pw) {
      this.pw = pw;
   }

   public int getGender() {
      return gender;
   }

   public void setGender(int gender) {
      this.gender = gender;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getTel1() {
      return tel1;
   }

   public void setTel1(String tel1) {
      this.tel1 = tel1;
   }

   public String getTel2() {
      return tel2;
   }

   public void setTel2(String tel2) {
      this.tel2 = tel2;
   }

   public String getTel3() {
      return tel3;
   }

   public void setTel3(String tel3) {
      this.tel3 = tel3;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getOverdue() {
      return overdue;
   }

   public void setOverdue(int overdue) {
      this.overdue = overdue;
   }
   
   public ArrayList<BookDTO> getBookCart() {
	   return bookCart;
   }
   
   
   public void setBookCart(ArrayList<BookDTO> bookCart) {
	   this.bookCart = bookCart;
   }

   @Override
   public String toString() {
      return memberName;
   }



}