package login.action;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

import login.bean.MemberDTO;
import login.dao.MemberDAO;
import login.action.SendEmail;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class JoinMember extends JFrame {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
	private JPasswordField pwT1, pwT2;
	private JRadioButton male, female;
	private JComboBox<String> telCB;
	private JButton overlapB, CertifiedB, joinB, cancelB, numberconfirmB;
	private String auth = null;
	
	private boolean idCheck = false;
	private boolean pwCheck = false;
	private boolean telCheck = false;
	private boolean authCheck = false;
	

	public JoinMember() {
		super("회원가입");
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setBounds(1150, 200, 435, 556);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
/*		setSize(420,570);
		
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙
*/	

		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 240));

		// JLable
		idL = new JLabel("아      이       디 : ");
		idL.setFont(new Font("Dialog", Font.BOLD, 12));
		pwL1 = new JLabel("비   밀   번   호 : ");
		pwL1.setFont(new Font("Dialog", Font.BOLD, 12));
		pwL2 = new JLabel("비밀번호 확인 : ");
		pwL2.setFont(new Font("Dialog", Font.BOLD, 12));
		nameL = new JLabel("이                 름 : ");
		nameL.setFont(new Font("Dialog", Font.BOLD, 12));
		genderL = new JLabel("성                 별 : ");
		genderL.setFont(new Font("Dialog", Font.BOLD, 12));
		telL = new JLabel("전   화   번   호 : ");
		telL.setFont(new Font("Dialog", Font.BOLD, 12));
		hyphenL1 = new JLabel("-");
		hyphenL1.setHorizontalAlignment(SwingConstants.CENTER);
		hyphenL2 = new JLabel("-");
		hyphenL2.setHorizontalAlignment(SwingConstants.CENTER);
		addrL = new JLabel("주                 소 : ");
		addrL.setFont(new Font("Dialog", Font.BOLD, 12));
		emailL = new JLabel("이      메       일 : ");
		emailL.setFont(new Font("Dialog", Font.BOLD, 12));
		certifiedNumL = new JLabel("인   증   번   호 : ");
		certifiedNumL.setFont(new Font("Dialog", Font.BOLD, 12));

		idL.setBounds(28, 20, 96, 36);
		pwL1.setBounds(28, 66, 92, 36);
		pwL2.setBounds(28, 112, 92, 36);
		nameL.setBounds(28, 158, 92, 36);
		genderL.setBounds(28, 196, 92, 36);
		telL.setBounds(28, 232, 92, 36);
		hyphenL1.setBounds(196, 239, 17, 20);
		hyphenL2.setBounds(273, 239, 17, 20);
		addrL.setBounds(28, 278, 92, 36);
		emailL.setBounds(28, 324, 92, 36);
		certifiedNumL.setBounds(28, 415, 92, 36);

		// JTextField
		idT = new JTextField(8);
		idT.setFont(new Font("굴림", Font.PLAIN, 15));
		pwT1 = new JPasswordField(8);
		pwT1.setFont(new Font("굴림", Font.PLAIN, 15));
		pwT2 = new JPasswordField(8);
		pwT2.setFont(new Font("굴림", Font.PLAIN, 15));
		nameT = new JTextField(8);
		nameT.setFont(new Font("굴림", Font.PLAIN, 15));
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

		idT.setBounds(136, 20, 166, 36);
		pwT1.setBounds(136, 66, 166, 36);
		pwT2.setBounds(136, 112, 166, 36);
		nameT.setBounds(136, 158, 166, 36);
		telT1.setBounds(213, 231, 60, 36);
		telT2.setBounds(292, 232, 60, 36);
		addrT.setBounds(136, 277, 268, 36);
		emailT.setBounds(136, 323, 268, 36);
		certifiedNumT.setBounds(136, 415, 166, 36);

		// JComboBox
		telCB = new JComboBox<String>();
		telCB.setFont(new Font("굴림", Font.PLAIN, 15));
		telCB.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"010", "011", "017", "019"}));

		telCB.setBounds(136, 231, 60, 36);

		// JRadioButton & ButtonGroup
		ButtonGroup group = new ButtonGroup();
		male = new JRadioButton("남성", true);
		male.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		female = new JRadioButton("여성");
		female.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		male.setBackground(new Color(255, 255, 240));
		female.setBackground(new Color(255, 255, 240));
		
		group.add(male);
		group.add(female);

		male.setBounds(136, 200, 60, 25);
		female.setBounds(196, 200, 60, 25);

		// JButton
		overlapB = new JButton("중복검사");
		overlapB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberDTO member = memberDAO.searchByID(idT.getText());
				if (member.getMemberId() == null) {
					if (idT.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
						idCheck = false;
						return;
					}
					JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.");
					idCheck = true;
				}else {					
					JOptionPane.showMessageDialog(null, "중복되는 ID 입니다. 다른 ID를 입력해주세요.");
					idCheck = false;
				}
			}
		});
		overlapB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
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
		joinB = new JButton("가입");
		joinB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idT.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
					idCheck = false;
					return;
				}
				
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
				if (idCheck && pwCheck && telCheck && authCheck) {
					join();
				}else if (idCheck == false){
					JOptionPane.showMessageDialog(null, "아이디 중복 검사를 해주세요.");						
				}else if (pwCheck == false){
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 맞지 않습니다.");						
				}else if (telCheck == false){
					JOptionPane.showMessageDialog(null, "올바른 전화번호를 입력해주세요.");						
				}else if (authCheck == false){
					JOptionPane.showMessageDialog(null, "인증 확인을 해주세요.");						
				}
				
			}
		});
		
		joinB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
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
		numberconfirmB.setEnabled(false);

		overlapB.setBounds(314, 18, 90, 36);
		CertifiedB.setBounds(292, 369, 112, 36);
		joinB.setBounds(252, 481, 70, 36);
		cancelB.setBounds(334, 481, 70, 36);
		numberconfirmB.setBounds(314, 415, 90, 36);

		// Container
		Container con = this.getContentPane();

		// 아이디, 중복버튼
		con.add(idL);
		con.add(idT);
		con.add(overlapB);

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
		con.add(joinB);
		con.add(cancelB);

		// con.setBackground(new Color(200, 191, 231));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 현재 창 닫는다.
	}

	

	private void join() {
		String id = idT.getText().trim();
		String pw1 = new String(pwT1.getPassword());
		String name = nameT.getText().trim();

		int gender = 0; // 성별
		if (male.isSelected())
			gender = 0; // 성별이 남자일때
		else if (female.isSelected())
			gender = 1; // 성별이 여자일때

		String telC = (String) telCB.getSelectedItem();// 전화번호
		String tel1 = telT1.getText().trim();
		String tel2 = telT2.getText().trim();

		String addr = addrT.getText().trim();// 주소
		String email = emailT.getText().trim();// 이메일

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(id);
		memberDTO.setPw(pw1);
		memberDTO.setMemberName(name);
		memberDTO.setGender(gender);
		memberDTO.setTel1(telC);
		memberDTO.setTel2(tel1);
		memberDTO.setTel3(tel2);

		memberDTO.setAddress(addr);
		memberDTO.setEmail(email);
		memberDTO.setOverdue(0);

		// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		int seq = memberDAO.getSeq(); // 시퀸스 만들기
		memberDTO.setSeq(seq);

		memberDAO.join(memberDTO);
		JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
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

		new JoinMember();
		
	}

}
