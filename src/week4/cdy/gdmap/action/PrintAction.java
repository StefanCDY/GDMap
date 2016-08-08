package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class PrintAction extends Action {
	
	public PrintAction() {
		// TODO Auto-generated constructor stub
		setText("打印...");
		setAccelerator(SWT.COMMAND + 'P');
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
