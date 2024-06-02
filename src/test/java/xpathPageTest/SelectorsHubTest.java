package xpathPageTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xpathPage.SelectorsHub;

public class SelectorsHubTest {
	
	WebDriver driver;
	SelectorsHub selectorHub;

	@BeforeTest
	public void setup() {
		selectorHub = new SelectorsHub();
		selectorHub.initWebDriver();
	}
	
	@Test
	public void fillFormTest() {
		selectorHub.fillForm("testemail", "testpass", "testCompany", "738647282");
	}
	
	@Test
	public void fillFirstCrushFieldTest() {
		selectorHub.fillFirstCrushField("Jimmy Sergil");
	}
	
	@Test
	public void clickOnSVGElementTest() {
		selectorHub.clickOnSVGElement("Test First Name", "Test last Name");
	}
	
	@DataProvider
	public Object[][] dropdownOptionURL(){
		return new Object[][] {
			{"Bootcamp - SelectorsHub"},
			{"SelectorsHub - YouTube"},
			{"SelectorsHub- Free Productivity Booster Tools For Testers"}
		};
	}
	
	@Test
	public void dropdownActionsTest() {
		selectorHub.dropdownActions();

	}
	
	@Test(dataProvider="dropdownOptionURL")
	public void getAllWindowHandlesTest(String expectedTitle) {
		Assert.assertTrue(selectorHub.getAllWindowHandles(expectedTitle));
	}

}
