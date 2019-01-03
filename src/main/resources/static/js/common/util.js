// js获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

// 字符串转时间
function toDate(str) {
    str = str.replace(/-/g, "/");
    var oDate1 = new Date(str);
    return oDate1;
}

// 计算传入日期与num求和后得到新日期
function dateAdd(num, date) {
    var v = date.valueOf();
    v += num;
    return new Date(v);
}

// 把时间转换成字符串
function timeStamp2String(datetime, formate) {
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    var str = formate;
    str = str.replace("yyyy", year);
    str = str.replace("MM", month);
    str = str.replace("dd", date);
    str = str.replace("HH", hour);
    str = str.replace("mm", minute);
    str = str.replace("ss", second);
    return str;
}
