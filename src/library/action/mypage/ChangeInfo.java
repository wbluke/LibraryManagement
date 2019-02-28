package library.action.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import login.action.SendEmail;
import login.bean.MemberDTO;
import login.dao.MemberDAO;

public class ChangeInfo extends JFrame {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
	private JPasswordField pwT1, pwT2;
	private JRadioButton male, female;
	private JComboBox<String> telCB;
	private JButton CertifiedB, updateB, cancelB, numberconfirmB;
	private MemberDTO memberDTO;
	
	private String auth = null;
	
	private boolean pwCheck = false;
	private boolean telCheck = false;
	private boolean authCheck = false;
	
	public ChangeInfo(MemberDTO memberDTO) {
		super("정보변경");
		getContentPane().setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		this.memberDTO = memberDTO;
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		// JLable
		idL = new JLabel("아 이 디 : ");
		idL = new JLabel("아      이      디 :");
		idL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		pwL1 = new JLabel("비   밀  번   호 : ");
		pwL1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		pwL2 = new JLabel("비밀번호 확인 : ");
		pwL2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		nameL = new JLabel("이                름 : ");
		nameL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		genderL = new JLabel("성                별 : ");
		genderL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		telL = new JLabel("전  화   번   호 : ");
		telL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		hyphenL1 = new JLabel("-");
		hyphenL1.setHorizontalAlignment(SwingConstants.CENTER);
		hyphenL2 = new JLabel("-");
		hyphenL2.setHorizontalAlignment(SwingConstants.CENTER);
		addrL = new JLabel("주                소 : ");
		addrL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		emailL = new JLabel("이      메      일 : ");
		emailL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		certifiedNumL = new JLabel("인   증   번   호 : ");
		certifiedNumL.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		idL.setBounds(14, 18, 108, 36);
		pwL1.setBounds(14, 66, 108, 32);
		pwL2.setBounds(14, 108, 114, 32);
		nameL.setBounds(14, 152, 108, 32);
		genderL.setBounds(14, 202, 108, 31);
		telL.setBounds(14, 243, 108, 39);
		hyphenL1.setBounds(195, 240, 16, 39);
		hyphenL2.setBounds(287, 243, 16, 39);
		addrL.setBounds(14, 296, 108, 37);
		emailL.setBounds(14, 352, 108, 20);
		certifiedNumL.setBounds(14, 445, 108, 20);

		// JTextField
		idT = new JTextField(8);
		idT.setFont(new Font("굴림", Font.PLAIN, 15));
		idT.setEditable(false);
		pwT1 = new JPasswordField(8);
		pwT1.setFont(new Font("굴림", Font.PLAIN, 15));
		pwT2 = new JPasswordField(8);
		pwT2.setFont(new Font("굴림", Font.PLAIN, 15));
		nameT = new JTextField(8);
		nameT.setFont(new Font("굴림", Font.PLAIN, 15));
		nameT.setEnabled(false);
		telT1 = new JTextField(4);
		telT1.setFont(new Font("굴림", Font.PLAIN, 15));
		telT2 = new JTextField(4);
		telT2.setFont(new Font("굴림", Font.PLAIN, 15));
		addrT = new JTextField(10);
		addrT.setFont(new Font("굴림", Font.PLAIN, 15));
		emailT = new JTextField(10);
		emailT.setFont(new Font("굴림", Font.PLAIN, 15));
		certifiedNumT = new JTextField(10);
		certifiedNumT.setFont(new Font("굴림", Font.PLAIN, 15));

		idT.setBounds(134, 18, 168, 36);
		pwT1.setBounds(134, 65, 168, 34);
		pwT2.setBounds(134, 107, 168, 34);
		nameT.setBounds(134, 151, 166, 34);
		telT1.setBounds(216, 243, 70, 38);
		telT2.setBounds(304, 243, 70, 38);
		addrT.setBounds(134, 296, 288, 37);
		emailT.setBounds(134, 343, 290, 38);
		certifiedNumT.setBounds(134, 437, 188, 37);

		// JComboBox
		telCB = new JComboBox<String>();
		telCB.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"010", "011", "017", "019"}));

		telCB.setBounds(134, 244, 60, 36);

		// JRadioButton & ButtonGroup
		ButtonGroup group = new ButtonGroup();
		male = new JRadioButton("남성", true);
		male.setEnabled(false);
		male.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		female = new JRadioButton("여성");
		female.setEnabled(false);
		female.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		male.setBackground(Color.WHITE);
		female.setBackground(Color.WHITE);
		group.add(male);
		group.add(female);

		male.setBounds(134, 200, 60, 34);
		female.setBounds(194, 200, 60, 34);
		
		// JButton
		CertifiedB = new JButton("인증번호 받기");
		CertifiedB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// emailCheck
				if (emailT.getText().indexOf("@") == -1) {
					JOptionPane.showMessageDialog(null, "올바른 이메일을 입력해주세요.");
					return;
				}else {
					if (emailT.getText().indexOf("@") == emailT.getText().length() - 1 || emailT.getText().indexOf("@") == 0) {
						JOptionPane.showMessageDialog(null, "올바른 이메일을 입력해주세요.");		
						return;
					}else {
					auth = new SendEmail(emailT.getText(), "도서관 인증번호").getAuth();
					System.out.println("인증번호  : " + auth);
					JOptionPane.showMessageDialog(null, "인증번호를 해당 이메일로 발송하였습니다.");	
					numberconfirmB.setEnabled(true);
					}
				}
			}
		});
		CertifiedB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		updateB = new JButton("\uC218\uC815");
		updateB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pwCheck
				if (new String(pwT1.getPassword()).equals(new String(pwT2.getPassword()))) {
					if (new String(pwT1.getPassword()).trim().equals("") || new String(pwT2.getPassword()).trim().equals("")) {
						pwCheck = false;
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
						return;
					}
					pwCheck = true;
				}else {
					pwCheck = false;
					JOptionPane.showMessageDialog(null, "동일한 비밀번호가 아닙니다.\n다시 확인해주세요.");
					return;
				}
				
				// telCheck
				if (isInteger(telT1.getText()) && isInteger(telT2.getText())) {
					if (telT1.getText().trim().equals("") || telT2.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
						telCheck = false;
						return;
					}
					telCheck = true;
				}else {
					telCheck = false;
					JOptionPane.showMessageDialog(null, "전화번호 형식이 올바르지 않습니다.");
					return;
				}
				
				
				
				if (nameT.getText().trim().equals("") || addrT.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "공백을 채워주세요.");	
					return;
				}
				
				// 모든 조건이 체크되면 join()으로 보내기
				if (pwCheck && telCheck && authCheck) {
					update();
				}else if (pwCheck == false){
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 맞지 않습니다.");						
				}else if (telCheck == false){
					JOptionPane.showMessageDialog(null, "올바른 전화번호를 입력해주세요.");						
				}else if (authCheck == false){
					JOptionPane.showMessageDialog(null, "인증 확인을 해주세요.");						
				}
			}
		});
		updateB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cancelB = new JButton("취소");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		numberconfirmB = new JButton("인증 확인");
		numberconfirmB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auth.equals(certifiedNumT.getText())) {
					JOptionPane.showMessageDialog(null, "인증이 완료되었습니다.");	
					authCheck = true;
				}else {
					JOptionPane.showMessageDialog(null, "인증번호를 다시 확인해주세요.");	
					authCheck = false;
					return;
				}
			}
		});
		numberconfirmB.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		CertifiedB.setBounds(134, 391, 202, 36);
		updateB.setBounds(266, 515, 70, 36);
		cancelB.setBounds(352, 515, 70, 36);
		numberconfirmB.setBounds(334, 436, 90, 37);
		
		

		// Container
		Container con = this.getContentPane();

		// 아이디, 중복버튼
		con.add(idL);
		con.add(idT);
		
		// 비밀번호
		con.add(pwL1);
		con.add(pwT1);

		// 비밀번호 확인
		con.add(pwL2);
		con.add(pwT2);

		// 이름
		con.add(nameL);
		con.add(nameT);

		// 성별
		con.add(genderL);
		con.add(male);
		con.add(female);

		// 전화번호
		con.add(telL);
		con.add(telCB);
		con.add(hyphenL1);
		con.add(telT1);
		con.add(hyphenL2);
		con.add(telT2);

		// 주소
		con.add(addrL);
		con.add(addrT);

		// 이메일, 인증번호 버튼
		con.add(emailL);
		con.add(emailT);
		con.add(CertifiedB);

		// 인증번호
		con.add(certifiedNumL);
		con.add(certifiedNumT);
		con.add(numberconfirmB);

		// 가입, 취소 버튼
		con.add(updateB);
		con.add(cancelB);

		setBounds(620, 200, 460, 610);
		// con.setBackground(new Color(200, 191, 231));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 현재 창 닫는다.
		
		
		idT.setText(memberDTO.getMemberId());
		pwT1.setText(memberDTO.getPw());
		pwT2.setText(memberDTO.getPw());
		if (memberDTO.getGender() == 0) {
			male.setSelected(true);
		}else {
			female.setSelected(true);
		}
		nameT.setText(memberDTO.getMemberName());
		telCB.setSelectedItem(memberDTO.getTel1());
		telT1.setText(memberDTO.getTel2());
		telT2.setText(memberDTO.getTel3());
		addrT.setText(memberDTO.getAddress());
		emailT.setText(memberDTO.getEmail());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void update() {
		String pw1 = new String(pwT1.getPassword());

		String telC = (String) telCB.getSelectedItem();// 전화번호
		String tel1 = telT1.getText();
		String tel2 = telT2.getText();

		String addr = addrT.getText();// 주소
		String email = emailT.getText();// 이메일

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(idT.getText());
		memberDTO.setPw(pw1);
		memberDTO.setTel1(telC);
		memberDTO.setTel2(tel1);
		memberDTO.setTel3(tel2);

		memberDTO.setAddress(addr);
		memberDTO.setEmail(email);
		memberDTO.setOverdue(0);

		// DB
		MemberDAO memberDAO = MemberDAO.getInstance();

		memberDAO.update(memberDTO);
		JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다.");
		dispose();
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}

	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		ChangeInfo memberMain = new ChangeInfo(memberDTO);
	}

}
