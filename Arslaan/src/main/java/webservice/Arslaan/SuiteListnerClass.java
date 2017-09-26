package webservice.Arslaan;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestResult;

public class SuiteListnerClass implements ISuiteListener {

	public void onStart(ISuite suite) {
		
	}

	public void onFinish(ISuite suite) {
		
		System.out.println("control in OnFinishISuiteListner");

		Map<String, ISuiteResult> results = suite.getResults();		
		
		for(Entry<String, ISuiteResult> result : results.entrySet()){
			
			String testName = result.getKey();			
			
			TestResultDataModal modal = new TestResultDataModal();
			modal.name = testName;
			IResultMap p = result.getValue().getTestContext().getPassedTests();
			IResultMap f  = result.getValue().getTestContext().getFailedTests();
			IResultMap s = result.getValue().getTestContext().getSkippedTests();
			IResultMap fs = result.getValue().getTestContext().getFailedButWithinSuccessPercentageTests();
			
			modal.totalPassedTest = p.getAllResults().size();
			modal.totalFailedTest = f.getAllResults().size();
			modal.totalSkippedTest = s.getAllResults().size();
			modal.totalFailedWithPercentageTest = fs.getAllResults().size();
			
			
			
			addResult(p, modal.classes);
			
			addResult(f, modal.classes);			
			
			addResult(s, modal.classes);	
			
			addResult(fs, modal.classes);
			
			System.out.println(modal);
		}
		
		
	}
	
	class TestClassDataModal{
		public TestClassDataModal(){
			methods = new LinkedHashMap<String, String>();
		}
		String name;
		Map<String, String> methods;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("~~~~~~~~~~~~~~~~~~~~\n");
			sb.append("Class Name:" + name).append("\n");
			for(Entry<String, String> method : methods.entrySet()){
				sb.append(method.getKey() + ": " + method.getValue()).append("\n");
			}
			sb.append("~~~~~~~~~~~~~~~~~~~~\n");
			return sb.toString();

		}
	}
	
	class TestResultDataModal{
		
		public TestResultDataModal(){
			classes = new LinkedHashMap<String, TestClassDataModal>();
		}
		
		String name;
		int totalPassedTest;
		int totalFailedTest;
		int totalSkippedTest;
		int totalFailedWithPercentageTest;
		
		Map<String, TestClassDataModal> classes;
		
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("------------------------\n");
			sb.append("Test Name:" + name).append("\n");
			sb.append("Passed Test Cases : " + totalPassedTest).append("\n");
			sb.append("Failed Test Cases : " + totalFailedTest).append("\n");
			sb.append("Skipped Test Cases : " + totalSkippedTest).append("\n");
			sb.append("FailedButWithinSuccessPercentage Test Cases : " + totalFailedWithPercentageTest).append("\n");
			for(Entry<String, TestClassDataModal> classed : classes.entrySet()){
				sb.append(classed.getValue());
			}
			sb.append("-----------------------------------\n");
			return sb.toString();
		}
		
	}
	
	private void addResult(IResultMap r, Map<String, TestClassDataModal> classes){
		for(ITestResult i : r.getAllResults()){
			
			String className = i.getInstanceName();
			String methodName = i.getName();
			String status = getStatus(i.getStatus());				
			
			
			if(classes.containsKey(className)){
				TestClassDataModal classed = classes.get(className);
				classed.methods.put(methodName, status);
			}
			else{
				TestClassDataModal classed = new TestClassDataModal();
				classed.name = className;			
				classed.methods.put(methodName, status);
				classes.put(className, classed);
			}			
		}
	}
	
	private void printResult(IResultMap r){
		for(ITestResult i : r.getAllResults()){
			
			String className = i.getInstanceName();
			String methodName = i.getName();
			String status = getStatus(i.getStatus());				
			
			
			System.out.println("Class Name : " +className);
			System.out.println("Method Name : " +methodName);
			System.out.println("Test Status : " +status);
		}
	}
	
	
	private String getStatus(int status) {
	    switch(status) {
	      case ITestResult.SUCCESS: return "SUCCESS";
	      case ITestResult.FAILURE: return "FAILURE";
	      case ITestResult.SKIP: return "SKIP";
	      case ITestResult.SUCCESS_PERCENTAGE_FAILURE: return "SUCCESS WITHIN PERCENTAGE";
	      case ITestResult.STARTED: return "STARTED";
	      default: throw new RuntimeException();
	    }
	  }

}
