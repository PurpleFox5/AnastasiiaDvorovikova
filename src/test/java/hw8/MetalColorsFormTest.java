package hw8;

import dataProviders.DataProviderJSON;
import entries.MetalsColorsData;
import enums.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static site.JDISite.*;

public class MetalColorsFormTest extends MetalColorsTestInit {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        metalColorPage.clearCache();
    }

    @Test(dataProvider = "dataProviders", dataProviderClass = DataProviderJSON.class)
    public void metalColorsTest(MetalsColorsData data) {
        //Open HomePage
        homePage.open();

        //Login
        login.submit(new User());

        //Check that homePage is opened
        homePage.checkOpened();

        //Check user name
        homePage.checkUserName(new User());

        //Open Metals & Colors page by Header menu
        openMetalColor();

        //Check that metalColorPage is opened
        metalColorPage.checkOpened();

        //Fill form Metals & Colors by data
        metalsColorsForm.submit(data);

        //Check result sections
        checkExample(data);
    }
}
