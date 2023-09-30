package com.CRM.VTiger.ContactTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.CRM.VTiger.GenericUtility.BaseClass;
import com.CRM.VTiger.GenericUtility.FileUtility;
import com.CRM.VTiger.ObjectRepo.HomePage;

public class CreateContactTest extends BaseClass{
	@Test(groups = "RegressionSuite",retryAnalyzer = com.CRM.VTiger.GenericUtility.RetryAnalyzer.class)
	public void contactTest() throws Throwable {

		FileUtility fu = new FileUtility();
		HomePage hp = new HomePage(driver);

		String firstName = fu.getDataFromExcelSheet("Sheet1", 2, 3);
		String lastName = fu.getDataFromExcelSheet("Sheet1", 2, 4);

		hp.clickContact();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("salutationtype")).click();
		driver.findElement(By.xpath("//option[contains(.,'Mr.')]")).click();
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	}
	
	@Test
	public void create()
	{
		System.out.println("Regional regression test done");
	}



}
