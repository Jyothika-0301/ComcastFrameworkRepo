package genericUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {



	// Generate random number
    public int generateRandomNumber() {
        Random r = new Random();
        return r.nextInt(5000);
    }

    // Generate random number with limit
    public int generateRandomNumber(int limit) {
        Random r = new Random();
        return r.nextInt(limit);
    }

    // Generate current date in yyyy-MM-dd format
    public String getSystemDateYYYYDDMM() {             //getSystemDateYYYYDDMM
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYY-MM-dd");
        return sdf.format(d);
    }
    
        //required date
        public String getRequiredDateYYYYDDMM (int days) {            
            Date d = new Date();
            SimpleDateFormat sim = new SimpleDateFormat("YYYYY-MM-dd");
   
            Calendar cal = sim.getCalendar();
            cal.add(Calendar.DAY_OF_MONTH,30);
            String reqDate = sim.format(cal.getTime());
            return reqDate;
            
        

        /*
         * Alternative:
         * LocalDate ld = LocalDate.now();
         * return ld.toString();
         */
    }


    }
