package com.CRM.VTiger.GenericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.CRM.VTiger.ObjectRepo.HomePage;
import com.CRM.VTiger.ObjectRepo.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver listenerDriver;
	public FileUtility fu = new FileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();

	@BeforeSuite(groups = { "RegressionSuite", "SmokeSuite" })
	public void DatabaseConnection() {
		System.out.println("Open Database Connection");
	}

	@BeforeClass(groups = { "RegressionSuite", "SmokeSuite" })
	public void ToLaunchBrowser() throws IOException {
		String BROWSER = fu.getDataFromPropertyFile("Browser");
		//String BROWSER=System.getProperty("Browser");
		String URL = fu.getDataFromPropertyFile("url");
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid Browser");
		}
			wutil.maximize(driver);
			wutil.impWait(driver);
			driver.get(URL);
		
	}

	@BeforeMethod(groups = { "RegressionSuite", "SmokeSuite" })
	public void LoginToTheApplication() throws IOException {
		String USERNAME = fu.getDataFromPropertyFile("username");
		String PASSWORD = fu.getDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

	}

	@AfterMethod(groups = { "RegressionSuite", "SmokeSuite" })
	public void LogoutFromTheApplication() {
		HomePage hp = new HomePage(driver);
		hp.logoutFromApp(driver);

	}

	@AfterClass(groups = { "RegressionSuite", "SmokeSuite" })
	public void CloseTheBrowser() {
		driver.quit();
	}

	@AfterSuite(groups = { "RegressionSuite", "SmokeSuite" })
	public void CloseDatabaseConnection() {
		System.out.println("close data base connection");
	}
}
