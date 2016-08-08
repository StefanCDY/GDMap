package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class FullScreenAction extends Action {
	
	private Rectangle rectangle;
	
	public FullScreenAction() {
		// TODO Auto-generated constructor stub
		setText("进入全屏幕");
		setAccelerator(SWT.CTRL + SWT.COMMAND + 'F');
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Display.getCurrent().getActiveShell().getFullScreen()) {
			if (rectangle != null) {
				Display.getCurrent().getActiveShell().setBounds(rectangle);
				setText("进入全屏幕");				
			}
		} else {
			rectangle = Display.getCurrent().getActiveShell().getBounds();
			Display.getCurrent().getActiveShell().setFullScreen(true);
			setText("退出全屏幕");
		}
	}

}
