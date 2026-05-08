package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HistoryPage extends BasePage {

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    By menuToggle = By.id("menu-toggle");
    By historyLink = By.linkText("History");

    By historySection = By.id("history");
    By appointmentPanels = By.cssSelector("#history .panel");
    By historyEmpty = By.id("history-empty");
    By fieldLabels = By.cssSelector("#history .panel-body .col-sm-2");

    public void goToHistory() {
        click(menuToggle);
        click(historyLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(historySection));
    }

    public boolean isHistoryPageDisplayed() {
        return isDisplayed(historySection);
    }

    public List<WebElement> getHeaders() {
        return driver.findElements(fieldLabels);
    }

    public List<WebElement> getRows() {
        return driver.findElements(appointmentPanels);
    }

    public String getLatestAppointmentDetails() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appointmentPanels)).getText();
    }

    public boolean isHistoryEmpty() {
        return isDisplayed(historyEmpty);
    }
}
