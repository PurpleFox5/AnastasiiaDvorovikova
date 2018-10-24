package pageObjects;

import base.DataPageTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.LogsLines.*;
import static enums.Users.PITER_SHAILOVSKII;
import static org.testng.Assert.assertEquals;

public class DataPageAllure extends DataPageTestBase {
    @FindBy(css = "h3.main-title")
    private SelenideElement mainTitle;

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

    @FindBy(xpath = "//li[@class='dropdown']")
    private SelenideElement headerService;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/child::li[contains(.,'Dates')]")
    private SelenideElement datePage;

    @FindBy(className = "ui-slider")
    private SelenideElement sliderTrack;

    @FindBy(css = "a.ui-corner-all:first-of-type")
    private SelenideElement leftSlider;

    @FindBy(css = "a.ui-corner-all:last-of-type")
    private SelenideElement rightSlider;

    @FindBy(css = ".logs")
    private SelenideElement logs;

    //===============Methods==========================

    @Step
    public void openPage() {
        open("https://epam.github.io/JDI/");
    }

    @Step
    public void login(Users user) {
        profileButton.click();
        login.sendKeys(user.login);
        password.sendKeys(user.password);
        submit.click();
    }

    @Step
    public void openPageDifferentElements() {
        headerService.click();
        datePage.click();
    }

    @Step
    public void moveRightSliders(int index) {
        int width = sliderTrack.getSize().width;
        Actions move = new Actions(getWebDriver());
        int xOffset1Right = getSliderPosition(width, rightSlider, index);
        move.dragAndDropBy(rightSlider, xOffset1Right, 0).build().perform();
    }

    @Step
    public void moveLeftSlider(int index) {
        int width = sliderTrack.getSize().width;
        Actions move = new Actions(getWebDriver());
        int xOffsetLeft = getSliderPosition(width, leftSlider, index);
        move.dragAndDropBy(leftSlider, xOffsetLeft, 0).build().perform();
    }

    //===============Check===========================

    @Step
    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    @Step
    public void checkUserName() {
        userName.shouldHave(Condition.text(PITER_SHAILOVSKII.name));
    }

    @Step
    public void checkLogs(int from, int to) {
        logs.should(Condition.text(RANGE2_FROM.line + from + SLIDER.line));
        logs.should(Condition.text(RANGE2_TO.line + to + SLIDER.line));
    }
}