package com.hzjc.wsstruts.exception;

/**
 * 数据访问层异常类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */

public class DAOException
    extends BaseException {
  /**
   *
   */
  public DAOException() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param message
   * @param cause
   */
  public DAOException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  /**
   *
   * @param iErrCode
   * @param e
   */
  public DAOException(int iErrCode, Throwable e) {
    super(iErrCode, e);
  }

  /**
   *
   * @param iErrCode
   * @param s
   * @param e
   */
  public DAOException(int iErrCode, String s, Throwable e) {
    super(iErrCode, s, e);
  }

    public DAOException(String strMsg) {
        super(strMsg);
    }

    public DAOException(int iErrCode) {
        super(iErrCode);
    }
}