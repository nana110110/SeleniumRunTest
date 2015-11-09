package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;

public class ContactusPage extends WebPage {

	public By webPageId = By.xpath("//h2[contains(text(), 'Contact Us')]");
	
	@Override
	public boolean invoke() {
		MainPage mainpage = new MainPage();
		
		if(mainpage.invoke())
		{
			mainpage.initElements();
			mainpage.contactUs.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("ContactUs page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("ContactUs page start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean invoke(String merchantid, String username, String password, String url){
		
		MainPage mainpage = new MainPage();

		if(mainpage.invoke(merchantid,username, password, url))
		{
			mainpage.initElements();
			mainpage.contactUs.click();
			
			if(exists(10, webPageId))
			{
				LogWriter.writeToFile("ContactUs page started.");
				initElements();
				return true;
			}
			else 
			{
				LogWriter.writeToFile("ContactUs page start failed.");
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public void initElements() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
