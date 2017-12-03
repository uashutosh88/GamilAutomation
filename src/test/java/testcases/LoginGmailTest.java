package testcases;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.Excel;

public class LoginGmailTest extends TestBase {
	
	@Test(dataProvider = "getData" )
	public void doLogin(String Email, String Password)
	{
	type("Email_xpath",Email);
	click("NextAfterEmail_xpath");
	type("Password_xpath",Password);
	click("NextAfterPassword_xpath");
	log.info("Login done on 3rd dec");
	log.info("Login done on 3rd dec");
	}
	
	@DataProvider
	public Object[][] getData(){
		
	
		return Excel.getData("LoginTest");
		
	}
	

}
