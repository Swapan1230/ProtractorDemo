package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.jp.datainitialization.USDataInt;

public class GERContactMe extends USContactUsPersonalPage {

	public GERContactMe(WebDriver driver) {
		super(driver);

	}

	@FindBy(how = How.LINK_TEXT, using = "Über uns")
	public WebElement uberuns;

	@FindBy(how = How.LINK_TEXT, using = "Kontakt")
	public WebElement Kontakt;

	public void gercontact(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			driver.get("http://rccq.juiceplus.com/+tw25620");
			selectger();
			uberuns.click();
			WebElement uberuns = driver.findElement(By.linkText("Über uns"));
			waitforElementToAppear(uberuns);
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", uberuns);
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);

			WebElement Kontakt = driver.findElement(By.linkText("Kontakt"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", Kontakt);
			Kontakt.click();
			frst.sendKeys(usdataInt.getfirstname());
			lst.sendKeys(usdataInt.getlastname());
			eml.sendKeys(usdataInt.getemail());
			phn.sendKeys(usdataInt.getphonenumber());
			addr.sendKeys(usdataInt.getgershippingaddress());
			cty.sendKeys(usdataInt.getgercity());
			pin.sendKeys(usdataInt.getgerzip());
			representative.click();
			representname.sendKeys(usdataInt.getrepresentname());
			representtnumber.sendKeys(usdataInt.getphonenumber());
			yes.click();
			msg.sendKeys(usdataInt.getmessage());
			send.click();

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
			e.printStackTrace();
		}
	}

}
