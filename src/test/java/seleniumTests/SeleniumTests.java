package seleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.SeleniumUtils;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTests {

    private SeleniumUtils utils = new SeleniumUtils();

    @AfterEach
    private void testEnd() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.quit();
        }
    }

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
                if (driver.getTitle().contains("Acesse_Terms_Of_Use")) {
                    assertEquals("WEBSITE TERMS OF USE", driver.findElement(By.xpath("//div[@class=\"textLayer\"]/div[2]")).getText());
                    break;
                }
            }
        }
    }

    @Test
    public void testLink1() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"gamesmart\")]")).click();
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains("win_ser_1")) {
                    assertEquals("GameSmart", driver.getTitle());
                    break;
                }
            }
        }
    }

    @Test
    public void testLink2() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"asports\")]")).click();
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains("win_ser_1")) {
                    assertEquals("ACESSE ESPORTS - Start playing fantasy eSports today!", driver.getTitle());
                    break;
                }
            }
        }
    }

    @Test
    public void testRegisterPage() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"signup\")]")).click();
            WebElement element = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
            element.click();
            assertTrue(driver.findElement(By.xpath("//div[contains(@class,\"alert alert-danger\")]/b")).getText().matches("^Warning You have entered an Invalid Sponsor ID[\\s\\S]*$"));
        }
    }

    @Test
    public void testLogInWithNoPassword() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"login\")]")).click();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).clear();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).sendKeys("123");
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).clear();
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).sendKeys("125");
            WebElement element = driver.findElement(By.xpath("//input[@value='Log in']"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
            element.click();
            assertEquals("You should specify this field", driver.findElement(By.xpath("//label[contains(text(),\"Password\")]//following::span[contains(@style,\"color: red\")]")).getText());
            assertEquals("Incorrect digits", driver.findElement(By.xpath("//body[@id='app-layout']/div/div/form/div[4]/span")).getText());
        }
    }

    @Test
    public void testLogInWithWrongTuring() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            driver.findElement(By.xpath("//a[contains(@href,\"login\")]")).click();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).clear();
            driver.findElement(By.xpath("//input[@name=\"Login\"]")).sendKeys("123");
            driver.findElement(By.xpath("//input[@name=\"Password\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"Password\"]")).clear();
            driver.findElement(By.xpath("//input[@name=\"Password\"]")).sendKeys("125");
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).clear();
            driver.findElement(By.xpath("//input[@name=\"turing\"]")).sendKeys("125");
            driver.findElement(By.xpath("//input[@value='Log in']")).click();
            assertEquals("Incorrect digits", driver.findElement(By.xpath("//label[contains(text(),\"Turing\")]//following::span[contains(@style,\"color: red\")]")).getText());
        }
    }

    @Test
    public void testFacebookLink() {
        for (WebDriver driver : utils.getWebDrivers()) {
            driver.get("https://acesse.com/en");
            WebElement element = driver.findElement(By.xpath("//div[contains(@class,\"soc-media\")]/ul/li[1]/a"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
            element.click();
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains("win_ser_1")) {
                    assertEquals("Facebook", driver.findElement(By.xpath("//i[contains(@class,\"fb_logo\")]/u")).getText());
                    break;
                }
            }
        }
    }

}
