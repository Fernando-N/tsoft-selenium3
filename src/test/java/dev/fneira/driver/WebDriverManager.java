package dev.fneira.driver;

import dev.fneira.util.FileUtils;
import dev.fneira.util.OsUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private final WebDriver webDriver;

    public WebDriverManager(DriverType driverType) {
        final String BASE_PATH = System.getProperty("user.dir") + FileUtils.SEPARATOR + "drivers" + FileUtils.SEPARATOR;
        switch (driverType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", BASE_PATH + "chromedriver" + (OsUtils.isWindows() ? ".exe" : ""));
                this.webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                throw new RuntimeException("Driver firefox no implementado aun");
                //System.setProperty("INSERTAR VARIABLE FIREFOX/GECKO DRIVER", BASE_PATH + "INSERTAR NOMBRE DRIVER");
                //this.webDriver = new FirefoxDriver();
                //break;
            case IE:
                throw new RuntimeException("Driver ie no implementado aun");
                //System.setProperty("INSERTAR VARIABLE IE DRIVER", BASE_PATH + "INSERTAR NOMBRE DRIVER");
                //this.webDriver = new InternetExplorerDriver();
                //break;
            default:
                throw new RuntimeException("Driver no encontrado para " + driverType);
        }
    }

    public WebDriverManager openURL(String url) {
        webDriver.get(url);
        return this;
    }

    public void close() {
        webDriver.close();
    }

    public WebDriverManager maximizeBrowser() {
        webDriver.manage().window().maximize();
        return this;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

}
