package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static String capture(String testName) {
        try {
            if (driver == null) {
                return null;
            }

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            File dest = new File("screenshots/" + testName + "_" + timestamp + ".png");

            FileUtils.copyFile(src, dest);
            return dest.getPath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
