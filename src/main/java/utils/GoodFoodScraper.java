package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class GoodFoodScraper {

    public static void main(String[] args) {
        WebDriver driver = null;
        List<GoodFoodRecipe> recipes;
        try {
            driver = new ChromeDriver();
            driver.manage()
                    .timeouts()
                    .implicitlyWait(Duration.ofSeconds(2));
            recipes = crawl(driver);
        } finally {
            Objects.requireNonNull(driver).close();
        }
        recipes.forEach(System.out::println);
    }

    private static List<GoodFoodRecipe> crawl(WebDriver driver) {
        driver.get("https://www.bbcgoodfood.com/search?q=Quick+and+easy+family+recipes");

        By cookieIframe = By.cssSelector("[title='SP Consent Message']");
        By cookieButton = By.cssSelector("[title*='Cookies']");

        driver.switchTo().frame(driver.findElement(cookieIframe));
        List<WebElement> cookieButtonElements = driver.findElements(cookieButton);
        if (!cookieButtonElements.isEmpty()) {
            cookieButtonElements.getFirst().click();
        }
        driver.switchTo().defaultContent();

        By card = By.className("search-result--list");
        By title = By.className("heading-4");
        By description = By.cssSelector(".card__description > p");
        By image = By.className("image__img");

        // TODO filter locked recipes
        return driver.findElements(card)
                .stream()
                .map(element -> {
                    String imageUrl = element.findElement(image).getAttribute("src");
                    if (imageUrl.contains("?")) {
                        imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("?"));
                    }
                    return new GoodFoodRecipe(
                            element.findElement(title).getText(),
                            element.findElement(description).getAttribute("textContent"),
                            imageUrl);
                })
                .toList();
    }
}
