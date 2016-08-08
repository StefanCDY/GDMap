package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class PutPinAction extends Action {
	
	private MainWindows mainWindows;
	
	public PutPinAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("放置大头针");
		setAccelerator(SWT.SHIFT + SWT.COMMAND + 'D');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.PUT_CENTER_PIN);
		this.mainWindows.getRemovePinAction().setEnabled(true);
	}

}
