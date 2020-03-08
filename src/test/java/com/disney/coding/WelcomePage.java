package com.disney.coding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.disney.common.CustomWebDriver;
import com.disney.helpers.ExcelHelpers;
import com.disney.helpers.Locators;
import com.disney.helpers.Constants;


public class WelcomePage {
	private static CustomWebDriver cwd;
	private WebDriver driver;
	private	ExcelHelpers excelhelpers; 
     
	public WelcomePage() throws Exception {
		cwd = CustomWebDriver.getInstance();
		excelhelpers = new ExcelHelpers("Events_Items.xls");
	}
	
	// driver setup
	@BeforeClass
	@Parameters({ "Browser" })
	public void startDriver(String browser) throws Exception
	{
		switch(browser)
		{
		case "FireFox":
			cwd.startFireFoxDriver();
			break;
		case "Chrome":
			cwd.startChromeDriver();
			break;
		case "IE":
			cwd.startIEDriver();
			break;
		default:
			throw new Exception("Invalid browser type passed");
		}
		driver = cwd.getDriver();	
	}
 
	@Test(priority=0)
	public void openDisneyHomePage() {
		driver.navigate().to(Locators.url_Disney);
		String strPageTitle = driver.getTitle();
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("Disney.com | The official home for all things Disney"),"Page title doesn't match");
	}
	
	
	@Test(priority=1)
	public void closeOverlay() {
 		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
 		driver.switchTo().frame(Locators.frame); 
 		driver.findElement(Locators.overlay).click();
 		//driver.findElement(By.xpath("//a[@class='sprite close']")).click();
	}
	
	public void click(String title) {	
 		for (WebElement element : driver.findElements(Locators.list)) {
 			if(element.getText().contains(title))
			{
 				 element.click();
 				 break;
			}
 		} 
	}
	
	@Parameters({"title"})
	@Test(priority=2)
	
	public void clickItem(String title) {
 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.switchTo().defaultContent();
  		click(title);
 
	} 
	
	
	@Test(priority=3)
 	public void createAccount() throws Exception {
		String newUSer ="useraccount" +Constants.d+"@test.com";
		String newPwd = Constants.pwd;
 		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
 		driver.findElement(Locators.login).click();
 		driver.switchTo().frame(Locators.createAccountframe);
 		 driver.findElement(Locators.createAccountLink).click();
 		 driver.findElement(Locators.firstName).sendKeys("firstTest" + Constants.d);
 		 driver.findElement(Locators.lastName).sendKeys("lastTest" +Constants.d);
 		 driver.findElement(Locators.email).sendKeys(newUSer);
 		 driver.findElement(Locators.newPassword).sendKeys(newPwd);
  		 driver.findElement(Locators.verifyPassword).sendKeys(newPwd);
 		 driver.findElement(Locators.dateOfBirth).sendKeys(Constants.dob);
 		 driver.findElement(Locators.checkbox).click();
 		 driver.findElement(Locators.createAccountButton).click();
 		 
 		Thread.sleep(4000);
  		//Assert.assertEquals(driver.findElement(headerText).getText(), "Account Created!",  "Account created popup is not displayed");
  		 driver.findElement(Locators.continueButton).click();
  		 excelhelpers.writeLoginInfoToExcel(newUSer, newPwd);
	}
	
	@Test(priority=4)
	public void selectSectionInfo() throws InterruptedException, IOException {
		
		driver.manage().timeouts().implicitlyWait(16000, TimeUnit.MILLISECONDS);
		driver.switchTo().defaultContent();
		ArrayList<String> al = new ArrayList<String>();

  		//for(WebElement list:driver.findElements(By.cssSelector("li[class*='nav-item dropdown nav-item--center']")))
  		for(WebElement list:driver.findElements(Locators.menuGroup_center))

 		{
		for(WebElement rList:list.findElements(By.tagName("a")))
				{
			String value = rList.getAttribute("textContent").trim();
		
			if(value == null || value.isEmpty()) { 	 
 				String img_text=rList.findElement(By.tagName("img")).getAttribute("alt");	
 				al.add(img_text);
				}
			else{
			al.add(value);
			}	 
				}	 
		}
   		excelhelpers.writeSiteInfoToExcel(al);
  		 }
		
	
	@Test(priority=5)
		public void logout() throws InterruptedException {	
 			driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
			driver.switchTo().defaultContent();
			WebElement login_name = driver.findElement(Locators.username);
			 Actions action = new Actions(driver);	  
			 action.moveToElement(login_name).build().perform();
			 Thread.sleep(5000);
	 		 WebElement element = driver.findElement(Locators.logoutt);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			 element.click();
	 
		}
		 
		
	 @Test(priority=6)
	public void loginUsingExcelData() {
 	  	 driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
 		 driver.findElement(Locators.login).click();
 		 driver.switchTo().frame(Locators.createAccountframe);
 		 HashMap<String,String> hm =  excelhelpers.readLoginInfoFromExcel(0);
 		 Assert.assertNotNull(hm, "Unable to get credentials from Excel");
 		 driver.findElement(Locators.userNameSignin).sendKeys(hm.get("username"));
		 driver.findElement(Locators.passwordSignin).sendKeys(hm.get("password")); 		
		 driver.findElement(Locators.buttonSignin).click();
	}
	
	
	@Test(priority=7)
		public void ListDropDown() throws InterruptedException, IOException {
	 	
			driver.manage().timeouts().implicitlyWait(16000, TimeUnit.MILLISECONDS);
			driver.switchTo().defaultContent();
			ArrayList<String> expectedList = excelhelpers.readSiteInfoFromExcel();
			Assert.assertNotNull(expectedList, "Unable to get site info from Excel");
			ArrayList<String> actualList = new ArrayList<String>();

	  		//for(WebElement list:driver.findElements(By.cssSelector("li[class*='nav-item dropdown nav-item--center']")))
			for(WebElement list:driver.findElements(Locators.menuGroup_center))
	 		{
	  			for(WebElement rList:list.findElements(By.tagName("a")))
				{
	  				String value = rList.getAttribute("textContent").trim();
					if(value == null || value.isEmpty()) 
					{ 	 
		 				String img_text=rList.findElement(By.tagName("img")).getAttribute("alt");	
		 				actualList.add(img_text);
					}
					else
					{
						actualList.add(value);
					}
				}
	 		}
			System.out.println("rList value " + actualList); 
			Assert.assertEquals(expectedList, actualList ,"Info displayed after login is not same as before");
			
			logout();
		}
	
	
	@AfterClass
	//driver quit 
	public void quitBrowser() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}
 
}