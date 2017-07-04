package TestThinvent.com;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
//import page.com.CustomerPage;
import page.com.DeleteCsPage;
import page.com.Loginpage;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;
/**
 * Created by xhm on 2017/6/6.
 */
public class DeleteCustomer {
    private WebDriver driver;
    private String baseUrl;
    private Loginpage loginpage;
    private DeleteCsPage deletePage;
    @BeforeClass
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginpage=new Loginpage(this.driver);
        deletePage=new DeleteCsPage(this.driver);
        driver.get(baseUrl);
        loginpage.login("cuiye","111111");
    }
    @Test
    public  void deleteCustomer() throws Exception {
        //进入渠道-最终客户
        loginpage.Final_page(Loginpage.getElement.FINAL_XPATH,true);
        //找到frame
        loginpage.Final_iframe(Loginpage.getElement.FINAL_FRAME_XPATH);
        new Select(driver.findElement(By.id("ddr_permissionGroup"))).selectByVisibleText("我下属的最终客户");
        Thread.sleep(3000);
        new Select(driver.findElement(By.xpath("//*[@id='ddr_effective']"))).selectByVisibleText("缓存客户");
        Thread.sleep(2000);
        int i=1;
        String[] arry=deletePage.DeleteCustomer(i);
        String alterTitle=arry[0];
        String finalName=arry[1];
        while (!alterTitle.equals("删除成功!")){
            i++;
            arry=deletePage.DeleteCustomer(i);
            alterTitle=arry[0];
            finalName=arry[1];
            System.out.println(finalName);
            System.out.println(alterTitle);
        }

        //判断删除的客户是否还存在
        //我下属的客户删除界面顶部的按钮重叠会找不到该按钮
        driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[3]/div[1]/button[1]")).click();
        WebElement EL= driver.findElement(By.xpath("//*[@id='search_name']"));
        EL.sendKeys(finalName);
        EL.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        //判断删除的客户是否还存在
        By el=By.xpath("//*[@id='tablebody']/tr[1]/td[2]/span");
        if(loginpage.isElementPresent(driver,el)){
            String finalName1=driver.findElement(By.xpath("//*[@id='tablebody']/tr[1]/td[2]/span")).getText();
            assertNotEquals(finalName1,finalName);
        }
        else {
            assertFalse(loginpage.isElementPresent(driver,el));
        }
        Thread.sleep(2000);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
