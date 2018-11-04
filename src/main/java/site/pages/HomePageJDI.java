package site.pages;

import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;

@JPage(url = "/index.html", title = "Home Page")
public class HomePageJDI extends WebPage {

    @FindBy(css = ".profile-photo")
    private SelenideElement userName;

    @FindBy(xpath = "//a[text()='Metals & Colors']")
    private SelenideElement metalColors;

    @Step
    public void checkUserName() {
        userName.shouldHave(text("PITER CHAILOVSKII"));
    }

    @Step
    public void openMetalColorsPage(){
        metalColors.click();
    }
}
