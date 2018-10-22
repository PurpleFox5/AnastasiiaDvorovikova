package lesson4;

import base.SelenideTestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTestSelenide extends SelenideTestBase {
    @Test
    public void simpleTest() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //2. Navigate
        open("https://epam.github.io/JDI/index.html");

        //3. Assert
        assertEquals(getWebDriver().getTitle(), "Home Page");

        //4.Login
        $(".profile-photo").click();
        $("[id = 'Name']").sendKeys("epam");
        $("[id = 'Password']").sendKeys("1234");
        $(".login [type = 'submit']").click();

        //5. Проверка значения текста
        SelenideElement mainTitle = $("h3.main-title");
        mainTitle.shouldHave(text("EPAM FRAMEWORK WISHES…"));

        //Check 4
        $$(By.xpath("")).shouldHaveSize(4);
        $$(By.xpath("")).shouldBe(CollectionCondition.sizeLessThan(5));



    }
}
