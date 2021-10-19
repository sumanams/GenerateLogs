package com.test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class LoginTest {
	WebDriver driver;
	
	//Create a logger object for generating our own log
	Logger log= Logger.getLogger(LoginTest.class);
	
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\suman\\Downloads\\chromedriver\\chromedriver.exe");
		driver =new ChromeDriver();
		
		log.info("Setting up browser");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	
		driver.get("https://www.freecrm.com/");
		log.info("Launched the url");
	}
	
	@Test(priority=1)
	public void freeCrmTitleTest() {
		String title=driver.getTitle();
		System.out.println(title);
		log.info("title is :"+title);
		Assert.assertEquals(title, "#1 Free CRM customer relationship management software cloud");
	}
	
	@Test(priority=2)
	public void freeCrmLogoTest() {
		Boolean logoDisplayed=driver.findElement(By.xpath("//image[@src='/images/cogtiny1.jpg']")).isDisplayed();
		Assert.assertTrue(logoDisplayed);
		log.fatal("Couldnt find the element");
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
