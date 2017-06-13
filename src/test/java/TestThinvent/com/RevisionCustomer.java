package TestThinvent.com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by xhm on 2017/6/7.
 */
public class RevisionCustomer {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @Before
    public void SetUp(){
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        page=new CustomerPage(driver,baseUrl);
        page.login("huxuan","111111");
        String name=page.user();
        assertEquals(name,"胡轩");
    }
    @Test
    public void Updata() throws Exception {
        page.Final_page();
        page.Final_iframe();
        int i=1;
        By EL=page.updataCustomer(i);
        //找到一个可以编辑的客户
        while (!page.isElementPresent(driver,EL)){
            driver.findElement(By.xpath("//*[@id='breadcrumbReturn']")).click();
            i++;
            EL=page.updataCustomer(i);
        }
        if (page.isElementPresent(driver,EL)){
            WebElement el=driver.findElement(By.xpath("html/body/div[2]/div[3]/iframe"));
            driver.switchTo().frame(el);
            Thread.sleep(2000);
            //编辑客户
            driver.findElement(By.id("Name")).clear();
            driver.findElement(By.id("Name")).sendKeys("huxuanc创建1");
            Thread.sleep(3000);
            page.SelectIndustry("政法","公安");
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("新建最终客户测试006");
            Thread.sleep(3000);
            page.SelecteAdress("上海","上海","徐汇");
            driver.findElement(By.id("AddressDetail")).clear();
            driver.findElement(By.id("AddressDetail")).sendKeys("181");
            driver.findElement(By.id("Telephone")).clear();
            driver.findElement(By.id("Telephone")).sendKeys("1589687894581");
            driver.findElement(By.id("CompanyWebsite")).clear();
            driver.findElement(By.id("CompanyWebsite")).sendKeys("www.baidu.com81");
            driver.findElement(By.id("Postcode")).clear();
            driver.findElement(By.id("Postcode")).sendKeys("7894561");
            driver.findElement(By.id("BriefIntroduction")).clear();
            driver.findElement(By.id("BriefIntroduction")).sendKeys("写的是根深蒂固81");
            driver.findElement(By.id("Remark")).clear();
            driver.findElement(By.id("Remark")).sendKeys("dgsdgsdgdfsgdfg81");
            //存在bug
            driver.findElement(By.id("btn_save")).click();
            driver.findElement(By.id("btn_save")).click();
            Thread.sleep(2000);
            //找到上个页面的frame
            page.Final_iframe();
            Thread.sleep(3000);
            String name1=driver.findElement(By.id("lblName")).getText();
            assertEquals(name1,"huxuanc创建1");
            Thread.sleep(3000);

        }

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
