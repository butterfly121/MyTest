package TestThinvent.com;
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
 * Created by xhm on 2017/6/2.
 */
public class CreateCustomer {
    private WebDriver driver;
    private String baseUrl;
    private String name;
    private String AlterTitle;
    private CustomerPage page;
    @BeforeTest
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.page=new CustomerPage(this.driver,baseUrl);
        page.login("wangml","111111");
        Thread.sleep(2000);
        String username=page.user();
        assertEquals(username,"汪名亮");
    }
    //新建客户
    @Test
    public void CreatCustomerTest()throws Exception{
        page.CreateCustom1("客户0616","缓存客户","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.FINAL_XPATH,CustomerPage.getElement.FINAL_FRAME_XPATH,true);
    }
//    //管理机构
    @Test
    public void createD()throws Exception{
        driver.switchTo().defaultContent();
        page.CreateCustom1("机构0616","缓存机构","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.ORG_XPATH,CustomerPage.getElement.ORG_FRAME_XPATH,false);
    }
    //咨设机构
    @Test
    public void CreateE()throws Exception{
        driver.switchTo().defaultContent();
        page.CreateCustom1("咨设机构0616","缓存机构","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.ORG1_XPATH,CustomerPage.getElement.ORG1_FRAME_XPATH,false);
    }
    @Test
    public void CreateF()throws Exception{
        driver.switchTo().defaultContent();
        page.CreateCustom1("代理616","缓存代理","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.ORG2_XPATH,CustomerPage.getElement.ORG2_FRAME_XPATH,false);
    }
    @Test
    public void CreateG()throws Exception{
        driver.switchTo().defaultContent();
        page.CreateCustom1("友商616","缓存上下友商","html/body/div[4]/div[3]/iframe",CustomerPage.getElement.ORG3_XPATH,CustomerPage.getElement.ORG3_FRAME_XPATH,false);
    }
    @AfterTest
    public void tearDown(){

        driver.quit();
    }
}
