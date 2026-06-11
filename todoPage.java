package ch4_3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class todoPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	private By typeField = By.className("new-todo");
	private By checkBox = By.className("toggle");
	private By labelItem = By.cssSelector("ul.todo-list li label");
	private By completedFilter = By.xpath("//a[contains(@href,'completed')]");
	private By allFilter = By.xpath("//a[contains(@href,'all') or contains(@href,'#/')]");
	private By deleteItem = By.className("destroy");

	private List<WebElement> list;

	public todoPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void addItemToList(String name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(typeField));
		driver.findElement(typeField).sendKeys(name, Keys.ENTER);
		System.out.println("Added item to list!");
	}

	public int itemListCount() throws InterruptedException {
		List<WebElement> list = driver.findElements(labelItem);
		return list.size();
	}

	public void markFirstAsCompleted() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
		WebElement chk = driver.findElement(checkBox);
		chk.click();
		System.err.println("Marked first item!");
	}

	public void completedFilter() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(completedFilter));
		driver.findElement(completedFilter).click();
		System.out.println("Filter by completed!");
	}

	public void allFilter() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(allFilter));
		driver.findElement(allFilter).click();
		System.out.println("Filter by all!");
	}

	public String getItemByLabel(String txt) {
		By labelT = By.xpath("//ul[contains(@class,'todo-list')]//label[text()='" + txt + "']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(labelT));
		WebElement element = driver.findElement(labelT);
		System.out.println("Item found!");
		return element.getText();
	}

	public void deleteAllItems() {
		list = driver.findElements(deleteItem);
		for (WebElement element : list) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
		System.out.println("successfully deleted all items!");
	}
}