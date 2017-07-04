package page.com;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xhm on 2017/6/20.
 */
public class Loginpage {
    private WebDriver driver;
    private String url;
    public static class getElement {
        //登录
        public static final String USERNAME_NAME = "txt_usercode";
        public static final String PASSWORD_NAME = "txt_pwd";
        public static final String LOGIN_BUTTON_ID = "btn_login";
        public static final String LOGIN_SUCCESS_TEXT_ID = "userName";

        public static final String LOGOUT_BUTTON_XPATH = "//*[@id='form1']/div[2]/div[1]/div[2]/nav/ul/li[3]/a/i";
        public static final String LOGOUT_COMMIT_XPATH = "//*[@id='myModal']/div/div/div[3]/button[2]";

        //进入渠道
        public static final String CUSTOM_XPATH = "//*[@id='side-menu']/li[4]/a";
        public static final String FINAL_XPATH = "//*[@id='side-menu']/li[4]/ul/li[1]/a";
        public static final String FINAL_FRAME_XPATH = "//*[@id='page31']";
        public static final String MOREDATA_XPATH = "//*[@id='simpledatatable']/tbody[2]/tr/td";
        //选择行业
        public static final String INDUSTRY_ID="IndustryId";
        public static final String SUBINDUSTRY_ID="SubIndustryId";
        //选择省市区
        public static final String PROVINCE_ID="AddressProvinceCode";
        public static final String CITY_ID="AddressCityCode";
        public static final String AREA_ID="AddressAreaCode";
        //选择负责人
        public static final String DEPARTMENT_ID="Department";
        public static final String PERSON_ID="PersonInCharge";
        //管理机构
        public static final String ORG_XPATH="//*[@id='side-menu']/li[4]/ul/li[2]/a";
        public static final String ORG_FRAME_XPATH="//*[@id='page32']";
        //咨设机构
        public static final String ORG1_XPATH="//*[@id='side-menu']/li[4]/ul/li[3]/a";
        public static final String ORG1_FRAME_XPATH="//*[@id='page33']";
        //招标代理
        public static final String ORG2_XPATH="//*[@id='side-menu']/li[4]/ul/li[4]/a";
        public static final String ORG2_FRAME_XPATH="//*[@id='page34']";
        //上下友商
        public static final String ORG3_XPATH="//*[@id='side-menu']/li[4]/ul/li[5]/a";
        public static final String ORG3_FRAME_XPATH="//*[@id='page35']";

    }
    public Loginpage(WebDriver driver){
        this.driver=driver;
        this.driver=driver;
//        this.url=url;
//        this.driver.get(url);
        //谷歌浏览器版本太高，最大化的方法无效果
//        this.driver.manage().window().maximize();
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }
    //登录
    public void login(String username,String password){
        WebElement el=this.driver.findElement(By.name(getElement.USERNAME_NAME));
        el.clear();;
        el.sendKeys(username);
        WebElement el1=this.driver.findElement(By.name(getElement.PASSWORD_NAME));
        el1.clear();
        el1.sendKeys(password);
        this.driver.findElement(By.id((getElement.LOGIN_BUTTON_ID))).click();
    }
    //找到最终客户
    public void Final_page(String finalXpath,boolean isFirst) throws Exception{
        if (isFirst){
            this.driver.findElement(By.xpath(getElement.CUSTOM_XPATH)).click();
            Thread.sleep(2000);
        }
        this.driver.findElement(By.xpath(finalXpath)).click();
        Thread.sleep(3000);
    }
    //找到最终客户frame
    public void Final_iframe(String finalFrameXpath){
        WebElement rc=this.driver.findElement(By.xpath(finalFrameXpath));
        driver.switchTo().frame(rc);
    }
    //退出登录
    public void loginOut()throws InterruptedException{
        Thread.sleep(2000);
        this.driver.findElement(By.xpath(getElement.LOGOUT_BUTTON_XPATH)).click();
        Thread.sleep(2000);
        this.driver.findElement(By.xpath(getElement.LOGOUT_COMMIT_XPATH)).click();
    }
    //返回成功之后的用户名
    public String user(){
        String text = this.driver.findElement(By.id(getElement.LOGIN_SUCCESS_TEXT_ID)).getText();
        System.out.println(text);
        return text;
    }
    //判断页面该元素是否存在，NoSuchElementException不管用
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
    //选择行业
    public void SelectIndustry(String industry,String subindustry) throws InterruptedException{
        new Select(driver.findElement(By.id(getElement.INDUSTRY_ID))).selectByVisibleText(industry);
        Thread.sleep(3000);
        new Select(driver.findElement(By.id(getElement.SUBINDUSTRY_ID))).selectByVisibleText(subindustry);
        Thread.sleep(3000);
    }
    //选择省市区
    public void SelecteAdress(String province,String city,String area)throws InterruptedException{
        new Select(driver.findElement(By.id(getElement.PROVINCE_ID))).selectByVisibleText(province);
        Thread.sleep(2000);
        new Select(driver.findElement(By.id(getElement.CITY_ID))).selectByVisibleText(city);
        Thread.sleep(2000);
        new Select(driver.findElement(By.id(getElement.AREA_ID))).selectByVisibleText(area);
    }
    //选择负责人
    public void SelectPerson(String department,String person) throws InterruptedException{
        new Select(driver.findElement(By.id(getElement.DEPARTMENT_ID))).selectByVisibleText(department);
        Thread.sleep(2000);
        new Select(driver.findElement(By.id(getElement.PERSON_ID))).selectByVisibleText(person);
    }
    public String getCurrentData(){
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
        //获取当前时间
        String dataStr=df.format(new Date());
        return dataStr;
    }
}
