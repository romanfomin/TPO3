package seleniumTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    SeleniumUtils utils = new SeleniumUtils();

    @Test
    public void testChangeLanguage() throws Exception {
        for(WebDriver driver: utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//img[@src=\"/img/globe.svq\"]//parent::a")).click();
            driver.findElement(By.xpath("//ul/li[3]/a[@href=\"/ru\"]")).click();
            assertEquals("О Компании", driver.findElement(By.xpath("//a[contains(@href,\"/about\")]")).getText());
        }
    }

}
