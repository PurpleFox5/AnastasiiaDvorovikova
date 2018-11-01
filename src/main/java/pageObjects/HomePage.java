package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static enums.MenuService.getNames;
import static enums.MenuService.values;

public class HomePage extends BasicPage {
    @FindBy(css = ".profile-photo")
    private WebElement profileButton;

    @FindBy(css = "[id = 'Name']")
    private WebElement login;

    @FindBy(css = "[id = 'Password']")
    private WebElement password;

    @FindBy(css = ".login [type = 'submit']")
    private WebElement submit;

    @FindBy(css = ".profile-photo")
    private SelenideElement userName;

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

    //=====================Methods==================

    @Step
    public void login(Users user) {
        profileButton.click();
        login.sendKeys(user.login);
        password.sendKeys(user.password);
        submit.click();
    }

    @Step
    public void openPage() {
        open("https://epam.github.io/JDI/index.html");
    }

    @Step
    public void clickHeaderService() {
        headerService.click();
    }

    @Step
    public void clickLeftMenuService() {
        leftService.click();
    }

    @Step
    public void openPageDifferentElements() {
        servicePage.click();
    }

    @Step
    public void openDataPage() {
        datePage.click();
    }

    //====================Checks================

    @Step("Check user name {0}")
    public void checkUserName(Users user) {
        userName.shouldHave(text(user.name));
    }

    @Step
    public void checkHeaderMenuService() {
        menuServiceHeader.shouldHaveSize(values().length);
        menuServiceHeader.shouldHave(exactTexts(getNames()));
    }

    @Step
    public void checkLeftMenuService() {
        menuService.shouldHaveSize(values().length);
        menuService.shouldHave(exactTexts(getNames()));
    }
}
