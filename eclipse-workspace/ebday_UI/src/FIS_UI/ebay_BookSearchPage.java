package FIS_UI;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ebay_BookSearchPage {
	
	@Test
	  public void add_BooktoCart_Validate() 
	  {
		  try {
				
				ChromeOptions options=new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);//Waits for all the resources to be downloaded.
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("start-maximized");

				
				WebDriver driver=WebDriverManager.chromedriver().capabilities(options).create();
		        driver.manage().deleteAllCookies();

		        // 1. Navigate to eBay's book search page
		        driver.get("https://www.ebay.com/sch/i.html?_nkw=books");
		        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		        
			      List<WebElement> al=driver.findElements(By.xpath("//div[@class='s-item__title']"));

			      WebElement firstBook = al.get(4);
			      wait.until(ExpectedConditions.elementToBeClickable(firstBook));

			      System.out.println(al.get(4).getText());

		        // 2. Click the first book in the search results
		        firstBook.click();
		        System.out.println("first clicked");
		        String parent=driver.getWindowHandle();
		        Set<String> set= driver.getWindowHandles();
		        
		        Iterator<String> I1= set.iterator();
		        while(I1.hasNext())
		        {
		        String child_window=I1.next();


		        if(!parent.equals(child_window))
		        {
		        driver.switchTo().window(child_window);
		        System.out.println(driver.switchTo().window(child_window).getTitle());
		        }
		        }

		        // Wait for the see in cards 
		      
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='atcBtn_btn_1']")));

		        // Click the 'Add to Cart' button
		        WebElement addToCartButton = driver.findElement(By.xpath("//a[@id='atcBtn_btn_1']"));
		        addToCartButton.click();
		        
		        

		        // Wait for the cart icon to update (indicating item is added)
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@role='img'][normalize-space()='1']")));

		        // Navigate to the cart to verify the item is added
		        driver.get("https://cart.ebay.com");

		        // Wait for the cart to load and verify that the cart contains 1 item
		        WebElement cartItemCount = driver.findElement(By.cssSelector(".cart-bucket"));
		        String cartText = cartItemCount.getText();

		        // Verify that 1 book is added to the cart
		        if (cartText.contains("1 item")) {
		            System.out.println("Successfully added 1 book to the cart!");
		        } else {
		            System.out.println("Failed to add book to the cart.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        // Close the browser
		       // driver.quit();
		    }
		  }

}
