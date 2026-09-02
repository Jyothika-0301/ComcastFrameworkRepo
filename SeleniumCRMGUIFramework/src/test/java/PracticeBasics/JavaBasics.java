package PracticeBasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {
	public static void main(String[] args) {
		
		Date date = new Date();
		
		SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
		String actDate = sim.format(date);
		System.out.println(actDate);
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);        // static variable day of month
		String daterequired= sim.format(cal.getTime());   // it will return the required date
		System.out.println(daterequired);

}
}
