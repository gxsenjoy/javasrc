package testing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("javasrc/testing.AllTests");
		//$JUnit-BEGIN$
		suite.addTestSuite(IntegerTest.class);
		suite.addTestSuite(PersonTest.class);
		suite.addTest(SuiteTest.suite());
		//$JUnit-END$
		return suite;
	}

}
