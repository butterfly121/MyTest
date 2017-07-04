package page.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

/**
 * Created by xhm on 2017/6/20.
 */
public class RevisiomCsPage {
    private WebDriver driver;
    private Loginpage page;
    private  String state;
    public static class getElement{
        //编辑客户
        public static final String UPDATABTN_ID="btnEdit";
        public static final String UPDATA_IFRAME_XPATH="html/body/div[2]/div[3]/iframe";
        public static final String SELETE_UPDATA_XPATH="//*[@id='tablebody']/tr[%d]/td[2]/span";
        public static final String MOREDATA_BTN_XPATH="//*[@id='simpledatatable']/tbody[2]/tr/td";
        public static final String NOT_UPDATA_XPATH="//*[@id='tablebody']/tr[%d]/td[10]";
    }
    public RevisiomCsPage(WebDriver driver){
        this.driver=driver;
    }
    //编辑客户
    public void updataCustomer(String finalStyle,String finaPage,String finalFrame,boolean isFirst,boolean isBigBox) throws Exception {
        page=new Loginpage(this.driver);
        page.Final_page(finaPage,isFirst);
        page.Final_iframe(finalFrame);
        new Select(driver.findElement(By.xpath("//*[@id='ddr_permissionGroup']"))).selectByVisibleText(String.format("我负责的%s",finalStyle));
        int i=1;
        if (finalStyle.equals("最终客户")){
            state=driver.findElement(By.xpath("//*[@id='tablebody']/tr[1]/td[10]")).getText();
        }
        else {
            state=driver.findElement(By.xpath("//*[@id='tablebody']/tr[1]/td[8]")).getText();
        }

        while (state.equals("待审批")){
            i++;
            System.out.println(state);
            if (i%16==0||i>16){
                driver.findElement(By.xpath(getElement.MOREDATA_BTN_XPATH)).click();
            }
            if (finalStyle.equals("最终客户")){
                state=driver.findElement(By.xpath(String.format(getElement.NOT_UPDATA_XPATH,i))).getText();
            }
            else {
                state=driver.findElement(By.xpath(String.format("//*[@id='tablebody']/tr[%d]/td[8]",i))).getText();
            }
        }

        String str=String.format(getElement.SELETE_UPDATA_XPATH,i);
        driver.findElement(By.xpath(str)).click();
        Thread.sleep(2000);
        driver.findElement(By.id(getElement.UPDATABTN_ID)).click();

        WebElement el=driver.findElement(By.xpath(getElement.UPDATA_IFRAME_XPATH));
        driver.switchTo().frame(el);
        Thread.sleep(2000);
        //编辑客户
        String Name=driver.findElement(By.id("Name")).getAttribute("value");
        Name=String.format("%s%s",Name,page.getCurrentData());
        updata("Name",Name);
        Thread.sleep(3000);
        page.SelectIndustry("政法","公安");
        if (finalStyle.equals("最终客户")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("新建最终客户测试006");
        }
        else if(finalStyle.equals("管理机构")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("大风车");
        }
        else if (finalStyle.equals("咨设机构")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("咨设机构");
        }
        Thread.sleep(3000);
        page.SelecteAdress("上海","上海","徐汇");
        String[] idnameArr={"AddressDetail","CompanyWebsite","BriefIntroduction","Remark"};
        String[] sendKeysArr={"181","www.baidu.com81","写的是根深蒂固81","dgsdgsdgdfsgdfg81"};
        for (int j=0;j<idnameArr.length;j++){
            updata(idnameArr[j],sendKeysArr[j]);
        }
        driver.findElement(By.id("btn_save")).click();
        //找到上个页面的frame
        page.Final_iframe(finalFrame);
        Thread.sleep(3000);
        if (isBigBox){
            String name1=driver.findElement(By.id("lblName")).getText();
            assertEquals(name1,Name);
        }else {
            WebElement display=driver.findElement(By.xpath("//*[@id='btnEdit']"));
            assertFalse(display.isEnabled());
        }
        Thread.sleep(3000);
    }
    public void updata(String idname,String sendkeys){
        driver.findElement(By.id(idname)).clear();
        driver.findElement(By.id(idname)).sendKeys(sendkeys);
    }
}
