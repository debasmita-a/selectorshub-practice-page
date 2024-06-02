package xpathPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectorsHub {

	public WebDriver driver;
	String parentWindowHandle;

	By userEmail = By.name("email");
	By password = By.id("pass");
	By company = By.name("company");
	By mobileNum = By.name("mobile number");
	By submitBtn = By.xpath("//button[@value='Submit']");

	By firstCrush = By.id("inp_val");

	By editIconSvg = By.xpath("(//*[local-name()='svg'])[2]");
	By enterFirstName = By.xpath("//input[@placeholder='First Enter name']");
	By enterLastName = By.xpath("//input[@placeholder='Enter Last name']");

	By checkOutHereBtn = By.xpath("//button[text()='Checkout here']"); // hoverover
	By dropdownOptions = By.xpath("//button[text()='Checkout here']/following-sibling::div//a"); // div[@class='dropdown-content']//a
	By carsDropdown = By.xpath("//div[@class='dropdown']/following-sibling::select");
	By carsDropdownOptions = By.xpath("//div[@class='dropdown']/following-sibling::select/option");

	By datePicker = By.id("datepicker");
	By calendar = By.name("the_date");
	// shadow dom elements :
	By learningHubLink = By.xpath("//a[@class='learningHub']");
	By userNameField = By.id("kils");
	By svgElement = By.xpath("//*[local-name()='svg' and @focusable='false']");
	By shadowDomInsideIFrameLink = By.xpath("//a[contains(text(),'shadow dom inside iframe')]");
	By iFrameInsideShadowDom = By.xpath("//a[contains(text(),'iframe inside shadow dom')]");
	By pizzaField = By.id("pizza");
	By conceptTest = By.id("training");
	By learnXPathLinkClick = By.linkText("Learn XPath & Advance Automation Concepts");
	By passwordField = By.id("pwd");

	// html user table -01:
	By tableWithScroll = By.id("tableWrapper");
	By resultTable = By.id("resultTable");
	By allHeaders = By.xpath("//table[@id='resultTable']/thead//th//a"); // Use a HashMap
	By allUserRows = By.xpath("//table[@id='resultTable']/tbody//tr");

	// alerts :
	By windowAlert = By.xpath("//button[text()='Click To Open Window Alert']");
	By windowPromtAlert = By.xpath("//button[text()='Click To Open Window Prompt Alert']");

	// modal :
	By openModalBtn = By.xpath("//button[@id='myBtn']");
	By modalCloseBtn = By.xpath("//div[@id='myModal']//div[@class='modal-header']//span");
	By modalBodyElements = By.xpath("//div[@id='myModal']//div[@class='modal-body']//p");
	By modalHeaders = By.xpath("//div[@id='myModal']//a");

	// html system distribution table -02 :
	By table2 = By.id("tablepress-1"); // scroll down to table
	// ************************ public constructor to initialize WenDriver
	// ******************

	public void initWebDriver() {
		driver = new ChromeDriver();
		driver.get("https://selectorshub.com/xpath-practice-page/");
		parentWindowHandle = driver.getWindowHandle();
		driver.manage().window().maximize();
	}

	// ************************* private element utilities
	// ***********************************

	private WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	private List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	private void doSendKeys(By locator, String keys) {
		getElement(locator).clear();
		getElement(locator).sendKeys(keys);
	}

	private void doClick(By locator) {
		getElement(locator).click();
	}

	private void doSendKeysWithWait(By locator, String keys, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(keys);
	}

	/**
	 * 
	 * @param email
	 * @param pass
	 * @param companyName
	 * @param mobile
	 * 
	 *                    Without wait or click on email field -- this method throws
	 *                    an InvalidElementStateException
	 */

	public void fillForm(String email, String pass, String companyName, String mobile) {
		// doClick(userEmail);
		// doSendKeys(userEmail, email);
		System.out.println(getElement(userEmail).isEnabled());
		doSendKeysWithWait(userEmail, email, 0);
		doSendKeys(password, pass);
		doSendKeys(company, companyName);
		doSendKeys(mobileNum, mobile);
		doClick(submitBtn);
	}

	public void fillFirstCrushField(String exName) {
		doSendKeys(firstCrush, exName);
	}

	public void clickOnSVGElement(String fname, String lname) {
		doClick(editIconSvg);
		// doSendKeys(enterFirstName, fname);
		// doSendKeys(enterLastName, lname);
		System.out.println(getElement(enterFirstName).isEnabled());
		doSendKeys(enterFirstName, fname);
		System.out.println(getElement(enterLastName).isEnabled());
	}

	public void dropdownActions() {
		
	}
	
	public boolean getAllWindowHandles(String expectedTitleValue) {		
	
		Actions action = new Actions(driver);
		action.moveToElement(getElement(checkOutHereBtn)).build().perform();
		
		List<WebElement> dropDownOptions = getElements(dropdownOptions);
		List<String> allLinks = new ArrayList<>();
		
		for(WebElement e : dropDownOptions) {
			allLinks.add(e.getAttribute("href"));
		}
		
		for(String s : allLinks) {
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(s);
		}
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<>(windowHandles);
		
		for(String s : windows) {
			String title = driver.switchTo().window(s).getTitle();
			System.out.println(title);
			
			if(title.equals(expectedTitleValue)) {
				return true;
			}
			
			driver.switchTo().window(s).close();
		}
	
		driver.switchTo().window(parentWindowHandle);
		
		//System.out.println(windows);
		return false;
		
	}


}
