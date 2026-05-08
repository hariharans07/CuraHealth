package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.HistoryPage;
import pages.LoginPage;

import java.util.List;
import org.openqa.selenium.WebElement;

public class HistoryTest extends BaseTest {

    // 🔐 Common Login Method
    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");
    }

    // 📅 Helper to Book Appointment
    public void bookAppointment(String facility, String date, String comment) {
        AppointmentPage ap = new AppointmentPage(driver);
        ap.bookAppointment(facility, date, comment, false);
    }

    // ✅ TC_HIST_01 – Verify History Page Loads
    @Test(priority = 1)
    public void historyPageLoadTest() {

        login();

        HistoryPage hp = new HistoryPage(driver);
        hp.goToHistory();

        Assert.assertTrue(hp.isHistoryPageDisplayed(),
                "History page is NOT displayed");
    }

    // ✅ TC_HIST_02 – Verify Latest Appointment Appears
    @Test(priority = 2)
    public void latestAppointmentTest() {

        login();

        // Book appointment first
        bookAppointment("Tokyo CURA Healthcare Center",
                "18/05/2026",
                "Checkup Test");

        HistoryPage hp = new HistoryPage(driver);
        hp.goToHistory();

        String latest = hp.getLatestAppointmentDetails();

        Assert.assertTrue(latest.contains("Tokyo CURA Healthcare Center"),
                "Facility not found in latest appointment");

        Assert.assertTrue(latest.contains("18/05/2026"),
                "Date not found in latest appointment");
    }

    // ✅ TC_HIST_03 – Verify Table Headers (FULL VALIDATION)
    @Test(priority = 3)
    public void verifyHistoryFieldLabelsTest() {

        login();

        bookAppointment("Seoul CURA Healthcare Center",
                "19/05/2026",
                "Header validation");

        HistoryPage hp = new HistoryPage(driver);
        hp.goToHistory();

        List<WebElement> headers = hp.getHeaders();

        // Expected headers
        String[] expectedHeaders = {
                "Facility",
                "Apply for hospital readmission",
                "Healthcare Program",
                "Comment"
        };

        // Validate each header
        for (String expected : expectedHeaders) {

            boolean found = false;

            for (WebElement header : headers) {
                if (header.getText().replace(":", "").trim().equalsIgnoreCase(expected)) {
                    found = true;
                    break;
                }
            }

            Assert.assertTrue(found, "Missing header: " + expected);
        }

        Assert.assertTrue(hp.getLatestAppointmentDetails().contains("19/05/2026"),
                "Visit date is missing from history appointment");
    }
}
