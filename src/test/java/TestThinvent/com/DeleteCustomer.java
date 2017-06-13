package TestThinvent.com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by xhm on 2017/6/6.
 */
public class DeleteCustomer {
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
        Thread.sleep(2000);
        String username=page.user();
        assertEquals(username,"胡轩");
    }
    @Test
    public  void deleteCustomer() throws Exception {
        //进入渠道-最终客户
        page.Final_page();
        //找到frame
        page.Final_iframe();
        new Select(driver.findElement(By.id("ddr_permissionGroup"))).selectByVisibleText("我下属的最终客户");
        Thread.sleep(3000);
        int i=2;
        String alterTitle=page.DeleteCustomer(i);
        while (!alterTitle.equals("删除成功!")){
            i++;
            alterTitle=page.DeleteCustomer(i);

        }
    }
    @After
    public void tearDown(){
        driver.quit();
    }


}
