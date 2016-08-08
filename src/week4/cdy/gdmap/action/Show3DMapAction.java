package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class Show3DMapAction extends Action {
	
	private MainWindows mainWindows;
	private boolean show;
	
	public Show3DMapAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		this.show = false;
		setText("显示3D地图");
		setAccelerator(SWT.COMMAND + 'O');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (show) {
			mainWindows.getBrowser().execute(Script.HIDE_3D);
			setText("显示3D地图");
			show = false;
		} else {
			mainWindows.getBrowser().execute(Script.SHOW_3D);
			setText("隐藏3D地图");
			show = true;
		}
	}

}
