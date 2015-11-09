package worldpay.QS.SeleniumRGNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.HomePage;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.RGNGPages.SearchPage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class SearchPageTest {
	static SearchPage searchPage= new SearchPage();

	@Test
	public void veraRun()
	{
		if(searchPage.invoke())
			searchPage.run();
	}
}
