package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FrontAction extends Action {
	
	public FrontAction() {
		// TODO Auto-generated constructor stub
		setText("前置全部窗口");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Shell[] shells = Display.getCurrent().getShells();
		Shell activeShell = Display.getCurrent().getActiveShell();
		for (Shell shell : shells) {
			shell.moveBelow(activeShell);
		}
	}

}
