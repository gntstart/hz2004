package com.hzjc.wsstruts.exception;

/**
 * 未知的异常类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class UnknownException
    extends BaseException {
  /**
   *
   */
  public UnknownException() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param s
   * @param e
   */
  public UnknownException(String s, Throwable e) {
    super(s, e);
    // TODO Auto-generated constructor stub
  }

  /**
   *
   * @param iErrCode
   * @param e
   */
  public UnknownException(int iErrCode, Throwable e) {
    super(iErrCode, e);
  }

  /**
   *
   * @param iErrCode
   * @param s
   * @param e
   */
  public UnknownException(int iErrCode, String s, Throwable e) {
    super(iErrCode, s, e);
  }

    public UnknownException(String strMsg) {
        super(strMsg);
    }

    public UnknownException(int iErrCode) {
        super(iErrCode);
    }
}