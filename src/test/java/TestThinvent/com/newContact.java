package TestThinvent.com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import java.util.List;

/**
 * Created by xhm on 2017/6/8.
 */
public class newContact {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @Before
    public void SetUp(){
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        page=new CustomerPage(driver,baseUrl);
        page.login("zhangjian","111111");
        String name=page.user();
        assertEquals(name,"张健");
    }
    @Test
    public void newContactTest() throws Exception{
        page.Final_page();
        page.Final_iframe();
        int i=1;
        By EL=page.newContanct(i);
        //找到一个可以新建联系人的客户
        while (!page.isElementPresent(driver,EL)){
            driver.findElement(By.xpath("//*[@id='breadcrumbReturn']")).click();
            i++;
            EL=page.newContanct(i);
        }
        if (page.isElementPresent(driver,EL)){
            //新建联系人
            WebElement el1=driver.findElement(By.className("zeromodal-frame"));
            driver.switchTo().frame(el1);
            Thread.sleep(2000);
            String name="新建联系人";
            int j=1;
            driver.findElement(By.xpath("//*[@id='Name']")).sendKeys("新建联系人");
            Thread.sleep(2000);

            new Select(driver.findElement(By.id("PersonDepartment"))).selectByVisibleText("教育行业部");
            Thread.sleep(2000);
            new Select(driver.findElement(By.id("PersonInCharge"))).selectByVisibleText("张健");
            Thread.sleep(2000);
            driver.findElement(By.id("Department")).sendKeys("设计部");
            driver.findElement(By.id("Duties")).sendKeys("设计总监");
            driver.findElement(By.id("Sex2")).click();
            driver.findElement(By.id("Birthday")).sendKeys("1989-09-06");
            driver.findElement(By.id("Birthday")).click();
            page.SelecteAdress("上海","上海","徐汇");
            Thread.sleep(3000);
            new Select(driver.findElement(By.id("Strategy"))).selectByVisibleText("季度");
            Thread.sleep(2000);
            driver.findElement(By.id("btnSave")).click();

            By el=By.xpath("//*[@id='alertdiv']");

            while (page.isElementPresent(driver,el)){
                driver.findElement(By.xpath("//i[@onclick='alertClose();']")).click();
                j++;
                driver.findElement(By.xpath("//*[@id='Name']")).clear();
                driver.findElement(By.xpath("//*[@id='Name']")).sendKeys("新建联系人"+j);
                driver.findElement(By.id("btnSave")).click();
                el=By.xpath("//*[@id='alertdiv']");
                name="新建联系人"+j;
            }
//            判断页面是否有之前新建的联系人
            page.Final_iframe();
            driver.findElement(By.xpath(CustomerPage.getElement.CONTANCT_LABLE_XPATH)).click();
            Thread.sleep(2000);
            List rows = driver.findElements(By.xpath("//*[@id='UpdatePanel1']/div[2]/table/tbody/tr"));
            System.out.println(rows.size());
            int num=rows.size()-2;
            String personName = driver.findElement(By.xpath(String.format("//*[@id='UpdatePanel1']/div[2]/table/tbody/tr[%d]/td[2]",num) )).getText();
            assertEquals(personName,name);
            Thread.sleep(2000);
            //发斯蒂芬斯蒂芬
        }
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
