package com.CRM.VTiger.organizationTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.CRM.VTiger.GenericUtility.BaseClass;
import com.CRM.VTiger.GenericUtility.FileUtility;
import com.CRM.VTiger.GenericUtility.JavaUtility;
import com.CRM.VTiger.ObjectRepo.CreateNewOrg;
import com.CRM.VTiger.ObjectRepo.HomePage;
import com.CRM.VTiger.ObjectRepo.OrganizationInfoPage;
import com.CRM.VTiger.ObjectRepo.OrganizationPage;

public class CreateMultipleOrganizationTest extends BaseClass{
	@Test(dataProvider = "multipleDataFromExcel", dataProviderClass = FileUtility.class)
	public void multipleOrg(String multipleName) throws IOException {
		JavaUtility ju = new JavaUtility();
		HomePage hp = new HomePage(driver);

		// String name = fu.getDataFromexcelSheet("Class", 0, 3) + ju.getRandom();
		String name = multipleName + ju.getRandom();
		OrganizationPage op = new OrganizationPage(driver);
		hp.clickOrganization();
		op.clickOnLookUp();
		CreateNewOrg cno = new CreateNewOrg(driver);
		cno.passOrgName(name);
		cno.clickOnSaveBtn();
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String head = orgInfo.nameOfOrg();
		wutil.screenshotForEntireWebPage(driver, "Papa");

		/*
		 * if (head.contains(name)) { System.out.println("TC passed"); } else {
		 * System.out.println("TC failed"); }
		 */
		Assert.assertTrue(head.contains(name));
		System.out.println("TC passed");

	}


}
