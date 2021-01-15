package com.hzjc.wsstruts.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.type.Type;

import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ResultNotFoundException;

/**
 * DAO数据库访问接口
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */
public interface WSDAO {

  /**
   *
   * @param entity
   * @return
   * @throws DAOException
   */
  public Serializable getId() throws DAOException;

  /**
   *
   * @param entity
   * @return
   */
  Object insertEntity(Object entity) throws DAOException;

  /**
   *
   * @param entity
   */
  void deleteEntity(Object entity) throws DAOException;

  /**
   *
   * @param entity
   * @return
   */
  Object updateEntity(Object entity) throws DAOException;

  /**
   *
   * @param serializable
   * @return
   */
  Object findEntityById(Serializable serializable) throws DAOException,
      ResultNotFoundException;

  /**
   *
   * @param strSQL
   * @param offset
   * @param pagesize
   * @return
   */
  List findOnePageEntities(String strSQL, int offset, int pagesize) throws
      DAOException, ResultNotFoundException
      ;

  List findEntities(String strSQL) throws
        DAOException, ResultNotFoundException
      ;

  /**
   *
   * @param typeName
   * @throws <{ClassNotFoundException}>
   */
  void setEntityType(String typeName) throws ClassNotFoundException;

  /**
   *
   * @param strHQL
   * @param objs
   * @param types
   * @return
   * @throws DAOException
   */
  int deleteEntities(String strHQL, Object[] objs, Type[] types) throws
      DAOException;

  /**
   *
   * @param strHQL
   * @return
   * @throws DAOException
   */
  int deleteEntities(String strHQL) throws DAOException;


  //////////////////////////////////////////////////////////////////////////////
  //针对Hibernate添加的数据访问层处理的接口
  //////////////////////////////////////////////////////////////////////////////
  /**
   *
   * @param object
   * @param lockMode
   * @throws DAOException
   */
  void refresh(Object object, LockMode lockMode) throws DAOException;
}
