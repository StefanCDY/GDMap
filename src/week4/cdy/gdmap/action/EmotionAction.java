package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class EmotionAction extends Action {
	
	public EmotionAction() {
		// TODO Auto-generated constructor stub
		setText("表情与符号");
		setAccelerator(SWT.CTRL + SWT.COMMAND + SWT.BS);
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
