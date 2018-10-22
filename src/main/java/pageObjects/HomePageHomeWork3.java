package pageObjects;

import base.TestBaseHW;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageHomeWork3 extends TestBaseHW {

    @FindBy(css = ".profile-photo")
    private WebElement profileButton;

    @FindBy(css = "[id = 'Name']")
    private WebElement login;

    @FindBy(css = "[id = 'Password']")
    private WebElement password;

    @FindBy(css = ".login [type = 'submit']")
    private WebElement submit;

    @FindBy(css = ".profile-photo")
    private WebElement profileName;

    @FindBy(css = ".m-l8 > li > a")
    private List<WebElement> headerSections;

    @FindBy(css = ".icons-benefit")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> txtElements;

    @FindBy(css = "h3.main-title")
    private WebElement mainHeader;

    @FindBy(css = ".main-txt")
    private WebElement mainTxt;

    @FindBy(css = "iframe")
    private WebElement iframe;

    @FindBy(css = ".epam-logo")
    private WebElement logo;

    @FindBy(css = ".uui-side-bar")
    private WebElement leftSection;

    @FindBy(css = ".footer-bg")
    private WebElement footer;

    @FindBy(xpath = "//a[text()='JDI Github']")
    private WebElement subHeader;

    //========================Methods======================

    public void open(WebDriver driver) {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void login(String name, String passwd) {
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(passwd);
        submit.click();
    }

    public void switchToIframe(WebDriver driver) {
        driver.switchTo().frame("iframe");
    }

    public void switchToOriginalWindow(WebDriver driver) {
        driver.switchTo().parentFrame();
    }

    public void close(WebDriver driver) {
        driver.close();
    }

    //=======================Asserts=========================

    public void checkProfileName() {
        assertEquals(profileName.getText(), "PITER CHAILOVSKII");
    }

    public void checkTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkHeaderSections() {
        assertEquals(headerSections.size(), COUNT);
        for (WebElement element : headerSections) {
            assertTrue(headerSectionsList.contains(element.getText()));
        }
    }

    public void checkImages() {
        assertEquals(images.size(), COUNT);
        assertTrue(images.stream().allMatch(WebElement::isDisplayed));
    }

    public void checkTextUnderPictures() {
        assertEquals(txtElements.size(), COUNT);
        for (WebElement element : txtElements) {
            assertTrue(texts.contains(element.getText()));
        }
    }

    public void checkMainHeader() {
        assertEquals(mainHeader.getText(), "EPAM FRAMEWORK WISHES…");
        assertEquals(mainTxt.getText(), MAIN_TEXT);
    }

    public void checkIframe() {
        assertTrue(iframe.isDisplayed());
    }

    public void checkLogo() {
        assertTrue(logo.isDisplayed());
    }

    public void checkLeftSection() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooter() {
        assertTrue(footer.isDisplayed());
    }

    public void checkSubHeader() {
        assertEquals(subHeader.getText(), "JDI GITHUB");
    }

    public void checkSubHeaderLink() {
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");
    }


}
