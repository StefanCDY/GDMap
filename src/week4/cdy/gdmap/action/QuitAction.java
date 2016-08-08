package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

public class QuitAction extends Action {
	
	public QuitAction() {
		// TODO Auto-generated constructor stub
		setText("退出地图");
		setAccelerator(SWT.COMMAND + 'Q');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
