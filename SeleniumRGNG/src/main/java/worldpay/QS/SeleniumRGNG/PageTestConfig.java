package worldpay.QS.SeleniumRGNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class PageTestConfig {
	static MainPage mainPage= new MainPage();
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
    	MainPage mainPage = new MainPage();
    	mainPage.invoke();
    	mainPage.logout();
    	
		Browser.getInstance().driver.quit();
		LogWriter.writeToFile("------------------------------------------------------------------------------------------------------");
		LogWriter.writeToFile("-------------------------------------RiskProfilePageTest finished.------------------------------------");
		LogWriter.closeFile();
	}
}
