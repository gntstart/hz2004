/* Generated by Together */

package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.type.PackXQL;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.wsstruts.vo.*;
import java.util.*;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.text.*;

/**
 * 一代证业务类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 * @since history
 *   2004-06-14 一代证受理信息表中添加字段RYID，根据该ID校验受理信息的时效性判断
 *   2004-08-05  由于有表结构进行了修改，一代证受理时，将居民身份证信息表插入常住人口基本信息表中
 *   2004-08-05  一代证受理时，换算签发日期和有效期限，到居民身份证信息表中的有效期限起始日期和有效期限截止日期
 *
 */
@Service
public class Zj1ServiceImpl
    extends ZjBaseService
    implements Zj1Service {
  //日志处理
  protected static Log _log = LogFactory.getLog(Zj1ServiceImpl.class);

  /**
   * 处理证件受理业务
   * @param voYdzZjslxx[]  - 一代证证件受理信息
   * @return com.hzjc.hz2004.vo.VoYdzZjslywfhxx
   * @roseuid 40593B850264
   */
  public VoYdzZjslywfhxx processZjslyw(VoYdzZjslxx[] voYdzZjslxx) throws
      ServiceException, DAOException {
    if (voYdzZjslxx == null || (voYdzZjslxx != null && voYdzZjslxx.length <= 0)) {
      //throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
      //                         "证件受理信息不完整,没有要办理的证件受理信息。", null);
      return null;
    }
    //_log.info("一代证证件受理业务_开始");
    VoYdzZjslywfhxx voYdzZjslywfhxx = null;
    try {
      VoYdzZjslfhxx[] arrayVoYdzZjslfhxxs = new VoYdzZjslfhxx[voYdzZjslxx.
          length];

      //////////////////////////////////////////////////////////////////////
      //开始事务
      ///////////////////////////////////////////////////////////////////////
      //_log.info("开始事务");

      //创建调用的DAO
      PojoInfo  zjyw_ydzslbDAO = DAOFactory.createZJYW_YDZSLBDAO();
      PojoInfo  zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();

      ///////////////////////////////////////////////////////////////////////////
      //1、业务限制判断
      ///////////////////////////////////////////////////////////////////////////
      VerifyBusinessLimit(PublicConstant.GNBH_ZJ_1DZSLYW, voYdzZjslxx);
      //_log.info("1、业务限制判断");
      ////////////////////////////////////////////////////////////////

      //////////////////////////////////////////////////////////////////////
      //1、数据范围
      //////////////////////////////////////////////////////////////////////
      for (int i = 0; i < voYdzZjslxx.length; i++) {
        VoYdzZjslxx aVoYdzZjslxx = voYdzZjslxx[i];
        Long lRynbid = aVoYdzZjslxx.getRynbid();

        /////////////////////////////////////////////////////////////////////////////
        //数据范围权限限制(modify by kgb 2004-06-1)
        //////////////////////////////////////////////////////////////////////////////
        //_log.info("业务提交，数据范围权限限制判断：");
        if (!VerifyDataRangeByRynbid(PublicConstant.GNBH_ZJ_1DZSLYW, lRynbid)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                     "一代证件受理业务数据范围受到限制，不能办理！", null);
        }

        /////////////////////////////////////////////////////////////////
        //得到受理信息对应的常住人口基本信息
        /////////////////////////////////////////////////////////////////
        PoHJXX_CZRKJBXXB poHJXX_CZRKJBXXB = super.
            get(PoHJXX_CZRKJBXXB.class,lRynbid); //根据人员内部ID,在数据库中refresh取得对应的常住人口基本信息PO
        if (poHJXX_CZRKJBXXB == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该受理业务对应的常住人口基本信息不存在。", null);
        }

        ////////////////////////////////////////////////////////////////////
        //重号判断(先判断系统控制参数)
        /////////////////////////////////////////////////////////////////////
        //_log.info("重号人员校验");
        checkYdzslChpd(new VoHJXX_CZRKJBXXB(poHJXX_CZRKJBXXB));

        //数据时效性判断
        StringBuffer strBufYslxx = new StringBuffer();
        strBufYslxx.append("from PoZJYW_YDZSLB where ryid=")
            .append(String.valueOf(poHJXX_CZRKJBXXB.getRyid()))
            .append(" and dkdy='").append(HjConstant.DKDY_WDY).append("'");
        //查询是否有已经办理，且底卡未打印的证件受理业务
        List lstYslxxPos =super.findAllByHQL(strBufYslxx.
            toString());
        /*注释于闵红斌(2004/10/12)
                 //如果有抛出异常
                 if (lstYslxxPos != null && !lstYslxxPos.isEmpty()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该人已经办理了一笔证件受理业务，底卡打印后方可再办理。", null);
                 }
         */
        Long lZjywid = (Long) zjls_sfzywczbDAO.getId(); //得到证件业务ID

        /////////////////////////////////////////////////////////////////////
        //2、保存证件受理信息
        /////////////////////////////////////////////////////////////////////
        PoZJYW_YDZSLB poZJYW_YDZSLB = new PoZJYW_YDZSLB();
        try {
          BeanUtils.copyProperties(poZJYW_YDZSLB, aVoYdzZjslxx);
          if (poZJYW_YDZSLB.getQfrq() == null ||
              (poZJYW_YDZSLB.getQfrq() != null &&
               "".equals(poZJYW_YDZSLB.getQfrq()))) {
            poZJYW_YDZSLB.setQfrq(StringUtils.formateDate()); //签发日期,客户端未传，填当前日期
          }
        }
        catch (Exception ex) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "将客户端传入的参数转换装载到一代证受理信息中发生异常。", ex);
        }
        //其他字段值的存取
        ////////////////////////////////////////////////////////////
        //身份证地址处理
        //////////////////////////////////////////////////////////
        String[] arraySfzZzs = splitSfzZz(aVoYdzZjslxx.getRynbid());
        poZJYW_YDZSLB.setZz1(arraySfzZzs[0]);
        poZJYW_YDZSLB.setZz2(arraySfzZzs[1]);
        poZJYW_YDZSLB.setZz3(arraySfzZzs[2]);
        /////////////////////////////////////////////////////////
        poZJYW_YDZSLB.setZjywid(lZjywid); //证件业务ID
        if (poZJYW_YDZSLB.getZpid() == null) { //根据照片ID判断数据是否完整
          poZJYW_YDZSLB.setBlbz(ZjConstant.ZJ_BLBZ_1ID_CS); //办理标志
        }
        else {
          poZJYW_YDZSLB.setBlbz(ZjConstant.ZJ_BLBZ_1ID_SJWZ); //办理标志
        }
        poZJYW_YDZSLB.setRyid(poHJXX_CZRKJBXXB.getRyid());
        poZJYW_YDZSLB.setDkdy(HjConstant.DKDY_WDY); //底卡打印
        Long lBzslid = (Long) zjyw_ydzslbDAO.getId();
        poZJYW_YDZSLB.setBzslid(lBzslid); //办证受理ID
        //_log.info("2、保存证件受理信息");
        super.create(poZJYW_YDZSLB); //插入一代证受理表
        //给证件受理数组信息赋值
        arrayVoYdzZjslfhxxs[i] = new VoYdzZjslfhxx(lBzslid, lRynbid);

        /////////////////////////////////////////////////////////////////////
        //3、保存一代证居民身份证信息
        /////////////////////////////////////////////////////////////////////
        PoZJXX_JMSFZXXB poZJXX_JMSFZXXB = new PoZJXX_JMSFZXXB();
        try { //将常住人口的信息赋值拷贝过来
          BeanUtils.copyProperties(poZJXX_JMSFZXXB, poHJXX_CZRKJBXXB); //常口信息拷贝
          BeanUtils.copyProperties(poZJXX_JMSFZXXB, poZJYW_YDZSLB); //一代证受理信息拷贝
        }
        catch (InvocationTargetException ex2) {
        }
        catch (IllegalAccessException ex2) {
        }
        poZJXX_JMSFZXXB.setZjlb(ZjConstant.ZJ_ZJLB_YDZ); //一代证件
        poZJXX_JMSFZXXB.setZjzt(ZjConstant.ZJ_ZJZT_ZC); //证件状态
        /////////////////////////////////////////////////////////////
        //add by kgb 2004-08-05
        //根据一代证的签发日期和有效期限，计算有效期限起始日期和有效期限截止日期
        String strQfrq = poZJYW_YDZSLB.getQfrq();
        String strYxqx = poZJYW_YDZSLB.getYxqx();
        String strYxqxjzrq = getYxqxjzrq(strQfrq, strYxqx);
        poZJXX_JMSFZXXB.setYxqxqsrq(strQfrq); //有效期限起始日期
        poZJXX_JMSFZXXB.setYxqxjzrq(strYxqxjzrq); //有效期限截止日期

        Long lNbsfzid = (Long) zjxx_jmsfzxxbDAO.getId();
        poZJXX_JMSFZXXB.setNbsfzid(lNbsfzid);
        //_log.info("3、保存一代证居民身份证信息");
        super.create(poZJXX_JMSFZXXB); //插入居民身份信息

        //////////////////////////////////////////////////////////////////////////
        //修改常住人口基本信息表，如果已经是二代证则不变 (2005/06/07 13:18:00)
        if (!ZjConstant.ZJ_ZJLB_EDZ.equals(poHJXX_CZRKJBXXB.getZjlb())) {
          poHJXX_CZRKJBXXB.setNbsfzid(lNbsfzid);
          poHJXX_CZRKJBXXB.setZjlb(ZjConstant.ZJ_ZJLB_YDZ);
          poHJXX_CZRKJBXXB.setYxqxqsrq(strQfrq);
          poHJXX_CZRKJBXXB.setYxqxjzrq(strYxqxjzrq);
         super.update(poHJXX_CZRKJBXXB);
        }

        ////////////////////////////////////////////////////////////////////
        //4、保存身份证业务操作表
        ////////////////////////////////////////////////////////////////////
        //edit by kgb 2004-08-05，插入操作表记录没有意义
        //_log.info("5、保存身份证业务操作表");
        saveSfzywlsXtrz(lZjywid, PublicConstant.GNBH_ZJ_1DZSLYW, null, null);
      }

      /////////////////////////////////////////////////////////////////////
      //提交事务
      /////////////////////////////////////////////////////////////////////
      //_log.info("提交事务");

      ///////////////////////////////////////////////////////////////////
      //6、返回信息处理
      ////////////////////////////////////////////////////////////////////
      voYdzZjslywfhxx = new VoYdzZjslywfhxx();
      //voYdzZjslywfhxx.setZjywid(lZjywid);
      voYdzZjslywfhxx.setVoYdzZjslfhxx(arrayVoYdzZjslfhxxs);

      //_log.info("一代证受理业务_结束");
    }
    catch (ServiceException ex1) {
      throw ex1;
    }
    catch (DAOException ex1) {
      throw ex1;
    }

    return voYdzZjslywfhxx;
  }

  /**
   * 查询一代证受理信息
   * @param strHQL - 查询条件VO
   * @param vopage - 分页信息VO
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZjslxx(String strHQL, VoPage vopage) throws
      ServiceException, DAOException {
    //调用超类的查询方法。
    return executeQuery(strHQL, vopage, PoV_ZJ_YDZSLXXB.class,
                        PublicConstant.GNBH_ZJ_1DZSLXXCX);
    /*
         VoQueryResult voQryResult = new VoQueryResult();
         try {
      ///////////////////////////////////////////////////////////////
      //1、判断传入的参数vopage
      ///////////////////////////////////////////////////////////////
      int iPageOffset = vopage.getPageindex();
      int iPageSize = vopage.getPagesize();
      long iRecordCount = vopage.getRecordcount();
      //创建组织HQL的Buffer
      StringBuffer strBufHQLFromWhere = new StringBuffer(100);
      ////////////////////////////////////////////////////////////////
      //2、组织select语句部分
      ///////////////////////////////////////////////////////////////
      String strHQLSelectCount = " select count(*) ";
      String strHQLSelect = " select V_ZJ_YDZSLXXB ";
      /////////////////////////////////////////////////////////////////
      ////////////////////////////////////////////////////////////////
      //3、组织from * where *语句部分
      ////////////////////////////////////////////////////////////////
      strBufHQLFromWhere.append(
          " from PoV_ZJ_YDZSLXXB as  V_ZJ_YDZSLXXB where 1=1 ");
      //数据范围???
      //组合传入的条件限制语句
      if (strHQL != null && !strHQL.trim().equals("")) {
        strBufHQLFromWhere.append(" and ");
        strBufHQLFromWhere.append(strHQL);
      }
      //////////////////////////////////////////////////////////////////
      //4、调用DAO查询
      //////////////////////////////////////////////////////////////////
      //开始事务
      //创建调用的DAO
      PojoInfo  dao = DAOFactory.createV_ZJ_YDZSLXXBDAO();
      List lstPos = null;
      long lCount = iRecordCount;
      String strHQLAll = strHQLSelect.concat(strBufHQLFromWhere.toString());
      //_log.info("一代证受理信息查询HQL=" + strHQLAll);
      //查询返回一页记录
      lstPos =super.getPageRecords(
          strHQLAll, iPageOffset, iPageSize);
      //如果传入的记录数为-1，则要查询返回总记录数
      if (iRecordCount == -1) {
        strHQLAll = strHQLSelectCount.concat(strBufHQLFromWhere.toString());
        lCount  = super.getCount(strHQLAll);
      }
      //提交事务
      /////////////////////////////////////////////////////////////////
      //5、返回值初始化
      /////////////////////////////////////////////////////////////////
      voQryResult.setPagelist(lstPos);
      voQryResult.setVotype(PoV_ZJ_YDZSLXXB.class);
      voQryResult.setRecordcount(lCount);
         }
         catch (DAOException ex) {
      //回滚事务
      throw ex;
         }
         catch (ServiceException ex) {
      throw ex;
         }
         return voQryResult;
     */
  }

  /**
   * 一代证底卡打印业务
   * @param lBzslid - 办证受理ID
   * @throws ServiceException
   * @throws DAOException
   */
  public void processDkdyyw(Long lBzslid) throws ServiceException, DAOException {
    if (lBzslid == null) {
      //throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
      //                         "传入的办证受理ID为空。", null);
      return;
    }
    //_log.info("底卡打印业务_开始");
    try {
      /////////////////////////////////////////////////////////////////
      //开始事务
      //////////////////////////////////////////////////////////////////
      //_log.info("开始事务");

      ///////////////////////////////////////////////////////////////////////////
      //1、业务限制判断
      ///////////////////////////////////////////////////////////////////////////
      //VerifyBusinessLimit(PublicConstant.GNBH_ZJ_1DZDKDYYW, );
      ////_log.info("0、业务限制判断");
      ////////////////////////////////////////////////////////////////

      ///////////////////////////////////////////////////////////////////
      //1、根据内部受理ID查找受理信息
      ///////////////////////////////////////////////////////////////////
      PojoInfo  zjyw_ydzslbDAO = DAOFactory.createZJYW_YDZSLBDAO();
      PojoInfo  zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();

      //_log.info("1、根据内部受理ID查找受理信息");
      PoZJYW_YDZSLB poZJYW_YDZSLB  = super.get(PoZJYW_YDZSLB.class,lBzslid);
      if (poZJYW_YDZSLB == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "该要打印的底卡的受理信息不存在。", null);
      }

      /////////////////////////////////////////////////////////////////////////////
      //数据范围权限限制(modify by kgb 2004-06-1)
      //////////////////////////////////////////////////////////////////////////////
      //_log.info("业务提交，数据范围权限限制判断：");
      if (!VerifyDataRangeByRynbid(PublicConstant.GNBH_ZJ_1DZDKDYYW,
                                   poZJYW_YDZSLB.getRynbid())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                   "一代证件底卡打印业务数据范围受到限制，不能办理！", null);
      }

      ///////////////////////////////////////////////////////////////////
      //2、修改一代证受理打印标志
      ///////////////////////////////////////////////////////////////////
      poZJYW_YDZSLB.setDkdy(HjConstant.DKDY_YDY); //已打印底卡
      //_log.info("2、修改一代证受理打印标志");
     super.update(poZJYW_YDZSLB); //修改打印标志

      //////////////////////////////////////////////////////////////////
      //3、保存打印记录？？？暂时空着
      //////////////////////////////////////////////////////////////////

      ///////////////////////////////////////////////////////////////////
      //4、保存日志
      ///////////////////////////////////////////////////////////////////
      ////_log.info("4、保存日志");
      //saveXtrz(PublicConstant.GNBH_ZJ_1DZDKDYYW); //一代证底卡打印标志
      //_log.info("4、保存身份证业务操作表 5、保存操作日志");
      Long lZjywid = (Long) zjls_sfzywczbDAO.getId();
      saveSfzywlsXtrz(lZjywid, PublicConstant.GNBH_ZJ_1DZDKDYYW, null, null);

      ////////////////////////////////////////////////////////////////////
      //提交事务
      ////////////////////////////////////////////////////////////////////
      //_log.info("提交事务");

      //_log.info("底卡业务_结束");
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
  }

  /**
   * 一代证受理删除业务
   * @param lBzslid
   * @throws ServiceException
   * @throws DAOException
   */
  public void processZjslscyw(Long lBzslid) throws ServiceException,
      DAOException {
    if (lBzslid == null) {
      //throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
      //                         "传入的办证受理ID为空。", null);
      return;
    }
    //_log.info("一代证受理删除业务_开始");
    try {
      /////////////////////////////////////////////////////////////////
      //开始事务
      //////////////////////////////////////////////////////////////////
      //_log.info("开始事务");

      ///////////////////////////////////////////////////////////////////////////
      //1、业务限制判断
      ///////////////////////////////////////////////////////////////////////////
      //VerifyBusinessLimit(PublicConstant.GNBH_ZJ_1DZDKDYYW, );
      ////_log.info("0、业务限制判断");
      ////////////////////////////////////////////////////////////////

      ///////////////////////////////////////////////////////////////////
      //1、根据内部受理ID查找受理信息
      ///////////////////////////////////////////////////////////////////
      PojoInfo  zjyw_ydzslbDAO = DAOFactory.createZJYW_YDZSLBDAO();
      PojoInfo  zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();

      //_log.info("1、根据内部受理ID查找受理信息");
      PoZJYW_YDZSLB poZJYW_YDZSLB  = super.get(PoZJYW_YDZSLB.class,lBzslid);
      if (poZJYW_YDZSLB == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "要删除的一代证受理信息不存在！", null);
      }
      /* 注释于闵红斌(2004/10/10)
             else if (!HjConstant.DKDY_WDY.equals(poZJYW_YDZSLB.getDkdy())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "该受理信息底卡已打印，不能删除该受理信息！", null);
             }
       */

      /////////////////////////////////////////////////////////////////////////////
      //数据范围权限限制(modify by kgb 2004-06-1)
      //////////////////////////////////////////////////////////////////////////////
      //_log.info("业务提交，数据范围权限限制判断：");
      if (!VerifyDataRangeByRynbid(PublicConstant.GNBH_ZJ_1DZSLSCYW,
                                   poZJYW_YDZSLB.getRynbid())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                   "一代证受理删除业务的数据范围受到限制！", null);
      }

      ///////////////////////////////////////////////////////////////////////////
      //删除一代证受理信息
      super.delete(poZJYW_YDZSLB); //删除一代证受理业务

      ///////////////////////////////////////////////////////////////////
      //4、保存身份证业务操作表 5、保存操作日志
      ///////////////////////////////////////////////////////////////////
      //_log.info("4、保存身份证业务操作表 5、保存操作日志");
      Long lZjywid = (Long) zjls_sfzywczbDAO.getId();
      saveSfzywlsXtrz(lZjywid, PublicConstant.GNBH_ZJ_1DZSLSCYW, null, null);

      ////////////////////////////////////////////////////////////////////
      //提交事务
      ////////////////////////////////////////////////////////////////////
      //_log.info("提交事务");

      //_log.info("一代证受理删除业务_结束");
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
  }

}
