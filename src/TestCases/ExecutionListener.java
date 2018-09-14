package TestCases;

import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener{

	@Override
	public void onExecutionStart() {
		System.out.println("inside onExecutionStart");
		
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("inside onExecutionFinish");
		
	}

}
