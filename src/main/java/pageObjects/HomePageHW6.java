package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.getUser;
import static org.testng.Assert.assertEquals;

public class HomePageHW6 {
    public HomePageHW6() {
        page(this);
    }

    @FindBy(css = ".profile-photo")
    private SelenideElement profileButton;

    @FindBy(css = "[id = 'Name']")
    private SelenideElement login;

    @FindBy(css = "[id = 'Password']")
    private SelenideElement password;

    @FindBy(css = ".login [type = 'submit']")
    private SelenideElement submit;

    @FindBy(css = ".nav")
    private ElementsCollection headerService;

    private SelenideElement service;

    //=================Methods================

    @Given("I am on \"([^\"]*)\"")
    public void openPage(String name) {
        open("https://epam.github.io/JDI/");
        assertEquals(getWebDriver().getTitle(), name);
    }

    @Given("I login as user \"([^\"]*)\"$")
    public void login(String user) {
        profileButton.click();
        login.sendKeys(getUser(user).login);
        password.sendKeys(getUser(user).password);
        submit.click();
    }

    @When("I click on \"(\\w+)\" button in Header")
    public void clickHeaderService(String s) {
        service = headerService.find(text(s));
        service.click();
    }

    @And("^I click on \"([^\"]*)\" button in Service dropdown$")
    public void clickUserTable(String s) {
        ElementsCollection li = service.$(".dropdown-menu").$$("li");
        li.find(text(s.toUpperCase())).click();
    }

}
