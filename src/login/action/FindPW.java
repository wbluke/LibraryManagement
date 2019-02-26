package login.action;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import login.dao.LoginDAO;
import java.awt.Font;

public class FindPW extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idT;
	private JTextField nameT;
	private JTextField emailT;
	private JButton pwFindB;

	public FindPW() {
		setTitle("��й�ȣ ã��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1100, 200, 298, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel idL = new JLabel("�� �� �� : ");
		idL.setFont(new Font("���� ���", Font.PLAIN, 12));
		idL.setBounds(12, 24, 90, 30);
		panel.add(idL);
		
		JLabel nameL = new JLabel("��      �� : ");
		nameL.setFont(new Font("���� ���", Font.PLAIN, 12));
		nameL.setBounds(12, 64, 90, 30);
		panel.add(nameL);
		
		pwFindB = new JButton("��й�ȣ ã��");
		pwFindB.setFont(new Font("���� ���", Font.PLAIN, 12));
		pwFindB.setBounds(141, 151, 120, 40);
		panel.add(pwFindB);
		
		idT = new JTextField();
		idT.setBounds(80, 26, 100, 30);
		idT.setColumns(10);
		panel.add(idT);
		
		nameT = new JTextField();
		nameT.setBounds(80, 66, 100, 30);
		nameT.setColumns(10);
		panel.add(nameT);
		
		JLabel emailL = new JLabel("�� �� �� : ");
		emailL.setFont(new Font("���� ���", Font.PLAIN, 12));
		emailL.setBounds(12, 104, 90, 30);
		panel.add(emailL);
		
		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(80, 106, 181, 30);
		panel.add(emailT);
		
		pwFindB.addActionListener(this);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
      });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pwFindB) {
			String id = idT.getText();
			String name = nameT.getText();
			String email = emailT.getText();
			
			LoginDAO loginDAO = LoginDAO.getInstance();
			
			String pw_Find = loginDAO.pwFind(id, name, email);
			
			if(pw_Find != null){
				new SendEmail(email, "[������ ȸ�� ��й�ȣ]", "ȸ������ ��й�ȣ�� [ " + pw_Find + " ] �Դϴ�.");
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �̸��Ϸ� �߼۵Ǿ����ϴ�.");
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} else {
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
				} else if(name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
				} else if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
				} else {
					JOptionPane.showMessageDialog(null, "�ش� ȸ�� ������ �����ϴ�.\n�Է��Ͻ� ������ Ȯ�����ּ���.");
				}
			}
		}
	}

	public static void main(String[] args) {
		new FindPW();
		
	}
}
