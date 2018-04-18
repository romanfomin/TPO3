package seleniumTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    SeleniumUtils utils = new SeleniumUtils();

    @Test
    public void testChangeLanguage() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//img[@src=\"/img/globe.svg\"]//parent::a")).click();
            driver.findElement(By.xpath("//ul/li[3]/a[@href=\"/ru\"]")).click();
            assertEquals("О Компании", driver.findElement(By.xpath("//a[contains(@href,\"/about\")]")).getText());
        }
    }

    @Test
    public void testAboutUs() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"/about\")]")).click();
            assertEquals("The Acesse Story", driver.findElement(By.xpath("//div[@class=\"col-sm-5\"]/h2")).getText());
        }
    }

    @Test
    public void testContact() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"/contact\")]")).click();
            assertEquals("Minneapolis Corporate Office", driver.findElement(By.xpath("//div[@class=\"col-sm-6\"]/h3")).getText());
        }
    }

    @Test
    public void testDocs1() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(text(),\"Legal Documents\")]")).click();
            driver.findElement(By.xpath("//span[contains(text(),\"Terms of use\")]//parent::a")).click();
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains("win_ser_1")) {
                    assertEquals("WEBSITE TERMS OF USE", driver.findElement(By.xpath("//div[@class=\"textLayer\"]/div[2]")).getText());
                }
            }
        }
    }

}
