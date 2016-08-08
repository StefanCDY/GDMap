package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class UndoAction extends Action {
	
	public UndoAction() {
		// TODO Auto-generated constructor stub
		setText("撤销");
		setAccelerator(SWT.COMMAND + 'Z');
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
