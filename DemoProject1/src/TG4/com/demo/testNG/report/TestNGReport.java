package TG4.com.demo.testNG.report;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestNGReport {
	WebDriver driver;
	String TestNGReportPath="E://Grid//TestRunReport";
	String ExpectedLoginText="Use UrbanPro login";
	String ActulLoginText;
	@Test (groups="job-2")
	public void m1() throws Exception {
		LaunchBrowser("https://www.urbanpro.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("Navigated to UrbanPro Site");
		WebElement NavButton= driver.findElement(By.xpath("//div/nav/span"));
		NavButton.click();
		Reporter.log("Clicked on Navigation button");
		WebElement Login= driver.findElement(By.xpath("//div/a[contains(text(),'Login')]"));
		Login.click();
		Reporter.log("Clicked on Login button");
		WebElement facebookLogin= driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/a/div/p"));
		facebookLogin.click();
		WebElement UrbanProLogintext= driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/p[2]"));
		ActulLoginText=UrbanProLogintext.getText();
		try {
			Assert.assertEquals(ActulLoginText, ExpectedLoginText);
		} catch(AssertionError e) {
			takeSnapShot();
		}
		
		
		
	}

	
	public void LaunchBrowser(String url) throws MalformedURLException {

		String Node = "http://localhost:4444/wd/hub";
		DesiredCapabilities cap = DesiredCapabilities.chrome();

		driver = new RemoteWebDriver(new URL(Node), cap);

		driver.navigate().to(url);

	}

	public void takeSnapShot() throws Exception{
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH.mm.ss");
			LocalDateTime now = LocalDateTime.now();

			String fileWithPath= TestNGReportPath + "\\SnapShotOfFailedScenarios\\" +"_" + dtf.format(now)+".png";
		//	String fileWithPath="G:\\TestExceutionResults\\SnapShotOfFailedScenarios\\" + scenarioID + "_" + dtf.format(now)+".png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(fileWithPath);
			FileUtils.copyFile(SrcFile, DestFile);
			Reporter.log("<a href='"+ DestFile.getAbsolutePath() + "'> <img src='file://"+ DestFile.getAbsolutePath() + "' height='300' width='500'/> </a>");

		}catch(Exception e){
			Reporter.log("Error Occurred : "+e.getMessage());
		}
	}
	@BeforeSuite(alwaysRun = true)
	public void setup(ITestContext ctx) {
		TestRunner runner = (TestRunner) ctx;

		runner.setOutputDirectory(TestNGReportPath);
	}
	
	@AfterTest
	public void driverclose() {
		if (driver != null)
			driver.close();
	}

}
