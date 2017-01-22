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

public class USJPDiffShipping extends USJPOrder {

	public USJPDiffShipping(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingPerson.firstName")
	public WebElement billfirstname;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingPerson.lastName")
	public WebElement billlastname;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingPhone.dialNumber")
	public WebElement billphone;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingEmail.addrLine")
	public WebElement billemail;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingAddress.line1")
	public WebElement billingaddress1;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingAddress.line2")
	public WebElement billingaddress2;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.billingAddress.postalCode")
	public WebElement billingzip;

	@FindBy(how = How.ID, using = "btnBillingZipLookup")
	public WebElement billinglookup;

	@FindBy(how = How.CSS, using = "a")
	public WebElement juiceplus1;

	public void usdiffshipping(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			juiceplus.click();
			ViewAll.click();
			qty.sendKeys(usdataInt.getqty());
			AddToCart.click();
			mouseover.click();
			Calculate1.click();
			Zipcode.sendKeys(usdataInt.getzipcode());
			Calculate2.click();
			new Select(city).selectByVisibleText("COLLIERVILLE / SHELBY");
			WebElement element = driver.findElement(By.xpath("//span[3]/a"));

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Checkout2.click();
			FirstName.sendKeys(usdataInt.getfirstname());
			LastName.sendKeys(usdataInt.getlastname());
			Phone1.sendKeys(usdataInt.getphone());
			Email.sendKeys(usdataInt.getemail());
			Address1.sendKeys(usdataInt.getshippingaddress1());
			Address2.sendKeys(usdataInt.getshippingaddress2());
			billfirstname.sendKeys(usdataInt.getbillfirstname());
			billlastname.sendKeys(usdataInt.getbilllastname());
			billphone.sendKeys(usdataInt.getbillphone());
			billemail.sendKeys(usdataInt.getbillemail());
			billingaddress1.sendKeys(usdataInt.getbillingaddress1());
			billingaddress2.sendKeys(usdataInt.getbillingaddress2());
			billingzip.sendKeys(usdataInt.getbillingzip());
			billinglookup.click();
			new Select(city).selectByVisibleText("MEMPHIS / SHELBY");
			WebElement element1 = driver.findElement(By.xpath("//span[3]/a"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
			RepresentativeNo.click();
			Continue.click();
			new Select(PaymentType).selectByVisibleText("Visa");
			CardNumber.sendKeys(usdataInt.getcardnumber());
			LOG.info(usdataInt.getexpmonth());
			new Select(ExpMonth).selectByVisibleText(usdataInt.getexpmonth());
			LOG.info(usdataInt.getexpyear());
			new Select(ExpYear).selectByVisibleText(usdataInt.getexpyear());
			Purchase.click();
			Purchase1.click();
			juiceplus1.click();

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}
}