package dev.fneira.steps;

import dev.fneira.business.UserData;
import dev.fneira.page.BookStoreHomePage;
import dev.fneira.page.BookStoreLoginPage;
import dev.fneira.page.BookStoreRegisterPage;
import dev.fneira.page.DemoQAHomePage;
import dev.fneira.util.LoggerUtil;
import dev.fneira.util.StringUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BookStoreRegisterSteps {

    private final DemoQAHomePage demoQAHomePage = new DemoQAHomePage();
    private final BookStoreHomePage bookStoreHomePage = new BookStoreHomePage();
    private final BookStoreLoginPage bookStoreLoginPage = new BookStoreLoginPage();
    private final BookStoreRegisterPage bookStoreRegisterPage = new BookStoreRegisterPage();

    @And("completo el formulario de registro")
    public void completoElFormularioDeRegistroConLosSiguientesDatos(DataTable table) {
        LoggerUtil.startStep("completo el formulario con los siguientes datos");
        UserData userData = new UserData(table);

        LoggerUtil.info("ingreso first name {%s}", userData.getFirstName());
        Assert.assertTrue(bookStoreRegisterPage.inputFirstName(userData.getFirstName()));

        LoggerUtil.info("ingreso last name {%s}", userData.getLastName());
        Assert.assertTrue(bookStoreRegisterPage.inputLastName(userData.getLastName()));

        LoggerUtil.info("ingreso user name {%s}", userData.getUserName());
        Assert.assertTrue(bookStoreRegisterPage.inputUserName(userData.getUserName()));// + StringUtils.randomCharacter()));

        LoggerUtil.info("ingreso password {%s}", userData.getPassword());
        Assert.assertTrue(bookStoreRegisterPage.inputPassword(userData.getPassword()));
        LoggerUtil.success(false, "completo el formulario con los siguientes datos");
    }

    @And("hago clic en reCaptcha")
    public void hagoClicEnReCaptcha() {
        LoggerUtil.startStep("hago clic en reCaptcha");
        Assert.assertTrue(bookStoreRegisterPage.clickOnCaptcha());
        LoggerUtil.success(false, "hago clic en reCaptcha");
    }

    @And("hago clic en boton registrarme")
    public void hagoClicEnBotonRegistrarme() {
        LoggerUtil.startStep("hago clic en boton registrarme");
        Assert.assertTrue(bookStoreRegisterPage.clickOnRegisterButton());
        LoggerUtil.success(false, "hago clic en boton registrarme");
    }

    @Then("visualizo alerta de registro correcto con texto {string}")
    public void visualizoPantallaDeRegistradoRecorrectamente(String arg0) {
        LoggerUtil.startStep("visualizo alerta de registro correcto con texto");
        LoggerUtil.info("validando expected = {%s} - actual = {%s} ", arg0, bookStoreRegisterPage.getTextAlertaVisible());
        Assert.assertEquals(arg0, bookStoreRegisterPage.getTextAlertaVisible());
        LoggerUtil.success(true, "visualizo alerta de registro correcto con texto");
    }

    @And("hago clic en cuadro Book Store Application")
    public void hagoClicEnCuadroBookStoreApplication() {
        LoggerUtil.startStep("hago clic en cuadro Book Store Application");
        Assert.assertTrue(demoQAHomePage.clickCardBookStoreApplication());
        LoggerUtil.success(false, "hago clic en cuadro Book Store Application");
    }

    @And("hago clic en boton Login")
    public void hagoClicEnBotonLogin() {
        LoggerUtil.startStep("hago clic en boton Login");
        Assert.assertTrue(bookStoreHomePage.clickOnLoginButton());
        LoggerUtil.success(false, "hago clic en boton Login");
    }

    @And("hago clic en boton New User")
    public void hagoClicEnBotonNewUser() {
        LoggerUtil.startStep("hago clic en boton New User");
        Assert.assertTrue(bookStoreLoginPage.clickOnNewUserButton());
        LoggerUtil.success(false, "hago clic en boton New User");
    }
}
