package com.infogix.resources;

import java.io.File;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	private static ExtentReports extent;
    private static Platform platform;
    private static String reportFileName = "InfogixReports-NehaJamdade-Test-Automaton-Report.html";
    private static String screenshotFileName = "InfogixReports-NehaJamdade-Test-Automaton-Report.png";
    private static String macPath = System.getProperty("user.dir")+ "/TestReport";
    private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
    private static String macScreenshotFileLoc = macPath + "/" + screenshotFileName;
    private static String winScreenshotFileLoc = windowsPath + "\\" + screenshotFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        System.out.println(" are you called ");
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
		reporter.config().setReportName("Infogix Test Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		
        return extent;
    }
    
    public static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }
    
    public static String getScreenshotFileLocation(Platform platform) {
        String screenshotFileLocation = null;
        switch (platform) {
            case MAC:
            	screenshotFileLocation = macScreenshotFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
            	screenshotFileLocation = winScreenshotFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return screenshotFileLocation;
    }
    
    /**
     * Create the report path if it does not exist
     * @param path
     */
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    /**
     * Get current platform, currently support windows, mac and linux
     * @return
     */
    public static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
