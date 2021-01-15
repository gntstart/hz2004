package com.hzjc.wsstruts.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.*;

/**
 * 框架所有层异常的基础处理类
 * 说明：供框架异常处理组包用
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class BaseException
    extends RuntimeException {
  //日志处理
  protected Log _log = LogFactory.getLog(this.getClass());
  //每层异常的错误代码

  /**
   *
   */
  public BaseException() {
    super();
    //将异常信息设置给错误消息消息属性
    setErrmsg(getMessage());
  }

  /**
   *
   * @param strMsg
   */
  public BaseException(String strMsg) {
    super(strMsg);
    //将异常信息设置给错误消息属性
    setErrmsg(strMsg.concat(getMessage()));
  }

  /**
   * 调用see#BaseException(int iErrCode, Throwable e)
   * @param iErrCode:传入的异常代码
   */
  public BaseException(int iErrCode) {
    this(iErrCode, null);
  }

  /**
   * 调用see#BaseException(int iErrCode, String s, Throwable e)
   * @param iErrCode :传入的异常代码
   * @param e:抛出的异常
   */
  public BaseException(int iErrCode, Throwable e) {
    this(iErrCode, "", e);
  }

  /**
   *see # BaseException(String s, Throwable e)
   * @param iErrCode:异常代码
   * @param s:自定义传入的异常信息
   * @param e:抛出的异常
   */
  public BaseException(int iErrCode, String s, Throwable e) {
    //组合代码对应的异常和自定义传入的异常
    this(WSErrCode.getErrMsg(iErrCode).concat(s), e);
    //设置异常错误代码属性
    setErrcode(iErrCode);
  }

  /**
   * 所有其他构造函数的基础实现类
   * @param s
   * @param e
   */
  public BaseException(String s, Throwable e) {
    //super(s, e); //Since JDK.1.4
    super(s + (e != null ? "\t" + e.getMessage() : ""));
    if (e == null) { //设置异常信息字符串
      setErrmsg(s);
    }
    else {
      this._rootCause = e;
      //setErrmsg(s.concat("；").concat(e.getMessage()));
      //setErrmsg(s.concat("；").concat(e.toString()));
      ///////////////////////////////////////////////////////////////////
      //组合自定义异常和异常的原始错误描述抛出，返回给前台客户端
      ///////////////////////////////////////////////////////////////////
      /*Since JDK1.4
             StackTraceElement[] trace = e.getStackTrace();
             StringBuffer strBuf = new StringBuffer();
             strBuf.append(s).append(";").append(e.toString()).append("\r\n");
             for (int i = 0; i < trace.length; i++) {
        strBuf.append("\tat " + trace[i] + "\r\n");
             }
             setErrmsg(strBuf.toString());
       */

      //修改于闵红斌(2004/10/10))_begin
      //StringWriter sw = new StringWriter(512);
      //PrintWriter pw = new PrintWriter(sw, true);
      //e.printStackTrace(pw);
      String strError = s != null ? s + " " : "";
      if (e instanceof BaseException) {
        strError += strError + ( (BaseException) e).getErrmsg();
      }
      else {
        strError += strError + e.getMessage();
      }

      setErrmsg(strError);
      //修改于闵红斌(2004/10/10))_end
    }
    _log.error(getErrmsg());

    return;
  }

  //////////////////////////////////////////////////////////////////////
  // 自定义的属性，异常内部定义的动作代码，错误代码，异常字符串信息属性
  //////////////////////////////////////////////////////////////////////
  private String actioncode = "F0000";
  private int errcode = 999; //未定义的默认异常代码
  private String errmsg = "未定义的异常处理"; //默认消息字符串
  private Throwable _rootCause;

  public String getActioncode() {
    return actioncode;
  }

  public void setActioncode(String actioncode) {
    this.actioncode = actioncode;
  }

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  /**
   *
   * @return
   */
  public Throwable getRootCause() {
    return _rootCause;
  }
  //////////////////////////////////////////////////////////////////////////////
}
