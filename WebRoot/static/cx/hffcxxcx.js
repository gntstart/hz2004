
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;

Ext.onReady(function(){
	
	Gnt.submit(
			{}, 
			"cx/hffcxxcx/getMdFive.json",
			"查询md5信息，请稍后...", 
			function(jsonData,params){
				if(jsonData&&jsonData.message){
					Gnt.ux.Dict.getKzcs('10024', function(config, user, kzdata){
						var url = config.bz;
						url += "&sfzh=" + encodeURI(user.gmsfhm) 
							+ "&sign=" + encodeURI(jsonData.message);
						window.open(url);
					});
				}
			}
	);
	//var url = "http://10.125.2.42/uaac-server/login?"+ "appCode=iVerification&loginType=SFZH&service=http://10.125.2.140:8004/iVerification/index.do?resource=24&sfzh=" + user.gmsfhm+"&sign=7242B09D3A97100C6ACECB79A9BC4438" ;
	//window.open(url);
});