package library.action.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import login.action.SendEmail;
import login.bean.MemberDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SendEmailToBlack extends JFrame {

	private JPanel contentPane;
	private MemberDTO target;
	private JTextArea textArea;
	private JTextArea textArea_1;

	public SendEmailToBlack(MemberDTO target) {
		setTitle("\uC774\uBA54\uC77C \uC804\uC1A1");
		
		this.target = target;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\uC544\uC774\uB514 : " + target.getMemberId());
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 14));
		label_1.setBounds(12, 10, 302, 20);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\uC774   \uB984 : " + target.getMemberName());
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 14));
		label_2.setBounds(12, 40, 302, 20);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\uC774\uBA54\uC77C : " + target.getEmail());
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 14));
		label_3.setBounds(12, 70, 302, 20);
		contentPane.add(label_3);
		
		JLabel label = new JLabel("\uC81C \uBAA9");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		label.setBounds(12, 107, 302, 20);
		contentPane.add(label);
		
		JLabel label_4 = new JLabel("\uB0B4 \uC6A9");
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		label_4.setBounds(12, 179, 302, 20);
		contentPane.add(label_4);
		
		JButton btnNewButton = new JButton("\uC804 \uC1A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SendEmail(target.getEmail(), textArea.getText(), textArea_1.getText());
				JOptionPane.showMessageDialog(
						null, "¿Ã∏ﬁ¿œ¿Ã ¿¸º€µ«æ˙Ω¿¥œ¥Ÿ.", "æ»≥ª", 
						JOptionPane.WARNING_MESSAGE);
			}
		});
		btnNewButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		btnNewButton.setBounds(231, 423, 97, 37);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 137, 316, 32);
		contentPane.add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(12, 209, 316, 204);
		contentPane.add(textArea_1);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(400, 200, 356, 508);
		setVisible(true);
	}

	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		new SendEmailToBlack(memberDTO);
		
	}
}
