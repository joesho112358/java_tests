package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObjects.GoogleNewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GoogleNewsTest {
    private WebDriver driver;
    private GoogleNewsPage googleNewsPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        googleNewsPage = new GoogleNewsPage(driver, wait);
    }

    @Test
    public void testGoogleNewsSearch() {
        String searchTerm = "Technology";
        WebElement initialResultsContainer = googleNewsPage.loadPage();
        googleNewsPage.search(searchTerm);

        googleNewsPage.waitForInitialResultsToDisappear(initialResultsContainer);

        List<WebElement> resultsArticles = googleNewsPage.getResultsArticles(searchTerm);
        assertFalse(
                resultsArticles.isEmpty(),
                "Search results articles should be displayed"
        );
        boolean containsTechnology = resultsArticles.stream()
                .anyMatch(
                        article -> article.getText().toLowerCase().contains(
                                searchTerm.toLowerCase()
                        )
                );
        assertTrue(containsTechnology, "At least one article should contain 'Technology'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}