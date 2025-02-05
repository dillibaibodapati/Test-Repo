package org.qa.testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.qa.utilities.Xls_Reader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    public static WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest extentTest;
    public static Properties prop;

    public BaseClass() {

        WebDriver driver;

//		prop = new Properties();
//		try {
//			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/com/qa/configuration/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }


    public void init() {

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-proxy-server");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--headless");  // Run in headless mode
//        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);

//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

    }

    public void FailedTC(String testMethodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("test-output/FailedSS"
					+ testMethodName + "_" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    	@BeforeTest
    public static void setExtent() {

        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
//		extent = new ExtentReports(System.getProperty("user.dir") + "/target/reports/{testng-report-dir}/ExtentReport.html", true);
//        extentTest = extent.startTest("test");
        extent.addSystemInfo("Host Name", "Windows");
        extent.addSystemInfo("User Name", "dillibai_b");
        extent.addSystemInfo("w3c", "true");
        extent.addSystemInfo("selenium_version", "4.28.0");
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            setExtent();
        }
        return extent;
    }

    public void setup() {
        extent = BaseClass.getInstance();
        extentTest = extent.startTest("Lambda Test");
    }

    @AfterTest
    public void endReport() {
        extent.flush();
//		extent.close();
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots"
        // under src folder
        String destination = System.getProperty("user.dir") + "/TestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    	@AfterMethod
    public void tearDown(ITestResult result) throws IOException {

            if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
            // extent report

            String screenshotPath = BaseClass.getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent
            // report
            // extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
            // //to add screencast/video in extent report
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

            String screenshotPath = BaseClass.getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
        }
//		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
//		driver.quit();
    }

    public void EndTest() {
        extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
        driver.close();
    }

}
