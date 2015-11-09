package auto_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Browser {

	private static Browser browser;
	
	public static WebDriver driver;

	Browser(String browserType) {
		
		switch (browserType) {
		case "Firefox":
			driver = new FirefoxDriver();
			break;
		case "IntenetExplorer":
			System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Internet Explorer\\iexplore.exe");
			driver = new InternetExplorerDriver();
			break;
		case "Opera":
			driver = new OperaDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			driver = new ChromeDriver();
			break;
		}
	}

	public static Browser getInstance() {
		
		if (driver == null) {
			browser =  new Browser("Firefox");
		}
		
		return browser;
	}

}
