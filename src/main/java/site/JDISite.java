package site;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import enums.User;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import site.pages.HomePageJDI;
import site.pages.MetalColorPage;
import site.sections.LoginForm;

@JSite("https://epam.github.io/JDI/")
public class JDISite extends WebSite {

    public static HomePageJDI homePage;
    public static MetalColorPage metalColorPage;

    public static LoginForm loginForm;

    @FindBy(css = ".profile-photo")
    public static Label profilePhoto;


    //====================Methods=================

    @Step
    public static void login() {
        profilePhoto.click();
        loginForm.loginAs(new User());
    }

    //====================Checks==================



}
