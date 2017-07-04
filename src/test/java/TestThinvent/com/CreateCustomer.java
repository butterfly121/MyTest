package TestThinvent.com;
import Toos.ParseExcel;
import Toos.TestNGListenerScreen;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
/**
 * Created by xhm on 2017/6/2.
 */
@Listeners({TestNGListenerScreen.class})
public class CreateCustomer extends driverBase {
    private String baseUrl;
    private String name;
    private String AlterTitle;
    private Loginpage loginpage;
    private CreateCsPage createPage;
    private int jietuCount;
    @BeforeClass
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.loginpage=new Loginpage(this.driver);
        this.createPage=new CreateCsPage(driver);
        driver.get(baseUrl);
        loginpage.login("wangml","111111");
        Thread.sleep(2000);
        String username=loginpage.user();
        assertEquals(username,"汪名亮");
        createPage.findFrame(Loginpage.getElement.FINAL_XPATH,Loginpage.getElement.FINAL_FRAME_XPATH,true);
    }
@DataProvider(name = "final1")
public Object[][] Createfinal() throws IOException, JXLException {
    ParseExcel peLogin = new ParseExcel("/Users/xhm/Desktop/2017周报/createcustomer.xls","新建客户");
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
//    新建客户
    @Test
    public void CreateC()throws Exception{
        createPage.CreateCustom1("客户","缓存客户",Loginpage.getElement.FINAL_FRAME_XPATH,"新建客户");
    }

    @AfterClass
    public void tearDown(){

        driver.quit();
    }
}
