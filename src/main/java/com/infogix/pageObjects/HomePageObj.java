package com.infogix.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObj {

	public WebDriver driver;

	By contact = By.xpath("//a[@href='https://www.infogix.com/data-gov-contact/']//parent::li[@id='menu-item-9950']");
	By search = By.xpath("//div[@class='header_inner']//*[local-name()='svg' and  @alt='Search']");
	By searchFor = By.xpath("//div[@class='header_inner']//form//input[@class='searchfor']");
	By submitSearch = By.xpath("//div[@class='header_inner']//form//input[@class='searchbtn']");

	public HomePageObj(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getContact() {
		return driver.findElement(contact);
	}

	public void clickSearch() {
		driver.findElement(search).click();
	}

	public void setSearchText(String searchText) {
		driver.findElement(searchFor).sendKeys(searchText);
	}

	public void submitSearch() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(submitSearch));
	}
}
