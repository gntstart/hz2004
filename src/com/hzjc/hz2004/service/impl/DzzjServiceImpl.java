package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.DzzjConstant;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.constant.*;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.constant.ZjConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 乡镇街道操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class DzzjServiceImpl
    extends Hz2004BaseService
    implements DzzjService {

  PojoInfo dzzj_czrzbDAO = DAOFactory.createDZZJ_CZRZBDAO();

  /**
   * 记录日志
   * @param po
   * @throws ServiceException
   * @throws DAOException
   */
  private PoDZZJ_CZRZB WriteRz(PoDZZJ_CZRZB po) throws ServiceException,
      DAOException {
    return (PoDZZJ_CZRZB)super.create(po);
  }

  /**
   * 注册操作员
   * @param poCzy
   * @param poSb
   * @param psGlyId,管理员ID有的话为填加操作员
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List RegisterOperater(PoDZZJ_CZYXXB poCzy[], PoDZZJ_SBXXB poSb[],
                               String psGlyId) throws
      ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    lstReturn.add(0, null);
    lstReturn.add(1, null);
    try {
      PojoInfo dzzj_czyxxbDAO = DAOFactory.createDZZJ_CZYXXBDAO();
      PojoInfo dzzj_sbxxbDAO = DAOFactory.createDZZJ_SBXXBDAO();
      //开始事务
      //处理设备信息
      if (poSb != null && poSb.length > 0) { //如果有设备信息
        PoDZZJ_SBXXB poReturn[] = new PoDZZJ_SBXXB[poSb.length];
        for (int i = 0; i < poSb.length; i++) {
          PoDZZJ_SBXXB poDZZJ_SBXXB = poSb[i];
          poDZZJ_SBXXB.setSbzt(DzzjConstant.ZT_ZC);
          poDZZJ_SBXXB.setZcsj(StringUtils.getServiceTime());
          poReturn[i] = (PoDZZJ_SBXXB)super.create(poDZZJ_SBXXB);
        }
        lstReturn.add(1, poReturn);
      }
      //处理操作员信息
      if (poCzy != null && poCzy.length > 0) { //如果有操作员信息
        PoDZZJ_CZYXXB poReturn[] = new PoDZZJ_CZYXXB[poCzy.length];
        for (int i = 0; i < poCzy.length; i++) {
          PoDZZJ_CZYXXB poDZZJ_CZYXXB = poCzy[i];
          poDZZJ_CZYXXB.setCzyid( (Long) dzzj_czyxxbDAO.getId());
          poDZZJ_CZYXXB.setCzyzt(DzzjConstant.ZT_ZC);
          poDZZJ_CZYXXB.setZcsj(StringUtils.getServiceTime());
          poReturn[i] = (PoDZZJ_CZYXXB)super.create(poDZZJ_CZYXXB);

          //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          if (psGlyId != null && !"".equals(psGlyId)) {
            poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_TJCZY); //操作动作
            poDZZJ_CZRZB.setCzyid(Long.valueOf(psGlyId)); //被操作人ID
          }
          else {
            poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_GLYZC); //操作动作
            poDZZJ_CZRZB.setCzyid(poReturn[i].getCzyid()); //被操作人ID
          }
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setBczyid(poReturn[i].getCzyid());
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
        }
        lstReturn.add(0, poReturn);
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

  /**
   * 操作员认证
   * 现在只记录管理员登录信息到日志表中
   * @param poCzy
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LoginOperater(PoDZZJ_CZYXXB poCzy[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo dzzj_czyxxbDAO = DAOFactory.createDZZJ_CZYXXBDAO();
      //处理操作员信息
      if (poCzy != null && poCzy.length > 0) { //如果有操作员信息
        for (int i = 0; i < poCzy.length; i++) {
          PoDZZJ_CZYXXB poDZZJ_CZYXXB = poCzy[i];
          //认证操作员
          List lstCzy = super.findAllByHQL(
              " from PoDZZJ_CZYXXB where czyid=" +
              String.valueOf(poDZZJ_CZYXXB.getCzyid()));
          if (lstCzy == null || lstCzy.size() <= 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要认证的操作员,认证失败！", null);
          }
          //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          poDZZJ_CZRZB.setBczyid(poDZZJ_CZYXXB.getCzyid()); //被操作人ID
          poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_GLYRZ); //操作动作--管理员认证
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setCzyid(poDZZJ_CZYXXB.getCzyid());
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
          lstReturn.add(poDZZJ_CZYXXB);
        }
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;

  }

  /**
   * 删除操作员
   * @param poCzy
   * @param psGlyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List DeleteOperater(PoDZZJ_CZYXXB poCzy[], String psGlyId) throws
      ServiceException, DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo dzzj_czyxxbDAO = DAOFactory.createDZZJ_CZYXXBDAO();
      //开始事务
      //删除操作员，将操作员的状态置为1，添入注册时间信息
      if (poCzy != null && poCzy.length > 0) { //如果有操作员信息
        for (int i = 0; i < poCzy.length; i++) {
          PoDZZJ_CZYXXB poDZZJ_CZYXXB = poCzy[i];
          PoDZZJ_CZYXXB poReturn = new PoDZZJ_CZYXXB();
          //根据操作员ID获取操作员信息
          poDZZJ_CZYXXB = super.get(PoDZZJ_CZYXXB.class,poDZZJ_CZYXXB.
              getCzyid());
          poDZZJ_CZYXXB.setCzyzt(DzzjConstant.ZT_ZX); //状态设置为注销
          poDZZJ_CZYXXB.setZxsj(StringUtils.getServiceTime()); //设置注销时间
          poReturn = (PoDZZJ_CZYXXB)super.update(poDZZJ_CZYXXB);

          //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_SCCZY); //操作动作
          poDZZJ_CZRZB.setCzyid(Long.valueOf(psGlyId)); //操作人ID
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setBczyid(poDZZJ_CZYXXB.getCzyid()); //被操作员ID
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
          lstReturn.add(poReturn);
        }
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

  /**
   * 修改操作员密码
   * @param poCzy
   * @param psGlyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ModiPassword(PoDZZJ_CZYXXB poCzy[], String psCzyId) throws
      ServiceException, DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo dzzj_czyxxbDAO = DAOFactory.createDZZJ_CZYXXBDAO();
      //开始事务
      //修改操作员密码
      if (poCzy != null && poCzy.length > 0) { //如果有操作员信息
        for (int i = 0; i < poCzy.length; i++) {
          PoDZZJ_CZYXXB poDZZJ_CZYXXB = null;
          PoDZZJ_CZYXXB poReturn = new PoDZZJ_CZYXXB();
          //根据操作员ID获取操作员信息
          poDZZJ_CZYXXB = super.get(PoDZZJ_CZYXXB.class,poCzy[i].
              getCzyid());
          poDZZJ_CZYXXB.setCzymm(poCzy[i].getCzymm()); //更新密码
          poReturn = (PoDZZJ_CZYXXB)super.update(poDZZJ_CZYXXB);

          //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_XGCZYMM); //操作动作
          poDZZJ_CZRZB.setCzyid(Long.valueOf(psCzyId)); //操作人ID
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setBczyid(poDZZJ_CZYXXB.getCzyid()); //被操作员ID
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
          lstReturn.add(poReturn);
        }
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

  /**
   * 查询操作员信息
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryOperater(String psSql, VoPage voPage) throws
      ServiceException, DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();
    StringBuffer sSql = new StringBuffer();
    StringBuffer sWhereSql = new StringBuffer();
    StringBuffer sCountSql = new StringBuffer();
    List lstCzyxx = null;
    //生成条件语句
    sWhereSql.append("from PoDZZJ_CZYXXB as DZZJ_CZYXXB ");
    if (psSql != null && psSql.trim().length() > 0) {
      sWhereSql.append(" where ").append(psSql);
    }
    //生成计算语句
    if (voPage.getRecordcount() == -1) {
      sCountSql.append("select count(*) ").append(sWhereSql);
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }
    //生成查询语句
    sSql.append("select DZZJ_CZYXXB ").append(sWhereSql);
    //执行查询
    try {
      PojoInfo dzzj_czyxxbDao = DAOFactory.createDZZJ_CZYXXBDAO();
      //开启事务

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(sCountSql.
            toString()));
      }

      lstCzyxx = super.getPageRecords(sSql.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }
    //设置返回信息
    voQueryResult.setPagelist(lstCzyxx);
    voQueryResult.setVotype(PoDZZJ_CZYXXB.class);
    return voQueryResult;
  }

  /**
   * 地址追加业务
   * @param cdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List DzzjYw(CdsVO cdsVo[]) throws ServiceException, DAOException {
    List lstReturn = new ArrayList();
    PoHJXX_CZRKJBXXB poHJXX_CZRKJBXXB = null;
    PoDZZJ_DZZJRZB poDZZJ_DZZJRZB = new PoDZZJ_DZZJRZB();
    PoZJXX_JMSFZXXB poZJXX_JMSFZXXB = new PoZJXX_JMSFZXXB();
    PoZJXX_JMSFZXXB poZjxxReturn = null;

    try {
      PojoInfo DZZJ_DZZJRZBDAO = DAOFactory.createDZZJ_DZZJRZBDAO();

      //开始事务

      if (cdsVo != null && cdsVo.length > 0) {

        for (int i = 0; i < cdsVo.length; i++) {
          PoDZZJ_DZZJRZB poReturn[] = new PoDZZJ_DZZJRZB[cdsVo.length];
          String sRynbid = (String) cdsVo[i].getMap().get("rynbid");
          String sGmsfhm = (String) cdsVo[i].getMap().get("gmsfhm");
          String sCzyId = (String) cdsVo[i].getMap().get("czyid");
          String sYhid = (String) cdsVo[i].getMap().get("yhid");
          String sZjdz = (String) cdsVo[i].getMap().get("zjdz");
          String sSldw = (String) cdsVo[i].getMap().get("sldw");
          if (sGmsfhm == null || "".equals(sGmsfhm.trim())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要追加的人员公民身份号码,地址追加业务无法完成.", null);
          }
          //根据RYNBID从常表中获取人员信息
          PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.
              createHJXX_CZRKJBXXBDAO();
              /*         poHJXX_CZRKJBXXB = hjxx_czrkjbxxbDAO.findHJXX_CZRKJBXXBById(Long.
                         valueOf(sRynbid));
                     if (poHJXX_CZRKJBXXB == null) {
           throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                 "没有找到要追加的人员信息,地址追加业务无法完成.", null);
                     }
           */
          //重号不允许追加地址
          List lstCzrkjbxx = super.findAllByHQL(
              " from PoHJXX_CZRKJBXXB where gmsfhm='" +
              sGmsfhm + "' and jlbz='1' and cxbz='0' and ryzt='0'");
          if (lstCzrkjbxx == null || lstCzrkjbxx.size() <= 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要追加的人员信息,地址追加业务无法完成.", null);
          }
          else
          if (lstCzrkjbxx.size() > 1) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "此人重号,地址追加业务无法完成.", null);
          }
          else {
            poHJXX_CZRKJBXXB = (PoHJXX_CZRKJBXXB) lstCzrkjbxx.get(0);
            if (!String.valueOf(poHJXX_CZRKJBXXB.getRynbid()).equals(sRynbid)) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         "公民身份号码和rynbid不一致,地址追加业务无法完成.", null);
            }
          }
          //根据人员ID和证件类别判断ZJXX_JMSFZXXB中是否存在此信息，
          //如果不存在，则增加，同时更新常表中的记录
          //如果存在，更新其地址信息,包括卡体管理号
          BeanUtils.populate(poZJXX_JMSFZXXB, cdsVo[i].getMap());
          poZJXX_JMSFZXXB.setXm(poHJXX_CZRKJBXXB.getXm());
          poZJXX_JMSFZXXB.setCsrq(poHJXX_CZRKJBXXB.getCsrq());
          poZJXX_JMSFZXXB.setGmsfhm(poHJXX_CZRKJBXXB.getGmsfhm());
          poZJXX_JMSFZXXB.setMz(poHJXX_CZRKJBXXB.getMz());
          poZJXX_JMSFZXXB.setXb(poHJXX_CZRKJBXXB.getXb());
          poZJXX_JMSFZXXB.setRyid(poHJXX_CZRKJBXXB.getRyid());
          poZJXX_JMSFZXXB.setZjlb(ZjConstant.ZJ_ZJLB_EDZ);
          poZJXX_JMSFZXXB.setZjzt(ZjConstant.ZJ_ZJZT_ZC);
          poZJXX_JMSFZXXB.setZjlx(ZjConstant.SFZXX_ZJLX_DZZJ);
          poZJXX_JMSFZXXB.setZjsj(StringUtils.getServiceTime());

          PojoInfo zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
          List sfzxxList = super.findAllByHQL(
              "from PoZJXX_JMSFZXXB where ryid=" + poHJXX_CZRKJBXXB.getRyid() +
              " and zjlb='" + ZjConstant.ZJ_ZJLB_EDZ + "'");
          if (sfzxxList == null || sfzxxList.size() <= 0) {
            //如果没有，则在身份证信息表中增加
            poZJXX_JMSFZXXB.setNbsfzid( (Long) zjxx_jmsfzxxbDAO.getId());
            poZjxxReturn = (PoZJXX_JMSFZXXB)super.create(poZJXX_JMSFZXXB);
            //更新常表中的证件信息
            poHJXX_CZRKJBXXB.setNbsfzid(poZjxxReturn.getNbsfzid());
            poHJXX_CZRKJBXXB.setYxqxjzrq(poZjxxReturn.getYxqxjzrq());
            poHJXX_CZRKJBXXB.setYxqxqsrq(poZjxxReturn.getYxqxqsrq());
            poHJXX_CZRKJBXXB.setQfjg(poZjxxReturn.getQfjg());
            poHJXX_CZRKJBXXB.setZjlb(poZjxxReturn.getZjlb());
            poHJXX_CZRKJBXXB = (PoHJXX_CZRKJBXXB)super.update(
                poHJXX_CZRKJBXXB);
          }
          else {
            //如果有,直接更新身份证信息表
            PoZJXX_JMSFZXXB po = ( (PoZJXX_JMSFZXXB) sfzxxList.get(0));
            poZJXX_JMSFZXXB.setNbsfzid(po.getNbsfzid());
            BeanUtils.copyProperties(po, cdsVo[i].getMap());
            poZjxxReturn = (PoZJXX_JMSFZXXB)super.update(po);
          }

          //增加信息到地址追加日志表中
          BeanUtils.copyProperties(poDZZJ_DZZJRZB, poHJXX_CZRKJBXXB);
          poDZZJ_DZZJRZB.setCzip(BaseContext.getUser().getIp());
          poDZZJ_DZZJRZB.setCzsj(StringUtils.getServiceTime());
          poDZZJ_DZZJRZB.setCzyid(Long.valueOf(sCzyId));
          poDZZJ_DZZJRZB.setDzzjid( (Long) DZZJ_DZZJRZBDAO.getId());
          poDZZJ_DZZJRZB.setYhid(Long.valueOf(sYhid));
          poDZZJ_DZZJRZB.setZjdz(sZjdz);
          poDZZJ_DZZJRZB.setSldw(sSldw);
          poDZZJ_DZZJRZB.setKtglh(poZJXX_JMSFZXXB.getKtglh());
          poReturn[i] = (PoDZZJ_DZZJRZB)super.create(poDZZJ_DZZJRZB);
          lstReturn.add(poReturn);
        }
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

  /**
   * 查询地址追加信息
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryDzzjxx(String psSql, VoPage voPage) throws
      ServiceException, DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();
    StringBuffer sSql = new StringBuffer();
    StringBuffer sWhereSql = new StringBuffer();
    StringBuffer sCountSql = new StringBuffer();
    List lstDzzjxx = null;
    //生成条件语句
    sWhereSql.append(" from PoZZZJ_ZZZJRZB as ZZZJ_ZZZJRZB where 1=1 ");

    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_ZZZJ_ZZZJRZCX);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        sWhereSql.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //数据范围end

    if (psSql != null && psSql.trim().length() > 0) {
      sWhereSql.append(" and ").append(psSql);
    }
    //生成计算语句
    if (voPage.getRecordcount() == -1) {
      sCountSql.append("select count(*) ").append(sWhereSql);
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }
    //生成查询语句
    sSql.append("select ZZZJ_ZZZJRZB ").append(sWhereSql);
    //执行查询
    try {
      PojoInfo zzzj_zzzjrzbDAO = DAOFactory.createZZZJ_ZZZJRZBDAO();

      //开启事务
   
      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(sCountSql.
            toString()));
      }

      lstDzzjxx = super.getPageRecords(sSql.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }
    //设置返回信息
    List lstReturn = new ArrayList();
    PoZZZJ_ZZZJRZB poZZZJ_ZZZJRZB = null;

    for (int i = 0; i < lstDzzjxx.size(); i++) {
      poZZZJ_ZZZJRZB = (PoZZZJ_ZZZJRZB)lstDzzjxx.get(i);
      VoZzzjrzbxx voZzzjxx = new VoZzzjrzbxx(poZZZJ_ZZZJRZB);
      lstReturn.add(voZzzjxx);

    }
    voQueryResult.setPagelist(lstReturn);
    voQueryResult.setVotype(VoZzzjrzbxx.class);



//    VoQueryResult voQueryResult = new VoQueryResult();
//    StringBuffer sSql = new StringBuffer();
//    StringBuffer sWhereSql = new StringBuffer();
//    StringBuffer sCountSql = new StringBuffer();
//    List lstDzzjxx = null;
//    //生成条件语句
//    sWhereSql.append(" from PoDZZJ_DZZJRZB as DZZJ_DZZJRZB,PoDZZJ_CZYXXB as DZZJ_CZYXXB where DZZJ_DZZJRZB.czyid=DZZJ_CZYXXB.czyid ");
//    if (psSql != null && psSql.trim().length() > 0) {
//      sWhereSql.append(" and ").append(psSql);
//    }
//    //生成计算语句
//    if (voPage.getRecordcount() == -1) {
//      sCountSql.append("select count(*) ").append(sWhereSql);
//    }
//    else {
//      voQueryResult.setRecordcount(voPage.getRecordcount());
//    }
//    //生成查询语句
//    sSql.append("select DZZJ_DZZJRZB,DZZJ_CZYXXB ").append(sWhereSql);
//    //执行查询
//    try {
//      DZZJ_DZZJRZBDAO dzzj_dzzjrzbDao = DAOFactory.createDZZJ_DZZJRZBDAO();
//      //开启事务
//      this.beginTransaction();
//
//      if (voPage.getRecordcount() == -1) {
//        voQueryResult.setRecordcount(dzzj_dzzjrzbDao.getCount(sCountSql.
//            toString()));
//      }
//
//      lstDzzjxx = dzzj_dzzjrzbDao.findOnePageDZZJ_DZZJRZBs(sSql.
//          toString(),
//          voPage.getPageindex(),
//          voPage.getPagesize());
//
//      this.commitTransaction();
//    }
//    catch (DAOException ex) {
//      this.rollbackTransaction();
//      throw ex;
//    }
//    //设置返回信息
//    Object[] objCzrzRtn = null;
//    List lstReturn = new ArrayList();
//    PoDZZJ_DZZJRZB poDZZJ_DZZJRZB = null;
//    PoDZZJ_CZYXXB poDZZJ_CZYXXB = null;
//
//    for (int i = 0; i < lstDzzjxx.size(); i++) {
//      objCzrzRtn = (Object[]) lstDzzjxx.get(i);
//      poDZZJ_DZZJRZB = (PoDZZJ_DZZJRZB) objCzrzRtn[0];
//      poDZZJ_CZYXXB = (PoDZZJ_CZYXXB) objCzrzRtn[1];
//      VoZzzjxx voZzzjxx = new VoZzzjxx(poDZZJ_DZZJRZB, poDZZJ_CZYXXB);
//      lstReturn.add(voZzzjxx);
//
//    }
//    voQueryResult.setPagelist(lstReturn);
//    voQueryResult.setVotype(VoZzzjxx.class);


    return voQueryResult;

  }

  /**
   * 查询卡体管理日志
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryCzrzxx(String psSql, VoPage voPage) throws
      ServiceException, DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();
    StringBuffer sSql = new StringBuffer();
    StringBuffer sWhereSql = new StringBuffer();
    StringBuffer sCountSql = new StringBuffer();
    List lstCzrzxx = null;
    //生成条件语句
    sWhereSql.append(" from PoDZZJ_CZRZB as DZZJ_CZRZB, PoDZZJ_CZYXXB as DZZJ_CZYXXB,PoDZZJ_CZYXXB as DZZJ_BCZYXXB where DZZJ_CZRZB.czyid=DZZJ_CZYXXB.czyid and  DZZJ_CZRZB.bczyid=DZZJ_BCZYXXB.czyid");
    if (psSql != null && psSql.trim().length() > 0) {
      sWhereSql.append(" and ").append(psSql);
    }
    //生成计算语句
    if (voPage.getRecordcount() == -1) {
      sCountSql.append("select count(*) ").append(sWhereSql);
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }
    //生成查询语句
    sSql.append("select DZZJ_CZRZB,DZZJ_CZYXXB,DZZJ_BCZYXXB ").append(sWhereSql);
    //执行查询
    try {
      PojoInfo dzzj_czrzbDao = DAOFactory.createDZZJ_CZRZBDAO();
      //开启事务

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(sCountSql.
            toString()));
      }

      lstCzrzxx = super.getPageRecords(sSql.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }
    //设置返回信息
    Object[] objCzrzRtn = null;
    List lstReturn = new ArrayList();
    PoDZZJ_CZRZB poDZZJ_CZRZB = null;
    PoDZZJ_CZYXXB poDZZJ_CZYXXB = null;
    PoDZZJ_CZYXXB poDZZJ_BCZYXXB = null;
    for (int i = 0; i < lstCzrzxx.size(); i++) {
      objCzrzRtn = (Object[]) lstCzrzxx.get(i);
      poDZZJ_CZRZB = (PoDZZJ_CZRZB) objCzrzRtn[0];
      poDZZJ_CZYXXB = (PoDZZJ_CZYXXB) objCzrzRtn[1];
      poDZZJ_BCZYXXB = (PoDZZJ_CZYXXB) objCzrzRtn[2];
      VoZzzjRzxx voZzzjxx = new VoZzzjRzxx(poDZZJ_CZRZB, poDZZJ_CZYXXB,
                                           poDZZJ_BCZYXXB);
      lstReturn.add(voZzzjxx);

    }
    voQueryResult.setPagelist(lstReturn);
    voQueryResult.setVotype(VoZzzjRzxx.class);
    return voQueryResult;
  }

  /**
   * 更换管理员
   * @param poCzy
   * @param psGhCzyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ChangeAdmin(CdsVO cdsVo[]) throws
      ServiceException, DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo dzzj_czyxxbDAO = DAOFactory.createDZZJ_CZYXXBDAO();
      //开始事务

      //更换操作员
      if (cdsVo != null && cdsVo.length > 0) { //如果有操作员信息
        PoDZZJ_CZYXXB poReturn[] = new PoDZZJ_CZYXXB[cdsVo.length];
        for (int i = 0; i < cdsVo.length; i++) {

          //查找被更换操作员信息
          String sBghCzyid = cdsVo[i].getMap().get("bghczyid").toString();
          PoDZZJ_CZYXXB poGhCZYXXB = super.get(PoDZZJ_CZYXXB.class, Long.valueOf(sBghCzyid));
          if (poGhCZYXXB == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要更换的操作员信息," + cdsVo[i].getMap().get("bghczyid"), null);
          }
          //删除要更换的管理员
          //将管理员的的操作员状态改为1
          poGhCZYXXB.setCzyzt(DzzjConstant.ZT_ZX); //状态设为注销
          poGhCZYXXB.setZxsj(StringUtils.getServiceTime()); //设置注销时间
          poGhCZYXXB = (PoDZZJ_CZYXXB)super.update(poGhCZYXXB);

          //增加新的操作员
          PoDZZJ_CZYXXB poCZYXXB = new  PoDZZJ_CZYXXB();
          BeanUtils.copyProperties(poCZYXXB,poGhCZYXXB);
          BeanUtils.populate(poCZYXXB, cdsVo[i].getMap());
          poCZYXXB.setKtglhdn(poCZYXXB.getCzysfzglh().substring(27));
          poCZYXXB.setCzyid( (Long) dzzj_czyxxbDAO.getId());
          poCZYXXB.setCzyzt(DzzjConstant.ZT_ZC);
          poCZYXXB.setZxsj(null);
          poCZYXXB.setZcsj(StringUtils.getServiceTime());
          poReturn[i] = (PoDZZJ_CZYXXB)super.create(poCZYXXB);

          //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_GHGLY); //操作动作
          poDZZJ_CZRZB.setCzyid(Long.valueOf(sBghCzyid)); //被操作人ID
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setBczyid(poReturn[i].getCzyid());
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
        }
        lstReturn.add(0, poReturn);
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

  /**
   * 解锁
   * @param psCzyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List FreeLock(List lstCzyId) throws ServiceException, DAOException {
    List lstReturn = new ArrayList();
    try {
       //开始事务

      //解锁
      //只需要保存解锁的日志就可以
      if (lstCzyId != null && lstCzyId.size() > 0) { //如果有操作员信息
        PoDZZJ_CZYXXB poReturn[] = new PoDZZJ_CZYXXB[lstCzyId.size()];
        for (int i = 0; i < lstCzyId.size(); i++) {
           //记录日志
          PoDZZJ_CZRZB poDZZJ_CZRZB = new PoDZZJ_CZRZB();
          poDZZJ_CZRZB.setRzid( (Long) dzzj_czrzbDAO.getId()); //id
          poDZZJ_CZRZB.setCzdz(DzzjConstant.CZDZ_JS); //操作动作
          poDZZJ_CZRZB.setCzyid( (Long) lstCzyId.get(i)); //被操作人ID
          poDZZJ_CZRZB.setCzip(BaseContext.getUser().getIp()); //操作IP
          poDZZJ_CZRZB.setCzsj(StringUtils.getServiceTime()); //操作时间
          poDZZJ_CZRZB.setBczyid((Long) lstCzyId.get(i));
          WriteRz(poDZZJ_CZRZB);
          //保存日志信息结束
         poReturn[i]=new  PoDZZJ_CZYXXB();
         poReturn[i].setCzyid((Long) lstCzyId.get(i));
        }
       lstReturn.add(0,poReturn);
      }
      ////处理结束
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstReturn;
  }

}
