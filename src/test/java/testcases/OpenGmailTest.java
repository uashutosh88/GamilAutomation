package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;

public class OpenGmailTest extends TestBase {

	@Test
	public void openGmailTest() {
		click("Gmail_xpath");
		System.out.println("login done");
		Assert.assertTrue(isElementPresent("Login_xpath"), "Login not successful");
		//Assert.fail("Bank Manager login not successful");
	}

}
