package com.disney.helpers;

import org.openqa.selenium.By;

public class Locators {

	/* default creds
	 String loginId ="test@ignore.com";
	 String loginPwd = "pLease@ignore2020";
	 */

	public static final By login = By.xpath("//div[@class='user user__container']/button");
	public static final By signUp = By.xpath("//span[@class='user-message']");
	public static final By userNameSignin = By.xpath("//div[@class='field field-username-email']//input");
	//public static final By userNameSignin = By.cssSelector("input#email");
	//public static final By passwordSignin = By.cssSelector("input#password");
	public static final By passwordSignin = By.xpath("//div[@class='field field-password']//input");
	public static final By buttonSignin = By.xpath("//div[@class='btn-group touch-print-btn-group-wrapper']");
	public static final By createAccountLink = By.xpath("//div[contains(@class, 'btn-group-create-account')]/a");
	public static final By firstName = By.name("firstName");
	public static final By lastName = By.name("lastName");
	public static final By email = By.name("email");
	public static final By newPassword = By.name("newPassword");
	public static final By verifyPassword = By.name("verifyPassword");
	public static final By dateOfBirth = By.name("dateOfBirth");
	public static final By checkbox = By.xpath("//label[@class='checkbox']");
	public static final By createAccountButton = By.xpath("//div[@class='btn-group']/button");
	public static final By continueButton = By.xpath("//div[@class='btn-group section']/button");
	public static final By username = By.xpath("//div[@class='user user__container user__container--signedin']//span[@class='user-message']");
	public static final By logoutt = By.cssSelector("a.signout");
	public static final By overlay = By.xpath("//a[@class='sprite close']");
	public static final By rowList = By.cssSelector("li[class*='nav-item--center']");
	public static final By horizontalList = By.cssSelector("li[class*='dropdown--horizontal']");
	public static final By horizontalListItem = By.cssSelector("a[class*='dropdown-link dropdown-toggle']");
	public static final By horizontalMenuList = By.cssSelector("a[class='dropdown-link']");
	public static final By list = By.xpath("//div[@class='goc-bound']/ul/span[@class='goc-left']/li/a");
	public static final By menuGroup = By.cssSelector("li[class*='nav-item--center']/");
	public static final By menuGroup_center =By.cssSelector("li[class*='nav-item dropdown nav-item--center']");
	
	public static final String url_Disney = "http://www.Disney.com";
	public static final String frame = "google_ads_iframe_/21783347309/dcom/homepage_0";
	public static final String createAccountframe = "disneyid-iframe";

}
