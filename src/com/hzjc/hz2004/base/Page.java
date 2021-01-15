package com.hzjc.hz2004.base;

import java.util.List;

public class Page implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<?> list;
	private int totalCount;
	private int pageIndex;
	private int pageSize;
	private String other;
	public Page() {
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}



	/**
	 * 根据实体列表，构造分页信息类
	 *@param list
	 *实体列表
	 */
	public Page(List<?> list) {
		this.list = list;
	}

	/**
	 * 返回当前页实体列表
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 获取总记录数，并不是getList().size()的值
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置列表
	 * @param list 
	 * 列表。
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * 设置总记录数据
	 * @param totalCount 记录数
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
