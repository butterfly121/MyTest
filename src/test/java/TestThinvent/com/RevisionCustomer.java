package TestThinvent.com;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.*;
import page.com.Loginpage;
import page.com.RevisiomCsPage;
/**
 * Created by xhm on 2017/6/7.
 */
public class RevisionCustomer {
    private WebDriver driver;
    private String baseUrl;
    private Loginpage loginpage;
    private RevisiomCsPage revisiomCsPage;
    @BeforeClass
    public void SetUp(){
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginpage=new Loginpage(driver);
        revisiomCsPage=new RevisiomCsPage(driver);
        driver.get(baseUrl);
        loginpage.login("zhangjian","111111");
        String name=loginpage.user();
        assertEquals(name,"张健");
    }
    @Test
    public void Updata() throws Exception {
        revisiomCsPage.updataCustomer("最终客户",Loginpage.getElement.FINAL_XPATH,Loginpage.getElement.FINAL_FRAME_XPATH,true,false);

    }
    @Test(dependsOnMethods = {"Updata"})
    public void Updatab()throws Exception{
        driver.switchTo().defaultContent();
        revisiomCsPage.updataCustomer("管理机构",Loginpage.getElement.ORG_XPATH,Loginpage.getElement.ORG_FRAME_XPATH,false,false);
    }
    @Test(dependsOnMethods = {"Updatab"})
    public void Updatac()throws Exception{
        driver.switchTo().defaultContent();
        revisiomCsPage.updataCustomer("咨设机构",Loginpage.getElement.ORG1_XPATH,Loginpage.getElement.ORG1_FRAME_XPATH,false,false);
    }
    @Test(dependsOnMethods = {"Updatac"})
    public void Updatad()throws Exception{
        driver.switchTo().defaultContent();
        revisiomCsPage.updataCustomer("招标代理",Loginpage.getElement.ORG2_XPATH,Loginpage.getElement.ORG2_FRAME_XPATH,false,false);
    }
    @Test(dependsOnMethods = {"Updatad"})
    public void Updatae()throws Exception{
        driver.switchTo().defaultContent();
        revisiomCsPage.updataCustomer("上下友商",Loginpage.getElement.ORG3_XPATH,Loginpage.getElement.ORG3_FRAME_XPATH,false,false);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
