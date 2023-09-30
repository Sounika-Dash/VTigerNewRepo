package com.CRM.VTiger.organizationTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.CRM.VTiger.GenericUtility.BaseClass;
import com.CRM.VTiger.GenericUtility.JavaUtility;
import com.CRM.VTiger.ObjectRepo.CreateNewOrg;
import com.CRM.VTiger.ObjectRepo.HomePage;
import com.CRM.VTiger.ObjectRepo.OrganizationInfoPage;
import com.CRM.VTiger.ObjectRepo.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = { "RegressionSuite",
			"SmokeSuite" }, retryAnalyzer = com.CRM.VTiger.GenericUtility.RetryAnalyzer.class)
	public void createOrganization() throws Throwable {

		JavaUtility ju = new JavaUtility();
		HomePage hp = new HomePage(driver);

		String name = fu.getDataFromExcelSheet("Class", 0, 3) + ju.getRandom();

		OrganizationPage op = new OrganizationPage(driver);
		hp.clickOrganization();
		Reporter.log("click on organization", true);
// Assert.fail();//to fail the test script
		op.clickOnLookUp();
		Reporter.log("click on lookup image", true);

		CreateNewOrg cno = new CreateNewOrg(driver);
		cno.passOrgName(name);
		cno.clickOnSaveBtn();
		Reporter.log("click on save button", true);

		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String head = orgInfo.nameOfOrg();
		wutil.screenshotForEntireWebPage(driver, "Papa");

//if (head.contains(name)) {
//	System.out.println("TC passed");		
//} else {
//	System.out.println("TC failed");
//}
		Assert.assertTrue(head.contains(name));
		Reporter.log("validation passed", true);
		System.out.println("TC passed");

	}

}
