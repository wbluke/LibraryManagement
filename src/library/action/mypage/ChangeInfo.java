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

public class ChangeInfo extends JFrame implements ActionListener {
	private JLabel idL, pwL1, pwL2, nameL, genderL, telL, hyphenL1, hyphenL2, addrL, emailL, certifiedNumL;
	private JTextField idT, nameT, telT1, telT2, addrT, emailT, certifiedNumT;
	private JPasswordField pwT1, pwT2;
	private JRadioButton male, female;
	private JComboBox<String> telCB;
	private JButton CertifiedB, updateB, cancelB, numberconfirmB;
	private MemberDTO memberDTO;
	private boolean joinCheak2 = true; // ������ȣ ȸ������ �غ� �� �Ǿ��ִ��� �����ϸ� true
	private String auth = null;
	
	public ChangeInfo(MemberDTO memberDTO) {
		super("��������");
		getContentPane().setFont(new Font("���� ���", Font.BOLD, 12));
		
		this.memberDTO = memberDTO;
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		// JLable
		idL = new JLabel("�� �� �� : ");
		idL = new JLabel("��      ��      �� :");
		idL.setFont(new Font("���� ���", Font.BOLD, 12));
		pwL1 = new JLabel("��   ��  ��   ȣ : ");
		pwL1.setFont(new Font("���� ���", Font.BOLD, 12));
		pwL2 = new JLabel("��й�ȣ Ȯ�� : ");
		pwL2.setFont(new Font("���� ���", Font.BOLD, 12));
		nameL = new JLabel("��                �� : ");
		nameL.setFont(new Font("���� ���", Font.BOLD, 12));
		genderL = new JLabel("��                �� : ");
		genderL.setFont(new Font("���� ���", Font.BOLD, 12));
		telL = new JLabel("��  ȭ   ��   ȣ : ");
		telL.setFont(new Font("���� ���", Font.BOLD, 12));
		hyphenL1 = new JLabel("-");
		hyphenL1.setHorizontalAlignment(SwingConstants.CENTER);
		hyphenL2 = new JLabel("-");
		hyphenL2.setHorizontalAlignment(SwingConstants.CENTER);
		addrL = new JLabel("��                �� : ");
		addrL.setFont(new Font("���� ���", Font.BOLD, 12));
		emailL = new JLabel("��      ��      �� : ");
		emailL.setFont(new Font("���� ���", Font.BOLD, 12));
		certifiedNumL = new JLabel("��   ��   ��   ȣ : ");
		certifiedNumL.setFont(new Font("���� ���", Font.BOLD, 12));

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
		idT.setFont(new Font("����", Font.PLAIN, 15));
		idT.setEditable(false);
		pwT1 = new JPasswordField(8);
		pwT1.setFont(new Font("����", Font.PLAIN, 15));
		pwT2 = new JPasswordField(8);
		pwT2.setFont(new Font("����", Font.PLAIN, 15));
		nameT = new JTextField(8);
		nameT.setFont(new Font("����", Font.PLAIN, 15));
		nameT.setEnabled(false);
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
		male = new JRadioButton("����", true);
		male.setEnabled(false);
		male.setFont(new Font("���� ���", Font.BOLD, 12));
		female = new JRadioButton("����");
		female.setEnabled(false);
		female.setFont(new Font("���� ���", Font.BOLD, 12));
		male.setBackground(Color.WHITE);
		female.setBackground(Color.WHITE);
		group.add(male);
		group.add(female);

		male.setBounds(134, 200, 60, 34);
		female.setBounds(194, 200, 60, 34);
		
		// JButton
		CertifiedB = new JButton("������ȣ �ޱ�");
		CertifiedB.setFont(new Font("���� ���", Font.BOLD, 12));
		updateB = new JButton("\uC218\uC815");
		updateB.setFont(new Font("���� ���", Font.BOLD, 12));
		cancelB = new JButton("���");
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		cancelB.setFont(new Font("���� ���", Font.BOLD, 12));
		numberconfirmB = new JButton("���� Ȯ��");
		numberconfirmB.setFont(new Font("���� ���", Font.BOLD, 12));

		CertifiedB.setBounds(134, 391, 202, 36);
		updateB.setBounds(266, 515, 70, 36);
		cancelB.setBounds(352, 515, 70, 36);
		numberconfirmB.setBounds(334, 436, 90, 37);
		
		

		// Container
		Container con = this.getContentPane();

		// ���̵�, �ߺ���ư
		con.add(idL);
		con.add(idT);
		
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
		con.add(updateB);
		con.add(cancelB);

		setBounds(620, 200, 460, 610);
		// con.setBackground(new Color(200, 191, 231));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ���� â �ݴ´�.
		
		
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
		
		if (e.getSource() == CertifiedB) {// ������ȣ �ޱ⸦ ��������
			if (emailT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
				return;
			}
			joinCheak2 = false;
			
			if(emailT.getText().indexOf("@") == -1) {
				JOptionPane.showMessageDialog(null, "�ùٸ� �̸����� �Է����ּ���.");
				return;
			}
			joinCheak2 = false;
			// System.out.println(emailT.getText()); //�̸��� �޾������� Ȯ��
			auth = new SendEmail(emailT.getText(), "������ ������ȣ").getAuth();
			System.out.println("������ȣ : " + auth);// ������ȣ Ȯ��
			JOptionPane.showMessageDialog(null, "������ȣ�� �ش� �̸��Ϸ� �߼��Ͽ����ϴ�.");
		} // CertifiedB

		
		else if (e.getSource() == numberconfirmB) {// ���� Ȯ���� ��������
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

		else if (e.getSource() == updateB) {// ������ ��������
			if (new String(pwT1.getPassword()).isEmpty() || new String(pwT2.getPassword()).isEmpty() || telT1.getText().isEmpty()
					|| telT2.getText().isEmpty() || addrT.getText().isEmpty() || certifiedNumT.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "�� ������ ä���ּ���.");
			} else {
				if (joinCheak2 == true) {// ������ȣ���� �� ����.
					update();
				} else {// ������ȣ�� ���� �ʴٸ�
					JOptionPane.showMessageDialog(null, "������ȣ�� Ȯ�����ּ���.");
				}
			}

		} // updateB

		// ���
		else if (e.getSource() == cancelB) {
			System.exit(0);
		}
	}

	public void update() {
		if (new String(pwT1.getPassword()).equals(new String(pwT2.getPassword()))) {

			String pw1 = new String(pwT1.getPassword());

			String telC = (String) telCB.getSelectedItem();// ��ȭ��ȣ
			String tel1 = telT1.getText();
			String tel2 = telT2.getText();

			String addr = addrT.getText();// �ּ�
			String email = emailT.getText();// �̸���

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
			JOptionPane.showMessageDialog(null, "���� ������ �Ϸ�Ǿ����ϴ�.");
		} // if
		else {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ��й�ȣ Ȯ���� ���� �ʽ��ϴ�.");
		}

	}

	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		ChangeInfo memberMain = new ChangeInfo(memberDTO);
		memberMain.event();
	}

}
