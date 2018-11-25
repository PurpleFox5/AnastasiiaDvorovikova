package mobile.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static mobile.hw3.Driver.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SimpleWebTests extends Hooks{

    @Test(description = "Open site, check title, elements, open another page", groups = "web")
    public void webTest() throws Exception {
        getDriver().get(SUT);
        getDriverWait().until(ExpectedConditions.urlToBe(SUT + "/"));

        //Check title
        assertEquals(getDriver().getTitle(), "Internet Assigned Numbers Authority");

        //Check 3 blocks and these titles
        List<WebElement> bloks = getDriver().findElements(By.cssSelector(".home-panel>h2"));
        assertEquals(bloks.size(), 3);
        assertEquals(bloks.get(0).getText(), "Domain Names");
        assertEquals(bloks.get(1).getText(), "Number Resources");
        assertEquals(bloks.get(2).getText(), "Protocol Assignments");

        //Go to another page by link Learn more
        getDriver().findElement(By.cssSelector(".avoid-break")).click();
        assertTrue(getDriver().findElement(By.id("logo")).isDisplayed());
        assertEquals(getDriver().getTitle(), "IANA — About us");

        System.out.println("Simplest Web test done");
    }

}

