package com.hzjc.hz2004.base;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import com.hzjc.hz2004.appbase.SysParam;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.proc.CallParameter;
import com.hzjc.hz2004.base.proc.CallParameter.ParameterValueType;
import com.hzjc.hz2004.base.proc.CallProc;
import com.hzjc.hz2004.base.proc.InCallParameter;
import com.hzjc.hz2004.base.proc.OutCallParameter;
import com.hzjc.hz2004.base.sqltemplate.Config;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParam;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParse;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoHZ_ZJ_SB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.ExtUtils;
import com.hzjc.hz2004.util.ObjectUtil;
import com.hzjc.hz2004.vo.VoXT_XTKZCSB;
import com.hzjc.util.DBLogUtils;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.common.ID;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;





/**
 * 封装了所有的持久层基本操作类
 * @author Administrator
 *
 */
public abstract class ServiceImplBase {
	public abstract SessionFactory getCurrentSessionFactory();
	
	public Session getCurrentSession() {
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public void setCurrentSessionFactoryRef(String dqbm, SessionFactory factory){
	}
	
	public void setCurrentSessionFactory(String dqbm, String factoryName){
	}

	public void setDqbm(String dqbm) {
	}
	
	public PoXT_YHXXB getUser(){
		return BaseContext.getBaseUser().getUser();
	}
	
	public PoXT_YHXXB getUserInfo(){
		return BaseContext.getBaseUser().getUser();
	}
	public PoPERSON_DY_SET getPersonDySet() {
		return BaseContext.getBaseUser().getPersonDySet();
		
	}
	public PoDW_DY_SET getDwDySet() {
		return BaseContext.getBaseUser().getDwDySet();
		
	}
	public Connection getConnection(){
		try{
			return SessionFactoryUtils.getDataSource(getCurrentSessionFactory()).getConnection();
		}catch(Exception e){
			throw new ServiceException("获取数据库连接失败！",e);
		}
	}
	
	public void executeProcedure(final CallProc proc){	
		Connection conn = null;
		try{
			conn = SessionFactoryUtils.getDataSource(getCurrentSessionFactory()).getConnection();
			CallableStatement call = null;
			if(CommonUtil.isEmpty(proc.getProcname()))
				throw new ServiceException("必须指定存储过程名称！");

			if(proc.getProcname().startsWith("{call"))
				call = conn.prepareCall(proc.getProcname());
			else
				call = conn.prepareCall("{call " + proc.getProcname() + "}");
			
			proc.executeCall(call);
		}catch(Exception ex){
			throw new ServiceException(ex);
		}finally{
			if(conn!=null) try{conn.close();}catch(Exception ex){;}
		}
	}
	
	/*
	public Object load(final String entityName, final Serializable id,
			final LockOptions  lockmode) throws DataAccessException {
		return getCurrentSession().load(entityName, id, lockmode);
	}
	*/
	
	@SuppressWarnings("unchecked")
	public <T>T load(final Class<T> cls, final Serializable id,
			final LockOptions  lockmode) throws DataAccessException {
		//query.setCacheable(false);//开启查询缓存
		return (T)getCurrentSession().load(cls, id, lockmode);
	}
	
	/**
	 * 依据ID获取对象，并且指定锁
	 * @param cls
	 * @param id
	 * @param lockmode
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T>T get(final Class<T> cls, final Serializable id,
			final LockOptions  lockmode) throws DataAccessException {
		//query.setCacheable(false);//开启查询缓存
		return (T)getCurrentSession().get(cls, id, lockmode);
	}
	
	/**
	 * 依据ID获取对象，并添加行锁for update
	 * @param cls
	 * @param id
	 * @param lockmode
	 * @return
	 * @throws DataAccessException
	 */
	public <T>T getLock(final Class<T> cls, final Serializable id) throws DataAccessException {
		//query.setCacheable(false);//开启查询缓存
		return get(cls, id, LockOptions.UPGRADE);
	}
	
	/**
	 * 获取批量行锁
	 * @param queryString
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getObjectListByHqlLock(final String queryString,final Object[] param) throws DataAccessException {
		DBLogUtils.createLog(queryString, param);
		
		 Query query=getCurrentSession().createQuery(queryString);
		 query.setLockOptions(LockOptions.UPGRADE);
		 
		 //如果设置缓存，那么怎么群集？
		// query.setCacheable(true);//开启查询缓存
		 
		 if(param != null && param.length > 0) {
				for(int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
		}
		return query.list();
	}
	
	  public void refresh(Object obj, LockOptions lockMode) throws DAOException {
		  if(obj  != null){
				Session s = getCurrentSession();
			     s.refresh(obj, lockMode);
			}
	  }
	
	public void delete(final Object obj) throws DataAccessException {
		if(obj  != null){
			Session s = getCurrentSession();
			s.delete(obj);
			s.flush();
			
			DBLogUtils.deleteObjectLog(obj);
			//delete 只是把对象转换为准备删除的状态，要提交以后再删除， 同一个对象，先delete，在save 对象状态 准备删除 －> 托管状态
			//this.getCurrentSession().flush();
		}
	}
	
	public long getCount(final String hql) {
		List<?> list=this.getObjectListByHql(hql,new Object[]{});
		if(CommonUtil.isNotEmpty(list))
			return Long.parseLong(list.get(0).toString());
		
		return 0;
	}
	
	public Object getObject(final String hql,final Object[] param) {
		List<?> list=this.getObjectListByHql(hql,
				param);
		if(CommonUtil.isNotEmpty(list))return list.get(0);
		return null;
	}
	
	public List<?> getObjectListByHql(final String querystr)
			throws DataAccessException {
		return this.getObjectListByHql(querystr, null);
	}
	
	public List<?> getObjectListByHql(final String queryString,final Object[] param)
			throws DataAccessException {
		return this.getObjectListByHql(queryString, param, -1, -1);
	}
	
	public List<?> getObjectListByHql(String config_path, String config_key, ExtMap<String,Object> params)
			throws DataAccessException {
		SqlParse sqlParse = new SqlParse(config_path, config_key);
		sqlParse.bind(params);
		SqlParam sqlParam = sqlParse.parse();
		
		return this.getObjectListByHql(sqlParam.getSql(), sqlParam.getParamsArrays());
	}
	
	public List<?> findEntities(final String querystr)
			throws DataAccessException {
		return this.getObjectListByHql(querystr, null);
	}
	
	public List<?> findEntities(final String queryString,final Object[] param)
			throws DataAccessException {
		return this.getObjectListByHql(queryString, param, -1, -1);
	}
	
	public List<?> findAllByHQL(final String querystr)
			throws DataAccessException {
		return this.getObjectListByHql(querystr, null);
	}
	
	public List<?> findAllByHQL(final String queryString,final Object[] param)
			throws DataAccessException {
		return this.getObjectListByHql(queryString, param, -1, -1);
	}
	
	/**
	 * 将一个查询返回的Object[] 转换为List<Map<String,String>>输出
	 * @param queryString
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String,String>> getObjectListByHqlObjectsToMap(final String queryString,final Object[] param)
			throws DataAccessException {
		return this.getObjectListByHqlObjectsToMap(queryString, param, -1, -1);
	}
	
	/**
	 * 将一个查询返回的Object[] 转换为List<Map<String,String>>输出
	 * @param queryString
	 * @param param
	 * @param pageindex
	 * @param pagesize
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String,String>> getObjectListByHqlObjectsToMap(final String queryString,final Object[] param,
			final int pageindex, final int pagesize) throws DataAccessException {
		List<?> list = this.executeSqlQuery(queryString, param, pageindex, pagesize);
		return ObjectUtil.makeReturnDataBySQLQuery(queryString, list);
	}
	
	public Object create(final Object obj) throws DataAccessException {
		Session s = getCurrentSession();
		s.save(obj);
		s.flush();
		
		DBLogUtils.createOrUpdateObjectLog(obj);
		
		return obj;
	}
	
	public Object update(final Object obj) throws DataAccessException {
		Session s = getCurrentSession();
		s.update(obj);
		s.flush();
		
		DBLogUtils.createOrUpdateObjectLog(obj);
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(final Class<T> entityClass, final Serializable id)
			throws DataAccessException {
		T t = (T) getCurrentSession().get(entityClass, id);
	
		return t;
	}
	
	public void saveOrUpdate(final Object obj) throws DataAccessException {
		Session s = getCurrentSession();
		s.saveOrUpdate(obj);
		s.flush();
		
		DBLogUtils.createOrUpdateObjectLog(obj);
	}
	
	public void clean() {
		getCurrentSession().clear();
	}
	
	public Page getPageRecords(String config_path, String config_key, ExtMap<String,Object> params) throws DataAccessException {
		int pageIndex = params.getInteger(ExtUtils.pageIndex);
		int pageSize = params.getInteger(ExtUtils.pageSize);

		SqlParse sqlParse = new SqlParse(config_path, config_key);
		sqlParse.bind(params);
		SqlParam sqlParam = sqlParse.parse();
		Page p = this.getPageRecords(sqlParam.getSql(), sqlParam.getParams(), pageIndex, pageSize);
		return p;
	}
	public Page getPageRecordsOflodop(String config_path, String config_key, ExtMap<String,Object> params) throws DataAccessException {
		int pageIndex = params.getInteger(ExtUtils.pageIndex);
		int pageSize = params.getInteger(ExtUtils.pageSize);

		SqlParse sqlParse = new SqlParse(config_path, config_key);
		sqlParse.bind(params);
		SqlParam sqlParam = sqlParse.parse();
		Page p = this.getPageRecords(sqlParam.getSql(), sqlParam.getParams(), pageIndex, pageSize);
		return p;
	}	
	public Page getPageRecords(final String queryString,final List<?> param,
			final int pageIndex, final int pageSize) throws DataAccessException {
		return this.getPageRecords(queryString,param.toArray(),pageIndex,pageSize);
	}
	
	/**
	 * 将一个分页的按字段查询返回的Object[]数组，转换为List<Map<String,String>>输出
	 * @param queryString
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws DataAccessException
	 */
	public Page getPageRecordsObjectsToMap(final String queryString,final List<?> param,
			final int pageIndex, final int pageSize) throws DataAccessException {
		Page p = this.getPageRecords(
				queryString, 
				param, 
				pageIndex, 
				pageSize);
		
		if(p!=null && p.getList()!=null)
			p.setList(ObjectUtil.makeReturnDataBySQLQuery(queryString, p.getList()));
		
		return p;
	}
	
	public Page getPageRecords(final String queryString,final Object[] param,
			final int pageIndex, final int pageSize) throws DataAccessException {
		String queryStr = queryString;
		int weizhi  = queryStr.indexOf("from")==-1?queryStr.indexOf("FROM"):queryStr.indexOf("from");
		String queryCountStr = "select count(*) "
				+ queryStr.substring(weizhi);
		int seek = queryCountStr.indexOf("order by");
		if (seek > 0)
			queryCountStr = queryCountStr.substring(0, seek);
	
		Page page = new Page();
		page.setList(this.getObjectListByHql(queryString,param, pageIndex, pageSize));
		page.setTotalCount(Integer.parseInt(this.getObjectListByHql(queryCountStr,param)
				.get(0).toString()));
		return page;
	}
	
	public List<?> getObjectListByHqlAndLock(final String queryString) throws DataAccessException {
		 Query query=getCurrentSession().createQuery(queryString);
		 query.setLockOptions(LockOptions.UPGRADE);
		 
		 DBLogUtils.createLog(queryString, null);
		 
		return query.list();
	}
	
	public List<?> getObjectListByHql(final String queryString,final Object[] param,
			final int pageindex, final int pagesize) throws DataAccessException {
		DBLogUtils.createLog(queryString, param);
		
		 Query query=getCurrentSession().createQuery(queryString);
		 
		 if(pageindex>0&&pagesize>0){
			 query.setFirstResult(((pageindex - 1) * pagesize));
			 query.setMaxResults(pagesize);
		 }
		 
		 //如果设置缓存，那么怎么群集？
		// query.setCacheable(true);//开启查询缓存
		 
		 if(param != null && param.length > 0) {
				for(int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
		}
		return query.list();
	}
	
	/**
	 * 数据枷锁
	 * @param queryString
	 * @param param
	 * @param lockMode
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getObjectListByHqlAndLock(final String queryString,final Object[] param, LockOptions lockMode) throws DataAccessException {
		Query query=getCurrentSession().createQuery(queryString);
		 query.setLockOptions(lockMode);
		 
		 if(param != null && param.length > 0) {
				for(int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
		}
		
		DBLogUtils.createLog(queryString, param);
			
		return query.list();
	}
	
	/**
	 * org.springframework.orm.hibernate4.SessionFactoryUtils 必须和spring-system-config里面的
	 * org.springframework.orm.hibernate4.HibernateTransactionManager 一一对应。
	 * @param jsbm
	 * @param qxbms
	 * @return
	 */
	public int executeSqlUpdate(String sql,Object[] objs) {
		DBLogUtils.createLog(sql, objs);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = SessionFactoryUtils.getDataSource(getCurrentSession().getSessionFactory()).getConnection();
			stmt = conn.prepareStatement(sql);
			if(objs!=null && objs.length>0){
				for(int i=0;i<objs.length;i++){
					if(objs[i] instanceof String){
						stmt.setString(i+1, (String)objs[i]);
					}else if(objs[i] instanceof Integer){
						stmt.setInt(i+1, (Integer)objs[i]);
					}else if(objs[i] instanceof Long){
						stmt.setLong(i+1, (Long)objs[i]);
					}else if(objs[i] instanceof Double){
						stmt.setDouble(i+1, (Double)objs[i]);
					}else if(objs[i] instanceof Date){
						stmt.setDate(i+1, new java.sql.Date( ((Date)objs[i]).getTime()));
					}else{
						throw new ServiceException("未知的参数类型！");
					}
				}
			}
			int i = stmt.executeUpdate();
			return i;
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateAll(final Collection<?> objects)
			throws DataAccessException {
		if (objects != null) {
			for (Iterator<?> iter = objects.iterator(); iter.hasNext();) {
				Object obj = (Object) iter.next();
				update(obj);
			}
		}
	}
	
	// add mang entitys
	public void createAll(final Collection<?> objects)
			throws DataAccessException {
		if (objects != null) {
			for (Iterator<?> iter = objects.iterator(); iter.hasNext();) {
				Object obj = (Object) iter.next();
				create(obj);
			}
		}
	}
	
	// Delete many entitys
	public int deleteAll(final Collection<?> objects)
			throws DataAccessException {
		int count=0;
		if (objects != null) {
			for (Iterator<?> iter = objects.iterator(); iter.hasNext();) {
				Object obj = (Object) iter.next();
				delete(obj);
				count++;
			}
			//先强制执行delete，否则会在insert之后执行此方法，导致唯一索引报错
			//delete 只是把对象转换为准备删除的状态，要提交以后再删除， 同一个对象，先delete，在save 对象状态 准备删除 －> 托管状态
			//不过是新增需要取得ID，所以会直接放到数据库去，所以不强制会导致先insert,后delete，当存在唯一索引的时候，会引发异常
		}
		return count;
	}
	
	public Page executeSQLPageRecords(String queryString, Object[] param,	int pageIndex, int pageSize) {
			String queryCountStr = null;
			String queryStr = queryString.toLowerCase();
			
			queryCountStr = "select count(*) " + queryStr.substring(queryStr.indexOf("from"));
			int seek = queryCountStr.indexOf("order by");
			if (seek > 0)
				queryCountStr = queryCountStr.substring(0, seek);
			
			Page page = new Page();
			
			if(pageIndex>=0){
				page.setList(this.executeSqlQuery(queryString, param, pageIndex, pageSize));
				List<?> objlist  = this.executeSqlQuery(queryCountStr,param);
				String obj = null;
				if(objlist==null || objlist.size()==0)
				    obj = "0";
				else
				    obj = objlist.get(0).toString();
				
				page.setTotalCount(Integer.parseInt(obj.toString()));
			}else{
				page.setList(this.executeSqlQuery(queryString,  param));
				page.setTotalCount(page.getList().size());
			}
	
			//sql查询返回的list里，每一个对象是object数组，如object[0]、object[1]，以下做处理，封装成map对象
			if(CommonUtil.isNotEmpty(page.getList()) && page.getList().get(0) instanceof Object[]){
				page.setList(ObjectUtil.makeReturnDataBySQLQuery(queryString, page.getList()));
			}
			return page;
	}
	
	public List<?> executeSqlQuery(final String sql, final Object[] param, int pageIndex, int pageSize) {
		DBLogUtils.createLog(sql, param);
		
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		
		if(pageIndex>=0 && pageSize>0){
			//if(pageIndex<=1)
			if(pageIndex==0) pageIndex = 1;
			
			query.setFirstResult((pageIndex - 1) *  pageSize);
			query.setMaxResults(pageSize);
		}
		
		if(param != null && param.length > 0) {
			for(int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		
		List<?> list = query.list();
		
		/*
		if (null != list && list.size() > 0) {
			if (list.get(0) instanceof Object[]) {
				// sql查询返回的list里，每一个对象是object数组，如object[0]、object[1]，以下做处理，封装成map对象
				List<Map<String, String>> mapList = ObjectUtil
						.makeReturnDataBySQLQuery(sql, list);
				return mapList;
			}
		}*/
		
		return list;
	}
	
	public List<?> executeSqlQuery(final String sql, final Object[] param) {
		return this.executeSqlQuery(sql,param,-1,-1);
	}
	
	/**
	 * 查询maplist
	 * @param sql
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, String>> executeSqlQueryMapList(final String sql, final Object[] param,
			int pageIndex, int pageSize) {
		List<?> list = this.executeSqlQuery(sql, param,
				pageIndex, pageSize);
		
		List<Map<String, String>> mapList = ObjectUtil.makeReturnDataBySQLQuery(sql, list);
		return mapList;
	}
	
	/**
	 * 查询maplist
	 * @param sql
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, String>> executeSqlQueryMapList(final String sql, final Object[] param) {
		return this.executeSqlQueryMapList(sql, param,-1,-1);
	}
	
	/**
	 * 查询map对象
	 * @param sql
	 * @param param
	 * @return
	 */
	public Map<String, String> executeSqlQueryMap(final String sql, final Object[] param) {
		List<Map<String, String>> result = this.executeSqlQueryMapList(sql,
				param, -1, -1);
		if (null == result || result.size() <= 0) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * 获取序列号
	 * @param tablename
	 * @return
	 */
	public String getSeqByTable(String tablename) {
		CallProc proc = new CallProc();
		proc.setProcname("{call P_SYSTEM_GET_SERIALNO(?,?,?,?)}");
		
		List<CallParameter> params = new ArrayList<CallParameter>();
		
		InCallParameter p1 = new InCallParameter();
		p1.setParameterIndex(1);
		p1.setValueType(ParameterValueType.STRING);
		p1.setParameterValue(tablename);
		params.add(p1);
		
		OutCallParameter p2 = new OutCallParameter();
		p2.setParameterIndex(2);
		p2.setParameterName("snvalue");
		p2.setValueType(ParameterValueType.STRING);
		params.add(p2);
		
		OutCallParameter p3 = new OutCallParameter();
		p3.setParameterIndex(3);
		p3.setParameterName("message");
		p3.setValueType(ParameterValueType.STRING);
		params.add(p3);
		
		OutCallParameter p4 = new OutCallParameter();
		p4.setParameterIndex(4);
		p4.setParameterName("result");
		p4.setValueType(ParameterValueType.STRING);
		params.add(p4);
		
		proc.setParams(params);
		
		executeProcedure(proc);
		
		String result = proc.getReturnData().getData().get("result");
		String message = proc.getReturnData().getData().get("message");
		String snvalue = proc.getReturnData().getData().get("snvalue");
		
		if("0".equals(result)){
			if(CommonUtil.isEmpty(snvalue))
				throw new ServiceException("获取[" + tablename + "]的序号返回值为空！");
			
			return snvalue;
		}else{
			if(CommonUtil.isEmpty(message))
				throw new ServiceException("获取[" + tablename + "]的序号返回错误代码：" + result);
			else
				throw new ServiceException(message);
		}
	}
	
    public SqlParam getSqlParam(String segment, String segmentkey, Map<String, Object> params) {
		SqlParse sqlParse = new SqlParse(Config.getSql(segment, segmentkey));
		sqlParse.bind(params);
		SqlParam sqlParam = sqlParse.parse();
		return sqlParam;
	}
    
	public  Serializable getIdBySequence(Class<?> entityClass) throws DAOException{
		String val = entityClass.getSimpleName();
		if(val.startsWith("Po")){
			val = "SID_" + val.substring(2);
		}
		
		ID id = new ID(entityClass.getName(), "assigned" , val);
		
		return getIdBySequence(id);
	}
	
	public  Serializable getIdBySequence(ID id) throws DAOException {
		Serializable ser = null;
		// 组织HQL查询语句
		StringBuffer strBufHQL = new StringBuffer();
		strBufHQL.append("select ").append(id.getValue().trim()).append(".nextval from dual");
		try {
			List<?> list = this.executeSqlQuery(strBufHQL.toString(), new Object[]{});
			try {
				/////////////////////////////////////////////////////////////////
				// 取SequenceID的Value
				///////////////////////////////////////////////////////////////////
				long lSeqid = Long.parseLong(list.get(0).toString());
				/////////////////////////////////////////////////////////////////
				// 根据系统控制参数表得到DBID的前3位和DBID的后6位
				/////////////////////////////////////////////////////////////////
				VoXT_XTKZCSB voBefore3 = null;
				VoXT_XTKZCSB voAfter6 = null;
				try {
					voBefore3 = SysParam.getXt_xtkzcsb(PublicConstant.XTKZCS_DBID_BEFORE_3);
					voAfter6 = SysParam.getXt_xtkzcsb(PublicConstant.XTKZCS_DBID_AFTER_6);
				} catch (ServiceException ex) {
					// 从内存中取不到系统控制参数,然后,可以从数据库中取.
				}
				/////////////////////////////////////////////////////////////////
				// 如果为空，则从数据库中取数据
				/////////////////////////////////////////////////////////////////
				if (voBefore3 == null || voAfter6 == null) {
					String strBefore3 = "from PoXT_XTKZCSB  where kzlb='" + PublicConstant.XTKZCS_DBID_BEFORE_3 + "'";
					String strAfter6 = "from PoXT_XTKZCSB  where kzlb='" + PublicConstant.XTKZCS_DBID_AFTER_6 + "'";
					List<?> lstPo3 = this.getObjectListByHql(strBefore3);
					List<?> lstPo6 = this.getObjectListByHql(strAfter6);
					if (lstPo3 != null && !lstPo3.isEmpty()) {
						voBefore3 = new VoXT_XTKZCSB((PoXT_XTKZCSB) lstPo3.get(0));
					}
					if (lstPo6 != null && !lstPo6.isEmpty()) {
						voAfter6 = new VoXT_XTKZCSB((PoXT_XTKZCSB) lstPo6.get(0));
					}
				}
				////////////////////////////////////////////////////////////////
				// 得到前9位
				/////////////////////////////////////////////////////////////////
				long lID = 0;
				if (voBefore3 != null && voAfter6 != null) {
					if (voBefore3.getKzz() != null && voAfter6.getKzz() != null) {
						// lID =
						// Long.parseLong(voBefore3.getKzz().trim().concat(voAfter6.
						// getKzz().trim()));
						// edit by kgb 2004-06-04
						lID = Long.parseLong(voAfter6.getKzz().trim().concat(voBefore3.getKzz().trim()));

					} else {
						throw new DAOException(WSErrCode.ERR_DB_GETID, "系统控制参数表中，控制参数代码为（9000和9001）对应的数据值没有设置。", null);
					}
				} else {
					throw new DAOException(WSErrCode.ERR_DB_GETID, "系统控制参数表中，控制参数代码为（9000和9001）对应的数据值没有设置。", null);
				}
				/////////////////////////////////////////////////////////////
				// 返回最后取得的ID
				///////////////////////////////////////////////////////////////
				if(id.getValue().equals("SID_HZ_ZJ_SB")) {//add by zjm 20190910 中间表id要求十位  特殊处理
					lID = Long.parseLong(this.getUser().getDqbm()) * 1000000L + lSeqid;
				}else {
					lID = lID * 10000000000L + lSeqid;
				}
				
				ser = new Long(lID);
			} finally {
			}
		} catch (Exception sqle) {
			throw new DAOException(WSErrCode.ERR_DB_GETID, sqle);
		}
		return ser;
	}
	
	public Date getSjksj() throws DAOException {
		Date serviceTime = null; // 数据库时间
		Date systemTime = null; // 中间件时间
		Date returnTime = null; // return的时间
		Date nowDate = null;
		boolean kz = false;
		String strSQL = null;

		try {
			// 根据控制参数判断取中间件时间或数据库时间
			String kzcs_sjqz = HjCommon.getXtkzcsAsStr(PublicConstant.XTKZCS_SFCSJKQSJ);
			if ("".equals(kzcs_sjqz) || kzcs_sjqz == null) {
				kzcs_sjqz = "0";
			}
			if ("1".equals(kzcs_sjqz)) {
				if (PublicConstant.DefaultServiceTime != null && PublicConstant.DefaultSystemTime != null) {

					serviceTime = PublicConstant.DefaultServiceTime; // 记录的数据库时间
					systemTime = PublicConstant.DefaultSystemTime; // 记录的中间件时间
					nowDate = new java.util.Date(); // 当前中间件时间

					long sjc = ((nowDate.getTime() - systemTime.getTime()) / 1000);// 时间差
					// 超过10分钟则重新获取数据库时间
					if (Math.abs(sjc) > 60) {
						kz = true;
					} else {
						// 记录的数据库时间+当前中间件时间-记录的数据库时间=当前数据库时间
						long time = serviceTime.getTime() + nowDate.getTime() - systemTime.getTime();
						returnTime = new java.util.Date(time);
					}

				} else {
					kz = true;
				}

				if (kz) {
					strSQL = "select to_char(sysdate,'yyyymmddhh24miss') from dual ";
					String time = (String)this.executeSqlQuery(strSQL, new Object[]{}).get(0);
					//String time = selectDual(strSQL); // 得到数据库时间
					nowDate = new java.util.Date(); // 得到当前中间件时间

					returnTime = StringUtils.strToDate(time, "yyyyMMddHHmmss"); // 转换时间格式
					PublicConstant.DefaultServiceTime = returnTime; // 将数据库时间保存到全局变量
					PublicConstant.DefaultSystemTime = nowDate; // 将中间件时间保存到全局变量
				}
			} else {
				returnTime = new java.util.Date();
			}

			if ("".equals(returnTime) || returnTime == null) {
				throw new Exception("得不到数据库时间，操作失败。");
			}

		}
		// 异常原因：ThreadLocalSession.currentSession抛出的异常
		catch (DAOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new DAOException(WSErrCode.ERR_DB_GETID, " 获取数据库时间异常。", ex);
		}
		return returnTime;
	}
	
	public String getServerTime(String format){
		Date date = getSjksj();
		return DateHelper.formateDate(date, CommonUtil.isEmpty(format)?"yyyyMMdd":format);
	}
	
	
}
