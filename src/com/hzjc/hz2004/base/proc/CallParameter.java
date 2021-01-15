package com.hzjc.hz2004.base.proc;

/**
 * 存储过程参数接口
 * @author ting_it
 *
 */
public interface CallParameter {
	public enum ParameterType { 
	       IN (1), OUT (2); 
	       
	       private int nCode ; 
	       private ParameterType( int _nCode) { 
	           this . nCode = _nCode; 
	       }
	       
	       public String toString() { 
	           return String.valueOf ( this . nCode ); 
	       } 
	 } 

	public enum ParameterValueType { 
			CURSOR (1), STRING (2), NUMBER(3), DATE(4);
	       
	       private int nCode ; 
	       private ParameterValueType( int _nCode) { 
	           this . nCode = _nCode; 
	       }
	   
	       public String toString() { 
	           return String.valueOf ( this . nCode ); 
	       } 
	 } 

	/**
	 * 参数索引号1...n
	 * @return
	 */
	public int getParameterIndex();
	
	/**
	 * 参数类型：in,out
	 * @return
	 */
	public ParameterType getParameterType();	
	
	/**
	 * 存储过程参数数据类型：cursor, string, number, date
	 * @return
	 */
	public ParameterValueType getValueType();
	
	/**
	 * 存储过程参数名称，这个对于OUT参数非常重要，用于存储过程返回的MAP组织
	 * 如果是游标，那么返回List<Map<String,String>>
	 * @return
	 */
	public String getParameterName();
	
	/**
	 * 参数内容，对于IN参数是必须的
	 * @return
	 */
	public Object getParameterValue();
}
