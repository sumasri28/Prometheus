package com.pageobjectmodel;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {
	@BeforeTest
	public static void setup(){
		Wikipedia.openBrowser();
	}
	@Test(priority=0)
	public void basicSearchBar() throws InterruptedException{
		Wikipedia.searchText("puppies");
		Wikipedia.clickOnSearch();
	}
	@Test(priority=1)
	public void hideContentsText() throws InterruptedException{
		Wikipedia.clickOnHideContents();
	}
	/*@Test(priority=2)
	public void autocompleteSearchTest() throws InterruptedException{
		Wikipedia.clickOnHideContents();
	}*/
	@Test(priority=2)
	public void autocompleteSearchText() throws InterruptedException{
		Wikipedia.autoSearch("Franc");
	}
	@Test(priority=3)
	public static void closeBroser(){
		Wikipedia.closeBrowser();
	}
}
