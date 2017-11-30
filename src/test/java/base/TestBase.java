package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import testnglearning.ExcelReader;
import utilities.DbManager;

public class TestBase {

	/*
	 * WebDriver logs properties mail jdbc Excel TestNG Reporting Waits -
	 * Implicit and Explicit
	 * 
	 * 
	 */

	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public WebElement dropdown;

	public void click(String locator) {

		if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}

		log.info("Clicking on an Element : " + locator);
	}

	public boolean isElementPresent(String locator) {

		try {
			if (locator.endsWith("_xpath")) {
				driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_css")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_id")) {
				driver.findElement(By.id(OR.getProperty(locator)));
			}
		} catch (Throwable t) {

			log.debug("Element not found : " + locator);
			return false;
		}
		log.info("Element found : " + locator);
		return true;

	}
	
	public void type(String locator, String value) {

		if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		log.info("Typing in an Element : " + locator+" entered the value as : "+value);

	}
	
	public void select(String locator, String value) {
		if (locator.endsWith("_xpath")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_css")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_id")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		log.debug("Selecting the value from dropdown element : "+locator+" value select as : "+value);
		
		
	}


	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				Config.load(fis);
				log.info("Config properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("firefox")) {

				driver = new FirefoxDriver();
				log.info("Launching Firefox");
			} else if (Config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Launching Chrome");
			} else if (Config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.info("Launching IE");
			}

			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigating to : " + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

		}

	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(5000L);

		driver.quit();
		log.info("Test execution completed !!!");
	}

}
