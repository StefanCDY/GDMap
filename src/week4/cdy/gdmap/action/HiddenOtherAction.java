package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class HiddenOtherAction extends Action {
	
	public HiddenOtherAction() {
		// TODO Auto-generated constructor stub
		setText("隐藏其他");
		setAccelerator(SWT.ALT + SWT.COMMAND + 'Q');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
