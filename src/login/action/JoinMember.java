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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import login.bean.MemberDTO;
import login.dao.MemberDAO;
import login.action.SendEmail;

public class JoinMember extends JFrame implements ActionListener {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, pwT1, pwT2, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
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
		pwL1 = new JLabel("��й�ȣ : ");
		pwL2 = new JLabel("��й�ȣ Ȯ�� : ");
		nameL = new JLabel("��      �� : ");
		genderL = new JLabel("��      �� : ");
		telL = new JLabel("��ȭ��ȣ : ");
		hyphenL1 = new JLabel("-");
		hyphenL2 = new JLabel("-");
		addrL = new JLabel("��      �� : ");
		emailL = new JLabel("�̸��� : ");
		certifiedNumL = new JLabel("������ȣ : ");

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
		certifiedNumL.setBounds(20, 370, 80, 20);

		// JTextField
		idT = new JTextField(8);
		pwT1 = new JTextField(8);
		pwT2 = new JTextField(8);
		nameT = new JTextField(8);
		telT1 = new JTextField(4);
		telT2 = new JTextField(4);
		addrT = new JTextField(10);
		emailT = new JTextField(10);
		certifiedNumT = new JTextField(10);

		idT.setBounds(90, 30, 100, 20);
		pwT1.setBounds(90, 70, 100, 20);
		pwT2.setBounds(120, 110, 100, 20);
		nameT.setBounds(90, 150, 70, 20);
		telT1.setBounds(165, 230, 40, 20);
		telT2.setBounds(235, 230, 40, 20);
		addrT.setBounds(90, 270, 210, 20);
		emailT.setBounds(90, 310, 190, 20);
		certifiedNumT.setBounds(90, 370, 80, 20);

		// JComboBox
		telCB = new JComboBox<String>();
		telCB.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"010", "011", "017", "019"}));

		telCB.setBounds(90, 230, 50, 20);

		// JRadioButton & ButtonGroup
		ButtonGroup group = new ButtonGroup();
		male = new JRadioButton("����", true);
		female = new JRadioButton("����");
		group.add(male);
		group.add(female);

		male.setBounds(90, 190, 60, 20);
		female.setBounds(150, 190, 60, 20);

		// JButton
		overlapB = new JButton("�ߺ��˻�");
		CertifiedB = new JButton("������ȣ �ޱ�");
		joinB = new JButton("����");
		cancelB = new JButton("���");
		numberconfirmB = new JButton("���� Ȯ��");

		overlapB.setBounds(210, 30, 90, 20);
		CertifiedB.setBounds(125, 340, 120, 20);
		joinB.setBounds(90, 420, 70, 20);
		cancelB.setBounds(180, 420, 70, 20);
		numberconfirmB.setBounds(180, 370, 120, 20);

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

		setBounds(1100, 200, 350, 500);
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
			joinCheak2 = false;
			// System.out.println(emailT.getText()); //�̸��� �޾������� Ȯ��
			auth = new SendEmail(emailT.getText(), "������ ������ȣ").getAuth();
			System.out.println("������ȣ : " + auth);// ������ȣ Ȯ��
		} // CertifiedB

		// ���� Ȯ���� ��������
		else if (e.getSource() == numberconfirmB) {
			if (joinCheak2 == false) {// �����ޱ� ��ư�� ���� ����
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
			if (idT.getText().isEmpty() || pwT1.getText().isEmpty() || pwT2.getText().isEmpty()
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
		if (pwT1.getText().equals(pwT2.getText())) {

			String id = idT.getText();
			String pw1 = pwT1.getText();
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

	}

	public static void main(String[] args) {

		JoinMember memberMain = new JoinMember();
		memberMain.event();
	}

}
