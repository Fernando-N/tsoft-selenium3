package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookStoreHomePage extends PageBase {

    @FindBy(xpath = "//button[text() = 'Login']")
    private WebElement btnLoginEl;

    @FindBy(xpath = "//div[text() = 'Forms']//parent::div[@class='header-wrapper']")
    private WebElement divFormsTabEl;

    @FindBy(xpath = "//span[text()='Practice Form']//parent::li//parent::ul//parent::div[contains(@class, 'element-list')]")
    private WebElement spanPracticeFormEl;

    @FindBy(className = "main-header")
    private WebElement titleEl;

    @FindBy(xpath = "//span[@class='mr-2']")
    private List<WebElement> booksList;

    @FindBy(xpath = "//span[text() = 'Profile']//parent::li")
    private WebElement btnProfileEl;

    public BookStoreHomePage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean isPageVisible() {
        waitForElementVisible(titleEl, 5);
        return "Book Store".equals(getText(titleEl));
    }

    public boolean clickOnLoginButton() {
        return waitForElementVisible(btnLoginEl, 10) && click(btnLoginEl);
    }

    public boolean clickOnFormsTab() {
        return waitForElementVisible(divFormsTabEl, 10) && click(divFormsTabEl) && implicitWait(1);
    }

    public boolean clickOnPracticeFormOption() {
        return waitForElementVisible(spanPracticeFormEl, 10) && click(spanPracticeFormEl);
    }

    public boolean clickOnBook(String title) {
        implicitWait(2);
        for (WebElement element: booksList) {
            if (title.equals(getText(element))) {
                scrollTo(element);
                return click(element);
            }
        }

        return false;
    }

    public boolean clickProfileBtn() {
        return waitForElementVisible(btnProfileEl, 5) && click(btnProfileEl) && implicitWait(3);
    }
}
