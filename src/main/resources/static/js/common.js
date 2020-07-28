var layOpen = function (title,data,success) {
    layer.open({
        type: 1,
        title: title,
        anim: 5,
        area: ['60%', '60%'],
        content: data,
        success:success
    });
};
var layOpenToEnd = function (title,data,success,end) {
    layer.open({
        type: 1,
        title: title,
        anim: 1,
        area: ['60%', '60%'],
        content: data,
        success:success,
        end:end
    });
};

function zylVerCode(len){
    len = len || 4;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = $chars.length;
    var zylCode = '';
    for (i = 0; i < len; i++) {
        zylCode += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    $(".zylVerCode").html(zylCode);
}