package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class MinimizeAction extends Action {
	
	public MinimizeAction() {
		// TODO Auto-generated constructor stub
		setText("最小化");
		setAccelerator(SWT.COMMAND + 'M');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Display.getCurrent().getActiveShell().setMinimized(true);
	}

}
