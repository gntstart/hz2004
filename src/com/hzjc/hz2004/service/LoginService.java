package com.hzjc.hz2004.service;

import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.menu.Menu;

public interface LoginService {
	/**
	 * 登录
	 * @param strUserId
	 * @param strPassword
	 * @param strYzm
	 * @param strIp
	 * @return
	 */
	public AuthToken loginAuthToken(String strUserId, String strPassword, String strYzm, String strIp);
	
	/**
	 * 登录
	 * @param params
	 * @return
	 */
	public AuthToken loginAuthToken(String yhdlm, String dqbm);
	
	public void logout();
	
	/**
	 * 获取菜单
	 * @return
	 */
	public Menu getMenu();
	
	/**
	 * 获取业务报表模板菜单
	 * @return
	 */
	public Menu getYwbbmbMenu();
	
	/**
	 * 获取制式报表模板菜单
	 * @return
	 */
	public Menu getZsbbmbMenu();
	
	
}
