package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header {
	
	private static WebElement element = null;

	//is this everywhere? doesn't look like it :(
	public static WebElement divHeaderWrapper(WebDriver driver){
		element = driver.findElement(By.className("google-one-wrapper"));
		return element;	 
	}

	public static WebElement divHeaderSearch(WebDriver driver){
		element = divHeaderWrapper(driver).findElement(By.name("q"));
		return element;	 
	}

	public static WebElement buttonHeaderSearch(WebDriver driver){
		element = divHeaderWrapper(driver).findElement(By.tagName("button"));
		return element;	 
	}

	public static void useSearch(WebDriver driver, String query){
		divHeaderSearch(driver).sendKeys(query);
		buttonHeaderSearch(driver).click();
	}
}
