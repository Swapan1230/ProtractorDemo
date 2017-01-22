package com.jp.us.pagefactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.jp.datainitialization.USDataInt;

public class USJPUpdateProduct extends USJPOrder {

	public USJPUpdateProduct(WebDriver driver) {
		super(driver);

	}

	@FindBy(how = How.XPATH, using = "//div[@id='tabs-0']/div/ul/li[2]/a/img")
	public WebElement productimg;

	@FindBy(how = How.LINK_TEXT, using = "Remove Item")
	public WebElement removeitem;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/form/div[3]/div[1]/div[2]/div/div[2]/div[3]/div/a[1]")
	public WebElement updateitem;

	@FindBy(how = How.LINK_TEXT, using = "Continue Shopping")
	public WebElement continueshpng;

	@FindBy(how = How.XPATH, using = "//div[@id='tabs-0']/div/ul/li[3]/a/img")
	public WebElement productimg1;

	@FindBy(how = How.ID, using = "userQuantity3000")
	public WebElement updateqty1;

	@FindBy(how = How.XPATH, using = "//div[@id='tabs-0']/div/ul/li[7]/a/img")
	public WebElement productimg2;

	@FindBy(how = How.CLASS_NAME, using = "juice")
	public WebElement juiceplus;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/form/div[3]/div[1]/div[2]/div")
	public WebElement shoppingcart;

	public List<WebElement> anchor;

	public WebElement row;

	public String deleteitem;

	public String datatext;

	public void usjpupdateproduct(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			scrollPage();
			ViewAll.click();
			qty.sendKeys(usdataInt.getqty());
			AddToCart.click();
			mouseover.click();

			List<WebElement> products = shoppingcart.findElements(By.tagName("div"));
			Iterator<WebElement> j = products.listIterator();
			String findqty = "no";
			while (j.hasNext()) {
				row = j.next();
				List<WebElement> quantityupdate = row.findElements(By.tagName("input"));
				Iterator<WebElement> k = quantityupdate.listIterator();
				while (k.hasNext()) {
					WebElement column = k.next();
					String data = column.getAttribute("type");
					datatext = column.getAttribute("id");
					if (data.contains("text")) {
						findqty = "yes";
						break;
					}
				}
				if (findqty == "yes") {
					break;
				}
			}
			driver.findElement(By.id(datatext)).clear();
			driver.findElement(By.id(datatext)).sendKeys(usdataInt.getqtyupdate());
			anchor = row.findElements(By.tagName("a"));
			Iterator<WebElement> k = anchor.iterator();
			while (k.hasNext()) {
				WebElement column = k.next();
				waitforElementToAppear(column); // added
				driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
				String updatelink = column.getAttribute("class");
				if (updatelink.contains("Update")) {
					String update = updatelink.toString().trim();
					driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
					driver.findElement(By.partialLinkText(update)).click();
					break;
				}
			}

		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}

	public void usjpcontinueshpng(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			waitforElementToAppear(continueshpng);

			continueshpng.click();
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			productimg1.click();

			qty.sendKeys(usdataInt.getqty());

			AddToCart.click();

			mouseover.click();

			String deleteqty = this.deletecart();

			driver.findElement(By.partialLinkText(deleteqty)).click();
			update(usdataInt);
		} catch (Exception e) {
			excep = e.toString();
			Assert.fail(excep);
		}
	}

	public String deletecart() {
		driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
		String delete = "";
		List<WebElement> products = shoppingcart.findElements(By.tagName("div"));
		int productsize = products.size();
		System.out.println(productsize);
		Iterator<WebElement> j = products.iterator();
		String findqty = "no";
		while (j.hasNext()) {
			WebElement row = j.next();
			List<WebElement> quantityupdate = row.findElements(By.tagName("a"));
			Iterator<WebElement> k = quantityupdate.iterator();
			while (k.hasNext()) {
				WebElement column = k.next();
				waitforElementToAppear(column);

				delete = column.getAttribute("class");
				if (delete.contains("Remove Item")) {
					driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
					deleteitem = delete.toString().trim();
					findqty = "yes";
					break;
				}
			}
			if (findqty == "yes") {
				break;
			}
		}
		return deleteitem;
	}
}