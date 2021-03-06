/* Generated by Together */

package com.hzjc.hz2004.service.impl;

import org.dom4j.*;
import org.hibernate.LockOptions;

import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.common.xml.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.constant.*;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */
abstract public class SfzServiceImpl
    extends ZjBaseService {

  /**
   * 得到系统使用结构单位代码
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected String getDwdm() throws ServiceException, DAOException {
    String strDwdm = null;
    try {
      strDwdm = this.getXtkzcsAsStr(PublicConstant.XTKZCS_XTSYDWJGDM);
      strDwdm = strDwdm == null ? "" : strDwdm.trim();
      if (strDwdm.length() != 12) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
            "系统控制参数表中，控制参数代码为（9002-->系统使用单位机构代码）对应的数据值没有设置。", null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return strDwdm;
  }

  /**
   *得到系统使用结构单位名称
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected String getDwmc() throws ServiceException, DAOException {
    String strDwdm = null;
    try {
      strDwdm = this.getXtkzcsAsStr(PublicConstant.XTKZCS_XTSYDWJGMC);
      strDwdm = strDwdm == null ? "" : strDwdm.trim();
      if (strDwdm.equals("")) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
            "系统控制参数表中，控制参数代码为（9003-->系统使用单位机构名称）对应的数据值没有设置。", null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return strDwdm;
  }

  /**
   *得到系统应用范围
   * @return
   */
  protected String getXtyyfw() {
    String strXtyyfw = null;
    try {
      strXtyyfw = this.getXtkzcsAsStr(PublicConstant.XTKZCS_XTYYFW);
      strXtyyfw = strXtyyfw == null ? "" : strXtyyfw.trim();
      if (strXtyyfw.equals("")) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                   "系统控制参数表中，控制参数代码为（5006-->系统应用范围）对应的数据值没有设置。", null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return strXtyyfw;
  }

  /**
   * 生成制证信息包的数据包编号
   * 常住人口信息交换数据包编号由25位代码组成
   * 单位代码 + 年月日 + 顺序号
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected synchronized String assignSjbbh() throws
      ServiceException,
      DAOException {
    String strSjbbh = null;
    try {
      ////////////////////////////////////////////////////////////////////
      //得到系统使用单位机构代码
      ////////////////////////////////////////////////////////////////////
      String strDwdm = getDwdm();
      ////////////////////////////////////////////////////////////////////
      //查询更新数据库中记录
      ////////////////////////////////////////////////////////////////////
      PojoInfo  xt_sjbbhbDAO = DAOFactory.createXT_SJBBHBDAO();
      String strCurDate = StringUtils.formateDate();
      StringBuffer strBufHQL = new StringBuffer();
      strBufHQL.append("from PoXT_SJBBHB where rq='").append(strCurDate).append(
          "'");
      List lstpos =super.findAllByHQL(strBufHQL.toString());
      if (lstpos != null && !lstpos.isEmpty()) {
        //1)有记录则更新并取出
        PoXT_SJBBHB po = (PoXT_SJBBHB) lstpos.get(0);
        super.refresh(po, LockOptions.UPGRADE); //锁定修改数据包编号
        int iBhid = Integer.parseInt(po.getBhid().trim());
        String strBhid = String.valueOf(++iBhid);
        po.setBhid(strBhid);
       super.update(po); //更新数据包编号
        String strAssignID = "00000".substring(0,
                                               5 -
                                               strBhid.length()).concat(
            strBhid);
        //返回数据包编号
        strSjbbh = strDwdm.concat(strCurDate).concat(strAssignID);
      }
      else {
        //2)没有XT_SLHXLB记录插入一条
        String strBhid = "1";
        PoXT_SJBBHB po = new PoXT_SJBBHB();
        po.setRq(strCurDate);
        po.setBhid(strBhid);
        Long lBhid = (Long) xt_sjbbhbDAO.getId();
        po.setXlid(lBhid);
        super.create(po); //插入数据包编号记录
        //返回数据包编号
        strSjbbh = strDwdm.concat(strCurDate).concat("00001");
      }
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                 "常住人口信息交换数据包编号ID非数字，累计计算异常。",
                                 ex);
    }
    return strSjbbh;
  }

}
