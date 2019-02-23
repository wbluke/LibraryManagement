package library.action;

import library.action.admin.AdminMain;
import library.action.client.ClientMain;
import login.action.StartWindow;

public class LibraryMain {
	
	public LibraryMain() {
		// 로그인 창 띄우기
		//new Client();
		new StartWindow();
		
		// 로그인 창에서 선택한 로그인 종류(회원, 관리자)를 받아오는 메소드
		//new AdminMain();
		// 회원 선택이면 ClientMain 생성
		
		// 관리자 선택이면 ManagerMain 생성
	}
	

	public static void main(String[] args) {
		new LibraryMain();
	}

	
}
