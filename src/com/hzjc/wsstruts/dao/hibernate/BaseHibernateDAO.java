package com.hzjc.wsstruts.dao.hibernate;

import java.util.*;
import java.sql.*;
import java.io.Serializable;
import org.apache.commons.logging.*;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.LockMode;
import org.hibernate.MappingException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PersistentObjectException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.TransientObjectException;
import org.hibernate.WrongClassException;
import org.hibernate.type.Type;

import com.hzjc.wsstruts.dao.AbstractDAO;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.common.IDGenerator;
import com.hzjc.wsstruts.common.db.DbUtils;

/**
 * Hibernate O/R Mapping框架实现数据库的持久化的简单封装
 * 事务Transaction从该层(DAO)剥离出来；将事务移到业务逻辑层。
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */

public class BaseHibernateDAO
    extends AbstractDAO {
  //日志处理
  protected static Log _log = LogFactory.getLog(BaseHibernateDAO.class);

  /**
   * 转换Hibernate访问数据库的异常为自定义的数据访问异常,简单分类封装
   * Convert the given HibernateException to an appropriate exception from
   * the org.springframework.dao hierarchy.
   * @param ex HibernateException that occured
   * @return the corresponding DataAccessException instance
   */
  protected static DAOException convertHibernateAccessException(
      HibernateException
      ex) {
    //PO中没有*.hbm.xml配置的属性
    if (ex instanceof PropertyNotFoundException) {
      throw new DAOException(WSErrCode.ERR_DB_PROPERTYNOTFOUNDEX, ex);
    }
    //*.hbm.xml资源文件找不到或该配置文件中的配置错误
     //异常原因：session.delete(entity) - update(entity)传入的参数不是配置文件中的PO
     if (ex instanceof MappingException) {
       throw new DAOException(WSErrCode.ERR_DB_MAPPINGEX, ex);
     }
    //异常原因：HQL语句中的字段在数据库中没有抛出
    if (ex instanceof JDBCException) {
      // SQLException during Hibernate access

      //增加于闵红斌(2004/10/10)_begin
      try {
        StringBuffer strError = new StringBuffer(512);
        JDBCException myjdbcex = (JDBCException) ex;
        SQLException mysqlex = myjdbcex.getSQLException();
        if (mysqlex != null) {
          strError.append("数据库操作错误，")
              .append(mysqlex.getMessage());
        }

        return new DAOException(strError.toString(), ex);
      }
      catch (Exception myex) {
        return new DAOException(WSErrCode.ERR_DB_JDBCEX, ex);
      }
      //增加于闵红斌(2004/10/10)_end

      //return new DAOException(WSErrCode.ERR_DB_JDBCEX, ex); //注释(2004/10/10于闵红斌)
    }
    //产生异常原因：数据库中找不到要查询的记录
    if (ex instanceof ObjectNotFoundException) {
      return new DAOException(WSErrCode.ERR_DB_OBJECTNOTFOUNDEX, ex);
    }
    if (ex instanceof WrongClassException) {
      return new DAOException(WSErrCode.ERR_DB_WRONGCLASSEX, ex);
    }
    if (ex instanceof StaleObjectStateException) {
      return new DAOException(WSErrCode.ERR_DB_STALEOBJECTSTATEEX, ex);
    }
    //异常原因：HQL语句不规范、不标准抛出异常
    if (ex instanceof QueryException) {
      return new DAOException(WSErrCode.ERR_DB_QUERYEX, ex);
    }
    if (ex instanceof PersistentObjectException) {
      return new DAOException(WSErrCode.ERR_DB_PERSISTENTOBJECTEX, ex);
    }
    if (ex instanceof TransientObjectException) {
      return new DAOException(WSErrCode.ERR_DB_TRANSIENTOBJECTEX, ex);
    }
    if (ex instanceof ObjectDeletedException) {
      return new DAOException(WSErrCode.ERR_DB_OBJECTDELETEDEX, ex);
    }
    // fallback
    //数据库的其他没有细分的异常
    return new DAOException(WSErrCode.ERR_DB_HIBERNATEEX, ex);
  }

  /**
   * SQL: select SID_HJHIS_LSBGSPZB.nextval from dual;
   * # create squence num increment by 1 start with 1;
   * # insert into table values( num.nextval,xxx)
   * @param entity
   * @return
   * @throws DAOException
   */
  public Serializable getId() throws DAOException {
    Serializable ser = null;
    try {
      ser = IDGenerator.getInstance().getId(getEntityType().getName());
    }
    catch (DAOException ex) {
      throw ex;
    }
    return ser;
  }

  /**
   * 增加、插入一实体
   * @param entity：保存的持久化实体PO
   * @return
   * @throws DAOException：
   *   在此捕捉
   *   (1)Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException
   *   (2)其他异常HibernateException
   *
   */
  public Object insertEntity(Object entity) throws DAOException {
    try {
    	super.create(entity);
    }
    //得到Session时，可能抛出异常
    catch (DAOException ex) {
      //得到当前CurrentSession异常，直接抛出
      throw ex;
    }
    //异常原因：Session.save()中传入的entity不是配置文件中的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //最后进行资源Session释放
    finally {
      //closeSession(_session);
    }
    //该PO应该重新refresh一下取出来
    return entity;
  }

  /**
   * 根据对象的主键ID，查找返回该对象
   * @param id：查找的实体主键ID
   * @return：返回找到的持久化对象
   * @throws DAOException
   * @throws ResultNotFoundException
   *    在此应捕捉：
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException或JDBCException
   *    （2）ObjectNotFoundException对象找不到
   */
  public Object findEntityById(Serializable id) throws DAOException {
    Object obj = null;
    if (id == null) {
      return obj;
    }
    try {
    	return super.get(_entityType, id);
    }
    //捕捉返回当前getCurrentSeesion异常
    catch (DAOException ex) {
      throw ex;
    }
    //没有记录返回，这种异常进行特殊处理（）
    catch (ObjectNotFoundException ex) {
      //throw new ResultNotFoundException(WSErrCode.ERR_DB_OBJECTDELETEDEX,
      //"没有记录结果返回。", ex);
      //edit 2004-04-23 kgb
      return null;
    }
    //产生异常的主要原因：数据库中缺少PO配置文件中的属性定义的字段。
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //最后释放Session连接资源。
    finally {
      //closeSession(_session);
    }
  }

  /**
   * 更新实体对象
   * @param entity
   * @return
   * @throws DAOException
   *    在此应捕捉：
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException
   */
  public Object updateEntity(Object entity) throws DAOException {
    //Transaction _tx = null;
    if (entity == null) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX, "传入要更新的实体为空！", null);
    }
    try {
    	super.update(entity);
    }
    //异常原因：getCurrentSession抛出的异常，直接抛出
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：session.update()中传入的参数entity不是数据库中配置的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    finally {
      //closeSession(_session);
    }
    return entity;
  }

  /**
   * 删除实体对象
   * @param entity
   * @throws DAOException
   *    在此应捕捉：
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException或JDBCException
   */
  public void deleteEntity(Object entity) throws DAOException {
    if (entity == null) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX, "传入要删除的实体为空！", null);
    }
    try {
    	super.delete(entity);
    }
    //异常原因：ThreadLocalSession.currentSession()抛出的异常，直接捕捉
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：session.delete(entity)传入的参数不是配置文件中的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //
    finally {
      //closeSession(_session);
    }
  }

  /**
   *
   * @param strHQL
   * @return
   * @throws DAOException
   */
  public int deleteEntities(String strHQL) throws DAOException {
    try {
     // int i = _session.delete(strHQL);
      //_session.flush();
    	throw new ServiceException("请调试编写代码");
    }
    //异常原因：ThreadLocalSession.currentSession()抛出的异常，直接捕捉
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：session.delete(entity)传入的参数不是配置文件中的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //
    finally {
      //closeSession(_session);
    }
  }

  /**
   *
   * @param strHQL
   * @param objs
   * @param types
   * @return
   * @throws DAOException
   */
  public int deleteEntities(String strHQL, Object[] objs, Type[] types) throws
      DAOException {
    Session _session = null;
    //Transaction _tx = null;
    try {
     // _session = currentSession();
      //_tx = _session.beginTransaction();
      //int i = _session.delete(strHQL, objs, types);
      //_session.flush();
      //_tx.commit();
     // return i;
    	throw new ServiceException("请调试编写代码");
    }
    //异常原因：ThreadLocalSession.currentSession()抛出的异常，直接捕捉
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：session.delete(entity)传入的参数不是配置文件中的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //
    finally {
      //closeSession(_session);
    }
  }

  /**
   * 删除所有实体对象
   * @param clazz
   * @throws DAOException
   *    在此应捕捉：
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException
   */
  public void deleteAllEntities(Class clazz) throws DAOException {
    Session _session = null;
    //Transaction _tx = null;
    if (clazz == null) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "传入要删除的的实体类型Class为空！", null);
    }
    try {
    	super.deleteAll(super.getObjectListByHql("FROM " + clazz.getName()));
      //_tx = _session.beginTransaction();
      //_session.delete("FROM " + clazz.getName());
      //_session.flush();
      //_tx.commit();
    }
    //异常原因：ThreadLocalSession.currentSession()抛出的异常
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：session.delete(entity)传入的参数不是配置文件中的PO
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    finally {
      //closeSession(_session);
    }
  }

  /**
   * 根据实体Class，查找所有实体对象
   * @param clazz
   * @return
   * @throws DAOException
   * @throws ResultNotFoundException
   */
  public List findAllEntities(Class clazz) throws DAOException {
    if (clazz == null) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX,
                             "传入要删除的的实体类型Class为空！", null);
    }
    try {
      return findAllEntities("FROM " + clazz.getName());
    }
    //异常原因：调用findallEntities(strHQL)抛出的异常
    catch (DAOException ex) {
      throw ex;
    }
  }

  /**
   * 根据HQL查找所有实体对象
   * @param strHQL
   * @return:<code>null</code>  if the po could not be found
   * @throws DAOException
   * @throws ResultNotFoundException
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException
   *    （2）数据库访问异常JDBCException和QueryException
   */
  public List findAllEntities(String strHQL) throws DAOException {
    Session _session = null;
    //Transaction _tx = null;
    List lst = null;
    if (strHQL == null) {
      throw new DAOException(WSErrCode.ERR_DB_HIBERNATEEX, "传入查询语句HQL有误！", null);
    }
    try {
 
      //_tx = _session.beginTransaction();
      lst = super.getObjectListByHql(strHQL);
      //_tx.commit();
      //返回记录为空,抛出异常
      /*
       edit kgb 2004-04-23
             if (lst == null || lst.isEmpty() || lst.size() == 0) {
        throw new ResultNotFoundException(WSErrCode.ERR_DB_OBJECTNOTFOUNDEX,
                                          "没有记录结果返回。", null);
             }
       */
    }
    //异常原因：ThreadLocalSession.currentSession抛出的异常
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：HQL语句中的字段在数据库中没有抛出
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //
    finally {
      //closeSession(_session);
    }
    return lst;
  }

  public  List findEntities(String strSQL) throws
            DAOException, ResultNotFoundException{
   
      //Transaction _tx = null;
      try {
        //_tx = _session.beginTransaction();
        List lst = null;
        lst = super.executeSqlQuery(strSQL, new Object[]{});
        		//_session.find(strSQL);
        //_tx.commit();
        //返回记录为空,抛出异常
        /*
         edit 2004-04-23 kgb
               if (lst == null || lst.isEmpty() || lst.size() == 0) {
          throw new ResultNotFoundException(WSErrCode.ERR_DB_OBJECTNOTFOUNDEX,
                                            "没有记录结果返回。", null);
               }
         */
        return lst;
      }
      //异常原因：ThreadLocalSession.currentSession抛出的异常
      catch (DAOException ex) {
        throw ex;
      }
      //异常原因：HQL语句中的字段在数据库中没有抛出
      catch (HibernateException ex) {
        //rollback(_tx);
        throw convertHibernateAccessException(ex);
      }
      //
      finally {
        //closeSession(_session);
      }
  }

  /**
   * 根据参数条件查询
   * @param strHQLWithParam
   * @param values
   * @param types
   * @return
   * @throws DAOException
   * @throws ResultNotFoundException
   */
  public List findAllEntities(String strHQLWithParam, Object[] values,
                              Type[] types) throws DAOException {
    Session _session = null;
    //Transaction _tx = null;
    try {
      //_tx = _session.beginTransaction();
      //List lst = null;
     // lst = _session.find(strHQLWithParam, values, types);
      //_session.flush();
      //_tx.commit();
      //返回记录为空,抛出异常
      /*
       edit 2004-04-23 kgb
             if (lst == null || lst.isEmpty() || lst.size() == 0) {
        throw new ResultNotFoundException(WSErrCode.ERR_DB_OBJECTNOTFOUNDEX,
                                          "没有记录结果返回。", null);
             }
       */
      //return lst;
      throw new ServiceException("请调试编写代码");
    }
    //异常原因：ThreadLocalSession.currentSession抛出的异常
    catch (DAOException ex) {
      throw ex;
    }
    //异常原因：HQL语句中的字段在数据库中没有抛出
    catch (HibernateException ex) {
      //rollback(_tx);
      throw convertHibernateAccessException(ex);
    }
    //
    finally {
      //closeSession(_session);
    }
  }

  /**
   * 分页查询实体，返回符合条件的一页对象
   * @param strHQL
   * @param offset
   * @param pagesize
   * @return
   * @throws DAOException
   * @throws ResultNotFoundException
   *    （1） Hibernate.cfg.xml或*.hbm.xml配置文件异常:PropertyNotFoundException和MappingException
   *    （2） 查询异常QueryException
   */
  public List findOnePageEntities(String strHQL, int offset, int pagesize) throws
      DAOException {
    ///////////////////////////////////////////////////////////////////////
    //分页返回进行判断,如果pagesize小于0则表示返回所有记录
    ///////////////////////////////////////////////////////////////////////
    if (pagesize < 0) {
      List lstAll = findAllEntities(strHQL);
      return lstAll;
    }

    int pageindex = offset/pagesize;
    if(pageindex<=0) pageindex=0;
    
    return super.getPageRecords(strHQL, new Object[]{}, pageindex, pagesize).getList();

  }


  //////////////////////////////////////////////////////////////////////////////
  //以下：重新包装Wrapper所有的Hibernate Session的所有方法
  //////////////////////////////////////////////////////////////////////////////
  /**
   *
   * @param object
   * @param lockMode
   * @throws DAOException
   */
  public void refresh(Object object, org.hibernate.LockMode lockMode) throws DAOException {
    try {
    	//super.getCurrentSession().refresh(object, lockMode);
    	throw new ServiceException("检查代码！");
    }
    catch (HibernateException ex) {
      throw convertHibernateAccessException(ex);
    }
    finally {
      //closeSession(_session);
    }
  }

}
