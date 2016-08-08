package week4.cdy.gdmap.function;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import week4.cdy.gdmap.MainWindows;

public class RegionFunction extends BrowserFunction {
	
	private MainWindows mainWindows;

	public RegionFunction(MainWindows mainWindows, Browser browser, String name) {
		super(browser, name);
		this.mainWindows = mainWindows;
	}

	@Override
	public Object function(Object[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			mainWindows.getChinaAction().setText("中国");
			return null;
		}
		String province = args[0].toString();
		String city = args[1].toString();
		String district = args[2].toString();
		if (!district.equals("")) {
			if (!city.equals("")) {
				mainWindows.getChinaAction().setText(city + "——" + district);
			} else {
				mainWindows.getChinaAction().setText(province + "——" + district);				
			}
		} else if (!city.equals("")) {
			mainWindows.getChinaAction().setText(province + "——" + city);
		} else {
			mainWindows.getChinaAction().setText(province);
		}
		return super.function(args);
	}

}
