package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class CutAction extends Action {
	
	private MainWindows mainWindows;
	
	public CutAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("剪切");
		setAccelerator(SWT.COMMAND + 'X');
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.CUT_TEXT);
	}

}
