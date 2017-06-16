package ManagementOrganization.com;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.com.CustomerPage;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
/**
 * Created by xhm on 2017/6/16.
 */
public class CreateOrganization {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    private String name;
    private String AlterTitle;
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        page=new CustomerPage(driver,baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        page.login("wangml","111111");
//        String userName=page.user();
//        assertEquals(userName,"张健");
    }
    @Test
    public void CreateOrgani() throws Exception{

        page.CreateCustom1("机构0617","缓存机构","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.ORG_XPATH,CustomerPage.getElement.ORG_FRAME_XPATH,true);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
