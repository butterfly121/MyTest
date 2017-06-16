package TestThinvent.com;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.com.CustomerPage;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
/**
 * Created by xhm on 2017/6/2.
 */
public class testLogin {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @BeforeTest
    public void SetUp(){
        this.driver=new ChromeDriver();
        this.baseUrl="http://192.168.64.222:8088/login.aspx";
        this.page=new CustomerPage(this.driver,baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void loginTest() throws InterruptedException{
        page.login("zhangjian","111111");
        Thread.sleep(2000);
        String username=page.user();
        assertEquals(username,"张健");
        page.loginOut();

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
