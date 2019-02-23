package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	// �γ�¥�� ���� ���ϱ�
	public void doDiffOfDate(){
		String start = "2015-04-01";
	    String end = "2015-05-05";
	     
	    try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date beginDate = formatter.parse(start);
	        Date endDate = formatter.parse(end);
	         
	        // �ð����̸� �ð�,��,�ʸ� ���� ������ ������ �Ϸ� ������ ����
	        long diff = endDate.getTime() - beginDate.getTime();
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	        System.out.println("��¥����=" + diffDays);
	        
	        // ���� ��¥�� String���� ��������
	        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        System.out.println(now);
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		new DateTest().doDiffOfDate();
	}
}



