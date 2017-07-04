package iosTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
/**
 * Created by xhm on 2017/6/19.
 */
public class iosLoginpage {
    private AppiumDriver driver;
    public static class getElement{
        public static final String USERNAME_XPATH="//XCUIElementTypeImage[@name=\"Login_bg\"]/XCUIElementTypeTextField";
        public static final String PASSWORD_XPATH="//XCUIElementTypeImage[@name=\"Login_bg\"]/XCUIElementTypeSecureTextField";
        public static final String CLICLK_ID="NewLogo";
        public static final String LOGIN_ID="登录";
    }
    public  iosLoginpage(AppiumDriver driver){
        this.driver=driver;
    }
    public AppiumDriver setUp(String deviceName,String platformVersion,String platformName , boolean isPlaform)throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("bundleId","com.strongit.CRM");
        capabilities.setCapability("noReset",true);
        capabilities.setCapability("app","/Users/xhm/Downloads/CRMForThinvent(4).ipa");
        if (!isPlaform){
//            b821ebe00f45811cbe3c84c977a1b787ece9af58//5
//            3b4d4bb9af8b641862d1366f45bac6d7d0a4f7ba//6p
            capabilities.setCapability("udid","3b4d4bb9af8b641862d1366f45bac6d7d0a4f7ba");
        }

//        if(driver.isAppInstalled("com.strongit.CRM"))
//            System.out.println("App already present");
//        else
//            capabilities.setCapability("app","/Users/xhm/Downloads/CRMForThinvent(4).ipa");


        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        return driver;
    }

    public void login(String username,String password)throws Exception{
        driver.findElementByXPath(getElement.USERNAME_XPATH).clear();
        driver.findElementByXPath(getElement.USERNAME_XPATH).sendKeys(username);
        driver.findElementById(getElement.CLICLK_ID).click();
        driver.findElementByXPath(getElement.PASSWORD_XPATH).clear();
        driver.findElementByXPath(getElement.PASSWORD_XPATH).sendKeys(password);
        driver.findElementById(getElement.CLICLK_ID).click();
        driver.findElementById(getElement.LOGIN_ID).click();
        Thread.sleep(5000);
    }
    public void loginOut()throws Exception{
        driver.findElementById("设置").click();
        driver.findElementById("退出登录").click();
        Thread.sleep(5000);
    }
    public String getUser(){
        driver.findElementById("我").click();
//        driver.findElementByXPath("//XCUIElementTypeButton[@name=\\\"我的\\\"]").click();
        String str= driver.findElementById("张健").getAttribute("name");
        System.out.println(str);
        return str;
    }
    //判断页面该元素是否存在，NoSuchElementException不管用
    public   boolean isElementPresent(WebDriver driver, By el) {
        try {
            System.out.println("存在");
            driver.findElement(el);
            return true;
        }
        catch (Exception e) {
            System.out.println("不存在");
            return false;
        }
    }
}
