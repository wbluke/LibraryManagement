package library.action.mypage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import login.bean.MemberDTO;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	public static void main(String[] args) {
		MemberDTO memberDTO = new MemberDTO();
		BookDTO bookDTO = new BookDTO();
		
		memberDTO.getBookCart().add(bookDTO);
		new MyPage(memberDTO);
	
	}

	public MyPage(MemberDTO memberDTO) {
		super("MyPage");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton infoChangeB = new JButton("\uD68C\uC6D0\uC815\uBCF4 \uBCC0\uACBD");
		infoChangeB.setFont(new Font("���� ���", Font.PLAIN, 12));
		infoChangeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeInfo(memberDTO);
			}
		});
		infoChangeB.setBounds(32, 59, 123, 23);
		contentPane.add(infoChangeB);
		
		JButton memberDeleteB = new JButton("\uD68C\uC6D0 \uD0C8\uD1F4");
		memberDeleteB.setFont(new Font("���� ���", Font.PLAIN, 12));
		memberDeleteB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] option = {"ȸ�� Ż��", "�� ��"};
				JOptionPane.showOptionDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "ȸ�� Ż��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, "�� ��");
			}
		});
		memberDeleteB.setBounds(167, 59, 97, 23);
		contentPane.add(memberDeleteB);
		
		JLabel RentL = new JLabel("\uB300\uC5EC \uD604\uD669");
		RentL.setFont(new Font("���� ���", Font.BOLD, 15));
		RentL.setBounds(32, 92, 76, 23);
		contentPane.add(RentL);
		
		JLabel basketL = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		basketL.setFont(new Font("���� ���", Font.BOLD, 15));
		basketL.setBounds(425, 92, 76, 23);
		contentPane.add(basketL);
		
		JButton allRentB = new JButton("\uC804\uBD80 \uB300\uC5EC");
		allRentB.setFont(new Font("���� ���", Font.PLAIN, 12));
		allRentB.setBounds(565, 60, 110, 23);
		contentPane.add(allRentB);
		
		JButton allDeleteB = new JButton("\uC804\uBD80 \uBE44\uC6B0\uAE30");
		allDeleteB.setFont(new Font("���� ���", Font.PLAIN, 12));
		allDeleteB.setBounds(565, 92, 110, 23);
		contentPane.add(allDeleteB);
		
		JButton selectRentB = new JButton("\uC120\uD0DD \uB300\uC5EC");
		selectRentB.setFont(new Font("���� ���", Font.PLAIN, 12));
		selectRentB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		selectRentB.setBounds(687, 60, 110, 23);
		contentPane.add(selectRentB);
		
		JButton selectDeleteB = new JButton("\uC120\uD0DD \uBE44\uC6B0\uAE30");
		selectDeleteB.setFont(new Font("���� ���", Font.PLAIN, 12));
		selectDeleteB.setBounds(687, 92, 110, 23);
		contentPane.add(selectDeleteB);
		
		JLabel label = new JLabel("\uD68C\uC6D0 \uC815\uBCF4");
		label.setFont(new Font("���� ���", Font.BOLD, 15));
		label.setBounds(32, 26, 76, 23);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 125, 372, 254);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"������", "�뿩��", "�ݳ���", "���� ��¥"
			}
		));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(425, 125, 372, 254);
		contentPane.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"������", "����", "���ǻ�", "�帣"
			}
		));
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setColumnHeaderView(table_1);
		scrollPane_1.setViewportView(table_1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(114);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_1.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		
		setBounds(400, 200, 842, 467);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
      });
	}
}
