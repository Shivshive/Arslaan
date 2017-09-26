package testclass;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners( webservice.Arslaan.TestDataMaker.class)
public class tests {

	@Test
	public void Test_1(){
		System.out.println("Test Running..");
//		Assert.assertEquals(true, false);
	}
	
	@Test
	public void Test_2(){
		System.out.println("Test Running..");
		Assert.assertEquals(true, false);
	}
	
	@Test
	public void Test_3(){
		System.out.println("Test Running..");
		throw new SkipException("Skipping this exception");
	}
	
	@Test(successPercentage=50)
	public void Test_4(){
		System.out.println("Test Running..");
		Assert.assertEquals(true, false);
	}
	
}
