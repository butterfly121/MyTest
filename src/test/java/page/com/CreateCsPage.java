package page.com;
import Toos.ParseExcel;
import Toos.ReadExcel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import jxl.JXLException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
/**
 * Created by xhm on 2017/6/20.
 */
public class CreateCsPage   {
    private WebDriver driver;
    private Loginpage page;

    public static class getElement {
        //新建
        public static final String NewButton_XPATH="//*[@id='form1']/div[3]/div[3]/div[1]/button[2]";
        public static final String NEWCUSTOMER_IFRAME="html/body/div[4]/div[3]/iframe";
        public static final String GAOJISHAIXUAN_XPATH="//*[@id='form1']/div[3]/div[3]/div[1]/button[1]";
        public static final String HUANCUN_XPATH="//*[@id='ddr_effective']";
        public static final String SEARCHERNAME_XPATH="//*[@id='tablebody']/tr[1]/td[2]/span";
    }
    public CreateCsPage(WebDriver driver){
        this.driver=driver;
    }
    public void verifyCreateFinal(String finalName,String level,String industry,String industry1,String bumen,String personInchage,String janjie)throws Exception{
        page=new Loginpage(this.driver);
        Thread.sleep(2000);
//        if (!finalName.equals("null"))
//        {
//            driver.findElement(By.id("Name")).clear();
//            driver.findElement(By.id("Name")).sendKeys(finalName);
//        }
//        if (!industry1.equals("null")){
//            page.SelectIndustry(industry,industry1);
//        }
//        if (!bumen.equals("null")){
//            page.SelectPerson(bumen,personInchage);
//        }
//        if (!janjie.equals("null")){
//            driver.findElement(By.id("Remark")).sendKeys(janjie);
//        }
            driver.findElement(By.id("Name")).clear();
            driver.findElement(By.id("Name")).sendKeys(finalName);
        if (industry1.length()!=0){
            page.SelectIndustry(industry,industry1);
        }
        if (bumen.length()!=0){
            page.SelectPerson(bumen,personInchage);
        }
        driver.findElement(By.id("Remark")).sendKeys(janjie);
        driver.findElement(By.id("btn_save")).click();
        Thread.sleep(2000);
    }
    public void findFrame(String finaPage,String finalFrame,Boolean isFirst) throws Exception {
        page=new Loginpage(this.driver);
        page.Final_page(finaPage,isFirst);
        page.Final_iframe(finalFrame);
        driver.findElement(By.xpath(getElement.NewButton_XPATH)).click();
        //新建管理机构
        WebElement rc1 = driver.findElement(By.xpath("html/body/div[4]/div[3]/iframe"));
        driver.switchTo().frame(rc1);
        Thread.sleep(2000);
    }
    public void CreateCustom1(String name,String selectType,String finalFrame,String NewBtnTitle)throws Exception{

        //拼接用户名
        String Name=String.format("%s%s",name,page.getCurrentData());

        //新建客户
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Name")).sendKeys(Name);
        //选择框，选择数据
        if (NewBtnTitle.equals("新建管理机构")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("柯达");
            driver.findElement(By.id("Level")).sendKeys("hahahaA");
        }else if (NewBtnTitle.equals("新建客户")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("nana");
            new Select(driver.findElement(By.id("Level"))).selectByVisibleText("大客户");
        }
        else if (NewBtnTitle.equals("新建咨设机构")){
            new Select(driver.findElement(By.id("ParentCustomer"))).selectByVisibleText("咨设机构");
            driver.findElement(By.id("Level")).sendKeys("中型机构");
        }
        else if (NewBtnTitle.equals("新建上下友商")){
            new Select(driver.findElement(By.id("Level"))).selectByVisibleText("大");
        }
        page.SelectIndustry("教育","教育");
        page.SelecteAdress("上海","上海","徐汇");
        driver.findElement(By.id("AddressDetail")).sendKeys("450号");
        page.SelectPerson("总监室","汪名亮");
        driver.findElement(By.id("CompanyWebsite")).sendKeys("hhhhhhhhhhhhhh");
        driver.findElement(By.id("BriefIntroduction")).sendKeys("xdgnkdsndfksdfsdf");
        driver.findElement(By.id("Remark")).sendKeys("hshhshshshshhshhsh");
        driver.findElement(By.id("btn_save")).click();
        Thread.sleep(2000);
        //切换到可以找到管理机构的frame
        page.Final_iframe(finalFrame);
        //判断新建客户是否存在
        new Select(driver.findElement(By.xpath(getElement.HUANCUN_XPATH))).selectByVisibleText(selectType);
        Thread.sleep(3000);
        driver.findElement(By.xpath(getElement.GAOJISHAIXUAN_XPATH)).click();
        WebElement EL= driver.findElement(By.xpath("//*[@id='search_name']"));
        EL.sendKeys(Name);
        EL.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String finalName=driver.findElement(By.xpath(getElement.SEARCHERNAME_XPATH)).getText();
        assertEquals(finalName,Name);
        Thread.sleep(2000);
    }
}
