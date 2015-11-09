package worldpay.QS.SeleniumRGNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.ContactusPage;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.RGNGPages.SupportPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class ContactusPageTest {
	static ContactusPage contactusPage= new ContactusPage();
	
	@Test
	public void veraRun()
	{
		if(contactusPage.invoke())
			contactusPage.run();
	}
}
