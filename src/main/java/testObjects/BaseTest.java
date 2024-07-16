package testObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;
	ExtentReports reports;
	ExtentTest logger;

	@BeforeTest
	public void reportsmanager() {

		ExtentSparkReporter spark = new ExtentSparkReporter("reports/report.html");
		spark.config().setDocumentTitle("learn");
		spark.config().setReportName("Test Report");
		spark.config().setTheme(Theme.STANDARD);
		reports = new ExtentReports();
		reports.attachReporter(spark);

	}

	@BeforeMethod
	public void webDriverSetup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void createreports(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, result.getTestName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, result.getTestName());
		} else {
			logger.info(result.getThrowable());
		}

	}

	@AfterTest
	public void teardown() {

		reports.flush();
		if (driver != null) {
			driver.quit();
		}
	}

}
