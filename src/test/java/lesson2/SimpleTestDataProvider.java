package lesson2;

import dataProviders.DataProviders;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SimpleTestDataProvider extends TestBase {

    @Test(dataProvider = "simpleDataProviders", dataProviderClass = DataProviders.class)
    public void simpleTest(String s, int i) {

        //1. Open BR
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //2. Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3. Assart
        assertEquals(driver.getTitle(), "Home Page");

        //4.Login иконка - 2 поля - кнопка
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'Name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'Password']")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();


        //Проверка значения текста
        WebElement mainTitle = driver.findElement(By.cssSelector("h3.main-title"));
        assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");

        driver.close();

        System.out.println("String:" + s);
        System.out.println("Integer:" + i);
    }
}
