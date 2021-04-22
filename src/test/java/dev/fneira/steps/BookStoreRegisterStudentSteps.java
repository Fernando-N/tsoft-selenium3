package dev.fneira.steps;

import dev.fneira.business.StudentData;
import dev.fneira.db.DatabaseActions;
import dev.fneira.page.BookStoreHomePage;
import dev.fneira.page.BookStoreRegisterStudentPage;
import dev.fneira.util.LoggerUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BookStoreRegisterStudentSteps {

    private final BookStoreHomePage bookStoreHomePage = new BookStoreHomePage();
    private final BookStoreRegisterStudentPage bookStoreRegisterStudentPage = new BookStoreRegisterStudentPage();

    @Given("expando menu lateral Forms")
    public void expandoMenuLateralForms() {
        LoggerUtil.startStep("expando menu lateral Forms");
        Assert.assertTrue(bookStoreHomePage.clickOnFormsTab());
        LoggerUtil.success(false, "expando menu lateral Forms");
    }

    @And("hago clic en opcion Practice Form")
    public void hagoClicEnOpcionPracticeForm() {
        LoggerUtil.startStep("hago clic en opcion Practice Form");
        Assert.assertTrue(bookStoreHomePage.clickOnPracticeFormOption());
        LoggerUtil.success(false, "hago clic en opcion Practice Form");
    }

    @And("hago clic en el boton submit")
    public void hagoClicEnElBotonSubmit() {
        LoggerUtil.startStep("hago clic en el boton submit");
        Assert.assertTrue(bookStoreRegisterStudentPage.clickOnSubmitBtn());
        LoggerUtil.success(false, "hago clic en el boton submit");
    }

    @And("completo el formulario de estudiante con los datos de base de datos")
    public void completoElFormularioDeEstudianteConLosDatosDeBaseDeDatos() {
        LoggerUtil.startStep("completo el formulario de estudiante con los datos de base de datos");
        StudentData studentData = new DatabaseActions().getStudentData();

        LoggerUtil.info("ingreso first name {%s}", studentData.getName());
        Assert.assertTrue(bookStoreRegisterStudentPage.inputFirstName(studentData.getName()));

        LoggerUtil.info("ingreso last name {%s}", studentData.getLastName());
        Assert.assertTrue(bookStoreRegisterStudentPage.inputLastName(studentData.getLastName()));

        LoggerUtil.info("ingreso email {%s}", studentData.getEmail());
        Assert.assertTrue(bookStoreRegisterStudentPage.inputEmail(studentData.getEmail()));

        LoggerUtil.info("selecciono gender {%s}", studentData.getGender());
        Assert.assertTrue(bookStoreRegisterStudentPage.selectGender(studentData.getGender()));

        LoggerUtil.info("ingreso mobile {%s}", studentData.getMobile());
        Assert.assertTrue(bookStoreRegisterStudentPage.inputNumber(studentData.getMobile()));

        LoggerUtil.info("ingreso date birth {%s}", studentData.getDateBirth());
        Assert.assertTrue(bookStoreRegisterStudentPage.selectDateBirth(studentData.getDateBirth()));

        LoggerUtil.info("ingreso current address {%s}", studentData.getCurrentAddress());
        Assert.assertTrue(bookStoreRegisterStudentPage.inputCurrentAddress(studentData.getCurrentAddress()));

        LoggerUtil.info("selecciono state {%s}", studentData.getState());
        Assert.assertTrue(bookStoreRegisterStudentPage.selectState(studentData.getState()));

        LoggerUtil.info("ingreso city {%s}", studentData.getCity());
        Assert.assertTrue(bookStoreRegisterStudentPage.selectCity(studentData.getCity()));

        LoggerUtil.success(false, "completo el formulario de estudiante con los datos de base de datos");
    }

    @And("agrego materias al formulario")
    public void agregoMateriasAlFormulario(DataTable table) {
        LoggerUtil.startStep("agrego materias al formulario");
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> actual: data) {
            LoggerUtil.info("agrego subject {%s}", actual.get("FullText"));
            Assert.assertTrue(bookStoreRegisterStudentPage.selectSubject(actual.get("Abbreviated"), actual.get("FullText")));
        }

        LoggerUtil.success(false, "agrego materias al formulario");
    }

    @And("agrego hobbies al formulario")
    public void agregoHobbiesAlFormulario(DataTable table) {
        LoggerUtil.startStep("agrego hobbies al formulario");
        List<String> data = table.asList(String.class);

        for (String actual: data) {
            if (actual.equals("Hobbies")) continue;
            LoggerUtil.info("agrego hobby {%s}", actual);
            Assert.assertTrue(bookStoreRegisterStudentPage.selectHobby(actual));
        }

        LoggerUtil.success(false, "agrego hobbies al formulario");
    }

    @Then("visualizo modal de formulario enviado correctamente")
    public void visualizoModalDeFormularioEnviadoCorrectamente() {
        LoggerUtil.startStep("visualizo modal de formulario enviado correctamente");
        Assert.assertTrue(bookStoreRegisterStudentPage.isModalSuccessfulVisible());
        LoggerUtil.success(false, "visualizo modal de formulario enviado correctamente");
    }
}
