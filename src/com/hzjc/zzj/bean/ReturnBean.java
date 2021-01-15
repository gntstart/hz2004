package com.hzjc.zzj.bean;

/**
 * 指示调用是否成功的结果BEAN
 * @author ting_it
 *
 */
public class ReturnBean  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean success;		//true/false之一
	private String message;		    //如果success=false,那么这里返回错误内容
	private String postid;			//用户提交记录的postid，用户和提交一一对应
	private String lkbm;			//如果成功，那么返回入住流水号，必须回填此流水号，退费必须使用此凭证
	
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
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getLkbm() {
		return lkbm;
	}
	public void setLkbm(String lkbm) {
		this.lkbm = lkbm;
	}
}
