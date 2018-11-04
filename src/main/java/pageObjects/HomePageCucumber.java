package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.MenuService.getNames;
import static enums.MenuService.values;
import static org.testng.Assert.assertEquals;

public class HomePageCucumber {

    public HomePageCucumber() {
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

    @FindBy(css = ".profile-photo")
    private SelenideElement userName;

    @FindBy(css = ".icons-benefit")
    private ElementsCollection pictures;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection texts;

    @FindBy(xpath = "//li[@class='menu-title']//span[text()='Service']")
    private SelenideElement description;

    @FindBy(css = ".main-txt")
    private SelenideElement headline;

    @FindBy(xpath = "//li[@class='menu-title']//span[text()='Service']")
    private SelenideElement leftService;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//child::li")
    private ElementsCollection menuServiceHeader;

    @FindBy(xpath = "//*[@class='sub']/child::li")
    private ElementsCollection menuService;

    @FindBy(xpath = "//li[@class='dropdown']")
    private SelenideElement headerService;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/child::li[contains(.,'Different elements')]")
    private SelenideElement servicePage;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/child::li[contains(.,'Dates')]")
    private SelenideElement datePage;

    //========================Methods===========================

    @Step
    @When("I'm on the Home Page")
    public void openPage() {
        open("https://epam.github.io/JDI/index.html");
    }

    @Step
    @When("^I login as user (.+) with password (.+)$")
    public void login(String name, String passwd) {
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(passwd);
        submit.click();
    }

    @Step
    @When("I click on Service subcategory in the header")
    public void clickHeaderService() {
        headerService.click();
    }

    @Step
    @When("I click on Service subcategory in the left section")
    public void clickLeftMenuService() {
        leftService.click();
    }

    @Step
    @When("I open through the header menu Service -> Different Elements Page")
    public void openPageDifferentElements() {
        servicePage.click();
    }

    //=====================checks===============
    @Step
    @Then("^The browser title is Home Page$")
    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    @Step
    @Then("^The user icon is displayed on the header$")
    public void checkUserIcon() {
        profileButton.shouldBe(visible);
    }

    @Step("Check user name {0}")
    @Then("The user name is (.+)$")
    public void checkUserName(String user) {
        userName.shouldHave(text(user));
    }

    @Step
    @Then("There are (\\d+) pictures")
    public void checkPictures(int count) {
        pictures.shouldHaveSize(count);
    }

    @Step
    @Then("There are (\\d+) texts under pictures")
    public void checkTextUnderPictures(int count) {
        texts.shouldHaveSize(count);
    }

    @Step
    @Then("There are 2 texts above pictures")
    public void checkTextAbovePictures() {
        headline.shouldBe(visible);
        description.shouldBe(visible);
    }

    @Step
    @Then("The drop down contains options")
    public void checkHeaderMenuService() {
        menuServiceHeader.shouldHaveSize(values().length);
        menuServiceHeader.shouldHave(exactTexts(getNames()));
    }

    @Step
    @Then("The that drop down in the left section contains options")
    public void checkLeftMenuService() {
        menuService.shouldHaveSize(values().length);
        menuService.shouldHave(exactTexts(getNames()));
    }
}

