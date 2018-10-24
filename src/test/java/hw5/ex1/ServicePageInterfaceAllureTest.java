package hw5.ex1;

import base.ServicePageTestBase;
import enums.Users;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.ServicePageAllure;

import static com.codeborne.selenide.Selenide.page;
import static enums.LogsLines.*;

@Feature("HomeWork5 tests")
@Story("Service Page Testing")
@Listeners(AllureListener.class)
public class ServicePageInterfaceAllureTest extends ServicePageTestBase {

    private ServicePageAllure servicePage;

    @BeforeClass
    public void beforeClass() {
        servicePage = page(ServicePageAllure.class);
    }

    @Test
    public void servicePageTest() {
        //1. Open test site by URL
        servicePage.openPage();

        //2. Assert Browser title
        servicePage.checkTitle();

        //3. Perform login
        servicePage.login(Users.PITER_SHAILOVSKII);

        //4. Assert User name in the left-top side of screen that user is loggined
        servicePage.checkUserName();

        //5. Click on "Service" subcategory in the header and check that drop down contains options
        servicePage.checkHeaderMenuService();

        //6. Click on Service subcategory in the left section and check that drop down contains options
        servicePage.checkLeftMenuService();

        //7. Open through the header menu Service -> Different Elements Page
        servicePage.openPageDifferentElements();

        //8. Check interface on Different elements page, it contains all needed elements
        servicePage.checkInterfaceOnDifferentElementsPage();

        //9. Assert that there is Right Section
        servicePage.checkRightSection();

        //10. Assert that there is Left Section
        servicePage.checkLeftSection();

        //11. Select checkboxes
        servicePage.selectCheckBoxes();
        servicePage.checkCheckBoxes();

        //12. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox. 
        servicePage.checkLogs(WATER_TRUE.line);
        servicePage.checkLogs(WIND_TRUE.line);

        //13. Select radio
        servicePage.selectRadio();
        servicePage.checkRadioSelen();

        //14. Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        servicePage.checkLogs(SELEN.line);

        //15. Select in dropdown
        servicePage.selectInDropdown();
        servicePage.checkselectOption();

        //16. Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        servicePage.checkLogs(YELLOW.line);

        //17. Unselect and assert checkboxes
        servicePage.unselectCheckBoxes();
        servicePage.checkUnselectCheckBoxes();

        //18. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox. 
        servicePage.checkLogs(WATER_FALSE.line);
        servicePage.checkLogs(WIND_FALSE.line);

    }
}
