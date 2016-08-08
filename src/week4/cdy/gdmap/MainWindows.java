package week4.cdy.gdmap;

import java.io.File;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import week4.cdy.gdmap.action.AboutAction;
import week4.cdy.gdmap.action.BusAction;
import week4.cdy.gdmap.action.ChinaAction;
import week4.cdy.gdmap.action.CloseAction;
import week4.cdy.gdmap.action.CopyAction;
import week4.cdy.gdmap.action.CutAction;
import week4.cdy.gdmap.action.FindAction;
import week4.cdy.gdmap.action.FrontAction;
import week4.cdy.gdmap.action.FullScreenAction;
import week4.cdy.gdmap.action.MinimizeAction;
import week4.cdy.gdmap.action.NewAction;
import week4.cdy.gdmap.action.NorthAction;
import week4.cdy.gdmap.action.OptionAction;
import week4.cdy.gdmap.action.PDFAction;
import week4.cdy.gdmap.action.ParstAction;
import week4.cdy.gdmap.action.PrintAction;
import week4.cdy.gdmap.action.PutPinAction;
import week4.cdy.gdmap.action.QuitAction;
import week4.cdy.gdmap.action.RemovePinAction;
import week4.cdy.gdmap.action.ReportAction;
import week4.cdy.gdmap.action.RouteAction;
import week4.cdy.gdmap.action.SatelliteAction;
import week4.cdy.gdmap.action.ScaleAction;
import week4.cdy.gdmap.action.SearchAction;
import week4.cdy.gdmap.action.SelectAction;
import week4.cdy.gdmap.action.ServiceAction;
import week4.cdy.gdmap.action.ShareAction;
import week4.cdy.gdmap.action.Show3DMapAction;
import week4.cdy.gdmap.action.ShowAllAction;
import week4.cdy.gdmap.action.ShowToolBarAction;
import week4.cdy.gdmap.action.ZoomAction;
import week4.cdy.gdmap.action.ZoomInAction;
import week4.cdy.gdmap.action.ZoomOutAction;
import week4.cdy.gdmap.function.CopyFunction;
import week4.cdy.gdmap.function.CutFunction;
import week4.cdy.gdmap.function.FocusedFunction;
import week4.cdy.gdmap.function.RegionFunction;
import week4.cdy.gdmap.function.Script;
import week4.cdy.gdmap.function.ShowMenuFunction;
import week4.cdy.gdmap.action.HelpAction;
import week4.cdy.gdmap.action.HiddenMapAction;
import week4.cdy.gdmap.action.HiddenOtherAction;
import week4.cdy.gdmap.action.LocationAction;
import week4.cdy.gdmap.action.MapAction;

public class MainWindows extends ApplicationWindow {
	
	private String mapPath = "html/map.html";
	private Browser browser;
	private Clipboard clipboard;
	private Menu menu;
	private ShellListenerManager shellListener;
	
	private CutAction cutAction;
	private CopyAction copyAction;
	private ParstAction parstAction;
	private SelectAction selectAction;
	private RemovePinAction removePinAction;
	private MapAction mapAction;
	private BusAction busAction;
	private SatelliteAction satelliteAction;
	private Show3DMapAction show3dMapAction;
	private MinimizeAction minimizeAction;
	private ZoomAction zoomAction;
	private FrontAction frontAction;
	private ChinaAction chinaAction;
	
	public MainWindows() {
		super(null);
		setShellStyle(SWT.SHELL_TRIM);
		
		shellListener = new ShellListenerManager();
		
		cutAction = new CutAction(this);
		copyAction = new CopyAction(this);
		parstAction = new ParstAction(this);
		selectAction = new SelectAction(this);
		removePinAction = new RemovePinAction(this);
		mapAction = new MapAction(this);
		busAction = new BusAction(this);
		satelliteAction = new SatelliteAction(this);
		show3dMapAction = new Show3DMapAction(this);
		minimizeAction = new MinimizeAction();
		zoomAction = new ZoomAction();
		frontAction = new FrontAction();
		chinaAction = new ChinaAction();
		
		this.addMenuBar();// 添加菜单栏
	}
	
	@Override
	protected void configureShell(Shell shell) {
		// TODO Auto-generated method stub
		super.configureShell(shell);
		shell.setText("Stefan——GDMap");
		Rectangle rectangle = shell.getMonitor().getClientArea();
		shell.setSize(rectangle.width * 618 /1000, rectangle.height * 618 /1000);
		shell.setLocation((rectangle.width - shell.getSize().x) / 2, (rectangle.height - shell.getSize().y) / 2);
		shell.forceFocus();
	}
	
	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		getShell().addShellListener(shellListener);// Shell添加事件监听器
		
		clipboard = new Clipboard(parent.getDisplay());
		
		browser = new Browser(parent, SWT.NONE);
		GridData gridBrowser  =new GridData(SWT.FILL, SWT.FILL, true, true);
		browser.setLayoutData(gridBrowser);
		browser.setUrl(new File(mapPath).toURI().toString());
		
		menu = new Menu(parent.getShell(), SWT.POP_UP);
		MenuItem pinItem = new MenuItem(menu, SWT.RADIO);
		pinItem.setText("放置大头针");
		pinItem.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				browser.execute(Script.PUT_PIN);
			}
			
		});
		MenuItem routeItem = new MenuItem(menu, SWT.RADIO);
		routeItem.setText("获取路线");
		routeItem.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				browser.execute(Script.GET_ROUTE);
			}
			
		});
//		MenuItem newItem = new MenuItem(menu, SWT.RADIO);
//		newItem.setText("在此位置新建窗口");
		
		new RegionFunction(this, browser, "region");
		new FocusedFunction(this, browser, "focused");
		new CopyFunction(this, browser, "copy");
		new CutFunction(this, browser, "cut");
		new ShowMenuFunction(this, browser, "showMenu");
		
		return parent;
	}
	
	@Override
	protected MenuManager createMenuManager() {
		// TODO Auto-generated method stub
		MenuManager menuBar = new MenuManager();// 创建菜单栏对象

		MenuManager mapMenu = new MenuManager("地图");// 地图菜单项
		MenuManager fileMenu = new MenuManager("文件");// 文件菜单项
		MenuManager editorMenu = new MenuManager("编辑");// 编辑菜单项
		MenuManager showMenu = new MenuManager("显示");// 显示菜单项
		MenuManager windowMenu = new MenuManager("窗口");// 窗口菜单项
		MenuManager helpMenu = new MenuManager("帮助");// 帮助菜单项
		
		// 为菜单栏添加事件监听器
		editorMenu.addMenuListener(new EditMenuManager());
		
		// 将菜单项添加到主菜单中
//		menuBar.add(mapMenu);
		menuBar.add(fileMenu);
		menuBar.add(editorMenu);
		menuBar.add(showMenu);
		menuBar.add(windowMenu);
//		menuBar.add(helpMenu);
		
		// 地图菜单项
		mapMenu.add(new AboutAction());
		mapMenu.add(new Separator());
		mapMenu.add(new ReportAction());
		mapMenu.add(new OptionAction());
		mapMenu.add(new Separator());
		mapMenu.add(new ServiceAction());
		mapMenu.add(new Separator());
		mapMenu.add(new HiddenMapAction());
		mapMenu.add(new HiddenOtherAction());
		mapMenu.add(new ShowAllAction());
		mapMenu.add(new Separator());
		mapMenu.add(new QuitAction());
		
		// 文件菜单项
		fileMenu.add(new NewAction());
		fileMenu.add(new Separator());
		fileMenu.add(new CloseAction());
		fileMenu.add(new Separator());
		fileMenu.add(new ShareAction());
		fileMenu.add(new PDFAction());
		fileMenu.add(new Separator());
		fileMenu.add(new PrintAction());
		
		// 编辑菜单项
//		editorMenu.add(new UndoAction());
//		editorMenu.add(new RedoAction());
//		editorMenu.add(new Separator());
		editorMenu.add(cutAction);
		editorMenu.add(copyAction);
		editorMenu.add(parstAction);
		editorMenu.add(selectAction);
		editorMenu.add(new Separator());
		editorMenu.add(new PutPinAction(this));
		editorMenu.add(removePinAction);
		
		editorMenu.add(new Separator());
		editorMenu.add(new FindAction(this));
//		editorMenu.add(new SpellAction());
//		editorMenu.add(new VoiceAction());
//		editorMenu.add(new Separator());
//		editorMenu.add(new DictationAction());
//		editorMenu.add(new EmotionAction());
		
		// 显示菜单项
		showMenu.add(mapAction);
		showMenu.add(busAction);
		showMenu.add(satelliteAction);
		showMenu.add(new Separator());
		showMenu.add(new ZoomInAction(this));
		showMenu.add(new ZoomOutAction(this));
		showMenu.add(new NorthAction(this));
		showMenu.add(new LocationAction(this));
		showMenu.add(new Separator());
//		showMenu.add(new HiddenLabelAction());
		showMenu.add(show3dMapAction);
//		showMenu.add(new TrafficAction());
		showMenu.add(new RouteAction(this));
		showMenu.add(new ScaleAction(this));
//		showMenu.add(new LabelAction());
//		showMenu.add(new DistanceAction());
		showMenu.add(new Separator());
//		showMenu.add(new BusRouteAction());
//		showMenu.add(new Separator());
		showMenu.add(new ShowToolBarAction());
		showMenu.add(new FullScreenAction());
		
		// 窗口菜单项
		windowMenu.add(minimizeAction);
		windowMenu.add(zoomAction);
		windowMenu.add(new Separator());
		windowMenu.add(frontAction);
		windowMenu.add(new Separator());
		windowMenu.add(chinaAction);
		
		// 帮助菜单项
		helpMenu.add(new SearchAction());
		helpMenu.add(new HelpAction());
		
		return menuBar;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainWindows windows = new MainWindows();
		windows.setBlockOnOpen(true);
		windows.open();
		Display.getDefault().dispose();
	}
	
	public Browser getBrowser() {
		return browser;
	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public Menu getMenu() {
		return menu;
	}

	public ParstAction getParstAction() {
		return parstAction;
	}

	public SelectAction getSelectAction() {
		return selectAction;
	}

	public CutAction getCutAction() {
		return cutAction;
	}

	public CopyAction getCopyAction() {
		return copyAction;
	}

	public MapAction getMapAction() {
		return mapAction;
	}

	public BusAction getBusAction() {
		return busAction;
	}

	public SatelliteAction getSatelliteAction() {
		return satelliteAction;
	}

	public RemovePinAction getRemovePinAction() {
		return removePinAction;
	}

	public Show3DMapAction getShow3dMapAction() {
		return show3dMapAction;
	}

	public ChinaAction getChinaAction() {
		return chinaAction;
	}

	public class ShellListenerManager implements ShellListener {

		@Override
		public void shellActivated(ShellEvent e) {
			// TODO Auto-generated method stub
			minimizeAction.setEnabled(true);
			zoomAction.setEnabled(true);
			frontAction.setEnabled(true);
		}

		@Override
		public void shellClosed(ShellEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void shellDeactivated(ShellEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void shellDeiconified(ShellEvent e) {// shell最大化
			// TODO Auto-generated method stub
			
		}

		@Override
		public void shellIconified(ShellEvent e) {// shell最小化
			// TODO Auto-generated method stub
			minimizeAction.setEnabled(false);
			zoomAction.setEnabled(false);
			frontAction.setEnabled(false);
		}

	}

	public class EditMenuManager implements IMenuListener {

		@Override
		public void menuAboutToShow(IMenuManager manager) {
			// TODO Auto-generated method stub
			browser.execute(Script.FOCUS_TEXT);
		}
		
	}
	
}
