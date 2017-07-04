package iosTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.com.Loginpage;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

/**
 * Created by xhm on 2017/6/20.
 */
public class CreateCustomer {
    private AppiumDriver wd;
    private iosLoginpage loginpg;
    private createCustomerPage page;

    @BeforeTest
    public void setUp() throws Exception{
        loginpg=new iosLoginpage(wd);
//        wd=loginpg.setUp("iphone 6 Plus","10.2.1","iOS",false);
        page=new createCustomerPage(wd);
        wd= loginpg.setUp("iphone 5s","10.2.1","iOS",false);
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        By el=By.xpath(iosLoginpage.getElement.USERNAME_XPATH);
        //判断是否在登录页面，若是在就登录 比较耗时，未找到好的替换方法
//        if (loginpg.isElementPresent(wd,el )){
//            loginpg.login("zhangjian","111111");
//        }
    }
    @Test
    public void Create()throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
        //获取当前时间
        String dataStr=df.format(new Date());

        wd.findElementById("渠道").click();
        wd.findElementById("最终客户").click();
        wd.findElementById("icon set 01@2x").click();

        String finalName=String.format("客户%s",dataStr);
        wd.findElementByXPath(createCustomerPage.getElement.NAME_XPATH).sendKeys(finalName);
        wd.findElementByXPath(createCustomerPage.getElement.LEVEL_XPATH).click();
        wd.findElementById("中客户").click();
        wd.findElementByXPath(createCustomerPage.getElement.INDUSTRY_XPATH).click();
        wd.findElementById("银行").click();
        wd.findElementById("证券").click();
        wd.findElementByName("教育").click();
        //XCUIElementTypeStaticText[@name=\"教育\"]
        wd.findElementByXPath("//XCUIElementTypeTable[2]/XCUIElementTypeCell").click();
        wd.findElementById("确定").click();
        wd.findElementByXPath(createCustomerPage.getElement.FUZERN_XPATH).click();
        wd.findElementByXPath(createCustomerPage.getElement.FUZERNSELECT_XPATH).click();
        wd.findElementByXPath(createCustomerPage.getElement.JIANJIE_XPATH).sendKeys("简介简介");
        wd.findElementById("保存").click();
        wd.findElementById(createCustomerPage.getElement.SEARCH_BTN_ID).click();
        wd.findElementById("搜索").sendKeys(finalName);
        wd.getKeyboard().pressKey(Keys.ENTER);
        String str=wd.findElementByXPath(createCustomerPage.getElement.SEARCH_RESULT).getAttribute("name");
        System.out.println(str);
        assertEquals(str,finalName);

    }


        @AfterTest
    public void tearDown(){
        wd.quit();
    }
}
