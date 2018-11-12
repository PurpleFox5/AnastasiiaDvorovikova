package site.pages;

import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import enums.User;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;

public class HomePageJDI extends WebPage {

    @FindBy(css = ".profile-photo")
    private SelenideElement userName;

    @Step
    public void checkUserName(User user) {
        userName.shouldHave(text(user.fullName));
    }

}
