package Testing1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;
	ShippingDetails sd;
	String completeSearchText = "jacket";
	String partialSearchText = "jac";
	
	@FindBy(id="option-label-color-93-item-56")
	WebElement orangeColour;
	
	@FindBy(id="product-updatecart-button")
	WebElement updateButton;
	
	@FindBy(id="search")
	WebElement search;
	
	@FindBy(id="top-cart-btn-checkout")
	WebElement checkout;
	
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void cartSelect() throws InterruptedException {
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement cartBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
		cartBtn1.click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"mini-cart\"]/li/div/div/div[3]/div[1]/a")).click();
		Thread.sleep(1000);
		orangeColour.click();
		updateButton.click();
		
		String currentUrl = driver.getCurrentUrl();
		
		search.sendKeys(partialSearchText);
		Thread.sleep(3000);
		WebElement searchTable= driver.findElement(By.xpath("//*[@id=\"search_autocomplete\"]/ul"));
		List<WebElement> listing = searchTable.findElements(By.tagName("li"));
		Thread.sleep(2000);
		
		for(WebElement searchList : listing) {
			if(searchList.getText().contains(completeSearchText)) {
				searchList.click();
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(2000);
		
		driver.navigate().to(currentUrl);
		Thread.sleep(2000);
		WebElement cartBtn2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
		cartBtn2.click();
		checkout.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shipping\"]/div[1]")));
	}
	
	public void checkout(String directory) throws InterruptedException, IOException {
		sd= new ShippingDetails(driver);
		sd.entryDetails();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(0));
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-same-as-shipping-checkmo")));
		checkbox.click();
		Thread.sleep(3000);
		driver.findElement(By.id("billing-address-same-as-shipping-checkmo")).click();
		Thread.sleep(2000);
		
		WebElement amount = driver.findElement(By.xpath("//*[@id=\"opc-sidebar\"]/div[1]/table/tbody/tr[3]/td/strong/span"));
		String amountDollars = amount.getText();
		System.out.println("TOTAL AMOUNT OF THE SELECTED ITEMS ARE : "+amountDollars);
		
		String filename3 = "ss8"+".png";
		File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot3, new File(directory + filename3));
		
		driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")).click();
	}
	
	public void bill(String directory) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[1]/a")));
		String idNumber = id.getText();
		System.out.println("YOUR ORDER NUMBER FOR PURCHASE IS : " + idNumber);
		Thread.sleep(2000);
		id.click();
		Thread.sleep(2000);
		
		String filename3 = "ss9"+".png";
		File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot3, new File(directory + filename3));
		
		driver.navigate().back();
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement receipt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/a")));
		receipt.click();
	}

}
