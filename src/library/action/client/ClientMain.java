package library.action.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library.action.LibraryMain;
import library.action.mypage.MyPage;
import login.bean.MemberDTO;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class ClientMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel libraryInfoP;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	private JLabel lblTel;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private MemberDTO memberDTO;
	private Image img;
	private JLabel mapLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberName("아무개");
		new ClientMain(memberDTO);
	}

	/**
	 * Create the frame.
	 */
	public ClientMain(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(96, 74, 662, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"전체", "도서명", "저자", "출판사", "장르"}));
		comboBox.setBounds(0, 0, 64, 39);
		panel_1.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(63, 0, 516, 39);
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("\uAC80 \uC0C9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchListWindow(memberDTO, comboBox.getSelectedItem().toString(), textField.getText());
			}
		});
		btnNewButton.setBounds(577, 0, 85, 39);
		panel_1.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(41, 59, 779, 393);
		contentPane.add(panel);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(41, 463, 157, 115);
		contentPane.add(panel_2);
		
		libraryInfoP = new JPanel();
		libraryInfoP.setBounds(210, 474, 386, 104);
		contentPane.add(libraryInfoP);
		libraryInfoP.setLayout(null);
		
		lblNewLabel_1 = new JLabel("\uC11C\uC6B8\uD2B9\uBCC4\uC2DC \uC885\uB85C\uAD6C \uB3C8\uD654\uBB38\uB85C 26 \uB2E8\uC131\uC0AC");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(0, 0, 386, 26);
		libraryInfoP.add(lblNewLabel_1);
		
		label_1 = new JLabel("\uC774\uC6A9\uC2DC\uAC04 : \uC6D4~\uAE08 08 : 30 ~ 16 : 30 / \uD1A0, \uC77C, \uACF5\uD734\uC77C \uC26C\uB294\uB0A0");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setBounds(0, 24, 386, 26);
		libraryInfoP.add(label_1);
		
		lblTel = new JLabel("TEL : 02 - 1234 - 1234 ~ 9");
		lblTel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblTel.setBounds(0, 48, 386, 26);
		libraryInfoP.add(lblTel);
		
		JLabel label = new JLabel("\uC6B0\uCE21 \uC9C0\uB3C4\uB97C \uB354\uBE14\uD074\uB9AD\uD558\uC2DC\uBA74 \uB3C4\uC11C\uAD00 \uC0C1\uC138 \uC815\uBCF4\uB97C \uBCF4\uC2E4 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBounds(0, 72, 386, 26);
		libraryInfoP.add(label);
		
		//panel_3 = new JPanel(); //도서관위치_최종.png
		File f = new File("도서관위치_최종.png");

		System.out.println(f.exists()); // 파일이 있는지 확인용 true/false
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("c:/work/pack/pack1.jpg");
		
		btnNewButton_1 = new JButton("\uB9C8\uC774\uD398\uC774\uC9C0");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage(memberDTO);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 12));
		btnNewButton_1.setBounds(723, 26, 97, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 로그아웃
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					setVisible(false);
					new LibraryMain();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Gulim", Font.PLAIN, 12));
		btnNewButton_2.setBounds(614, 26, 97, 23);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel = new JLabel(memberDTO.getMemberName() + "\uB2D8 \uB3C4\uC11C\uAD00 \uBC29\uBB38\uC744 \uD658\uC601\uD569\uB2C8\uB2E4!");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(41, 34, 246, 15);
		contentPane.add(lblNewLabel);
		
		mapLabel = new JLabel("New label");
		mapLabel.setBounds(608, 462, 211, 115);
		contentPane.add(mapLabel);
	    
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 878, 640);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				new LibraryMain();
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
