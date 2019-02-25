package library.action.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import library.action.LibraryMain;
import library.bean.BookDTO;
import library.dao.BookDAO;
import login.bean.MemberDTO;
import login.dao.MemberDAO;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table_1;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField_15;
	private JTable table_4;
	private DefaultTableModel dtm2;
	private DefaultTableModel dtm3;
	
	private int table_2Checked = -1;
	private int table_3Checked = -1;
	private JButton button_5;
	private JButton button_7;
	private DefaultTableModel dtm4;
	private int table_4Checked = -1;

	@SuppressWarnings("serial")
	public AdminMain(MemberDTO memberDTO) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 745, 553);
		contentPane.add(tabbedPane);
		
		JPanel books = new JPanel();
		tabbedPane.addTab("\uB3C4\uC11C \uAD00\uB9AC", null, books, null);
		tabbedPane.setBackgroundAt(0, UIManager.getColor("Button.light"));
		tabbedPane.setForegroundAt(0, Color.BLACK);
		books.setLayout(null);
		
		JLabel label = new JLabel("\uB3C4\uC11C \uAD00\uB9AC");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setBounds(550, 21, 142, 45);
		books.add(label);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnNewButton.setBounds(625, 76, 67, 34);
		books.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(397, 76, 230, 34);
		books.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uB3C4\uC11C\uBA85", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uC7A5\uB974"}));
		comboBox.setBounds(335, 76, 62, 34);
		books.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 160, 644, 242);
		books.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBC88\uD638", "\uCC45 \uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uC7A5\uB974", "대여 여부"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(236);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		table.setBounds(48, 160, 644, 196);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("\uC0AD\uC81C");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnNewButton_1.setBounds(625, 120, 67, 30);
		books.add(btnNewButton_1);
		
		JButton button = new JButton("\uCD94\uAC00");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminInsert().event();
			}
		});
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		button.setBounds(546, 120, 67, 30);
		books.add(button);
		
		JLabel lblNewLabel = new JLabel("\uB3C4\uC11C \uBC88\uD638");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 412, 67, 34);
		books.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(121, 418, 78, 25);
		books.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\uCD9C\uD310\uC0AC");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_3.setBounds(42, 456, 67, 34);
		books.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(121, 462, 78, 25);
		books.add(textField_2);
		
		JLabel label_4 = new JLabel("\uC7A5\uB974");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_4.setBounds(211, 412, 67, 34);
		books.add(label_4);
		
		JLabel label_5 = new JLabel("\uB3C4\uC11C\uBA85");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_5.setBounds(211, 456, 67, 34);
		books.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(290, 462, 124, 25);
		books.add(textField_4);
		
		JLabel label_6 = new JLabel("\uC800\uC790");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_6.setBounds(426, 412, 67, 34);
		books.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(505, 418, 78, 25);
		books.add(textField_5);
		
		JLabel label_7 = new JLabel("\uB300\uC5EC \uC5EC\uBD80");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_7.setBounds(426, 456, 67, 34);
		books.add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(505, 462, 78, 25);
		books.add(textField_6);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uC18C\uC124", "\uC2DC/\uC5D0\uC138\uC774", "\uACBD\uC81C/\uACBD\uC601", "\uC790\uAE30 \uACC4\uBC1C", "\uC778\uBB38", "\uC885\uAD50", "\uACFC\uD559"}));
		comboBox_1.setBounds(290, 418, 83, 25);
		books.add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("\uC218\uC815");
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnNewButton_2.setBounds(614, 419, 78, 67);
		books.add(btnNewButton_2);
		
		JPanel members = new JPanel();
		tabbedPane.addTab("\uD68C\uC6D0 \uAD00\uB9AC", null, members, null);
		members.setLayout(null);
		
		JLabel label_1 = new JLabel("\uD68C\uC6D0 \uAD00\uB9AC");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label_1.setBounds(550, 21, 142, 45);
		members.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(361, 76, 230, 34);
		members.add(textField_3);
		
		JButton button_1 = new JButton("\uC774\uB984 \uAC80\uC0C9");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		button_1.setBounds(590, 76, 102, 34);
		members.add(button_1);
		
		JButton button_3 = new JButton("\uC0AD\uC81C");
		button_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		button_3.setBounds(625, 120, 67, 30);
		members.add(button_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 160, 644, 242);
		members.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"이름", "회원 ID", "회원 PW", "성별", "주소", "연락처", "이메일"
			}
		));
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setColumnHeaderView(table_1);
		scrollPane_1.setViewportView(table_1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(150);
		table_1.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		
		JButton button_4 = new JButton("\uC218\uC815");
		button_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		button_4.setBounds(614, 419, 78, 67);
		members.add(button_4);
		
		JLabel lblId = new JLabel("\uD68C\uC6D0 ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblId.setBounds(42, 412, 67, 34);
		members.add(lblId);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(121, 418, 78, 25);
		members.add(textField_7);
		
		JLabel label_9 = new JLabel("\uC774\uB984");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_9.setBounds(42, 441, 67, 34);
		members.add(label_9);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(121, 447, 78, 25);
		members.add(textField_8);
		
		JLabel lblPw = new JLabel("\uD68C\uC6D0 PW");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPw.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblPw.setBounds(202, 412, 67, 34);
		members.add(lblPw);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(281, 418, 78, 25);
		members.add(textField_9);
		
		JLabel label_10 = new JLabel("\uC131\uBCC4");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_10.setBounds(202, 441, 67, 34);
		members.add(label_10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uB0A8");
		rdbtnNewRadioButton.setBounds(281, 448, 45, 23);
		members.add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\uC5EC");
		radioButton.setBounds(324, 448, 47, 23);
		members.add(radioButton);
		
		JLabel lblWnth = new JLabel("\uC8FC\uC18C");
		lblWnth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWnth.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblWnth.setBounds(361, 412, 67, 34);
		members.add(lblWnth);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(440, 418, 151, 25);
		members.add(textField_10);
		
		JLabel lblDlapdlf = new JLabel("\uC774\uBA54\uC77C");
		lblDlapdlf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDlapdlf.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblDlapdlf.setBounds(361, 441, 67, 34);
		members.add(lblDlapdlf);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(440, 447, 151, 25);
		members.add(textField_11);
		
		JLabel label_8 = new JLabel("\uC5F0\uB77D\uCC98");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_8.setBounds(42, 471, 67, 34);
		members.add(label_8);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(202, 480, 52, 25);
		members.add(textField_12);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "017", "019"}));
		comboBox_2.setBounds(121, 480, 58, 25);
		members.add(comboBox_2);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(279, 480, 52, 25);
		members.add(textField_13);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(188, 485, 14, 15);
		members.add(lblNewLabel_1);
		
		JLabel label_11 = new JLabel("-");
		label_11.setBounds(264, 485, 14, 15);
		members.add(label_11);
		
		JPanel statement = new JPanel();
		tabbedPane.addTab("\uB300\uC5EC \uAD00\uB9AC", null, statement, null);
		statement.setLayout(null);
		
		JLabel label_2 = new JLabel("\uB300\uC5EC \uAD00\uB9AC");
		label_2.setBounds(550, 21, 142, 45);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		statement.add(label_2);
		
		JLabel label_13 = new JLabel("\uD68C\uC6D0 \uBAA9\uB85D");
		label_13.setBounds(44, 44, 142, 30);
		label_13.setHorizontalAlignment(SwingConstants.LEFT);
		label_13.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		statement.add(label_13);
		
		JLabel label_14 = new JLabel("\uB3C4\uC11C \uC815\uBCF4");
		label_14.setBounds(44, 230, 142, 30);
		label_14.setHorizontalAlignment(SwingConstants.LEFT);
		label_14.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		statement.add(label_14);
		
		textField_14 = new JTextField();
		textField_14.setBounds(44, 84, 99, 23);
		statement.add(textField_14);
		textField_14.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("\uC774\uB984 \uAC80\uC0C9");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_14.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(
							null, "검색어를 입력해주세요.", "안내", 
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				MemberDAO memberDAO = MemberDAO.getInstance();
				ArrayList<MemberDTO> list = memberDAO.searchByName(textField_14.getText().trim());
				
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm2.getRowCount();) {
					dtm2.removeRow(0);
				}         

				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "검색 결과가 없습니다.", "안내", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					for(MemberDTO member : list) {
						
						Object data[] = { member.getMemberName(), member.getMemberId(), member.getAddress(), 
								member.getTel1() + "-" + member.getTel2() + "-" + member.getTel3(), member.getEmail()};
						dtm2.addRow(data);
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnNewButton_3.setBounds(143, 84, 99, 23);
		statement.add(btnNewButton_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(44, 117, 644, 103);
		statement.add(scrollPane_2);
		scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table_2 = new JTable();
		dtm2 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"이름", "회원 ID", "주소", "연락처", "이메일"
				}
			) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_2.setModel(dtm2);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setColumnHeaderView(table_2);
		scrollPane_2.setViewportView(table_2);
		
		table_2.getColumnModel().getColumn(0).setPreferredWidth(70);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(91);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(170);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(145);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(150);
		table_2.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		
		table_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table_2Checked = table_2.getSelectedRow();
				setEnableBorrowB();
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(44, 303, 644, 185);
		statement.add(scrollPane_3);
		scrollPane_3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table_3 = new JTable();
		dtm3 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"번호", "책 제목", "회원 ID", "회원 이름", "대여일", "반납일"
				}
			) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_3.setModel(dtm3);
		table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table_3.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(196);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_3.getColumnModel().getColumn(5).setPreferredWidth(90);
		table_3.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		table_3.setBounds(48, 160, 644, 196);
		scrollPane_3.setViewportView(table_3);
		
		table_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table_3Checked = table_3.getSelectedRow();
				setEnableBorrowB();
				setEnableReturnB();
			}
		});
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(44, 270, 130, 23);
		statement.add(textField_15);
		
		JButton button_2 = new JButton("\uB3C4\uC11C\uBA85 \uAC80\uC0C9");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_15.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(
							null, "검색어를 입력해주세요.", "안내", 
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				BookDAO bookDAO = BookDAO.getInstance();
				MemberDAO memberDAO = MemberDAO.getInstance();
				String nameWhoBorrowed = null;
				ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
				
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm3.getRowCount();) {
					dtm3.removeRow(0);
				}         

				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "검색 결과가 없습니다.", "안내", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					for(BookDTO book : list) {
						if (book.getMemberId() != null) {
							MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
							nameWhoBorrowed = whoBorrowed.getMemberName();
						}
						
						Object data[] = { 
							book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
						};
						dtm3.addRow(data);
						nameWhoBorrowed = null;
					}
				}
			}
		});
		button_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_2.setBounds(174, 270, 107, 23);
		statement.add(button_2);
		
		button_5 = new JButton("\uB300\uC5EC\uD558\uAE30");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "대여하시겠습니까?", "대여 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// table_2 에서 memberID 가져오기
					String targetID = (String) table_2.getValueAt(table_2Checked, 1);
					
					// 블랙리스트인지 체크
					MemberDAO memberDAO = MemberDAO.getInstance();
					MemberDTO member = memberDAO.searchByID(targetID);
					if (member.getOverdue() >= 3) {
						JOptionPane.showMessageDialog(
								null, "해당 회원은 블랙리스트로 대여 불가 대상입니다.", "안내", 
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// table_3 에서 seq 가져오기
					int targetBookSeq = (int) table_3.getValueAt(table_3Checked, 0);

					// 현재 sysDate를 String으로 바꿔서 반납 날짜까지 계산해서 넣기
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String now = formatter.format(new Date());
					Calendar cal = Calendar.getInstance();
					String until = null;
					try {
						cal.setTime(formatter.parse(now));
						cal.add(Calendar.DATE, 14);
						until = formatter.format(cal.getTime());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// DAO 작업
					//System.out.println(now + until);
					BookDAO bookDAO = BookDAO.getInstance();
					bookDAO.updateBorrowInfo(targetBookSeq, targetID, now, until);
					
					// ============================================================ 테이블 새로고침 : 함수로 만들기 복잡해서 중복코드
					String nameWhoBorrowed = null;
					ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
					
					// DefaultTableModel에 있는 기존 데이터 지우기
					for (int i = 0; i < dtm3.getRowCount();) {
						dtm3.removeRow(0);
					}         

					if(list.isEmpty()) {
						JOptionPane.showMessageDialog(
								null, "검색 결과가 없습니다.", "안내", 
								JOptionPane.WARNING_MESSAGE);
					}else {
						for(BookDTO book : list) {
							if (book.getMemberId() != null) {
								MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
								nameWhoBorrowed = whoBorrowed.getMemberName();
							}
							
							Object data[] = { 
								book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
							};
							dtm3.addRow(data);
							nameWhoBorrowed = null;
						}
					}
				}
			}
		});
		
		button_5.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_5.setBounds(367, 270, 99, 23);
		statement.add(button_5);
		
		JButton button_6 = new JButton("\uC9C0\uC6B0\uAE30");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm3.getRowCount();) {
					dtm3.removeRow(0);
				} 
			}
		});
		button_6.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_6.setBounds(589, 270, 99, 23);
		statement.add(button_6);
		
		button_7 = new JButton("\uBC18\uB0A9\uD558\uAE30");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "반납하시겠습니까?", "반납 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					
					// table_3 에서 seq 가져오기
					int targetBookSeq = (int) table_3.getValueAt(table_3Checked, 0);

					// DAO 작업
					//System.out.println(now + until);
					BookDAO bookDAO = BookDAO.getInstance();
					MemberDAO memberDAO = MemberDAO.getInstance();
					bookDAO.updateReturnInfo(targetBookSeq);
					
					// ============================================================ 테이블 새로고침 : 함수로 만들기 복잡해서 중복코드
					String nameWhoBorrowed = null;
					ArrayList<BookDTO> list = bookDAO.searchByName(textField_15.getText().trim());
					
					// DefaultTableModel에 있는 기존 데이터 지우기
					for (int i = 0; i < dtm3.getRowCount();) {
						dtm3.removeRow(0);
					}         

					if(list.isEmpty()) {
						JOptionPane.showMessageDialog(
								null, "검색 결과가 없습니다.", "안내", 
								JOptionPane.WARNING_MESSAGE);
					}else {
						for(BookDTO book : list) {
							if (book.getMemberId() != null) {
								MemberDTO whoBorrowed = memberDAO.searchByID(book.getMemberId());
								nameWhoBorrowed = whoBorrowed.getMemberName();
							}
							
							Object data[] = { 
								book.getSeq(), book.getBookName(), book.getMemberId(), nameWhoBorrowed, book.getSt(), book.getEn()
							};
							dtm3.addRow(data);
							nameWhoBorrowed = null;
						}
					}
				}
			}
		});
		button_7.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_7.setBounds(478, 270, 99, 23);
		statement.add(button_7);
		
		JPanel overdue = new JPanel();
		tabbedPane.addTab("\uC5F0\uCCB4 \uAD00\uB9AC", null, overdue, null);
		overdue.setLayout(null);
		
		JLabel label_12 = new JLabel("\uC5F0\uCCB4 \uAD00\uB9AC");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label_12.setBounds(550, 21, 142, 45);
		overdue.add(label_12);
		
		JButton button_8 = new JButton("\uBC18\uB0A9 \uC608\uC815");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm4.getRowCount();) {
					dtm4.removeRow(0);
				}  
				
				// 오늘 날짜에 해당되는 데이터 추출
				BookDAO bookDAO = BookDAO.getInstance();
				MemberDAO memberDAO = MemberDAO.getInstance();
				ArrayList<BookDTO> list = bookDAO.searchBorrowListByToday();
				
				String memberName = null;
				int overdue = -1;
				String blackList = null;
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "오늘 반납 예정인 회원이 없습니다.", "안내", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					
					for(BookDTO book : list) {
						MemberDTO member = memberDAO.searchByID(book.getMemberId());
						memberName = member.getMemberName();
						overdue = member.getOverdue();
						if (overdue >= 3) {
							blackList = "YES";
						}else {
							blackList = null;
						}
						
						// 테이블에 뿌리기 
						Object data[] = { 
							book.getMemberId(), memberName, book.getBookName(), book.getSt(), book.getEn(), overdue, blackList
						};
						dtm4.addRow(data);
					}
				}
			}
		});
		button_8.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_8.setBounds(26, 75, 99, 23);
		overdue.add(button_8);
		
		JButton button_9 = new JButton("\uC5F0\uCCB4\uC790\uBAA9\uB85D");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm4.getRowCount();) {
					dtm4.removeRow(0);
				}  
				
				// 오늘 날짜에 해당되는 데이터 추출
				BookDAO bookDAO = BookDAO.getInstance();
				MemberDAO memberDAO = MemberDAO.getInstance();
				ArrayList<BookDTO> list = bookDAO.searchBorrowListUntilYesterday();
				
				String memberName = null;
				int overdue = -1;
				String blackList = null;
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "오늘 반납 예정인 회원이 없습니다.", "안내", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					
					for(BookDTO book : list) {
						MemberDTO member = memberDAO.searchByID(book.getMemberId());
						memberName = member.getMemberName();
						overdue = member.getOverdue();
						if (overdue >= 3) {
							blackList = "YES";
						}else {
							blackList = null;
						}
						
						// 테이블에 뿌리기 
						Object data[] = { 
							book.getMemberId(), memberName, book.getBookName(), book.getSt(), book.getEn(), overdue, blackList
						};
						dtm4.addRow(data);
					}
				}
			}
		});
		button_9.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_9.setBounds(137, 75, 99, 23);
		overdue.add(button_9);
		
		JButton button_10 = new JButton("\uBE14\uB799\uB9AC\uC2A4\uD2B8");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DefaultTableModel에 있는 기존 데이터 지우기
				for (int i = 0; i < dtm4.getRowCount();) {
					dtm4.removeRow(0);
				} 
				
				// 오늘 날짜에 해당되는 데이터 추출
				BookDAO bookDAO = BookDAO.getInstance();
				MemberDAO memberDAO = MemberDAO.getInstance();
				ArrayList<MemberDTO> list = memberDAO.searchByBlackList();
				
				int overdue = -1;
				String blackList = null;
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(
							null, "현재 블랙리스트가 없습니다.", "안내", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					
					for(MemberDTO member : list) {
						overdue = member.getOverdue();
						if (overdue >= 3) {
							blackList = "YES";
						}else {
							blackList = null;
						}
						
						// 테이블에 뿌리기 
						Object data[] = { 
								member.getMemberId(), member.getMemberName(), "", "", "", overdue, blackList
						};
						dtm4.addRow(data);
					}
				}
			}
		});
		button_10.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_10.setBounds(248, 75, 99, 23);
		overdue.add(button_10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(26, 108, 699, 279);
		overdue.add(scrollPane_4);
		scrollPane_4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table_4 = new JTable();
		table_4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dtm4 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"아이디", "회원 이름", "도서명", "대여일", "반납일", "연체 횟수", "블랙리스트"
				}
			) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_4.setModel(dtm4);
		table_4.getColumnModel().getColumn(0).setPreferredWidth(90);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_4.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_4.getColumnModel().getColumn(5).setPreferredWidth(90);
		table_4.getColumnModel().getColumn(6).setPreferredWidth(90);
		table_4.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		table_4.setBounds(48, 160, 644, 196);
		scrollPane_4.setViewportView(table_4);
		
		table_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table_4Checked = table_4.getSelectedRow();
			}
		});
		
		JButton btnNewButton_4 = new JButton("\uC774\uBA54\uC77C \uC804\uC1A1");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String targetID = (String) table_4.getValueAt(table_4Checked, 0);
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberDTO target = memberDAO.searchByID(targetID);
				
				// 새로운 창 생성
				new SendEmailToBlack(target);
			}
		});
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnNewButton_4.setBounds(592, 397, 133, 45);
		overdue.add(btnNewButton_4);
		
		button_5.setEnabled(false); // 대여하기			
		button_7.setEnabled(false); // 반납하기
		
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 771, 606);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void	windowClosing(WindowEvent e){
				new LibraryMain();
			}
		});
	}
	
	private void setEnableBorrowB() {
		if (table_2Checked != -1 && table_3Checked != -1 && (String)table_3.getValueAt(table_3Checked, 4) == null) {
			button_5.setEnabled(true);
		}else {
			button_5.setEnabled(false);
		}
	}
	
	private void setEnableReturnB() {
		if (table_3Checked != -1 && (String)table_3.getValueAt(table_3Checked, 4) != null) {
			button_7.setEnabled(true);
		}else {
			button_7.setEnabled(false);			
		}
	} 

	public static void main(String[] args) {
		new AdminMain(null); 
	}
}
