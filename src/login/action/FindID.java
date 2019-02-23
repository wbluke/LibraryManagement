package login.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import login.bean.MemberDTO;
import login.dao.LoginDAO;

public class FindID extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JTextField nameT, telT1, telT2;
	private JButton idFindB;
	private JComboBox<String> telCB;
	private MemberDTO memberDTO;

	/**
	 * Create the frame.
	 */
	public FindID() {
		setTitle("아이디 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1100, 200, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameL = new JLabel("이      름 : ");
		nameL.setBounds(30, 40, 90, 30);
		contentPane.add(nameL);
		
		JLabel telL = new JLabel("전화번호 : ");
		telL.setBounds(30, 80, 90, 30);
		contentPane.add(telL);
		
		idFindB = new JButton("아이디 찾기");
		idFindB.setBounds(80, 200, 120, 40);
		contentPane.add(idFindB);
		
		nameT = new JTextField();
		nameT.setBounds(95, 40, 100, 30);
		contentPane.add(nameT);
		nameT.setColumns(10);
		
		telT1 = new JTextField();
		telT1.setColumns(10);
		telT1.setBounds(170, 80, 40, 30);
		contentPane.add(telT1);
		
		String[] number = new String[] { "010", "011", "017", "019" };
		telCB = new JComboBox<String>(number);
		telCB.setBounds(95, 80, 55, 27);
		contentPane.add(telCB);
		
		telT2 = new JTextField();
		telT2.setColumns(10);
		telT2.setBounds(230, 80, 40, 30);
		contentPane.add(telT2);
		
		JLabel label = new JLabel("-");
		label.setBounds(155, 90, 10, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(215, 90, 10, 15);
		contentPane.add(label_1);
		
		idFindB.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
      });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == idFindB) {
			String name = nameT.getText();
			String tel1 = (String)telCB.getSelectedItem();
			String tel2 = telT1.getText();
			String tel3 = telT2.getText();

			LoginDAO loginDAO = LoginDAO.getInstance();
			
			String id_Find = loginDAO.idFind(name, tel1, tel2, tel3);
			System.out.println(id_Find);
			
			if(id_Find != null){
				JOptionPane.showMessageDialog(null, "아이디는 " + id_Find + " 입니다.");
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} else {
				if(name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
				} else if(tel2.isEmpty() || tel3.isEmpty()) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
				} else {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		FindID memberIdFind = new FindID();
		memberIdFind.setVisible(true);
	}
}
