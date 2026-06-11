package ch4_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	private By firstNameField = By.id("firstName");
	private By lastNameField = By.id("lastName");
	private By emailField = By.id("userEmail");
	HashMap<String, By> genderMenu;
	private By numberPhoneField = By.id("userNumber");

	private By dobField = By.xpath("//input[@id='dateOfBirthInput']");
	private By addressField = By.id("currentAddress");

	private By submitBtn = By.xpath(".//button[@id='submit']");

	private By thanksText = By.xpath(".//div[contains(text(),'Thanks')]");

	public FormPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		genderMenu = new HashMap<>();
		genderMenu.put("male", By.id("gender-radio-1"));
		genderMenu.put("female", By.id("gender-radio-2"));
		genderMenu.put("other", By.id("gender-radio-3"));
	}

	public void enterFirstName(String firstName) {
		driver.findElement(firstNameField).sendKeys(firstName);
		System.out.println("----------------------------------");
		System.out.println("enterFirstName");
	}

	public void enterLastName(String lastName) {
		driver.findElement(lastNameField).sendKeys(lastName);
		System.out.println("enterLastName");
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
		System.out.println("enterEmail");
	}

	public void selectGender(String gender) {
		if (!genderMenu.containsKey(gender))
			driver.findElement(genderMenu.get("other")).click();
		driver.findElement(genderMenu.get(gender.toLowerCase())).click();
		System.out.println("selectGender");
	}

	public void enterPhoneNumber(String num) {
		driver.findElement(numberPhoneField).sendKeys(num);
	}

	public void enterDob(String dob) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date convertedDate = new Date();
		String finalDateString = "";
		try {
			convertedDate = dateFormat.parse(dob);
			SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd MMM yyyy");
			finalDateString = sdfnewformat.format(convertedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		WebElement dobE = driver.findElement(dobField);
		dobE.sendKeys(Keys.CONTROL + "a");
		dobE.sendKeys(finalDateString);
		System.out.println("enterDob");
	}

	public void enterAddress(String address) {
		driver.findElement(addressField).sendKeys(address);
		System.out.println("enterAddress");
	}

	public void clickSubmitBtn() throws InterruptedException {

		WebElement submitButton = driver.findElement(submitBtn);
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitButton);
//		submitButton.click();
		System.out.println("clickSubmitBtn");
	}

	public boolean checkIfFormSuccess() {
		try {
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}
			driver.findElement(thanksText);
			System.out.println("checkIfFormSuccess");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void enterForm(String firstName, String lastName, String email, String gender, String phone, String dob,
			String address) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmail(email);
		selectGender(gender);
		enterPhoneNumber(phone);
		enterDob(dob);
		enterAddress(address);
		try {
			clickSubmitBtn();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
