package worldpay.QS.SeleniumRGNG;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.RGNGPages.MainPage;
import worldpay.QS.Selenium.RGNGPages.RiskProfilePage;
import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class RiskProfilePageTest {

	static RiskProfilePage riskProfilePage = new RiskProfilePage();
	
	@Test()
	@Parameters({"profileIndex","expectProfileName"})
	public void profileTest(int profileIndex, String expectProfileName)
	{	
    	if(riskProfilePage.invoke())
    	{		
    		String profilenameString = riskProfilePage.getProfile(profileIndex);
    		
    		AssertJUnit.assertEquals(profilenameString, expectProfileName);
    		
    	}
	}
	
	@Test()
	@Parameters({"profileIndex", "sectionIndex","expectSectionName"})
	public void sectionTest(int profileIndex, int sectionIndex, String expectSectionName)
	{
    	if(riskProfilePage.invoke())
    	{		
    		String profilenameString = riskProfilePage.getSection(sectionIndex);
    		
    		AssertJUnit.assertFalse(profilenameString.equals(""));
    		
    		if(!profilenameString.equals(""))
    		{
    			String SectionNameString = riskProfilePage.getSection(sectionIndex);
    			
    			AssertJUnit.assertEquals(SectionNameString, expectSectionName);
    		}
    	}  	
	}
	
	@Test()
	@Parameters({"profileName"})
	public void profileExist(String profileName)
	{
    	if(riskProfilePage.invoke())
    	{		
    		String resultName = riskProfilePage.getProfile(profileName);
    		
    		AssertJUnit.assertFalse(resultName.equals(""));
    		
    	}
	}
	
	@Test()
	@Parameters({"profileName","sectionName"})
	public void sectionExist(String profileName, String sectionName)
	{
    	if(riskProfilePage.invoke())
    	{		
    		
    		String resultProfileName = riskProfilePage.getProfile(profileName);
    		
    		AssertJUnit.assertFalse(resultProfileName.equals(""));
    		
    		if(!resultProfileName.equals(""))
    		{
    			String resultSectionName = riskProfilePage.getSection(sectionName);
    			
    			AssertJUnit.assertFalse(resultSectionName.equals(""));
    		}
    		
    		
    	}  	
	}
	
	 @Test
	 @Parameters({"customerRuleName"})
	 public void editCustomerRule(String customerRuleName)
	 {
		RiskProfilePage riskProfilePage = new RiskProfilePage();
		
		if(riskProfilePage.invoke())
		{
			riskProfilePage.editCustomerRule(customerRuleName);
		}
	 }
	 
	 @Test
	 public void veraRun()
	 {
 		if(riskProfilePage.invoke())
			riskProfilePage.run();
	 }
	
}
