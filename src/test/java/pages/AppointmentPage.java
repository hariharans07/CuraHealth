package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    By facilityDropdown = By.id("combo_facility");
    By hospitalCheckbox = By.id("chk_hospotal_readmission");
    By visitDate = By.id("txt_visit_date");
    By commentBox = By.id("txt_comment");
    By bookBtn = By.id("btn-book-appointment");

    public void selectFacility(String facility) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(facilityDropdown)));
        select.selectByVisibleText(facility);
    }

    public void applyHospitalAdmission(boolean value) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(hospitalCheckbox));
        if (value != checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void enterDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(visitDate));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                dateInput,
                date
        );
    }

    public void enterComment(String comment) {
        type(commentBox, comment);
    }

    public void bookAppointment(String facility, String date, String comment, boolean admission) {
        selectFacility(facility);
        applyHospitalAdmission(admission);
        enterDate(date);
        enterComment(comment);
        click(bookBtn);
    }
    public void goToHomePage() {
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/");
    }
    public void clickBookAppointment() {
        click(bookBtn);
    }
}
