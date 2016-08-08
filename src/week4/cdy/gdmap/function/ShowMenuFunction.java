package week4.cdy.gdmap.function;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import week4.cdy.gdmap.MainWindows;

public class ShowMenuFunction extends BrowserFunction {
	
	private MainWindows mainWindows;

	public ShowMenuFunction(MainWindows mainWindows, Browser browser, String name) {
		super(browser, name);
		this.mainWindows = mainWindows;
	}
	
	@Override
	public Object function(Object[] args) {
		// TODO Auto-generated method stub
		this.mainWindows.getMenu().setVisible(true);
		return super.function(args);
	}

}
