package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class NorthAction extends Action {
	
	private MainWindows mainWindows;
	
	public NorthAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("固定往北");
		setAccelerator(SWT.CTRL + SWT.COMMAND + '↑');
//		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.NORTH);
//		setEnabled(false);
	}

}
