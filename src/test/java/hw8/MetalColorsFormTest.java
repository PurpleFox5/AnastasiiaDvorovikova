package hw8;

import dataProviders.DataProviderJSON;
import entries.Data;
import enums.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static site.JDISite.*;

// TODO What of the reason of  BeforeMethod and AfterMethod in this particular case ?
public class MetalColorsFormTest extends MetalColorsTestInit {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        login.submit(new User());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        login.logout();
    }

    // TODO Where is the steps from TestCase as a comments ?
    @Test(dataProvider = "dataProviders", dataProviderClass = DataProviderJSON.class)
    public void metalColorsTest(Data data) {
        homePage.checkOpened();
        homePage.checkUserName();
        openMetalColor();
        metalsColorsForm.submit(data);
        checkExample(data);
    }

}
