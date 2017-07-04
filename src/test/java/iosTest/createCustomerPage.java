package iosTest;
import io.appium.java_client.AppiumDriver;

/**
 * Created by xhm on 2017/6/20.
 */
public class createCustomerPage {
    private AppiumDriver driver;
    public static class getElement{
        public static final String NAME_XPATH="//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField";
        public static final String LEVEL_XPATH="//XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]\n";
        public static final String INDUSTRY_XPATH="//XCUIElementTypeTable/XCUIElementTypeCell[3]";
        public static final String FUZERN_XPATH="//XCUIElementTypeTable/XCUIElementTypeCell[10]\n";
        public static final String FUZERNSELECT_XPATH="//XCUIElementTypeTable/XCUIElementTypeCell[10]";
        public static final String JIANJIE_XPATH="//XCUIElementTypeTable/XCUIElementTypeCell[11]";
        public static final String SEARCH_BTN_ID="icon search 03@2x";
        public static final String SEARCH_RESULT="//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText";
    }
    public createCustomerPage(AppiumDriver driver){
        this.driver=driver;
    }

}
