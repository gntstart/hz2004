package com.hzjc.wsstruts.exception;

/**
 * 事务异常类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class TxException
    extends BaseException {

  /**
   *
   */
  public TxException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public TxException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   *
   * @param iErrCode
   * @param e
   */
  public TxException(int iErrCode, Throwable e) {
    super(iErrCode, e);
  }

  /**
   *
   * @param iErrCode
   * @param s
   * @param e
   */
  public TxException(int iErrCode, String s, Throwable e) {
    super(iErrCode, s, e);
  }

    public TxException(String strMsg) {
        super(strMsg);
    }

    public TxException(int iErrCode) {
        super(iErrCode);
    }
}
