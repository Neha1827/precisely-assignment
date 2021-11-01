package com.infogix.pageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ContactPageObj {
	
	
	public static enum FORM_FILEDS {
		FIRSTNAME, LASTNAME, COMAPNY, EMAIL, COUNTRY, PHONE, COMMENTS, INDUSTRY, IAGREE, SUBMIT
	}
	
	public WebDriver driver;
	Map<FORM_FILEDS, By> contactFormSelectorMap = new HashMap<>();
	
	By firstName = By.xpath(("//input[@name='FirstName']"));
	By lastName = By.xpath("//input[@id='LastName']");
	By companyName = By.cssSelector("input[id ='Company']");
	By email = By.xpath("//input[@id='Email']");
	By country = By.xpath("//select[@id = 'HQ_Location_Country__c']");
	By phoneNumber = By.xpath("//input[@id='Phone']");
	By comments = By.xpath("//textarea[@id='Next_Step_Comments__c']");
	By industry = By.xpath("//select[@id='Industry__c']");
	By iAgree = By.xpath("//input[@id='Consent_to_Processing__c']//parent::div[1]");
	By submit = By.xpath("//form[@id='mktoForm_3219']//child::button[@type='submit']");
	
	
	
	
	public ContactPageObj(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		initSelectorMap();
	}
	
	private void initSelectorMap() {
		contactFormSelectorMap.put(FORM_FILEDS.FIRSTNAME, firstName);
		contactFormSelectorMap.put(FORM_FILEDS.LASTNAME, lastName);
		contactFormSelectorMap.put(FORM_FILEDS.COMAPNY, companyName);
		contactFormSelectorMap.put(FORM_FILEDS.EMAIL, email);
		contactFormSelectorMap.put(FORM_FILEDS.COUNTRY, country);
		contactFormSelectorMap.put(FORM_FILEDS.PHONE, phoneNumber);
		contactFormSelectorMap.put(FORM_FILEDS.COMMENTS, comments);
		contactFormSelectorMap.put(FORM_FILEDS.INDUSTRY, industry);
		contactFormSelectorMap.put(FORM_FILEDS.IAGREE, iAgree);
		contactFormSelectorMap.put(FORM_FILEDS.SUBMIT, submit);
	}
	
	public void locateForm() {
		WebElement element = driver.findElement(iAgree);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public void setFormField(FORM_FILEDS formField, String value)
	{
		try {
			driver.findElement(contactFormSelectorMap.get(formField)).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDropDownByText(FORM_FILEDS formField, String value)
	{
		try {
			Select select = new Select(driver.findElement(contactFormSelectorMap.get(formField)));
			select.selectByVisibleText(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setClick(FORM_FILEDS formField) {
		try {
			driver.findElement(contactFormSelectorMap.get(formField)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
