window.wtx = window.wtx || {};
/**
 * 获取 url上的参数
 * @returns {Object}
 */
wtx.getGetParam=function(url) {
    var url = url||location.search; //获取url中"?"符后的字串
    var theRequest = {};
    if(url.indexOf("?")>0){
        url=url.substr(url.indexOf("?"));
    }
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            var s=strs[i].split("=");
            if(s.length==2)
                theRequest[s[0]]=unescape(s[1]).replace("+"," ");
        }
    }
    return theRequest;
}
wtx.ajax= function(options) {
    if (typeof options === 'object') {
       var params= options.data?options.data:{};
        $.ajax({
            type : options.type,
            url : options.url,
            dataType : "json",
            timeout : options.timeout?options.timeout:60000,//超时1分钟
            data : params,
            success : function(data) {
                if(data.success){
                    if(!data.list){
                        data.list=[];
                    }
                    if (data.message){
                    }
                    options.success(data.list,data.totalCount,data.message);
                } else {
                    var message="";
                   if (data.message) {
                       message=data.message;
                    } else{
                       message = "服务器返回未知错误！";
                    }
                    if (options.error){
                       options.error(message);
                    }else{
                        alert(message);

                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                var message="您的网络不太给力哦，请稍后再试!";
                if (options.error){
                    options.error(message);
                }else{
                    alert(message);
                }
            }
        });
    }
};
/**
 * 异步GET请求
 * @param url
 * url地址
 * @param success
 * 成功回调方法
 * @param error
 * 失败回调方法
 */
wtx.get=function(url,success,error){
    wtx.ajax({url:url,type:'get',success:success,error:error});
}
/**
 * 异步POST请求
 * @param url
 * url地址
 * @param params
 * 参数
 * @param success
 * 成功回调方法
 * @param error
 * 失败回调方法
 */
wtx.post=function(url,params,success,error){
    wtx.ajax({url:url,type:'post',data:params,success:success,error:error});
}
