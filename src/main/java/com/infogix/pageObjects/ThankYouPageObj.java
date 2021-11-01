package com.infogix.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ThankYouPageObj {

	public WebDriver driver;
	By thankyou = By.xpath("(//h1[text()='Thank You!'])");

	public ThankYouPageObj(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void checkThankYou() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(thankyou));
			Assert.assertEquals(we.isDisplayed(), true);
		} catch (Exception e) {
			// test failed
			System.out.println("Test Failed");
		}
	}

}
