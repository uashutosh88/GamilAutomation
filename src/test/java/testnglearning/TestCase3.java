package testnglearning;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase3 {
	
	
	@Test(priority=1,groups="p1")
	public void doUserReg(){
		
		
		System.out.println("Executing User Reg Test");
		Assert.fail("User not registered successfully");
	
		
	}
	
	@Test(priority=2,dependsOnMethods="doUserReg",groups="p1")
	public void doLogin(){
		
		System.out.println("Executing login Test");
		
	}
	
	@Test(priority=3,groups={"p3","p2"})
	public void isSkip(){
		
		throw new SkipException("Skipping the test as the image is not present");
		
	}

}
