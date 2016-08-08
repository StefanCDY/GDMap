package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;

public class ShowToolBarAction extends Action {
	
	public ShowToolBarAction() {
		// TODO Auto-generated constructor stub
		setText("始终显示工具栏");
		setChecked(true);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (isChecked()) {
			setChecked(true);
		} else {
			setChecked(false);
		}
	}

}
