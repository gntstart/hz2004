var CardReadType = null;
var lgEdz = dkqlx;//cofig里查询出来读卡器类型，全局变量
var rdcard;
var CVR_IDCard;
var JL_IDCard;
var XZX_IDCard;
var scanHtml = null;
function initReadCard(){
	if(!CVR_IDCard){
		CVR_IDCard = document.getElementById("_READ_CARD_ID");
		if(CVR_IDCard){
			//如果DOM树存在特殊标记
			if (lgEdz=='1') {
				scanHtml += '<DIV style="display=\'none\'"><OBJECT id="RD_OBJ" name="RD_OBJ" classid="clsid:F1317711-6BDE-4658-ABAA-39E31D3704D3" width="100" height="100"></OBJECT></DIV>';
			} else if (lgEdz == '2') {
				scanHtml += '<DIV style="display=\'none\'"><OBJECT id="CVR_OBJ" name="CVR_OBJ" classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7" width="100" height="100"></OBJECT></DIV>';
			} else if (lgEdz == '5') {
				scanHtml += '<DIV style="display=\'none\'"><OBJECT id="JL_OBJ" name="JL_OBJ" classid="clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF" width="100" height="100"></OBJECT></DIV>';
			} else if (lgEdz == '4') {
				scanHtml += '<DIV style="display=\'true\'"><OBJECT id="XZX_OBJ" name="XZX_OBJ" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039" width="0" height="0" border="0"></OBJECT></DIV>';
			}
			CVR_IDCard.innerHTML = scanHtml;
			//读卡器类型
		    if (lgEdz=='1') {
		    	rdcard = document.getElementById("RD_OBJ");
		    } else if (lgEdz == '2') {
		    	CVR_IDCard = document.getElementById("CVR_OBJ");
		    }if (lgEdz == '5') {
		    	JL_IDCard = document.getElementById("JL_OBJ");
		    }if (lgEdz == '4') {
		    	XZX_IDCard = document.getElementById("XZX_OBJ");
		    }
			
		}
	}
	//CVR_IDCard = document.getElementById("CVR_OBJ");
}

//读卡器类型
//    if (lgEdz=='1') {
//    	rdcard = document.getElementById("RD_OBJ");
//    } else if (lgEdz == '2') {
//    	CVR_IDCard = document.getElementById("CVR_OBJ");
//    }if (lgEdz == '5') {
//    	JL_IDCard = document.getElementById("JL_OBJ");
//    }if (lgEdz == '4') {
//    	XZX_IDCard = document.getElementById("XZX_OBJ");
//    }
//  

  //验证读卡器类型  
	function checkOcx(flag,dom){
		CardReadType=1;
	  try{
	  		CVR_IDCard.DoStopRead;
	  		ClearIDCard(flag);
	  		CardReadType=2;
	   }catch(e){
		   	try{
			   	rdcard.EndRead();
			   	rdcard.GetState(); 
				 	CardReadType=1;
			 }catch(a){
			 	try{
			 		XZX_IDCard.FindReader();
			 		CardReadType=4;
			 	}catch(a){}
			 }
	  }
	}
	//二代证对象
	function IdCard(){
		this.Name="";
		this.NameL="";
		this.Sex ="";
		this.SexL="";
		this.Nation="";
		this.NationL="";
		this.Born="";
		this.BornL="";
		this.CardNo ="";
		this.Address="";
		this.Police="";
		this.PhotoPath ="c:\\";     
		this.Ret=0;
		this.lastError="";
		this.picbase64="";
	}
	//二代证读取
	function readCard(flag){
		var rt=new IdCard();
		if(CardReadType==1){//神思
			rt.Ret=rdcard.ReadCard();
			if(rdcard.bHaveCard){
				rt.Name=trim(rdcard.NameS);
				rt.NameL=trim(rdcard.NameL);
				rt.Sex=trim(rdcard.Sex);
				rt.SexL=trim(rdcard.SexL);
				rt.Nation=trim(rdcard.Nation);
				rt.NationL=trim(rdcard.NationL);
				rt.Born=trim(rdcard.Born);
				rt.BornL=trim(rdcard.BornL);
				rt.CardNo=trim(rdcard.CardNo);
				rt.Address=trim(rdcard.Address);
				rt.Police=trim(rdcard.Police);
				rt.PhotoPath =rdcard.PhotoPath;
			}else{
				if(rt.Ret==-1)rt.lastError= '相片解码错误';
				else if(rt.Ret==-2)rt.lastError= 'wlt文件后缀错误';
				else if(rt.Ret==-3)rt.lastError= 'wlt文件打开错误';
				else if(rt.Ret==-4)rt.lastError= 'wlt文件格式错误';
				else if(rt.Ret==-5)rt.lastError= '软件授权文件错误或没有授权文件';
				else if(rt.Ret==-6)rt.lastError= '设备连接错误';
				else if(rt.Ret==-8)rt.lastError= '文件存储失败';
				else if(rt.Ret==-10)rt.lastError= '端口操作失败';
				else if(rt.Ret==-11)rt.lastError= '解码失败';
				else if(rt.Ret==2)rt.lastError= '接收数据超时';
			}
		}else if(CardReadType==2){//华视
			CVR_IDCard.PhotoPath=rt.PhotoPath;
			CVR_IDCard.TimeOut=3;
			rt.Ret= CVR_IDCard.ReadCard;
			
			if(rt.Ret==0){
			  rt.Name=trim(CVR_IDCard.Name);
				rt.NameL=trim(CVR_IDCard.NameL);
				rt.Sex=trim(CVR_IDCard.Sex);
				rt.SexL=trim(CVR_IDCard.SexL);
				rt.Nation=trim(CVR_IDCard.Nation);
				rt.NationL=trim(CVR_IDCard.NationL);
				rt.Born=trim(CVR_IDCard.Born);
				rt.BornL=trim(CVR_IDCard.BornL);
				rt.CardNo=trim(CVR_IDCard.CardNo);
				rt.Address=trim(CVR_IDCard.Address);
				rt.Police=trim(CVR_IDCard.Police);
				//alert(rt.Nation);//新版返回汉字，老板是代码
				if(flag==0){
					if(rt.Nation='01'){
						rt.PhotoPath =CVR_IDCard.PhotoPath;
					}else{
						rt.PhotoPath ="c:\\zp.bmp";
					}
				}
				if(flag==1){
					rt.PhotoPath =CVR_IDCard.PhotoPath;
				}
				rt.picbase64=CVR_IDCard.Picture;
			}else{
				if(rt.Ret==-1)rt.lastError= '未连接机具';
				else if(rt.Ret==-2)rt.lastError= '放卡超时';
				else if(rt.Ret==-3)rt.lastError= '用户已取消读卡';
				else if(rt.Ret==-4)rt.lastError= '读基本信息出错';
				else if(rt.Ret==-5)rt.lastError= '照片创建失败';
			}
		}else if(CardReadType==5){//精伦
			rt.Ret= JL_IDCard.ReadCard("1001",rt.PhotoPath);
			if(rt.Ret==1){
			  rt.Name=trim(JL_IDCard.GetName());
				rt.Sex=trim(JL_IDCard.GetSex());
				rt.Nation=trim(JL_IDCard.GetFolk());
				rt.Born=trim(JL_IDCard.GetBirthYear() + "年" + JL_IDCard.GetBirthMonth() + "月" + JL_IDCard.GetBirthDay() +  "日");
				rt.CardNo=trim(JL_IDCard.GetCode());
				rt.Address=trim(JL_IDCard.GetAddress());
				rt.Police=trim(JL_IDCard.GetAgency());
				rt.PhotoPath ="c:\\zp.bmp";
			}else{
				if(rt.Ret==-1)rt.lastError= '端口初始化失败';
				else if(rt.Ret==-2)rt.lastError= '卡认证失败（请重新将卡放到读卡器）';
				else if(rt.Ret==-3)rt.lastError= '读取数据失败';
				else if(rt.Ret==-4)rt.lastError= '生成照片文件失败（请检查设定路径和磁盘空间）';
			}
			//关闭接口
			JL_IDCard.CloseComm();
		}else if(CardReadType==4){//新中新
			//alert("1111111111111");
			
			var t_dkh= XZX_IDCard.FindReader();//自动寻找读卡器
			if(t_dkh> 0){
				XZX_IDCard.SetPhotoPath(0,"");//设置照片保存目录为C盘根目录
				XZX_IDCard.SetReadType(0);//手动读卡
	  			rt.Ret = XZX_IDCard.ReadCardMsg();
	  			if(rt.Ret==0){
	  				rt.Name=trim(XZX_IDCard.NameA);
						rt.Sex=trim(XZX_IDCard.Sex);
						rt.Nation=trim(XZX_IDCard.Nation);
						rt.Born=trim(XZX_IDCard.Born);
						rt.CardNo=trim(XZX_IDCard.CardNo);
						rt.Address=trim(XZX_IDCard.Address);
						rt.Police=trim(XZX_IDCard.Police);
						rt.PhotoPath =XZX_IDCard.PhotoName;
	  			}
	  		}else{
				rt.lastError= '没有找到读卡器';
			}
			
		}
		else{
	 	  rt.Ret=-1;	
	 	  rt.lastError="请先正确安装读卡器!";
		}
		return rt;
	}  
	function trim(str){ //删除左右两端的空格
	　　     return str.replace(/(^\s*)|(\s*$)/g, "");
	} 
	//
	function ClearIDCard(flag) {
	   CVR_IDCard.Name="";
	   CVR_IDCard.NameL="";
	   CVR_IDCard.Sex="";   
	   CVR_IDCard.Nation="";
	   CVR_IDCard.Born="";
	   CVR_IDCard.Address="";
	   CVR_IDCard.CardNo="";
	   CVR_IDCard.Police="";
	   CVR_IDCard.Activity="";
	   CVR_IDCard.NewAddr="";
	   CVR_IDCard.PhotoPath="";
	   CVR_IDCard.Picture="";
	  
	   return true;
	}