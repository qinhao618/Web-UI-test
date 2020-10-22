package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pageObjects.CartPageObject;
import runtime.LoadConfig;
import runtime.SharedDriver;
import utils.TakeSnapshotAndCloseBrowser;

import java.io.IOException;

public class CartPageStepDefs {

    public CartPageObject cartPage;
    public WebDriver webDriver;

    public CartPageStepDefs() {
        this.webDriver = new SharedDriver().getDriver();
        cartPage = new CartPageObject(webDriver);
    }

    @Given("the user search {string} in google")
    public void the_user_search_in_google(String keywords) {
        cartPage.openUrl(LoadConfig.loadEnvProp("test.url"));
        cartPage.googleTest(keywords);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        TakeSnapshotAndCloseBrowser.close(scenario,webDriver);
    }

}
