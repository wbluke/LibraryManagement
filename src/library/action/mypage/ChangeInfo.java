package library.action.mypage;

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

import login.action.SendEmail;
import login.bean.MemberDTO;
import login.dao.MemberDAO;

public class ChangeInfo extends JFrame implements ActionListener {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
	private JPasswordField pwT1, pwT2;
	private JRadioButton male, female;
	private JComboBox<String> telCB;
	private JButton CertifiedB, updateB, cancelB, numberconfirmB;
	private MemberDTO memberDTO;
	private boolean joinCheak2 = true; // 인증번호 회원가입 준비 다 되어있는지 가능하면 true
	private String auth = null;
	
	public ChangeInfo(MemberDTO memberDTO) {
		super("정보변경");

		this.memberDTO = memberDTO;
		
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
		idT.setEditable(false);
		pwT1 = new JPasswordField(8);
		pwT2 = new JPasswordField(8);
		nameT = new JTextField(8);
		nameT.setEnabled(false);
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
		male.setEnabled(false);
		male.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		female = new JRadioButton("여성");
		female.setEnabled(false);
		female.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		group.add(male);
		group.add(female);

		male.setBounds(110, 166, 60, 25);
		female.setBounds(170, 166, 60, 25);

		// JButton
		CertifiedB = new JButton("인증번호 받기");
		CertifiedB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		updateB = new JButton("\uC218\uC815");
		updateB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cancelB = new JButton("취소");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		numberconfirmB = new JButton("인증 확인");
		numberconfirmB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		CertifiedB.setBounds(110, 304, 202, 25);
		updateB.setBounds(152, 399, 70, 36);
		cancelB.setBounds(242, 399, 70, 36);
		numberconfirmB.setBounds(222, 351, 90, 25);
		
		

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

		setBounds(1100, 200, 350, 491);
		// con.setBackground(new Color(200, 191, 231));
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
	}

	public void event() {
		CertifiedB.addActionListener(this);
		numberconfirmB.addActionListener(this);
		updateB.addActionListener(this);
		cancelB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == CertifiedB) {// 인증번호 받기를 눌렀을때
			joinCheak2 = false;
			// System.out.println(emailT.getText()); //이메일 받아지는지 확인
			auth = new SendEmail(emailT.getText(), "도서관 인증번호").getAuth();
			System.out.println("인증번호 : " + auth);// 인증번호 확인
		} // CertifiedB

		
		else if (e.getSource() == numberconfirmB) {// 인증 확인을 눌렀을때
			if (joinCheak2 == false) {// 인증받기 버튼을 누른 상태
				if (auth.equals(certifiedNumT.getText())) {
					JOptionPane.showMessageDialog(null, "인증이 완료되었습니다.");
					joinCheak2 = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "인증번호를 확인해주세요.");
			}
		} // numberconfirmB

		else if (e.getSource() == updateB) {// 수정을 눌렀을때
			if (new String(pwT1.getPassword()).isEmpty() || new String(pwT2.getPassword()).isEmpty() || telT1.getText().isEmpty()
					|| telT2.getText().isEmpty() || addrT.getText().isEmpty() || certifiedNumT.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "빈 공간을 채워주세요.");
			} else {
				if (joinCheak2 == true) {// 인증번호맞을 시 가입.
					update();
				} else {// 인증번호가 맞지 않다면
					JOptionPane.showMessageDialog(null, "인증번호를 확인해주세요.");
				}
			}

		} // updateB

		// 취소
		else if (e.getSource() == cancelB) {
			System.exit(0);
		}
	}

	public void update() {
		if (new String(pwT1.getPassword()).equals(new String(pwT2.getPassword()))) {

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
		} // if
		else {
			JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 맞지 않습니다.");
		}

	}

	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		ChangeInfo memberMain = new ChangeInfo(memberDTO);
		memberMain.event();
	}

}
