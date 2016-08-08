package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class ZoomOutAction extends Action {
	
	private MainWindows mainWindows;
	
	public ZoomOutAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("缩小");
		setAccelerator(SWT.COMMAND + '-');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		mainWindows.getBrowser().execute(Script.zoomOut);
	}

}
