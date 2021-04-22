package dev.fneira.page;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookStoreRegisterStudentPage extends PageBase {

    @FindBy(xpath = "//button[text() = 'Submit']")
    private WebElement btnSubmitEl;

    @FindBy(id = "firstName")
    private WebElement txtFirstNameEl;

    @FindBy(id = "lastName")
    private WebElement txtLastNameEl;

    @FindBy(id = "userEmail")
    private WebElement txtEmailEl;

    @FindBy(id = "userNumber")
    private WebElement txtNumberEl;

    @FindBy(id = "currentAddress")
    private WebElement txtAddressEl;

    @FindBy(id = "genterWrapper")
    private WebElement divGenderEl;

    @FindBy(id = "state")
    private WebElement divStateEl;

    @FindBy(id = "city")
    private WebElement divCityEl;

    @FindBy(className = "react-datepicker-wrapper")
    private WebElement divDateBirthPickerEl;

    @FindBy(className = "react-datepicker__year-select")
    private WebElement selectYearEl;

    @FindBy(className = "react-datepicker__month-select")
    private WebElement selectMonthEl;

    @FindBy(id = "subjectsInput")
    private WebElement inputSubjectsEl;

    @FindBy(className = "modal-content")
    private WebElement divModalEl;

    public BookStoreRegisterStudentPage() {
        super(WebDriverHolder.getWebDriverManager());
        PageFactory.initElements(WebDriverHolder.getWebDriverManager().getWebDriver(), this);
    }

    public boolean clickOnSubmitBtn() {
        return waitForElementVisible(btnSubmitEl, 10) && click(btnSubmitEl);
    }

    public boolean inputFirstName(String firstName) {
        return sendKeys(txtFirstNameEl, firstName);
    }

    public boolean inputLastName(String lastName) {
        return sendKeys(txtLastNameEl, lastName);
    }

    public boolean inputEmail(String email) {
        return sendKeys(txtEmailEl, email);
    }

    public boolean inputNumber(String number) {
        return sendKeys(txtNumberEl, number);
    }

    public boolean inputCurrentAddress(String currentAddress) {
        return sendKeys(txtAddressEl, currentAddress);
    }

    public boolean selectGender(String gender) {
        WebElement genderOption = divGenderEl.findElement(By.xpath(String.format("//input[@value = '%s']//parent::div", gender)));
        return waitForElementVisible(genderOption, 5) && click(genderOption);
    }

    public boolean selectState(String state) {
        waitForElementVisible(divStateEl, 5);
        click(divStateEl);

        WebElement stateOption = getWebDriver().findElement(By.xpath(String.format("//div[contains(@id, 'react-select') and text() = '%s']", state)));


        return waitForElementVisible(stateOption, 5) && click(stateOption);
    }

    public boolean selectCity(String city) {
        waitForElementVisible(divCityEl, 5);
        click(divCityEl);

        WebElement stateOption = getWebDriver().findElement(By.xpath(String.format("//div[contains(@id, 'react-select') and text() = '%s']", city)));


        return waitForElementVisible(stateOption, 5) && click(stateOption);
    }

    public boolean selectDateBirth(String date) {
        String[] dateSplit = date.split("/");

        waitForElementVisible(divDateBirthPickerEl, 5);
        click(divDateBirthPickerEl);

        new Select(selectYearEl).selectByVisibleText(dateSplit[2]);
        new Select(selectMonthEl).selectByVisibleText(dateSplit[1]);
        WebElement dayEl = getWebDriver().findElement(By.xpath(String.format("//div[@class = 'react-datepicker__month']//div[text() = '%s']", dateSplit[0])));

        return waitForElementVisible(dayEl, 5) && click(dayEl);
    }

    public boolean selectSubject(String abbreviated, String full) {
        waitForElementVisible(inputSubjectsEl, 5);
        sendKeys(inputSubjectsEl, abbreviated);

        implicitWait(1);

        WebElement subjectOption = getWebDriver().findElement(By.xpath(String.format("//div[contains(@id, 'react-select') and text() = '%s']", full)));

        return waitForElementVisible(subjectOption, 5) && click(subjectOption);
    }

    public boolean selectHobby(String hobby) {
        WebElement hobbyOption = getWebDriver().findElement(By.xpath(String.format("//label[text() = '%s']//parent::div", hobby)));
        return waitForElementVisible(hobbyOption, 5) && click(hobbyOption);
    }

    public boolean isModalSuccessfulVisible() {
        return waitForElementVisible(divModalEl, 5);
    }

}
