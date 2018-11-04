package pageObjects;

import enums.Titles;
import io.qameta.allure.Step;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class BasicPage {

    @Step
    public void checkTitle(Titles title) {
        assertEquals(getWebDriver().getTitle(), title.name);
    }

}
