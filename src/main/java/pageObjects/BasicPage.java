package pageObjects;

import cucumber.api.java.en.Then;
import enums.Titles;
import io.qameta.allure.Step;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class BasicPage {

    @Then("The title is Home Page")
    @Step
    public void checkTitle(Titles title) {
        assertEquals(getWebDriver().getTitle(), title.name);
    }

}
