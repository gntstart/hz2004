package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.service.XtsjccService;
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
import com.hzjc.util.StringUtils;

/**
 * 系统数据查错业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
@Service
public class XtsjccServiceImpl
    extends HjCommon
    implements XtsjccService {

  //日志处理
  protected static Log _log = LogFactory.getLog(XtsjccServiceImpl.class);

  /**
   * 重号同步业务
   */
  public List processChtbyw(String strHQL) throws ServiceException,
      DAOException {

    Long hjywid = null;
    List chxxList = null;
    StringBuffer strAllHQL = new StringBuffer();
    String now = StringUtils.getServiceTime();

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      ////////////////////////////////////
      //事务开始

      ///////////////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //得到重号信息
      strAllHQL.append("select max(HJXX_CZRKJBXXB.ryid),HJXX_CZRKJBXXB.gmsfhm from PoHJXX_CZRKJBXXB HJXX_CZRKJBXXB ,PoHJXX_MLPXXXXB HJXX_MLPXXXXB ")
          .append("where HJXX_MLPXXXXB.mlpnbid=HJXX_CZRKJBXXB.mlpnbid ")
          .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
          .append("and HJXX_CZRKJBXXB.ryzt='" + HjConstant.RYZT_ZC + "' ")
          .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ")//增加于2005/02/16 13:45:00
          .append(strHQL != null ? "and " + strHQL + " " : "")
          .append("group by HJXX_CZRKJBXXB.gmsfhm ")
          .append("having count(HJXX_CZRKJBXXB.gmsfhm) > 1 ")
          .append("order by HJXX_CZRKJBXXB.gmsfhm");

      //debug
      //_log.info(strAllHQL.toString());

      List chryList =super.findAllByHQL(strAllHQL.
          toString());
      if (chryList != null && chryList.size() > 0) {
        chxxList = new ArrayList();
        for (int i = 0; i < chryList.size(); i++) {
          Object ob[] = (Object[]) chryList.get(i);
          VoChxx vo = new VoChxx();
          vo.setBchryid( (Long) ob[0]);
          vo.setChsfhm( (String) ob[1]);
          chxxList.add(vo);
        }
        //保存重号信息
        this.saveCHXX(hjywid, chxxList,now);
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid,
                        PublicConstant.GNBH_XT_CHTBYW,
                        PublicConstant.HJYWLS_YWLX_QH,
                        chxxList != null ? chxxList.size() : 0,
                        null,
                        now);

      //////////////////////////////////////
      //事务提交
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }

    return chxxList;
  }

  /**
   * 公民身份号码尾数生成业务
   * @return - 户籍业务ID
   * @throws DAOException
   * @throws ServiceException
   */
  public Long processGmsfhmwsscyw() throws DAOException, ServiceException {

    final int MAX_RECORD = 100;
    String strHQL = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      ////////////////////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////////////
      //得到没有在公民身份号码尾数表中的人员信息并保存
      int allcount = 0;
      while (true) {
        int count = 0;
        int ii = 0;
        //事务开始
        strHQL = "select a from PoHJXX_CZRKJBXXB a where a.jlbz='" +
            PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' and not exists (select b.gmsfhm from PoHJYW_GMSFHMSXMFPXXB b where a.gmsfhm=b.gmsfhm)";
        List ryxxList =super.getPageRecords(strHQL, new Object[]{}, 0,
            MAX_RECORD).getList();
        if (ryxxList != null) {
          for (int i = 0; i < ryxxList.size(); i++) {
            ii++;
            PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(i);
            //处理重号只填其一的需求
            strHQL =
                "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
                poRyxx.getGmsfhm() + "'";
            if (super.getCount(strHQL) > 0) {
              continue;
            }
            //保存尾数信息
            count++;
            PoHJXX_MLPXXXXB poMlpxxxx = super.
                get(PoHJXX_MLPXXXXB.class,
                poRyxx.getMlpnbid());
            PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
            poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
            poFpxx.setRyid(poRyxx.getRyid());
            poFpxx.setCsrq(poRyxx.getCsrq());
            poFpxx.setXm(poRyxx.getXm());
            poFpxx.setGmsfhm(poRyxx.getGmsfhm());
            poFpxx.setXb(poRyxx.getXb());
            poFpxx.setDwdm(poMlpxxxx != null ? poMlpxxxx.getPcs() : null);
            poFpxx.setSxh(poRyxx.getGmsfhm().substring(14, 17));
            poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_QT);
            poFpxx.setHjywid(hjywid);
            super.create(poFpxx);
          }
        }
        //事务提交
        //计数
        allcount += count;
        if (ii < MAX_RECORD) {
          break;
        }
      }

      ////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid,
                        PublicConstant.GNBH_XT_GMSFHMWSSCYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        allcount,
                        null,
                        now);

      //////////////////////////////////////
      //事务提交
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }

    return hjywid;
  }

}
