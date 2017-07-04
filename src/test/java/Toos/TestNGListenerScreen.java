package Toos;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
 import org.testng.TestListenerAdapter;

import java.util.Iterator;

public class TestNGListenerScreen extends TestListenerAdapter{

      @Override
     public void onTestSuccess(ITestResult tr) {
         super.onTestSuccess(tr);
     }
 //主要是用到这个方法了，当你报错时他会监听到，然后就会执行截图操作，这里的 ITestresult tr是testng里的，获取到的是当前运行对象，你这样理解，他就是//我当前运行的类这个对象
     @Override
    public void onTestFailure(ITestResult tr) {
      System.out.println("1");
        super.onTestFailure(tr);
       System.out.println(tr);
        takeScreenShot(tr);
    }
//所以这里我执行截图的时候就获取到了我运行过程中实力对象的driver了，懂了吧。所以当我再回去调用我基类的对象时，那么driver就是同一个了
    private void takeScreenShot(ITestResult tr) {
        driverBase b = (driverBase) tr.getInstance();
        b.takeScreenShot();


    }
    @Override
    public void onTestSkipped(ITestResult tr) {
         super.onTestSkipped(tr);
    }

     @Override
     public void onTestStart(ITestResult result) {
         super.onTestStart(result);
     }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
     }

     @Override
     public void onFinish(ITestContext testContext) {
//         log.info("Test Finish");

//         Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
         Iterator<ITestResult> listOfSkipedTests = testContext.getSkippedTests().getAllResults().iterator();
         while (listOfSkipedTests.hasNext()) {
             ITestResult failedTest = listOfSkipedTests.next();
             ITestNGMethod method = failedTest.getMethod();
             if (testContext.getFailedTests().getResults(method).size() > 0) {
                 listOfSkipedTests.remove();
             } else {
                 if (testContext.getPassedTests().getResults(method).size() > 0) {
                     listOfSkipedTests.remove();
                 }
             }
         }

     }
 }