package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStoreBookViewerPage extends PageBase {

    @FindBy(xpath = "//button[text() = 'Add To Your Collection']")
    private WebElement btnAddBookToCollectionEl;

    @FindBy(xpath = "//button[text() = 'Back To Book Store']")
    private WebElement btnBackToBookStore;

    public BookStoreBookViewerPage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean clickAddBookToCollectionBtn() {
        return waitForElementVisible(btnAddBookToCollectionEl, 5) && click(btnAddBookToCollectionEl) && implicitWait(1);
    }

    public boolean acceptAlert() {
        getAlert().accept();
        return true;
    }

    public boolean clickBackToBookStoreBtn() {
        return click(btnBackToBookStore) && new BookStoreHomePage().isPageVisible();
    }

}
