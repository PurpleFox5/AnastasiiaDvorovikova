package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Then;
import enums.CheckBoxes;
import enums.RadioButtons;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.CheckBoxes.values;
import static org.testng.Assert.assertEquals;

public class DifferentElementsPageCucumber {

    public DifferentElementsPageCucumber() {
        page(this);
    }

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

    @Then("I select checkboxes (.+)")
    @Step
    public void selectCheckBoxes(String value) {
        Pattern pattern = Pattern.compile(",");
        String[] values = pattern.split(value);
        for (String s : values) {
            SelenideElement element = checkBoxes.find(text(s));
            element.click();
        }
    }

    @Then("I select radio (.+)")
    @Step
    public void selectRadio(String value) {
        radioButtons.find(text(value)).click();
    }

    @Then("I select in dropdown Yellow")
    @Step
    public void selectInDropdown() {
        selectOption.click();
    }

    @Then("I unselect checkboxes (.+)")
    @Step
    public void unselectCheckBoxes(String value) {
        selectCheckBoxes(value);
    }

    //===============Check===========================

    @Then("The title is Different Elements$")
    @Step
    public void checkTitle() {
        assertEquals(getWebDriver().getTitle(), "Different Elements");
    }

    @Then("There are 4 checkboxes")
    @Step
    public void checkVisibleCheckBoxes() {
        checkBoxes.shouldHaveSize(values().length);
        for (SelenideElement element : checkBoxes) {
            element.shouldBe(visible);
        }
    }

    @Then("There are 4 radioButtons")
    @Step
    public void checkVisibleRadioButtons() {
        radioButtons.shouldHaveSize(RadioButtons.values().length);
        for (SelenideElement element : radioButtons) {
            element.shouldBe(visible);
        }
    }

    @Then("There is 1 dropdown")
    @Step
    public void checkDropDown() {
        dropDown.shouldBe(visible);
    }

    @Then("There are 2 buttons")
    @Step
    public void checkButtons() {
        buttons.shouldHaveSize(2);
        for (SelenideElement element : buttons) {
            element.shouldBe(visible);
        }
    }

    @Then("There is Right Section")
    @Step
    public void checkRightSection() {
        rightSection.should(visible);
    }

    @Then("There is Left Section")
    @Step
    public void checkLeftSection() {
        leftSection.should(visible);
    }

    @Then("Checkboxes (.+) are checked")
    @Step
    public void checkCheckBoxes(String value) {
        Pattern pattern = Pattern.compile(",");
        String[] values = pattern.split(value);
        for (String s : values) {
            SelenideElement element = checkBoxes.find(text(s)).$("input");
            element.shouldBe(Condition.checked);
        }
    }

    @Then("Check logs (.+)")
    @Step
    public void checkLog(String value) {
        Pattern pattern = Pattern.compile(",");
        String[] values = pattern.split(value);
        List<SelenideElement> logsList = logs.$$("li");
        List<SelenideElement> sublist = logsList.subList(0, values.length);
        int index = values.length - 1;
        for (String s : values) {
            sublist.get(index).should(text(s));
            index--;
        }
    }

    @Then("Radiobutton (.+) is selected")
    @Step
    public void checkRadioButton(String value) {
         radioButtons.find(text(value)).$("input").shouldBe(selected);
    }

    @Then("Dropdown Yellow is selected")
    @Step
    public void checkSelectOption() {
        selectOption.should(selected);
    }

    @Then("Checkboxes (.+) are unchecked")
    @Step
    public void checkUnselectCheckBoxes(String value) {
        Pattern pattern = Pattern.compile(",");
        String[] values = pattern.split(value);
        for (String s : values) {
            SelenideElement element = checkBoxes.find(text(s)).$("input");
            element.shouldNotBe(checked);
        }
    }

}
