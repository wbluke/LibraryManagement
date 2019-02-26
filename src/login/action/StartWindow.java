package login.action;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;

import library.action.admin.AdminMain;
import library.action.client.ClientMain;
import login.bean.MemberDTO;
import login.dao.LoginDAO;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class StartWindow extends JFrame implements ActionListener {
	private JLabel idL, pwL;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton logB, idB, joinB;
	private JRadioButton memberB;
	private JRadioButton adminB;
	private MemberDTO memberDTO;
	private JButton pwB;

	public StartWindow() {
	   
	   
		setTitle("Library Management");
		setBounds(700, 250, 395, 418);
		getContentPane().setLayout(null);
		setForeground(new Color(176, 224, 230));
		setResizable(false);
	  
	  getContentPane().setBackground(new Color(255, 228, 225));      
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  Panel panel = new Panel();
	  panel.setBackground(new Color(176, 224, 230));
	  panel.setBounds(92, 30, 203, 110);
	  
	  idL = new JLabel("아 이 디");
	  pwL = new JLabel("비밀번호");      
	
	  idL.setBounds(43, 198, 57, 15);
	  pwL.setBounds(43, 244, 67, 15);
	  
	  idL.setFont(new Font("굴림", Font.BOLD, 15));
	  pwL.setFont(new Font("굴림", Font.BOLD, 15));
	
	  idT = new JTextField();
	  idT.setHorizontalAlignment(SwingConstants.LEFT);
	  pwT = new JPasswordField();
	
	  idT.setFont(new Font("굴림", Font.PLAIN, 15));
	  pwT.setFont(new Font("굴림", Font.PLAIN, 15));
	
	  idT.setBounds(112, 187, 140, 36);
	  pwT.setBounds(112, 233, 140, 36);
	  pwT.setHorizontalAlignment(SwingConstants.LEFT);
	
	  logB = new JButton("로그인");
	  logB.addActionListener(this);
	  
	  joinB = new JButton("회원가입");
	  joinB.addActionListener(this);
	  
	  idB = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
	  idB.addActionListener(this);
	  
	  logB.setBounds(264, 187, 79, 82);
	  joinB.setBounds(43, 289, 300, 27);
	  idB.setBounds(43, 335, 133, 27);
	  
	  logB.setFont(new Font("굴림", Font.BOLD, 15));
	  joinB.setFont(new Font("굴림", Font.BOLD, 15));
	  idB.setFont(new Font("굴림", Font.BOLD, 15));
	  
	  memberB = new JRadioButton("회원");
	  memberB.setSelected(true);
	  adminB = new JRadioButton("관리자");
	  
	  ButtonGroup group = new ButtonGroup();
	  group.add(memberB);
	  group.add(adminB);
	  
	  memberB.setBackground(new Color(255, 228, 225));
	  adminB.setBackground(new Color(255, 228, 225));
	
	  memberB.setBounds(43, 152, 79, 29);
	  adminB.setBounds(124, 152, 171, 29);
	  
	  getContentPane().add(panel);
	
	  getContentPane().add(idL);
	  getContentPane().add(pwL);      
	
	  getContentPane().add(idT);
	  getContentPane().add(pwT);
	
	  getContentPane().add(logB);
	  getContentPane().add(idB);      
	  getContentPane().add(joinB);
	  
	  getContentPane().add(memberB);
	  getContentPane().add(adminB);
	  
	  pwB = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
	  pwB.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		new FindPW();
	  	}
	  });
	  pwB.setFont(new Font("굴림", Font.BOLD, 15));
	  pwB.setBounds(210, 335, 133, 27);
	  getContentPane().add(pwB);
	  
	  setVisible(true);
  
	  
   }
   
	@Override
	public void actionPerformed(ActionEvent e) {
		//int service; //회원, 관리자
		
		if (e.getSource() == logB) {
			if (memberB.isSelected()) { //회원 선택
				//service = 0;
				String id = idT.getText();
				String pw = new String(pwT.getPassword());
				
				
				LoginDAO loginDAO = LoginDAO.getInstance();
				
				memberDTO = loginDAO.loginCheck(id, pw);
				
				if (memberDTO != null) {
					setVisible(false);
					new ClientMain(memberDTO);
				}
				else JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
				
			} else if(adminB.isSelected()) {
				//service = 1;
				String id = idT.getText();
				String pw = new String(pwT.getPassword());
				
				LoginDAO loginDAO = LoginDAO.getInstance();
				
				memberDTO = loginDAO.loginCheckAdmin(id, pw);
				
				if (memberDTO != null) {
					setVisible(false);
					new AdminMain(memberDTO);					
				}
				else JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
			}
				
		} else if (e.getSource() == joinB) { 
			new JoinMember().event();
				
		} else if (e.getSource() == idB) {
			new FindID();
		
		} 
		
	}
	
   
   
   public static void main(String[] args) {
      new StartWindow();
   }
}