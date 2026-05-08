package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage lp = new LoginPage(driver);

        lp.goToLogin();
        lp.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Test(dataProvider = "loginData", priority = 2)
    public void invalidLoginTest(String username, String password) {

        LoginPage lp = new LoginPage(driver);

        lp.goToLogin();
        lp.login(username, password);
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"John Doe", "ThisIsNotAPassword"},
                {"wrong", "wrong"}
        };
    }
}