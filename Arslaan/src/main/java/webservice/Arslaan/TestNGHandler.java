package webservice.Arslaan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

public class TestNGHandler {

	public void createSuite(){

		TestNG myTestNG = new TestNG();
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		
//		TestListenerAdapter tla = new TestListenerAdapter();
//		myTestNG.addListener(tla);

		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("SuiteName");
		mySuite.setVerbose(1);
		mySuite.setParallel(ParallelMode.METHODS);
		mySuite.setThreadCount(2);
		mySuite.addListener("webservice.Arslaan.TestDataMaker");


		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("XmlTestName-1");
		
		Map<String, String> testngParams = new HashMap<String,String>();	 
		testngParams.put("browser", "chrome");	
		myTest.setParameters(testngParams);
		
		List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		myClasses.add(new XmlClass("testclass.tests"));
		
		myTest.setXmlClasses(myClasses);
		


		XmlTest myTest2 = new XmlTest(mySuite);
		myTest2.setName("XmlTestName-2");
		Map<String, String> testngParams2 = new HashMap<String,String>();	 
		testngParams2.put("browser", "firefox");	
		myTest2.setParameters(testngParams);
		List<XmlClass> myClasses2 = new ArrayList<XmlClass> ();
		myClasses.add(new XmlClass("testclass.tests"));
		myTest2.setXmlClasses(myClasses);
		
		
		myTests.add(myTest);
		myTests.add(myTest2);
		
		
		mySuite.setTests(myTests);

		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		myTestNG.setXmlSuites(mySuites);

		System.out.println(mySuite.toXml());

		myTestNG.run();

		
		int status = myTestNG.getStatus();
		System.out.println("Test Status "+status);

	}

}
