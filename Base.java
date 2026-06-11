package ch4_3;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	protected WebDriver driver;
	protected WebDriverWait wait;
//	private static String baseUrl = "https://todomvc.com/examples/angular/dist/browser/#/all";
	private static String baseUrl = "https://todomvc.com/examples/angular/dist/browser/#/all";	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		System.out.println("Open the browser.");
	}

	@BeforeMethod
	public void navigateToBase() {
		driver.get(baseUrl);
	}

	@AfterTest
	public void tearDown() {
		System.out.println("Close the browser!");
		
		if (driver != null) {
			driver.quit();
		}
	}
}
