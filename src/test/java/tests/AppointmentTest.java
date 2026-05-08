package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.ConfirmationPage;
import pages.LoginPage;

public class AppointmentTest extends BaseTest {

    // Helper method for login
    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");
    }

    // ✅ TC_APPT_01 – Book Appointment (Valid Data)
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

    // ✅ TC_APPT_02 – Verify Confirmation Details
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

    // ✅ TC_APPT_03 – Hospital Admission Checkbox
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

    // ❌ TC_APPT_04 – Past Date Validation
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
