package org.example.pageObjects.wikipedia;

import org.example.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WikipediaPage extends BasePage {
    // Locators
    private final By searchBox = By.name("search");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By pageTitle = By.id("firstHeading");
    private final By contentText = By.cssSelector("#mw-content-text p");

    public WikipediaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void loadPage() {
        driver.get("https://www.wikipedia.org/");
    }

    /**
     * Performs a search for the given query
     *
     * @param query The search term
     */
    public void search(String query) {
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBox)
        );
        searchInput.sendKeys(query);
        driver.findElement(searchButton).click();
    }

    /**
     * Gets the page title after search
     *
     * @return The text of the page title
     */
    public String getPageTitle() {
        WebElement titleElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        );
        return titleElement.getText();
    }

    /**
     * Gets the content of the page after waiting for first
     * element to load
     *
     * @return List of paragraph elements
     */
    public List<WebElement> getTextContent() {
        return getVisibleElements(contentText);
    }
}
