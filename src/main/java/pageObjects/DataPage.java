package pageObjects;

import base.DataPageTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.Users;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.LogsLines.*;
import static enums.Users.PITER_SHAILOVSKII;
import static org.testng.Assert.assertEquals;

public class DataPage extends DataPageTestBase {
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

    public void openPage() {
        open("https://epam.github.io/JDI/");
    }

    public void login(Users user) {
        profileButton.click();
        login.sendKeys(user.login);
        password.sendKeys(user.password);
        submit.click();
    }

    public void openPageDifferentElements() {
        headerService.click();
        datePage.click();
    }

    public void moveRightSliders(int index) {
        int width = sliderTrack.getSize().width;
        Actions move = new Actions(getWebDriver());
        int xOffset1Right = getSliderPosition(width, rightSlider, index);
        move.dragAndDropBy(rightSlider, xOffset1Right, 0).build().perform();
    }

    public void moveLeftSlider(int index) {
        int width = sliderTrack.getSize().width;
        Actions move = new Actions(getWebDriver());
        int xOffsetLeft = getSliderPosition(width, leftSlider, index);
        move.dragAndDropBy(leftSlider, xOffsetLeft, 0).build().perform();
    }

    //===============Check===========================

    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    public void checkUserName() {
        userName.shouldHave(Condition.text(PITER_SHAILOVSKII.name));
    }

    public void checkLogs(int from, int to) {
        logs.should(Condition.text(RANGE2_FROM.line + from + SLIDER.line));
        logs.should(Condition.text(RANGE2_TO.line + to + SLIDER.line));
    }
}