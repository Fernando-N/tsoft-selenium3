package dev.fneira.page;

import dev.fneira.business.BookData;
import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStoreBookViewerProfilePage extends PageBase {

    @FindBy(xpath = "//button[text() = 'Back To Book Store']")
    private WebElement btnBackToBookStore;

    @FindBy(xpath = "//label[@id = 'ISBN-label']/parent::div/following::div/label")
    private WebElement lblISBNValue;

    @FindBy(xpath = "//label[@id = 'title-label']/parent::div/following::div/label")
    private WebElement lblTitleValue;

    @FindBy(xpath = "//label[@id = 'subtitle-label']/parent::div/following::div/label")
    private WebElement lblSubtitleValue;

    @FindBy(xpath = "//label[@id = 'author-label']/parent::div/following::div/label")
    private WebElement lblAuthorValue;

    @FindBy(xpath = "//label[@id = 'publisher-label']/parent::div/following::div/label")
    private WebElement lblPublisherValue;

    @FindBy(xpath = "//label[@id = 'pages-label']/parent::div/following::div/label")
    private WebElement lblPagesValue;

    @FindBy(xpath = "//label[@id = 'website-label']/parent::div/following::div/label")
    private WebElement lblWebsiteValue;

    public BookStoreBookViewerProfilePage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public BookData getCurrentBookData() {
        waitForElementVisible(lblISBNValue, 5);
        return new BookData()
                .setIsbn(getText(lblISBNValue))
                .setTitle(getText(lblTitleValue))
                .setSubTitle(getText(lblSubtitleValue))
                .setAuthor(getText(lblAuthorValue))
                .setPublisher(getText(lblPublisherValue))
                .setTotalPages(getText(lblPagesValue))
                .setWebsite(getText(lblWebsiteValue));
    }

    public boolean clickBackToBookStoreBtn() {
        return click(btnBackToBookStore) && new BookStoreHomePage().isPageVisible();
    }

}
