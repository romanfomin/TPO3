package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumUtils {

    private WebDriver firefoxDriver;

    private WebDriver chromeDriver;
    private String baseUrl = "http://acesse.com/en";

    private List<WebDriver> webDrivers = new ArrayList<>();

    public SeleniumUtils(){
        System.setProperty("webdriver.gecko.driver","/home/fomin/drivers/geckodriver-v0.20.1-linux64/geckodriver");
        System.setProperty("webdriver.chrome.driver","/home/fomin/drivers/chromedriver_linux64/chromedriver");
        chromeDriver = new ChromeDriver();
        firefoxDriver=new FirefoxDriver();
        webDrivers.add(firefoxDriver);
        webDrivers.add(chromeDriver);
        for (WebDriver webDriver : webDrivers) {
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            webDriver.get(baseUrl);
        }
    }

    public WebDriver getFirefoxDriver() {
        return firefoxDriver;
    }

    public void setFirefoxDriver(WebDriver firefoxDriver) {
        this.firefoxDriver = firefoxDriver;
    }

    public WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<WebDriver> getWebDrivers() {
        return webDrivers;
    }

    public void setWebDrivers(List<WebDriver> webDrivers) {
        this.webDrivers = webDrivers;
    }
}
