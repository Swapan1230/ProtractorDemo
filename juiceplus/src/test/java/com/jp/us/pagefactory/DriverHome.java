package com.jp.us.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.jp.utilities.PropertiesFileReader;

public class DriverHome extends MPBase {

	public DriverHome(WebDriver driver) {
		super(driver);

	}

	public static final String prod_url = PropertiesFileReader.getproperty("app.url");

	public DriverHome(String browser, String text) {
		super(prod_url, browser);
	}

	public DriverHome(String url) {
		super(url, "test");

	}

	public USJPOrder getUsjpOrder() {
		return PageFactory.initElements(driver, USJPOrder.class);
	}

	public USJPDiffShipping getUsjpDiffShipping() {
		return PageFactory.initElements(driver, USJPDiffShipping.class);
	}

	public USJPUpdateProduct getUpdateProduct() {
		return PageFactory.initElements(driver, USJPUpdateProduct.class);
	}

	public USContactUs getUsContactUs() {
		return PageFactory.initElements(driver, USContactUs.class);
	}

	public USJPNewsLetterSignUp getNewsLetterSignUp() {
		return PageFactory.initElements(driver, USJPNewsLetterSignUp.class);
	}

	public USJPOrderPersonalPage getUsjpOrderPersonalPage() {
		return PageFactory.initElements(driver, USJPOrderPersonalPage.class);
	}

	public USContactUsPersonalPage getContactUsPersonalPage() {
		return PageFactory.initElements(driver, USContactUsPersonalPage.class);
	}

	public USPPNewsletterSignUp getUsppNewsletterSignUp() {
		return PageFactory.initElements(driver, USPPNewsletterSignUp.class);
	}

	public GERContactMe getGerContactMe() {
		return PageFactory.initElements(driver, GERContactMe.class);

	}
	// -------------------------Canada--------------------------
	// ------------------Australia-----------------------------------

	/*
	 * public ITContactMe getItContactMe() { return
	 * PageFactory.initElements(driver, ITContactMe.class);
	 * 
	 * }
	 */
}