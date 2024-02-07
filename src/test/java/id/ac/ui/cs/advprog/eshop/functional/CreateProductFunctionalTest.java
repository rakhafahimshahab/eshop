package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }

    @Test
    void checkCreateProduct(ChromeDriver driver) {
        String productName = "Example Product";
        int productQuantity = 50;

        driver.get(baseUrl);

        // Fill in the product creation form
        WebElement inputProductName = driver.findElement(By.id("nameInput"));
        inputProductName.clear();
        inputProductName.sendKeys(productName);

        WebElement inputProductQuantity = driver.findElement(By.id("quantityInput"));
        inputProductQuantity.clear();
        inputProductQuantity.sendKeys(String.valueOf(productQuantity));

        // Submit the form
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        // Wait for redirection or AJAX call to complete and list to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()=\"Product' List\"]"))); // Adjust as per your list's identifier

        // Verify the product is in the list
        WebElement newProductName = driver.findElement(By.xpath("//td[contains(text(), '" + productName + "')]")); // Adjust XPath as needed
        assertEquals(productName, newProductName.getText());

    }
}
