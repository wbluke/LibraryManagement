package library.action.client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class LibMap extends JFrame {
	public LibMap() {
		super("¿À½Ã´Â ±æ");
		setResizable(false);
		getContentPane().setLayout(null);

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	    String imagePath ="libMap.png";
		ImageIcon originIcon = new ImageIcon(imagePath);
	    Image originImg = originIcon.getImage();

	 
	    //ÀÌ¹ÌÁö·Î º¯È¯
	    Image changedImg = originImg.getScaledInstance(250,250,Image.SCALE_SMOOTH);
	    ImageIcon Icon = new ImageIcon(changedImg);

	    lblNewLabel.setIcon(Icon);
		
	    lblNewLabel.setBounds(124, 32, 250, 250);
	    getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ÁÖ  ¼Ò : ¼­¿ïÆ¯º°½Ã Á¾·Î±¸ µ·È­¹®·Î 26 ´Ü¼º»ç");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_1.setBounds(47, 306, 390, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("¿À½Ã´Â ±æ : ÁöÇÏÃ¶(1,3,5È£¼±) 1ºÐ °Å¸®");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_2.setBounds(47, 378, 363, 26);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\uC8FC\uBCC0 \uBC84\uC2A4\uC815\uB958\uC7A5 - ");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_4.setBounds(60, 477, 416, 26);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC885\uB85C4\uAC00.\uC885\uBB18(01-017) 111,101,103,140,143,150,160,201,260,262");
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_5.setBounds(86, 528, 377, 26);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uC8FC\uBCC0 \uC9C0\uD558\uCCA0\uC5ED - ");
		lblNewLabel_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_6.setBounds(60, 414, 321, 26);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel("T E L : 02 - 1234 - 1234 ~ 9");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_3.setBounds(47, 342, 376, 26);
		getContentPane().add(lblNewLabel_3);
		
//		getContentPane().setBackground(new Color(255, 255, 240));
		getContentPane().setBackground(Color.WHITE);
		
		JLabel lblNewLabel_7 = new JLabel("\uC885\uB85C3\uAC00.1,3,5\uD638\uC120(01-550) \uC885\uB85C12");
		lblNewLabel_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_7.setBounds(86, 504, 197, 26);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("1\uD638\uC120(\uC885\uB85C3\uAC00), 3\uD638\uC120(\uC885\uB85C3\uAC00), 5\uD638\uC120(\uC885\uB85C3\uAC00)");
		lblNewLabel_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_8.setBounds(86, 441, 350, 26);
		getContentPane().add(lblNewLabel_8);
		
		setBounds(500, 200, 515, 619);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new LibMap();
	}
}
