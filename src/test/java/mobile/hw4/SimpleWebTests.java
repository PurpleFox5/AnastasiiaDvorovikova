package mobile.hw4;

import mobile.hw3.PropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static mobile.hw4.DriverCapabilitiesFromJson.*;
//import static mobile.hw4.Driver.getDriver;

public class SimpleWebTests {

    @BeforeSuite
    public void setWeb() throws Exception {
//        getDriver(PropertyFile.WEB_PROPERTY);
        getDriver(PropertyFile.IOS_WEB);
        System.out.println("Driver prepared");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        getDriver().quit();
        System.out.println("Driver is closed");
    }

    @Test(description = "Open site, check title, elements, open another page")
    public void webTest() throws Exception {
        getDriver().get(SUT);
        getDriverWait().until(ExpectedConditions.urlToBe(SUT+"/"));

        //Check title
        assertEquals(getDriver().getTitle(), "Internet Assigned Numbers Authority");

        //Check 3 blocks and these titles
        List<WebElement> bloks  = getDriver().findElements(By.cssSelector(".home-panel>h2"));
        assertEquals(bloks.size(), 3);
        assertEquals(bloks.get(0).getText(), "Domain Names");
        assertEquals(bloks.get(1).getText(), "Number Resources");
        assertEquals(bloks.get(2).getText(), "Protocol Assignments");

        //Go to another page by link Learn more
        getDriver().findElement(By.cssSelector(".avoid-break")).click();
        getDriverWait().until(ExpectedConditions.urlToBe(SUT+"/about"));
        assertEquals(getDriver().getTitle(), "IANA — About us");
        assertTrue(getDriver().findElement(By.id("logo")).isDisplayed());

        System.out.println("Simplest Web test done");

        getDriver().close();
    }
}

