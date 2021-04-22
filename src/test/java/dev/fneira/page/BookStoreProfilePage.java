package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookStoreProfilePage extends PageBase {

    @FindBy(xpath = "//div[@class='action-buttons']//a")
    private List<WebElement> booksList;

    @FindBy(xpath = "//button[text() = 'Back To Book Store']")
    private WebElement btnBackToStore;

    public BookStoreProfilePage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean clickBookWithIndex(int index) {
        return click(booksList.get(index));
    }

    public boolean clickBtnBackToStore() {
        return click(btnBackToStore) && implicitWait(3);
    }

}
