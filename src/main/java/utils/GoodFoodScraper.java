package utils;

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
    private static final String VALUES_SQL = "('%s', '%s', '%s', '%s')";
    private static final String DESTINATION_FOLDER = "src/main/resources/static/recipe-images/";
    
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            DRIVER.manage()
                    .timeouts()
                    .implicitlyWait(Duration.ofSeconds(2));
            for (int c=0; c < PAGES_TO_SCRAPE; c++) {
                crawl(c);
            }
        } finally {
            DRIVER.close();
        }
    }

    private static void crawl(int page) throws IOException, InterruptedException {
        DRIVER.get(URL + "&page=" + (page + 1));

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
        By premium = By.className("premium-identifier");
        By title = By.className("heading-4");
        By description = By.cssSelector(".card__description > p");
        By recipeUrl = By.cssSelector(".link.d-block");
        By image = By.className("image__img");

        for (WebElement element : DRIVER.findElements(card)) {
            if (!element.findElements(premium).isEmpty()) {
                continue;
            }

            String imageUrl = element.findElement(image).getAttribute("src");
            if (imageUrl.contains("?")) {
                imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("?"));
            }
            
            ImageDownloader.download(imageUrl, DESTINATION_FOLDER);

            System.out.printf(VALUES_SQL + ",%n",
                    element.findElement(title).getText().replace("'", "''"),
                    element.findElement(description).getAttribute("textContent").replace("'", "''"),
                    imageUrl.substring(imageUrl.lastIndexOf("/") + 1),
                    element.findElement(recipeUrl).getAttribute("href")
            );
        }
    }

    private static boolean elementExists(By element) {
        return !DRIVER.findElements(element).isEmpty();
    }
}
