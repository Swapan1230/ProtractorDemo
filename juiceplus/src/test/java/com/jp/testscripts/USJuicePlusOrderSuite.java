package com.jp.testscripts;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jp.datainitialization.USDataInt;
import com.jp.utilities.USUtil;
import com.jp.utilities.Xls_Reader;

public class USJuicePlusOrderSuite extends Base {

	public static final Logger LOG = Logger
			.getLogger(USJuicePlusOrderSuite.class);
	String testDataPath = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\jp\\test\\data\\Juiceplus.xlsx";
	public Xls_Reader xls = new Xls_Reader(testDataPath);

	@Test(description = "USJuiceplusorder", dataProvider = "getUsjporder", priority = 0)
	public void usjporder(USDataInt usdataInt) throws Exception {
		
		usjporder = driverhome.getUsjpOrder();
		usjporder.usaddqty(usdataInt);
		usjporder.update(usdataInt);

	}

	@Test(description = "USJuiceplusDiffshipping", dataProvider = "getUsjporder", priority = 1)
	public void usjpdiffshipping(USDataInt usdataInt) throws Exception {
		usjpdiff = driverhome.getUsjpDiffShipping();
		usjpdiff.usdiffshipping(usdataInt);

	}

	@Test(description = "USJuiceplusUpdateproduct", dataProvider = "getUsjporder", priority = 2)
	public void usjpUpdate(USDataInt usdataInt) throws Exception {
		usjpupdate = driverhome.getUpdateProduct();
		usjpupdate.usjpupdateproduct(usdataInt);
		usjpupdate.usjpcontinueshpng(usdataInt);
	}

	@Test(description = "USJuiceplusContactUs", dataProvider = "getUsjporder", priority = 3)
	public void uscontactus(USDataInt usdataInt) throws Exception {
		uscontact = driverhome.getUsContactUs();
		uscontact.uscontact(usdataInt);
	}

	@Test(description = "USJuiceplusNewslettersignup", dataProvider = "getUsjporder", priority = 4)
	public void usjpnewslettersignup(USDataInt usdataInt) throws Exception {
		usnewsletter = driverhome.getNewsLetterSignUp();
		usnewsletter.usnewsletter(usdataInt);
	}

	@Test(description = "USJPOrderpersonalpage", dataProvider = "getUsjporder", priority = 5)
	public void usjporderpersonalpage(USDataInt usdataInt) throws Exception {
		uspersnljp = driverhome.getUsjpOrderPersonalPage();
		uspersnljp.uspersnladdqty(usdataInt);
		uspersnljp.persnlpagecheckout(usdataInt);
	}

	@Test(description = "USJPContactpersonalpage", dataProvider = "getUsjporder", priority = 6)
	public void uscontactpersonalpage(USDataInt usdataInt) throws Exception {
		uspersnlcontact = driverhome.getContactUsPersonalPage();
		uspersnlcontact.uscontactus(usdataInt);
	}

	@Test(description = "USPPNewslettersignup", dataProvider = "getUsjporder", priority = 7)
	public void usppnewslettersignup(USDataInt usdataInt) throws Exception {
		usppsignup = driverhome.getUsppNewsletterSignUp();
		usppsignup.usppnewsletter(usdataInt);
	}

	@Test(description = "Gercontactme", dataProvider = "getUsjporder", priority = 8)
	public void gercontactus(USDataInt usdataInt) throws Exception {
		gercontact = driverhome.getGerContactMe();
		gercontact.gercontact(usdataInt);
	}

	@Test(description = "Itcontactme", dataProvider = "getUsjporder", priority = 9)
	public void itcontactus(USDataInt usdataInt) throws Exception {
		//itcontact = driverhome.getItContactMe();
		//itcontact.itcontactme(usdataInt);
	}

	@DataProvider
	public Iterator<Object[]> getUsjporder() {
		return USUtil.getUSjporder("US-JPOrder", xls).iterator();
	}
}