package com.infogix.pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RedirectPageObj {

	public WebDriver driver;

	public RedirectPageObj(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyLink(String link) {
		System.out.println(" driver.getCurrentUrl() " + driver.getCurrentUrl());
		System.out.println(" link " + link);
		Assert.assertEquals(driver.getCurrentUrl(), link);
	}
}
