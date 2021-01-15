
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;

Ext.onReady(function(){
	//http://10.124.13.243:8280/zwcj/jcLogin?idCard=hh&sfzh=341226198310100673
	var url = "http://10.124.13.243:8280/zwcj/jcLogin?"+ "idCard=" + encodeURIComponent(user.usercode)+"&sfzh=" + user.gmsfhm ;
	window.open(url);
});