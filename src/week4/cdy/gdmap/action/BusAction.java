package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class BusAction extends Action {
	
	private MainWindows mainWindows;
	
	public BusAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("交通");
		setAccelerator(SWT.COMMAND + '2');
		setChecked(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		mainWindows.getBrowser().execute(Script.BUS);
		
		mainWindows.getMapAction().setChecked(false);
		mainWindows.getBusAction().setChecked(true);
		mainWindows.getSatelliteAction().setChecked(false);
		
		mainWindows.getShow3dMapAction().setEnabled(true);
	}

}
