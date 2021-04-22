package dev.fneira.excel;

import dev.fneira.business.BookData;
import dev.fneira.util.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

public class ExcelDocument {

    private final Workbook workbook;
    private final String fileName;
    private Sheet currentSheet;
    private int currentRowData;

    public ExcelDocument(String fileName) {
        this.workbook = new XSSFWorkbook();
        this.fileName = fileName;
    }

    public void createSheet(String sheetName) {
        this.currentSheet = workbook.createSheet(sheetName);
    }

    public void selectSheet(String sheetName) {
        this.currentSheet = workbook.getSheet(sheetName);
    }

    public void addHeaderRow(String... headers) {
        Row header = currentSheet.createRow(0);
        CellStyle cellStyle = getHeaderCellStyle();
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyle);
            currentSheet.autoSizeColumn(i);
        }
        currentRowData = 1;
    }

    public void addData(BookData bookData) {
        Row row = currentSheet.createRow(currentRowData);

        Cell isbnCell = row.createCell(0);
        isbnCell.setCellValue(bookData.getIsbn());

        Cell titleCell = row.createCell(1);
        titleCell.setCellValue(bookData.getTitle());

        Cell subtitleCell = row.createCell(2);
        subtitleCell.setCellValue(bookData.getSubTitle());

        Cell authorCell = row.createCell(3);
        authorCell.setCellValue(bookData.getAuthor());

        Cell publisherCell = row.createCell(4);
        publisherCell.setCellValue(bookData.getPublisher());

        Cell pagesCell = row.createCell(5);
        pagesCell.setCellValue(Integer.parseInt(bookData.getTotalPages()));

        Cell webSiteCell = row.createCell(6);
        webSiteCell.setCellValue(bookData.getWebsite());

        currentRowData++;
    }

    public void close() {
        try {
            autoSizeColumns();
            File file = Files.createTempFile("tsoft", ".tmp").toFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            workbook.write(fileOutputStream);
            workbook.close();
            FileUtils.copyFileForTestCase(file, fileName + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CellStyle getHeaderCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 18);

        cellStyle.setFont(font);

        return cellStyle;
    }

    private void autoSizeColumns() {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                }
            }
        }
    }


}
