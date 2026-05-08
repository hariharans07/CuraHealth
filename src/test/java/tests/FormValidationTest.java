package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.ConfirmationPage;
import pages.LoginPage;

public class FormValidationTest extends BaseTest {

    // 🔐 Login helper
    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");
    }

    // ❌ TC_FORM_01 – Empty Date Field
    @Test(priority = 1)
    public void emptyDateTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);

        ap.selectFacility("Tokyo CURA Healthcare Center");
        ap.enterComment("Test without date");

        ap.clickBookAppointment();

        String url = driver.getCurrentUrl();

        Assert.assertFalse(url.contains("confirmation"),
                "Appointment booked without date (validation failed)");
    }

    // ❌ TC_FORM_02 – Empty Login Fields
    @Test(priority = 2)
    public void emptyLoginTest() {

        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();

        lp.clickLogin(); // no username/password

        String url = driver.getCurrentUrl();

        Assert.assertTrue(url.contains("login"),
                "Login should fail with empty fields");
    }

    // ✅ TC_FORM_03 – Long Comment Input
    @Test(priority = 3)
    public void longCommentTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);

        // ✅ FIXED HERE
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("This is a very long comment ");
        }
        String longText = sb.toString();

        ap.bookAppointment("Seoul CURA Healthcare Center",
                "26/05/2026",
                longText,
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertTrue(cp.getComment().contains("This is a very long comment"),
                "Long comment not accepted properly");
    }
    // ❌ TC_FORM_04 – Invalid Date Format
    @Test(priority = 4)
    public void invalidDateFormatTest() {

        login();

        AppointmentPage ap = new AppointmentPage(driver);

        ap.bookAppointment("Tokyo CURA Healthcare Center",
                "invalid-date",
                "Wrong format",
                false);

        ConfirmationPage cp = new ConfirmationPage(driver);

        Assert.assertEquals(cp.getDate(),
                "invalid-date",
                "CURA should preserve the date text entered by the user");
    }
}
