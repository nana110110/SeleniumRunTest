package worldpay.QS.SeleniumRGNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.RGNGPages.SearchPage;
import worldpay.QS.Selenium.RGNGPages.SupportPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class SupportPageTest {
	static SupportPage supportPage= new SupportPage();

	@Test
	public void veraRun()
	{
		if(supportPage.invoke())
			supportPage.run();
	}
	 

}
