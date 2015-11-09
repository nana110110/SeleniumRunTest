package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;

public class MainPage extends WebPage {

	public By webPageId = By.xpath("//div/ul/li/a[contains(text(), 'Home')]");

	public WebItem home;
	public WebItem riskprofile;
	public WebItem search;
	public WebItem support;
	public WebItem contactUs;
	public WebItem logout;

	@Override
	public void initElements() {
		home = 			new WebItem(By.xpath("//a[@href='/RG_Merchant_Admin_Tool_Test_NG/home']"));
		riskprofile = 	new WebItem(By.xpath("//a[@href='/RG_Merchant_Admin_Tool_Test_NG/risk-profile/profile-specific']"));
		search = 		new WebItem(By.xpath("//a[@href='/RG_Merchant_Admin_Tool_Test_NG/search']"));
		support = 		new WebItem(By.xpath("//li[@class='navSupport']"));
		contactUs = 	new WebItem(By.xpath("//li[@class='navContact']"));
		logout = 		new WebItem(By.xpath("//li[@class='navLogout']"));
	}
	
	public boolean invoke()
	{
		
        if(exists(10, webPageId))
		{
			LogWriter.writeToFile("Get to Main page.");
			initElements();
			return true;
		}
		else 
		{
			LogWriter.writeToFile("Get to Main page failed.");
			return true;
		}
		
	}
	
	public boolean invoke(String merchantid, String username, String password, String url){
		
		LoginPage start = new LoginPage();
		
		start.invoke(url);
		start.merchantId.sendKeys(merchantid);
        start.userName.sendKeys(username);
        start.userPassword.sendKeys(password);
        start.submit.click();      
        
        if(exists(10, webPageId))
		{
        	LogWriter.writeToFile("Log into RG NG FES.");
			initElements();
			return true;
		}
		else 
		{
			LogWriter.writeToFile("Log into RG NG FES failed.");
			return false;
		}
	}
	
	public void logout()
	{
		logout.click();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
