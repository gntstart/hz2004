package com.hzjc.hz2004.base.proc;

import java.io.CharArrayWriter;
import java.io.Reader;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hzjc.hz2004.base.proc.CallParameter.ParameterType;
import com.hzjc.hz2004.base.proc.CallParameter.ParameterValueType;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.util.DateConverter;
import com.hzjc.hz2004.util.DateHelper;

import oracle.jdbc.internal.OracleTypes;

/**
 * 存储过程对象
 * @author ting_it
 *
 */
public class CallProc {
	//存储过程调用名称，例如：Fsp_Plan_CheckPrj(?,?,?)
	private String procname = null;
	//存储过程参数
	private List<CallParameter> params = null;
	//返回数据
	private CallReturnData returnData = null;
	
	public List<CallParameter> getParams() {
		return params;
	}

	public void setParams(List<CallParameter> params) {
		this.params = params;
	}

	public CallReturnData getReturnData() {
		return returnData;
	}

	public void setReturnData(CallReturnData returnData) {
		this.returnData = returnData;
	}

	public String getProcname() {
		return procname;
	}

	public void setProcname(String procname) {
		this.procname = procname;
	}
	
	private int getSQLType(CallParameter param){
		if(param.getValueType()==CallParameter.ParameterValueType.CURSOR)
			return OracleTypes.CURSOR;
		
		if(param.getValueType()==CallParameter.ParameterValueType.DATE)
			return java.sql.Types.DATE;
		
		if(param.getValueType()==CallParameter.ParameterValueType.NUMBER)
			return java.sql.Types.NUMERIC;
		
		if(param.getValueType()==CallParameter.ParameterValueType.STRING)
			return java.sql.Types.VARCHAR;
		
		return java.sql.Types.VARCHAR;
	}
	
	public int executeCall(CallableStatement call){
		try{
			if(getParams()!=null){
				//参数
				for(CallParameter param: getParams()){
					if(param.getParameterType().equals(ParameterType.OUT)){
						//入参
						call.registerOutParameter(param.getParameterIndex(), getSQLType(param));
					}else{
						//字符型
						if(param.getValueType()==CallParameter.ParameterValueType.STRING){
							if(param.getParameterValue()==null){
								call.setNull(param.getParameterIndex(), getSQLType(param));
							}else{
								if(param.getParameterValue() instanceof String){
									call.setString(param.getParameterIndex(), (String)param.getParameterValue());
								}else{
									call.setString(param.getParameterIndex(), param.getParameterValue().toString());
								}
							}
							continue;
						}
						
						//日期型
						if(param.getValueType()==CallParameter.ParameterValueType.DATE){
							//日期型
							if(param.getParameterValue()==null){
								call.setNull(param.getParameterIndex(), getSQLType(param));
							}else{
								if(param.getParameterValue() instanceof Date){
									call.setTimestamp(param.getParameterIndex(), new java.sql.Timestamp(((Date)param.getParameterValue()).getTime()));
								}else{
									Date date = DateConverter.convertDate(param.getParameterValue());
									if(date==null)
										throw new ServiceException("日期" + param.getParameterValue() + "参数格式不正确！");
									
									call.setTimestamp(param.getParameterIndex(), new java.sql.Timestamp(date.getTime()));
								}
							}
							continue;
						}
						
						//数字型
						if(param.getValueType()==CallParameter.ParameterValueType.NUMBER){
							if(param.getParameterValue()==null){
								call.setNull(param.getParameterIndex(), getSQLType(param));
							}else{
								if(param.getParameterValue() instanceof Long){
									call.setLong(param.getParameterIndex(), (Long)param.getParameterValue());
								}else if(param.getParameterValue() instanceof Integer){
									call.setInt(param.getParameterIndex(), (Integer)param.getParameterValue());
								}else if(param.getParameterValue() instanceof Double){
									call.setDouble(param.getParameterIndex(), (Double)param.getParameterValue());
								}else if(param.getParameterValue() instanceof Float){
									call.setFloat(param.getParameterIndex(), (Float)param.getParameterValue());
								}else{
									call.setString(param.getParameterIndex(), param.getParameterValue().toString());
								}
							}
							continue;
						}
						
						throw new ServiceException("不支持的入参数据类型！");
					}
				}
				int count = call.executeUpdate();

				CallReturnData reData = new CallReturnData();
				this.setReturnData(reData);
				
				for(CallParameter param: getParams()){
					if(param.getParameterType().equals(ParameterType.OUT)){
						if(param.getValueType()==CallParameter.ParameterValueType.NUMBER
								|| param.getValueType()==CallParameter.ParameterValueType.STRING){
							String value = call.getString(param.getParameterIndex());
							if(value!=null){
								if(param.getParameterName()==null){
									reData.getData().put("re" + param.getParameterIndex(), value);
								}else{
									reData.getData().put(param.getParameterName(), value);
								}
							}
						}
						
						if(param.getValueType()==CallParameter.ParameterValueType.DATE){
							Date date = call.getDate(param.getParameterIndex());
							if(date!=null){
								if(param.getParameterName()==null){
									reData.getData().put("re" + param.getParameterIndex(), DateHelper.formateDate(date, DateHelper.PRINT_DATETIME_STYLE2));
								}else{
									reData.getData().put(param.getParameterName(), DateHelper.formateDate(date, DateHelper.PRINT_DATETIME_STYLE2));
								}
							}
						}
						
						if(param.getValueType()==CallParameter.ParameterValueType.CURSOR){
							ResultSet res = null;
							try{
									res = (ResultSet)call.getObject(param.getParameterIndex());
									if(res!=null){
										java.sql.ResultSetMetaData meta = res.getMetaData();
										String cols = null;
										for(int i=1;i<=meta.getColumnCount();i++){
											if(cols==null)
												cols = meta.getColumnName(i);
											else
												cols += "," + meta.getColumnName(i);
										}
										
										reData.getTableschema().put(param.getParameterName(), cols.toLowerCase());
										reData.getResultmap().put(param.getParameterName(), getResultSet(res, true));
										
										res.close();
										res = null;
									}
							}catch(Exception e){
								throw new ServiceException(e);
							}finally{
								if(res!=null) try{res.close();}catch(Exception ex){;}
							}
						}
					}
				}
				
				return count;
			}else{
				//没有参数的调用
				return call.executeUpdate();
			}
		}catch(Exception ex){
			throw new ServiceException(ex);
		}finally{
			if(call!=null) try{call.close();}catch(Exception ex){;}
		}
	}
	
	static public Object getParamValue(Object value,ParameterValueType type){
		if(value==null)
			return null;
		
		if(type==ParameterValueType.STRING)
			return value.toString();
		
		if(type==ParameterValueType.NUMBER){
			if(value.toString().indexOf(".")>0){
				return new Double((String)value);
			}else{
				return new Long((String)value);
			}
		}
		
		if(type==ParameterValueType.DATE){
			return DateConverter.convertDate(value);
		}
		
		throw new ServiceException("参数类型不正确");
	}
	
	static public List<Serializable> getResultSet(ResultSet res, boolean existsClbBob) throws Exception{
		java.sql.ResultSetMetaData meta = res.getMetaData();
		
		List<Serializable> list = new ArrayList<Serializable>();
		int maxcount = 0;
		while(res.next()){
			maxcount ++;
			if(maxcount>1000)
				throw new ServiceException("返回数据集太大，不允许超过1000行！");
			
			HashMap<String,String> data = new HashMap<String,String>();
			for(int i=0;i<meta.getColumnCount();i++){
				int index = i + 1;
				
				Object obj = null;
				if(meta.getColumnType(index)==java.sql.Types.DATE || meta.getColumnType(index)==java.sql.Types.TIMESTAMP){
					obj = DateHelper.formateDate(res.getTimestamp(index),DateHelper.PRINT_DATETIME_STYLE2);
				}else if(meta.getColumnType(index)==java.sql.Types.CLOB){
					Reader reader = res.getCharacterStream(index);
					if(reader==null){
						obj = null;
					}else{
						CharArrayWriter out = new CharArrayWriter();
						char[] buff = new char[1024];
						int len = 0;
						try{
							while( (len=reader.read(buff))!=-1 ){
								out.write(buff, 0, len);
							}
							
							obj = new String(out.toCharArray());
						}catch(Exception ex){
							throw new ServiceException("读取CLOB异常:" + ex.getMessage());
						}finally{
							reader.close();
						}
					}
				}else{
					obj = res.getObject(index);
				}
				
				String colname = meta.getColumnName(index).toLowerCase();
				if(obj!=null){
					data.put(colname, obj.toString());
				}
			}
			list.add(data);
		}
		return list;
	}
}
