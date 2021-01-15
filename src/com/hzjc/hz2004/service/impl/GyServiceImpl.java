package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.GyService;
import com.hzjc.wsstruts.type.PackXQL;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ResultNotFoundException;
import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.hz2004.dao.*;
import java.util.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.service.impl.XtywqxServiceImpl;
import com.hzjc.wsstruts.dao.hibernate.DefaultDAO;

/**
 * 公用业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
@Service
public class GyServiceImpl
    extends Hz2004BaseService
    implements GyService {

  //日志处理
  protected static Log _log = LogFactory.getLog(GyServiceImpl.class);

  /*
    public void processYwsjxgyw(VoYwsjxgxx voYwsjxgxx){
    }
   */

  /**
   * 通过 户号内部ID 得到
   * 户籍户地信息，户地信息由PoHJXX_HXXB(户信息)+PoHJXX_MLPXXXXB(地信息)组成。
   * @param hhnbid - 户号内部ID
   * @return java.util.List
   * @roseuid 404FC3D80147
   */
  public java.util.List queryHdxx(Long hhnbid) throws ServiceException,
      DAOException {

    PoHJXX_HXXB poHJXX_HXXB = null;
    PoHJXX_MLPXXXXB poHJXX_MLPXXXXB = null;
    ArrayList HdxxList = null;

    try {
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      //执行DAO查询
      poHJXX_HXXB = super.get(PoHJXX_HXXB.class,hhnbid);
      if (poHJXX_HXXB != null) {
        poHJXX_MLPXXXXB = super.get(PoHJXX_MLPXXXXB.class,poHJXX_HXXB.
            getMlpnbid());
      }
    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poHJXX_HXXB != null && poHJXX_MLPXXXXB != null) {
      HdxxList = new ArrayList();
      VoHdxxHqFhxx voHdxx = new VoHdxxHqFhxx(poHJXX_HXXB, poHJXX_MLPXXXXB);
      HdxxList.add(voHdxx);
    }

    return HdxxList;
  }

  /**
   * 通过 门楼牌内部ID 得到 地址冻结状态。
   *
   * 0-没冻结
   * 1-冻结
   * 2-没有启用冻结功能
   * @param mlpnbid - 门楼牌内部ID
   * @return Integer
   * @roseuid 404FC3D8014B
   */
  public Integer getDzdjztByMlpnbID(Long mlpnbid) throws ServiceException,
      DAOException {
    return null;
  }

  /**
   * 通过 人员内部ID 得到 人员状态
   * @param rynbid - 人员内部ID
   * @return String
   * @roseuid 405425C202F7
   */
  public String getRyztByRynbID(Long rynbid) throws ServiceException,
      DAOException {
    return null;
  }

  /**
   * 通过门(楼)牌内部ID得到地址离线锁定状态:
   * 0 - 没锁定
   * 1 - 锁定
   * @param mlpnbid - 门楼牌内部ID
   * @return Boolean
   * @roseuid 4056772E00D8
   */
  public Boolean getDzlxsdztByMlpnbID(Long mlpnbid) throws ServiceException,
      DAOException {
    return null;
  }

  /**
   * 得到人员照片
   *
   * @param ZpID - 照片ID
   * @return java.util.List
   * @roseuid 405C075C0367
   */
  public VoHJXX_RYZPXXB queryRyzp(Long ZpID) throws ServiceException,
      DAOException {

    VoHJXX_RYZPXXB voRyzpxx = null;
    PoHJXX_RYZPXXB poHJXX_RYZPXXB = null;

    //执行DAO查询
    try {
      PojoInfo hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();

      poHJXX_RYZPXXB = super.get(PoHJXX_RYZPXXB.class,ZpID);
      if (poHJXX_RYZPXXB != null) {
        voRyzpxx = new VoHJXX_RYZPXXB(poHJXX_RYZPXXB);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }

    return voRyzpxx;
  }

  /**
   * 得到人员信息，人员信息是由PoHJXX_CZRKJBXXB(人信息) + PoHJXX_HXXB(户信息) + PoHJXX_MLPXXXXB(门（楼）牌详细信息表)组成。
   * @param strHQL
   * @param voPage
   * @param ywbz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRyxx(String strHQL, VoPage voPage, String ywbz) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poRyxxList = null;
    List voRyxxList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), ywbz);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //数据范围end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ");
      strFromHQL.append(strHQL);
    }

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append(
        "select HJXX_CZRKJBXXB ")
        .append(strFromHQL.toString())
        .append(strHQL != null && strHQL.trim().length() > 0 ? "" :
                "order by HJXX_CZRKJBXXB.xm,HJXX_CZRKJBXXB.rynbid");

    //debug info
    _log.info("人员信息查询SQL语句：" + strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }
      poRyxxList = super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poRyxxList != null && poRyxxList.size() > 0) {
      voRyxxList = new ArrayList();
      for (int i = 0; i < poRyxxList.size(); i++) {
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) poRyxxList.get(i);

        VoRyxxHqFhxx voRyxxHqFhxx = new VoRyxxHqFhxx(poRyxx, null, null);
        voRyxxList.add(voRyxxHqFhxx);
      }
    }

    //翻释户主信息
    if (PublicConstant.GNBH_HJ_RYXXHQ_HZ.equals(ywbz) &&
        voRyxxList != null &&
        voRyxxList.size() > 0) {
      try {
        PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.
            createHJXX_CZRKJBXXBDAO();


        for (int i = 0; i < voRyxxList.size(); i++) {
          VoRyxxHqFhxx vo = (VoRyxxHqFhxx) voRyxxList.get(i);
          //户主
          if (PublicConstant.JLBZ_ZX.equals(vo.getJlbz()) &&
              HjConstant.RYZT_ZC.equals(vo.getRyzt())) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and cxbz='" + PublicConstant.CXBZ_FCX +
                "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and hhnbid =" +
                vo.getHhnbid();
          }
          else {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and hhnbid =" +
                vo.getHhnbid() + " and qysj <= '" + vo.getQysj() +
                "' and cxbz='" + PublicConstant.CXBZ_FCX +
                "' order by qysj desc";
          }
          List hzxxList = super.findAllByHQL(strHQL);
          if (hzxxList != null && hzxxList.size() > 0) {
            PoHJXX_CZRKJBXXB po = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            vo.setHzgmsfhm(po.getGmsfhm());
            vo.setHzryid(po.getRyid());
            vo.setHzrynbid(po.getRynbid());
            vo.setHzxb(po.getXb());
            vo.setHzxm(po.getXm());
          }
          //男户成员数
          strHQL = "select count(distinct HJXX_CZRKJBXXB.ryid) from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB where xb='" +
              HjConstant.RYXB_NA + "' and cxbz='" + PublicConstant.CXBZ_FCX +
              "' and hhnbid=" + vo.getHhnbid() + " and ryzt='" +
              HjConstant.RYZT_ZC + "' and jlbz='" + PublicConstant.JLBZ_ZX +
              "' ";
          vo.setNahcys(new Long(super.getCount(strHQL)));
          //女户成员数
          strHQL = "select count(distinct HJXX_CZRKJBXXB.ryid) from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB where xb='" +
              HjConstant.RYXB_NV + "' and cxbz='" + PublicConstant.CXBZ_FCX +
              "' and hhnbid=" + vo.getHhnbid() + " and ryzt='" +
              HjConstant.RYZT_ZC + "' and jlbz='" + PublicConstant.JLBZ_ZX +
              "' ";
          vo.setNvhcys(new Long(super.getCount(strHQL)));
        }

      }
      catch (DAOException ex) {
        throw ex;
      }

    }

    voQueryResult.setPagelist(voRyxxList);
    voQueryResult.setVotype(VoRyxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 户成员信息获取_增强
   * @param hhnbid
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHcyxxEx(Long hhnbid, String strHQL, VoPage voPage) throws
      ServiceException, DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB where HJXX_CZRKJBXXB.rynbid in (select max(a.rynbid) from PoHJXX_CZRKJBXXB as a where a.hhnbid=" +
                      hhnbid.toString() + " group by a.ryid) ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ");
      strFromHQL.append(strHQL);
    }

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select HJXX_CZRKJBXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();

      

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList = super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoHJXX_CZRKJBXXB.class);
    return voQueryResult;
  }

  /**
   * 得到户成员信息
   * @param hhnbid - 户号内部ID
   * @param rynbid - 人员内部ID
   * @param strHQL - HQL
   * @param voPage - 分页信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHcyxx(Long hhnbid, Long rynbid, String strHQL,
                                  VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poHcyxxList = null;
    ArrayList HcyxxList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL
    strFromHQL.append("from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
        .append("where HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.hhnbid=" + hhnbid.toString() + " ");
    //.append("and HJXX_CZRKJBXXB.ryzt ='" + HjConstant.RYZT_ZC + "' ");
    if (rynbid != null) {
      strFromHQL.append("and ( HJXX_CZRKJBXXB.yhzgx < 10 ")
          .append("or HJXX_CZRKJBXXB.rynbid=" + rynbid.toString() + " ) ");
    }

    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ");
      strFromHQL.append(strHQL);
    }

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select HJXX_CZRKJBXXB ")
        .append(strFromHQL.toString());
    if (strHQL == null || "".equals(strHQL)) {
      strAllHQL.append(" order by HJXX_CZRKJBXXB.yhzgx,HJXX_CZRKJBXXB.rynbid ");
    }

    //debug info
    _log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();


      long pagecount = voPage.getRecordcount();

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }

      if(voPage.getHzywid()!=null && !voPage.getHzywid().equals("") ){
        String hql = "from PoHZ_ZJ_SB a where a.id=" + voPage.getHzywid();
        List list = super.findAllByHQL(hql);
        String gmsfhm = "";
        if (list.size() > 0) {
        	PoHZ_ZJ_SB obj = (PoHZ_ZJ_SB) list.get(0);
          if (obj.getPch() != null && !obj.getPch().equals("")) {
            hql = "from PoHZ_ZJ_SB a where a.pch='" + obj.getPch() + "'";
            list = super.findAllByHQL(hql);
          }
        }

        String baksql = strAllHQL.toString();

        //处理户政业务，第一页必须把所有人查询出来
        if(pagecount==-1 && voPage.getPageindex()==0){
          //第一页
          strAllHQL.append(" and (");
          for (int i = 0; i < list.size(); i++) {
        	  PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB) list.get(i);
            if(i>0) strAllHQL.append(" or ");

            strAllHQL.append(" gmsfhm='" + sb.getBsqrsfz() + "' ");
          }
          strAllHQL.append(" )");

          poHcyxxList = super.getPageRecords(strAllHQL.toString(),
        		 new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

          //不足一页
          if(poHcyxxList.size()<voPage.getPagesize()){
            for (int i = 0; i < list.size(); i++) {
            	PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB) list.get(i);
              baksql += " and gmsfhm<>'" + sb.getBsqrsfz() + "' ";
            }
            List poHcyxxList2 = super.getPageRecords(baksql,
            		new Object[]{},
                0,
                voPage.getPagesize()-poHcyxxList.size()).getList();
            poHcyxxList.addAll(poHcyxxList2);
          }
        }else{
          //后续页
          for (int i = 0; i < list.size(); i++) {
        	  PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB) list.get(i);
            strAllHQL.append(" and gmsfhm<>'" + sb.getBsqrsfz() + "' ");
          }

          poHcyxxList = super.getPageRecords(strAllHQL.toString(),
        		  new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();
        }
      }else{
        poHcyxxList = super.getPageRecords(strAllHQL.toString(),
        		new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

      }
    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poHcyxxList != null && poHcyxxList.size() > 0) {
      HcyxxList = new ArrayList();
      for (int i = 0; i < poHcyxxList.size(); i++) {
        PoHJXX_CZRKJBXXB poHJXX_CZRKJBXXB = (PoHJXX_CZRKJBXXB) poHcyxxList.get(
            i);
        VoHcyxxHqFhxx voHcyxxList = new VoHcyxxHqFhxx(poHJXX_CZRKJBXXB);
        HcyxxList.add(voHcyxxList);
      }
    }

    voQueryResult.setPagelist(HcyxxList);
    voQueryResult.setVotype(VoHcyxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 通过人员内部ID判断就是否为工作对象。
   * 返回值：
   * 0 - 没有启用工作对象
   * 1 - 是工作对象(内部)
   * 2 - 不是工作对象(内部)
   * 3 - 是工作对象(外部)
   * 4 - 不是工作对象(外部)
   * @param rynbid - 人员内部ID
   * @return Long
   * @roseuid 404B37730244
   */
  public Long getGzdxpdByRynbID(Long rynbid) throws ServiceException,
      DAOException {
    return null;
  }

  public VoQueryResult queryTable(String tn, String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL
    strFromHQL.append("from Po").append(tn).append(" as ").append(tn).append(
        strHQL != null ?
        " where " + strHQL : "");

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());
    _log.info(strCountHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }

      poList = super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    voQueryResult.setPagelist(poList);
    try {
      voQueryResult.setVotype(Class.forName("com.hzjc.hz2004.po.Po".concat(tn)));
    }
    catch (ClassNotFoundException ex1) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                 "com.hzjc.hz2004.po.Po".concat(tn).concat(
          "不存在。"), ex1);
    }
    if (poList != null && poList.size() > 0) {
      Object ob = poList.get(0);
    }
    return voQueryResult;
  }

}
