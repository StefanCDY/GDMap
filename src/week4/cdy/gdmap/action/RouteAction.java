package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class RouteAction extends Action {
	
	private MainWindows mainWindows;
	private boolean show;
	
	public RouteAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		this.show = false;
		setText("显示路线...");
		setAccelerator(SWT.COMMAND + 'R');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (show) {
			setText("显示路线");
			show = false;
		} else {
			setText("隐藏路线");
			show = true;
		}
		this.mainWindows.getBrowser().execute(Script.SHOW_ROUTE);
	}

}
