package com.hzjc.hz2004.base.proc;

/**
 * 存储过程输入参数
 * @author ting_it
 *
 */
public class InCallParameter implements CallParameter{
	private int parameterIndex;
	private ParameterValueType valueType;
	private String parameterName;
	private Object parameterValue;
	
	public ParameterType getParameterType() {
		return ParameterType.IN;
	}
	
	public int getParameterIndex() {
		return parameterIndex;
	}
	public void setParameterIndex(int parameterIndex) {
		this.parameterIndex = parameterIndex;
	}
	public ParameterValueType getValueType() {
		return valueType;
	}
	public void setValueType(ParameterValueType valueType) {
		this.valueType = valueType;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Object getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
}
