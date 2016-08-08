var tileLayer = new AMap.TileLayer();// 切片图层类
var satellite = new AMap.TileLayer.Satellite();// 卫星图层类
var traffic = new AMap.TileLayer.Traffic();// 实时交通图层类
var buildings = new AMap.Buildings();// 3D图层

var map = new AMap.Map('container', {
    zoom: 13,
    resizeEnable: true,
    rotateEnable: true,
    keyboardEnable: true,
    doubleClickZoom: false,
    defaultLayer: tileLayer
});

var scale = null;// 地图比例尺插件
var tool = null;// 地图工具条插件

var placeSearch = null;// 地点搜索服务插件
var geocoder = null;// 地理编码与逆地理编码类
var autocomplete = null;// 匹配提示插件
var driving = null;
var walking = null;
var transfer = null;

// 点标记
var marker = new AMap.Marker({
	map: map,
	offset: new AMap.Pixel(-10, -34),
	topWhenClick: true,
	animation: 'AMAP_ANIMATION_DROP',
	topWhenMouseOver: false,
	clickable: true,
});
// 提示匹配时的点标记
var autoMarker = new AMap.Marker({
	map: map,
	offset: new AMap.Pixel(-10, -34),
	topWhenClick: true,
	animation: 'AMAP_ANIMATION_DROP',
	topWhenMouseOver: false,
	clickable: true,
});
// 信息窗体
var infoWindow = new AMap.InfoWindow({
	isCustom: false,
	autoMove: true,
	closeWhenClickMap: false,
	offset: new AMap.Pixel(0, -30),
});

var local = {};// 当前定位的位置
var position = {};// 鼠标右击时的位置
var markers = [];// 存放搜索时所有的点标记

// 加载插件。
map.plugin(["AMap.Scale", "AMap.ToolBar"],function(){
	// 比例尺插件。位于地图右下角，用户可控制其显示与隐藏。
    scale = new AMap.Scale();
    scale.hide();

    // 地图操作工具条插件。可支持方向导航、位置定位、视野级别缩放、视野级别选择等操作。
    tool = new AMap.ToolBar({
    	offset: new AMap.Pixel(10,10),// 相对于地图容器左上角的偏移量，正数代表向右下偏移。默认为AMap.Pixel(10,10)
        ruler: true,// 标尺键盘是否可见，默认为true
        locate: true,// 是否显示定位按钮，默认为false
        direction: true,// 方向键盘是否可见，默认为true
        autoPosition: true,// 是否自动定位，即地图初始化加载完成后，是否自动定位的用户所在地，该功能仅在支持HTML5的浏览器中有效，默认为false
    	useNative: false//是否使用高德定位sdk用来辅助优化定位效果，默认：false
    	// locationMarker:// 自定义定位图标，值为Marker对象
    });
    tool.hide();
	
    map.addControl(scale);
    map.addControl(tool);

	AMap.event.addListener(tool, 'location', onToolLocation);// 使用ToolBar定位按钮或doLocation函数进行定位，定位完成时触发此事件。
});

// 加载服务插件
AMap.service(["AMap.Geocoder", "AMap.PlaceSearch", "AMap.Autocomplete", "AMap.Driving", "AMap.Walking", "AMap.Transfer"], function() {
	// 地理编码与逆地理编码类，用于地址描述与坐标之间的转换。
	geocoder = new AMap.Geocoder({
		radius: 1000,// 逆地理编码时，以给定坐标为中心点，单位：米,取值范围：0-3000,默认值：1000
		extensions: 'all',// 逆地理编码时，返回信息详略,默认值：base，返回基本地址信息；取值为：all，返回地址信息及附近poi、道路、道路交叉口等信息
		// city:// 城市，地理编码时，设置地址描述所在城市,可选值：城市名（中文或中文全拼）、citycode、adcode；默认值：“全国”
	});

    // 地点搜索服务，提供某一特定地区的位置查询服务。
    placeSearch = new AMap.PlaceSearch({
    	citylimit: false,// 是否强制限制在设置的城市内搜索，true：强制限制设定城市，false：不强制限制设定城市，默认值为：false
    	lang:'zh_cn',// 检索语言类型,可选值：zh_cn：中文简体，en：英文,默认为: zh_cn：中文简体
        pageSize: 20,// 单页显示结果条数,默认值：20,取值范围：1-50，超出取值范围按最大值返回
        pageIndex: 1,// 页码,默认值：1,取值范围：1-100，超过实际页数不返回poi
        extensions:'all', // 此项默认值：base，返回基本地址信息,取值：all，返回基本+详细信息
        map: map,// AMap.Map对象, 展现结果的地图实例。当指定此参数后，搜索结果的标注、线路等均会自动添加到此地图上。
        panel: "searchPanel",// 结果列表的HTML容器id或容器元素，提供此参数后，结果列表将在此容器中进行展示。
    	// city:// 兴趣点城市,可选值：城市名（中文或中文全拼）、citycode、adcode,默认值：“全国”
    	// type:// 兴趣点类别，多个类别用“|”分割，如“餐饮|酒店|电影院”,默认值：餐饮服务、商务住宅、生活服务
    });

    // 根据输入关键字提示匹配信息，可将Poi类型和城市作为输入提示的限制条件。
    autocomplete = new AMap.Autocomplete({
    	datatype:'all',// 返回的数据类型，可选值。all-返回所有数据类型、poi-返回POI数据类型、bus-返回公交站点数据类型、busline-返回公交线路数据类型
    	citylimit:false,// 是否强制限制在设置的城市内搜索，true：强制限制设定城市，false：不强制限制设定城市，默认值为：false
        input: 'tipinput',// 可选参数，用来指定一个input输入框，，设定之后，将自动生成下拉选择列表
        // type:// 提示Poi类型，多个类型用“|”分隔
    	// city:// 兴趣点城市,可选值：城市名（中文或中文全拼）、citycode、adcode,默认值：“全国”
    });

    // 驾车路线规划服务，提供起、终点坐标的驾车导航路线查询功能。
    driving = new AMap.Driving({
        extensions:'all',// 默认值：base，返回基本地址信息,当取值为：all，返回DriveStep基本信息+DriveStep详细信息
        map:map,// AMap.Map对象, 展现结果的地图实例。
        panel:'result',// 结果列表的HTML容器id或容器元素，提供此参数后，结果列表将在此容器中进行展示。
        hideMarkers:false,// 设置隐藏路径规划的起始点图标，设置为true：隐藏图标；设置false：显示图标 默认值为：false
        // policy:'LEAST_TIME',// 驾车路线规划策略
    });

    // 步行导航服务，提供起始、终点步行路线查询服务。
    walking = new AMap.Walking({
        map: map,// AMap.Map对象, 展现结果的地图实例。
        panel: 'result',// 结果列表的HTML容器id或容器元素，提供此参数后，结果列表将在此容器中进行展示。
        hideMarkers: false,// 设置隐藏路径规划的起始点图标，设置为true：隐藏图标；设置false：显示图标 默认值为：false
    });

    // 公交换乘服务，提供起始点公交路线规划服务
    transfer = new AMap.Transfer({
        nightflage:false,// 是否计算夜班车，默认为不计算,true：计算，false：不计算
        extensions:'all',// 返回结果控制,可选值：base/all,base:返回基本信息,all:返回全部信息,默认值 ：base
        map: map,// AMap.Map对象, 展现结果的地图实例。
        panel:'result',// // 结果列表的HTML容器id或容器元素，提供此参数后，结果列表将在此容器中进行展示。
        hideMarkers: false,// 设置隐藏路径规划的起始点图标，设置为true：隐藏图标；设置false：显示图标 默认值为：false
        // policy:// 公交换乘策略
        // city: citycode,// 公交换乘的城市，支持城市名称、城市区号、电话区号，此项为必填
        // cityd:// 终点城市，跨城公交路径规划时为必填参数
    });

    AMap.event.addListener(autocomplete, "select", onAutocompleteSelect);
});

AMap.event.addListener(map, "moveend", onMapMoveend);
AMap.event.addListener(map, "rightclick", onMapRightclick);