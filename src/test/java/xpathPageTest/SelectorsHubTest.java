package xpathPageTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import xpathPage.SelectorsHub;

public class SelectorsHubTest {
	
	WebDriver driver;
	SelectorsHub selectorHub;

	@BeforeTest
	public void setup() {
		selectorHub = new SelectorsHub(driver);
		selectorHub.launchBrowser();
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
}
