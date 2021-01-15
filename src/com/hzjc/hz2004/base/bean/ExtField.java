package com.hzjc.hz2004.base.bean;

import java.io.Serializable;

import com.hzjc.hz2004.po.PoXT_SJPZB;

public class ExtField implements Serializable{
	private static final long serialVersionUID = 6276732388509713381L;

	private String xtype;//'tree_comboBox',
	private String searchUrl;//'yw/dqdict.do?method=searchXzqh',
	private String treeUrl;//'yw/dqdict.do?method=searchTreeXzqh',
	private String selMode;//'single', //single单选，默认；multi多选
	private String valueField;//"code",
	private String displayField;// "name",
	private String name;//'qcdssxq',
	private String dict;//:'VisionType=CS_HLX'
	private double columnWidth;//:0.25,
	private String maxLength;//:40,
	private boolean allowBlank;//:false,
	private String fieldLabel;//:'迁入地'
	private String id;
	private PoXT_SJPZB pz;
	private String anchor;
	private String blankText;// : String
	private String emptyText;// : String
	private String labelStyle ;//: String
	private String labelSeparator;// : String
	private Integer labelWidth;
	private String altFormats;//:'Ymd|Y-m-d|Y/m/d',
	private String format;//:'Y-m-d',
	private String dsname; //字典名称
	
	private String mode;//: 'local',
	private String triggerAction;//: 'all',
	private Boolean selectOnFocus;//:true,
	private Boolean typeAhead;//: true,  
	private Boolean editable;//:true,
	private Boolean forceSelection;//: true,
	private Boolean lazyRender;//:true,
	private String readonly = "0";
	
	public ExtField(PoXT_SJPZB pz){
		//this.pz = pz;
		//this.id = pz.getId();
		this.name = pz.getFieldname();
		this.fieldLabel = pz.getDisplayname();
		this.columnWidth = 0.25;
		this.anchor = "100%";
		this.xtype = "textfield";
		this.labelWidth = 120;
		this.readonly = pz.getReadonly();
		
		if(pz.getDisplayname()!=null)
			this.setAllowBlank(!pz.getDisplayname().startsWith("*"));
		
		if(pz.getVisibletype().startsWith("000")){
			this.xtype = "hidden";
		}else{
			if(pz.getFieldtypename()==null){
				;
			}else if(pz.getFieldtypename().equals("dateedit")){
				//日期
				this.xtype = "datefield";
				this.altFormats = "Ymd|Y-m-d|Y/m/d";
				this.format = "Ymd";
			}else if(pz.getFieldtypename().equals("codeedit")){
				this.dsname = pz.getDsname().toUpperCase();
				this.xtype = "dict_combox";
				this.dict = "VisionType=" + pz.getDsname().toUpperCase();
				
				//行政区划
				if(this.dsname.startsWith("XZQHB")){
					this.xtype = "tree_comboBox";
					this.searchUrl = "yw/dqdict.do?method=searchXzqh";
					this.treeUrl="yw/dqdict.do?method=searchTreeXzqh";
				}else if(!this.dsname.startsWith("CS_")){
					//可选可输入
					this.forceSelection = false;
					this.selectOnFocus = false;
					this.xtype = "dict_combox_cust";
				}
			}
		}
	}
	
	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTriggerAction() {
		return triggerAction;
	}

	public void setTriggerAction(String triggerAction) {
		this.triggerAction = triggerAction;
	}

	public Boolean getSelectOnFocus() {
		return selectOnFocus;
	}

	public void setSelectOnFocus(Boolean selectOnFocus) {
		this.selectOnFocus = selectOnFocus;
	}

	public Boolean getTypeAhead() {
		return typeAhead;
	}

	public void setTypeAhead(Boolean typeAhead) {
		this.typeAhead = typeAhead;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getForceSelection() {
		return forceSelection;
	}

	public void setForceSelection(Boolean forceSelection) {
		this.forceSelection = forceSelection;
	}

	public Boolean getLazyRender() {
		return lazyRender;
	}

	public void setLazyRender(Boolean lazyRender) {
		this.lazyRender = lazyRender;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getAltFormats() {
		return altFormats;
	}

	public void setAltFormats(String altFormats) {
		this.altFormats = altFormats;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(Integer labelWidth) {
		this.labelWidth = labelWidth;
	}

	public String getBlankText() {
		return blankText;
	}

	public void setBlankText(String blankText) {
		this.blankText = blankText;
	}

	public String getEmptyText() {
		return emptyText;
	}

	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	public String getLabelStyle() {
		return labelStyle;
	}

	public void setLabelStyle(String labelStyle) {
		this.labelStyle = labelStyle;
	}

	public String getLabelSeparator() {
		return labelSeparator;
	}

	public void setLabelSeparator(String labelSeparator) {
		this.labelSeparator = labelSeparator;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public PoXT_SJPZB getPz() {
		return pz;
	}

	public void setPz(PoXT_SJPZB pz) {
		this.pz = pz;
	}

	public String getXtype() {
		return xtype;
	}
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public String getTreeUrl() {
		return treeUrl;
	}
	public void setTreeUrl(String treeUrl) {
		this.treeUrl = treeUrl;
	}
	public String getSelMode() {
		return selMode;
	}
	public void setSelMode(String selMode) {
		this.selMode = selMode;
	}
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public String getDisplayField() {
		return displayField;
	}
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(double columnWidth) {
		this.columnWidth = columnWidth;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
