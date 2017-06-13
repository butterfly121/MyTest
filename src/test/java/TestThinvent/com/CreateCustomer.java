package TestThinvent.com;
import org.apache.bcel.generic.DREM;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

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
    @Before
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
    @Test
    public void testCreatCustomer()throws Exception{
        //进入渠道-最终客户
        page.Final_page();
        name="客户606";
        int i=1;
        //添加多个用户
        for (int j=1;j<=5;j++){
            //找到frame
            page.Final_iframe();
            //点击新建按钮
            driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[3]/div[1]/button[2]")).click();
            //新建客户
            WebElement rc1=driver.findElement(By.xpath("html/body/div[4]/div[3]/iframe"));
            driver.switchTo().frame(rc1);
            page.CreateCustom(name);
            Thread.sleep(2000);
            By el = By.xpath("//*[@id='alertdiv']");
            while (page.isElementPresent(driver,el)) {
                i++;
                driver.findElement(By.xpath("//*[@id='alertdiv']/i[2]")).click();
                driver.findElement(By.id("Name")).clear();
                driver.findElement(By.id("Name")).sendKeys(name+i);
                driver.findElement(By.id("btn_save")).click();
                Thread.sleep(2000);
            }
        }

    }

    @After
    public void tearDown(){

        driver.quit();
    }
}
