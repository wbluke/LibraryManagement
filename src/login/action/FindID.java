package login.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
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
import java.awt.Font;

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
		setBounds(1100, 200, 350, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameL = new JLabel("이      름 : ");
		nameL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		nameL.setBounds(12, 38, 90, 30);
		contentPane.add(nameL);
		
		JLabel telL = new JLabel("전화번호 : ");
		telL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		telL.setBounds(12, 80, 90, 30);
		contentPane.add(telL);
		
		idFindB = new JButton("아이디 찾기");
		idFindB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		idFindB.setBounds(202, 129, 120, 40);
		contentPane.add(idFindB);
		
		nameT = new JTextField();
		nameT.setBounds(77, 40, 100, 30);
		contentPane.add(nameT);
		nameT.setColumns(10);
		
		telT1 = new JTextField();
		telT1.setColumns(10);
		telT1.setBounds(166, 82, 48, 30);
		contentPane.add(telT1);
		
		telCB = new JComboBox();
		telCB.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		telCB.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "017", "019"}));
		telCB.setBounds(77, 82, 55, 30);
		contentPane.add(telCB);
		
		telT2 = new JTextField();
		telT2.setColumns(10);
		telT2.setBounds(248, 82, 48, 30);
		contentPane.add(telT2);
		
		JLabel label = new JLabel("-");
		label.setBounds(144, 89, 10, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(226, 89, 10, 15);
		contentPane.add(label_1);
		
		idFindB.addActionListener(this);
		setVisible(true);
		
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
				JOptionPane.showMessageDialog(null, "찾으시는 아이디는 " + id_Find + " 입니다.");
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} else {
				if(name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
				} else if(tel2.isEmpty() || tel3.isEmpty()) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
				} else {
					JOptionPane.showMessageDialog(null, "찾으시는 아이디가 존재하지 않습니다.\n다시 검색해주세요.");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		FindID memberIdFind = new FindID();
	}
}
