package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entries.User;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.AssertJUnit.assertEquals;

public class UserTableHW6 {

    public UserTableHW6() {
        page(this);
    }

    @FindBy(css = "select")
    private ElementsCollection dropdowns;

    @FindBy(xpath = "//table//child::a")
    private ElementsCollection userNames;

    @FindBy(xpath = "//table//child::img")
    private ElementsCollection descriptionImages;

    @FindBy(xpath = "//table//child::span")
    private ElementsCollection descriptionTexts;

    @FindBy(xpath = "//table//input")
    private ElementsCollection checkBoxes;

    @FindBy(css = ".info-panel-section")
    private SelenideElement log;

    @FindBy(css = "table>tbody>tr>td")
    private ElementsCollection elements;

    private SelenideElement dropdownUser;

    private List<User> usersTable = new ArrayList<>();

    //============Methods=============

    private void prepateUserTable() {
        for (int i = 0; i < userNames.size(); i++) {
            usersTable.add(new User(elements.get(i * 4).getText(),
                    userNames.get(i).getText(),
                    descriptionTexts.get(i).getText()));
        }
    }

    @When("^I select 'vip' checkbox for \"([^\"]*)\"$")
    public void selectVip(String s) {
        Optional<String> line = usersTable.stream().filter(user -> user.getUser().equals(s)).map(User::getNumber).findFirst();
        int num = Integer.parseInt(line.get()) - 1;
        checkBoxes.get(num).click();
    }

    @When("^I click on dropdown in column Type for user \"([^\"]*)\"$")
    public void selectCheckbox(String s) {
        Optional<String> line = usersTable.stream().filter(user -> user.getUser().equals(s)).map(User::getNumber).findFirst();
        int num = Integer.parseInt(line.get()) - 1;
        dropdownUser = dropdowns.get(num);
        dropdownUser.click();
    }

    //============Checks=====================

    @Then("^\"([^\"]*)\" page is opened$")
    public void checkTitle(String s) {
        assertEquals(getWebDriver().getTitle(), s);
    }

    @And("^(\\d+) NumberType Dropdowns are displayed on Users Table on User Table Page$")
    public void countDropdowns(int count) {
        dropdowns.shouldHaveSize(count);
    }

    @And("^(\\d+) User names are displayed on Users Table on User Table Page$")
    public void countUserNames(int count) {
        userNames.shouldHaveSize(count);
    }

    @And("^(\\d+) Description images are displayed on Users Table on User Table Page$")
    public void countDescriptionImages(int count) {
        descriptionImages.shouldHaveSize(count);
    }

    @And("^(\\d+) Description texts under images are displayed on Users Table on User Table Page$")
    public void countDescriptionTexts(int count) {
        descriptionTexts.shouldHaveSize(count);
        descriptionTexts.forEach(e -> e.shouldBe(visible));
    }

    @And("^(\\d+) checkboxes are displayed on Users Table on User Table Page$")
    public void countCheckboxes(int count) {
        checkBoxes.shouldHaveSize(count);
    }

    @Then("User table contains following values:$")
    public void checkContains(DataTable dataTable) {
        prepateUserTable();
        List<User> users = dataTable.asList(User.class);
        for (int i = 0; i < users.size(); i++) {
            elements.get(i * 4).shouldHave(text(users.get(i).getNumber()));
            userNames.get(i).shouldHave(text(users.get(i).getUser()));
            descriptionTexts.get(i).shouldHave(text(users.get(i).getDescription()));
        }
    }

    @Then("^(\\d+) log row has \"([^\"]*)\" text in log section$")
    public void logRowHasTextInLogSection(int logCount, String s) {
        log.findAll("ul").shouldHaveSize(logCount);
        log.$("li").shouldHave(text(s));
    }

    @Then("^droplist contains values$")
    public void droplistContainsValues(DataTable dataTable) {
        List<String> optionList = dataTable.asList(String.class);
        ElementsCollection option = dropdownUser.findAll("option");
        for (int i = 0; i < option.size(); i++) {
            option.get(i).shouldHave(text(optionList.get(i+1)));
        }
    }
}
