package dev.fneira.util;

import dev.fneira.driver.WebDriverHolder;
import org.openqa.selenium.OutputType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoggerUtil {

    private LoggerUtil() {

    }

    public static void startScenario(String scenarioName) {
        System.out.println("###############################################");
        System.out.println("#### STARTING SCENARIO: " + scenarioName);
    }

    public static void finishScenario(String scenarioName) {
        System.out.println("#### FINISHED SCENARIO: " + scenarioName);
        System.out.println("###############################################");
    }

    public static void startStep(String msg, String... args) {
        System.out.println(String.format("[INI STEP] " + msg, args));
    }

    public static void info(String msg, String... args) {
        System.out.println(String.format("[INFO] " + msg, args));
    }

    public static void success(boolean withRobot, String msg, String... args) {
        String msgFinal = String.format("[SUCCESS] " + msg, args);
        System.out.println(msgFinal);
        takeScreenShot(withRobot, msgFinal);
    }

    public static void error(boolean withRobot, String msg, String... args) {
        String msgFinal = String.format("[FAILED]" + msg, args);
        System.out.println(msgFinal);
        takeScreenShot(withRobot, msgFinal);
    }

    public static void takeScreenShot(boolean withRobot, String step) {
        if (withRobot) {
            takeScreenShotWithRobot(step);
            return;
        }

        File file = WebDriverHolder.getTakesScreenshot().getScreenshotAs(OutputType.FILE);
        FileUtils.copyFileForTestCase(file, step);
    }

    public static void takeScreenShotWithRobot(String step) {
        try {
            BufferedImage bufferedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            Path path = Files.createTempFile("tsoft", ".tmp");
            ImageIO.write(bufferedImage, "png", path.toFile());
            FileUtils.copyFileForTestCase(path, step);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

}
