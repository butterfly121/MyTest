package TestThinvent.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by xhm on 2017/6/2.
 */
public class loginModule {
    public static void login(WebDriver driver,String userName,String password)throws InterruptedException{
        driver.findElement(By.id("txt_usercode")).clear();
        driver.findElement(By.id("txt_usercode")).sendKeys(userName);
        driver.findElement(By.id("txt_pwd")).clear();
        driver.findElement(By.id("txt_pwd")).sendKeys(password);
        driver.findElement(By.id("btn_login")).click();
    }
    public static void loginOut(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("//*[@id='form1']/div[2]/div[1]/div[2]/nav/ul/li[3]/a/i")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='myModal']/div/div/div[3]/button[2]")).click();
    }
    public static void CreateCustom(WebDriver driver,String name) throws InterruptedException{
        //新建客户
        Thread.sleep(2000);
        driver.findElement(By.id("Name")).sendKeys(name);
        //选择框，选择数据
        new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("nana");
        new Select(driver.findElement(By.id("Level"))).selectByVisibleText("大客户");
        new Select(driver.findElement(By.id("IndustryId"))).selectByVisibleText("金融");
        Thread.sleep(3000);
        new Select(driver.findElement(By.id("SubIndustryId"))).selectByVisibleText("证券");
        Thread.sleep(3000);
        new Select(driver.findElement(By.id("AddressProvinceCode"))).selectByVisibleText("河南");
        Thread.sleep(2000);
        new Select(driver.findElement(By.id("AddressCityCode"))).selectByVisibleText("郑州");
        Thread.sleep(2000);
        new Select(driver.findElement(By.id("AddressAreaCode"))).selectByVisibleText("管城");
        driver.findElement(By.id("AddressDetail")).sendKeys("450号");
        driver.findElement(By.id("Telephone")).sendKeys("18756598745");
        new Select(driver.findElement(By.id("Department"))).selectByVisibleText("总监室");
        Thread.sleep(2000);
        new Select(driver.findElement(By.id("PersonInCharge"))).selectByVisibleText("汪名亮");
        driver.findElement(By.id("CompanyWebsite")).sendKeys("hhhhhhhhhhhhhh");
        driver.findElement(By.id("BriefIntroduction")).sendKeys("xdgnkdsndfksdfsdf");
        driver.findElement(By.id("Remark")).sendKeys("hshhshshshshhshhsh");
        driver.findElement(By.id("btn_save")).click();

    }

}
