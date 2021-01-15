package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import java.sql.SQLException;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.constant.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 数据字典操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtsjzdServiceImpl
    extends XtBaseService

    implements XtsjzdService {

  PojoInfo   Xt_sjzdbDao = DAOFactory.createXT_SJZDBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();

  /**
   *查询信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjzdxx(String sHqlXtsjzd) throws ServiceException,
      DAOException {
    return getXtsjzdxx(sHqlXtsjzd, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtsjzdxx(String sHqlXtsjzd, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtsjzd,new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return lstXtRtn;
  }

  /**
   *查询信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtsjzdxx(VarVO vvQryXtzd,String psWhere,VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtsjzdxx = null;
    VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息,
    String sZdid = vvQryXtzd.getVarValue("zdid");
    String sZdmc = vvQryXtzd.getVarValue("zdmc");
    String sZdhy = vvQryXtzd.getVarValue("zdhy");
    String sZdlx = vvQryXtzd.getVarValue("zdlx");
    String sZdcd = vvQryXtzd.getVarValue("zdcd");
    String sZdmj = vvQryXtzd.getVarValue("zdmj");
    String sBdlx = vvQryXtzd.getVarValue("bdlx");
    String sBdsj = vvQryXtzd.getVarValue("bdsj");
    //HQL条件语句

    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(" select XT_SJZDB FROM PoXT_SJZDB as XT_SJZDB where 1=1");
    strCountHql.append(" select count(*) FROM PoXT_SJZDB as XT_SJZDB where 1=1");
    //HQL条件语句zdid,zdmc,zdhy,zdlx,zdcd,zdmj,bdlx,bdsj;
    if ( (sZdid != null) && ! ("".equals(sZdid))) {
      strHql.append(" and  zdid ='").append(sZdid).append("'");
    }
    if ( (sZdmc != null) && ! ("".equals(sZdmc))) {
      strHql.append(" and  zdmc ='").append(sZdmc).append("'");
    }
    if ( (sZdhy != null) && ! ("".equals(sZdhy))) {
      strHql.append(" and  zdhy ='").append(sZdhy).append("'");
    }
    if ( (sZdlx != null) && ! ("".equals(sZdlx))) {
      strHql.append(" and  zdlx ='").append(sZdlx).append("'");
    }
    if ( (sZdcd != null) && ! ("".equals(sZdcd))) {
      strHql.append(" and  zdcd ='").append(sZdcd).append("'");
    }
    if ( (sZdmj != null) && ! ("".equals(sZdmj))) {
      strHql.append(" and  zdmj ='").append(sZdmj).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
    strHql.append(" and (" + psWhere + ")");
  }
   strHql.append(" order by zdmc");
   //HQL条件语句
  strBufHql.append(strHql);
  strCountHql.append(strHql);

  if (voPage.getRecordcount() == -1) {
    try {
      voQueryResult.setRecordcount(super.getCount(strCountHql.
          toString()));
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }
  voQueryResult.setPagelist(getXtsjzdxx(strBufHql.toString(),
                                            voPage.getPageindex(),
                                            voPage.getPagesize()));
  voQueryResult.setVotype(PoXT_SJZDB.class);
  return voQueryResult;
   //执行HQL语句
  }

  /**
   *增加数据字典信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsjzdxx(VarVO vvXtsjzdb) throws ServiceException,
      DAOException {
    ArrayList lstXtsjzd = new ArrayList();
    PoXT_SJZDB poXTSJZDB = new PoXT_SJZDB();
    PoXT_SJZDB rtnpoXTSJZDB = new PoXT_SJZDB();
    //执行HQL语句
    try {
      //获取参数信息
      // zdid,zdmc,zdhy,zdlx,zdcd,zdmj,bdlx,bdsj;
      BeanUtils.populate(poXTSJZDB, vvXtsjzdb.getVarmap());
      poXTSJZDB.setZdid( (Long) Xt_sjzdbDao.getId());
      poXTSJZDB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTSJZDB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTSJZDB =(PoXT_SJZDB)super.create(poXTSJZDB);
      lstXtsjzd.add(rtnpoXTSJZDB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstXtsjzd;
  }

  /**
   *修改数据字典信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsjzdxgxx(VarVO vvXtsjzdb) throws ServiceException,
      DAOException {
    ArrayList lstXtsjzd = new ArrayList();
    PoXT_SJZDB poXTSJZDB = null;
    PoXT_SJZDB rtnpoXTSJZDB = new PoXT_SJZDB();
    //组合poXT_XTCSB
    //执行HQL语句
    try {
      //获取参数信息
      // zdid,zdmc,zdhy,zdlx,zdcd,zdmj,bdlx,bdsj;
      poXTSJZDB  = super.get(PoXT_SJZDB.class,Long.valueOf(vvXtsjzdb.
          getVarValue("zdid")));
      if (poXTSJZDB == null) {
        return null;
      }
      BeanUtils.populate(poXTSJZDB, vvXtsjzdb.getVarmap());
      poXTSJZDB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTSJZDB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTSJZDB =      (PoXT_SJZDB)super.update(poXTSJZDB);
      lstXtsjzd.add(rtnpoXTSJZDB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstXtsjzd;
  }

  /**
   *删除系统参数信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtsjzdscxx(VarVO vvXtsjzdb) throws ServiceException,
      DAOException {
    PoXT_SJZDB poXTSJZDB = new PoXT_SJZDB();
    PoXT_SJZDB rtnpoXTSJZDB = new PoXT_SJZDB();
    ArrayList lstXtsjzd = new ArrayList();
    int nReturn = PublicConstant.XT_BZ_ERROR;

    //执行HQL语句
    try {
      poXTSJZDB.setZdid(Long.valueOf(vvXtsjzdb.getVarValue("zdid")));
      super.delete(poXTSJZDB);
      nReturn = PublicConstant.XT_BZ_SUCC;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return nReturn;
  }

  /**
   * latest change date
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjzdzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_SJZDB FROM PoXT_SJZDB as XT_SJZDB where ")
        .append(
        " bdsj in (select max(XT_SJZDB.bdsj) FROM PoXT_SJZDB as XT_SJZDB )");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
                                                  PublicConstant.XT_PAGE,
                                                  1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_SJZDB FROM PoXT_SJZDB as XT_SJZDB";
        lstXtRtn =super.getPageRecords(sHql,new Object[]{},
            PublicConstant.XT_PAGE, 1).getList();
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return lstXtRtn;
  }

	@Override
	public boolean reload(ExtMap<String, Object> params) {
		
		boolean result = false;
		int reloadFlag = params.getInteger("reloadFlag");
		if(reloadFlag == 0) {
			try{
				DictData.reLoad(null);
				result = true;
			}catch(SQLException e){
				throw new ServiceException(WSErrCode.ERR_CTRL_LOAD_DATADICTIONARY, "，户别变更业务无法完成。", null);
			}catch(Exception e){
				throw new ServiceException(WSErrCode.ERR_CTRL_LOAD_DATADICTIONARY, "数据字典加载异常", null);
			}
		}else if(reloadFlag == 1) {
			try{
				DictData.reLoadZzjg(null);
				result = true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(reloadFlag == 2) {
			try{
				DictData.reLoadZzjy(null);
				result = true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

}
