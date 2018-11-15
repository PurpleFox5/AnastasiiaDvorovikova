package hw8;

import dataProviders.DataProviderJSON;
import entries.MetalsColorsData;
import enums.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static site.JDISite.*;

public class MetalColorsFormTest extends MetalColorsTestInit {

    static private User PiterChailovskii = new User("PITER CHAILOVSKII", "epam", "1234");

    @AfterMethod
    public void afterMethod() {
        homePage.clearCache();
    }

    @Test(dataProvider = "dataProviders", dataProviderClass = DataProviderJSON.class)
    public void metalColorsTest(MetalsColorsData data) {
        //Open HomePage
        homePage.open();

        //Login
        homePage.login.submit(PiterChailovskii);

        //Check that homePage is opened
        homePage.checkOpened();

        //Check user name
        homePage.checkUserName(PiterChailovskii);

        //Open Metals & Colors page by Header menu
        openMetalColor();

        //Check that metalColorPage is opened
        metalColorPage.checkOpened();

        //Fill form Metals & Colors by data
        metalColorPage.metalsColorsForm.submit(data);

        //Check result sections
        metalColorPage.checkExample(data);
    }
}
