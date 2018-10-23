package pageObjects;

import base.ServicePageTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_SHAILOVSKII;
import static org.testng.Assert.assertEquals;

public class ServicePage extends ServicePageTestBase {

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

    @FindBy(xpath = "//li[@class='menu-title']//span[text()='Service']")
    private SelenideElement leftService;

    @FindBy(xpath = "//li[@class='dropdown']")
    private SelenideElement headerService;

    private SelenideElement checkBoxWater;
    private SelenideElement checkBoxWind;
    private SelenideElement radioSelen;
    private SelenideElement selectOption;

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

    public void openPageDifferentElements(){
        headerService.click();
        $(By.xpath("//ul[@class='dropdown-menu']/child::li[contains(.,'Different elements')]")).click();
    }

    public void selectCheckBoxes(){
        checkBoxWater = $(By.xpath("//*[contains(.,'Water')]/child::input[@type='checkbox']"));
        checkBoxWater.click();
        checkBoxWind = $(By.xpath("//*[contains(.,'Wind')]/child::input[@type='checkbox']"));
        checkBoxWind.click();
    }

    public void selectRadio(){
        radioSelen = $(By.xpath("//*[contains(.,'Selen')]/child::input[@type='radio']"));
        radioSelen.click();
    }

    public void selectInDropdown(){
        selectOption=$(By.xpath("//select//option[text()='Yellow']"));
        selectOption.click();
    }

    public void unselectCheckBoxes(){
        checkBoxWater.click();
        checkBoxWind.click();
    }

    //===============Check===========================

    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    public void checkUserName() {
        userName.shouldHave(Condition.text(PITER_SHAILOVSKII.name));
    }

    public void checkHeaderMenuService() {
        headerService.click();
        List<SelenideElement> menuService = $$(By.xpath("//ul[@class='dropdown-menu']//child::li"));
        for (int i = 0; i < menuService.size();i++) {
            menuService.get(i).shouldHave(Condition.text(menuServiceList.get(i)));
        }
    }

    public void checkLeftMenuService() {
        leftService.click();
        List<SelenideElement> menuService = $$(By.xpath("//*[@class='sub']/child::li"));
        for (int i = 0; i < menuService.size();i++) {
            menuService.get(i).shouldHave(Condition.text(menuServiceList.get(i)));
        }
        leftService.click();
    }

    public void checkInterfaceOnDifferentElementsPage(){
        $$("[type = checkbox]").shouldHaveSize(4);
        $$("[type=radio]").shouldHaveSize(4);
        $$("select").shouldHaveSize(1);
        $$("[type=button]").shouldHaveSize(1);

    }

    public void checkRightSection(){
        $(".right-fix-panel").should(Condition.visible);
    }

    public void checkLeftSection(){
        $("._mCS_1").should(Condition.visible);
    }

    public void checkCheckBoxes(){
        checkBoxWater.should(Condition.selected);
        checkBoxWind.should(Condition.selected);
    }

    public void checkLogs(String s){
        logs = $(".logs");
        logs.should(Condition.text(s));
    }

    public void checkRadioSelen(){
        radioSelen.should(Condition.selected);
    }

    public void checkselectOption(){
        selectOption.should(Condition.selected);
    }

    public void checkUnselectCheckBoxes(){
        checkBoxWater.shouldNot(Condition.selected);
        checkBoxWind.shouldNot(Condition.selected);
    }


}
