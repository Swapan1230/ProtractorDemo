package com.jp.utilities;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.jp.datainitialization.USDataInt;
import com.jp.utilities.USUtil;
import com.jp.utilities.Xls_Reader;

public class USUtil {



public static final Logger LOG = Logger.getLogger(USUtil.class);


public static LinkedList<Object[]> getUSjporder(String sheetName1,
		Xls_Reader xls) {

	LinkedList<Object[]> USjporder = new LinkedList<Object[]>();

	try {
		USDataInt dataInitialization = null;
		int dataStartRowNum = 3;
		int totalRows = 0;

		while (!xls.getCellData(sheetName1, 0, dataStartRowNum + totalRows)
				.equals("")) {

			totalRows++;
		}

		for (int i = dataStartRowNum; i <= (dataStartRowNum + totalRows); i++) {

			dataInitialization = new USDataInt();
			Object obj[] = new Object[1];

			if (xls.getCellData(sheetName1, 0, i).equalsIgnoreCase("US-JPOrder")) {

				dataInitialization.setqty(xls.getCellData(sheetName1, 1, i));
				dataInitialization.setfirstname(xls.getCellData(sheetName1, 2, i));
				dataInitialization.setlastname(xls.getCellData(sheetName1, 3, i));
				dataInitialization.setshippingaddress1(xls.getCellData(sheetName1, 4, i));
				dataInitialization.setshippingaddress2(xls.getCellData(sheetName1, 5, i));
				dataInitialization.setcity(xls.getCellData(sheetName1, 6, i));
				dataInitialization.setzipcode(xls.getCellData(sheetName1, 7, i));
				dataInitialization.setphone(xls.getCellData(sheetName1, 8, i));
				dataInitialization.setemail(xls.getCellData(sheetName1, 9, i));
				dataInitialization.setpaymentmethod(xls.getCellData(sheetName1, 10, i));
				dataInitialization.setcardnumber(xls.getCellData(sheetName1, 11, i));
				dataInitialization.setexpmonth(xls.getCellData(sheetName1, 12, i));
				dataInitialization.setexpyear(xls.getCellData(sheetName1, 13, i));
				dataInitialization.setbillfirstname(xls.getCellData(sheetName1, 14, i));
				dataInitialization.setbilllastname(xls.getCellData(sheetName1, 15, i));
				dataInitialization.setbillphone(xls.getCellData(sheetName1, 16, i));
				dataInitialization.setbillemail(xls.getCellData(sheetName1, 17, i));
				dataInitialization.setbillingaddress1(xls.getCellData(sheetName1, 18, i));
				dataInitialization.setbillingaddress2(xls.getCellData(sheetName1, 19, i));
				dataInitialization.setbillingzip(xls.getCellData(sheetName1, 20, i));
				dataInitialization.setmessage(xls.getCellData(sheetName1, 21, i));
				dataInitialization.setphonenumber(xls.getCellData(sheetName1, 22, i));
				dataInitialization.setrepresentname(xls.getCellData(sheetName1, 23, i));
				dataInitialization.setqtyupdate(xls.getCellData(sheetName1, 24, i));
				dataInitialization.setgershippingaddress(xls.getCellData(sheetName1, 25, i));
				dataInitialization.setgerzip(xls.getCellData(sheetName1, 26, i));
				dataInitialization.setgercity(xls.getCellData(sheetName1, 27, i));
				dataInitialization.setitlshippingaddress(xls.getCellData(sheetName1, 28, i));
				dataInitialization.setitlcity(xls.getCellData(sheetName1, 29, i));
				dataInitialization.setitlzipcode(xls.getCellData(sheetName1, 30, i));
				dataInitialization.setitlphone(xls.getCellData(sheetName1, 31, i));
				obj[0] = dataInitialization;
				USjporder.add(obj);
			}
		}

	} catch (Exception e) {
		LOG.info(e.toString());

	}
	return USjporder;
}
}