package TestThinvent.com;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import javax.security.auth.spi.LoginModule;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhm on 2017/6/2.
 */
public class testLogin {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @BeforeTest
    public void SetUp(){
        this.driver=new ChromeDriver();
        this.baseUrl="http://192.168.64.222:8088/login.aspx";
        this.page=new CustomerPage(this.driver,baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void loginTest() throws InterruptedException{
        page.login("zhangjian","111111");
        Thread.sleep(2000);
        String username=page.user();
        assertEquals(username,"张健");
        page.loginOut();
//        Thread.sleep(2000);

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

//    public static void main(String[] args) throws InterruptedException,IOException{
//        WebDriver driver=new ChromeDriver();
//        driver.get("http://192.168.64.222:8088/login.aspx");
//        String username="zhangjian";
//        String password="111111";
//        loginModule.login(driver, username, password);
//        Thread.sleep(5000);
//        System.out.println("login success");
//        loginModule.loginOut(driver);
//        System.out.println("loginOut success");
//        Thread.sleep(5000);
//        driver.quit();
//
//    }
}
