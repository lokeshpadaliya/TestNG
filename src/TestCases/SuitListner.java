package TestCases;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuitListner implements ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Start suite " + suite.getName() + " in OnStart");

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Start suite " + suite.getName() + " in OnFinish");
		
	}

}
