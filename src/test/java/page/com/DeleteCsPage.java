package page.com;

import TestThinvent.com.DeleteCustomer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by xhm on 2017/6/20.
 */
public class DeleteCsPage {
    private WebDriver driver;
    public static class getElement{
        //删除
        public static final String DELETEBTN_CSS="button.form-control.list-transfer";
        public static final String DELETECOM_CSS="button.zeromodal-btn.zeromodal-btn-primary";
        public static final String DELETEALT_XPATH="//*[@id='alertdiv']";
        public static final String ALTCLOSE_XPATH="//i[@onclick='alertClose();']";
    }
    public DeleteCsPage(WebDriver driver){
        this.driver=driver;
    }
    //删除客户
    public String[]  DeleteCustomer(int i) throws InterruptedException{
        if (i%16==0||i>16){
            for (int j=1;j<=i/16;j++){
                this.driver.findElement(By.xpath(Loginpage.getElement.MOREDATA_XPATH)).click();
            }
        }
        String str=String.format("//*[@id='tablebody']/tr[%d]/td[1]/input[1]",i);
        String str1=driver.findElement(By.xpath(String.format("//*[@id='tablebody']/tr[%d]/td[2]/span",i))).getText();
        this.driver.findElement(By.xpath(str)).click();
        this.driver.findElement(By.cssSelector(getElement.DELETEBTN_CSS)).click();
        this.driver.findElement(By.cssSelector(getElement.DELETECOM_CSS)).click();
        Thread.sleep(2000);
        String alterTitle=driver.findElement(By.xpath(getElement.DELETEALT_XPATH)).getText();
        driver.findElement(By.xpath(getElement.ALTCLOSE_XPATH)).click();
        Thread.sleep(3000);
        String[] title={alterTitle,str1};
        return title;
    }
}
