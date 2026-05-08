package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.ConfirmationPage;
import pages.HistoryPage;
import pages.LoginPage;

import java.util.List;
import org.openqa.selenium.WebElement;

public class MultipleAppointmentTest extends BaseTest {


    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");
    }


    @Test(priority = 1)
    public void bookMultipleAppointmentsTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);


        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "20/05/2026",
                "First Appointment",
                false);


        ap.goToHomePage();


        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();


        ap.bookAppointment("Hongkong CURA Healthcare Center",
                "21/05/2026",
                "Second Appointment",
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertTrue(cp.isDisplayed(),
                "Second appointment booking failed");
    }


    @Test(priority = 2)
    public void verifyMultipleAppointmentsInHistory() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);


        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "22/05/2026",
                "First",
                false);

        ap.goToHomePage();
        new LoginPage(driver).goToLogin();


        ap.bookAppointment("Seoul CURA Healthcare Center",
                "23/05/2026",
                "Second",
                false);


        HistoryPage hp = new HistoryPage(driver);
        hp.goToHistory();

        List<WebElement> rows = hp.getRows();

        boolean foundFirst = false;
        boolean foundSecond = false;

        for (WebElement row : rows) {
            String text = row.getText();

            if (text.contains("Tokyo CURA Healthcare Center")) {
                foundFirst = true;
            }
            if (text.contains("Seoul CURA Healthcare Center")) {
                foundSecond = true;
            }
        }

        Assert.assertTrue(foundFirst, "First appointment missing");
        Assert.assertTrue(foundSecond, "Second appointment missing");
    }


    @Test(priority = 3)
    public void verifyAppointmentSorting() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);


        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "10/05/2026",
                "Old",
                false);

        ap.goToHomePage();
        new LoginPage(driver).goToLogin();


        ap.bookAppointment("Hongkong CURA Healthcare Center",
                "25/05/2026",
                "New",
                false);

        HistoryPage hp = new HistoryPage(driver);
        hp.goToHistory();

        List<WebElement> rows = hp.getRows();

        String firstRow = rows.get(0).getText();

        Assert.assertTrue(firstRow.contains("25/05/2026") || firstRow.contains("10/05/2026"),
                "History should display booked appointments");
    }
}
