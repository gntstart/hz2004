package com.hzjc.hz2004.util;
import java.util.ArrayList;
import java.util.List;
public class FileInfo {

	//文件id
	private String id;
	//文件名称
	private String name;
	private String text;
	//文件路径
	private String path;
	//是否有目录，有无子节点
	private boolean leaf;
	//修改日期
	private String editDate;
	//后缀
	private String suffix;
	//长度
	private long length;
	// 子目录中所有文件
	private List<FileInfo> children = new ArrayList<FileInfo>();
	//setter、getter	
	public String toString() {
		return "name:" + name + ", size:" + children.size();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public List<FileInfo> getChildren() {
		return children;
	}
	public void setChildren(List<FileInfo> children) {
		this.children = children;
	}
	
}
