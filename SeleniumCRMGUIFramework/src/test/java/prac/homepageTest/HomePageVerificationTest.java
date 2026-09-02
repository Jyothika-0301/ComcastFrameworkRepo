package prac.homepageTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class HomePageVerificationTest {

	@Test
	public void homepageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");
		String expectedpage = "Home Page";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.29.4:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
//		//Hard Assert
		Assert.assertEquals(actTitle, expectedpage);

		driver.quit();
//		if(actTitle.trim().equals(expectedpage)){
//            System.out.println(expectedpage +"page is verified ===PASS");
//		}else {
//			System.out.println(expectedpage +"page is  not verified ===FAIL");
//		}
		System.out.println(mtd.getName() + "Test End");
	}

	@Test

	public void verifyLogoHomePageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.29.4:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isDisplayed();
		// Hard Assert
		Assert.assertTrue(status);
//		if(status) {
//			System.out.println("Logo verified==PASS");
//		}else {
//			System.out.println("Logo is not verified==FAIL");
//		}
		driver.quit();
		System.out.println(mtd.getName() + "Test End");
	}

}
	
	
	
//	@Test
//	public void homePageTest(Method mtd) {
//		Reporter.log(mtd.getName()+"test start");
//		Reporter.log("Step-1",true);
//		Reporter.log("Step-2",true);
//		Reporter.log("Step-3",true);
//		Reporter.log("Step-4",true);    we will get in console also if it is true
//		Reporter.log(mtd.getName()+"test end");
//	}
//}