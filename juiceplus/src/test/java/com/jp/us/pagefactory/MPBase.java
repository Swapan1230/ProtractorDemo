package com.jp.us.pagefactory;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.jp.utilities.PropertiesFileReader;

/**
 * 
 * 
 * This Class is the base class for the entire script the driver is initialized
 * here
 * 
 */
public class MPBase {
	public String timeStamp;
	public String browserName;
	public String sikulipath = System.getProperty("user.dir");
	public String screenshot = System.getProperty("user.dir") + "_Screenshot";
	public String str;
	public String snum;
	public String excep;
	public String order;
	public String itlssn;
	public String distemail;

	@FindBy(how = How.ID, using = "orderNumber")
	public WebElement ordernumber;

	@FindBy(how = How.LINK_TEXT, using = "Country/Language")
	public WebElement country;

	@FindBy(how = How.ID, using = "local-select")
	public WebElement selcountry;

	@FindBy(how = How.ID, using = "locationApply")
	public WebElement apply;

	public static Properties CONFIG = null;
	public static WebDriver driver;
	public static WebElement webelement = null;
	// public Screen s = new Screen();
	// public Screen screen = new Screen();
	// MP Base File
	public WebDriverWait dwait;
	String[] dialog;
	protected String url = null;
	protected String browser;
	public final int elementTimeOut = Integer.parseInt(PropertiesFileReader.getproperty("element.time.out"));
	public int windowTimeOut = Integer.parseInt(PropertiesFileReader.getproperty("window.time.out"));

	public static final Logger LOG = Logger.getLogger(MPBase.class);

	@SuppressWarnings("static-access")
	protected MPBase(WebDriver driver) {
		if (this.driver == null) {
			this.driver = driver;
		}
		dwait = new WebDriverWait(driver, 30);
	}

	public MPBase(String url, String browser) {

		createWebDriver(url, browser);

	}

	public void createWebDriver(String url, String browser) {

		try {
			this.url = url;
			driver = initDriver(url, browser);
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
		} catch (Exception exec) {

		}
	}

	public WebDriver initDriver(String url, String browser) throws Exception {
		if (browser.equalsIgnoreCase("IE")) {

			// Added by swapan

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + System.getProperty("file.separator") + "BrowserDrivers"
							+ System.getProperty("file.separator") + "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			driver.get(url);

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.get(url);

		}

		driver.manage().window().maximize();
		return driver;
	}

	public static FirefoxProfile FirefoxDriverProfile() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", System.getProperty("user.dir") + "_Downloads");
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"application/octet-stream;application/pdf,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream;application/pdf,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("pdfjs.disabled", true);
		profile.setPreference("plugin.scan.Acrobat", "99.0");
		profile.setPreference("plugin.scan.plid.all", false);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;

	}

	public void quitDriver() {
		driver.close();
	}

	public int switchToNewWindow(String sTitle) {

		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parentWindow)) {
				driver.switchTo().window(childWindows);
				break;
			}
		}
		return 0;
	}

	public void switchWindowUsingTitle(String title) {

		String handle = driver.getWindowHandle();

		for (int i = 1; i <= windowTimeOut; i++) {
			for (String windowHandler : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandler);
				if (driver.getTitle().contains(title)) {
					return;
				}
			}
			if (i == windowTimeOut) {
				driver.switchTo().window(handle);
				throw new NoSuchElementException("No window available with given title : " + title);
			}
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException ie) {

				throw new RuntimeException("Exception occured in sleep method");
			}
		}
	}

	public void scrollPage() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("scroll(0,500)");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		((JavascriptExecutor) driver).executeScript("scroll(500,0)");

	}

	public void switchbackToMainWindow() {

		Set<String> windowIterator = driver.getWindowHandles();
		Object[] handles = windowIterator.toArray();
		driver.switchTo().window(handles[0].toString());
	}

	@SuppressWarnings("static-access")
	public WebElement findElement(By by) throws Exception {
		WebElement element = null;
		for (int i = 1; i <= elementTimeOut; i++) {
			try {
				element = this.driver.findElement(by);
			} catch (WebDriverException e) {
				if (i == elementTimeOut) {
					throw e;
				}

				try {
					Thread.sleep(1000L);
				} catch (InterruptedException ie) {

					throw new RuntimeException("Exception occured in sleep method");
				}
			}
		}
		if (element != null) {
			return element;
		} else {
			throw new WebDriverException("Cannot find element with " + by.getClass());
		}

	}

	public boolean isElementExist(WebElement elemetName) {

		boolean present = true;
		try {
			elemetName.isDisplayed();
			present = true;

		} catch (NoSuchElementException e) {
			present = false;
		}

		return present;
	}

	public static boolean hasTextContainsString(String actual, String expected) throws Exception {

		boolean result = false;
		if (actual.contains(expected)) {
			result = true;
		}
		return result;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickButtoninputValueContaintext(String text) throws Exception {
		findElement(By.xpath("//input[@value='" + text + "']")).click();
	}

	public WebElement waitforElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}

	public void mouseOver(WebElement parentElement, WebElement childElement) throws Exception {
		LOG.info("Inside the Mouse Over Method");
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action.moveToElement(parentElement).perform();

		action.moveToElement(childElement).perform();

	}

	public boolean checkAlert() {
		try {

			str = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			return true;

		} catch (NoAlertPresentException Ex) {

		}
		return false;

	}

	public void browsername() {

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		browserName = cap.getBrowserName().toLowerCase();
		LOG.info("BrowserName is" + browserName);
		String os = cap.getPlatform().toString();
		LOG.info("OS is" + os);
		String v = cap.getVersion().toString();
		LOG.info("OS version is" + v);

	}

	public String gettimestamp() {
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		LOG.info("TimeStamp" + timeStamp);
		return timeStamp;
	}

	public void robot() throws Exception {
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_DELETE);
	}

	public String getssn() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 100000000L;
		snum = String.valueOf(number);
		LOG.info("SSN number is" + number);
		return snum;

	}

	public Boolean waitforElementTotype(WebElement con, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		Boolean tr = wait.until(ExpectedConditions.textToBePresentInElement(con, text));
		return tr;
	}

	public Boolean switchtonewtab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean switchbacktotab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.close();
			driver.switchTo().window(tabs2.get(0));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean getscreenshot() {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File browseFile = new File((screenshot + File.separator + getnum() + ".png"));
			Files.move(scrFile, browseFile);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public String getnum() {
		order = ordernumber.getText();
		return order;
	}

	// -----------------------Program to generate SSN for
	// Italy-----------------------------------------------------------
	public static enum Mode {
		ALPHA, NUMERIC
	}

	public static String generateRandomString(int length, Mode numeric) throws Exception {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (numeric) {

		case ALPHA:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case NUMERIC:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public String italyssn() throws Exception {
		for (int i = 0; i < 1; i++) {
			String alpha = MPBase.generateRandomString(6, MPBase.Mode.ALPHA);
			String numeric = MPBase.generateRandomString(2, MPBase.Mode.NUMERIC);
			String alpha2 = MPBase.generateRandomString(1, MPBase.Mode.ALPHA);
			String numeric1 = MPBase.generateRandomString(2, MPBase.Mode.NUMERIC);
			String alpha3 = MPBase.generateRandomString(1, MPBase.Mode.ALPHA);
			String numeric2 = MPBase.generateRandomString(3, MPBase.Mode.NUMERIC);
			String alpha4 = MPBase.generateRandomString(1, MPBase.Mode.ALPHA);
			itlssn = alpha.concat(numeric).concat(alpha2).concat(numeric1).concat(alpha3).concat(numeric2)
					.concat(alpha4);
		}
		return itlssn;
	}

	// ----------Denmark CPR Number-----------------

	public String getcpr() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 1000000000L;
		snum = String.valueOf(number);
		System.out.println(snum);
		snum = snum.substring(0, 6) + "-" + snum.substring(6, snum.length());
		System.out.println(snum);
		return snum;

	}

	public boolean getFailedScreenshot() {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File browseFile = new File((screenshot + File.separator + gettimestamp() + ".png"));
			Files.move(scrFile, browseFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// ----------------Program to generate Random Email for Add
	// Distributor-------------------
	public static enum ModeEmail {
		ALPHA, NUMERIC
	}

	public static String generateRandomStringemail(int length, ModeEmail alpha) throws Exception {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (alpha) {

		case ALPHA:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case NUMERIC:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public String addDistEmail() throws Exception {
		for (int i = 0; i < 1; i++) {
			String alpha = MPBase.generateRandomStringemail(6, MPBase.ModeEmail.ALPHA);
			String numeric = MPBase.generateRandomStringemail(2, MPBase.ModeEmail.NUMERIC);
			String alpha2 = MPBase.generateRandomStringemail(1, MPBase.ModeEmail.ALPHA);
			String numeric1 = MPBase.generateRandomStringemail(2, MPBase.ModeEmail.NUMERIC);
			String alpha3 = MPBase.generateRandomStringemail(1, MPBase.ModeEmail.ALPHA);
			String numeric2 = MPBase.generateRandomStringemail(3, MPBase.ModeEmail.NUMERIC);
			String alpha4 = MPBase.generateRandomStringemail(1, MPBase.ModeEmail.ALPHA);
			distemail = alpha.concat(numeric).concat(alpha2).concat(numeric1).concat(alpha3).concat(numeric2)
					.concat(alpha4).concat("@juiceplus.com");
		}
		return distemail;
	}

	public void selectcanada() {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			country.click();
			new Select(selcountry).selectByVisibleText("Canada");
			// apply.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectaus() {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			country.click();
			new Select(selcountry).selectByVisibleText("Australia");
			// apply.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectger() {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			country.click();
			new Select(selcountry).selectByVisibleText("Germany");
			// apply.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectus() {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			country.click();
			new Select(selcountry).selectByVisibleText("United States");
			// apply.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}