package com.tquiz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimeQuizApplicationTests {

	@Test
	void contextLoads() {
		
String st="";
		
		
		Date currentDate = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		
		Date endDate=null;
		Date startDate=null;
		try {
			endDate = sdf.parse("2023-05-26T18:46:00.000+00:00");
			startDate = sdf.parse("2023-05-26T18:57:00.000+00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("1 + = " +startDate.compareTo(currentDate));
		System.out.println("2 - = " +endDate.compareTo(currentDate));
		
		if(new Date().before(startDate)) {
			
			st="inactive";
			System.out.println("inactive");
		}
		if(new Date().after(startDate) && new Date().before(endDate)) {
			st="active";
			System.out.println("active");
		}
		if(new Date().after(endDate)){
			System.out.println("finished");
		}
		
	}

}
