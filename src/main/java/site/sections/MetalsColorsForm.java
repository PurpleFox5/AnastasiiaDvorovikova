package site.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import com.epam.jdi.uitests.web.selenium.elements.complex.ComboBox;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Selector;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JComboBox;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import entries.*;
import org.openqa.selenium.support.FindBy;

public class MetalsColorsForm extends Form<Data> {

    @FindBy(css = "#odds-selector p")
    public Selector<Odds> odds;
    @FindBy(css = "#even-selector p")
    public Selector<Even> even;

    @FindBy(css = "#elements-checklist p")
    public CheckList<Elements> elements;

    @JDropdown(
            root = @FindBy(css = ".colors"),
            list = @FindBy(tagName = "li"),
            value = @FindBy(css = ".filter-option")
    )
    public Dropdown<Colors> color;

    @JComboBox(
            root = @FindBy(css = ".metals"),
            list = @FindBy(tagName = "li"),
            value = @FindBy(css = "input")
    )
    public ComboBox<Metals> metals;

    @JDropdown(
            root = @FindBy(id = "salad-dropdown"),
            list = @FindBy(tagName = "li"),
            value = @FindBy(tagName = "button")
    )
    public Dropdown<Vegetables> vegetables;


    @Css("#submit-button")
    public IButton submit;

    public void submit(Data data) {
        odds.select(data.summaries.get(0));
        even.select(data.summaries.get(1));

        for (Elements element : data.elements) {
            elements.select(element);
        }

        color.select(data.color);

        metals.setValue(data.metals.toString());

        vegetables.select(Vegetables.VEGETABLES);
        for (Vegetables vegetable : data.vegetables) {
            vegetables.select(vegetable);
        }

        submit.click();
    }

}
