package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class RedoAction extends Action {
	
	public RedoAction() {
		// TODO Auto-generated constructor stub
		setText("重做");
		setAccelerator(SWT.SHIFT + SWT.COMMAND + 'Z');
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
