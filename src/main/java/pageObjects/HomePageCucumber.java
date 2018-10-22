package pageObjects;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;

public class HomePageCucumber {

    public HomePageCucumber(){
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


    @When("I'm on the Home Page")
    public void openPage() {
        open("https://epam.github.io/JDI/index.html");
    }

    @Then("^The browser title is Home Page$")
    public void checkTitle() {
//        assertEquals(ge.getTitle(), "Home Page");
    }

    @When("^I login as (.+) epam with password (.+)$")
    public void login(String name, String passwd){
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(passwd);
        submit.click();
    }

}

