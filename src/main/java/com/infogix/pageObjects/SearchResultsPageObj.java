package com.infogix.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPageObj {
	
	public WebDriver driver;

	By resultList = By.xpath("//div[@class='article-list']//li[@class='equal_height']//h4//a");
	By nextPage = By.xpath("//nav[@class='paged-prev-next']//a[text()='Next Page']");
	
	public SearchResultsPageObj(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement searchElement(String searchElement) {
		
		List<WebElement> searchedResults = driver.findElements(resultList);
		WebElement foundElement = null;
		for (int i = 0; i < searchedResults.size(); i++) {
			if (searchElement.equals(searchedResults.get(i).getText())) {
				foundElement = searchedResults.get(i);
				System.out.println(" ****** MATCH FOUND ****** ");
				break;
			}
		}
		// if element not found on the page then we need to go to the next page
		if (foundElement == null) {
			
			driver.findElement(nextPage).click();
			return searchElement(searchElement);
		}
		return foundElement;
	}
	
	public void clickSearchedElement(WebElement webElement) {
		webElement.click();
	}
	
	public void clickLink(String linkText) {
		driver.findElement(By.xpath("//a[text()='"+linkText+"']")).click();
	}

}
