import org.testng.annotations.Test;

import page_objects.Home;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class GoogleNews {

	WebDriver driver;

	@Test
	public void canSearchTheNews() {
		Home.useSearch(driver, "Google");
		// do validation later
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("https://news.google.com");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
