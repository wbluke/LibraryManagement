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

public class JoinMember extends JFrame implements ActionListener {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
	private JPasswordField pwT1, pwT2;
	private JRadioButton male, female;
	private JComboBox<String> telCB;
	private JButton overlapB, CertifiedB, joinB, cancelB, numberconfirmB;
	private boolean joinCheak; // 중복체크 문제없으면 true
	private boolean joinCheak2 = true; // 인증번호 회원가입 준비 다 되어있는지 가능하면 true
	private String auth = null;

	public JoinMember() {
		super("회원가입");
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setBounds(1150, 200, 435, 556);
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
		overlapB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		CertifiedB = new JButton("인증번호 받기");
		CertifiedB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinB = new JButton("가입");
		joinB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cancelB = new JButton("취소");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		numberconfirmB = new JButton("인증 확인");
		numberconfirmB.setFont(new Font("맑은 고딕", Font.BOLD, 12));

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

	public void event() {
		overlapB.addActionListener(this);
		CertifiedB.addActionListener(this);
		numberconfirmB.addActionListener(this);
		joinB.addActionListener(this);
		cancelB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 중복검사를 눌렀을때
		if (e.getSource() == overlapB) {
			String target_id = idT.getText();
			MemberDAO memberDAO = MemberDAO.getInstance();
			if (target_id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "공백은 사용할 수 없습니다.");
			} else if (memberDAO.checkID(target_id)) {
				JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디입니다.");
				joinCheak = true;
			} else {
				JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
				joinCheak = false;
			}
		} // overlapB

		// 인증번호 받기를 눌렀을때
		else if (e.getSource() == CertifiedB) {
			if (emailT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
				return;
			}
			joinCheak2 = false;
			
			if(emailT.getText().indexOf("@") == -1) {
				JOptionPane.showMessageDialog(null, "올바른 이메일을 입력해주세요.");
				return;
			}
			// System.out.println(emailT.getText()); //이메일 받아지는지 확인
			auth = new SendEmail(emailT.getText(), "도서관 인증번호").getAuth();
			System.out.println("인증번호 : " + auth);// 인증번호 확인
			JOptionPane.showMessageDialog(null, "인증번호를 해당 이메일로 발송하였습니다.");
		} // CertifiedB

		// 인증 확인을 눌렀을때
		else if (e.getSource() == numberconfirmB) {
			if (certifiedNumT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "인증번호를 입력해주세요.");
				return;
			}
			if (joinCheak2 == false) {// 인증받기 버튼을 누른 상태
				if (auth == null) {
					JOptionPane.showMessageDialog(null, "인증메일을 발송해주세요.");
					return;
				}
				if (auth.equals(certifiedNumT.getText())) {
					JOptionPane.showMessageDialog(null, "인증이 완료되었습니다.");
					joinCheak2 = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "인증번호를 확인해주세요.");
			}
		} // numberconfirmB

		// 가입을 눌렀을때
		else if (e.getSource() == joinB) {
			if (idT.getText().isEmpty() || new String(pwT1.getPassword()).isEmpty() || new String(pwT2.getPassword()).isEmpty()
					|| nameT.getText().isEmpty() || telT1.getText().isEmpty() || telT2.getText().isEmpty()
					|| addrT.getText().isEmpty() || certifiedNumT.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "빈 공간을 채워주세요.");
			} else {
				if (joinCheak == true && joinCheak2 == true) {// 아이디 인증번호 둘 다 맞을 시 가입.
					join();
				} else if (joinCheak2 == false) {// 인증번호가 맞지 않다면
					JOptionPane.showMessageDialog(null, "인증번호를 입력해주세요.");
				} else if (joinCheak == false) {// 아이디가 같은것이 있다면
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해 주세요.");
				}
			}

		} // joinB

		// 취소
		else if (e.getSource() == cancelB) {
			System.exit(0);
		}
	}

	private void join() {
		// 데이터 얻어오기
		if (new String(pwT1.getPassword()).equals(new String(pwT2.getPassword()))) {

			String id = idT.getText();
			String pw1 = new String(pwT1.getPassword());
			String name = nameT.getText();

			int gender = 0; // 성별
			if (male.isSelected())
				gender = 0; // 성별이 남자일때
			else if (female.isSelected())
				gender = 1; // 성별이 여자일때

			String telC = (String) telCB.getSelectedItem();// 전화번호
			String tel1 = telT1.getText();
			String tel2 = telT2.getText();

			String addr = addrT.getText();// 주소
			String email = emailT.getText();// 이메일

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
		} // if
		else {
			JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 맞지 않습니다.");
		}
		idT.setText("");
		pwT1.setText("");
		pwT2.setText("");
		nameT.setText("");
		telCB.setSelectedItem("010");
		telT1.setText("");
		telT2.setText("");
		addrT.setText("");
		emailT.setText("");
		joinCheak = false;
		joinCheak2 = false;
		certifiedNumT.setText("");
		
		setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {

		JoinMember memberMain = new JoinMember();
		memberMain.event();
	}

}
