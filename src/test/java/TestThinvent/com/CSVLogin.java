package TestThinvent.com;
import java.io.IOException;
import  java.nio.charset.Charset;
import com.csvreader.CsvReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by xhm on 2017/6/2.
 */
public class CSVLogin {

    @Test
    public static void  main(String[] args)throws InterruptedException,IOException{
        String filePath="/Users/xhm/IdeaProjects/MyTest/.idea/dataFile/info.csv";
        CsvReader reader=new CsvReader(filePath,',',Charset.forName("GBK"));
        reader.readHeaders();
        while (reader.readRecord()){
            //读取一条记录
//            System.out.println(reader.getRawRecord());
//            System.out.println("======");
            //按列名读取这条记录的值
            WebDriver driver=new ChromeDriver();
            driver.get("http://192.168.64.222:8088/login.aspx");
            System.out.println(reader.get("username"));
            System.out.println(reader.get("password"));
            System.out.println("======");
            String username=reader.get("username");
            String password=reader.get("password");
            loginModule.login(driver, username, password);
            Thread.sleep(5000);
            System.out.println("login success");
            loginModule.loginOut(driver);
            System.out.println("loginOut success");
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
