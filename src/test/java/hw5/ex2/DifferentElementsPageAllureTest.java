package hw5.ex2;

import base.PageTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DifferentElementsPage;
import pageObjects.HomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.CheckBoxes.*;
import static enums.LogsLines.*;
import static enums.RadioButtons.SELEN;
import static enums.Titles.HOME_PAGE;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("HomeWork5 tests")
@Story("Service Page Testing")
@Listeners(AllureListener.class)
public class DifferentElementsPageAllureTest extends PageTestBase {

    private HomePage homePage;
    private DifferentElementsPage differentElements;

    @BeforeClass
    public void beforeClass() {
        differentElements = page(DifferentElementsPage.class);
        homePage = page(HomePage.class);
    }

    @Test
    public void differentElementsTest() {
        //1. Open test site by URL
        homePage.openPage();

        //2. Assert Browser title
        homePage.checkTitle(HOME_PAGE);

        //3. Perform login
        homePage.login(PITER_CHAILOVSKII);

        //4. Assert User name in the left-top side of screen that user is logged
        homePage.checkUserName(PITER_CHAILOVSKII);

        //5. Click on "Service" subcategory in the header and check that drop down contains options
        homePage.clickHeaderService();
        homePage.checkHeaderMenuService();

        //6. Click on Service subcategory in the left section and check that drop down contains options
        homePage.clickLeftMenuService();
        homePage.checkLeftMenuService();

        //7. Open through the header menu Service -> Different Elements Page
        homePage.clickHeaderService();
        homePage.openPageDifferentElements();
        differentElements.checkTitle(HOME_PAGE);

        //8. Check interface on Different elements page, it contains all needed elements
        differentElements.checkVisibleCheckBoxes();
        differentElements.checkVisibleRadioButtons();
        differentElements.checkDropDown();
        differentElements.checkButtons();

        //9. Assert that there is Right Section
        differentElements.checkRightSection();

        //10. Assert that there is Left Section
        differentElements.checkLeftSection();

        //11. Select checkboxes
        differentElements.selectCheckBoxes(WATER, EARTH);
        differentElements.checkCheckBoxes(WATER, WIND);

        //12. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox. 
        differentElements.checkLogs(WATER + CHECHBOX_TRUE.toString(),
                WIND + CHECHBOX_TRUE.toString());

        //13. Select radio
        differentElements.selectRadio(SELEN);
        differentElements.checkRadioButton(SELEN);

        //14. Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        differentElements.checkLogs(RADIO_CHANGE.toString() + SELEN);

        //15. Select in dropdown
        differentElements.selectInDropdown();
        differentElements.checkSelectOption();

        //16. Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        differentElements.checkLogs(DROPDOWN.toString());

        //17. Unselect and assert checkboxes
        differentElements.unselectCheckBoxes(WATER, WIND);
        differentElements.checkUnselectCheckBoxes(WATER, WIND);

        //18. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox. 
        differentElements.checkLogs(WATER.toString() + CHECHBOX_FALSE,
                WIND.toString() + CHECHBOX_FALSE);
    }
}
