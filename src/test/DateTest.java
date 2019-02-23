package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	// 두날짜의 차이 구하기
	public void doDiffOfDate(){
		String start = "2015-04-01";
	    String end = "2015-05-05";
	     
	    try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date beginDate = formatter.parse(start);
	        Date endDate = formatter.parse(end);
	         
	        // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
	        long diff = endDate.getTime() - beginDate.getTime();
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	        System.out.println("날짜차이=" + diffDays);
	        
	        // 현재 날짜를 String으로 가져오기
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



