package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAHomePage extends PageBase {

    @FindBy(xpath = "//h5[text() = 'Book Store Application']//parent::div//parent::div//parent::div[contains(@class, 'top-card')]")
    private WebElement cardBookStoreApplication;

    public DemoQAHomePage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean clickCardBookStoreApplication() {
        return waitForElementVisible(cardBookStoreApplication, 5) && scrollTo(cardBookStoreApplication) && click(cardBookStoreApplication);
    }

}
