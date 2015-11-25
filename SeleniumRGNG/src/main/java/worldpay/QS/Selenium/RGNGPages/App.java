package worldpay.QS.Selenium.RGNGPages;

import worldpay.QS.Selenium.util.Config;
import worldpay.QS.Selenium.util.LogWriter;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogWriter.openLogToWrite(Config.CONFIG.getLogFileName(), Config.CONFIG.getLogFilePath());
		
		AFBagPage afbagpage = new AFBagPage();
		afbagpage.run();
	}

}
