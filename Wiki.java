package com.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Wiki {
	
	
	WebDriver driver;

	@BeforeClass
	public void setUp() {
// driver = new FirefoxDriver();
		// Instantiate WebDriver and open a new Chrome window to Wikipedia
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sagar\\Desktop\\Suma Testing\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void test1_basicSearchBarTest() {

		driver.get("http://www.wikipedia.org");

		// Find the search box by id and input text
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		searchBox.sendKeys("Puppies");

		// Find the search button by Xpath and click
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"search-form\"]/fieldset/button"));
		searchButton.click();
		
		// Assert on the Puppy wiki page
		Assert.assertTrue(driver.getCurrentUrl().equals("https://en.wikipedia.org/wiki/Puppy"));
	}

	@Test (dependsOnMethods={"test1_basicSearchBarTest"})
	public void test2_hideContentsTest() {

		WebElement contentsPanel = driver.findElement(By.id("toc"));
		contentsPanel.findElement(By.className("togglelink")).click();
		
		// Assert panel is hidden
		Assert.assertTrue(contentsPanel.getAttribute("class").equals("toc tochidden"));
	}

	@Test (dependsOnMethods={"test2_hideContentsTest"})
	public void test3_autocompleteSearchTest() throws InterruptedException {

		WebElement searchBar = driver.findElement(By.name("search"));
		searchBar.sendKeys("Franc");

		// Wait up to three seconds for the suggestions to show
		(new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				return driver.findElement(By.className("suggestions-results")).findElement(By.xpath("//a[@title='France']")).isDisplayed();
			}
		});
		driver.findElement(By.className("suggestions-results")).findElement(By.xpath("//a[@title='France']")).click();
		
		// Assert on the France wiki page
		Assert.assertTrue(driver.getCurrentUrl().equals("https://en.wikipedia.org/wiki/France"));
	}	
}




