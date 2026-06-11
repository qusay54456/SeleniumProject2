package ch4_3;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class jetTest extends Base {
	private jetbluePage jetbluePage;
	
	@BeforeTest
	public void setupPage() throws InterruptedException {
		jetbluePage = new jetbluePage(driver, wait);
	}
	
	@Test(priority = 1)
	public void test() throws InterruptedException {
		jetbluePage.acceptCookie();
		jetbluePage.enterFromField("Mumbai");
		jetbluePage.enterToField("London-Heathrow, UK (LHR)");
		jetbluePage.enterDate();
	}
}
