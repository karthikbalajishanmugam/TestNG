package org.Flipkart;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test; 



public class Mobile {
	
	@DataProvider (name = "mobile")
	public Object[][] mobilename(){
		return new Object[][] {{"realme"}};
	}
	
static WebDriver driver ;
	@Parameters({"url"})
	@BeforeClass
	public static void Lanch(String url) throws InterruptedException {
		System.out.println("LanchBrowser");
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\balas\\eclipse-workspace\\org.Test\\driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	Thread.sleep(3000);
	}
	
	@AfterClass
	public static void quit() {
	driver.quit();
	
	}
	
	long StartTime;
	
	@BeforeMethod
	public void Beforemethod() {
		System.out.println("Beforemethod");
		StartTime =System.currentTimeMillis();
		
	}
	
	@AfterMethod 
	public void Aftermethod() {
		System.out.println("Aftermethod");
		long EndTime =System.currentTimeMillis();
		System.out.println("TimeTaken:"+ + (EndTime - StartTime));
	}
	
	
	
	
	
	
	@Test(priority = 1)
	public void Login() throws InterruptedException {
		System.out.println("login");
		driver.findElement(By.xpath("(//input[@autocomplete = 'off'])[2]")).sendKeys("9443822714");
		driver.findElement(By.xpath("(//input[@autocomplete = 'off'])[3]")).sendKeys("bala@1704");
		driver.findElement(By.xpath("(//button[@type= 'submit'])[2]")).click();
		Thread.sleep(3000);
	}
	
	@Test(priority = 2, dataProvider = "mobile")
	
	public void Search(String name) throws InterruptedException, IOException {
		System.out.println("search");
		driver.findElement(By.name("q")).sendKeys(name);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(3000);
	}
		
	@Test(priority = 3)
	public void selectMobile () throws InterruptedException  {
		System.out.println("Select mobile");
		WebElement select = driver.findElement(By.xpath("(//div[@class='_4rR01T'])[4]"));
		select.click();
		Thread.sleep(3000);
		String s = select.getText();
		System.out.println(s);
String prwnd = driver.getWindowHandle();
		
		Set<String> allwin = driver.getWindowHandles();
		for(String x :allwin ) {
			if(!x.equals(prwnd)){
				driver.switchTo().window(x);
				
			}
		}
	}
	
	@Test(priority = 4)
	public void addToCart() {
		
		System.out.println("addToCart");
		
		System.out.println("drpdown & screenshot");
		WebElement buy = driver.findElement(By.xpath("//button[text()='BUY NOW']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", buy);
		String text = buy.getText();
		
		Assert.assertTrue(buy.isDisplayed());
		
		Assert.assertEquals("BUY", text);

	}

	
}
	


