package library.action;

import library.action.admin.AdminMain;
import library.action.client.ClientMain;
import login.action.StartWindow;

public class LibraryMain {
	
	public LibraryMain() {
		// �α��� â ����
		//new Client();
		new StartWindow();
		
		// �α��� â���� ������ �α��� ����(ȸ��, ������)�� �޾ƿ��� �޼ҵ�
		//new AdminMain();
		// ȸ�� �����̸� ClientMain ����
		
		// ������ �����̸� ManagerMain ����
	}
	

	public static void main(String[] args) {
		new LibraryMain();
	}

	
}
