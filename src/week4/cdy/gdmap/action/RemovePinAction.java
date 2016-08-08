package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class RemovePinAction extends Action {
	
	private MainWindows mainWindows;
	
	public RemovePinAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("移除大头针");
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainWindows.getBrowser().execute(Script.REMOVE_PIN);
		setEnabled(false);
	}

}
