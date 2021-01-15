package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 用户IP允许操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtyhipyxServiceImpl
    extends XtBaseService

    implements XtyhipyxService {

  PojoInfo   Xt_yhipyxbDao = DAOFactory.createXT_YHIPYXXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhipyxxx(String sHql) throws ServiceException,
      DAOException {
    return getXtyhipyxxx(sHql, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtyhipyxxx(String sHql, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtyhipyxxx = null;
    Object[] objXtRtn = null;
    PoXT_YHIPYXXXB poXtyhipyxb = null;
    PoXT_YHXXB poXtyhxxb = null;
    PoXT_YHXXB poXtcjrxxb = null;
    List lstReturn = new ArrayList();
    try {
      lstXtyhipyxxx =super.getPageRecords(sHql,new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtyhipyxxx.size(); i++) {
        objXtRtn = (Object[]) lstXtyhipyxxx.get(i);
        poXtyhipyxb = (PoXT_YHIPYXXXB) objXtRtn[0];
        poXtyhxxb = (PoXT_YHXXB) objXtRtn[1];
        poXtcjrxxb = (PoXT_YHXXB) objXtRtn[2];
        VoXtyhipyx voXtyhipyx = new VoXtyhipyx(poXtyhipyxb, poXtyhxxb,
                                               poXtcjrxxb);
        lstReturn.add(voXtyhipyx);
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

  public List getXtyhipyxxx(VarVO vvQryXtyhipyx) throws ServiceException,
      DAOException {
    List lstXtyhipyxxx = null;
    String sIpyxid = vvQryXtyhipyx.getVarValue("ipyxid");
    String sYhxm = vvQryXtyhipyx.getVarValue("yhxm");
    String sYhid = vvQryXtyhipyx.getVarValue("yhid");
    //获取参数信息
    //ipyxid,yhid,ipdz,cjrid,cjsj;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHIPYXXXB,XTYHXXBA,XTYHXXBB FROM PoXT_YHIPYXXXB as XT_YHIPYXXXB, ")
        .append("PoXT_YHXXB as XTYHXXBA,PoXT_YHXXB as XTYHXXBB  ")
        .append(" where XT_YHIPYXXXB.yhid = XTYHXXBA.yhid(+)")
        .append(" and XT_YHIPYXXXB.cjrid = XTYHXXBB.yhid");
    if ( (sIpyxid != null) && ! ("".equals(sIpyxid))) {
      strBufHql.append(" and  XT_YHIPYXXXB.ipyxid =").append(sIpyxid);
    }
    if ( (sYhxm != null) && ! ("".equals(sYhxm))) {
      strBufHql.append(" and  XTYHXXBA.yhxm ='").append(sYhxm).append("'");
    }
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHIPYXXXB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHIPYXXXB.ipyxid");
    return getXtyhipyxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhipyxxx(VarVO vvXtyhipyxb) throws ServiceException,
      DAOException {
    ArrayList lstXtyhipyx = new ArrayList();
    PoXT_YHIPYXXXB poXTYHIPYXB = new PoXT_YHIPYXXXB();
    PoXT_YHIPYXXXB rtnpoXTYHIPYXB = new PoXT_YHIPYXXXB();
    String sYhid = vvXtyhipyxb.getVarValue("yhid");
    //获取参数信息
    //ipyxid,yhid,ipdz,cjrid,cjsj;
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户IP允许的权限", null);
        }
      }

      BeanUtils.populate(poXTYHIPYXB, vvXtyhipyxb.getVarmap());
      poXTYHIPYXB.setIpyxid( (Long) Xt_yhipyxbDao.getId());
      poXTYHIPYXB.setCjrid(this.getUserInfo().getYhid());
      poXTYHIPYXB.setCjsj(StringUtils.getServiceTime());
      rtnpoXTYHIPYXB =(PoXT_YHIPYXXXB)super.create(poXTYHIPYXB);
      lstXtyhipyx.add(rtnpoXTYHIPYXB);
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
    return lstXtyhipyx;
  }
  public List setXtyhipyxxxNew(VarVO vvXtyhipyxb) throws ServiceException,DAOException
  {
	String sYhid ="";
	ArrayList lstXtyhipyx = new ArrayList();
	
	PoXT_YHIPYXXXB rtnpoXTYHIPYXB = new PoXT_YHIPYXXXB();
	String[] ipdzArray =vvXtyhipyxb.getVarValue("ipdzArray").split(",");
//	if(vvXtyhipyxb.getVarValue("ipdzArray")!= null) {
//		sYhid = String.valueOf(this.getUser().getYhid());
//	}else {
//		sYhid = vvXtyhipyxb.getVarValue("yhid");
//	}
	sYhid = vvXtyhipyxb.getVarValue("yhid");
	
	//获取参数信息
	//ipyxid,yhid,ipdz,cjrid,cjsj;
	try {
	  if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
	    if (!this.GetYHDwdm(sYhid).substring(0,
	                                         6).equals(this.getUserInfo().
	        getDwdm().substring(0, 6))) {
	      throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
	                                 "无增加非本区县用户IP允许的权限", null);
	    }
	  }
	
	  //BeanUtils.populate(poXTYHIPYXB, vvXtyhipyxb.getVarmap());
	  for(int i=0;i<ipdzArray.length;i++) {
		  PoXT_YHIPYXXXB poXTYHIPYXB = new PoXT_YHIPYXXXB();
		  BeanUtils.populate(poXTYHIPYXB, vvXtyhipyxb.getVarmap());
		  poXTYHIPYXB.setIpdz(ipdzArray[i]);
		  poXTYHIPYXB.setIpyxid( (Long) Xt_yhipyxbDao.getId());
		  poXTYHIPYXB.setCjrid(this.getUserInfo().getYhid());
		  poXTYHIPYXB.setCjsj(StringUtils.getServiceTime());
		  poXTYHIPYXB.setYhid(Long.parseLong(sYhid));
		  rtnpoXTYHIPYXB =(PoXT_YHIPYXXXB)super.create(poXTYHIPYXB);
		}
	  lstXtyhipyx.add(rtnpoXTYHIPYXB);
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
return lstXtyhipyx;
}
  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhipyxxgxx(VarVO vvXtyhipyxb) throws ServiceException,
      DAOException {
    ArrayList lstXtyhipyx = new ArrayList();
    PoXT_YHIPYXXXB poXTYHIPYXB = null;
    PoXT_YHIPYXXXB rtnpoXTYHIPYXB = new PoXT_YHIPYXXXB();
    //组合poXT_XTCSB
    //执行HQL语句
    try {
      poXTYHIPYXB  = super.get(PoXT_YHIPYXXXB.class,Long.valueOf(vvXtyhipyxb.
          getVarValue("ipyxid")));
      if (poXTYHIPYXB == null) { //如果查询不到记录
        return null;
      }
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHIPYXB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无修改非本区县用户IP允许的权限", null);
        }
      }
      BeanUtils.populate(poXTYHIPYXB, vvXtyhipyxb.getVarmap());
      rtnpoXTYHIPYXB =      (PoXT_YHIPYXXXB)super.update(poXTYHIPYXB);
      lstXtyhipyx.add(rtnpoXTYHIPYXB);
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
    return lstXtyhipyx;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhipyxscxx(VarVO vvXtyhipyxb) throws ServiceException,
      DAOException {
    PoXT_YHIPYXXXB poXTYHIPYXB = new PoXT_YHIPYXXXB();
    PoXT_YHIPYXXXB rtnpoXTYHIPYXB = new PoXT_YHIPYXXXB();
    ArrayList lstXtyhipyx = new ArrayList();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTCSB
    try {
      poXTYHIPYXB  = super.get(PoXT_YHIPYXXXB.class,Long.valueOf(vvXtyhipyxb.
          getVarValue("ipyxid")));
      if (poXTYHIPYXB == null) { //如果查询不到记录
        return nReturn;
      }
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHIPYXB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无删除非本区县用户IP允许的权限", null);
        }
      }
      super.delete(poXTYHIPYXB);
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

}
