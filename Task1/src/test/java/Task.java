import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task {
    WebDriver driver;
    String product = "add-to-cart-test.allthethings()-t-shirt-(red)";
    String productName = "//*[@id=\"item_3_title_link\"]/div";

    @BeforeTest
    public void Start() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void verifyItemCart(){
        driver.findElement(By.id(product)).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement name = driver.findElement(By.xpath(productName));
        String nameText = name.getText();

        if(itemIsInList()){
            System.out.println("I find Item in cart");
        }else {
            System.out.println("I not find Item in cart");
        }

        if(nameText.contains("Test.allTheThings() T-Shirt (Red)")){
            System.out.println("The shirt is in the cart");
        }else {
            System.out.println("The shirt is not in the cart");
        }
    }

    public boolean itemIsInList() {
        List<WebElement> listItems = driver.findElements(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]"));
        return !listItems.isEmpty();
    }

    @AfterTest
    public void Final(){
        driver.close();
    }
}
