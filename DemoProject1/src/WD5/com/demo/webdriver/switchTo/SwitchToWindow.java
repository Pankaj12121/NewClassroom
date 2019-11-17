package WD5.com.demo.webdriver.switchTo;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchToWindow {

	static WebDriver driver;

	@FindBy(linkText = "NSE")
	static WebElement nselink;

	@FindBy(linkText = "Customer Service")
	static WebElement cusService;

	@FindBy(id = "keyword")
	static WebElement searchKeyword;

	@FindBy(linkText = "Login")
	static WebElement loginlink;

	@FindBy(id = "txtUserId")
	static WebElement uName;

	@FindBy(id = "txtPass")
	static WebElement passWord;

	@FindBy(id = "txtDOB")
	static WebElement dob;

	@FindBy(id = "lbtLogin")
	static WebElement loginButton;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "\\Grid\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, new SwitchToWindow());
		String baseUrl = "http://icicidirect.com";
		driver.get(baseUrl);
		String BaseTitle = driver.getTitle();

		try {
			SwitchToWindow Obj = new SwitchToWindow();

			cusService.click();
			String title = "NSE - National Stock Exchange of India Ltd.";
			bringElementintoView(nselink);
			nselink.click();
			Obj.switchToWindow(title);
			searchKeyword.click();
			searchKeyword.sendKeys("bajaj");
			System.out.println("Switched to new window successful");
			driver.close();
			Obj.switchToWindow(BaseTitle);
			String baseTitle = "ICICI Direct Customer Service";
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			bringElementintoView(loginlink);
			loginlink.click();

			uName.sendKeys("Test123");
			passWord.sendKeys("pass1234");
			dob.sendKeys("12122001");
			loginButton.click();
			System.out.println("switch back to default window successful");
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element not displayed");

		}
	}

	public void switchToWindow(String title) {
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			driver.switchTo().window(handle);
			// driver.manage().window().maximize();

			if (driver.getTitle().contains(title)) {
				break;
			}
		}

	}

	public static void bringElementintoView(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}
}