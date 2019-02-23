package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import library.action.LibraryMain;

public class ImageTest extends JFrame {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "java";
	private String password = "itbank";
	
	private Image bufferImg;	// ������۸�
	private Graphics bufferG;	// ������۸�
	
	@Override
	public void update(Graphics g){ // paint���� update�� �ٲٰ� (�޸𸮿��� �׷����ϴϱ�)

		Dimension d = getSize(); // this.getSize();

		if(bufferG == null){	// ���� bufferG�� �̹����� ���ٸ�
			bufferImg = createImage(d.width, d.height); // ĵ������ ũ�⸸ŭ �̹��� �����
			bufferG = bufferImg.getGraphics();	// bufferImg�� �׸� ���� Graphics
		}

		bufferG.setColor(Color.WHITE);	// ĵ������ ���� �Ȱ��� (WHITE�� �⺻)
		bufferG.fillRect(0, 0, d.width, d.height); // ���� �� �����

		

		paint(g); // �� �׸� �� �Ʒ��� �ִ� paint() ȣ��
	}

	@Override
	public void paint(Graphics g){
		g.drawImage(bufferImg, 0, 0, this); // �޸𸮿��� �׸� �̹����� ȣ���ؼ� �׸���
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("JDBC Connection Success!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void inputImage(File file) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into imagetest values(?,?)"; 
		try { 
			int img_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Image ID")); 
			String image_source = file.toString().replace("D:\\LibraryImage\\", "");
		    pstmt=conn.prepareStatement(sql); 
		    pstmt.setString(1, image_source);  
		    pstmt.setInt(2, img_id); 
		    pstmt.executeUpdate(); 
		    JOptionPane.showMessageDialog(null, "Image Successfully Uploaded to Database"); 
	
		} catch (SQLException e) { 
			e.printStackTrace();
	    } finally {
	    	try {
	    		if (pstmt != null) pstmt.close();
	    		if (conn != null) conn.close();
	    	}catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	
	public void getSavedImages(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from imagetest"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("image") + "\t" + rs.getInt("image_code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	} 
	
	public File openDialog(){
		JFileChooser chooser = new JFileChooser();

		int result = chooser.showOpenDialog(this);
		File file = null;
		if(result == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}

		//JOptionPane.showMessageDialog(this, file);
		return file;
	}
	
	public static void main(String[] args) {
		ImageTest lm = new ImageTest();
		File file = lm.openDialog(); // Ž���⸦ ��� �������� �ش� �̹��� ������ ��θ� �޾ƿ´�.
		
		lm.inputImage(file); // ���� ��θ� ������ ���� db�� �ش� �̹��� ������ �ִ´�.
		
		lm.getSavedImages(); // �̹����� 
	}
}
