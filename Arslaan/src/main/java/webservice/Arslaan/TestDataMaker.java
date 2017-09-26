package webservice.Arslaan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.TestNGException;

import modal.classmodal;
import modal.methodmodal;
import modal.testmodal;

public class TestDataMaker implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {

	}

	@Override
	public void onFinish(ISuite suite) {
		
		System.out.println("control in OnFinishISuiteListner");
		System.out.println("XmlSuite Name : "+suite.getName());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Map<String,ISuiteResult> suiteResults = suite.getResults();
		for(Map.Entry<String, ISuiteResult> sResult : suiteResults.entrySet()){
			
			testmodal modal = new testmodal();
			
			String xmlTestName = sResult.getKey();
			int passed = sResult.getValue().getTestContext().getPassedTests().size();
			int failed = sResult.getValue().getTestContext().getFailedTests().size();
			int skipped = sResult.getValue().getTestContext().getSkippedTests().size();
			int failedwithsuccesspercentage = sResult.getValue().getTestContext().getFailedButWithinSuccessPercentageTests().size();
			
			IResultMap rp = sResult.getValue().getTestContext().getPassedTests();
			IResultMap rf = sResult.getValue().getTestContext().getFailedTests();
			IResultMap rs = sResult.getValue().getTestContext().getSkippedTests();
			IResultMap rfsp = sResult.getValue().getTestContext().getFailedButWithinSuccessPercentageTests();
			
			modal.setTestname(xmlTestName);
			modal.setPassed(passed);
			modal.setFailed(failed);
			modal.setSkipped(skipped);
			modal.setFailedwithsuccesspercentage(failedwithsuccesspercentage);
			modal.setSuitename(suite.getName());
			
			List<classmodal> pclass = addClass(rp, modal.getClasses());
			modal.setClasses(pclass);
			
			List<classmodal> fclass = addClass(rp, modal.getClasses());
			modal.setClasses(fclass);
			
			List<classmodal> sclass = addClass(rp, modal.getClasses());
			modal.setClasses(sclass);
			
			List<classmodal> fspclass = addClass(rp, modal.getClasses());
			modal.setClasses(fspclass);
			
			System.out.println(modal.printModal());
		}
		
	}
	
	public List<classmodal> addClass(IResultMap resultMap, List<classmodal> classlist){
		
		for(ITestResult testResult : resultMap.getAllResults()){
			
			String testMethodName = testResult.getName();
			String className = testResult.getInstanceName();
			String status = getStatus(testResult.getStatus());
			
			int indexOfClass = -1;
			for(classmodal mclass : classlist){
				
				indexOfClass++;
				if(mclass.getName().equalsIgnoreCase(className)){
					break;
				}
			}
			
			methodmodal method = new methodmodal();
			method.setName(testMethodName);
			method.setStatus(status);
			
			if(indexOfClass != -1){
				
				classmodal mclass = classlist.get(indexOfClass);
				List<methodmodal> methodlist = mclass.getMethods();
				methodlist.add(method);
				mclass.setMethods(methodlist);
			}
			else{
				
				classmodal mclass = new classmodal();
				mclass.setName(className);
				List<methodmodal>methodlist = mclass.getMethods();
				methodlist.add(method);
				mclass.setMethods(methodlist);
				classlist.add(mclass);
			}
	
		}
		
		return classlist;
	}
	
	private static String getStatus(int status) {
	    switch(status) {
	      case ITestResult.SUCCESS: return "SUCCESS";
	      case ITestResult.FAILURE: return "FAILURE";
	      case ITestResult.SKIP: return "SKIP";
	      case ITestResult.SUCCESS_PERCENTAGE_FAILURE: return "SUCCESS WITHIN PERCENTAGE";
	      case ITestResult.STARTED: return "STARTED";
	      default: throw new TestNGException("Encountered an un-defined test status of [" + status + "].");
	    }
	  }
	
}
