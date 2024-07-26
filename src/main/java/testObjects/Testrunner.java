package testObjects;
import org.testng.annotations.Test;
import  pageobjects.homeTest;


public class Testrunner extends BaseTest{
	public void testrun()
	{
		homeTest ht =new homeTest();
		ht.test1();
		
		logger=reports.createTest("Home Reports").assignAuthor("ict").assignCategory("Regression");
		logger.pass("url Loaded sucessfully");
		logger.info("values entered");
		logger.pass("login sucessfull");
		logger.info("Test sucessfull");
		
		
	}
	@Test
	public void testrun1()
	{
		logger=reports.createTest("login Reports").assignAuthor("ict").assignCategory("Regression");
		logger.pass("url Loaded sucessfully");
		logger.pass("login sucessfull");
		logger.info("Test sucessfull");
			
	}

}
