package modal;

import java.util.ArrayList;
import java.util.List;

public class testmodal {

	String testname;
	String suitename;
	int passed;
	int failed;
	int skipped;
	int failedwithsuccesspercentage;
	List<classmodal> classes;
	
	public testmodal(){
		classes = new ArrayList<>();
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getSuitename() {
		return suitename;
	}

	public void setSuitename(String suitename) {
		this.suitename = suitename;
	}

	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public int getFailedwithsuccesspercentage() {
		return failedwithsuccesspercentage;
	}

	public void setFailedwithsuccesspercentage(int failedwithsuccesspercentage) {
		this.failedwithsuccesspercentage = failedwithsuccesspercentage;
	}

	public List<classmodal> getClasses() {
		return classes;
	}

	public void setClasses(List<classmodal> classes) {
		this.classes = classes;
	}
	
	public String printModal(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Test Name:" + getTestname()).append("\n");
		sb.append("Passed Test Cases : " + getPassed()).append("\n");
		sb.append("Failed Test Cases : " + getFailed()).append("\n");
		sb.append("Skipped Test Cases : " + getSkipped()).append("\n");
		sb.append("FailedButWithinSuccessPercentage Test Cases : " + getFailedwithsuccesspercentage()).append("\n");
		for(classmodal modalclass : getClasses()){
			
			sb.append(modalclass);
		}
		sb.append("=================================================\n");
		
		
		return sb.toString();
	}
	
}
