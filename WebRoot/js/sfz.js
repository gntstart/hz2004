
/*
 * ============================身份证验证====================================
 */
 
 /**
  *验证身份证号码是否有效，同时验证身份证号码与性别、出生日期是否相符。验证通过返回true ，否则给出提示并返回false
  *strIdCard ：身份证号码字符串
  *strSexCode： 性别代码  1:男    2:女
  *objDateBirthday ：出生日期的日期对象（javascript 的Date对象）
  */
function validateCard(strIdCard,strSexCode,objDateBirthday){

	if(!isIDCard(strIdCard)){
		return false;
	}
	
	if(strSexCode == "1" || strSexCode == "2"){
		if(!checkIDCardSex(strIdCard,strSexCode)){
			return false;
		}	
	}	
	
	if(objDateBirthday != null){	
		var intYear = objDateBirthday.getYear();
		var strYear = intYear.toString();
		if(strYear.length < 4){
			strYear = "19" + strYear;
		}
				
		var month = objDateBirthday.getMonth() + 1;		
		var strMonth = month.toString();		
		if(month < 10){
			strMonth = "0" + month.toString();
		}
		
		var date = objDateBirthday.getDate();
		var strDate = date.toString();
		
		if(date < 10){
			strDate = "0" + date.toString();;
		}
		
		var strBirthday = strYear + strMonth + strDate;		
		if(!checkIDCardBirth(strIdCard,strBirthday)){
			return false;
		}
	}
	
	return true;	
}

function isIDCard(editorValue) {
    var num = editorValue;
    var len = num.length;
    var re = null;
    
    if (len == 15) {
        if (isNaN(num)) {
            alert("输入的身份证号不是数字！");
            return false;
        }
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);        
    } else if (len == 18) {
        if (isNaN(num)) {
            if (isNaN(num.substr(0,17))) {
                alert("输入的身份证号不是数字！");
                return false;
            }
        }
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
    } else {
        alert ("输入的身份证号数字位数不对！"); 
        return false;   
    }
    
    var a = num.match(re);
    if (a != null)    {
        if (len==15)      {
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
        } else {
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];

        }
        if (!B) {
            alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); 
            return false;
        }
    }
    
    if (len == 18) {
        if (!isCheckCode(num)) {
            alert("输入的身份证号与校验码不符！ ");
            return false;
        }        
    }
    return true;
}
// 2008 06 17
function isIDCard2(editorValue) {
    var num = editorValue;
    var len = num.length;
    var re = null;
    
    if (len == 15) {
        if (isNaN(num)) {
            return false;
        }
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);        
    } else if (len == 18) {
        if (isNaN(num)) {
            if (isNaN(num.substr(0,17))) {
                return false;
            }
        }
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
    } else {
        return false;   
    }
    
    var a = num.match(re);
    if (a != null)    {
        if (len==15)      {
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
        } else {
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];

        }
        if (!B) {
            return false;
        }
    }
    
    if (len == 18) {
        if (!isCheckCode(num)) {
            return false;
        }        
    }
    return true;
}
/*
 * 身份证验证码
 */
function isCheckCode(num) {
    var checkCodes =  new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var mulFactors =  new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var iPtr = null;
    
    var i=0;
    
    for (i=0; i<17; i++) {
        iPtr = iPtr + parseInt(num.substr(i,1))*parseInt(mulFactors[i]);
    }
    iPtr = iPtr%11;
    //alert(checkCodes[iPtr]);
    if (checkCodes[iPtr] != num.substr(17,1)) {
        return false;
    }
    
    return true;
} 



//身份证15转18位
function id15to18(num){
    if(num.length==15){
       var id17=num.substring(0,6)+"19"+num.substring(6,12)+num.substring(12,15);
       return id17+getIDLastCode(id17);
    }
    else{
       return num; 
    }    

}
//身份证18转15位
function id18to15(num){
    if(num.length==18){
       return num.substring(0,6)+num.substring(8,17);
    }
    else{
       return num; 
    }    

}
function getIDLastCode(num) {
    var checkCodes =  new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var mulFactors =  new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var iPtr = null;
    
    var i=0;
    
    for (i=0; i<17; i++) {
        iPtr = iPtr + parseInt(num.substr(i,1))*parseInt(mulFactors[i]);
    }
    iPtr = iPtr%11; 
    
    return checkCodes[iPtr];
} 

//生日和身份证是否一致
//IdCardNo 身份证号码支持15位与18位长度的身份证号
//birth  出生日期，格式：年年年年月月日日
function checkIDCardBirth(IdCardNo,Birth){
    var vBirth="";
    
    //出生日期格式检查
    var vDay=Birth.substring(6,8);//日
    var vMonth=Birth.substring(4,6);
    var vYear=Birth.substring(0,4);
    //alert(vDay+ " "+vMonth+" "+ vYear);
    if(!isValidDate(vDay, vMonth, vYear)){
        alert("出生日期格式不正确!");
		return false;
    }
    switch (IdCardNo.length){
        case 15:{            
            vBirth="19"+IdCardNo.substring(6,12);
            break;
            }
        case 18:{            
            vBirth=IdCardNo.substring(6,14);            
            break;
            }
	    default:{
		   	alert("身份证号码格式不正确!");
		   	return false;
		    }
    }     
    if(vBirth!=Birth){
        alert("身份证号码与出生日期不匹配!");
        return false;   
    }

    //alert("IdCardNo:"+IdCardNo+",Birth:"+Birth+",Sex:"+Sex);
    //alert("vBirth:"+vBirth+",vSex:"+vSex);
    return true;
}
function isValidDate(day, month, year) {
	if(day.length<1 && month.length <1 && year.length<1) return true;

    if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) &&
            (day == 31)) {
            return false;
        }
        if (month == 2) {
            var leap = (year % 4 == 0 &&
                       (year % 100 != 0 || year % 400 == 0));
            if (day>29 || (day == 29 && !leap)) {
                return false;
            }
        }
        return true;
}

//性别与身份证是否一致
//IdCardNo 身份证号码支持15位与18位长度的身份证号
//sex:1:男 2女
function checkIDCardSex(IdCardNo,Sex){
    //sex:1:男 2女
    var vSex="";
    
    switch (IdCardNo.length){
        case 15:{
            vSex=IdCardNo.substring(IdCardNo.length-1);
            break;
            }
        case 18:{
            vSex=IdCardNo.substring(IdCardNo.length-2,IdCardNo.length-1);       
            break;
            }
	    default:{
		   	alert("身份证号码格式不正确!");
		   	return false;
		   }
    }    
    if((vSex%2)==0) vSex=2;
    else vSex=1;  

    if(vSex!=Sex){
        alert("身份证号码与性别不匹配!");
        return false;   
    } 
    //alert("IdCardNo:"+IdCardNo+",Birth:"+Birth+",Sex:"+Sex);
    //alert("vBirth:"+vBirth+",vSex:"+vSex);
    return true;
}

function getIDCardSex(IdCardNo){
    //sex:1:男 2女
    var vSex="";
    
    switch (IdCardNo.length){
        case 15:{
            vSex=IdCardNo.substring(IdCardNo.length-1);
            break;
            }
        case 18:{
            vSex=IdCardNo.substring(IdCardNo.length-2,IdCardNo.length-1);       
            break;
            }
	    default:{
		   return "身份证号码格式不正确!";
	}
    }
    
    if((vSex%2)==0) vSex="2";
    else vSex="1";
    
    //alert("IdCardNo:"+IdCardNo+",Birth:"+Birth+",Sex:"+Sex);
    //alert("vBirth:"+vBirth+",vSex:"+vSex);
    return vSex;
}

//从身份证计算出生日
function SynclCsrq(f){
//f.sfzh.value
    IdCardNo=f;
    vBirth="";
    switch (IdCardNo.length){
        case 15:{            
            vBirth="19"+IdCardNo.substring(6,12);
            break;
            }
        case 18:{            
            vBirth=IdCardNo.substring(6,14);            
            break;
            }
    }
    if(vBirth!=""){
	var year = parseInt(vBirth.substr(0,4));
	var mon =  parseInt(vBirth.substr(4,2));
	var day =  parseInt(vBirth.substr(6,2));
	if(vBirth.substr(4,1)=='0')
		mon = vBirth.substr(5,1)
	if(vBirth.substr(6,1)=='0')
		day = vBirth.substr(7,1)	
    	var csrqYear = new Date(parseInt(year),parseInt(mon)-1,parseInt(day)); // 初始化日期对象
        return csrqYear; 
     }
          
}
//从身份证计算性别
function setXb(obj){
    var IdCardNo=obj;
    if(IdCardNo!=""){
        var xb = IdCardNo.substring(16,17)%2==0?"2":"1";
	return xb;
    }
    
}