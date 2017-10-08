package com.pageobjectmodel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Imgur1 {
	
	
	WebDriver driver;

	@BeforeClass
	public void setUp() {
 
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sagar\\Desktop\\Suma Testing\\chromedriver.exe");
	FirefoxDriver driver = new 	FirefoxDriver();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void ImageUpload() throws InterruptedException, AWTException {
		driver.get("https://imgur.com/");
		WebElement NewpostButton = driver.findElement(By.id("create-dropdown-button"));
		NewpostButton.click();
		WebElement UploadButton = driver.findElement(By.xpath("//*[@id='createDropdown']/ul/li[1]/a"));
		UploadButton.click();

		Thread.sleep(2000);
		WebElement BrowseElement = driver.findElement(By.xpath("//*[@id='upload-modal']/div[2]/div[2]/label"));
		BrowseElement.click();

		StringSelection ss = new StringSelection("C:\\Users\\sagar\\Downloads\\image.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);


		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		String bodyText = driver.findElement(By.xpath("//*[@id='upload-global']/div/span[2]/div/div[1]/div/div[1]/div/div[2]/h1")).getText();
		Assert.assertTrue(bodyText.contains("Give your post a title..."));

}
}
