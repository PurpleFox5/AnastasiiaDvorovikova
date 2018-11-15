package site.pages;

import com.epam.jdi.uitests.web.selenium.elements.complex.TextList;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import entries.MetalsColorsData;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import site.sections.MetalsColorsForm;

import static org.testng.AssertJUnit.assertEquals;

public class MetalColorPage extends WebPage {

    public MetalsColorsForm metalsColorsForm;

    @FindBy(css = ".results")
    public TextList<Enum> resultsLog;

    @Step("Check result sections")
    public void checkExample(MetalsColorsData data) {
        int sum = Integer.parseInt(data.summaries.get(0)) + Integer.parseInt(data.summaries.get(1));

        String expectedResult = "[Summary: " + sum + "\n" +
                "Elements: " + data.elements.toString().substring(1, data.elements.toString().length() - 1) + "\n" +
                "Color: " + data.color + "\n" +
                "Metal: " + data.metals + "\n" +
                "Vegetables: " + data.vegetables.toString().substring(1, data.vegetables.toString().length() - 1) + "]";

        assertEquals(resultsLog.getTextList().toString(), expectedResult);
    }
}
