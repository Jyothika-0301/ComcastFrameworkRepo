package TestNG;



import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;

public class InvoiceTest extends BaseClass {
	@Test
	public void createInvoiceTest() {
		System.out.println("Execute createInvoiceTest");
		String actTitle =driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("Execute createInvoiceWithContactTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
//	@Test(retryAnalyzer = genericUtility.RetryListenerImp.class)
//	public void activateSim() {
//		System.out.println("Execute createInvoiceTest");
//		
//		Assert.assertEquals(" ", "Login");
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		System.out.println("Step-3");
//		System.out.println("Step-4");
//	
//}
}