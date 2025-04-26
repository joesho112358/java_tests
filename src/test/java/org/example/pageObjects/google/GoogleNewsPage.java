package org.example.pageObjects.google;

import org.example.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleNewsPage extends BasePage {
    // Locators
    private final By initialResultsContainer = By.cssSelector("main article");
    private final By searchBox = By.cssSelector("input[aria-label='Search for topics, locations & sources']");
    private final By searchButton = By.cssSelector("button[aria-label='Search']");
    private String getResultArticlesSelector(String term) {
        return "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + term.toLowerCase() + "')]";
    }

    public GoogleNewsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Navigates to Google News and returns the initial
     * results container for later validation
     *
     * @return The initial visible article element
     */
    public WebElement loadPage() {
        driver.get("https://news.google.com/");
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(initialResultsContainer)
        );
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
     * Waits for the initial results to disappear, indicating new results have loaded
     *
     * @param initialContainer The initial results container
     */
    public void waitForInitialResultsToDisappear(WebElement initialContainer) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(initialContainer));
        } catch (Exception e) {
            System.out.println("Initial results container still present or stale: " + e.getMessage());
        }
    }

    /**
     * Waits for and returns the search results articles
     *
     * @return List of visible article elements
     */
    public List<WebElement> getResultsArticles(String term) {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(getResultArticlesSelector(term))
                )
        );
    }
}
