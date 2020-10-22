package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runtime.BasicOperation;
import runtime.LoadConfig;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver webDriver;

    protected BasicOperation basicOperation;

    protected WebDriverWait wait;

    protected String currentURL = "";

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.basicOperation = new BasicOperation(webDriver);
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {

        if (url.contains("http")) {
            webDriver.navigate().to(url);
            basicOperation.injectJQuery();
        } else {
            webDriver.navigate().to(LoadConfig.loadEnvProp(url));
        }
    }

    public void refresh() {
        webDriver.navigate().refresh();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void waitToBeShown(By by) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
