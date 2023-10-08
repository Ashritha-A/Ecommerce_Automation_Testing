package Testing1;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Testing1.SignUpPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import Testing1.WelcomeText;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class MainPage {
	WebDriver driver;
	String baseUrl;
	Long startTime;
	Long endTime;
	Long duration;
	double seconds;
	SignUpPage sup;
	WelcomeText wt;
	SignInPage sip;
	String firstname;
	String lastname;
	SelectionPage sp;
	ProductDetailPage pdp;
	CartPage cp;
	ExtentReports report;
	ExtentTest test;
	String dir = System.getProperty("user.dir")+"//Evidence//";
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "D:\\Eclipse Projects\\Drivers\\chromedriver.exe");
	  report = new ExtentReports("E:\\Pictures\\ProjectReport.html");
	  test = report.startTest("Project_Report");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));   
	  baseUrl = "https://magento.softwaretestingboard.com/";
	  sup=new SignUpPage(driver);
	  wt = new WelcomeText(driver);
	  sip = new SignInPage();
	  sp = new SelectionPage(driver);
	  pdp = new ProductDetailPage(driver);
	  cp = new CartPage(driver);
	  
  }

  @Test(priority=0)
  public void OpenUrl() throws InterruptedException, IOException {
	  startTime = System.nanoTime();
	  driver.get(baseUrl);
	  String tittle = driver.getTitle();
	  System.out.println("TITTLE OF THE PAGE IS : "+tittle);
	  endTime = System.nanoTime();
	  duration = endTime-startTime;
	  seconds = (double)duration/1000000000.0;
	  System.out.println("PERFORMANCE TIME OF OPENING/LOADING WEBSITE : " +seconds);
	  Thread.sleep(2000);
	  String filename = "ss1"+".png";
	  File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot1, new File(dir + filename));
  }
  
@Test(priority=1)
  public void SignUp() throws InterruptedException, IOException {
	  System.out.println("HI");
	  driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[3]/a")).click();
	  Thread.sleep(3000);
	  sup.getProperties();
	  firstname = sup.getfname();
	  lastname = sup.getlname();
	  wt.getWelcomeText(firstname,lastname);
	  String filename = "ss2"+".png";
	  File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot1, new File(dir + filename));
  }
  
 @Test(priority=2)
  public void LogOut() throws InterruptedException, IOException {
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")).click();
	  String filename = "ss3"+".png";
	  File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot1, new File(dir + filename));
  }
 
  @Test(priority=3, dataProvider ="LoginInputs", dataProviderClass = SignInPage.class)
  public void SignIn(String userid, String password) throws InterruptedException, IOException {
	  driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.id("email")).sendKeys(userid);
	  Thread.sleep(2000);
	  driver.findElement(By.id("pass")).sendKeys(password);
	  Thread.sleep(2000);
	  String filename = "ss4"+".png";
	  File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot1, new File(dir + filename));
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(2000);
  }
  
  @Test(priority=4)
  public void selection() throws InterruptedException, IOException {
	 sp.mouseHoverDetails(dir);
	 Thread.sleep(1000);
	 pdp.ProdSelection(dir);
	 String filename3 = "ss7"+".png";
	 File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 FileUtils.copyFile(screenshot3, new File(dir + filename3));
  }
  
  @Test(priority=5)
  public void Cart_Checkout() throws InterruptedException, IOException {
	 cp.cartSelect();
	 Thread.sleep(2000);
	 cp.checkout(dir);
	 Thread.sleep(2000);
	 cp.bill(dir);
	 String filename3 = "ss10"+".png";
	 File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 FileUtils.copyFile(screenshot3, new File(dir + filename3));
  }
 
  @AfterClass
  public void afterClass() throws InterruptedException, IOException {
	  Thread.sleep(2000);
	  String fname="ev1"+".png";
	  String dir = "E:\\Pictures\\";
	  File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(ss, new File(dir+fname));
	  String dest = dir+fname;
	  String imgpath = test.addScreenCapture(dest);
	  test.log(LogStatus.PASS, "PASSED !!" ,imgpath);
      driver.quit();
      report.endTest(test);
      report.flush();
  }
  

}
