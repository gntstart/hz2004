package com.hzjc.menu;

import java.util.List;

import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.JSONUtil;

/**
    	root = {
			text:'根',
			expanded:true,
			children:[
				{text:'站长权限',leaf:false,
					expanded:true,
					children:[
						{text:'我绑定的加油站',leaf:true,data:{url:'jyzadmin/zz_jyzyj.jsp',code:'zz_jyzyj'}},
						{text:'我要回复',leaf:true,data:{url:'jyzadmin/zz_jyzht.jsp',code:'zz_jyzht'}}
					]
				}
			]
		};
 */
public class Menu implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String text;
	private boolean expanded;
	private boolean leaf;
	private List<Menu> children;
	private MenuData data;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public MenuData getData() {
		return data;
	}
	public void setData(MenuData data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 获取菜单
	 * @return
	 */
	public static String getMenu(){
		LoginService loginService = SpringContextHolder.getBean("loginService");
		return JSONUtil.toJSON(loginService.getMenu());
	}
	
	/**
	 * 获取业务报表模板打印菜单
	 * @return
	 */
	public static String getywbbmbMenu(){
		LoginService loginService = SpringContextHolder.getBean("loginService");
		return JSONUtil.toJSON(loginService.getYwbbmbMenu());
	}
	
	/**
	 * 获取制式报表模板打印菜单
	 * @return
	 */
	public static String getzsbbmbMenu(){
		LoginService loginService = SpringContextHolder.getBean("loginService");
		return JSONUtil.toJSON(loginService.getZsbbmbMenu());
	}
}
