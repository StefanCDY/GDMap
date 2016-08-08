package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class ZoomAction extends Action {
	
	private Rectangle rectangle;
	
	public ZoomAction() {
		// TODO Auto-generated constructor stub
		setText("缩放");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Display.getCurrent().getActiveShell().getMaximized()) {
			if (rectangle != null) {
				Display.getCurrent().getActiveShell().setBounds(rectangle);
			}
		} else {
			rectangle = Display.getCurrent().getActiveShell().getBounds();
			Display.getCurrent().getActiveShell().setMaximized(true);
		}
	}

}
