package worldpay.QS.Selenium.RGNGPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import worldpay.QS.Selenium.Base.Browser;
import worldpay.QS.Selenium.Base.WebItem;
import worldpay.QS.Selenium.Base.WebPage;
import worldpay.QS.Selenium.util.LogWriter;
import worldpay.QS.Selenium.util.Utility;

public class AFFrontPage extends WebPage {

	public By webPageId = By.xpath("//a[@class='logo white']");

	public static String invokeURL = "http://www.abercrombie.ca/shop/ca";

	public WebItem signin;
	public WebItem bags;

	public AFFrontPage() {
		super();
	}

	@Override
	public void initElements() {

		signin = new WebItem(By.xpath("//li/a[@class='util-main' and contains(text(), 'Sign In')]"));
		bags = new WebItem(By.xpath("//li/a[@class='util-main']/span[contains(text(), 'BAG')]"));

	}

	public void invoke() {

		WebDriver driver = Browser.getInstance().driver;
		driver.get(invokeURL);

		WebItem btn_close = new WebItem(By.xpath("//a[@class='modalCloseImg simplemodal-close' and @title='Close']"));

		if (!btn_close.isEmpty())
			btn_close.movetoclick();

		/*if (exists(10, webPageId)) {
			LogWriter.writeToFile("Get to login page");
			initElements();
			return true;
		} else {
			LogWriter.writeToFile("Get to login page failed.");
			return false;
		}
*/
	}

	public void invoke(String url) {

		WebDriver driver = Browser.getInstance().driver;
		driver.get(url);

		WebItem btn_close = new WebItem(By.xpath("//a[@class='modalCloseImg simplemodal-close' and @title='Close']"));

		if (!btn_close.isEmpty())
			btn_close.movetoclick();

/*		if (exists(5, webPageId)) {
			LogWriter.writeToFile("Get to login page");
			initElements();
			return true;
		} else {
			LogWriter.writeToFile("Get to login page failed.");
			return false;
		}*/
	}

	public void signIn() throws InterruptedException {
		initElements();
		if (!this.signin.isEmpty()) {
			this.signin.movetoclick();

			Thread.sleep(1000);

			WebItem tb_username = new WebItem(By.id("login-email-field"));
			WebItem tb_password = new WebItem(By.id("login-password-field"));
			WebItem btn_sub = new WebItem(By.xpath(
					"//div[@class='actions']//a[@class='action button submit']//span[contains(text(),'Sign In')]"));

			if (!tb_username.isEmpty() && !tb_password.isEmpty() && !btn_sub.isEmpty()) {
				tb_username.sendKeys(Utility.username);
				tb_password.sendKeys(Utility.password);
				btn_sub.movetoclick();
			}

			Thread.sleep(2000);
		}
	}

	public void getToBag() {
		try {
			signIn();

			initElements();
			if (!this.bags.isEmpty()) {
				this.bags.movetoclick();

				Thread.sleep(1000);
			}

		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

	}
}
