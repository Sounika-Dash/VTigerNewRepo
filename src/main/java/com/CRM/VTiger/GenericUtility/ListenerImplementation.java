package com.CRM.VTiger.GenericUtility;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {
	ExtentReports er;
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-->"+"TestScript Execution Started");
		test=er.createTest(methodName);
	}
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-->"+"TestScript Pass");
		test.log(Status.PASS, "TestScript Pass");
	}
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-->"+"TestScript Fail");
		test.log(Status.FAIL, "TestScript Fail");
		// To print exception in our report
				test.log(Status.INFO, result.getThrowable());
				WebDriverUtility wdu = new WebDriverUtility();
				JavaUtility ju = new JavaUtility();
				String screenshotName = methodName + "-" + ju.modifiedDate();
				try {
					String path = wdu.screenshotForEntireWebPage(BaseClass.listenerDriver, screenshotName);
					// Copy the SS fron SS folder and attach the SS into our report.
					test.addScreenCaptureFromPath(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-->"+"TestScript Skipped");
		test.log(Status.SKIP, "TestScript Fail");
		test.log(Status.INFO, result.getThrowable());
		
	}
	public void onStart() {
		System.out.println("Suite level Execution started");
		//Basic system configuration
		ExtentSparkReporter esr=new ExtentSparkReporter("./ExtendReport\\Report-"+new JavaUtility().modifiedDate()+".html");
		esr.config().setDocumentTitle("Execution Report");
		esr.config().setTheme(Theme.DARK);
		esr.config().setReportName("VTiger Execution Report");
		//Report Configuration
		er=new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("BaseUrl", "http://localhost:8888");
		er.setSystemInfo("Browser", "Chrome");
		er.setSystemInfo("Platform", "Window");
		er.setSystemInfo("Reporter Name", "Sriyanka");
	}
	public void onFinish() {
		System.out.println("Suite level execution ended");
	}

}
