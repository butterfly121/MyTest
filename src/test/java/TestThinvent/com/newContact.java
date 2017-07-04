package TestThinvent.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
//import page.com.CustomerPage;
import page.com.Loginpage;
import page.com.NewContactPage;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import java.util.List;
/**
 * Created by xhm on 2017/6/8.
 */
public class newContact {
    private WebDriver driver;
    private String baseUrl;
    private Loginpage loginpage;
    private NewContactPage contactPage;
    @BeforeClass
    public void SetUp(){
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginpage=new Loginpage(driver);
        contactPage=new NewContactPage(driver);
        driver.get(baseUrl);
        loginpage.login("wangml","111111");
    }
    @Test
    public void newContactTest() throws Exception{
        loginpage.Final_page(Loginpage.getElement.FINAL_XPATH,true);
        loginpage.Final_iframe(Loginpage.getElement.FINAL_FRAME_XPATH);
        int i=1;
        By EL=contactPage.newContanct(i);
        //找到一个可以新建联系人的客户
        while (!loginpage.isElementPresent(driver,EL)){
            driver.findElement(By.xpath("//*[@id='breadcrumbReturn']")).click();
            i++;
            EL=contactPage.newContanct(i);
        }
        if (loginpage.isElementPresent(driver,EL)){
            //新建联系人
            WebElement el1=driver.findElement(By.className("zeromodal-frame"));
            driver.switchTo().frame(el1);
            Thread.sleep(2000);
            String name=String.format("联系人%s",loginpage.getCurrentData());
//            int j=1;
            driver.findElement(By.xpath("//*[@id='Name']")).sendKeys(name);
            Thread.sleep(2000);
            new Select(driver.findElement(By.id("PersonDepartment"))).selectByVisibleText("总监室");
            Thread.sleep(2000);
            new Select(driver.findElement(By.id("PersonInCharge"))).selectByVisibleText("汪名亮");
            Thread.sleep(2000);
            driver.findElement(By.id("Department")).sendKeys("设计部");
            driver.findElement(By.id("Duties")).sendKeys("设计总监");
            driver.findElement(By.id("Sex2")).click();
            driver.findElement(By.id("Birthday")).sendKeys("1989-09-06");
            driver.findElement(By.id("Birthday")).click();
            loginpage.SelecteAdress("上海","上海","徐汇");
            Thread.sleep(3000);
            new Select(driver.findElement(By.id("Strategy"))).selectByVisibleText("季度");
            Thread.sleep(2000);
            driver.findElement(By.id("btnSave")).click();
//            判断页面是否有之前新建的联系人
            loginpage.Final_iframe(Loginpage.getElement.FINAL_FRAME_XPATH);
            driver.findElement(By.xpath(NewContactPage.getElement.CONTANCT_LABLE_XPATH)).click();
            Thread.sleep(2000);
            List rows = driver.findElements(By.xpath("//*[@id='UpdatePanel1']/div[2]/table/tbody/tr"));
            System.out.println(rows.size());
            int num=rows.size()-2;
            String personName = driver.findElement(By.xpath(String.format("//*[@id='UpdatePanel1']/div[2]/table/tbody/tr[%d]/td[2]",num) )).getText();
            assertEquals(personName,name);
            Thread.sleep(2000);
        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
