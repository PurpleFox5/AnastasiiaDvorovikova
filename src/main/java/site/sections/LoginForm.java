package site.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import enums.User;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.uitests.web.selenium.elements.base.JdiStatic.$;

public class LoginForm extends Form<User> {

    @FindBy(id = "Name")
    public TextField name;
    @FindBy(id = "Password")
    public TextField password;
    @Css("[type=submit]")
    public Button enter;
    @FindBy(css = ".profile-photo")
    public Label profilePhoto;

    @Override
    public void submit(User user) {
        profilePhoto.click();
        super.submit(user);
    }

    public void logout() {
        profilePhoto.click();
        $(".logout").click();
    }
}