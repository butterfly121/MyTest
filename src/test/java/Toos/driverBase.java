package Toos;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * @author
 * */
public class driverBase {
	public WebDriver driver;
	/**
	 * 获取driver
	 * */
	public WebDriver getDriver() {
        return driver;
    }
	/**
	 * 自动截图
	 * */
    public void takeScreenShot() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateStr = sf.format(date);//上面几行代码的意思都是获取时间，并且格式化，用来作为图片的名称　　　　//下面这个是获取当前运行的类名称和时间的组合一起命名图片
        String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";//因为我们截图是需要用到driver的，所以这里需要获取driver，这个driver是获取的当前对象的driver
        takeScreenShot((TakesScreenshot) this.getDriver(), path);
    }
    /**
     * 传入参数截图
     * */
    public void takeScreenShot(TakesScreenshot drivername, String path) {
        String currentPath ="/Users/xhm/Desktop/截图/"; // get current work
        System.out.println(currentPath);
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	System.out.println("截图成功");
        }
    }
}