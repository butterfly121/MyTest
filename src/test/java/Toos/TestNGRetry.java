package Toos;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * 用例失败自动重跑逻辑
 * @author Charlie.chen
 *
 */
public class TestNGRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount=2;


    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "running retry for  '" + result.getName() + "' on class " + 
                                       this.getClass().getName() + " Retrying " + retryCount + " times";
            retryCount++;
            return true;
        }
        return false;
    }
}