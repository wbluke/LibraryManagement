package login.action;

import java.awt.Container;
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

		getContentPane().setLayout(null);

		// JLable
		idL = new JLabel("아 이 디 : ");
		idL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pwL1 = new JLabel("비밀번호 : ");
		pwL1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pwL2 = new JLabel("비밀번호 확인 : ");
		pwL2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		nameL = new JLabel("이      름 : ");
		nameL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		genderL = new JLabel("성      별 : ");
		genderL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		telL = new JLabel("전화번호 : ");
		telL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		hyphenL1 = new JLabel("-");
		hyphenL2 = new JLabel("-");
		addrL = new JLabel("주      소 : ");
		addrL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		emailL = new JLabel("\uC774  \uBA54  \uC77C : ");
		emailL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		certifiedNumL = new JLabel("인증번호 : ");
		certifiedNumL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		idL.setBounds(20, 29, 80, 20);
		pwL1.setBounds(20, 66, 80, 20);
		pwL2.setBounds(20, 101, 90, 20);
		nameL.setBounds(20, 136, 80, 20);
		genderL.setBounds(20, 168, 80, 20);
		telL.setBounds(20, 199, 80, 20);
		hyphenL1.setBounds(170, 200, 10, 20);
		hyphenL2.setBounds(245, 200, 10, 20);
		addrL.setBounds(20, 235, 80, 20);
		emailL.setBounds(20, 270, 80, 20);
		certifiedNumL.setBounds(20, 353, 80, 20);

		// JTextField
		idT = new JTextField(8);
		pwT1 = new JPasswordField(8);
		pwT2 = new JPasswordField(8);
		nameT = new JTextField(8);
		telT1 = new JTextField(4);
		telT2 = new JTextField(4);
		addrT = new JTextField(10);
		emailT = new JTextField(10);
		certifiedNumT = new JTextField(10);

		idT.setBounds(110, 30, 100, 25);
		pwT1.setBounds(110, 65, 100, 25);
		pwT2.setBounds(110, 100, 100, 25);
		nameT.setBounds(110, 135, 100, 25);
		telT1.setBounds(188, 198, 45, 25);
		telT2.setBounds(267, 198, 45, 25);
		addrT.setBounds(110, 234, 202, 25);
		emailT.setBounds(110, 269, 202, 25);
		certifiedNumT.setBounds(110, 352, 100, 25);

		// JComboBox
		telCB = new JComboBox<String>();
		telCB.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"010", "011", "017", "019"}));

		telCB.setBounds(110, 199, 50, 25);

		// JRadioButton & ButtonGroup
		ButtonGroup group = new ButtonGroup();
		male = new JRadioButton("남성", true);
		male.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		female = new JRadioButton("여성");
		female.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		group.add(male);
		group.add(female);

		male.setBounds(110, 166, 60, 25);
		female.setBounds(170, 166, 60, 25);

		// JButton
		overlapB = new JButton("중복검사");
		overlapB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CertifiedB = new JButton("인증번호 받기");
		CertifiedB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		joinB = new JButton("가입");
		joinB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cancelB = new JButton("취소");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		numberconfirmB = new JButton("인증 확인");
		numberconfirmB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		overlapB.setBounds(222, 29, 90, 25);
		CertifiedB.setBounds(110, 304, 202, 25);
		joinB.setBounds(152, 399, 70, 36);
		cancelB.setBounds(242, 399, 70, 36);
		numberconfirmB.setBounds(222, 351, 90, 25);

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

		setBounds(1100, 200, 350, 491);
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {

		JoinMember memberMain = new JoinMember();
		memberMain.event();
	}

}
