package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;

import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;

public class AFBagPage extends WebPage {

	public By webPageId = By.xpath("//h2[contains(text(),'Shopping Bag')]");
	
	public WebItem tb_prom;
	public WebItem btn_apply;
	public WebItem td_price;
	
	public AFBagPage()
	{
		super();
	}
	
	@Override
	public boolean invoke() {
		AFFrontPage afFrontPage = new AFFrontPage();
		afFrontPage.getToBag();
		
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

	@Override
	public void initElements() {
		tb_prom = new WebItem(By.id("promo-code"));
		btn_apply = new WebItem(By.xpath("//a[@class='action button submit submit']//span[contains(text(),'Apply')]"));
		td_price = new WebItem(By.xpath("//tr[@class='subtotal']/td"));
	}
	
	public String getOrigPrice()
	{
		initElements();
		String price="";
		
		if(!td_price.isEmpty())
		{
			price=td_price.getText().replace("$", "").replace("CAD", "").trim();
		}
		
		return price;

	}
	
	public String getNewPrice(String prom) throws InterruptedException
	{
		initElements();
		String price="";
		
		if(!tb_prom.isEmpty()&&!td_price.isEmpty()&&!btn_apply.isEmpty())
		{
			tb_prom.sendKeys(prom);
			btn_apply.movetoclick();
			Thread.sleep(2000);
			
			price=td_price.getText().replace("$", "").replace("CAD", "").trim();
		}
		
		return price;

	}
	
	public void comparePrice(String prom) throws Exception
	{
		LogWriter.writeToFile(prom+": "+getOrigPrice() +" VS "+getNewPrice(prom));
	}
	
	@Override
	public void run() {
		int intCode = 0;
		for(int ii=0; ii<=9; ii++)
			for(int jj=0;jj<=9;jj++)
				for(int kk=0;kk<=9;kk++)
					for(int mm=0;mm<=9;mm++)
						for(int nn=0;nn<=9;nn++)
						{
							intCode = nn+mm*10+kk*100+jj*1000+ii*10000;
							
							String strCode=String.valueOf(intCode);
							
							if(strCode.length()==4)
								strCode="0"+strCode;
						}
	}

}
