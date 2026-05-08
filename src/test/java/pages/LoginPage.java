package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By makeAppointmentBtn = By.id("btn-make-appointment");
    By username = By.id("txt-username");
    By password = By.id("txt-password");
    By loginBtn = By.id("btn-login");
    By errorMsg = By.xpath("//p[@class='lead text-danger']");

    public void goToLogin() {
        click(makeAppointmentBtn);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }

    public String getErrorMessage() {
        return text(errorMsg);
    }
    public void clickLogin() {
        click(loginBtn);
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(username);
    }
}
