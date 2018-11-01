package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.CheckBoxes;
import enums.RadioButtons;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static enums.CheckBoxes.values;

public class DifferentElementsPage extends BasicPage {

    @FindBy(xpath = "//select//option[text()='Yellow']")
    private SelenideElement selectOption;

    @FindBy(css = ".right-fix-panel")
    private SelenideElement rightSection;

    @FindBy(css = "._mCS_1")
    private SelenideElement leftSection;

    @FindBy(css = ".logs")
    private SelenideElement logs;

    @FindBy(css = ".checkbox-row>.label-checkbox")
    private ElementsCollection checkBoxes;

    @FindBy(css = ".checkbox-row>.label-radio")
    private ElementsCollection radioButtons;

    @FindBy(css = ".main-content-hg>.uui-button")
    private ElementsCollection buttons;

    @FindBy(css = "select")
    private SelenideElement dropDown;

    //===============Methods==========================

    @Step
    public void selectCheckBoxes(CheckBoxes... items) {
        for (CheckBoxes checkBox : items) {
            SelenideElement element = checkBoxes.find(text(checkBox.toString()));
            element.click();
        }
    }

    @Step
    public void selectRadio(RadioButtons... items) {
        for (RadioButtons radioButton : items) {
            SelenideElement element = radioButtons.find(text(radioButton.toString()));
            element.click();
        }
    }

    @Step
    public void selectInDropdown() {
        selectOption.click();
    }

    @Step
    public void unselectCheckBoxes(CheckBoxes... items) {
        selectCheckBoxes(items);
    }

    //===============Check===========================

    @Step
    public void checkVisibleCheckBoxes() {
        checkBoxes.shouldHaveSize(values().length);
        for (SelenideElement element : checkBoxes) {
            element.shouldBe(visible);
        }
    }

    @Step
    public void checkVisibleRadioButtons() {
        radioButtons.shouldHaveSize(RadioButtons.values().length);
        for (SelenideElement element : radioButtons) {
            element.shouldBe(visible);
        }
    }

    @Step
    public void checkDropDown() {
        dropDown.shouldBe(visible);
    }

    @Step
    public void checkButtons() {
        buttons.shouldHaveSize(2);
        for (SelenideElement element : buttons) {
            element.shouldBe(visible);
        }
    }

    @Step
    public void checkRightSection() {
        rightSection.should(visible);
    }

    @Step
    public void checkLeftSection() {
        leftSection.should(visible);
    }

    @Step
    public void checkCheckBoxes(CheckBoxes... items) {
        for (CheckBoxes checkBox : items) {
            SelenideElement element = checkBoxes.find(text(checkBox.toString())).$("input");
            element.shouldBe(Condition.checked);
        }
    }

    @Step
    public void checkLogs(String... strings) {
        List<SelenideElement> logsList = logs.$$("li");
        List<SelenideElement> sublist = logsList.subList(0, strings.length);
        int index = strings.length - 1;
        for (String s : strings) {
            sublist.get(index).should(text(s));
            index--;
        }
    }

    @Step
    public void checkRadioButton(RadioButtons... items) {
        for (RadioButtons radioButton : items) {
            SelenideElement element = radioButtons.find(text(radioButton.toString())).$("input");
            element.shouldBe(selected);
        }
    }

    @Step
    public void checkSelectOption() {
        selectOption.should(selected);
    }

    @Step
    public void checkUnselectCheckBoxes(CheckBoxes... items) {
        for (CheckBoxes checkBox : items) {
            SelenideElement element = checkBoxes.find(text(checkBox.name())).$("input");
            element.shouldNotBe(checked);
        }
    }

}
