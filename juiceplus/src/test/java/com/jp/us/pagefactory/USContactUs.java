package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import com.jp.datainitialization.USDataInt;
import junit.framework.Assert;

public class USContactUs extends USJPOrder {

	public USContactUs(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.LINK_TEXT, using = "About Us")
	public WebElement about;

	@FindBy(how = How.LINK_TEXT, using = "Contact Us")
	public WebElement contact;

	@FindBy(how = How.ID, using = "firstName")
	public WebElement frst;

	@FindBy(how = How.ID, using = "lastName")
	public WebElement lst;

	@FindBy(how = How.ID, using = "email")
	public WebElement eml;

	@FindBy(how = How.ID, using = "phone")
	public WebElement phn;

	@FindBy(how = How.ID, using = "address1")
	public WebElement addr;

	@FindBy(how = How.ID, using = "address2")
	public WebElement adds;

	@FindBy(how = How.ID, using = "city")
	public WebElement cty;

	@FindBy(how = How.ID, using = "st")
	public WebElement state;

	@FindBy(how = How.ID, using = "zip")
	public WebElement pin;

	@FindBy(how = How.ID, using = "web-ad")
	public WebElement advt;

	@FindBy(how = How.ID, using = "no")
	public WebElement alt;

	@FindBy(how = How.ID, using = "message")
	public WebElement msg;

	@FindBy(how = How.ID, using = "send_btn")
	public WebElement send;

	public void uscontact(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			juiceplus.click();
			waitforElementToAppear(about);
			about.click();
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			contact.click();
			frst.sendKeys(usdataInt.getfirstname());
			lst.sendKeys(usdataInt.getlastname());
			eml.sendKeys(usdataInt.getemail());
			phn.sendKeys(usdataInt.getphonenumber());
			addr.sendKeys(usdataInt.getshippingaddress1());
			adds.sendKeys(usdataInt.getshippingaddress2());
			cty.sendKeys(usdataInt.getcity());
			new Select(state).selectByVisibleText("Tennessee");
			pin.sendKeys(usdataInt.getzipcode());
			advt.click();
			alt.click();
			msg.sendKeys(usdataInt.getmessage());
			send.click();
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("scroll(500,0)");

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
			e.printStackTrace();
		}
	}

}