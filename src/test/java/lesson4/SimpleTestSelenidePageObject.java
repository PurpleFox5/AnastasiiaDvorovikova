package lesson4;

import base.PageTestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_SHAILOVSKII;
import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTestSelenidePageObject extends PageTestBase {


    private HomePage homePageSelenide;

    @BeforeClass
    public void beforeClass() {
        homePageSelenide = page(HomePage.class);
    }

    @Test
    public void simpleTest() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //2. Navigate
        homePageSelenide.openPage();

        //3. Assert
        assertEquals(getWebDriver().getTitle(), "Home Page");

        //4.Login
        homePageSelenide.login(PITER_SHAILOVSKII);

        //5. Проверка значения текста
        SelenideElement mainTitle = $("h3.main-title");
        mainTitle.shouldHave(text("EPAM FRAMEWORK WISHES…"));

        //Check 4
        $$(By.xpath("")).shouldHaveSize(4);
        $$(By.xpath("")).shouldBe(CollectionCondition.sizeLessThan(5));


    }
}
