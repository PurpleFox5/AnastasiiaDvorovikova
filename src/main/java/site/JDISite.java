package site;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.complex.TextList;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import entries.MetalsColorsData;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import site.pages.HomePageJDI;
import site.pages.MetalColorPage;
import site.sections.LoginForm;
import site.sections.MetalsColorsForm;

import static com.epam.web.matcher.testng.Assert.assertEquals;

@JSite("https://epam.github.io/JDI/")
public class JDISite extends WebSite {

    @JPage(url = "/index.html")
    public static HomePageJDI homePage;

    @FindBy(css = ".uui-profile-menu")
    public static LoginForm login;

    @JPage(url = "/metals-colors.html", title = "Metal and Colors")
    public static MetalColorPage metalColorPage;

    // TODO This should be encapsulate in Page
    public static MetalsColorsForm metalsColorsForm;

    @FindBy(css = ".nav")
    public static Menu navigation;

    @FindBy(css = ".results")
    public static TextList<Enum> resultsLog;
    // !TODO

    @Step("Open Metals & Colors page by Header menu")
    public static void openMetalColor() {
        navigation.select("Metals & Colors");
    }

    // TODO And this
    @Step("Check result sections")
    public static void checkExample(MetalsColorsData data) {
        int sum = Integer.parseInt(data.summaries.get(0)) + Integer.parseInt(data.summaries.get(1));

        String expectedResult = "[Summary: " + sum + "\n" +
                "Elements: " + data.elements.toString().substring(1, data.elements.toString().length() - 1) + "\n" +
                "Color: " + data.color + "\n" +
                "Metal: " + data.metals + "\n" +
                "Vegetables: " + data.vegetables.toString().substring(1, data.vegetables.toString().length() - 1) + "]";

        assertEquals(resultsLog.getTextList().toString(), expectedResult);
    }
}
