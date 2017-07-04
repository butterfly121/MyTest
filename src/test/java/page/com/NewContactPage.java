package page.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by xhm on 2017/6/20.
 */
public class NewContactPage {
    private WebDriver driver;
    public static class getElement{
        //新建联系人
        public static final String CONTANCT_LABLE_XPATH="//*[@id='form1']/div[3]/div[3]/div/div[2]/div/ul/li[2]/a";
        public static final String CONTANCT_BEN_XPATH="//*[@id='btnAdd']";
        public static final String CONTANCT_IFRAME_XPATH="zeromodal-frame";
    }
    public NewContactPage(WebDriver driver){
        this.driver=driver;
    }
    //新建联系人
    public By newContanct(int i) throws InterruptedException{
        String str=String.format(RevisiomCsPage.getElement.SELETE_UPDATA_XPATH,i);
        driver.findElement(By.xpath(str)).click();
        //点击联系人标签，新建联系人按钮
        driver.findElement(By.xpath(getElement.CONTANCT_LABLE_XPATH)).click();
        driver.findElement(By.xpath(getElement.CONTANCT_BEN_XPATH)).click();
        Thread.sleep(2000);
        //新建联系人
        By el=By.className(getElement.CONTANCT_IFRAME_XPATH);
        return el;
    }
}

