package dev.fneira.steps;

import dev.fneira.business.BookData;
import dev.fneira.db.DatabaseActions;
import dev.fneira.excel.ExcelDocument;
import dev.fneira.page.BookStoreBookViewerProfilePage;
import dev.fneira.page.BookStoreProfilePage;
import dev.fneira.util.LoggerUtil;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BookStoreProfileSteps {

    private final BookStoreProfilePage bookStoreProfilePage = new BookStoreProfilePage();
    private final BookStoreBookViewerProfilePage bookStoreBookViewerProfilePage = new BookStoreBookViewerProfilePage();
    private final DatabaseActions databaseActions = new DatabaseActions();

    @Then("valido que los libros de mi coleccion existan en base de datos")
    public void validoQueLosLibrosDeMiColeccionExistanEnBaseDeDatos() {
        LoggerUtil.startStep("valido que los libros de mi coleccion existan en base de datos");
        ExcelDocument excelDocument = new ExcelDocument("Reporte datos obtenido desde la web DemoQA");
        excelDocument.createSheet("Mi colección");
        excelDocument.addHeaderRow("isbn", "title", "subtitle", "author", "publisher", "pages", "website");
        for (int i = 0; i < 5; i++) {
            LoggerUtil.info("ingresando al libro numero {%s}", String.valueOf(i+1));
            Assert.assertTrue(bookStoreProfilePage.clickBookWithIndex(i));
            BookData currentBook = bookStoreBookViewerProfilePage.getCurrentBookData();
            LoggerUtil.info("data libro actual: %s", currentBook.toString());
            BookData bookDb = databaseActions.getBook(currentBook.getIsbn());
            Assert.assertNotNull(bookDb);
            LoggerUtil.info("data libro db: %s", bookDb.toString());
            Assert.assertEquals(bookDb.getIsbn(), currentBook.getIsbn());
            Assert.assertEquals(bookDb.getTitle(), currentBook.getTitle());
            Assert.assertEquals(bookDb.getSubTitle(), currentBook.getSubTitle());
            Assert.assertEquals(bookDb.getAuthor(), currentBook.getAuthor());
            Assert.assertEquals(bookDb.getPublisher(), currentBook.getPublisher());
            Assert.assertEquals(bookDb.getTotalPages(), currentBook.getTotalPages());
            Assert.assertEquals(bookDb.getWebsite(), currentBook.getWebsite());
            LoggerUtil.success(false, "Libro {%s} coincide con el de base de datos", currentBook.getTitle());
            excelDocument.addData(currentBook);
            LoggerUtil.info("Volviendo a perfil");
            bookStoreProfilePage.clickBtnBackToStore();
        }

        excelDocument.close();
        LoggerUtil.success(false, "valido que los libros de mi coleccion existan en base de datos");
    }
}
