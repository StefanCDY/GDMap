package week4.cdy.gdmap.function;

public class Script {
	
	// 地图显示类型
	public static final String MAP = "showDefaultMap()";
	public static final String BUS = "showBusMap()";
	public static final String SATELLITE = "showSatelliteMap()";
	
	public static final String SHOW_3D = "buildings.setMap(map);";
	public static final String HIDE_3D = "buildings.setMap(null);";
	
	// 地图缩放
	public static final String zoomIn = "map.zoomIn();";
	public static final String zoomOut = "map.zoomOut();";
	
	// 显示地图比例
	public static final String SHOW_SCALE = "scale.show();";
	public static final String HIDE_SCALE = "scale.hide();";
	
	// 地图固定往北
	public static final String NORTH = "map.setRotation(0);";
	// 重定位
	public static final String LOCATION = "tool.doLocation();";
	
	// 添加/移除点标记
	public static final String PUT_CENTER_PIN = "putPin('center');";
	public static final String REMOVE_PIN = "removePin();";
	public static final String PUT_PIN = "putPin();";
	
	// 搜索
	public static String placeSearch(String keyword) {
		return "search('" + keyword + "');";
	}
	
	// 显示路线面板
	public static String SHOW_ROUTE = "showRoutePanel();";
	
	public static String GET_ROUTE = "getRoute()";// 获取当前的路线
	
	public static final String FOCUS_TEXT = "focusText()";// 焦点文本框
	public static final String FIND_TEXT = "findText()";// 文本框查找
	public static final String SELECT_ALL = "selectAll()";// 全选
	public static final String COPY_TEXT = "copyText()";// 复制
	public static final String CUT_TEXT = "cutText()";// 剪切
	public static final String PARST_TEXT(String str) {// 粘贴
		return "parstText('" + str + "');";
	}
	
}
