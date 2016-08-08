package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class HiddenMapAction extends Action {
	
	public HiddenMapAction() {
		// TODO Auto-generated constructor stub
		setText("隐藏地图");
		setAccelerator(SWT.COMMAND + 'H');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
