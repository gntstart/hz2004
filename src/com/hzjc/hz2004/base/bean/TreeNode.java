package com.hzjc.hz2004.base.bean;

import java.util.List;

import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JSXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_XTGNCDB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YWBBMBXXB;

public class TreeNode implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * null 无复选项 true 有复选，并选中 false 有复选，没选中
	 */
	private Boolean checked;
	
	private Boolean disabled;

	/**
	 * 节点默认是否展开
	 */
	private boolean expanded = false;
	/**
	 * text属性为节点的显示文字，必须
	 */
	private String text;
	/**
	 * 节点是否最终节点，必须： true Ext.tree.TreeNode类型的叶子节点 false 树枝节点，又分下面2中情况：
	 * 提供children 那么创建Ext.tree.TreeNode类型树枝节点 不提供children
	 * 那么创建Ext.tree.AsyncTreeNode类型树枝节点
	 */
	private boolean leaf;
	
	/**
	 * 本节点的子节点列表，当leaf为false时有效
	 */
	private List<TreeNode> children;

	private String pid; 						// 存放父亲节点数据
	private Code code; 						// 普通字典
	private String codevalue;						//字典值
	private SysOrganizeInfo zzjg; 		// 组织结构
	private PoXT_YHXXB zzjy; 				// 组织警员
	private String qtip; 						// 节点提示信息
	private String pathcode; 				// 用于展开树的属性
	private String icon;
	private String iconCls;
	private PoXT_JSXXB js;
	private PoXT_XZQHB xzqh;			//行政区划
	private PoXT_JWHXXB jwh;			//居委会
	private PoXT_DWXXB dw;			//普通单位
	private PoXT_XTGNCDB gncdb;
	private PoXT_XTGNB gnb;
	private PoXT_YWBBMBXXB ywmb;

	//下面为下拉菜单准备
	private TreeNode menu;
	private List items;
	private String handler;
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public SysOrganizeInfo getZzjg() {
		return zzjg;
	}
	public void setZzjg(SysOrganizeInfo zzjg) {
		this.zzjg = zzjg;
	}
	public PoXT_YHXXB getZzjy() {
		return zzjy;
	}
	public void setZzjy(PoXT_YHXXB zzjy) {
		this.zzjy = zzjy;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public String getPathcode() {
		return pathcode;
	}
	public void setPathcode(String pathcode) {
		this.pathcode = pathcode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public PoXT_JSXXB getJs() {
		return js;
	}
	public void setJs(PoXT_JSXXB js) {
		this.js = js;
	}
	public PoXT_XZQHB getXzqh() {
		return xzqh;
	}
	public void setXzqh(PoXT_XZQHB xzqh) {
		this.xzqh = xzqh;
	}
	public PoXT_JWHXXB getJwh() {
		return jwh;
	}
	public void setJwh(PoXT_JWHXXB jwh) {
		this.jwh = jwh;
	}
	public PoXT_DWXXB getDw() {
		return dw;
	}
	public void setDw(PoXT_DWXXB dw) {
		this.dw = dw;
	}
	public PoXT_XTGNCDB getGncdb() {
		return gncdb;
	}
	public void setGncdb(PoXT_XTGNCDB gncdb) {
		this.gncdb = gncdb;
	}
	public PoXT_XTGNB getGnb() {
		return gnb;
	}
	public void setGnb(PoXT_XTGNB gnb) {
		this.gnb = gnb;
	}
	public PoXT_YWBBMBXXB getYwmb() {
		return ywmb;
	}
	public void setYwmb(PoXT_YWBBMBXXB ywmb) {
		this.ywmb = ywmb;
	}
	public TreeNode getMenu() {
		return menu;
	}
	public void setMenu(TreeNode menu) {
		this.menu = menu;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}