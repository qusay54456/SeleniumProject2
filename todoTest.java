package ch4_3;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class todoTest extends Base {
	private todoPage todopage;
	private String todo1 = "Study";
	private String todo2 = "Work";

	@BeforeTest
	public void setupPage() {
		todopage = new todoPage(driver, wait);
		todopage.deleteAllItems();
	}

	@Test(priority = 1)
	public void addItemsToList() throws InterruptedException {
		todopage.addItemToList(todo1);
		todopage.addItemToList(todo2);
		Assert.assertEquals(todopage.getItemByLabel(todo1), todo1);
		Assert.assertEquals(todopage.getItemByLabel(todo2), todo2);
		Assert.assertEquals(todopage.itemListCount(), 2, "counting items error");
		System.out.println("Counting items correct!");
	}

	@Test(priority = 2)
	public void markOneAsCompleted() {
		todopage.markFirstAsCompleted();
	}

	@Test(priority = 3)
	public void filterByCompleted() {
		todopage.completedFilter();
		Assert.assertEquals(todopage.getItemByLabel(todo1), todo1, "filter not working!");
	}

	@Test(priority = 4)
	public void deleteAllItems() {
		todopage.allFilter();
		todopage.deleteAllItems();
	}

}
