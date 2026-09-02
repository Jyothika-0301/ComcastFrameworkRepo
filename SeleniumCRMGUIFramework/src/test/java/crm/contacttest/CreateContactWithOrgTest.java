package crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebdriverUtility;

public class CreateContactWithOrgTest extends BaseClass {
	@Test
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
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else
			System.out.println(orgName + "==is not created===FAIL");

		// verify header info expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "===is created=== PASS");
		} else
			System.out.println(orgName + "==is not created===FAIL");

	}

}
