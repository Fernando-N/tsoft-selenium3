package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

public class BookStoreLoginPage extends PageBase {

    @FindBy(xpath = "//button[text() = 'New User']")
    private WebElement btnNewUserEl;

    @FindBy(id = "userName")
    private WebElement txtUsernameEl;

    @FindBy(id = "password")
    private WebElement txtPasswordEl;

    @FindBy(id = "login")
    private WebElement btnLoginEl;

    @FindBy(xpath = "//p[@id = 'name']")
    private WebElement lblErrorEl;

    public BookStoreLoginPage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean clickOnNewUserButton() {
        return waitForElementVisible(btnNewUserEl, 10) && click(btnNewUserEl);
    }

    public boolean inputUsername(String username) {
        return sendKeys(txtUsernameEl, username);
    }

    public boolean inputPassword(String password) {
        return sendKeys(txtPasswordEl, password);
    }

    public boolean clickLoginBtn() {
        return click(btnLoginEl);
    }

    public Optional<String> getErrorText() {
        return waitForElementVisible(lblErrorEl, 5) ? Optional.of(getText(lblErrorEl)) : Optional.empty();
    }

}
