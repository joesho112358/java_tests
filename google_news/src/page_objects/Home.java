package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home extends Header {

	private static WebElement element = null;

	public static WebElement lnk_MyAccount(WebDriver driver){
		element = driver.findElement(By.id(""));
		return element;	 
	}

}
