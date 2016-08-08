package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class LocationAction extends Action {
	
	private MainWindows mainWindows;
	
	public LocationAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("前往当前位置");
		setAccelerator(SWT.COMMAND + 'L');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.LOCATION);
	}

}
