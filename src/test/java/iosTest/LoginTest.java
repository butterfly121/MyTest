package iosTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;
/**
 * Created by xhm on 2017/6/19.
 */
public class LoginTest {
    private AppiumDriver wd;
    private iosLoginpage page;
    @BeforeTest
    public void setUp() throws Exception {
        this.page=new iosLoginpage(wd);
        wd= page.setUp("iphone 5s","10.2.1","iOS",false);
//        wd= page.setUp("iPhone Simulator","10.3","iOS",true);
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void test() throws Exception {
        page.login("zhangjian","111111");
        String str=page.getUser();
        System.out.println(str);
        assertEquals(str,"张健");
        page.loginOut();
    }

    @AfterTest
    public void tearDown() {
        wd.quit();
    }

}
