package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class SatelliteAction extends Action {
	
	private MainWindows mainWindows;
	
	public SatelliteAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("卫星");
		setAccelerator(SWT.COMMAND + '3');
		setChecked(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		mainWindows.getBrowser().execute(Script.SATELLITE);

		mainWindows.getMapAction().setChecked(false);
		mainWindows.getBusAction().setChecked(false);
		mainWindows.getSatelliteAction().setChecked(true);
		
		mainWindows.getShow3dMapAction().setEnabled(false);
	}

}
