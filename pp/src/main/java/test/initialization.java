package test;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class initialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.get("https://d3pv22lioo8876.cloudfront.net/tiptop/");
	}

}
