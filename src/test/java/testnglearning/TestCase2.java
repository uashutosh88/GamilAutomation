package testnglearning;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestCase2 {
	
	
	@Test(groups="p3")
	public void validateTitles(){
		
		
		String expected_title = "Gmail.com";
		String actual_title = "Google.com";
		
		/*if(expected_title.equals(actual_title)){
			
			System.out.println("Test case pass");
		}else{
			
			System.out.println("Test case fail");
		}*/
		
		Assert.assertEquals(actual_title, expected_title);
		
		
		
		
	}

}
