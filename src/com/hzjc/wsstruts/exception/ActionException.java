/* Generated by Together */

package com.hzjc.wsstruts.exception;

/**
 * 动作层异常类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class ActionException
    extends BaseException {

  /**
   *
   */
  public ActionException() {
    super();
  }

  /**
   *
   * @param s
   * @param e
   */
  public ActionException(String s, Throwable e) {
    super(s, e);
  }

  /**
   *
   * @param iErrCode
   * @param e
   */
  public ActionException(int iErrCode, Throwable e) {
    super(iErrCode, e);
  }

  /**
   *
   * @param iErrCode
   * @param s
   * @param e
   */
  public ActionException(int iErrCode, String s, Throwable e) {
    super(iErrCode, s, e);
  }

  public ActionException(String strMsg) {
    super(strMsg);
  }

  public ActionException(int iErrCode) {
    super(iErrCode);
  }
}
