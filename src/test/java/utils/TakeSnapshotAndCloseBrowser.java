package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeSnapshotAndCloseBrowser {

    public static void close(Scenario scenario, WebDriver webDriver) throws IOException {

        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + scenario.getName()+ ".png"));
            webDriver.quit();

        }
        webDriver.quit();
    }

}
