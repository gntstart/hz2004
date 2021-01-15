package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 本市市区操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtbssqServiceImpl
    extends XtBaseService
    implements XtbssqService {

  PojoInfo   Xt_bssqbDao = DAOFactory.createXT_BSSQBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbssqxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtbssqxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                       PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtbssqxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbssqxx = null;
    PoXT_BSSQB poXT_BSSQB = null;
    PoXT_XZQHB poXT_XZQHB = null;
    PoXT_XTCSB poXT_XTCSBA = null;
    PoXT_XTCSB poXT_XTCSBB = null;
    PoXT_XTCSB poXT_XTCSBC = null;
    Object[] objXtRtn = null;
    List lstReturn = new ArrayList();
    //执行HQL语句
    try {
      lstXtbssqxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtbssqxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbssqxx.get(i);
        poXT_BSSQB = (PoXT_BSSQB) objXtRtn[0];
        poXT_XZQHB = (PoXT_XZQHB) objXtRtn[1];
        poXT_XTCSBA = (PoXT_XTCSB) objXtRtn[2];
        poXT_XTCSBB = (PoXT_XTCSB) objXtRtn[3];
        poXT_XTCSBC = (PoXT_XTCSB) objXtRtn[4];
        VoXtbssq voXtbssqxx = new VoXtbssq(poXT_BSSQB, poXT_XZQHB, poXT_XTCSBA,
                                           poXT_XTCSBB, poXT_XTCSBC);
        lstReturn.add(voXtbssqxx);
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
    return lstReturn;
  }

  /**
   *查询本市市区信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtbssqxx(VarVO vvQryXtbssq, String psWhere,
                                   VoPage voPage) throws ServiceException,
      DAOException {

     VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息
    //sqid,qhdm,qybz,bdlx,bdsj;
    String sSqid = vvQryXtbssq.getVarValue("sqid");
    String sQhdm = vvQryXtbssq.getVarValue("qhdm");
    String sQybz = vvQryXtbssq.getVarValue("qybz");
    String sSqbz = vvQryXtbssq.getVarValue("sqbz");
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_BSSQB,XT_XZQHB,XT_XTCSBA,XT_XTCSBB,XT_XTCSBC FROM PoXT_BSSQB");
     strCountHql.append(" select count(*) from PoXT_BSSQB");
       strHql.append(
        " as XT_BSSQB,PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSBA, PoXT_XTCSB")
        .append(" as XT_XTCSBB,PoXT_XTCSB as XT_XTCSBC ")
        .append(
        " where  XT_BSSQB.sqbz = XT_XTCSBA.dm and XT_XTCSBA.cslb = '9029'")//" where  XT_BSSQB.sqbz = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9029'")
        .append(
        " and XT_BSSQB.gxbz = XT_XTCSBB.dm and XT_XTCSBB.cslb = '9030'")//" and XT_BSSQB.gxbz = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '9030'")
        .append(
        " and XT_BSSQB.qybz = XT_XTCSBC.dm and XT_XTCSBC.cslb = '9002'")//" and XT_BSSQB.qybz = XT_XTCSBC.dm(+) and XT_XTCSBC.cslb(+) = '9002'")
        .append("  and XT_BSSQB.qhdm = XT_XZQHB.dm");
    if ( (sSqid != null) && ! ("".equals(sSqid))) {
      strHql.append(" and  XT_BSSQB.sqid =").append(sSqid);
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strHql.append(" and  XT_BSSQB.qhdm =").append(sQhdm);
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_BSSQB.qybz ='").append(sQybz).append("'");
    }
    if ( (sSqbz != null) && ! ("".equals(sSqbz))) {
      strHql.append(" and  XT_BSSQB.sqbz ='").append(sSqbz).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by XT_BSSQB.qhdm");
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
    voQueryResult.setPagelist(getXtbssqxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(VoXtbssq.class);
    return voQueryResult;
  }

  /**
   *增加本市市区信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbssqxx(VarVO vvXtbssqb) throws ServiceException,
      DAOException {
    ArrayList lstXtbssq = new ArrayList();
    PoXT_BSSQB poXTBSSQB = new PoXT_BSSQB();
    PoXT_BSSQB rtnpoXTBSSQB = new PoXT_BSSQB();
    //获取参数信息
    //sqid,qhdm,qybz,bdlx,bdsj;
    //组合poXT_XtbssqB
    try {
      BeanUtils.populate(poXTBSSQB, vvXtbssqb.getVarmap());
      poXTBSSQB.setSqid( (Long) Xt_bssqbDao.getId());
      poXTBSSQB.setQybz(PublicConstant.XT_BZ_QY);
      poXTBSSQB.setBdlx(PublicConstant.XT_BDLX_ADD);
      poXTBSSQB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTBSSQB =(PoXT_BSSQB)super.create(poXTBSSQB);
      lstXtbssq.add(rtnpoXTBSSQB);
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
    return lstXtbssq;
  }
  /**
   *增加本市市区信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbssqxxNew(VarVO vvXtbssqb) throws ServiceException,
      DAOException {
	String addType = vvXtbssqb.getVarValue("addType");
	List<String> qhdmlist = new ArrayList<String>();
	if ( (addType != null) && ! ("".equals(addType))) {
		if(addType.equals("1")) {
			qhdmlist.add(vvXtbssqb.getVarValue("xzqhdmadd1"));
		}else if(addType.equals("2")) {
			int begin = Integer.parseInt(vvXtbssqb.getVarValue("xzqhdmadd2"));
			int end = Integer.parseInt(vvXtbssqb.getVarValue("xzqhdmadd3"));
			for(int i=begin;i<=end;i++) {
				PoXT_XZQHB xzqhb = super.get(PoXT_XZQHB.class, i+"");
				if(xzqhb!=null) {
					qhdmlist.add(Integer.toString(i));
				}
			}
		}
	}
    ArrayList lstXtbssq = new ArrayList();
    
    PoXT_BSSQB rtnpoXTBSSQB = new PoXT_BSSQB();
    //获取参数信息
    //sqid,qhdm,qybz,bdlx,bdsj;
    //组合poXT_XtbssqB
    try {
    	for(int i=0;i<qhdmlist.size();i++) {
    		PoXT_BSSQB poXTBSSQB = new PoXT_BSSQB();
    		BeanUtils.populate(poXTBSSQB, vvXtbssqb.getVarmap());
    		poXTBSSQB.setQhdm(qhdmlist.get(i));
    		poXTBSSQB.setSqid( (Long) Xt_bssqbDao.getId());
    		poXTBSSQB.setQybz(PublicConstant.XT_BZ_QY);
    		poXTBSSQB.setBdlx(PublicConstant.XT_BDLX_ADD);
    		poXTBSSQB.setBdsj(StringUtils.getServiceTime());
    		rtnpoXTBSSQB =(PoXT_BSSQB)super.create(poXTBSSQB);
    	}
      lstXtbssq.add(rtnpoXTBSSQB);
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
    return lstXtbssq;
  }
  /**
   *修改本市市区信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbssqxgxx(VarVO vvXtbssqb) throws ServiceException,
      DAOException {
    ArrayList lstXtbssq = new ArrayList();
    PoXT_BSSQB poXTBSSQB = null;
    PoXT_BSSQB rtnpoXTBSSQB = new PoXT_BSSQB();
    //获取参数信息
    //sqid,qhdm,qybz,bdlx,bdsj;
    //组合poXT_XtbssqB
    //执行HQL语句
    try {
      poXTBSSQB  = super.get(PoXT_BSSQB.class,Long.valueOf(vvXtbssqb.
          getVarValue("sqid")));
      if (poXTBSSQB == null) {
        return null;
      }
      BeanUtils.populate(poXTBSSQB, vvXtbssqb.getVarmap());
      poXTBSSQB.setBdlx(PublicConstant.XT_BDLX_UPDATE);
      poXTBSSQB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTBSSQB =    (PoXT_BSSQB)super.update(poXTBSSQB);
      lstXtbssq.add(rtnpoXTBSSQB);
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
    return lstXtbssq;
  }

  /**
   *删除本市市区信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbssqscxx(VarVO vvXtbssqb) throws ServiceException,
      DAOException {
    PoXT_BSSQB poXTBSSQB = null;
    PoXT_BSSQB rtnpoXTBSSQB = new PoXT_BSSQB();
    List lstXtRtn = new ArrayList();
    //组合poXT_XtbssqB
    try {
      poXTBSSQB  = super.get(PoXT_BSSQB.class,Long.valueOf(vvXtbssqb.
          getVarValue("sqid")));
      if (poXTBSSQB == null) {
        return null;
      }
      poXTBSSQB.setQybz(PublicConstant.XT_BZ_BQY);
      poXTBSSQB.setBdlx(PublicConstant.XT_BDLX_UPDATE);
      poXTBSSQB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTBSSQB = (PoXT_BSSQB)super.update(poXTBSSQB);
      lstXtRtn.add(rtnpoXTBSSQB);
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
   * latest change date
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbssqzdbdsj() throws ServiceException, DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer sHql = new StringBuffer();
    sHql.append(" select XT_BSSQB FROM PoXT_BSSQB as XT_BSSQB where ")
        .append(
        " bdsj in (select max(XT_BSSQB.bdsj) FROM PoXT_BSSQB as XT_BSSQB )");
    try {
      lstXtRtn =super.findAllByHQL(sHql.toString());
      if (lstXtRtn == null || (lstXtRtn != null && lstXtRtn.isEmpty())) {
        String Hql = "select XT_BSSQB FROM PoXT_BSSQB as XT_BSSQB ";
        lstXtRtn =super.getPageRecords(Hql,new Object[]{}, 0, 1).getList();
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

}
