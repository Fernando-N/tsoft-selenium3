package dev.fneira.steps;

import dev.fneira.business.LoginData;
import dev.fneira.db.DatabaseActions;
import dev.fneira.page.BookStoreHomePage;
import dev.fneira.page.BookStoreLoginPage;
import dev.fneira.util.LoggerUtil;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.Optional;

public class BookStoreLoginSteps {

    private final BookStoreLoginPage bookStoreLoginPage = new BookStoreLoginPage();
    private final BookStoreHomePage bookStoreHomePage = new BookStoreHomePage();

    @Given("inicio sesión con cuenta de base de datos")
    public void inicioSesiónConCuentaDeBaseDeDatos() {
        LoggerUtil.startStep("inicio sesión con cuenta de base de datos");
        LoginData loginData = new DatabaseActions().getLoginData();

        LoggerUtil.info("ingreso username {%s}", loginData.getUsername());
        Assert.assertTrue(bookStoreLoginPage.inputUsername(loginData.getUsername()));

        LoggerUtil.info("ingreso password {%s}", loginData.getPassword());
        Assert.assertTrue(bookStoreLoginPage.inputPassword(loginData.getPassword()));

        LoggerUtil.info("haciendo clic en login");
        Assert.assertTrue(bookStoreLoginPage.clickLoginBtn());

        Optional<String> errorOptional = bookStoreLoginPage.getErrorText();

        if (errorOptional.isPresent()) {
            Assert.fail();
            LoggerUtil.error(false, "Error en login {%s}", errorOptional.get());
        }

        Assert.assertTrue(bookStoreHomePage.isPageVisible());
        LoggerUtil.info("se visualiza pagina inicio visible");
        LoggerUtil.success(false, "inicio sesión con cuenta de base de datos");
    }
}
