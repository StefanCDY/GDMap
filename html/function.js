var flage = false;// 判断按下回车之前是否按下上下键

// 获取元素属性
function getAttribute(obj,attr) {
    if(obj.currentStyle) {
        return obj.currentStyle[attr];
    } else {
        return document.defaultView.getComputedStyle(obj,false)[attr];
    }
}

// 地图放大一级显示
function zoomIn() {
    map.zoomIn();
}
// 地图缩小一级显示
function zoomOut() {
    map.zoomOut();
}
// 显示默认地图
function showDefaultMap() {
    document.getElementById('map').style.backgroundColor = 'rgb(95, 95, 95)';
    document.getElementById('map').style.color = 'white';
    document.getElementById('bus').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('bus').style.color = 'black';
    document.getElementById('satellite').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('satellite').style.color = 'black';
    tileLayer.setMap(map);
    traffic.setMap(null);
    satellite.setMap(null);
}
// 显示公交地图
function showBusMap() {
    document.getElementById('map').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('map').style.color = 'black';
    document.getElementById('bus').style.backgroundColor = 'rgb(95, 95, 95)';
    document.getElementById('bus').style.color = 'white';
    document.getElementById('satellite').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('satellite').style.color = 'black';
    tileLayer.setMap(map);
    traffic.setMap(map);
    satellite.setMap(null);
}
// 显示卫星地图
function showSatelliteMap() {
    document.getElementById('map').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('map').style.color = 'black';
    document.getElementById('bus').style.backgroundColor = 'rgb(240, 240, 240)';
    document.getElementById('bus').style.color = 'black';
    document.getElementById('satellite').style.backgroundColor = 'rgb(95, 95, 95)';
    document.getElementById('satellite').style.color = 'white';
    tileLayer.setMap(map);
    traffic.setMap(null);
    satellite.setMap(map);
}
// 显示路线面板
function showRoutePanel() {
    var obj = document.getElementById('routePanel');
    var display = getAttribute(obj, 'display');
    if (display == 'none') {
        document.getElementById('route').style.backgroundColor = 'rgb(95, 95, 95)';
        document.getElementById('route').style.color = 'white';
        document.getElementById('routePanel').style.display = 'block';
        document.getElementById('searchPanel').style.display = 'none';
    } else {
        document.getElementById('route').style.backgroundColor = 'rgb(240, 240, 240)';
        document.getElementById('route').style.color = 'black';
        document.getElementById('routePanel').style.display = 'none';
        document.getElementById('searchPanel').style.display = 'none';
    }
}
// 定位至当前位置
function relocation() {
    tool.doLocation();
}
// 交换起点与终点
function exchange() {
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    document.getElementById('start').value = end;
    document.getElementById('end').value = start;
}
// 修改起点/终点输入框的样式
function setStyle(obj) {
    if (obj.value == '当前位置') {
        obj.style.color = 'blue';
        obj.style.fontWeight = 'bold';
    } else if (obj.value == '已放置的大头针') {
        obj.style.color = 'purple';
        obj.style.fontWeight = 'bold';
    } else {
        obj.style.color = 'black';
        obj.style.fontWeight = 'solid';
    }
}
// 搜索框的焦点事件
function tipfocus(obj) {
    obj.style.textAlign = 'left';
}
// 搜索框的失焦事件
function tipblur(obj) {
    if (obj.value.length == 0) {
        obj.style.textAlign = 'center';
    }
}
// 搜索框的input事件——隐藏搜索面板
function tipinput(obj) {
    var length = obj.value.length;
    if (length == 0) {
        document.getElementById('searchPanel').style.display = 'none';
        document.getElementsByClassName('amap-sug-result')[0].style.display = 'none';
        clearMarkers();
    }
}
// 搜索框的keydown事件——回车搜索操作
function tipkeydown(obj) {
    var theEvent = event || window.event;// 兼容FF和IE和Opera
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {// 按下回车键
        if (!flage) {// 按下回车之前没有按下上下键
            var keyword = obj.value;
            if (keyword.length > 0) {
                search(keyword);// 搜索操作
                obj.blur();
                document.getElementsByClassName('amap-sug-result')[0].style.display = 'none';// 隐藏提示框
                flage = false;
            }
        } else {// 按下回车之前已经按了上下键
            var visibility = document.getElementsByClassName('amap-sug-result')[0].style.visibility;
            var display = document.getElementsByClassName('amap-sug-result')[0].style.display;
            if (visibility == 'hidden' || display == 'none') {// 此时未出现提示框进行搜索操作
                var keyword = obj.value;
                if (keyword.length > 0) {
                    search(keyword);
                    obj.blur();
                    document.getElementsByClassName('amap-sug-result')[0].style.display = 'none';
                    flage = false;
                }
            }
        }
    } else if (code == 38 || code == 40) {
        flage = true;
    } else {
        flage = false;
    }
}
// 点击搜索
function clickSearch() {
    var keyword = document.getElementById('tipinput').value;
    if (keyword.length > 0) {
        search(keyword);// 搜索操作
        obj.blur();
        document.getElementsByClassName('amap-sug-result')[0].style.display = 'none';// 隐藏提示框
        flage = false;
    }
}
// 查询驾车路线
function showDriveline() {
    document.getElementById('result').innerText = '';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入起点！';
        return;
    } else if (start == '当前位置') {
        start = formatAddress(local.regeocode.addressComponent);
    } else if (start == '已放置的大头针') {
        start = formatAddress(position.regeocode.addressComponent);
    }
    if (end.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入终点！';
        return;
    } else if (end == '当前位置') {
        end = formatAddress(local.regeocode.addressComponent);
    } else if (end == '已放置的大头针') {
        end = formatAddress(position.regeocode.addressComponent);
    }
    map.clearMap();
    document.getElementById('result').style.textAlign = 'left';
    driving.search([{keyword: start},{keyword: end}], function(status, result) {
        document.getElementById('driving').style.backgroundColor = 'rgb(45, 140, 250)';
        document.getElementById('walking').style.backgroundColor = 'rgb(255, 255, 255)';
        document.getElementById('transfing').style.backgroundColor = 'rgb(255, 255, 255)';
        if (status === 'complete' && result.info === 'OK') {

        } else {
            document.getElementById('result').style.textAlign = 'center';
            document.getElementById('result').innerText = '查询路线失败';
        }
    });
}
// 步行路线
function showWalkline() {
    document.getElementById('result').innerText = '';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入起点！';
        return;
    } else if (start == '当前位置') {
        start = formatAddress(local.regeocode.addressComponent);
    } else if (start == '已放置的大头针') {
        start = formatAddress(position.regeocode.addressComponent);
    }
    if (end.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入终点！';
        return;
    } else if (end == '当前位置') {
        end = formatAddress(local.regeocode.addressComponent);
    } else if (end == '已放置的大头针') {
        end = formatAddress(position.regeocode.addressComponent);
    }
    map.clearMap();
    document.getElementById('result').style.textAlign = 'left';
    walking.search([{keyword: start},{keyword: end}], function(status, result) {
        document.getElementById('driving').style.backgroundColor = 'rgb(255, 255, 255)';
        document.getElementById('walking').style.backgroundColor = 'rgb(45, 140, 250)';
        document.getElementById('transfing').style.backgroundColor = 'rgb(255, 255, 255)';
        if (status === 'complete' && result.info === 'OK') {

        } else {
            document.getElementById('result').style.textAlign = 'center';
            document.getElementById('result').innerText = '查询步行路线失败';
        }
    });
}
// 公交路线
function showTransferline() {
    document.getElementById('result').innerText = '';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入起点！';
        return;
    } else if (start == '当前位置') {
        start = formatAddress(local.regeocode.addressComponent);
    } else if (start == '已放置的大头针') {
        start = formatAddress(position.regeocode.addressComponent);
    }
    if (end.length == 0) {
        document.getElementById('result').style.textAlign = 'center';
        document.getElementById('result').innerText = '请输入终点！';
        return;
    } else if (end == '当前位置') {
        end = formatAddress(local.regeocode.addressComponent);
    } else if (end == '已放置的大头针') {
        end = formatAddress(position.regeocode.addressComponent);
    }
    var citycode = local.regeocode.addressComponent.citycode;
    map.clearMap();
    document.getElementById('result').style.textAlign = 'left';
    transfer.setCity(citycode);
    transfer.search([{keyword: start},{keyword: end}], function(status, result) {
        document.getElementById('driving').style.backgroundColor = 'rgb(255, 255, 255)';
        document.getElementById('walking').style.backgroundColor = 'rgb(255, 255, 255)';
        document.getElementById('transfing').style.backgroundColor = 'rgb(45, 140, 250)';
        if (status === 'complete' && result.info === 'OK') {

        } else {
            document.getElementById('result').style.textAlign = 'center';
            document.getElementById('result').innerText = '查询公交路线失败';
        }
    });
}
// 清除markers数组的点标记
function clearMarkers() {
    while(markers.length > 0) {
        var marker = markers.pop();
        marker.setMap(null);
    }
}
// 放置大头针
function putPin(pos) {
    if (pos == 'center') {
        geocoder.getAddress(map.getCenter(), function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                marker.setPosition(map.getCenter());
                marker.setExtData(result.regeocode);
                marker.setMap(map);
                infoWindow.setContent(result.regeocode.formattedAddress);
                infoWindow.open(map, map.getCenter());

                AMap.event.addListener(marker, "click", onMarkerClick);
                showPin();// 调用Java函数修改菜单项
            }
        });
    } else {
        marker.setPosition(position.lnglat);
        marker.setExtData(position.regeocode);
        marker.setMap(map);
        infoWindow.setContent(position.regeocode.formattedAddress);
        infoWindow.open(map, position.lnglat);
        AMap.event.addListener(marker, "click", onMarkerClick);
        showPin();// 调用Java函数修改菜单项
    }
}
// 移除大头针
function removePin() {
    marker.hide();
    infoWindow.close();
}
//关键词查询
function search(keyword) {
    clearMarkers();
    document.getElementById('searchPanel').style.textAlign = 'left';
    var citycode = local.regeocode.addressComponent.citycode;
    placeSearch.setCity(citycode);
    placeSearch.search(keyword, function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            var pois = result.poiList.pois;
            for(var i = 0; i < pois.length; i++){
                var searchMarker = new AMap.Marker({
                    content: '<div class="marker" >'+ (i + 1) +'</div>',
                    position: pois[i].location,
                    map: map,
                    animation: 'AMAP_ANIMATION_DROP',
                    topWhenClick: true,
                    clickable: true,
                });
                var extData = {data: pois[i], num: (i + 1)};
                searchMarker.setExtData(extData);
                markers.push(searchMarker);
                AMap.event.addListener(searchMarker, "click", onSearchMarkerClick);
            }
            document.getElementById('searchPanel').style.display = 'block';
            document.getElementById('routePanel').style.display = 'none';
            // map.setFitView();
        } else {
            document.getElementById('routePanel').style.display = 'none';
            document.getElementById('searchPanel').style.display = 'block';
            document.getElementById('searchPanel').style.textAlign = 'center';
            document.getElementById('searchPanel').innerText = '找不到结果！';
        }
    });
}
// 获取当前路线
function getRoute() {
    // putPin();
    document.getElementById('routePanel').style.display = 'block';
    document.getElementById('searchPanel').style.display = 'none';
    document.getElementById('start').value = '当前位置';
    document.getElementById('start').style.color = 'blue';
    document.getElementById('start').style.fontWeight = 'bold';
    document.getElementById('end').value = '已放置的大头针';
    document.getElementById('end').style.color = 'purple';
    document.getElementById('end').style.fontWeight = 'bold';
    document.getElementById('start').select();
    showDriveline();
}
// 创建信息窗口内容信息
function createInfoWindows(info) {
    var content = '<div class="amap-lib-infowindow-title">' + info.num + '.' + info.data.name + '&nbsp;' + 
                    '</div>' + '<div class="amap-lib-infowindow-content">' + 
                    '<div class="amap-lib-infowindow-content-wrap">' + '<div>地址：' + info.data.address + '</div>';
    if (info.data.tel != '') {
        content = content + '<div>电话：' + info.data.tel + '</div>';
    }
    content = content + '</div>' + '</div>';
    return content;
}
// 返回路线地址
function formatAddress(data) {
    var address = data['township'] + data['street'] + data['streetNumber'] + data['neighborhood'] + data['building'];
    return address;
}


/* 文本框操作 */

// 搜索框查找
function findText() {
    document.getElementById('tipinput').select();
}
// 文本框全选
function selectAll () {
    document.activeElement.select();
}
// 文本框复制
function copyText() {
    var active = document.activeElement;
    var start = active.selectionStart;
    var end = active.selectionEnd;
    if(typeof(start) != 'undefined') {
        var str = active.value.substring(start, end);      
        copy(str);// 调用Java函数
    }
}
// 文本框剪切
function cutText() {
    var active = document.activeElement;
    var start = active.selectionStart;
    var end = active.selectionEnd;
    if(typeof(start) != 'undefined') {
        var str = active.value.substring(start, end);
        var leftstr = active.value.substring(0, start);
        var rightstr = active.value.substring(end, active.value.length);
        active.value = leftstr + rightstr;
        cut(str);// 调用Java函数
    }
}
// 文本框粘贴
function parstText (str) {
    var active = document.activeElement;
    var start = active.selectionStart;
    if(typeof(start) != 'undefined') {
        var leftstr = active.value.substring(0, start);
        var rightstr = active.value.substring(start, active.value.length);
        active.value = leftstr + str + rightstr;
    }
}
// 判断文本框是否有焦点
function focusText() {
    var tipinput = document.getElementById('tipinput');
    var start = document.getElementById('start');
    var end = document.getElementById('end');
    var active = document.activeElement;
    if (active == tipinput || active == start || active == end) {
        var length = active.selectionEnd - active.selectionStart;
        focused('YES', length);// 调用Java函数
    } else {
        focused('NO', 0);
    }
}


/* 回调函数 */

// 地图拖动时的回调事件——获取地图当前所在的城市   
function onMapMoveend() {
    map.getCity(function(data) {
        var info = JSON.stringify(data);
        region(info);//调用Java函数
    });
}
// toolbar进行定位的回调函数
function onToolLocation(e) {
    local.lnglat = e.lnglat;
    geocoder.getAddress(e.lnglat, function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            local.regeocode = result.regeocode;// 返回定位地址信息
        }
    });
}
// 鼠标右键点击的回调函数——获取鼠标所在位置
function onMapRightclick(e) {
    position.lnglat = e.lnglat;
    position.pixel = e.pixel;
    geocoder.getAddress(e.lnglat, function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            position.regeocode = result.regeocode;// 返回定位地址信息
            showMenu();// 调用Java函数显示右键菜单
        }
    });
}
// 点标记点击的回调事件
function onMarkerClick(e) {
    var marker = e.target;
    var position = marker.getPosition();
    var data = marker.getExtData();
    map.panTo(position);
    infoWindow.setContent(data['formattedAddress']);
    infoWindow.open(map, position);
}
// 搜索标记点击的回调事件
function onSearchMarkerClick(e) {
    var marker = e.target;
    var position = marker.getPosition();
    var data = marker.getExtData();
    map.panTo(position);
    infoWindow.setContent(createInfoWindows(data));
    infoWindow.open(map, position);
}
// 自动提示框选择的回调函数-根据选择的搜索框信息生成地图标记
function onAutocompleteSelect(e) {
    clearMarkers();
    document.getElementById('searchPanel').style.display = 'none';
    var info = e.poi;
    var content = info.name + '<br/>' + info.district + info.address;
    map.setCenter(info.location);
    autoMarker.setPosition(info.location);
    infoWindow.setContent(content);
    infoWindow.open(map, info.location);
    document.getElementById('tipinput').blur();
    document.getElementsByClassName('amap-sug-result')[0].style.display = 'none';
    flage = false;
}