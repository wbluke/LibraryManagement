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
	private boolean joinCheak; // �ߺ�üũ ���������� true
	private boolean joinCheak2 = true; // ������ȣ ȸ������ �غ� �� �Ǿ��ִ��� �����ϸ� true
	private String auth = null;

	public JoinMember() {
		super("ȸ������");

		getContentPane().setLayout(null);

		// JLable
		idL = new JLabel("�� �� �� : ");
		idL.setFont(new Font("���� ���", Font.PLAIN, 12));
		pwL1 = new JLabel("��й�ȣ : ");
		pwL1.setFont(new Font("���� ���", Font.PLAIN, 12));
		pwL2 = new JLabel("��й�ȣ Ȯ�� : ");
		pwL2.setFont(new Font("���� ���", Font.PLAIN, 12));
		nameL = new JLabel("��      �� : ");
		nameL.setFont(new Font("���� ���", Font.PLAIN, 12));
		genderL = new JLabel("��      �� : ");
		genderL.setFont(new Font("���� ���", Font.PLAIN, 12));
		telL = new JLabel("��ȭ��ȣ : ");
		telL.setFont(new Font("���� ���", Font.PLAIN, 12));
		hyphenL1 = new JLabel("-");
		hyphenL2 = new JLabel("-");
		addrL = new JLabel("��      �� : ");
		addrL.setFont(new Font("���� ���", Font.PLAIN, 12));
		emailL = new JLabel("\uC774  \uBA54  \uC77C : ");
		emailL.setFont(new Font("���� ���", Font.PLAIN, 12));
		certifiedNumL = new JLabel("������ȣ : ");
		certifiedNumL.setFont(new Font("���� ���", Font.PLAIN, 12));

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
		male = new JRadioButton("����", true);
		male.setFont(new Font("���� ���", Font.PLAIN, 12));
		female = new JRadioButton("����");
		female.setFont(new Font("���� ���", Font.PLAIN, 12));
		group.add(male);
		group.add(female);

		male.setBounds(110, 166, 60, 25);
		female.setBounds(170, 166, 60, 25);

		// JButton
		overlapB = new JButton("�ߺ��˻�");
		overlapB.setFont(new Font("���� ���", Font.PLAIN, 12));
		CertifiedB = new JButton("������ȣ �ޱ�");
		CertifiedB.setFont(new Font("���� ���", Font.PLAIN, 12));
		joinB = new JButton("����");
		joinB.setFont(new Font("���� ���", Font.PLAIN, 12));
		cancelB = new JButton("���");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("���� ���", Font.PLAIN, 12));
		numberconfirmB = new JButton("���� Ȯ��");
		numberconfirmB.setFont(new Font("���� ���", Font.PLAIN, 12));

		overlapB.setBounds(222, 29, 90, 25);
		CertifiedB.setBounds(110, 304, 202, 25);
		joinB.setBounds(152, 399, 70, 36);
		cancelB.setBounds(242, 399, 70, 36);
		numberconfirmB.setBounds(222, 351, 90, 25);

		// Container
		Container con = this.getContentPane();

		// ���̵�, �ߺ���ư
		con.add(idL);
		con.add(idT);
		con.add(overlapB);

		// ��й�ȣ
		con.add(pwL1);
		con.add(pwT1);

		// ��й�ȣ Ȯ��
		con.add(pwL2);
		con.add(pwT2);

		// �̸�
		con.add(nameL);
		con.add(nameT);

		// ����
		con.add(genderL);
		con.add(male);
		con.add(female);

		// ��ȭ��ȣ
		con.add(telL);
		con.add(telCB);
		con.add(hyphenL1);
		con.add(telT1);
		con.add(hyphenL2);
		con.add(telT2);

		// �ּ�
		con.add(addrL);
		con.add(addrT);

		// �̸���, ������ȣ ��ư
		con.add(emailL);
		con.add(emailT);
		con.add(CertifiedB);

		// ������ȣ
		con.add(certifiedNumL);
		con.add(certifiedNumT);
		con.add(numberconfirmB);

		// ����, ��� ��ư
		con.add(joinB);
		con.add(cancelB);

		setBounds(1100, 200, 350, 491);
		// con.setBackground(new Color(200, 191, 231));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ���� â �ݴ´�.
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

		// �ߺ��˻縦 ��������
		if (e.getSource() == overlapB) {
			String target_id = idT.getText();
			MemberDAO memberDAO = MemberDAO.getInstance();
			if (target_id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "������ ����� �� �����ϴ�.");
			} else if (memberDAO.checkID(target_id)) {
				JOptionPane.showMessageDialog(null, "����� �� �ִ� ���̵��Դϴ�.");
				joinCheak = true;
			} else {
				JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �Դϴ�.");
				joinCheak = false;
			}
		} // overlapB

		// ������ȣ �ޱ⸦ ��������
		else if (e.getSource() == CertifiedB) {
			if (emailT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
				return;
			}
			joinCheak2 = false;
			
			if(emailT.getText().indexOf("@") == -1) {
				JOptionPane.showMessageDialog(null, "�ùٸ� �̸����� �Է����ּ���.");
				return;
			}
			// System.out.println(emailT.getText()); //�̸��� �޾������� Ȯ��
			auth = new SendEmail(emailT.getText(), "������ ������ȣ").getAuth();
			System.out.println("������ȣ : " + auth);// ������ȣ Ȯ��
			JOptionPane.showMessageDialog(null, "������ȣ�� �ش� �̸��Ϸ� �߼��Ͽ����ϴ�.");
		} // CertifiedB

		// ���� Ȯ���� ��������
		else if (e.getSource() == numberconfirmB) {
			if (certifiedNumT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������ȣ�� �Է����ּ���.");
				return;
			}
			if (joinCheak2 == false) {// �����ޱ� ��ư�� ���� ����
				if (auth == null) {
					JOptionPane.showMessageDialog(null, "���������� �߼����ּ���.");
					return;
				}
				if (auth.equals(certifiedNumT.getText())) {
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
					joinCheak2 = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "������ȣ�� Ȯ�����ּ���.");
			}
		} // numberconfirmB

		// ������ ��������
		else if (e.getSource() == joinB) {
			if (idT.getText().isEmpty() || new String(pwT1.getPassword()).isEmpty() || new String(pwT2.getPassword()).isEmpty()
					|| nameT.getText().isEmpty() || telT1.getText().isEmpty() || telT2.getText().isEmpty()
					|| addrT.getText().isEmpty() || certifiedNumT.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "�� ������ ä���ּ���.");
			} else {
				if (joinCheak == true && joinCheak2 == true) {// ���̵� ������ȣ �� �� ���� �� ����.
					join();
				} else if (joinCheak2 == false) {// ������ȣ�� ���� �ʴٸ�
					JOptionPane.showMessageDialog(null, "������ȣ�� �Է����ּ���.");
				} else if (joinCheak == false) {// ���̵� �������� �ִٸ�
					JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� �� �ּ���.");
				}
			}

		} // joinB

		// ���
		else if (e.getSource() == cancelB) {
			System.exit(0);
		}
	}

	private void join() {
		// ������ ������
		if (new String(pwT1.getPassword()).equals(new String(pwT2.getPassword()))) {

			String id = idT.getText();
			String pw1 = new String(pwT1.getPassword());
			String name = nameT.getText();

			int gender = 0; // ����
			if (male.isSelected())
				gender = 0; // ������ �����϶�
			else if (female.isSelected())
				gender = 1; // ������ �����϶�

			String telC = (String) telCB.getSelectedItem();// ��ȭ��ȣ
			String tel1 = telT1.getText();
			String tel2 = telT2.getText();

			String addr = addrT.getText();// �ּ�
			String email = emailT.getText();// �̸���

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
			int seq = memberDAO.getSeq(); // ������ �����
			memberDTO.setSeq(seq);

			memberDAO.join(memberDTO);
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
		} // if
		else {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ��й�ȣ Ȯ���� ���� �ʽ��ϴ�.");
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
