package Testing1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage {
	WebDriver driver;

	public ReviewPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterReview() throws InterruptedException {
		Thread.sleep(2000);	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement rating = wait.until(ExpectedConditions.elementToBeClickable(By.id("Rating_1_label")));
		rating.click();
		
		driver.findElement(By.id("summary_field")).sendKeys("Average top");
		driver.findElement(By.id("review_field")).sendKeys("Quality is poor. it can be better");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"review-form\"]/div/div/button/span")).click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));		
		WebElement confirmMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")));
		String confirmReviewMessage = confirmMsg.getText();
		System.out.println("THE CONFIRM DISPLAYED MESSAGE OF REVIEW SUBMITTED IS : "+ confirmReviewMessage);
		Thread.sleep(3000);
		
	}
	
}
