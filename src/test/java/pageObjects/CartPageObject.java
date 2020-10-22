package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import runtime.LoadConfig;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CartPageObject extends BasePage {

    public CartPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputPaybyID(String id) {
       String inputPaybyID="input[name=\"paybyID\"]";
        basicOperation.inputText(inputPaybyID,id);
    }
    public void inputAmount(String amount){

        String inputAmount = "input[name=\"paymentAmount\"]";
        basicOperation.inputText(inputAmount,amount);
    }

    public  void clickSubmitButton(String button){
        String submitButton = "button.btn-primary";
        basicOperation.clickButton(submitButton);
    }

    public  void  googleTest(String keywords){
        basicOperation.inputText("input[name=\"q\"]",keywords+ Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("div")));
        System.out.println(firstResult.getAttribute("textContent"));


    }


}
