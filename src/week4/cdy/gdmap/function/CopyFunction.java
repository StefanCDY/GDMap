package week4.cdy.gdmap.function;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import week4.cdy.gdmap.MainWindows;

public class CopyFunction extends BrowserFunction {
	
	private MainWindows mainWindows;

	public CopyFunction(MainWindows mainWindows, Browser browser, String name) {
		super(browser, name);
		this.mainWindows = mainWindows;
	}
	
	@Override
	public Object function(Object[] args) {
		// TODO Auto-generated method stub
		String content = args[0].toString();
		Object[] data = new Object[] {content};
		Transfer[] dataTypes = new Transfer[] {TextTransfer.getInstance()};
		this.mainWindows.getClipboard().setContents(data, dataTypes);
		return super.function(args);
	}

}
