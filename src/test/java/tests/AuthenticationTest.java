package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class AuthenticationTest extends BaseTest {


    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("appointment"),
                "User is not redirected to appointment page after login");
    }


    @Test(priority = 2)
    public void invalidLoginTest() {

        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("WrongUser", "WrongPass");

        String error = lp.getErrorMessage();

        Assert.assertTrue(error.contains("Login failed"),
                "Error message not displayed for invalid login");
    }


    @Test(priority = 3)
    public void logoutTest() {

        LoginPage lp = new LoginPage(driver);
        lp.goToLogin();
        lp.login("John Doe", "ThisIsNotAPassword");

        HomePage hp = new HomePage(driver);
        hp.logout();

        String url = hp.getCurrentUrl();

        Assert.assertTrue(url.contains("cura"),
                "User not redirected to homepage after logout");
    }


    @Test(priority = 4)
    public void unauthorizedAccessTest() {

        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");

        LoginPage lp = new LoginPage(driver);

        Assert.assertTrue(lp.isUsernameFieldDisplayed(),
                "Unauthorized user was not redirected to login page");
    }
}
