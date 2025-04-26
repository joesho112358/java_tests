package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObjects.wikipedia.WikipediaPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class WikipediaTest {
    private WebDriver driver;
    private WikipediaPage wikipediaPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        wikipediaPage = new WikipediaPage(driver, wait);
    }

    @Test
    public void testWikipediaSearch() {
        wikipediaPage.loadPage();

        String searchTerm = "Artificial Intelligence";
        wikipediaPage.search(searchTerm);

        String pageTitle = wikipediaPage.getPageTitle();
        List<WebElement> contentParagraphs = wikipediaPage.getTextContent();

        // Assert that the title or content contains the search term
        boolean containsSearchTerm = pageTitle.toLowerCase().contains(searchTerm.toLowerCase()) ||
                contentParagraphs.stream()
                        .anyMatch(p -> p.getText().toLowerCase().contains(
                                searchTerm.toLowerCase())
                        );
        assertTrue(
                containsSearchTerm,
                "Page title or content should contain '" + searchTerm + "'"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
