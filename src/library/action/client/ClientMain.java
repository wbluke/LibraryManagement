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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	public ClientMain(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		setBounds(400, 200, 878, 640);
		
		setSize(1100,800);
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈	
		this.setLocation((screenSize.width - frameSize.width)/2, 
				(screenSize.height - frameSize.height)/2); // 화면 중앙
		
		panel_1 = new JPanel();
		panel_1.setBounds(41, 117, 999, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
	            new String[] {"전체", "도서명", "저자", "출판사", "장르"}));
		comboBox.setBounds(0, 0, 105, 45);
		panel_1.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.BOLD, 15));
		textField.setBounds(103, 0, 814, 45);
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton.setBounds(914, 0, 85, 45);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchListWindow(memberDTO, comboBox.getSelectedItem().toString(), textField.getText());
			}
		});
		
		lblNewLabel_4 = new JLabel("");
	    String imagePath ="mainLibImage.jpg";
		ImageIcon originIcon = new ImageIcon(imagePath);
	    Image originImg = originIcon.getImage();

	 
	    //이미지로 변환
	    Image changedImg = originImg.getScaledInstance(1000,540,Image.SCALE_SMOOTH);
	    ImageIcon Icon = new ImageIcon(changedImg);

	    lblNewLabel_4.setIcon(Icon);
		
		lblNewLabel_4.setBounds(40, 88, 1000, 514);
		contentPane.add(lblNewLabel_4);
		
		libraryInfoP = new JPanel();
		libraryInfoP.setBackground(new Color(255, 255, 240));
		libraryInfoP.setBounds(272, 644, 442, 101);
		contentPane.add(libraryInfoP);
		libraryInfoP.setLayout(null);
		
		label_1 = new JLabel("이용시간 : 월~금 08 : 30 ~ 16 : 30 / 토, 일, 공휴일 쉬는날");
		label_1.setBounds(0, 25, 386, 26);
		libraryInfoP.add(label_1);
		
		lblTel = new JLabel("TEL : 02 - 1234 - 1234 ~ 9");
		lblTel.setBounds(0, 48, 386, 26);
		libraryInfoP.add(lblTel);
		
		
		
		JLabel label = new JLabel("\u203B \uC6B0\uCE21 \uC9C0\uB3C4\uB97C \uB354\uBE14\uD074\uB9AD\uD558\uC2DC\uBA74 \uB3C4\uC11C\uAD00 \uC0C1\uC138 \uC815\uBCF4\uB97C \uBCF4\uC2E4 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBounds(0, 72, 386, 26);
		libraryInfoP.add(label);
		
/*		//panel_3 = new JPanel(); //도서관위치_최종.png
		File f = new File("도서관위치_최종.png");

		System.out.println(f.exists()); // 파일이 있는지 확인용 true/false
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("c:/work/pack/pack1.jpg");*/
		
		btnNewButton_1 = new JButton("마이페이지");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage(memberDTO);
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton_1.setBounds(943, 33, 97, 45);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("로그아웃");
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
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton_2.setBounds(823, 33, 97, 45);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel = new JLabel(memberDTO.getMemberName() + "님 도서관 방문을 환영합니다!");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(41, 48, 246, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon("libMark.png"));
		lblNewLabel_2.setBounds(41, 625, 210, 120);
		contentPane.add(lblNewLabel_2);
	    
		lblNewLabel_5 = new JLabel("");
	    String imagePath2 ="libMap.png";
		ImageIcon originIcon2 = new ImageIcon(imagePath2);
	    Image originImg2 = originIcon2.getImage();

	 
	    //이미지로 변환
	    Image changedImg2 = originImg2.getScaledInstance(220,120,Image.SCALE_SMOOTH);
	    ImageIcon Icon2 = new ImageIcon(changedImg2);

	    lblNewLabel_5.setIcon(Icon2);
		
	    lblNewLabel_5.setBounds(820, 626, 220, 120);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_1 = new JLabel("서울특별시 종로구 돈화문로 26 단성사");
		lblNewLabel_1.setBounds(0, 0, 386, 26);
		libraryInfoP.add(lblNewLabel_1);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				new LibraryMain();
			}
		});
		lblNewLabel_5.addMouseListener(new MyMouseListener());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberName("아무개");
		new ClientMain(memberDTO);
	}
}
class MyMouseListener extends MouseAdapter {
	  public void mouseClicked(MouseEvent evt) {
	    if (evt.getClickCount() ==2) {
	    	new LibMap();	
	    	System.out.println("double-click");
	    } 
	  }
	
}
