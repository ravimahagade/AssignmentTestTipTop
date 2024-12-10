package Testcasses;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class TipTop_Testcases {

	    
	    WebDriver driver;

	    
	    @BeforeMethod
	    public void setUp() throws InterruptedException {
	        WebDriverManager.chromedriver().setup();  
	        driver = new ChromeDriver();              
	        driver.get("https://d3pv22lioo8876.cloudfront.net/tiptop/");
	        Thread.sleep(2000);// Navigate to URL
	    }

	    // Test Case 1: Verify that the text input element with xpath .//input[@name='my-disabled'] is disabled
	    @Test
	    public void TestDisabledTextInput() throws InterruptedException {
	        WebElement disabledInput = driver.findElement(By.xpath(".//input[@name='my-disabled']"));
	        Assert.assertTrue(!disabledInput.isEnabled(), "Input field is not disabled");
	        Thread.sleep(2000);
	    }

	    // Test Case 2: Verify that the text input with value 'Readonly input' is in readonly state using 2 xpaths
	    @Test
	    public void TestReadonlyInput() throws InterruptedException {
	        WebElement readonlyInput1 = driver.findElement(By.xpath(".//input[@value='Readonly input']"));
	        WebElement readonlyInput2 = driver.findElement(By.xpath(".//input[@type='text' and @value='Readonly input']"));
	        
	        Assert.assertNotNull(readonlyInput1.getAttribute("readonly"));
	        Assert.assertNotNull(readonlyInput2.getAttribute("readonly"));
	        Thread.sleep(3000);
	    }
 
	    // Test Case 3: Verify that the dropdown field to select color has 8 elements using 2 xpaths
	    @Test
	    public void testDropdownSize() {
	        WebElement dropdown1 = driver.findElement(By.xpath("//select[@class=\"form-select\"]"));
	        Select select1 = new Select(dropdown1);
	        Assert.assertEquals(select1.getOptions().size(), 8, "Dropdown does not have 8 options");
	        
	        WebElement dropdown2 = driver.findElement(By.xpath("//select[@name=\"my-select\"]"));
	        Select select2 = new Select(dropdown2);
	        Assert.assertEquals(select2.getOptions().size(), 8, "Dropdown does not have 8 options");
	    }

	    // Test Case 4: Verify that the submit button is disabled when no data is entered in Name field
	    @Test
	    public void testSubmitButtonDisabledWhenNoData() {
	        WebElement nameField = driver.findElement(By.xpath("//input[@id=\"my-name-id\"]"));
	        WebElement submitButton = driver.findElement(By.xpath("//button[@id=\"submit-form\"]"));
	        
	        nameField.clear(); // Ensure Name field is empty
	        Assert.assertFalse(submitButton.isEnabled(), "Submit button is not disabled");
	    }

	    // Test Case 5: Verify that the submit button is enabled when both Name and Password field are filled
	    @Test
	    public void testSubmitButtonEnabledWhenDataEntered() {
	        WebElement nameField = driver.findElement(By.xpath("//input[@id=\"my-name-id\"]"));
	        WebElement passwordField = driver.findElement(By.xpath("//input[@id=\"my-password-id\"]"));
	        WebElement submitButton = driver.findElement(By.xpath("//button[@id=\"submit-form\"]"));
	        
	        nameField.sendKeys("amazon");
	        passwordField.sendKeys("password@123");
	        
	        Assert.assertTrue(submitButton.isEnabled(), "Submit button is not enabled");
	    }

	    // Test Case 6: Verify that on submit of 'Submit' button, the page shows 'Received' text
	    @Test
	    public void testPageShowsReceivedTextAfterSubmit() {
	        WebElement nameField = driver.findElement(By.xpath("//input[@id=\"my-name-id\"]"));
	        WebElement passwordField = driver.findElement(By.xpath("//input[@id=\"my-password-id\"]"));
	        WebElement submitButton = driver.findElement(By.xpath("//button[@id=\"submit-form\"]"));
	        
	        nameField.sendKeys("Rmm");
	        passwordField.sendKeys("password123");
	        submitButton.click();
	        
	        WebElement receivedText = driver.findElement(By.xpath("//p[text()='Received!']"));
	        Assert.assertNotNull(receivedText, "Received text not found after form submission");
	    }

	    // Test Case 7: Verify that on submit of form, all the data passed to the URL
	    @Test
	    public void testFormDataPassedToURL() {
	        WebElement nameField = driver.findElement(By.xpath("//input[@id=\"my-name-id\"]"));
	        WebElement passwordField = driver.findElement(By.xpath("//input[@id=\"my-password-id\"]"));
	        WebElement submitButton = driver.findElement(By.xpath("//button[@id=\"submit-form\"]"));
	        
	        nameField.sendKeys("Rmm");
	        passwordField.sendKeys("password123");
	        submitButton.click();
	        
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains("name=Rmm") && currentURL.contains("password=password123"),
	                "username and password not passed to the URL correctly");
	    }

	    // Close the browser after each test
	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
	}



