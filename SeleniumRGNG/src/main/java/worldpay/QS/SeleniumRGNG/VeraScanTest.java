package worldpay.QS.SeleniumRGNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Parameters;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.ContactusPage;
import worldpay.QS.Selenium.RGNGPages.HomePage;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.RGNGPages.RiskProfilePage;
import worldpay.QS.Selenium.RGNGPages.SearchPage;
import worldpay.QS.Selenium.RGNGPages.SupportPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

import com.thoughtworks.selenium.webdriven.commands.Close;


public class VeraScanTest
{
	static MainPage mainPage = new MainPage();
	static HomePage homePage = new HomePage();
	static RiskProfilePage riskProfilePage = new RiskProfilePage();
	static SupportPage supportPage = new SupportPage();
	static ContactusPage contactusPage = new ContactusPage();
	static SearchPage searchPage = new SearchPage();
	
	@Test()
    public void run()
    {	
    	
    	if(homePage.invoke())
    	{
    		homePage.run();
    	
    		if(searchPage.invoke())
    			searchPage.run();
    		
    		if(riskProfilePage.invoke())
    			riskProfilePage.run();
    			
        	if(supportPage.invoke())
        		supportPage.run();
    			
            if(contactusPage.invoke())
            	contactusPage.run();
    	}
    }
    
	@BeforeSuite(alwaysRun = true)
	public static void init()
	{
		LogWriter.openLogToWrite(Config.CONFIG.getLogFileName(), Config.CONFIG.getLogFilePath());
		LogWriter.writeToFile("-----------------------------------------------------------------------------------------------------");
		
		LogWriter.writeToFile("-------------------------------------RiskProfilePageTest started.------------------------------------");
		
		mainPage.invoke(Config.CONFIG.getFESMerchantId(),Config.CONFIG.getFESUserName(), Config.CONFIG.getFESPassWord(), Config.CONFIG.getFESURL());
	}
	
    @AfterSuite(alwaysRun = true)
	public static void close()
	{
    	
    	mainPage.invoke();
    	mainPage.logout();
    	
		Browser.getInstance().driver.quit();
		LogWriter.writeToFile("------------------------------------------------------------------------------------------------------");
		LogWriter.writeToFile("-------------------------------------RiskProfilePageTest finished.------------------------------------");
		LogWriter.closeFile();
	}
}
