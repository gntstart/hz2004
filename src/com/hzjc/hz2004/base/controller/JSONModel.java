package com.hzjc.hz2004.base.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.alibaba.fastjson.annotation.JSONField;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;

/**
 * Created by Administrator on 2016/9/8.
 */
public class JSONModel implements java.io.Serializable{
    public enum MessageType{
        Info,Warning,Error
    }
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private MessageType messageType=MessageType.Info;
    private List<?> list = null;
    private String dateFormat;
    private int totalCount;
    @SuppressWarnings("unused")
    private long responseTime;
    private Map<String,String> opmap = null;
    //异常代码，特殊异常需要进一步处理
    private int errcode;
    private String spmbid;
    
    public JSONModel(){
        this(true,"");
    }
    public JSONModel(boolean _success, String _message){
        message=_message;
        success=_success;
        //记录响应时间
        responseTime=new Date().getTime();
        //list=new ArrayList<>();
        totalCount = 0;
    }
    
    public String getSpmbid() {
		return spmbid;
	}
	public void setSpmbid(String spmbid) {
		this.spmbid = spmbid;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public Map<String, String> getOpmap() {
		return opmap;
	}
	public void setOpmap(Map<String, String> opmap) {
		this.opmap = opmap;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setEntity(Object entity){
        if(null==entity){
            return;
        }
        if(entity instanceof Page){
            Page page = (Page)entity;
            if(page.getList()!=null){
                setTotalCount(page.getTotalCount());
                setList(page.getList());
            }
            return;
        }
        if(entity instanceof List){
            List<?> l = (List<?>)entity;
            setList(l);
            setTotalCount(l.size());
            return;
        }
        List<Object> l =new ArrayList<Object>();
        l.add(entity);
        setList(l);
        setTotalCount(1);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public List<?> getList() {
        return list;
    }
    public void setList(List<?> list) {
        this.list = list;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString(){
        return JSONUtil.toJSON(this,dateFormat);
    }

    @JSONField(serialize = false)
    public String getDateFormat() {
        return dateFormat;
    }
    @JSONField(serialize = false)
    public Object getEntity() {
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        return list.get(0);

    }
    
    @JsonIgnore
    public <T> List<T> getList(Class<T> clz) {
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        if(clz==list.get(0).getClass()){
            return (List<T>) list;
        }
        return JSONUtil.fromArrayJSON(JSONUtil.toJSON(list),clz);
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
