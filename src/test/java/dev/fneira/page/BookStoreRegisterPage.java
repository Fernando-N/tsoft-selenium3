package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import dev.fneira.util.LoggerUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStoreRegisterPage extends PageBase {

    @FindBy(className = "main-header")
    private WebElement headerEl;

    @FindBy(id = "firstname")
    private WebElement txtFirstNameEl;

    @FindBy(id = "lastname")
    private WebElement txtLastNameEl;

    @FindBy(id = "userName")
    private WebElement txtUserNameEl;

    @FindBy(id = "password")
    private WebElement txtPasswordEl;

    @FindBy(xpath = "//iframe[@title = 'reCAPTCHA']")
    private WebElement iframeCaptchaEl;

    @FindBy(className = "rc-anchor-content")
    private WebElement divCaptchaEl;

    @FindBy(xpath = "/html/body/div[2]/div[1]")
    private WebElement divCaptchaImageCheckerEl;

    @FindBy(id = "register")
    private WebElement btnRegisterEl;

    public BookStoreRegisterPage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean isCurrentPage() {
        return getText(headerEl).contains("Register");
    }

    public boolean inputFirstName(String firstName) {
        return waitForElementVisible(txtFirstNameEl, 10) && sendKeys(txtFirstNameEl, firstName);
    }

    public boolean inputLastName(String lastName) {
        return sendKeys(txtLastNameEl, lastName);
    }

    public boolean inputUserName(String userName) {
        return sendKeys(txtUserNameEl, userName);
    }

    public boolean inputPassword(String password) {
        return sendKeys(txtPasswordEl, password);
    }

    public boolean clickOnCaptcha() {
        focusTo(iframeCaptchaEl);

        boolean result = click(divCaptchaEl);
        resetFocus();

        while (waitForElementVisible(divCaptchaImageCheckerEl, 10)) {
            LoggerUtil.startStep("Captcha pide validación humana, favor seleccionar imagenes, activando espera de 30 segundos");
            implicitWait(30);
        }

        return result;
    }

    public boolean clickOnRegisterButton() {
        return click(btnRegisterEl);
    }

    public String getTextAlertaVisible() {
        return getAlert().getText();
    }



}
