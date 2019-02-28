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
	private boolean joinCheak; // �ߺ�üũ ���������� true
	private boolean joinCheak2 = true; // ������ȣ ȸ������ �غ� �� �Ǿ��ִ��� �����ϸ� true
	private String auth = null;

	public JoinMember() {
		super("ȸ������");
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setBounds(1150, 200, 435, 556);
/*		setSize(420,570);
		
		Dimension frameSize = this.getSize(); // ������ ������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ����� ������

		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // ȭ�� �߾�
*/	

		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 240));

		// JLable
		idL = new JLabel("��      ��       �� : ");
		idL.setFont(new Font("Dialog", Font.BOLD, 12));
		pwL1 = new JLabel("��   ��   ��   ȣ : ");
		pwL1.setFont(new Font("Dialog", Font.BOLD, 12));
		pwL2 = new JLabel("��й�ȣ Ȯ�� : ");
		pwL2.setFont(new Font("Dialog", Font.BOLD, 12));
		nameL = new JLabel("��                 �� : ");
		nameL.setFont(new Font("Dialog", Font.BOLD, 12));
		genderL = new JLabel("��                 �� : ");
		genderL.setFont(new Font("Dialog", Font.BOLD, 12));
		telL = new JLabel("��   ȭ   ��   ȣ : ");
		telL.setFont(new Font("Dialog", Font.BOLD, 12));
		hyphenL1 = new JLabel("-");
		hyphenL1.setHorizontalAlignment(SwingConstants.CENTER);
		hyphenL2 = new JLabel("-");
		hyphenL2.setHorizontalAlignment(SwingConstants.CENTER);
		addrL = new JLabel("��                 �� : ");
		addrL.setFont(new Font("Dialog", Font.BOLD, 12));
		emailL = new JLabel("��      ��       �� : ");
		emailL.setFont(new Font("Dialog", Font.BOLD, 12));
		certifiedNumL = new JLabel("��   ��   ��   ȣ : ");
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
		idT.setFont(new Font("����", Font.PLAIN, 15));
		pwT1 = new JPasswordField(8);
		pwT1.setFont(new Font("����", Font.PLAIN, 15));
		pwT2 = new JPasswordField(8);
		pwT2.setFont(new Font("����", Font.PLAIN, 15));
		nameT = new JTextField(8);
		nameT.setFont(new Font("����", Font.PLAIN, 15));
		telT1 = new JTextField(4);
		telT1.setFont(new Font("����", Font.PLAIN, 15));
		telT2 = new JTextField(4);
		telT2.setFont(new Font("����", Font.PLAIN, 15));
		addrT = new JTextField(10);
		addrT.setFont(new Font("����", Font.PLAIN, 15));
		emailT = new JTextField(10);
		emailT.setFont(new Font("����", Font.PLAIN, 15));
		certifiedNumT = new JTextField(10);
		certifiedNumT.setFont(new Font("����", Font.PLAIN, 15));

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
		telCB.setFont(new Font("����", Font.PLAIN, 15));
		telCB.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"010", "011", "017", "019"}));

		telCB.setBounds(136, 231, 60, 36);

		// JRadioButton & ButtonGroup
		ButtonGroup group = new ButtonGroup();
		male = new JRadioButton("����", true);
		male.setFont(new Font("���� ���", Font.PLAIN, 12));
		female = new JRadioButton("����");
		female.setFont(new Font("���� ���", Font.PLAIN, 12));
		male.setBackground(new Color(255, 255, 240));
		female.setBackground(new Color(255, 255, 240));
		
		group.add(male);
		group.add(female);

		male.setBounds(136, 200, 60, 25);
		female.setBounds(196, 200, 60, 25);

		// JButton
		overlapB = new JButton("�ߺ��˻�");
		overlapB.setFont(new Font("���� ���", Font.BOLD, 12));
		CertifiedB = new JButton("������ȣ �ޱ�");
		CertifiedB.setFont(new Font("���� ���", Font.BOLD, 12));
		joinB = new JButton("����");
		joinB.setFont(new Font("���� ���", Font.BOLD, 12));
		cancelB = new JButton("���");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("���� ���", Font.BOLD, 12));
		numberconfirmB = new JButton("���� Ȯ��");
		numberconfirmB.setFont(new Font("���� ���", Font.BOLD, 12));

		overlapB.setBounds(314, 18, 90, 36);
		CertifiedB.setBounds(292, 369, 112, 36);
		joinB.setBounds(252, 481, 70, 36);
		cancelB.setBounds(334, 481, 70, 36);
		numberconfirmB.setBounds(314, 415, 90, 36);

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {

		JoinMember memberMain = new JoinMember();
		memberMain.event();
	}

}
