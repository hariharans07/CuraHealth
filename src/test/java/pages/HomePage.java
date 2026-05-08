package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By menuToggle = By.id("menu-toggle");
    By logoutBtn = By.linkText("Logout");

    public void logout() {
        click(menuToggle);
        click(logoutBtn);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
