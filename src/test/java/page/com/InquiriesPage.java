package page.com;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;

/**
 * Created by xhm on 2017/6/16.
 */
public class InquiriesPage {
    private WebDriver driver;
    public InquiriesPage(WebDriver driver){
        this.driver=driver;
    }
    //高级筛选
    public void search(String serchXpath,String sendKey)throws Exception{
        this.driver.findElement(By.xpath(serchXpath)).sendKeys(sendKey);
        this.driver.findElement(By.xpath(serchXpath)).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        this.driver.findElement(By.xpath(serchXpath)).clear();
    }
    //筛选款筛选
    public void quiryFinal(String permissionGroup, String industry,String subIndustry,String effective) throws Exception{
        new Select(driver.findElement(By.xpath("//*[@id='ddr_permissionGroup']"))).selectByVisibleText(permissionGroup);
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//*[@id='ddr_industry']"))).selectByVisibleText(industry);
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//*[@id='ddr_subIndustry']"))).selectByVisibleText(subIndustry);
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//*[@id='ddr_effective']"))).selectByVisibleText(effective);
        Thread.sleep(2000);
        if (permissionGroup.equals("我下属的最终客户")){
            this.driver.findElement(By.xpath("//*[@id='s2id_ddr_department']/a/span[1]")).click();
            WebElement from_inpox =  this.driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
            Actions actions = new Actions( this.driver);
            actions.moveToElement(from_inpox).click().perform();
            this.driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[4]/div")).click();
            Thread.sleep(2000);
            this.driver.findElement(By.xpath("//*[@id='s2id_ddr_staff']/a/span[1]")).click();
            WebElement from_inpox1 =  this.driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
            Actions actions1 = new Actions( this.driver);
            actions1.moveToElement(from_inpox1).click().perform();
            this.driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[2]/div")).click();

        }
    }
    //默认是否我负责的最终客户
    public void isPersonIncharge(String username ){
        By el=By.xpath("//*[@id='tablebody']/tr[1]/td[9]");
        String personcharge= this.driver.findElement(el).getText();
        if (isElementPresent( this.driver,el)){
            assertEquals(personcharge,username);
        }
    }
    public   boolean isElementPresent(WebDriver driver,By el) {
        try {
            System.out.println("存在");
            driver.findElement(el);
            return true;
        }
        catch (Exception e) {
            System.out.println("不存在");
            return false;
        }
    }
}
