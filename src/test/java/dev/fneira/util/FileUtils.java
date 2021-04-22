package dev.fneira.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String REPORTS_BASE_DIR = System.getProperty("user.dir") + SEPARATOR + "reports";

    private static String current_test_dir;
    private static int currentStep;

    static {
        createDirectory(REPORTS_BASE_DIR);
    }

    public static void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error al crear directorio reportes en ruta \"%s\"", REPORTS_BASE_DIR));
        }
    }

    public static void createDirectoryForTestCase(String testCaseName) {
        createDirectory(REPORTS_BASE_DIR + SEPARATOR + testCaseName);
        current_test_dir = testCaseName;
        currentStep = 1;
    }

    public static void copyFileForTestCase(File file, String fileName) {
        try {
            String extension = fileName.contains(".xlsx") ? "" : ".png";

            Files.copy(file.toPath(), Paths.get(REPORTS_BASE_DIR + SEPARATOR + current_test_dir + SEPARATOR + String.format("%02d", currentStep) + " - " + fileName + extension), StandardCopyOption.REPLACE_EXISTING);
            currentStep++;
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error al copiar screenshot de \"%s\" en ruta \"%s\"", file.getAbsolutePath() , REPORTS_BASE_DIR + SEPARATOR + fileName));
        }
    }

    public static void copyFileForTestCase(Path file, String fileName) {
        try {
            Files.copy(file, Paths.get(REPORTS_BASE_DIR + SEPARATOR + current_test_dir + SEPARATOR + String.format("%02d", currentStep) + " - " + fileName + ".png"), StandardCopyOption.REPLACE_EXISTING);
            currentStep++;
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error al copiar screenshot de \"%s\" en ruta \"%s\"", file.getFileName().toAbsolutePath() , REPORTS_BASE_DIR + SEPARATOR + fileName));
        }
    }


}
