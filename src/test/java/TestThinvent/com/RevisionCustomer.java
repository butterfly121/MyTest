package TestThinvent.com;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.com.CustomerPage;
/**
 * Created by xhm on 2017/6/7.
 */
public class RevisionCustomer {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    @BeforeTest
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
        page.Final_page(CustomerPage.getElement.FINAL_XPATH,true);
        page.Final_iframe(CustomerPage.getElement.FINAL_FRAME_XPATH);
        int i=1;
        int k=1;
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
            String Name=driver.findElement(By.id("Name")).getAttribute("value");
            updata("Name",Name+k);
            Thread.sleep(3000);
            page.SelectIndustry("政法","公安");
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("新建最终客户测试006");
            Thread.sleep(3000);
            page.SelecteAdress("上海","上海","徐汇");
            String[] idnameArr={"AddressDetail","Telephone","CompanyWebsite","Postcode","BriefIntroduction","Remark"};
            String[] sendKeysArr={"181","1589687894581","www.baidu.com81","7894561","写的是根深蒂固81","dgsdgsdgdfsgdfg81"};
            for (int j=0;j<idnameArr.length;j++){
                updata(idnameArr[j],sendKeysArr[j]);
            }
            //存在bug
            driver.findElement(By.id("btn_save")).click();
            By el1 = By.xpath("//*[@id='alertdiv']");
            while (page.isElementPresent(driver, el1)) {
                k++;
                driver.findElement(By.xpath("//*[@id='alertdiv']/i[2]")).click();
                driver.findElement(By.id("Name")).clear();
                updata("Name",Name+k);
                Thread.sleep(2000);
            }
            //找到上个页面的frame
            page.Final_iframe(CustomerPage.getElement.FINAL_FRAME_XPATH);
            Thread.sleep(3000);
            String name1=driver.findElement(By.id("lblName")).getText();
            assertEquals(name1,Name+1);
            Thread.sleep(3000);
        }
    }
    public void updata(String idname,String sendkeys){
        driver.findElement(By.id(idname)).clear();
        driver.findElement(By.id(idname)).sendKeys(sendkeys);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
