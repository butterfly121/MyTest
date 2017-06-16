package TestThinvent.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.com.CustomerPage;
import page.com.InquiriesPage;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;
/**
 * Created by xhm on 2017/6/13.
 */
public class FinalInquiries {
    private WebDriver driver;
    private String baseUrl;
    private CustomerPage page;
    private String username;
    private InquiriesPage inquirPage;
    @BeforeTest
    public void setUp() throws Exception{
        driver=new ChromeDriver();
        baseUrl="http://192.168.64.222:8088/login.aspx";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.page=new CustomerPage(this.driver,baseUrl);
        page.login("huxuan","111111");
        Thread.sleep(3000);
        username=page.user();
        assertEquals(username,"胡轩");
    }
    @Test
    public void Inquiries() throws Exception{
        page.Final_page(CustomerPage.getElement.FINAL_XPATH,true);
        page.Final_iframe(CustomerPage.getElement.FINAL_FRAME_XPATH);
        String name=driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[1]/ul/li[2]")).getText();
        assertEquals(name,"最终客户");
        inquirPage=new InquiriesPage(driver);
        //验证是否默认是我负责的客户展示
        inquirPage.isPersonIncharge(username);
        //筛选框筛选
        inquirPage.quiryFinal("我负责的最终客户","金融","银行","缓存客户");
        inquirPage.quiryFinal("我负责的最终客户","选择领域","选择行业","有效客户");
        inquirPage.quiryFinal("我参与的最终客户","军警","部队","缓存客户");
        inquirPage.quiryFinal("我参与的最终客户","选择领域","选择行业","有效客户");
        inquirPage.quiryFinal("我下属的最终客户","交通","交通","缓存客户");
        inquirPage.quiryFinal("我负责的最终客户","选择领域","选择行业","有效客户");
        Thread.sleep(3000);
        //高级筛选
        driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[3]/div[1]/button[1]")).click();
        String[] xpathArr={"//*[@id='search_name']","//*[@id='search_level']","//*[@id='search_code']","//*[@id='search_industryName']","//*[@id='search_createTime']","//*[@id='search_updateTime']","//*[@id='search_contactNumber']","//*[@id='search_personInCharge']","//*[@id='search_approvalStatus']"};
        String[] sendTextArr={driver.findElement(By.xpath("//*[@id='tablebody']/tr[1]/td[2]/span")).getText(),"中客户","4","酒店","2017-05-02","2017-05-02","2","胡","待审批"};
        for (int i=0;i<xpathArr.length;i++){
            inquirPage.search(xpathArr[i],sendTextArr[i]);
        }
        driver.findElement(By.cssSelector("button.form-control.btn-field-filter")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("td.link-text")).click();
        Thread.sleep(2000);
        //排序
        for (int i=2 ;i<=10;i++){
            for (int j=1;j<=2;j++){

                driver.findElement(By.xpath(String.format("//table[@id='simpledatatable']/thead/tr/th[%d]",i))).click();
                Thread.sleep(2000);
            }
        }
        driver.findElement(By.id("check_checkAll")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("check_checkAll")).click();
        Thread.sleep(2000);
        //新建客户验证新建客户弹框是否弹出
        driver.findElement(By.xpath("//*[@id='form1']/div[3]/div[3]/div[1]/button[2]")).click();
        By el=By.xpath("html/body/div[4]/div[3]/iframe");
        assertTrue(page.isElementPresent(driver,el));
        driver.findElement(By.cssSelector(".zeromodal-close")).click();
        Thread.sleep(2000);
        //移交客户
        int i=2;
        By handel= page.HandoverSelect(i);
        //判断界面是否有提示信息
        while (page.isElementPresent(driver,handel)){
            //取消选中
            page.HandoverUnSelect(i);
            i++;
            //选中移交
            el= page.HandoverSelect(i);
        }
        //判断页面是否有新的元素
        By el1=page.HandovergetElement();
        assertTrue(page.isElementPresent(driver,el1));
        if (page.isElementPresent(driver,el1)){
            driver.findElement(By.cssSelector(".zeromodal-close")).click();
        }
        //删除客户
        inquirPage.quiryFinal("我下属的最终客户","选择领域","选择行业","缓存客户");
        int j=1;
        String[] arry=page.DeleteCustomer(j);
        String alterTitle=arry[0];
        String finalName=arry[1];
        while (!alterTitle.equals("删除成功!")){
            i++;
            arry=page.DeleteCustomer(j);
            alterTitle=arry[0];
            finalName=arry[1];
        }
    }
    public void isPersonIncharge(){
        By el=By.xpath("//*[@id='tablebody']/tr[1]/td[9]");
        String personcharge=driver.findElement(el).getText();
        if (page.isElementPresent(driver,el)){
            assertEquals(personcharge,username);
        }
    }
    @AfterTest
    public void tearDown(){

        driver.quit();
    }

}
