package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.ConfirmationPage;
import pages.LoginPage;

public class AppointmentTest extends BaseTest {


    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");
    }


    @Test(priority = 1)
    public void bookAppointmentTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);
        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "15/05/2026",
                "Regular Checkup",
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertTrue(cp.isDisplayed(),
                "Appointment not booked successfully");
    }


    @Test(priority = 2)
    public void verifyConfirmationDetailsTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);
        ap.bookAppointment("Hongkong CURA Healthcare Center",
                "16/05/2026",
                "Dental",
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertEquals(cp.getFacility(),
                "Hongkong CURA Healthcare Center");

        Assert.assertEquals(cp.getDate(),
                "16/05/2026");
    }


    @Test(priority = 3)
    public void hospitalAdmissionTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);
        ap.bookAppointment("Seoul CURA Healthcare Center",
                "17/05/2026",
                "Surgery",
                true);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertEquals(cp.getAdmissionStatus(),
                "Yes");
    }


    @Test(priority = 4)
    public void pastDateValidationTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);
        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "01/01/2020",
                "Invalid Date Test",
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertTrue(cp.isDisplayed(),
                "CURA should accept the appointment date entered by the user");
    }
}
