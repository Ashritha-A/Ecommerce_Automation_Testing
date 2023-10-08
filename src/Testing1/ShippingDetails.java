package Testing1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingDetails {
   WebDriver driver;
   
    @FindBy(name="firstname")
    WebElement first_name;
    
    @FindBy(name="lastname")
    WebElement last_name;
    
    @FindBy(name="company")
    WebElement company;
    
    @FindBy(name="street[0]")
    WebElement streetAddress1;
    
    @FindBy(name="street[1]")
    WebElement streetAddress2;
    
    @FindBy(name="city")
    WebElement city;
    
    @FindBy(name="region_id")
    WebElement state;
    
    @FindBy(name="postcode")
    WebElement zipCode;
    
    @FindBy(name="country_id")
    WebElement country;
    
    @FindBy(name="telephone")
    WebElement phno;
	
	public ShippingDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void entryDetails() throws InterruptedException {
			
			Thread.sleep(2000);
			company.sendKeys("HOME");
			streetAddress1.sendKeys("#201 3rd main,10th cross");
			streetAddress2.sendKeys("new horsman road");
			city.sendKeys("Heinsberg");
			Select sa = new Select(state);
			sa.selectByIndex(3);
			zipCode.sendKeys("23908");
			Select sa1 = new Select(country);
			sa1.selectByIndex(3);
			phno.sendKeys("9568741333");

			driver.findElement(By.xpath("//input[@value=\"flatrate_flatrate\"]")).click();
			
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")).click();
	}
}
