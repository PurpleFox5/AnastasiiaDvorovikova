package pageObjects;

import enums.Titles;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class BasicPage {

    public void checkTitle(Titles title) {
        assertEquals(getWebDriver().getTitle(), title.name);
    }

}
