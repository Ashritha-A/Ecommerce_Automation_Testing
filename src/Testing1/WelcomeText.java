package Testing1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomeText {
	WebDriver driver;
	boolean check;
	
	public WelcomeText(WebDriver driver) {
		this.driver=driver;
		 PageFactory.initElements(driver, this);
	}

	public boolean getWelcomeText(String Fname, String Lname) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementLocated((By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span")), "Welcome, " +Fname+" "+Lname+"!"));
		Thread.sleep(5000);
		WebElement welcomeMessage = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span"));
		String textOfMsg = 	welcomeMessage.getText();
		System.out.println("\nWELCOME MESSAGE IS : " +textOfMsg);
		check = welcomeMessage.isDisplayed();
		return check;		
	}

}
