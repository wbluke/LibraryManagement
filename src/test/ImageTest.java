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
	
	private Image bufferImg;	// 더블버퍼링
	private Graphics bufferG;	// 더블버퍼링
	
	@Override
	public void update(Graphics g){ // paint에서 update로 바꾸고 (메모리에서 그려야하니까)

		Dimension d = getSize(); // this.getSize();

		if(bufferG == null){	// 지금 bufferG에 이미지가 없다면
			bufferImg = createImage(d.width, d.height); // 캔버스의 크기만큼 이미지 만들고
			bufferG = bufferImg.getGraphics();	// bufferImg에 그릴 전용 Graphics
		}

		bufferG.setColor(Color.WHITE);	// 캔버스와 배경색 똑같이 (WHITE가 기본)
		bufferG.fillRect(0, 0, d.width, d.height); // 전부 다 지우기

		

		paint(g); // 다 그린 후 아래에 있는 paint() 호출
	}

	@Override
	public void paint(Graphics g){
		g.drawImage(bufferImg, 0, 0, this); // 메모리에서 그린 이미지를 호출해서 그리기
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
		File file = lm.openDialog(); // 탐색기를 열어서 넣으려는 해당 이미지 파일의 경로를 받아온다.
		
		lm.inputImage(file); // 파일 경로를 가지고 들어가서 db에 해당 이미지 파일을 넣는다.
		
		lm.getSavedImages(); // 이미지를 
	}
}
