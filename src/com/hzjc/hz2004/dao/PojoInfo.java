package com.hzjc.hz2004.dao;

import java.io.Serializable;

import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.wsstruts.common.IDGenerator;
import com.hzjc.wsstruts.exception.DAOException;

public class PojoInfo {
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
	  public Class getEntityType() {
	    return _entityType;
	  }
	  
	  public Serializable getId() throws DAOException {
		  return SpringContextHolder.getCommonService().getId(_entityType);
	  }
}
