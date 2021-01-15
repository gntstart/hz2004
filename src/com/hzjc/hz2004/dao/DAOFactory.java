package com.hzjc.hz2004.dao;

import com.hzjc.wsstruts.exception.*;

public class DAOFactory {
  private DAOFactory() {
  }

  public static PojoInfo createXT_YHHHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHHHXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJLS_HJYWLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJLS_HJYWLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_BGSPXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_BGSPXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_BGSPZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_BGSPZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HBBGSPSQBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HBBGSPSQB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJBLSPSQBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJBLSPSQB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJSCSPSQBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJSCSPSQB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJSPFDCLBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJSPFDCLB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJSPLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJSPLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJSPSQBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJSPSQB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_HJSPZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_HJSPZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_QYZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_QYZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJSP_ZQZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJSP_ZQZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJTJ_HBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJTJ_HBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJTJ_RYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJTJ_RYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_CZRKJBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_HGLGXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_HGLGXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_HXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_HXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_MLPXXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_MLPXXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_RHFLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_RHFLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_RYZPXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_RYZPXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_ZPLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_ZPLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXZ_BGDYZDKZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXZ_BGDYZDKZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }


  public static PojoInfo createHJXZ_GBRYXZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXZ_GBRYXZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXZ_MLPXXDJBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXZ_MLPXXDJB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXZ_YWBLXZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXZ_YWBLXZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_BGGZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_BGGZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_CHCLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_CHCLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_CSDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_CSDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_GMSFHMSXMFPXXBDAO() throws
      DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_GMSFHMSXMFPXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_HBBGXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_HBBGXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_HCYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_HCYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_HFHKXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_HFHKXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_HJBLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_HJBLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_HJSCXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_HJSCXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_QCCLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_QCCLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_QCZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_QCZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_QRDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_QRDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_SWZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_SWZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_ZXHKXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_ZXHKXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJYW_ZZBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJYW_ZZBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_BGGZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_BGGZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_CHCLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_CHCLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_CSDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_CSDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_GMSFHMSXMFPXXBDAO() throws
      DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_GMSFHMSXMFPXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HBBGXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HBBGXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HCYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HCYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HJBLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HJBLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_HJSCXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_HJSCXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_QCCLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_QCCLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_QCZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_QCZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_QRDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_QRDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_RDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_RDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_RHDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_RHDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_SWZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_SWZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_HJ_ZZBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJ_ZZBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_IN_CHRYBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_IN_CHRYB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_XT_YHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_XT_YHXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_GSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_GSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_JMSFZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_JMSFZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_LQFFXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_LQFFXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_SHQFXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_SHQFXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_SJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_SJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_XHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_XHXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_YDZSLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_YDZSLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_YSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_YSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createV_ZJ_ZZHKXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_ZJ_ZZHKXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_BSSQBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_BSSQB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_CZBDBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_CZBDB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_DWXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_DWXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_HHXLBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_HHXLB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JLXJWHDZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JLXJWHDZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JLXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JLXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JSCDQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JSCDQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JSGNQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JSGNQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JSYWBBQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JSYWBBQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JSZSBBQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JSZSBBQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JWHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JWHXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_JWZRQXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_JWZRQXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_LNWSDBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_LNWSDB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_MBLCXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_MBLCXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_PYFHBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_PYFHB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_QYSZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_QYSZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_RYLJJYCWXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_RYLJJYCWXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SJBBHBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SJBBHB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SJPZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SJPZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SJZDBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SJZDB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SLHXLBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SLHXLB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SPDZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SPDZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_SPMBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_SPMBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XTCSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XTCSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XTGNBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XTGNB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XTGNCDBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XTGNCDB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XTKZCSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XTKZCSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XTRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XTRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XXBGLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XXBGLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XZJDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XZJDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_XZQHBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_XZQHB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHDTQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHDTQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHDZQXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHDZQXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHIPYXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHIPYXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHJSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHJSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHSJFWBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHSJFWB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YHXXB");
    }
    catch (Exception ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YWBBMBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YWBBMBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_YWQXKZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_YWQXKZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_ZSBBMBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_ZSBBMBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createXT_ZSBBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoXT_ZSBBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJLS_SFZYWCZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJLS_SFZYWCZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJSH_SHQFXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJSH_SHQFXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJXX_JMSFZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJXX_JMSFZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_GSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_GSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_LQFFXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_LQFFXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_SJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_SJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_SLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_SLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_XHXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_XHXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_YDZSLBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_YDZSLB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_YSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_YSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createZJYW_ZZHKXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_ZZHKXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_PZRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_PZRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  public static PojoInfo createHJXX_SMKZPBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_SMKZPB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  //地址追加
  public static PojoInfo createDZZJ_CZYXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoDZZJ_CZYXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  public static PojoInfo createDZZJ_SBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoDZZJ_SBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  public static PojoInfo createDZZJ_CZRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoDZZJ_CZRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  public static PojoInfo createDZZJ_DZZJRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoDZZJ_DZZJRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  /**
   * 变动修改日志表
   * @return
   * @throws DAOException
   */
  public static PojoInfo createHJTJ_BDXGRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJTJ_BDXGRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  /**
   * 临时身份证受理表
   * @return
   * @throws DAOException
   */
  public static PojoInfo createLSSFZ_SLXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoLSSFZ_SLXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  /**
   * 临时身份证受理历史表
   * @return
   * @throws DAOException
   */
  public static PojoInfo createLSSFZ_SLLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoLSSFZ_SLLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 临时身份证打印信息表
   * @return
   * @throws DAOException
   */
  public static PojoInfo createLSSFZ_DYXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoLSSFZ_DYXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 区划调整户号控制表
   * @throws DAOException
   * @return QHTZHHKZBDAO
   */
  public static PojoInfo createQHTZHHKZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoQHTZHHKZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 户籍信息打印信息表
   * @throws DAOException
   * @return HJXX_DYXXBDAO
   */
  public static PojoInfo createHJXX_DYXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_DYXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 上报居民身份证信息表
   * @throws DAOException
   * @return SB_JMSFZXXBDAO
   */
  public static PojoInfo createSB_JMSFZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoSB_JMSFZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史人员变动信息表（四变）
   * @throws DAOException
   * @return OLD_HJTJ_RYBDXXBDAO
   */
  public static PojoInfo createOLD_HJTJ_RYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJTJ_RYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史人员变动信息表（四变）
   * @throws DAOException
   * @return OLD_HJXX_CZRKJBXXBDAO
   */
  public static PojoInfo createOLD_HJXX_CZRKJBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJXX_CZRKJBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史变更更正信息表
   * @throws DAOException
   * @return OLD_HJYW_BGGZXXBDAO
   */
  public static PojoInfo createOLD_HJYW_BGGZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_BGGZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 证件发放接收信息表
   * @throws DAOException
   * @return ZJYW_FFJSXXBDAO
   */
  public static PojoInfo createZJYW_FFJSXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZJYW_FFJSXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史迁入登记信息表
   * @throws DAOException
   * @return OLD_HJYW_QRDJXXBDAO
   */
  public static PojoInfo createOLD_HJYW_QRDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_QRDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史出生登记信息表
   * @throws DAOException
   * @return OLD_HJYW_CSDJXXBDAO
   */
  public static PojoInfo createOLD_HJYW_CSDJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_CSDJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史迁出注销信息表
   * @throws DAOException
   * @return OLD_HJYW_QCZXXXBDAO
   */
  public static PojoInfo createOLD_HJYW_QCZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_QCZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史死亡注销信息表
   * @throws DAOException
   * @return OLD_HJYW_SWZXXXBDAO
   */
  public static PojoInfo createOLD_HJYW_SWZXXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_SWZXXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史住址变动信息表
   * @throws DAOException
   * @return OLD_HJYW_ZZBDXXBDAO
   */
  public static PojoInfo createOLD_HJYW_ZZBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_ZZBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 历史户成员变动信息表
   * @throws DAOException
   * @return OLD_HJYW_HCYBDXXBDAO
   */
  public static PojoInfo createOLD_HJYW_HCYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoOLD_HJYW_HCYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 异地证件信息表
   * @throws DAOException
   * @return YDZJ_SBXXBDAO
   */
  public static PojoInfo createYDZJ_SBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoYDZJ_SBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 异地证件视读照片
   * @throws DAOException
   * @return YDZJ_SDZPDAO
   */
  public static PojoInfo createYDZJ_SDZPDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoYDZJ_SDZP");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 异地证件视读照片
   * @throws DAOException
   * @return YDZJ_DBLKZDAO
   */
  public static PojoInfo createYDZJ_DBLKZDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoYDZJ_DBLKZ");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 所有的变更更正信息表
   * @throws DAOException
   * @return V_HJYW_BGGZXXBDAO
   */
  public static PojoInfo createV_HJYW_BGGZXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJYW_BGGZXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 所有的四变信息表
   * @throws DAOException
   * @return V_HJTJ_RYBDXXBDAO
   */
  public static PojoInfo createV_HJTJ_RYBDXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJTJ_RYBDXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 所有的人员信息表
   * @throws DAOException
   * @return V_HJTJ_RYBDXXBDAO
   */
  public static PojoInfo createV_HJXX_CZRKJBXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HJXX_CZRKJBXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 杭州市民卡 写卡日志表
   * @throws DAOException
   * @return HZSMK_XKRZDAO
   */
  public static PojoInfo createHZSMK_XKRZDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHZSMK_XKRZ");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 杭州市民卡 读卡日志表
   * @throws DAOException
   * @return HZSMK_XKRZDAO
   */
  public static PojoInfo createHZSMK_DKRZDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHZSMK_DKRZ");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 杭州市民卡 黑名单视图
   * @throws DAOException
   * @return V_HZSMK_HMD
   */
  public static PojoInfo createV_HZSMK_HMDDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoV_HZSMK_HMD");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 杭州市民卡 修改日志表
   * @throws DAOException
   * @return HZSMK_XGRZ
   */
  public static PojoInfo createHZSMK_XGRZDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoHZSMK_XGRZ");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 住址追加日志表
   * @throws DAOException
   * @return ZZZJ_ZZZJRZBDAO
   */
  public static PojoInfo createZZZJ_ZZZJRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoZZZJ_ZZZJRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 绍兴绿色通道身份证表
   * @throws DAOException
   * @return WW_BZLSTDRYXXDAO
   */
  public static PojoInfo createWW_BZLSTDRYXXDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoWW_BZLSTDRYXX");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 查询结果保存日志表
   * @throws DAOException
   * @return TJ_CXJGBCRZBDAO
   */
  public static PojoInfo createTJ_CXJGBCRZBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoTJ_CXJGBCRZB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 评价信息表
   * @throws DAOException
   * @return WW_PJXXBDAO
   */
  public static PojoInfo createWW_PJXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoWW_PJXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }

  /**
   * 评价信息业务流水表
   * @throws DAOException
   * @return WW_PJXXYWLSBDAO
   */
  public static PojoInfo createWW_PJXXYWLSBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoWW_PJXXYWLSB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
  /**
   * 消息信息表
 * @return
 * @throws DAOException
 */
public static PojoInfo createMESSAGEXXBDAO() throws DAOException {
	  PojoInfo dao = null;
    try {
      dao = new PojoInfo();
      dao.setEntityType("com.hzjc.hz2004.po.PoMESSAGEXXB");
    }
    catch (ClassNotFoundException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
    }
    return dao;
  }
public static PojoInfo createSSXQQRSFFBBDAO() throws DAOException {
	  PojoInfo dao = null;
  try {
    dao = new PojoInfo();
    dao.setEntityType("com.hzjc.hz2004.po.PoSSXQQRSFFBB");
  }
  catch (ClassNotFoundException ex) {
    throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                           "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
  }
  return dao;
}

public static PojoInfo createHZ_JS_BSDAO() {

	PojoInfo dao = null;
	try {
	  dao = new PojoInfo();
	  dao.setEntityType("com.hzjc.hz2004.po.PoHZ_ZJ_SB");
	}
	catch (ClassNotFoundException ex) {
	  throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
	                         "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
	}
	return dao;

}
public static PojoInfo createJSSDLOGXXBDAO() {

	PojoInfo dao = null;
	try {
	  dao = new PojoInfo();
	  dao.setEntityType("com.hzjc.hz2004.po.PoJSSDLOGXXB");
	}
	catch (ClassNotFoundException ex) {
	  throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
	                         "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
	}
	return dao;

}
public static PojoInfo createJTTDLSBDAO() throws DAOException {
	  PojoInfo dao = null;
  try {
    dao = new PojoInfo();
    dao.setEntityType("com.hzjc.hz2004.po.PoJTTDLSB");
  }
  catch (ClassNotFoundException ex) {
    throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                           "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
  }
  return dao;
}
public static PojoInfo createHJXX_JWHZPLSBDAO() throws DAOException {
	  PojoInfo dao = null;
  try {
    dao = new PojoInfo();
    dao.setEntityType("com.hzjc.hz2004.po.PoHJXX_JWHZPLSB");
  }
  catch (ClassNotFoundException ex) {
    throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                           "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
  }
  return dao;
}

public static PojoInfo createSFJFFJBDAO() throws DAOException {
	  PojoInfo dao = null;
try {
  dao = new PojoInfo();
  dao.setEntityType("com.hzjc.hz2004.po.PoSFJFFJB");
}
catch (ClassNotFoundException ex) {
  throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                         "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
}
return dao;
}

public static PojoInfo createUPLOAD_TEMPDAO() throws DAOException {
	  PojoInfo dao = null;
try {
  dao = new PojoInfo();
  dao.setEntityType("com.hzjc.hz2004.po.PoUPLOAD_TEMP");
}
catch (ClassNotFoundException ex) {
  throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                         "创建数据访问对象（DAOFactory.createDAO）异常！", ex);
}
return dao;
}
}
