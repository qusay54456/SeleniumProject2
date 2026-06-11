package ch4_3;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestForm extends Base {
	private FormPage formPage;

	@BeforeMethod
	public void setupPage() {
		formPage = new FormPage(driver, wait);
	}

	@DataProvider(name = "XLSXDataProvider")
	public Object[][] XLSXDataProvider() throws EncryptedDocumentException, IOException, InvalidFormatException {
		return Readxlsx.excelDataProvider();
	}

	@Test(dataProvider = "XLSXDataProvider")
	public void testFormPage(String firstName, String lastName, String email, String gender, String phone, String dob,
			String address) {
		formPage.enterForm(firstName, lastName, email, gender, phone, dob, address);

		boolean actualResult = formPage.checkIfFormSuccess();
		Assert.assertTrue(actualResult, "Submiting form failed!");
	}
}
