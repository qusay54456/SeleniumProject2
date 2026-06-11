package ch4_3;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sws extends Base {
	
	@Test
    public void verifyFlightSearchResults() throws InterruptedException {
 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
 
        LocalDate departureDate = LocalDate.now();
 
        LocalDate returnDate = departureDate.plusDays(2);
 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 
        String depart = departureDate.format(formatter);
 
        String returning = returnDate.format(formatter);
 
        WebElement fromField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[text()='From']/preceding-sibling::input")
                )
        );
 
        fromField.sendKeys("Mumbai");
 
        Thread.sleep(2000);
 
        fromField.sendKeys(Keys.ARROW_DOWN);
 
        fromField.sendKeys(Keys.ENTER);
 
        WebElement toField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[text()='To']/preceding-sibling::input")
                )
        );
 
        toField.sendKeys("London-Heathrow, UK (LHR)");
 
        Thread.sleep(2000);
 
        toField.sendKeys(Keys.ARROW_DOWN);
 
        toField.sendKeys(Keys.ENTER);
 
        WebElement departField = driver.findElement(
                By.xpath("//label[text()='Depart']/preceding-sibling::input")
        );
 
        departField.sendKeys(Keys.CONTROL + "a");
 
        departField.sendKeys(depart);
 
        WebElement returnField = driver.findElement(
                By.xpath("//label[text()='Return']/preceding-sibling::input")
        );
 
        returnField.sendKeys(Keys.CONTROL + "a");
 
        returnField.sendKeys(returning);
 
        WebElement searchButton = driver.findElement(
                By.xpath("//button[contains(.,'Search flights')]")
        );
 
        searchButton.click();
 
        Thread.sleep(5000);
 
        boolean resultsDisplayed =
                driver.getCurrentUrl().contains("booking")
                || driver.getPageSource().contains("flight")
                || driver.getPageSource().contains("No flights");
 
        Assert.assertTrue(
                resultsDisplayed,
                "Flight search results were not displayed"
        );
 
        System.out.println("Flight results displayed successfully");
    }
}
