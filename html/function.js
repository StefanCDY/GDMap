function setLeft(obj) {
    obj.style.textAlign = 'left';
}
// 逆向编码的回调函数
function geocoder_CallBack(data) {
    var address = data.regeocode.formattedAddress; //返回地址描述
    infoWindow.setContent(address);
    marker.setExtData(address);
}
function resultAlign(info) {
    if (info == 'NO_DATA') {
        document.getElementById('result').style.textAlign = 'center';
    } else {
        document.getElementById('result').style.textAlign = 'left';
    }
}
// 定位成功时的回调函数
function onGeolocationComplete(data) {
    geocoder.setCity(citycode);
    local['position'] = data.position;
    geocoder.getAddress(data.position, function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            // local = result.regeocode.formattedAddress; //返回地址描述
            local['formattedAddress'] = result.regeocode.formattedAddress;
            local['addressComponent'] = result.regeocode.addressComponent;
        }
    });
}
// 驾车路线
function showDriveline() {
    document.getElementById('driving').style.backgroundColor = 'rgb(45, 140, 250)';
    document.getElementById('walking').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('transfing').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('drive').style.display = 'block';
    document.getElementById('walk').style.display = 'none';
    document.getElementById('transfer').style.display = 'none';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start == '当前位置') {
        start = local['formattedAddress'];
    } else if (start == '已放置的大头针') {
        start = position['formattedAddress'];
    }
    if (end == '当前位置') {
        end = local['formattedAddress'];
    } else if (end == '已放置的大头针') {
        end = position['formattedAddress'];
    }
    driving.search([{keyword: start},{keyword: end}], function(status, result) {
        // alert(status + "-" + start + ":" + end);
        if (status != 'complete') {
            document.getElementById('drive').innerText = '查询路线失败';
        }
    });
}
// 步行路线
function showWalkline() {
    document.getElementById('driving').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('walking').style.backgroundColor = 'rgb(45, 140, 250)';
    document.getElementById('transfing').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('drive').style.display = 'none';
    document.getElementById('walk').style.display = 'block';
    document.getElementById('transfer').style.display = 'none';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start == '当前位置') {
        start = local['formattedAddress'];
    } else if (start == '已放置的大头针') {
        start = position['formattedAddress'];
    }
    if (end == '当前位置') {
        end = local['formattedAddress'];
    } else if (end == '已放置的大头针') {
        end = position['formattedAddress'];
    }
    walking.search([{keyword: start},{keyword: end}], function(status, result) {
        // alert(status + "-" + start + ":" + end);
        if (status != 'complete') {
            document.getElementById('walk').innerText = '查询步行路线失败';
        }
    });
}
// 公交路线
function showTransferline() {
    document.getElementById('driving').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('walking').style.backgroundColor = 'rgb(255, 255, 255)';
    document.getElementById('transfing').style.backgroundColor = 'rgb(45, 140, 250)';
    document.getElementById('drive').style.display = 'none';
    document.getElementById('walk').style.display = 'none';
    document.getElementById('transfer').style.display = 'block';
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    if (start == '当前位置') {
        start = local['formattedAddress'];
    } else if (start == '已放置的大头针') {
        start = position['formattedAddress'];
    }
    if (end == '当前位置') {
        end = local['formattedAddress'];
    } else if (end == '已放置的大头针') {
        end = position['formattedAddress'];
    }
    transfer.setCity(citycode);
    transfer.search([{keyword: start},{keyword: end}], function(status, result) {
        // alert(status + "-" + start + ":" + end);
        if (status != 'complete') {
            document.getElementById('transfer').innerText = '查询公交路线失败';
        }
    });
}

// 获取当前路线
function getRoute() {
    putPin();
    document.getElementById('routePanel').style.display = 'block';
    document.getElementById('start').value = '当前位置';
    document.getElementById('start').style.color = 'blue';
    document.getElementById('start').style.fontWeight = 'bold';
    document.getElementById('end').value = '已放置的大头针';
    document.getElementById('end').style.color = 'purple';
    document.getElementById('end').style.fontWeight = 'bold';
    document.getElementById('start').select();
    showDriveline();
}