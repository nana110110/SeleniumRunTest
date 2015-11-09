package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;

public class LoginPage extends WebPage {

	public By webPageId = By.xpath("//h2/div[contains(text(), 'RiskGuardian')]");
	
	public static String invokeURL = "https://mtlqavweb09/RG_Merchant_Admin_Tool_Test_NG/";
	
	public WebItem merchantId;
	public WebItem userName;
	public WebItem userPassword;
	public WebItem submit;
	
	@Override
	public void initElements() {
		merchantId = 	new WebItem(By.xpath(".//*[@id='content']/div[1]/table/tbody/tr[2]/td/input[2]"));
		userName = 		new WebItem(By.xpath(".//*[@id='content']/div[1]/table/tbody/tr[3]/td/input[2]"));
		userPassword = 	new WebItem(By.xpath(".//*[@id='content']/div[1]/table/tbody/tr[4]/td/input[2]"));
		submit = 		new WebItem(By.id("ctl00_ContentPlaceHolder1_btnAuthenticate"));
	}



	public boolean invoke(){
		
		WebDriver driver = Browser.getInstance().driver;
		driver.get(invokeURL);
		
		if(exists(10, webPageId))
		{
			LogWriter.writeToFile("Get to login page");
			initElements();
			return true;
		}
		else {
			LogWriter.writeToFile("Get to login page failed.");
			return false;
		}
			
		
	}
	
	public boolean invoke(String url){
		
		WebDriver driver = Browser.getInstance().driver;
		driver.get(url);
		
		if(exists(5, webPageId))
		{
			LogWriter.writeToFile("Get to login page");
			initElements();
			return true;
		}
		else 
		{
			LogWriter.writeToFile("Get to login page failed.");
			return false;
		}		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
}
