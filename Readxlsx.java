package ch4_3;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Readxlsx {
    private final static String XLSX_FILE = "src/data/FormTestData.xlsx";

    public static Object[][] excelDataProvider()
            throws EncryptedDocumentException, InvalidFormatException, IOException {

        FileInputStream inputStream = new FileInputStream(XLSX_FILE);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        Object[][] data = new Object[rowCount][7];
        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            String firstName = formatter.formatCellValue(row.getCell(0));
            String lastName = formatter.formatCellValue(row.getCell(1));
            String email = formatter.formatCellValue(row.getCell(2));
            String gender = formatter.formatCellValue(row.getCell(3));
            String phone = formatter.formatCellValue(row.getCell(4));
            String dob = formatter.formatCellValue(row.getCell(5));
            String address = formatter.formatCellValue(row.getCell(6));
            data[i] = new Object[]{firstName, lastName, email, gender, phone, dob, address};
        }

        workbook.close();
        inputStream.close();
        return data;
    }
}