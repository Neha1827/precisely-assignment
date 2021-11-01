package com.infogix.e2e;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.infogix.resources.Base;
import com.infogix.resources.ExtentReportManager;

public class Listeners extends Base implements ITestListener {
	private static ExtentReports extent = ExtentReportManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Reports Started..");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Reports Finished.."));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		test.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {

		}
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationFile = ExtentReportManager.getScreenshotFileLocation(ExtentReportManager.getCurrentPlatform());
		try {
			FileUtils.copyFile(source,new File(destinationFile));
			System.out.println(" destinationFile " + destinationFile);
			test.get().addScreenCaptureFromPath(destinationFile,
					result.getMethod().getMethodName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().fail(result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("On Test Failed But Within Success Percentage for " + result.getMethod().getMethodName()));
	}
}
