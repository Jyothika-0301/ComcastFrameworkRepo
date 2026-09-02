//package crm.producttest;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
//import com.comcast.crm.objectrepositoryutility.HomePage;
//import com.comcast.crm.objectrepositoryutility.LoginPage;
//import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
//import com.comcast.crm.objectrepositoryutility.OrganizationPage;
//
//import genericUtility.ExcelUtility;
//import genericUtility.FileUtility;
//import genericUtility.JavaUtility;
//import genericUtility.WebdriverUtility;
//
//public class DeleteOrgTest {
//	public static void main(String[] args) throws Exception {
//
//		FileUtility fu = new FileUtility();
//		ExcelUtility eu = new ExcelUtility();
//		JavaUtility ju = new JavaUtility();
//		WebdriverUtility wu = new WebdriverUtility();
//
//		String BROWSER = fu.getData("browser");
//		String URL = fu.getData("url");
//		String USERNAME = fu.getData("username");
//		String PASSWORD = fu.getData("password");
//
//		String orgName = eu.readDataFromExcel("Sheet1", 10, 2) + ju.generateRandomNumber();
//
//		WebDriver driver = null;
//		if (BROWSER.equals("chrome"))
//			driver = new ChromeDriver();
//		else if (BROWSER.equals("edge"))
//			driver = new EdgeDriver();
//		else if (BROWSER.equals("firefox"))
//			driver = new FirefoxDriver();
//		else
//			driver = new ChromeDriver();
//
//		// login to app
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.get(URL);
//
//		LoginPage lp = new LoginPage(driver); // it will take care of initialization
//		lp.loginToApp(USERNAME, PASSWORD, orgName);
//
//		// navigate to organization module
//		HomePage hp = new HomePage(driver);
//		hp.getOrglink().click();
//
//		// click on create organization button
//
//		OrganizationPage op = new OrganizationPage(driver);
//		op.getCreateNewOrgBtn().click();
//
//		// enter all the details and create new organization
//
//		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
//		cp.createOrg(orgName);
//
//		// verify Header msg expected result
//		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
//		String actOrgName = oip.getHeaderMsg().getText();
//		if (actOrgName.contains(orgName)) {
//			System.out.println(orgName + "name is verified==pass");
//		} else
//			System.out.println(orgName + "name is not verified==fail");
//
//		// go to the organization
//		hp.getOrglink().click();
//
//		// search for organization
//		op.getSearchEdt().sendKeys(orgName);
//		wu.select(op.getSearchDD(), "Organization Name");
//		op.getSearchBtn().click();
//
//		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../../td[8]/a[text()='del']")).click(); // this xpath is contructed at the runtime in dynamic webtable select and delete org
//																										
//		wu.switchToAlertAndAccept(driver); 
//	
//
//		// logout
//		hp.logout();
//
//	}
//
//}
