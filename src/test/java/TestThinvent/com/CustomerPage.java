package TestThinvent.com;

import org.apache.regexp.RE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by xhm on 2017/6/7.
 */
public class CustomerPage {
    public static class getElement{
        //登录
        public static final String USERNAME_NAME = "txt_usercode";
        public static final String PASSWORD_NAME = "txt_pwd";
        public static final String LOGIN_BUTTON_ID = "btn_login";
        public static final String LOGIN_SUCCESS_TEXT_ID = "userName";

        public static final String LOGOUT_BUTTON_XPATH="//*[@id='form1']/div[2]/div[1]/div[2]/nav/ul/li[3]/a/i";
        public static final String LOGOUT_COMMIT_XPATH="//*[@id='myModal']/div/div/div[3]/button[2]";

        //进入渠道
        public static final String CUSTOM_XPATH="//*[@id='side-menu']/li[4]/a";
        public static final String FINAL_XPATH="//*[@id='side-menu']/li[4]/ul/li[1]/a";
        public static final String FINAL_FRAME_XPATH="//*[@id='page31']";
        public static final String MOREDATA_XPATH="//*[@id='simpledatatable']/tbody[2]/tr/td";
        //新建
        public static final String NewButton_XPATH="//*[@id='form1']/div[3]/div[3]/div[1]/button[2]";
        public static final String NEWCUSTOMER_IFRAME="html/body/div[4]/div[3]/iframe";

        //删除
        public static final String DELETEBTN_CSS="button.form-control.list-transfer";
        public static final String DELETECOM_CSS="button.zeromodal-btn.zeromodal-btn-primary";
        public static final String DELETEALT_XPATH="//*[@id='alertdiv']";
        public static final String ALTCLOSE_XPATH="//i[@onclick='alertClose();']";
        //移交
        public static final String HANDBTN_CSS=".form-control.list-add2";
        public static final String HANDALT_XPATH="//*[@id='alertdiv']";
        public static final String HAND_IFRAME_XPATH="html/body/div[4]/div[3]/iframe";

        public static final String HAND_INPUT_XPATH="//*[@id='s2id_ddr_handOverPerson']/a/span[1]";
        public static final String HAND_FORMINPUT_XPATH="//*[@id='select2-drop']/div/input";
        public static final String HAND_INPUTTEXT_XPATH="//*[@id='select2-drop']/ul/li[10]";
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
        //编辑客户
        public static final String UPDATABTN_ID="btnEdit";
        public static final String UPDATA_IFRAME_XPATH="html/body/div[2]/div[3]/iframe";
        public static final String SELETE_UPDATA_XPATH="//*[@id='tablebody']/tr[%d]/td[2]/span";
        //新建联系人
        public static final String CONTANCT_LABLE_XPATH="//*[@id='form1']/div[3]/div[3]/div/div[2]/div/ul/li[2]/a";
        public static final String CONTANCT_BEN_XPATH="//*[@id='btnAdd']";
        public static final String CONTANCT_IFRAME_XPATH="zeromodal-frame";




    }
    private WebDriver driver;
    private String url;
    public CustomerPage(WebDriver driver,String url){
        this.driver=driver;
        this.url=url;
        this.driver.get(url);
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
    public void Final_page() throws Exception{
        this.driver.findElement(By.xpath(getElement.CUSTOM_XPATH)).click();
        Thread.sleep(2000);
        this.driver.findElement(By.xpath(getElement.FINAL_XPATH)).click();
        Thread.sleep(3000);
    }
    //找到最终客户frame
    public void Final_iframe(){
        WebElement rc=this.driver.findElement(By.xpath(getElement.FINAL_FRAME_XPATH));
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
    //新建客户
    public  void CreateCustom(String name) throws InterruptedException{
        //新建客户
        Thread.sleep(2000);
        driver.findElement(By.id("Name")).sendKeys(name);
        //选择框，选择数据
        new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("nana");
        new Select(driver.findElement(By.id("Level"))).selectByVisibleText("大客户");
        SelectIndustry("教育","教育");
        SelecteAdress("上海","上海","徐汇");
        driver.findElement(By.id("AddressDetail")).sendKeys("450号");
        driver.findElement(By.id("Telephone")).sendKeys("18756598745");
        SelectPerson("总监室","汪名亮");
        driver.findElement(By.id("CompanyWebsite")).sendKeys("hhhhhhhhhhhhhh");
        driver.findElement(By.id("BriefIntroduction")).sendKeys("xdgnkdsndfksdfsdf");
        driver.findElement(By.id("Remark")).sendKeys("hshhshshshshhshhsh");
        driver.findElement(By.id("btn_save")).click();

    }
    //删除客户
    public String DeleteCustomer(int i) throws InterruptedException{
        if (i%16==0||i>16){
            for (int j=1;j<=i/16;j++){
                this.driver.findElement(By.xpath(getElement.MOREDATA_XPATH)).click();
            }
        }
        String str=String.format("(//input[@type='checkbox'])[%d]",i);
//        System.out.println(str);
        this.driver.findElement(By.xpath(str)).click();
        this.driver.findElement(By.cssSelector(getElement.DELETEBTN_CSS)).click();
        this.driver.findElement(By.cssSelector(getElement.DELETECOM_CSS)).click();
        Thread.sleep(2000);
        String alterTitle=driver.findElement(By.xpath(getElement.DELETEALT_XPATH)).getText();
        driver.findElement(By.xpath(getElement.ALTCLOSE_XPATH)).click();
        Thread.sleep(3000);
        return alterTitle;
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
        driver.findElement(By.xpath(getElement.ALTCLOSE_XPATH)).click();
        String str1=String.format("(//input[@type='checkbox'])[%d]",i);
        driver.findElement(By.xpath(str1)).click();
        Thread.sleep(2000);
    }
    public By HandovergetElement(){
        By el=By.xpath(getElement.HAND_IFRAME_XPATH);
        return el;

    }
    public void HandOver() throws InterruptedException{
        WebElement rc1=driver.findElement(By.xpath(getElement.HAND_IFRAME_XPATH));
        driver.switchTo().frame(rc1);
        //选择输入框定位的方法
        driver.findElement(By.xpath(getElement.HAND_INPUT_XPATH)).click();
        WebElement from_inpox = driver.findElement(By.xpath(getElement.HAND_FORMINPUT_XPATH));
        Actions actions = new Actions(driver);
        actions.moveToElement(from_inpox).click().perform();
        driver.findElement(By.xpath(getElement.HAND_INPUTTEXT_XPATH)).click();

        Thread.sleep(2000);
        driver.findElement(By.id("RelatedBusiness_0")).click();
        driver.findElement(By.id("RelatedBusiness_1")).click();
        driver.findElement(By.xpath("//*[@id='form1']/div[4]/button[2]")).click();
        Thread.sleep(2000);
    }
    //编辑客户
    public By updataCustomer(int i) throws InterruptedException{
        String str=String.format(getElement.SELETE_UPDATA_XPATH,i);
        driver.findElement(By.xpath(str)).click();
        Thread.sleep(2000);
        driver.findElement(By.id(getElement.UPDATABTN_ID)).click();
        By EL=By.xpath(getElement.UPDATA_IFRAME_XPATH);
        return EL;
    }
    //新建联系人
    public By newContanct(int i) throws InterruptedException{
        String str=String.format(getElement.SELETE_UPDATA_XPATH,i);
        driver.findElement(By.xpath(str)).click();
        //点击联系人标签，新建联系人按钮
        driver.findElement(By.xpath(getElement.CONTANCT_LABLE_XPATH)).click();
        driver.findElement(By.xpath(getElement.CONTANCT_BEN_XPATH)).click();
        Thread.sleep(2000);
        //新建联系人
//        WebElement el1=driver.findElement(By.className(getElement.CONTANCT_IFRAME_XPATH));
//        driver.switchTo().frame(el1);
        By el=By.className(getElement.CONTANCT_IFRAME_XPATH);
        return el;
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
}
