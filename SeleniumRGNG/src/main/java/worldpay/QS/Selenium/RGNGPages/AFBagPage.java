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

	public AFBagPage() {
		super();
	}

	@Override
	public void invoke() {
		AFFrontPage afFrontPage = new AFFrontPage();
		afFrontPage.getToBag();

		/*
		 * if(exists(10, webPageId)) { LogWriter.writeToFile("Get to login page"
		 * ); initElements(); return true; } else { LogWriter.writeToFile(
		 * "Get to login page failed."); return false; }
		 */
	}

	@Override
	public void initElements() {
		tb_prom = new WebItem(By.id("promo-code"));
		btn_apply = new WebItem(By.xpath("//a[@class='action button submit submit']//span[contains(text(),'Apply')]"));
		td_price = new WebItem(By.xpath("//tr[@class='subtotal']/td"));
	}

	public String getOrigPrice() {
		initElements();
		String price = "";

		if (!td_price.isEmpty()) {
			price = td_price.getText().replace("$", "").replace("CAD", "").trim();
		}

		return price;

	}

	public String getNewPrice(String prom) throws InterruptedException {
		initElements();
		String price = "";

		if (!tb_prom.isEmpty() && !td_price.isEmpty() && !btn_apply.isEmpty()) {
			tb_prom.sendKeys(prom);
			btn_apply.movetoclick();
			Thread.sleep(2000);

			initElements();
			price = td_price.getText().replace("$", "").replace("CAD", "").trim();

		}

		return price;

	}

	public void comparePrice(String prom) throws Exception {
		String orgPrice = getOrigPrice();
		String newPrice = getNewPrice(prom);

		if (orgPrice.equals(newPrice))
			LogWriter.writeToFile(prom + ": " + orgPrice + " VS " + newPrice + "\n");
		else
			LogWriter.writeToFile(prom + ": " + orgPrice + " VS " + newPrice + ". Code found!!!!!!!\n");
	}

	@Override
	public void run() {
		int intCode = 0;
		try {
			for (int ii = 1; ii <= 9; ii++)
				for (int jj = 5; jj <= 9; jj++)
					for (int kk = 5; kk <= 9; kk++)
						for (int mm = 5; mm <= 9; mm++)
							for (int nn = 0; nn <= 9; nn++) {
								intCode = nn + mm * 10 + kk * 100 + jj * 1000 + ii * 10000;

								String strCode = String.valueOf(intCode);

								if (strCode.length() != 5) {
									strCode = ("00000" + strCode);
									strCode = strCode.substring(strCode.length() - 5, strCode.length());
								}

								initElements();

								comparePrice(strCode);

								Thread.sleep(1000);

							}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
