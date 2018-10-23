package pageObjects;

import base.DataPageTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
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

    @FindBy(className = "ui-slider")
    private SelenideElement sliderTrack;

    @FindBy(css = "a.ui-corner-all")
    private SelenideElement leftSlider;

    @FindBy(css = "a.ui-corner-all")
    private SelenideElement rightSlider;

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
        $(By.xpath("//ul[@class='dropdown-menu']/child::li[contains(.,'Dates')]")).click();
    }

    public void moveSliders() {
        Actions move = new Actions(getWebDriver());
        System.out.println(leftSlider.getText());
        int xOffset = getSliderPosition(sliderTrack, leftSlider, 0);
        move.dragAndDropBy(leftSlider, xOffset, 0).build().perform();
        System.out.println(leftSlider.getText());
        System.out.println(rightSlider.getText());
    }

    //===============Check===========================

    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    public void checkUserName() {
        userName.shouldHave(Condition.text(PITER_SHAILOVSKII.name));
    }

    public void checkLogs(){
        logs = $(".logs");
        logs.should(Condition.text(""));
    }

}
