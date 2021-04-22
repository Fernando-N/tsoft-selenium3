package dev.fneira.steps;

import dev.fneira.page.BookStoreBookViewerPage;
import dev.fneira.page.BookStoreHomePage;
import dev.fneira.util.LoggerUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;

import java.util.List;

public class BookStoreHomeSteps {

    private final BookStoreHomePage bookStoreHomePage = new BookStoreHomePage();
    private final BookStoreBookViewerPage bookStoreBookViewerPage = new BookStoreBookViewerPage();

    @And("agrego libros a mi coleccion")
    public void agregoLibrosAMiColeccion(DataTable table) {
        LoggerUtil.startStep("agrego libros a mi coleccion");
        List<String> data = table.asList(String.class);

        for (String actual: data) {
            if (actual.equals("Title")) continue;
            LoggerUtil.info("agregando libro {%s} a mi coleccion", actual);
            LoggerUtil.info("Abriendo libro {%s}", actual);
            Assert.assertTrue(bookStoreHomePage.clickOnBook(actual));
            Assert.assertTrue(bookStoreBookViewerPage.clickAddBookToCollectionBtn());
            LoggerUtil.success(true, "Agrego libro {%s} a mi coleccion", actual);
            Assert.assertTrue(bookStoreBookViewerPage.acceptAlert());
            LoggerUtil.info("Clic en volver a BookStore");
            Assert.assertTrue(bookStoreBookViewerPage.clickBackToBookStoreBtn());
        }

        LoggerUtil.success(false, "agrego libros a mi coleccion");
    }

    @And("ingreso a mi perfil")
    public void ingresoAMiPerfil() {
        LoggerUtil.startStep("ingreso a mi perfil");
        Assert.assertTrue(bookStoreHomePage.clickProfileBtn());
        LoggerUtil.success(false, "ingreso a mi perfil");
    }
}
