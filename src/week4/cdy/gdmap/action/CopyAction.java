package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class CopyAction extends Action {
	
	private MainWindows mainWindows;
	
	public CopyAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("拷贝");
		setAccelerator(SWT.COMMAND + 'C');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.COPY_TEXT);
	}

}
