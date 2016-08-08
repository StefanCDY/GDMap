package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class MapAction extends Action {
	
	private MainWindows mainWindows;
	
	public MapAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("地图");
		setAccelerator(SWT.COMMAND + '1');
		setChecked(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mainWindows.getBrowser().execute(Script.MAP);
		
		mainWindows.getMapAction().setChecked(true);
		mainWindows.getBusAction().setChecked(false);
		mainWindows.getSatelliteAction().setChecked(false);
		
		mainWindows.getShow3dMapAction().setEnabled(true);
	}
}