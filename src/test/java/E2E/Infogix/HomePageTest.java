package E2E.Infogix;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.infogix.pageObjects.ContactPageObj;
import com.infogix.pageObjects.HomePageObj;
import com.infogix.pageObjects.RedirectPageObj;
import com.infogix.pageObjects.SearchResultsPageObj;
import com.infogix.pageObjects.ThankYouPageObj;
import com.infogix.resources.Base;

@Listeners(com.infogix.e2e.Listeners.class)
public class HomePageTest extends Base {

	public WebDriver driver;
	private String URL = "https://www.infogix.com/";

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeBrowserDriver();
		driver.manage().window().maximize();
	}

	/**
	 * Test Case 1
	 * 
	 * @param firstName
	 * @param lastName
	 * @param company
	 * @param email
	 * @param country
	 * @param phone
	 * @param comments
	 * @param industry
	 * @throws IOException
	 */
	@Test(priority = 0, dataProvider = "getContactFormData")
	public void contactForm(String firstName, String lastName, String company, String email, String country,
			String phone, String comments, String industry) throws IOException {
		System.out.println(" URL is " + URL);
		driver.get(URL);

		HomePageObj hm = new HomePageObj(driver);
		hm.getContact().click();

		ContactPageObj contact = new ContactPageObj(driver);
		contact.locateForm();
		contact.setFormField(ContactPageObj.FORM_FILEDS.FIRSTNAME, firstName);
		contact.setFormField(ContactPageObj.FORM_FILEDS.LASTNAME, lastName);
		contact.setFormField(ContactPageObj.FORM_FILEDS.COMAPNY, company);
		contact.setFormField(ContactPageObj.FORM_FILEDS.EMAIL, email);
		contact.setDropDownByText(ContactPageObj.FORM_FILEDS.COUNTRY, country);
		contact.setFormField(ContactPageObj.FORM_FILEDS.PHONE, phone);
		contact.setFormField(ContactPageObj.FORM_FILEDS.COMMENTS, comments);
		contact.setDropDownByText(ContactPageObj.FORM_FILEDS.INDUSTRY, industry);

		contact.setClick(ContactPageObj.FORM_FILEDS.IAGREE);

		// submit form
		contact.setClick(ContactPageObj.FORM_FILEDS.SUBMIT);

		new ThankYouPageObj(driver).checkThankYou();
	}

	/**
	 * Test Case 2
	 * 
	 * @param firstName
	 * @param lastName
	 * @param company
	 * @param email
	 * @param country
	 * @param phone
	 * @param comments
	 * @param industry
	 * @throws IOException
	 */
	@Test(priority = 1, dataProvider = "getSearchData")
	public void searchText(String searchText, String clickElementText, String linkText, String linkUrl)
			throws IOException {
		driver.get(URL);

		HomePageObj hm = new HomePageObj(driver);
		hm.clickSearch();
		hm.setSearchText(searchText);
		hm.submitSearch();
		SearchResultsPageObj search = new SearchResultsPageObj(driver);
		WebElement elementFound = search.searchElement(clickElementText);
		if (elementFound != null) {
			search.clickSearchedElement(elementFound);
			search.clickLink(linkText);
			RedirectPageObj redirectpage = new RedirectPageObj(driver);
			redirectpage.verifyLink(linkUrl);
		}
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
	}

	@DataProvider
	public Object[][] getContactFormData() {

		Object[][] data = new Object[1][8];

		// data as per email, but we do not have the option of "America" in the country
		// drop down

//		data[0][0]="Neha";
//		data[0][1]="Jamdade";
//		data[0][2]="Infogix HR";
//		data[0][3]="neha@gmail.com";
//		data[0][4]="America";
//		data[0][5]="1234567890";
//		data[0][6]="This is a coding challenge for Test Automation position. Please disregard this message";
//		data[0][7]="Media & Communication";

		data[0][0] = "Neha";
		data[0][1] = "Jamdade";
		data[0][2] = "Infogix HR";
		data[0][3] = "neha@gmail.com";
		data[0][4] = "United States";
		data[0][5] = "1234567890";
		data[0][6] = "This is a coding challenge for Test Automation position. Please disregard this message";
		data[0][7] = "Media & Communication";

		return data;
	}

	@DataProvider
	public Object[][] getSearchData() {

		Object[][] data = new Object[1][4];

		data[0][0] = "govern";
		data[0][1] = "BUILDING DATA TRUST WITH STRATEGIC DATA GOVERNANCE";
		data[0][2] = "regulatory compliance";
		data[0][3] = "https://www.infogix.com/solutions/regulatory-compliance/";

		return data;
	}
}
