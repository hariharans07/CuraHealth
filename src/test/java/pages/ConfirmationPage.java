package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    By heading = By.xpath("//h2[normalize-space()='Appointment Confirmation']");
    By facility = By.id("facility");
    By date = By.id("visit_date");
    By admission = By.id("hospital_readmission");
    By comment = By.id("comment");

    public boolean isDisplayed() {
        return isDisplayed(heading);
    }

    public String getFacility() {
        return text(facility);
    }

    public String getDate() {
        return text(date);
    }

    public String getAdmissionStatus() {
        return text(admission);
    }

    public String getComment() {
        return text(comment);
    }
}
