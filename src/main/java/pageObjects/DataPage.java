package pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.LogsLines.*;

public class DataPage extends BasicPage {

    @FindBy(className = "ui-slider")
    private SelenideElement sliderTrack;

    @FindBy(css = "a.ui-corner-all:first-of-type")
    private SelenideElement leftSlider;

    @FindBy(css = "a.ui-corner-all:last-of-type")
    private SelenideElement rightSlider;

    @FindBy(css = ".logs")
    private SelenideElement logs;

    //===============Methods==========================

    @Step("Move slider in position {0},{1}")
    public void moveSliders(int left, int right) {
        int width = sliderTrack.getSize().width;
        Actions moveLeft = new Actions(getWebDriver());
        Actions moveRight = new Actions(getWebDriver());

        int currentRight = Integer.parseInt(rightSlider.getText());
        int currentLeft = Integer.parseInt(leftSlider.getText());

        int xOffset1Right = (int) (width / 100.0 * (right - currentRight) - 1);
        int xOffsetLeft = (int) (width / 100.0 * (left - currentLeft) - 1);

        if (left >= currentRight) {
            moveRight.dragAndDropBy(rightSlider, xOffset1Right, 0).build().perform();
            moveLeft.dragAndDropBy(leftSlider, xOffsetLeft, 0).build().perform();
        } else {
            moveLeft.dragAndDropBy(leftSlider, xOffsetLeft, 0).build().perform();
            moveRight.dragAndDropBy(rightSlider, xOffset1Right, 0).build().perform();
        }
    }

    //===============Check===========================

    @Step("Check slider`s position, it should be {0},{1}")
    public void checkLogs(int from, int to) {
        List<SelenideElement> logsList = logs.$$("li");

        if (logsList.get(0).getText().contains(RANGE2_TO.toString())) {
            logsList.get(1).should(text(RANGE2_FROM.toString() + from + SLIDER));
            logsList.get(0).should(text(RANGE2_TO.toString() + to + SLIDER));
        } else {
            logsList.get(0).should(text(RANGE2_FROM.toString() + from + SLIDER));
            logsList.get(1).should(text(RANGE2_TO.toString() + to + SLIDER));
        }
    }
}