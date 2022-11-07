package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage;

public  class Listeners extends BasePage implements ITestListener {

	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
     
	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			takeSnapShot(result.getName());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
   }
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
   }
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
   }
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}




}


