package com.hzjc.hz2004.base.login;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.menu.Menu;

public class AuthToken implements Serializable{
	/**
	 * 获取当前用户
	 * @return
	 */
	public static AuthToken getCurrent(){
		AuthToken user = (AuthToken)BaseContext.getContext().getRequest().getAttribute(Constants.USER_ATTRNAME);
		if(user!=null)
			return user;
		
		String COOKIE_SID = HSession.getSID();
		user = HSession.getBaseUser(COOKIE_SID);
		if(user!=null)
			BaseContext.getContext().getRequest().setAttribute(Constants.USER_ATTRNAME, user);
		
		return user;
	}
	
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private String lastaccesstime = null;
	
	// 其它特殊内容，如果需要传递的话
	private Map<String, java.io.Serializable> sessionMap = new HashMap<String, Serializable>();
	private PoXT_YHXXB 					user;				//用户
	private PoXT_DWXXB 					organize;		//单位
	private String 							sid;				//会话ID, 非session有效
	private String 							authToken;	//登录字符串，和C/S通用
	private ExtMap<String,Object>		otherParams;
	private String 							khmsg;	//提醒内容
	private List<PoXT_YHSJFWB> sjfw;	//数据范围
	private PoPERSON_DY_SET    personDySet;//个人打印设置
	private PoDW_DY_SET dwDySet;//单位打印设置
	private List<PoXT_XTGNB> xtgn;	//用户功能List add by zjm 20190731 
	private String ip;
	private Menu menu;
	private boolean admin=false;
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getYhdlm(){
		if(this.getUser()==null)
			return "";
		
		return this.getUser().getYhdlm();
	}
	
	public PoDW_DY_SET getDwDySet() {
		return dwDySet;
	}

	public void setDwDySet(PoDW_DY_SET dwDySet) {
		this.dwDySet = dwDySet;
	}

	public String getKhmsg() {
		return khmsg;
	}
	public void setKhmsg(String khmsg) {
		this.khmsg = khmsg;
	}

	public String getLastaccesstime() {
		return lastaccesstime;
	}

	public void setLastaccesstime(String lastaccesstime) {
		this.lastaccesstime = lastaccesstime;
	}

	public Map<String, java.io.Serializable> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, java.io.Serializable> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public ExtMap<String, Object> getOtherParams() {
		return otherParams;
	}
	public void setOtherParams(ExtMap<String, Object> otherParams) {
		this.otherParams = otherParams;
	}
	public List<PoXT_YHSJFWB> getSjfw() {
		return sjfw;
	}
	public void setSjfw(List<PoXT_YHSJFWB> sjfw) {
		this.sjfw = sjfw;
	}
	public PoXT_YHXXB getUser() {
		return user;
	}
	public void setUser(PoXT_YHXXB user) {
		this.user = user;
	}
	public PoXT_DWXXB getOrganize() {
		return organize;
	}
	public void setOrganize(PoXT_DWXXB organize) {
		this.organize = organize;
	}

	public PoPERSON_DY_SET getPersonDySet() {
		return personDySet;
	}

	public void setPersonDySet(PoPERSON_DY_SET personDySet) {
		this.personDySet = personDySet;
	}

	public List<PoXT_XTGNB> getXtgn() {
		return xtgn;
	}

	public void setXtgn(List<PoXT_XTGNB> xtgn) {
		this.xtgn = xtgn;
	}
	
}
