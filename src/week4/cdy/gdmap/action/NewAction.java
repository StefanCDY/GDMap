package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;

public class NewAction extends Action {
	
	public NewAction() {
		// TODO Auto-generated constructor stub
		setText("新建窗口");
		setAccelerator(SWT.COMMAND + 'N');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MainWindows windows = new MainWindows();
		windows.setBlockOnOpen(true);
		windows.open();
	}

}
