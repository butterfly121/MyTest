package ManagementOrganization.com;
import Toos.ParseExcel;
import Toos.driverBase;
import jxl.JXLException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.com.CreateCsPage;
import page.com.Loginpage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
/**
 * Created by xhm on 2017/6/16.
 */
public class CreateOrganization extends driverBase {
//    private WebDriver driver;
    private String baseUrl;
    private Loginpage page;
    private CreateCsPage createPage;
    @BeforeClass
    public void setUp() throws Exception {
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        page=new Loginpage(driver);
        createPage=new CreateCsPage(driver);
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        page.login("wangml","111111");
        createPage.findFrame(Loginpage.getElement.ORG_XPATH,Loginpage.getElement.ORG_FRAME_XPATH,true);
    }
    @DataProvider(name = "final1")
    public Object[][] Createfinal() throws IOException, JXLException {
        ParseExcel peLogin = new ParseExcel("/Users/xhm/Desktop/2017周报/createcustomer.xls","新建管理机构");
        Object[][] paramLogin = peLogin.getParameter();
        return paramLogin;
    }
    @Test(dataProvider = "final1")
    public void AverifyLogin(String finalName,String level,String industry,String industry1,String bumen,String personInchage,String janjie,String assertStr )throws Exception {
        Thread.sleep(3000);
        createPage.verifyCreateFinal(finalName, level, industry, industry1, bumen, personInchage, janjie);
        try {
            assertEquals(driver.findElement(By.xpath("//*[@id='alertdiv']")).getText(), assertStr);
        } finally {
        }
    }
    @Test
    public void CreateOrgani() throws Exception{

        createPage.CreateCustom1("机构","缓存机构",Loginpage.getElement.ORG_FRAME_XPATH,"新建管理机构");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
