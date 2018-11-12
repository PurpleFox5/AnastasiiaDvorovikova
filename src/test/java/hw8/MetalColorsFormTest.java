package hw8;

import dataProviders.DataProviderJSON;
import entries.MetalsColorsData;
import enums.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static site.JDISite.*;

// TODO What of the reason of  BeforeMethod and AfterMethod in this particular case ?
// TODO Once again, you should create a one single tests with specific steps.
// TODO Why don't you do that just in @Test method ?
public class MetalColorsFormTest extends MetalColorsTestInit {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        //Open HomePage
        open();

        //Login
        login.submit(new User());

        //Check that homePage is opened
        homePage.checkOpened();

        // TODO This method should be parametrised.
        //Check user name
        homePage.checkUserName();

        //Open Metals & Colors page by Header menu
        openMetalColor();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        metalColorPage.refresh();
    }

    @Test(dataProvider = "dataProviders", dataProviderClass = DataProviderJSON.class)
    public void metalColorsTest(MetalsColorsData data) {
        //Check that metalColorPage is opened
        metalColorPage.checkOpened();

        //Fill form Metals & Colors by data
        metalsColorsForm.submit(data);

        //Check result sections
        checkExample(data);
    }

}
