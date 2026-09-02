package crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.baseclass.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.ProductPage;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.ListImpClass;
import genericUtility.UtilityClassObject;

@Listeners(genericUtility.ListImpClass.class)
public class CreateContactTest  extends BaseClass{
	
	@Test(groups="smokeTest")                      // (groups ={"smokeTest", "regressionTest"})
	public void createContactTest1() throws Exception {
	
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		//read testscript data from excel file
		String lastName =eu.readDataFromExcel("Contact", 1, 2) + ju.generateRandomNumber();
		
		// navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
				HomePage hp = new HomePage(driver);
				hp.getContactLink().click();
				
				// click on create organization button
				UtilityClassObject.getTest().log(Status.INFO, "navigate to contact org page");
				ContactPage op = new ContactPage(driver);
				op.getContactBtn().click();
				
				
				// enter all the details and create new organization
				UtilityClassObject.getTest().log(Status.INFO, "create org");
				CreateNewContactPage cp = new CreateNewContactPage(driver);
				cp.getLastSearchEdt().sendKeys(lastName);
				cp.getSaveBtn().click();
				
				UtilityClassObject.getTest().log(Status.INFO, "====created new org");
				
				String actHeader = op.getHeaderMsg().getText();
				
				boolean status=actHeader.contains(lastName);
				Assert.assertTrue(status);
				
				String actLastName =driver.findElement(By.id("dtlview_Last Name")).getText();
				
				SoftAssert soft = new SoftAssert();
				soft.assertEquals(actLastName, lastName);
				soft.assertAll();

				// verify Header msg expected result
//				ContactInformationPage cip = new ContactInformationPage(driver);
//				String actContactName = cip.getContactHeaderMsg().getText();
//				if (actContactName.contains(lastName)) {
//					System.out.println(lastName + "name is verified==pass");
//				} else
//					System.out.println(lastName + "name is not verified==fail");

	   	}
//	
	@Test(groups="regressionTest") 
	public void createConWithOrgTest() throws Exception {

		String orgName = eu.readDataFromExcel("Contact", 7, 2) + ju.generateRandomNumber();
		String contactLastName = eu.readDataFromExcel("Contact", 7, 3);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on create organization button

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else
			System.out.println(orgName + "==is not created===FAIL");

//			//verify header phone number info expected result
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		// enter all the details and create new organization

		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.createConWithOrg(contactLastName, orgName);

		// verify header msg
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(contactLastName);
		
		// verify header info expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else
			System.out.println(orgName + "==is not created===FAIL");

	}
}

