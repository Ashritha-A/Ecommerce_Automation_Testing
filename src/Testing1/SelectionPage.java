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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectionPage {
	WebDriver driver;
	JavascriptExecutor js;
	
	@FindBy(id="ui-id-4")
	WebElement womenSection;
	
	@FindBy(id="ui-id-9")
	WebElement topsSection;
	
	@FindBy(id="sorter")
	WebElement priceDrop;
	
	@FindBy(id="mode-list")
	WebElement listSelect;

	public SelectionPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void mouseHoverDetails(String directory) throws InterruptedException, IOException {
		js = (JavascriptExecutor)driver;
		Actions act = new Actions(driver);
		act.moveToElement(womenSection).perform();
		Thread.sleep(1000);
		
		topsSection.click();
		Thread.sleep(1000);
		
		Select dropCategory = new Select(priceDrop);
		dropCategory.selectByValue("price");
		Thread.sleep(1000);
		
		WebElement climate = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[13]"));
		climate.click();
		Thread.sleep(1000);
		WebElement Indoor = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[13]/div[2]/ol/li[4]/a"));
		Indoor.click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"layered-filter-block\"]/div[2]/div[2]/a/span")).click();
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,-400);");
		Thread.sleep(1000);
		String filename3 = "ss5"+".png";
		File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot3, new File(directory + filename3));
		js.executeScript("window.scrollBy(0,400);");
		
		listSelect.click();
		
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[3]/div/a/span/span/img")).click();
		
	}

}
