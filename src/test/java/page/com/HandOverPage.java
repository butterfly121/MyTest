package page.com;

import TestThinvent.com.HandoverCustomer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by xhm on 2017/6/20.
 */
public class HandOverPage {
    private WebDriver driver;
    public static class getElement{
        //移交
        public static final String HANDBTN_CSS=".form-control.list-add2";
        public static final String HANDALT_XPATH="//*[@id='alertdiv']";
        public static final String HAND_IFRAME_XPATH="html/body/div[4]/div[3]/iframe";
        public static final String HAND_INPUT_XPATH="//*[@id='s2id_ddr_handOverPerson']/a/span[1]";
        public static final String HAND_FORMINPUT_XPATH="//*[@id='select2-drop']/div/input";
        public static final String HAND_INPUTTEXT_XPATH="//*[@id='select2-drop']/ul/li[10]";
    }
    public HandOverPage(WebDriver driver){
        this.driver=driver;
    }
    //移交客户
    public By HandoverSelect(int i) throws InterruptedException{
        String str=String.format("(//input[@type='checkbox'])[%d]",i);
        System.out.println(str);
        driver.findElement(By.xpath(str)).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(getElement.HANDBTN_CSS)).click();
        By el = By.xpath(getElement.HANDALT_XPATH);
        return el;
    }
    public void HandoverUnSelect(int i) throws InterruptedException{
        driver.findElement(By.xpath(DeleteCsPage.getElement.ALTCLOSE_XPATH)).click();
        String str1=String.format("(//input[@type='checkbox'])[%d]",i);
        driver.findElement(By.xpath(str1)).click();
        Thread.sleep(2000);
    }
    public By HandovergetElement(){
        By el=By.xpath(getElement.HAND_IFRAME_XPATH);
        return el;

    }
    public void HandOver() throws InterruptedException{
        WebElement rc1=driver.findElement(By.xpath(HandOverPage.getElement.HAND_IFRAME_XPATH));
        driver.switchTo().frame(rc1);
        //选择输入框定位的方法
        driver.findElement(By.xpath(HandOverPage.getElement.HAND_INPUT_XPATH)).click();
        WebElement from_inpox = driver.findElement(By.xpath(HandOverPage.getElement.HAND_FORMINPUT_XPATH));
        Actions actions = new Actions(driver);
        actions.moveToElement(from_inpox).click().perform();
        driver.findElement(By.xpath(HandOverPage.getElement.HAND_INPUTTEXT_XPATH)).click();
        Thread.sleep(2000);
        driver.findElement(By.id("RelatedBusiness_0")).click();
        driver.findElement(By.id("RelatedBusiness_1")).click();
        driver.findElement(By.xpath("//*[@id='form1']/div[4]/button[2]")).click();
        driver.findElement(By.xpath("html/body/div[4]/div[4]/div/button[1]")).click();

        Thread.sleep(2000);
    }
}
