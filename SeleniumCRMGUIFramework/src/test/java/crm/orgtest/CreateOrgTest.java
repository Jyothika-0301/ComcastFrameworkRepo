package crm.orgtest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

import com.baseclass.BaseClass;
import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;

public class CreateOrgTest extends BaseClass {

	@Test(groups ="smokeTest")
	public void createOrganizationTest() throws Throwable {

		String orgName = eu.readDataFromExcel("Contact", 1, 2) + ju.generateRandomNumber();

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on create organization button

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		cp.createOrg(orgName);

		// verify Header msg expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified==pass");
		} else
			System.out.println(orgName + "name is not verified==fail");
	}

}