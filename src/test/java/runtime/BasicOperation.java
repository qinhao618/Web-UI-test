package runtime;

import org.junit.rules.ExpectedException;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BasicOperation {

    WebDriver driver;

    public BasicOperation(WebDriver driver) {
        this.driver = driver;
    }

    public void injectJQuery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String injection = "var scriptElt = document.createElement('script'); \n" +
                "scriptElt.type = 'text/javascript'; \n" +
                "scriptElt.src = '" + LoadConfig.loadEnvProp("jqueryCDN") + "'; \n" +
                "document.getElementsByTagName('head')[0].appendChild(scriptElt);";
        js.executeScript(injection);
        this.sleepTimeout("interval");
    }


    public List<WebElement> waitFor(Supplier<List<WebElement>> action, Predicate<List<WebElement>> checker,
            String error_template) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < Long.parseLong("5") * 1000) {
            List<WebElement> elements = action.get();
            if (checker.test(elements)) {
                return elements;
            }
            sleepTimeout("interval");
        }
        throw new AssertionError(error_template);
    }

    public Object waitForText(Supplier<Object> action, Predicate<Object> checker, String error_template) {
        long start = System.currentTimeMillis();
        Object value;
        while (System.currentTimeMillis() - start < Long.parseLong("15") * 1000) {
            value = action.get();
            if (checker.test(value)) {
                return value;
            }
            sleepTimeout("interval");
        }
        throw new AssertionError(error_template);
    }

    public Object getFromByLocation(String script, Predicate<Object> verify) {
        Supplier<Object> action = () -> driver.findElements(By.cssSelector(script));

        return waitForText(action, verify, "Element not found!");
    }

    public void sleepTimeout(String time) {
        try {
            Thread.sleep(Long.parseLong("1") * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> findElementsByScript(String script) {
        this.injectJQuery();
        final String actualScript = script.replace("$", "jQuery").concat(".toArray()");
        System.out.printf("scrpit================"+actualScript);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Supplier<List<WebElement>> action = () -> (List<WebElement>) js.executeScript(actualScript);
        Predicate<List<WebElement>> checker = elements -> elements.size() > 0 && elements.get(0).isEnabled();
        return waitFor(action, checker, "Element not found!");
    }

    public WebElement findElement(String script) {
        Supplier<List<WebElement>> action = () -> driver.findElements(By.cssSelector(script));
        Predicate<List<WebElement>> checker = elements -> elements.size() > 0 && elements.get(0).isEnabled();
        return waitFor(action, checker, "Element not found!").get(0);
    }

    public void inputText(String location, String text) {
        this.findElement(location).sendKeys(text);
    }

    private List<WebElement> getElements(Object results) {
        if (results instanceof List) {
            return (List<WebElement>) results;
        } else if (results instanceof Map) {
            return (List<WebElement>) ((Map) (results)).values().stream().filter(o -> o instanceof WebElement)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException();
    }

    public ExpectedException exceptionRule = ExpectedException.none();

    public boolean notExistElement(String script) {
        return this.findElementsByScript(script).isEmpty();

    }

    public void clickButton(String location) {
        this.findElement(location).click();
    }

    public void scrollDownByPixel(int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+ pixel + ")");
    }
}
