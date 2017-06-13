package TestThinvent.com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

/**
 * Created by xhm on 2017/6/6.
 */
public class HandoverCustomer {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @Before
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        page=new CustomerPage(this.driver,baseUrl);
        page.login("huxuan","111111");
        Thread.sleep(3000);
        String title=page.user();
        assertEquals(title,"胡轩");
    }
    @Test
    public void handoverCustomer() throws Exception {
        //进入渠道-最终客户
        page.Final_page();
        page.Final_iframe();
        int i=2;
       By el= page.HandoverSelect(i);
        //判断界面是否有提示信息
        while (page.isElementPresent(driver,el)){
            //取消选中
            page.HandoverUnSelect(i);
            i++;
            //选中移交
            el= page.HandoverSelect(i);
        }
        //判断页面是否有新的元素
        By el1=page.HandovergetElement();
        if (page.isElementPresent(driver,el1)){
            //移交
            page.HandOver();
        }

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
