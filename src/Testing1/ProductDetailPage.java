package Testing1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProductDetailPage {
    WebDriver driver;
    JavascriptExecutor js;
    ReviewPage rp;
    
	@FindBy(id="tab-label-additional-title")
	WebElement moreInfo;
	
	@FindBy(id="tab-label-reviews-title")
	WebElement reviews;
	
	@FindBy(id="option-label-size-143-item-168")
	WebElement size;
	
	@FindBy(id="option-label-color-93-item-60")
	WebElement colour;
	
	@FindBy(id="qty")
	WebElement quantity;
	
	@FindBy(id="product-addtocart-button")
	WebElement addToCart;
	
	public ProductDetailPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void ProdSelection(String directory) throws InterruptedException, IOException {
		js = (JavascriptExecutor)driver;
		rp = new ReviewPage(driver);
		
		Thread.sleep(3000);
		
		String filename3 = "ss6"+".png";
		File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot3, new File(directory + filename3));
		
		moreInfo.click();
		reviews.click();
		Thread.sleep(1000);
		
		rp.enterReview();
		
		Thread.sleep(1000);
		size.click();
		colour.click();
		quantity.sendKeys("2");
		
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span")).click();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		quantity.clear();
		
		WebElement instock = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[3]/div[2]/div[1]/span"));
		String instockMsg = instock.getText();
		
		SoftAssert sa = new SoftAssert();

		if(instockMsg.equals("IN STOCK")) {
			sa.assertEquals(instockMsg, "IN STOCK");
			size.click();
			colour.click();
			quantity.sendKeys("1");
			Thread.sleep(1000);
			addToCart.click();	
			Thread.sleep(4000);
		}else {
			System.out.println("SELECTED DRESS IS NOT IN STOCK !!!");
		}
		
	}
}
