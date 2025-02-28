package utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

public class GoodFoodScraper {

    private static final String URL = "https://www.bbcgoodfood.com/search?q=Quick+and+easy+family+recipes";
    private static final int PAGES_TO_SCRAPE = 5;
    private static final WebDriver DRIVER = new ChromeDriver();

    private static JsonGenerator generator;

    public static void main(String[] args) throws IOException {
        try {
            DRIVER.manage()
                    .timeouts()
                    .implicitlyWait(Duration.ofSeconds(2));
            generator = new JsonFactory().createGenerator(System.out);
            generator.writeStartArray();
            for (int c=0; c < PAGES_TO_SCRAPE; c++) {
                crawl(c);
            }
        } finally {
            DRIVER.close();
            generator.writeEndArray();
            generator.close();
            System.out.println();
        }
    }

    private static void crawl(int page) throws IOException {
        DRIVER.get(URL + "?page=" + page);

        By cookieIframe = By.cssSelector("[title='SP Consent Message']");
        By cookieButton = By.cssSelector("[title*='Cookies']");

        if (elementExists(cookieIframe)) {
            DRIVER.switchTo().frame(DRIVER.findElement(cookieIframe));
            if (elementExists(cookieButton)) {
                DRIVER.findElement(cookieButton).click();
            }
            DRIVER.switchTo().defaultContent();
        }

        By card = By.className("search-result--list");
        By title = By.className("heading-4");
        By description = By.cssSelector(".card__description > p");
        By recipeUrl = By.cssSelector(".link.d-block");
        By image = By.className("image__img");

        // TODO filter locked recipes
        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
        for (WebElement element : DRIVER.findElements(card)) {
            String imageUrl = element.findElement(image).getAttribute("src");
            if (imageUrl.contains("?")) {
                imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("?"));
            }
            writer.writeValue(generator, new GoodFoodRecipe(
                    element.findElement(title).getText(),
                    element.findElement(description).getAttribute("textContent"),
                    element.findElement(recipeUrl).getAttribute("href"),
                    imageUrl));
        }
    }

    private static boolean elementExists(By element) {
        return !DRIVER.findElements(element).isEmpty();
    }
}
