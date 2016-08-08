package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class CloseAction extends Action {
	
	public CloseAction() {
		// TODO Auto-generated constructor stub
		setText("关闭窗口");
		setAccelerator(SWT.COMMAND + 'W');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Display.getDefault().getActiveShell().close();
	}

}
