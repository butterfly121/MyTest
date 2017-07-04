package Toos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectDriver {
	public WebDriver driverName(String browser){
		if(browser.equalsIgnoreCase("fireFox")){//设置环境变量，并且返回driver
//			System.setProperty("webdriver.firefox.marionette","D:\\java\\geckodriver\\geckodriver-v0.14.0-win64\\geckodriver.exe");
			return new FirefoxDriver();
		}else{
//			System.setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver_win32\\chromedriver.exe");
			return new ChromeDriver();
		}
	}
}