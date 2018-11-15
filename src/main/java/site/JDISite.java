package site;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import site.pages.HomePageJDI;
import site.pages.MetalColorPage;
import site.sections.LoginForm;

@JSite("https://epam.github.io/JDI/")
public class JDISite extends WebSite {

    @JPage(url = "/index.html")
    public static HomePageJDI homePage;

    @JPage(url = "/metals-colors.html", title = "Metal and Colors")
    public static MetalColorPage metalColorPage;

    @FindBy(css = ".nav")
    public static Menu navigation;

    @Step("Open Metals & Colors page by Header menu")
    public static void openMetalColor() {
        navigation.select("Metals & Colors");
    }
}
