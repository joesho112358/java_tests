package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Finds elements matching the given By selector and filters
     * out those that are not visible.
     *
     * @param locator The By selector to locate elements.
     * @return A list of visible WebElements.
     */
    public List<WebElement> getVisibleElements(By locator) {
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
        );

        return elements.stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
    }
}
