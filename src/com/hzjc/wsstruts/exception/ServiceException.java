package com.hzjc.wsstruts.exception;

/**
 * 业务逻辑层异常类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class ServiceException
    extends BaseException {
  public ServiceException() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param message
   * @param cause
   */
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  /**
   *
   * @param iErrCode
   * @param e
   */
  public ServiceException(int iErrCode, Throwable e) {
	//super(iErrCode, e);
    super(iErrCode, e.getCause());
  }

  /**
   *
   * @param iErrCode
   * @param s
   * @param e
   */
  public ServiceException(int iErrCode, String s, Throwable e) {
    super(iErrCode, s, e);
  }

    public ServiceException(String strMsg) {
        super(strMsg);
    }

    public ServiceException(int iErrCode) {
        super(iErrCode);
    }
}