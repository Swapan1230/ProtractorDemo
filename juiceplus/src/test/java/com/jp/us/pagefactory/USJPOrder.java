package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.jp.datainitialization.USDataInt;

public class USJPOrder extends MPBase {

	public USJPOrder(WebDriver driver) {
		super(driver);

	}

	public static final Logger LOG = Logger.getLogger(USJPOrder.class);

	@FindBy(how = How.XPATH, using = ".//*[@id='personalized-main-products']/div[2]/div/div/div/div[1]/a")
	public WebElement ViewAll;

	@FindBy(how = How.ID, using = "product-quantity")
	public WebElement qty;

	@FindBy(how = How.ID, using = "add-to-cart")
	public WebElement AddToCart;

	@FindBy(how = How.XPATH, using = "//li[2]/a")
	public WebElement mouseover;

	@FindBy(how = How.LINK_TEXT, using = "CHECK OUT")
	public WebElement Checkout1;

	@FindBy(how = How.XPATH, using = ".//*[@id='taxDetail']/a")
	public WebElement Calculate1;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingAddress.postalCode")
	public WebElement Zipcode;

	@FindBy(how = How.ID, using = "btnZipLookup")
	public WebElement Calculate2;

	@FindBy(how = How.ID, using = "citySelectBox")
	public WebElement city;

	@FindBy(how = How.XPATH, using = "//span[3]/a")
	public WebElement Go;

	@FindBy(how = How.XPATH, using = "//form[@id='formCart']/div[3]/div/div[2]/div/fieldset/div/a")
	public WebElement Checkout2;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingPerson.firstName")
	public WebElement FirstName;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingPerson.lastName")
	public WebElement LastName;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingPhone.dialNumber")
	public WebElement Phone1;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingPhoneAlternate.dialNumber")
	public WebElement Phone2;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingEmail.addrLine")
	public WebElement Email;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingAddress.line1")
	public WebElement Address1;

	@FindBy(how = How.ID, using = "customerOrder.orderShipment.shippingAddress.line2")
	public WebElement Address2;

	@FindBy(how = How.ID, using = "chkSameAsShipping")
	public WebElement ShippingYes;

	@FindBy(how = How.ID, using = "rdoReferredNo")
	public WebElement RepresentativeNo;

	@FindBy(how = How.XPATH, using = "//form[@id='formCart']/fieldset[5]/div/a")
	public WebElement Continue;

	@FindBy(how = How.ID, using = "selPaymentType")
	public WebElement PaymentType;

	@FindBy(how = How.ID, using = "customerOrder.orderPayment.creditcardPayment.cardNumber")
	public WebElement CardNumber;

	@FindBy(how = How.ID, using = "selExpMonth")
	public WebElement ExpMonth;

	@FindBy(how = How.ID, using = "selExpYear")
	public WebElement ExpYear;

	@FindBy(how = How.XPATH, using = "//form[@id='formCart']/fieldset[3]/div/a")
	public WebElement Purchase;

	@FindBy(how = How.ID, using = "next-button")
	public WebElement Purchase1;

	@FindBy(how = How.ID, using = "userQuantity2000")
	public WebElement updateqty;

	@FindBy(how = How.LINK_TEXT, using = "Update")
	public WebElement update;

	@FindBy(how = How.LINK_TEXT, using = "REMOVE ITEM")
	public WebElement remove;

	@FindBy(how = How.CSS, using = "a")
	public WebElement juiceplus1;

	@FindBy(how = How.XPATH, using = "//div[@class='top-bar']")
	public WebElement juiceplus;

	@FindBy(how = How.XPATH, using = "//div[2]/a/div")
	public WebElement jphome;

	public void usaddqty(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			selectus();
			scrollPage();
			ViewAll.click();
			qty.sendKeys(usdataInt.getqty());
			AddToCart.click();
			mouseover.click();

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}

	public void update(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			Calculate1.click();
			Zipcode.sendKeys(usdataInt.getzipcode());
			Calculate2.click();
			new Select(city).selectByVisibleText("COLLIERVILLE / SHELBY");
			WebElement element = driver.findElement(By.xpath("//span[3]/a"));
			// JavaScript Click
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Checkout2.click();
			FirstName.sendKeys(usdataInt.getfirstname());
			LastName.sendKeys(usdataInt.getlastname());
			Phone1.sendKeys(usdataInt.getphone());
			Email.sendKeys(usdataInt.getemail());
			Address1.sendKeys(usdataInt.getshippingaddress1());
			Address2.sendKeys(usdataInt.getshippingaddress2());
			ShippingYes.click();
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