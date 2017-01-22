package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.jp.datainitialization.USDataInt;

public class USContactUsPersonalPage extends USContactUs {

	public USContactUsPersonalPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(how = How.ID, using = "jp-rep")
	public WebElement representative;

	@FindBy(how = How.ID, using = "rep-name")
	public WebElement representname;

	@FindBy(how = How.ID, using = "rep-no")
	public WebElement representtnumber;

	@FindBy(how = How.ID, using = "yes")
	public WebElement yes;

	public void uscontactus(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			driver.get("http://rccq.juiceplus.com/+tw25620");
			about.click();
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
			representative.click();
			representname.sendKeys(usdataInt.getrepresentname());
			representtnumber.sendKeys(usdataInt.getphonenumber());
			yes.click();
			msg.sendKeys(usdataInt.getmessage());
			send.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}