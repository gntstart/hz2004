package com.hzjc.wsstruts.dao;

import java.util.List;
import java.io.Serializable;

import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.wsstruts.exception.*;

/**
 * DAO接口的抽象实现
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */

abstract public class AbstractDAO extends ServiceImpl
    implements WSDAO {
  /**
   * 实体对象的类型Class
   */
  protected Class _entityType;

  /**
   *
   * @param typeName
   * @throws java.lang.ClassNotFoundException
   */
  public void setEntityType(String typeName) throws ClassNotFoundException {
    Class clazz = getClass().getClassLoader().loadClass(typeName);
    _entityType = clazz;
  }

  /**
   *
   * @return
   */
  protected Class getEntityType() {
    return _entityType;
  }

  /////////////////////////////////////////////////////////////////////////////
  //抽象实现
  /////////////////////////////////////////////////////////////////////////////

  public abstract Serializable getId() throws DAOException;

  public abstract Object insertEntity(Object entity) throws DAOException;

  public abstract Object updateEntity(Object entity) throws DAOException;

  public abstract void deleteEntity(Object entity) throws DAOException;

  public abstract Object findEntityById(Serializable serializable) throws
      DAOException, ResultNotFoundException
      ;

  public abstract List findEntities(String strSQL) throws
          DAOException, ResultNotFoundException
      ;

  public abstract List findOnePageEntities(String strSQL, int offset,
                                           int pagesize) throws DAOException,
      ResultNotFoundException;


}
