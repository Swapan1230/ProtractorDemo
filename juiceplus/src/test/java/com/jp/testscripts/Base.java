package com.jp.testscripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.jp.us.pagefactory.DriverHome;
import com.jp.us.pagefactory.GERContactMe;
//import com.jp.us.pagefactory.ITContactMe;
import com.jp.us.pagefactory.USContactUs;
import com.jp.us.pagefactory.USContactUsPersonalPage;
import com.jp.us.pagefactory.USJPDiffShipping;
import com.jp.us.pagefactory.USJPNewsLetterSignUp;
import com.jp.us.pagefactory.USJPOrder;
import com.jp.us.pagefactory.USJPOrderPersonalPage;
import com.jp.us.pagefactory.USJPUpdateProduct;
import com.jp.us.pagefactory.USPPNewsletterSignUp;
import com.jp.utilities.Xls_Reader;

/**
 * 
 * 
 * This is the base class for all the test suites,It executes before executing
 * the TestSuite Classes
 * 
 */

public class Base {
	public static final Logger LOG = Logger.getLogger(Base.class);
	public DriverHome driverhome;
	public USJPOrder usjporder;
	public USJPDiffShipping usjpdiff;
	public USJPUpdateProduct usjpupdate;
	public USContactUs uscontact;
	public USJPNewsLetterSignUp usnewsletter;
	public USJPOrderPersonalPage uspersnljp;
	public USContactUsPersonalPage uspersnlcontact;
	public USPPNewsletterSignUp usppsignup;
	
	public GERContactMe gercontact;
	//public ITContactMe itcontact;
	public static String passMessage = null;
	public static String finalMessage = null;
	public static String skipMessage = null;
	public Xls_Reader xls;

	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws Exception {
		try {

			LOG.info("Open The Url");
			driverhome = new DriverHome(browser, "test");
			LOG.info("test");
		} catch (WebDriverException e) {
			System.out.println(e);

		}
	}

	@AfterClass
	public void close() throws Exception {
		try {
			Thread.sleep(3000);
			driverhome.quitDriver();
		} catch (WebDriverException e) {
			System.out.println(e);

		}
	}

}