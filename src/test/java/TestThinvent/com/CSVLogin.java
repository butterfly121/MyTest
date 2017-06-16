package TestThinvent.com;
import java.io.IOException;
import  java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import com.csvreader.CsvReader;
import module.com.loginModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
/**
 * Created by xhm on 2017/6/2.
 */
public class CSVLogin {
    private WebDriver driver;
    private String baseUrl;
    @Before
    public void setUp(){
        driver=new ChromeDriver();
        driver.get("http://192.168.64.222:8088/login.aspx");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public  void  login()throws InterruptedException,IOException{
        String filePath="/Users/xhm/IdeaProjects/MyTest/.idea/dataFile/info.csv";
        CsvReader reader=new CsvReader(filePath,',',Charset.forName("GBK"));
        reader.readHeaders();
        while (reader.readRecord()){
            //读取一条记录
//            System.out.println(reader.getRawRecord());
            //按列名读取这条记录的值
            String username=reader.get("username");
            String password=reader.get("password");
            String name=reader.get("name");
            loginModule.login(driver, username, password);
            Thread.sleep(3000);
            //判断登录人的名字和账号是否一致
            assertEquals(name,driver.findElement(By.xpath("//*[@id='userName']")).getText());
            loginModule.loginOut(driver);
            Thread.sleep(3000);
        }
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
