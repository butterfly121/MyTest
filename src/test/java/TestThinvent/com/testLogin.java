package TestThinvent.com;
import Toos.TestNGListenerScreen;
import Toos.driverBase;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.com.Loginpage;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
/**
 * Created by xhm on 2017/6/2.
 */
@Listeners({TestNGListenerScreen.class})
public class testLogin extends driverBase {
//    private WebDriver driver;
    private String baseUrl;
    private Loginpage loginpage;
    @BeforeClass
    public void SetUp(){
        this.driver=new ChromeDriver();
        this.baseUrl="http://192.168.64.222:8088/login.aspx";
        this.loginpage=new Loginpage(this.driver);
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void login()throws InterruptedException{
        loginpage.login("zhangjian","111111");
        String userName=loginpage.user();
        assertEquals(userName,"张健");
        Thread.sleep(3000);
        loginpage.loginOut();
    }
    @DataProvider(name = "final")
    public Object[][] Users(){
        return new Object[][]{
                {"","","用户名不能为空!1"},
                {"test","","密码不能为空！"},
                {"error","error","登录失败！"}
        };
    }
    @Test(dataProvider = "final")
    public void verifyLogin(String username,String password,String assertStr)throws Exception {
        loginpage.login(username, password);
        try {
            assertEquals(driver.switchTo().alert().getText(), assertStr);
        }
       finally {
            driver.switchTo().alert().accept();
        }

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
