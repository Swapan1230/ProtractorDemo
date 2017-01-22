package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.jp.datainitialization.USDataInt;

public class USJPOrderPersonalPage extends USJPOrder {

	public USJPOrderPersonalPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(how = How.XPATH, using = "//li[@id='personalized-alternate-products']/div[2]/div/div/div/a")
	public WebElement viewall;

	@FindBy(how = How.LINK_TEXT, using = "Continue Shopping")
	public WebElement continueshpng;

	@FindBy(how = How.XPATH, using = "//div[@id='tabs-0']/div/ul/li[3]/a/img")
	public WebElement productimg1;

	public void uspersnladdqty(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			driver.get("http://rccq.juiceplus.com/+tw25620");
			selectus();
			ViewAll.click();
			qty.sendKeys(usdataInt.getqty());
			AddToCart.click();
			mouseover.click();
			continueshpng.click();
			productimg1.click();
			AddToCart.click();
			mouseover.click();
		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}

	public void persnlpagecheckout(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			Calculate1.click();
			Zipcode.sendKeys(usdataInt.getzipcode());
			Calculate2.click();
			new Select(city).selectByVisibleText("COLLIERVILLE / SHELBY");
			WebElement element = driver.findElement(By.xpath("//span[3]/a"));
			// JavaSceipt Click
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Checkout2.click();
			FirstName.sendKeys(usdataInt.getfirstname());
			LastName.sendKeys(usdataInt.getlastname());
			Phone1.sendKeys(usdataInt.getphone());
			Phone2.sendKeys(usdataInt.getphonenumber());
			Email.sendKeys(usdataInt.getemail());
			Address1.sendKeys(usdataInt.getshippingaddress1());
			Address2.sendKeys(usdataInt.getshippingaddress2());
			ShippingYes.click();
			Continue.click();
			new Select(PaymentType).selectByVisibleText("Visa");
			CardNumber.sendKeys(usdataInt.getcardnumber());
			LOG.info(usdataInt.getexpmonth());
			new Select(ExpMonth).selectByVisibleText(usdataInt.getexpmonth());
			LOG.info(usdataInt.getexpyear());
			new Select(ExpYear).selectByVisibleText(usdataInt.getexpyear());
			Purchase.click();
			Purchase1.click();

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}
}