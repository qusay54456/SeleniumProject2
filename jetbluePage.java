package ch4_3;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class jetbluePage {
	private final WebDriver driver;
	private final WebDriverWait wait;
	
	private By cookie = By.className("call");
	
	private By fromField = By.xpath("//label[text()='From']//preceding-sibling::input");
	private By toField = By.xpath("//label[text()='To']//preceding-sibling::input");
	private By london = By.xpath("//li[contains(@id,'option-LHR-2')]");
	private By dept = By.xpath("//label[text()='Depart']//preceding-sibling::input");
			
	
	public jetbluePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	public void acceptCookie() throws InterruptedException {
		Thread.sleep(5000);
		By testt = By.xpath("//div[@class=\"trustarc_newcm_container truste_popframe\"]");
		SearchContext shadowRoot = driver.findElement(testt).getShadowRoot();
		WebElement element = shadowRoot.findElement(cookie);
		element.click();
		System.out.println("Accepted Cookie");
	}
	
	public void enterFromField(String name)  {
		WebElement element = driver.findElement(fromField);
		element.sendKeys(name,Keys.ENTER);
		System.out.println("found it");
	}
	
	public void enterToField(String name)  {
		WebElement element = driver.findElement(toField);
		element.sendKeys(name);
		WebElement ele2 = driver.findElement(london);
		ele2.click();
		System.out.println("found it");
		
	}
	
	public void enterDate()throws InterruptedException {
		WebElement element = driver.findElement(dept);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddd, MMM D");
		String formattedDate = sdf.format(date);
		element.sendKeys(formattedDate);
		System.out.println(formattedDate);
		Thread.sleep(5000);
	}
}
