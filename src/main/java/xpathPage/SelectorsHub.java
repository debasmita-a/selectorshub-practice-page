package xpathPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectorsHub {

	public WebDriver driver;
	
	By userEmail = By.name("email");
	By password = By.id("pass");
	By company = By.name("company");
	By mobileNum = By.name("mobile number");
	By submitBtn = By.xpath("//button[@value='Submit']");
	
	By firstCrush = By.id("inp_val");
	
	By editIconSvg = By.xpath("(//*[local-name()='svg'])[2]");
	By enterFirstName = By.xpath("//input[@placeholder='First Enter name']");
	By enterLastName = By.xpath("//input[@placeholder='Enter Last name']");
	
	By checkOutHereBtn = By.xpath("//button[text()='Checkout here']"); //hoverover
	By dropdownOptions = By.xpath("//button[text()='Checkout here']/following-sibling::div//a");
	By carsDropdown = By.xpath("//div[@class='dropdown']/following-sibling::select");
	By carsDropdownOptions = By.xpath("//div[@class='dropdown']/following-sibling::select/option");
	
	By datePicker = By.id("datepicker");	
	By calendar = By.name("the_date");
	
	By shadowDomInsideIFrameLink = By.xpath("//a[contains(text(),'shadow dom inside iframe')]");
	By iFrameInsideShadowDom = By.xpath("//a[contains(text(),'iframe inside shadow dom')]");
	
	By tableWithScroll = By.id("tableWrapper");
	By resultTable = By.id("resultTable");
	By allHeaders = By.xpath("//table[@id='resultTable']/thead//th//a"); //Use a HashMap
	By allUserRows = By.xpath("//table[@id='resultTable']/tbody//tr"); 
	
	// ************************ public constructor to initialize WenDriver ******************
	
	public SelectorsHub(WebDriver driver) {
		this.driver = driver;
	}
	
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("https://selectorshub.com/xpath-practice-page/");
		driver.manage().window().maximize();
	}

	// ************************* private element utilities ***********************************
	
	private WebElement getElement(By locator) {
		return driver.findElement(locator);
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
	 * Without wait or click on email field -- this method throws an InvalidElementStateException
	 */
	
	public void fillForm(String email, String pass, String companyName, String mobile) {
		//doClick(userEmail);
		//doSendKeys(userEmail, email);
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
		//doSendKeys(enterFirstName, fname);
		//doSendKeys(enterLastName, lname);			
		System.out.println(getElement(enterFirstName).isEnabled());
		doSendKeys(enterFirstName, fname);
		System.out.println(getElement(enterLastName).isEnabled());
	}
	
	
	
	
}