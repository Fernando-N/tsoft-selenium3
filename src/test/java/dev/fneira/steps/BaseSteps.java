package dev.fneira.steps;

import dev.fneira.driver.DriverType;
import dev.fneira.driver.WebDriverHolder;
import dev.fneira.driver.WebDriverManager;
import dev.fneira.util.FileUtils;
import dev.fneira.util.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;

import java.util.List;

public class BaseSteps {

    @Before
    public void before(Scenario scenario) {
        WebDriverHolder.setWebDriverManager(new WebDriverManager(DriverType.CHROME));
        FileUtils.createDirectoryForTestCase(getTestCaseName(scenario));
        LoggerUtil.startScenario(getTestCaseName(scenario));
    }

    @After
    public void after(Scenario scenario) {
//        WebDriverHolder.getWebDriverManager().close();
        LoggerUtil.finishScenario(getTestCaseName(scenario));
    }

    @When("ingreso a url {string}")
    public void openUrl(String arg0) {
        LoggerUtil.startStep("Ingreso a url url \"%s\"", arg0);
        WebDriverHolder.getWebDriverManager().maximizeBrowser().openURL(arg0);
        LoggerUtil.success(false, "Url abierta correctamente");
    }

    private String getTestCaseName(Scenario scenario) {
        String tagName = "";
        List<String> tags = ((List<String>) scenario.getSourceTagNames());
        if (!tags.isEmpty() && tags.get(0).contains("Test")) {
            tagName = tags.get(0).replace("@", "") + " - ";
        }

        return tagName + scenario.getName();
    }

}
