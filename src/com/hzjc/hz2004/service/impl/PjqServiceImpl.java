package com.hzjc.hz2004.service.impl;

/**
 * 评价器接口业务类
 * <p>Title: </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;

import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.service.PjqService;
import com.hzjc.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.common.HjCommon;
@Service
public class PjqServiceImpl
    extends HjCommon
    implements PjqService {

  /**
   * 评价信息保存
   * @param vodkxx VoSmkdkxxHq[]
   * @throws ServiceException
   * @throws DAOException
   * @return int
   */
  public Long processPjxxbc(String sPjjg,String sPjrlxfs,String sPjkssj) throws ServiceException,
      DAOException {
    Long sPjid = null;
    String strSQL = "";
    List lstYwls,lstZjls,lstZjyw,lstDyxx,lstDysl = null;
    String now = StringUtils.getServiceTime();

    try {
      PojoInfo  ww_pjxxbDao = DAOFactory.createWW_PJXXBDAO();
      PojoInfo  ww_pjxxywlsbDao = DAOFactory.createWW_PJXXYWLSBDAO();
      PojoInfo  hjls_hjywlsbDao = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  zjyw_slxxbDAO = DAOFactory.createZJYW_SLXXBDAO();
      PojoInfo  zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();
      PojoInfo  lssfz_dyxxbDAO = DAOFactory.createLSSFZ_DYXXBDAO();
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();

      if (sPjjg == null && sPjrlxfs == null){
        return sPjid;
      }

      //事务开始

      //获取评价时间段内的业务流水信息
      lstYwls =super.findAllByHQL(" from PoHJLS_HJYWLSB where slrid = "
          + this.getUserInfo().getYhid() + " and slsj >= '" + sPjkssj
          + "' and slsj <= '" + now + "' order by slsj ");
      //获取评价时间段内的证件业务流水信息
      lstZjls =super.findAllByHQL(" from PoZJLS_SFZYWCZB where CZYID = "
          + this.getUserInfo().getYhid() + " and CZSJ >= '" + sPjkssj
          + "' and CZSJ <= '" + now + "' order by CZSJ ");
      //获取评价时间段内的临证打印信息
      lstDyxx =super.findAllByHQL(" from PoLSSFZ_DYXXB where CZYID = "
          + this.getUserInfo().getYhid() + " and CZSJ >= '" + sPjkssj
          + "' and CZSJ <= '" + now + "' order by CZSJ ");


      //保存评价信息
      sPjid = (Long)ww_pjxxbDao.getId();
      PoWW_PJXXB poWw_pjxxb = new PoWW_PJXXB();
      poWw_pjxxb.setPjid(sPjid);
      poWw_pjxxb.setPjjg(sPjjg);
      poWw_pjxxb.setPjrlxfs(sPjrlxfs);
      poWw_pjxxb.setPjkssj(sPjkssj);
      poWw_pjxxb.setPjjssj(now);
      poWw_pjxxb.setBpjrid(this.getUserInfo().getYhid());
      poWw_pjxxb.setBpjrdlm(this.getUserInfo().getYhdlm());
      poWw_pjxxb.setBpjrxm(this.getUserInfo().getYhxm());
      poWw_pjxxb.setBpjdw(this.getUserInfo().getDwdm());
      poWw_pjxxb.setYwzs(new Long((long)(lstYwls.size()+lstZjls.size()+lstDyxx.size())));
      super.create(poWw_pjxxb);

      //保存评价信息业务流水
      for (int i =0; i < lstYwls.size();i++){
        PoHJLS_HJYWLSB pohjls_hjywlsb = (PoHJLS_HJYWLSB)lstYwls.get(i);

        PoWW_PJXXYWLSB poWw_pjxxywlsb = new PoWW_PJXXYWLSB();
        poWw_pjxxywlsb.setPjywid((Long)ww_pjxxywlsbDao.getId());
        poWw_pjxxywlsb.setPjid(sPjid);
        poWw_pjxxywlsb.setHjywid(pohjls_hjywlsb.getHjywid());
        poWw_pjxxywlsb.setYwbz(pohjls_hjywlsb.getYwbz());
        poWw_pjxxywlsb.setYwlx(pohjls_hjywlsb.getYwlx());
        poWw_pjxxywlsb.setCzsm(pohjls_hjywlsb.getCzsm());
        poWw_pjxxywlsb.setSbsj(pohjls_hjywlsb.getSbsj());
        poWw_pjxxywlsb.setSbryxm(pohjls_hjywlsb.getSbryxm());
        poWw_pjxxywlsb.setSbrgmsfhm(pohjls_hjywlsb.getSbrgmsfhm());
        poWw_pjxxywlsb.setSlsj(now);
        poWw_pjxxywlsb.setSldw(this.getUserInfo().getDwdm());
        poWw_pjxxywlsb.setSlrid(this.getUserInfo().getYhid());
        //poWw_pjxxywlsb.setPjjg(sPjjg);
        super.create(poWw_pjxxywlsb);

      }

      //保存评价信息业务流水--证件业务
      for (int i =0; i < lstZjls.size();i++){
        PoZJLS_SFZYWCZB poZJLS_SFZYWCZB = (PoZJLS_SFZYWCZB)lstZjls.get(i);

        lstZjyw =super.findAllByHQL(" from PoZJYW_SLXXB where slh ='"
                  + poZJLS_SFZYWCZB.getSlh() + "'");

        PoWW_PJXXYWLSB poWw_pjxxywlsb = new PoWW_PJXXYWLSB();
        poWw_pjxxywlsb.setPjywid((Long)ww_pjxxywlsbDao.getId());
        poWw_pjxxywlsb.setPjid(sPjid);
        poWw_pjxxywlsb.setHjywid(poZJLS_SFZYWCZB.getZjywid());
        poWw_pjxxywlsb.setYwbz(poZJLS_SFZYWCZB.getYwbz());
        //poWw_pjxxywlsb.setPjjg(sPjjg);
        poWw_pjxxywlsb.setSlsj(now);
        poWw_pjxxywlsb.setSldw(this.getUserInfo().getDwdm());
        poWw_pjxxywlsb.setSlrid(this.getUserInfo().getYhid());

        if (lstZjyw.size() > 0){
          PoZJYW_SLXXB pozjyw_slxxb = (PoZJYW_SLXXB)lstZjyw.get(0);
          poWw_pjxxywlsb.setSbsj(pozjyw_slxxb.getCzsj());
          poWw_pjxxywlsb.setSbryxm(pozjyw_slxxb.getXm());
          poWw_pjxxywlsb.setSbrgmsfhm(pozjyw_slxxb.getGmsfhm());
        }

        super.create(poWw_pjxxywlsb);
      }

      //保存评价信息业务流水--临时身份证打印
      for (int i =0; i < lstDyxx.size();i++){
        PoLSSFZ_DYXXB poLSSFZ_DYXXB = (PoLSSFZ_DYXXB)lstDyxx.get(i);

        lstDysl =super.findAllByHQL(" from PoLSSFZ_SLXXB where lsslid ="
                  + poLSSFZ_DYXXB.getLsslid());

        PoWW_PJXXYWLSB poWw_pjxxywlsb = new PoWW_PJXXYWLSB();
        poWw_pjxxywlsb.setPjywid((Long)ww_pjxxywlsbDao.getId());
        poWw_pjxxywlsb.setPjid(sPjid);
        poWw_pjxxywlsb.setHjywid(poLSSFZ_DYXXB.getLsdyid());
        poWw_pjxxywlsb.setYwbz("F7003");
       // poWw_pjxxywlsb.setPjjg(sPjjg);
        poWw_pjxxywlsb.setSlsj(now);
        poWw_pjxxywlsb.setSldw(this.getUserInfo().getDwdm());
        poWw_pjxxywlsb.setSlrid(this.getUserInfo().getYhid());

        if (lstDysl.size() > 0){
          PoLSSFZ_SLXXB polssfz_slxxb = (PoLSSFZ_SLXXB)lstDysl.get(0);
          poWw_pjxxywlsb.setSbsj(polssfz_slxxb.getCzsj());
          poWw_pjxxywlsb.setSbryxm(polssfz_slxxb.getXm());
          poWw_pjxxywlsb.setSbrgmsfhm(polssfz_slxxb.getGmsfhm());
        }

        super.create(poWw_pjxxywlsb);
      }


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
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return sPjid;
  }

}
