package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactTest_DP {
	@Test (dataProvider = "getData")
	public void createContactTest(String firstName, String lastName, long phoneNumber) {
		System.out.println(firstName+","+lastName+","+phoneNumber);
	}

	@DataProvider 
	public Object[][] getData(){
		Object[][] objarr = new Object[3][3];       //3- no of times execution , 2-no of data passing
		objarr[0][0] ="jyothika";
		objarr[0][1]="golu";
		objarr[0][2]=9999999999l;
		
		objarr[1][0] ="biswa";
		objarr[1][1]="idiot";
		objarr[1][2]=8888888888l;
		
		objarr[2][0] ="aditya";
		objarr[2][1]="stupid";
		objarr[2][2]=7777777777l;
		return objarr;
		
	}
}
