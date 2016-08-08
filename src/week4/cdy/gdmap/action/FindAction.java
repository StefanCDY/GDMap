package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class FindAction extends Action {
	
	private MainWindows mainWindows;
	
	public FindAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("查找");
		setAccelerator(SWT.COMMAND + 'F');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.FIND_TEXT);
	}

}
