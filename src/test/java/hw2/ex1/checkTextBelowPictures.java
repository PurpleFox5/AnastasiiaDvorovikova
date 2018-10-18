package hw2.ex1;

import dataProviders.DataProviderTextBelowPictures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class checkTextBelowPictures {

    @Test(dataProvider = "textBelowPicturesDataProviders", dataProviderClass = DataProviderTextBelowPictures.class)
    public void checkText(String text){

        //1. Open BR
        WebDriver driver = new ChromeDriver();

        //2. Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3. Assert text below pictures
        List<WebElement> txtElements = driver.findElements(By.cssSelector(".benefit-txt"));
        assertTrue(txtElements.stream().anyMatch(e -> e.getText().contains(text)));

        //4. Close Browser
        driver.close();
    }
}
