package Testing1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	WebDriver driver;
	String pass1;
	String pass2;
	String field1;
	String field2;
	String field3;
	String field41;
	String field42;
	String fnamestatus = "";
	String lnamestatus = "";
	String emailstatus = "";
	
	
	@FindBy(id="firstname")
	WebElement fname;	
	@FindBy(id="lastname")
	WebElement lname;	
	@FindBy(id="email_address")
	WebElement email;
	@FindBy(id="password")
	WebElement pswd;
	@FindBy(id="password-confirmation")
	WebElement conpswd;
	
	public void getProperties() throws IOException, InterruptedException {
		Properties prop = new Properties ();
		try {
			FileInputStream fs = new FileInputStream("D:\\Eclipse Projects\\Java racttice\\Ecommerce_Project\\src\\Testing1\\SignUpFields.properties");
			prop.load(fs);
			
			field1 = prop.getProperty("firstname");
			firstName(driver,field1);
			field2 = prop.getProperty("lastname");
			lastName(driver,field2);
			field3 = prop.getProperty("emailid");
			email(driver,field3);
			field41 = prop.getProperty("originalpassword");
			field42 = prop.getProperty("confirmpassword");
			password(driver,field41,field42);
			
		} catch (FileNotFoundException e) {
			System.out.println("PATH NOT FOUND !!! PLEASE DO ENTER VALID PATH");
		}
	}
	
	
	public SignUpPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}
	
   public void  firstName(WebDriver driver, String key) throws InterruptedException {
		   fname.sendKeys(key);
		   Thread.sleep(1000);
		   fnamestatus = fname.getAttribute("value");
   }
   
  public void lastName(WebDriver driver, String key) throws InterruptedException{
	    lname.sendKeys(key);
	    Thread.sleep(1000);
	    lnamestatus = lname.getAttribute("value");
	}
  
   public void email(WebDriver driver, String key) throws InterruptedException {
	    email.sendKeys(key);
	    Thread.sleep(1000);
	    emailstatus = email.getAttribute("value");
	    
	}
   
   public void password(WebDriver driver, String key1, String key2) throws InterruptedException{
	    pswd.sendKeys(key1);
	    String pass1 = pswd.getAttribute("value");
		conpswd.sendKeys(key2);
	    String pass2 = conpswd.getAttribute("value");
	    checkDetails(pass1,pass2);
	}
   
  /* public void checkDetails(String p1, String p2) throws InterruptedException {
	 if(p1.equals(p2)) {
			 clickSubmit(driver);
			 if(fnamestatus.isEmpty()) {
			     Thread.sleep(1000);
				 WebElement reqdfname = driver.findElement(By.id("firstname-error"));
				 boolean fnameErrorStatus = reqdfname.isDisplayed(); 
				 String fnameErrorMessage = reqdfname.getText();
				 System.out.println("\nDisplayed message is : " +fnameErrorMessage +"\nAnd its displayed status is : "+ fnameErrorStatus);
			}
			 if(emailstatus.contains("@") && emailstatus.contains(".")) {
			 }else {
				 WebElement emailerror = driver.findElement(By.id("email_address-error"));
				 boolean emailerrorstatus = emailerror.isDisplayed();
				 String emailerrormessage = emailerror.getText();
				 System.out.println("\nDisplayed message is : " +emailerrormessage +"\nAnd its displayed status is : "+ emailerrorstatus);
			 }
	 }else {
		 clickSubmit(driver);
		 WebElement error = driver.findElement(By.id("password-confirmation-error"));
		 boolean errorStatus = error.isDisplayed();
		 String errorMessage = error.getText();
		 System.out.println("\nDisplayed message is : " +errorMessage +"\nAnd its displayed status is : "+ errorStatus);
		 if(fnamestatus.isEmpty()) {
		     Thread.sleep(1000);
			 WebElement reqdfname = driver.findElement(By.id("firstname-error"));
			 boolean fnameErrorStatus = reqdfname.isDisplayed(); 
			 String fnameErrorMessage = reqdfname.getText();
			 System.out.println("\nDisplayed message is : " +fnameErrorMessage +"\nAnd its displayed status is : "+ fnameErrorStatus);
		}
		 if(emailstatus.contains("@") && emailstatus.contains(".")) {
		 }else {
			 WebElement emailerror = driver.findElement(By.id("email_address-error"));
			 boolean emailerrorstatus = emailerror.isDisplayed();
			 String emailerrormessage = emailerror.getText();
			 System.out.println("\nDisplayed message is : " +emailerrormessage +"\nAnd its displayed status is : "+ emailerrorstatus);
		 }
	  }
   }*/
   
   public void checkDetails(String p1, String p2) throws InterruptedException {
	   clickSubmit(driver);
		 if(p1.equals(p2)) {}
		 else {
			 WebElement error = driver.findElement(By.id("password-confirmation-error"));
			 boolean errorStatus = error.isDisplayed();
			 String errorMessage = error.getText();
			 System.out.println("\nDisplayed message is : " +errorMessage +"\nAnd its displayed status is : "+ errorStatus);
		 }
			 if(fnamestatus.isEmpty()) {
				 WebElement reqdfname = driver.findElement(By.id("firstname-error"));
				 boolean fnameErrorStatus = reqdfname.isDisplayed(); 
				 String fnameErrorMessage = reqdfname.getText();
				 System.out.println("\nDisplayed message is : " +fnameErrorMessage +"\nAnd its displayed status is : "+ fnameErrorStatus);
			}
			 if(emailstatus.contains("@") && emailstatus.contains(".")) {}
			 else {
				 WebElement emailerror = driver.findElement(By.id("email_address-error"));
				 boolean emailerrorstatus = emailerror.isDisplayed();
				 String emailerrormessage = emailerror.getText();
				 System.out.println("\nDisplayed message is : " +emailerrormessage +"\nAnd its displayed status is : "+ emailerrorstatus);
			 }
	   }
  
  public void clickSubmit(WebDriver driver) throws InterruptedException{
		  WebElement submit = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button/span"));
		  submit.click(); 
		  Thread.sleep(1000);
   }


  public String getfname() {
	return fnamestatus;
  }


  public String getlname() {	
	return lnamestatus;
  }
}
