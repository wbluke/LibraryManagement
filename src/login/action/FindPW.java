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

public class FindPW extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idT;
	private JTextField nameT;
	private JTextField emailT;
	private JButton pwFindB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPW frame = new FindPW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FindPW() {
		setTitle("��й�ȣ ã��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1100, 200, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel idL = new JLabel("�� �� �� : ");
		idL.setBounds(35, 40, 90, 30);
		panel.add(idL);
		
		JLabel nameL = new JLabel("��      �� : ");
		nameL.setBounds(35, 80, 90, 30);
		panel.add(nameL);
		
		pwFindB = new JButton("��й�ȣ ã��");
		pwFindB.setBounds(80, 200, 120, 40);
		panel.add(pwFindB);
		
		idT = new JTextField();
		idT.setBounds(110, 40, 100, 30);
		idT.setColumns(10);
		panel.add(idT);
		
		nameT = new JTextField();
		nameT.setBounds(110, 80, 100, 30);
		nameT.setColumns(10);
		panel.add(nameT);
		
		JLabel emailL = new JLabel("�� �� �� : ");
		emailL.setBounds(35, 120, 90, 30);
		panel.add(emailL);
		
		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(110, 120, 145, 30);
		panel.add(emailT);
		
		pwFindB.addActionListener(this);
		
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
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ���Ϸ� �߼۵Ǿ����ϴ�.");
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} else {
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
				} else if(name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
				} else if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
				} else {
					JOptionPane.showMessageDialog(null, "ȸ���� �ƴմϴ�.");
				}
			}
		}
	}

}
