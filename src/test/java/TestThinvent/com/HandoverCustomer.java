package TestThinvent.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.com.HandOverPage;
import page.com.Loginpage;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

/**
 * Created by xhm on 2017/6/6.
 */
public class HandoverCustomer {
    private WebDriver driver;
    private String baseUrl;
//    private CustomerPage page;
    private Loginpage loginpage;
    private HandOverPage handPage;
    @BeforeClass
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginpage=new Loginpage(this.driver);
        handPage=new HandOverPage(this.driver);
        driver.get(baseUrl);
        loginpage.login("zhangjian","111111");
        Thread.sleep(3000);
        String title=loginpage.user();
        assertEquals(title,"张健");
    }
    @Test
    public void handoverCustomer() throws Exception {
        //进入渠道-最终客户
        loginpage.Final_page(Loginpage.getElement.FINAL_XPATH,true);
        loginpage.Final_iframe(Loginpage.getElement.FINAL_FRAME_XPATH);
        int i=2;
       By el= handPage.HandoverSelect(i);
        //判断界面是否有提示信息
        while (loginpage.isElementPresent(driver,el)){
            //取消选中
            handPage.HandoverUnSelect(i);
            i++;
            //选中移交
            el= handPage.HandoverSelect(i);
        }
        //判断页面是否有新的元素
        By el1=handPage.HandovergetElement();
        if (loginpage.isElementPresent(driver,el1)){
            //移交
            handPage.HandOver();
        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
