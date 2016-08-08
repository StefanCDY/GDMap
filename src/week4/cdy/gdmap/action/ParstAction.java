package week4.cdy.gdmap.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.TextTransfer;

import week4.cdy.gdmap.MainWindows;
import week4.cdy.gdmap.function.Script;

public class ParstAction extends Action {
	
	private MainWindows mainWindows;
	
	public ParstAction(MainWindows mainWindows) {
		// TODO Auto-generated constructor stub
		this.mainWindows = mainWindows;
		setText("粘贴");
		setAccelerator(SWT.COMMAND + 'V');
		setEnabled(false);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = (String) this.mainWindows.getClipboard().getContents(TextTransfer.getInstance());
		if (str != null && !str.equals("")) {
			this.mainWindows.getBrowser().execute(Script.PARST_TEXT(str));
		}
//		if (this.mainWindows.getSearch().isFocusControl()) {// 搜索框获得焦点
//			this.mainWindows.getSearch().paste();
//		}
	}

}
