package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class ScaleAction extends Action {
	
	private MainWindows mainWindows;
	private boolean show;
	
	public ScaleAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		this.show = false;
		setText("显示比例");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (show) {
			mainWindows.getBrowser().execute(Script.HIDE_SCALE);
			setText("显示比例");
			show = false;
		} else {
			mainWindows.getBrowser().execute(Script.SHOW_SCALE);
			setText("隐藏比例");
			show = true;
		}
	}

}
