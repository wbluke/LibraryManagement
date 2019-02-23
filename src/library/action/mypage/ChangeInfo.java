package library.action.mypage;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import login.bean.MemberDTO;

public class ChangeInfo extends JFrame implements ActionListener{
   private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
   private JTextField idT, pwT1, pwT2, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
   private JRadioButton male, female;
   private JComboBox<String> telCB;
   private JButton overlapB, CertifiedB, joinB, cancelB;
private MemberDTO memberDTO;
   
   public ChangeInfo(MemberDTO memberDTO) {
      super("정보변경");
      
      this.memberDTO = memberDTO;
      
      setLayout(null);
      
      //JLable
      idL = new JLabel("아 이 디 : ");
      pwL1 = new JLabel("비밀번호 : ");
      pwL2 = new JLabel("비밀번호 확인 : ");
      nameL = new JLabel("이      름 : ");
      genderL = new JLabel("성      별 : ");
      telL = new JLabel("전화번호 : ");
      hyphenL1 = new JLabel("-");
      hyphenL2 = new JLabel("-");
      addrL = new JLabel("주      소 : ");
      emailL = new JLabel("이메일 : ");
      certifiedNumL = new JLabel("인증번호 : ");
      
      idL.setBounds(20, 30, 80, 20);
      pwL1.setBounds(20, 70, 80, 20);
      pwL2.setBounds(20, 110, 90, 20);
      nameL.setBounds(20, 150, 80, 20);
      genderL.setBounds(20, 190, 80, 20);
      telL.setBounds(20, 230, 80, 20);
      hyphenL1.setBounds(150, 230, 10, 20);
      hyphenL2.setBounds(220, 230, 10, 20);
      addrL.setBounds(20, 270, 80, 20);
      emailL.setBounds(20, 310, 80, 20);
      certifiedNumL.setBounds(90, 370, 80, 20);
      
      //JTextField
      idT = new JTextField(8);
      pwT1 = new JTextField(8);
      pwT2 = new JTextField(8);
      nameT = new JTextField(8);
      telT1 = new JTextField(4);
      telT2 = new JTextField(4);
      addrT = new JTextField(10);
      emailT = new JTextField(10);
      certifiedNumT = new JTextField(10);
      
      idT.setEnabled(false);
      nameT.setEnabled(false);

      
      idT.setBounds(90, 30, 100, 20);
      pwT1.setBounds(90, 70, 100, 20);
      pwT2.setBounds(120, 110, 100, 20);
      nameT.setBounds(90, 150, 70, 20);
      telT1.setBounds(165, 230, 40, 20);
      telT2.setBounds(235, 230, 40, 20);
      addrT.setBounds(90, 270, 210, 20);
      emailT.setBounds(90, 310, 190, 20);
      certifiedNumT.setBounds(160, 370, 80, 20);
      
      //JComboBox
      String[] number = new String[] { "010", "011", "017", "019" };
      telCB = new JComboBox<String>(number);
      
      telCB.setBounds(90, 230, 50, 20);
      
      //JRadioButton & ButtonGroup
      ButtonGroup group = new ButtonGroup();
      male = new JRadioButton("남성", true);
      female = new JRadioButton("여성");
      group.add(male);
      group.add(female);
      male.setEnabled(false);
      female.setEnabled(false);
      
      male.setBounds(90, 190, 60, 20);
      female.setBounds(150, 190, 60, 20);
      
      //JButton
      overlapB = new JButton("중복검사");
      CertifiedB = new JButton("인증번호 받기");
      joinB = new JButton("가입");
      cancelB = new JButton("취소");
      
      overlapB.setEnabled(false);
      
      overlapB.setBounds(210, 30, 90, 20);
      CertifiedB.setBounds(125, 340, 120, 20);
      joinB.setBounds(90, 400, 70, 20);
      cancelB.setBounds(180, 400, 70, 20);
      
      //Container
      Container con = this.getContentPane();
      
      //아이디, 중복버튼
      con.add(idL);
      con.add(idT);
      con.add(overlapB);
      
      //비밀번호
      con.add(pwL1);
      con.add(pwT1);
      
      //비밀번호 확인
      con.add(pwL2);
      con.add(pwT2);
      
      //이름
      con.add(nameL);
      con.add(nameT);
      
      //성별
      con.add(genderL);
      con.add(male);
      con.add(female);
      
      //전화번호
      con.add(telL);
      con.add(telCB);
      con.add(hyphenL1);
      con.add(telT1);
      con.add(hyphenL2);
      con.add(telT2);
      
      //주소
      con.add(addrL);
      con.add(addrT);
      
      //이메일, 인증번호 버튼
      con.add(emailL);
      con.add(emailT);
      con.add(CertifiedB);
      
      //인증번호
      con.add(certifiedNumL);
      con.add(certifiedNumT);
      
      //가입, 취소 버튼
      con.add(joinB);
      con.add(cancelB);
      
      setBounds(1100, 200, 350, 500);
      //con.setBackground(new Color(200, 191, 231));
      setVisible(true);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE); //현재 창 닫는다.
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
   }
   
   public static void main(String[] args) {
	   MemberDTO memberDTO = new MemberDTO();
	   new ChangeInfo(memberDTO);
   }

}
