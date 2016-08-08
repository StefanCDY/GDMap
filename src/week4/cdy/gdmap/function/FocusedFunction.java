package week4.cdy.gdmap.function;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import week4.cdy.gdmap.MainWindows;

public class FocusedFunction extends BrowserFunction {
	
	private MainWindows mainWindows;

	public FocusedFunction(MainWindows mainWindows, Browser browser, String name) {
		super(browser, name);
		this.mainWindows = mainWindows;
	}
	
	@Override
	public Object function(Object[] args) {
		// TODO Auto-generated method stub
		String info = args[0].toString();
		double length = (double) args[1];
		if (info.equals("YES")) {
			this.mainWindows.getParstAction().setEnabled(true);
			this.mainWindows.getSelectAction().setEnabled(true);
			if (length > 0) {
				this.mainWindows.getCutAction().setEnabled(true);
				this.mainWindows.getCopyAction().setEnabled(true);
			} else {
				this.mainWindows.getCutAction().setEnabled(false);
				this.mainWindows.getCopyAction().setEnabled(false);
			}
		} else {
			this.mainWindows.getCutAction().setEnabled(false);
			this.mainWindows.getCopyAction().setEnabled(true);
			this.mainWindows.getParstAction().setEnabled(false);
			this.mainWindows.getSelectAction().setEnabled(false);
		}
		return super.function(args);
	}

}
