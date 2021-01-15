package com.hzjc.hz2004.service.impl;


import com.hzjc.hz2004.service.CxService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.common.PID;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.util.StringUtils;
import org.apache.commons.logging.*;
import org.hibernate.LockOptions;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import java.util.*;

@Service
public class CxServiceImpl
    extends HjCommon
    implements CxService {

  //日志处理
  protected static Log _log = LogFactory.getLog(CxServiceImpl.class);

  /**
   * 户籍恢复业务
   * @param rybdid - 人员变动ID
   * @param sbhjywid - 上笔户籍业务ID
   * @param voBggzxxEx - 变更更正信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjhfywfhxx processHjhfyw(Long rybdid, Long sbhjywid,
                                    VoBggzxxEx[] voBggzxxEx) throws
      ServiceException, DAOException {

    VoHjhfywfhxx voHjhfywfhxx = null;
    Long hjywid = null;
    String strHQL = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    String now = StringUtils.getServiceTime();
    String now1 = (Long.parseLong(now) + 1) + "";

    ////////////////////////////////////////////
    //数据校验
    if (rybdid == null || sbhjywid == null) {
      return null;
    }

    try {
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjyw_swzxxxbDAO = DAOFactory.createHJYW_SWZXXXBDAO();
      PojoInfo hjyw_qczxxxbDAO = DAOFactory.createHJYW_QCZXXXBDAO();
      PojoInfo hjyw_hjscxxbDAO = DAOFactory.createHJYW_HJSCXXBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjyw_hcybdxxbDAO = DAOFactory.createHJYW_HCYBDXXBDAO();

      ///////////////////////////////////////////
      //事务开始
      ////////////////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ///////////////////////////////////////
      //恢复人员变动信息(四变)
      PoHJTJ_RYBDXXB poRybdxx = super.get(PoHJTJ_RYBDXXB.class,rybdid);
      if (poRybdxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到对应的人员变动信息,户籍恢复业务无法完成.", null);
      }
      if (!this.checkHJHFYW(poRybdxx)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "不支持恢复的业务,户籍恢复业务无法完成.", null);
      }
      //判断户籍业务冲销时间范围
      this.checkHJYWCXSJFW(poRybdxx.getSlsj());
      //时效性处理
      super.refresh(poRybdxx, LockOptions.UPGRADE);
      if (!PublicConstant.CXBZ_FCX.equals(poRybdxx.getCxbz())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "该业务已经恢复了,户籍恢复业务无法完成.", null);
      }
      //add by hh 20060301 重号不允许业务恢复
      if (this.countCHRYXX(poRybdxx.getGmsfhm()) >= 1) {
         throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                    "不允许重号人员办理业务恢复，业务恢复无法完成。", null);
       }

      //保存人员变动信息
      poRybdxx.setCxbz(PublicConstant.CXBZ_SJCX);
      poRybdxx.setCxsj(now);
      poRybdxx.setCxrid(this.getUser().getYhid());
      super.update(poRybdxx);

      ///////////////////////////
      //恢复相关业务信息
      //死亡注销
      if (HjConstant.YWNR_SWZX.equals(poRybdxx.getYwnr())) {
        PoHJYW_SWZXXXB poSwzxxx = super.get(PoHJYW_SWZXXXB.class,poRybdxx.
            getBdbid());
        if (poSwzxxx != null) {
          poSwzxxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poSwzxxx.setCxhjywid(hjywid);
          poSwzxxx.setCxrid(this.getUserInfo().getYhid());
          poSwzxxx.setCxsj(now);
          super.update(poSwzxxx);
        }
      }
      //迁出注销
      else if (HjConstant.YWNR_QCZX.equals(poRybdxx.getYwnr())) {
        PoHJYW_QCZXXXB poQczxxx = super.get(PoHJYW_QCZXXXB.class,poRybdxx.
            getBdbid());
        if (poQczxxx != null) {
          poQczxxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poQczxxx.setCxhjywid(hjywid);
          poQczxxx.setCxrid(this.getUserInfo().getYhid());
          poQczxxx.setCxsj(now);
          super.update(poQczxxx);
        }
      }
      //户籍删除
      else if (HjConstant.YWNR_HJSC.equals(poRybdxx.getYwnr())) {
        PoHJYW_HJSCXXB poHjscxx = super.get(PoHJYW_HJSCXXB.class, poRybdxx.
            getBdbid());
        if (poHjscxx != null) {
          poHjscxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poHjscxx.setCxhjywid(hjywid);
          poHjscxx.setCxrid(this.getUserInfo().getYhid());
          poHjscxx.setCxsj(now);
          super.update(poHjscxx);
        }
      }

      ///////////////////////////////////////
      //注销恢复人员的户成员变动信息
      strHQL = "from PoHJYW_HCYBDXXB where hjywid=" + poRybdxx.getHjywid() +
          " and rynbid=" + poRybdxx.getRynbid();
      List hcybdxxList = super.findAllByHQL(strHQL);
      if (hcybdxxList != null && hcybdxxList.size() > 0) {
        for (int i = 0; i < hcybdxxList.size(); i++) {
          PoHJYW_HCYBDXXB poHcybdxx = (PoHJYW_HCYBDXXB) hcybdxxList.get(i);
          poHcybdxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poHcybdxx.setCxhjywid(hjywid);
          poHcybdxx.setCxrid(this.getUserInfo().getYhid());
          poHcybdxx.setCxsj(now);
          super.update(poHcybdxx);
        }
      }

      ///////////////////////////////////////
      //注销人员信息
      PoHJXX_CZRKJBXXB poRyxx = null;
      strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poRybdxx.getRyid() +
          " and qysj >= '" + poRybdxx.getSlsj() + "' order by qysj asc";
      List ryxxList = super.findAllByHQL(strHQL);
      if (ryxxList != null && ryxxList.size() > 0) {
        for (int i = 0; i < ryxxList.size(); i++) {
          PoHJXX_CZRKJBXXB po = (PoHJXX_CZRKJBXXB) ryxxList.get(i);
          if (po.getRynbid().equals(poRybdxx.getRynbid())) {
            poRyxx = po;
            //校验人信息的时效性
            this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户籍恢复业务");
          }
          if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
            po.setCxbz(PublicConstant.CXBZ_SJCX);
            if (po.getJssj() == null ||
                (po.getJssj() != null && "99999999999999".equals(po.getJssj()))) {
              po.setJssj(now);
            }
            if (!PublicConstant.JLBZ_LS.equals(po.getJlbz())) {
              po.setJlbz(PublicConstant.JLBZ_LS);
            }
            super.update(po);
          } //if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
        } //for (int i = 0; i < ryxxList.size(); i++) {
      } //if (ryxxList != null && ryxxList.size() > 0) {
      if (poRyxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到对应的人员信息,户籍恢复业务无法完成.", null);
      }

      ///////////////////////////////////////////////
      //得到地信息
      PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,poRyxx.
          getMlpnbid());
      if (poMlpxxxx == null) {
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到户籍恢复人员对应的地信息，户籍恢复业务无法完成。", null);
        }
      }

      /////////////////////////////////////////////////////
      //得到户信息
      PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
      if (poHxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DAO_NOTEXIST,
                                   "找不到户籍恢复人员对应的户信息，户籍恢复业务无法完成。", null);
      }

      /////////////////////////////////////////////////////
      //数据范围限制
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_CX_HJHFYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "，户籍恢复业务无法完成。", null);
      }

      ////////////////////////////////////////////////////
      //业务限制处理
      VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx, BaseContext.getUser());
      VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
          GNBH_CX_HJHFYW, voRhdxx);
      if (voLimit.getLimitflag()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "户籍恢复业务受限，受限信息：" + voLimit.getLimitinfo(), null);
      }
      voRhdxx = null;

      ////////////////////////////////////////////
      //保存人员信息为最新信息
      PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
      BeanUtils.copyProperties(poRyxxNew, poRyxx);
      poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
      PoHJXX_HXXB poHxxREVERT = this.revertH(poRyxx.getHhnbid(), hjywid, now);
      if (poHxxREVERT != null) {
        poRyxxNew.setHhnbid(poHxxREVERT.getHhnbid()); //还原户
        poRyxxNew.setMlpnbid(poHxxREVERT.getMlpnbid());
      }
      //处理poRyxxNew变更更正信息begin
      if (voBggzxxEx != null) {
        for (int k = 0; k < voBggzxxEx.length; k++) {
          if (poRyxx.getRynbid().equals(voBggzxxEx[k].getRynbid())) {
            voBggzxxEx[k].setFlag(0); ////是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
            VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, null,
                voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_CX_HJHFYW,now);
            if (vobEx != null) {
              if (vobEx.getVoBggzfhxx() != null) {
                for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                  bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                }
              } //if(vobEx!=null)
              if (vobEx.getVoHcygxtzfhxx() != null) {
                for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                  hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                }
              }
            } //if(vobEx!=null)

            //add hubin 20080310
            for (int i = 0; i < voBggzxxEx[k].getBggzxxList().size(); i++) {
              VoBggzxx voBggzxx = (VoBggzxx) voBggzxxEx[k].getBggzxxList().get(
                  i);
              if (voBggzxx.getBggzxm() != null &&
                  voBggzxx.getBggzxm().equalsIgnoreCase("yhzgx")) {
                if (hcybdxxList != null && hcybdxxList.size() > 0) {
                  PoHJYW_HCYBDXXB poHcybdxx = (PoHJYW_HCYBDXXB) hcybdxxList.get(0);
                  PoHJYW_HCYBDXXB poHcybdxxnew = new PoHJYW_HCYBDXXB();
                  BeanUtils.copyProperties(poHcybdxxnew, poHcybdxx);
                  poHcybdxxnew.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                  poHcybdxxnew.setHcybdlx(HjConstant.HCYBDLX_JTGXBD);
                  poHcybdxxnew.setHcybdlb(voBggzxx.getBggzlb());
                  poHcybdxxnew.setHjywid(poRyxx.getCjhjywid());
                  poHcybdxxnew.setYgx(poHcybdxx.getXgx());
                  poHcybdxxnew.setXgx(voBggzxx.getBggzhnr());
                  poHcybdxxnew.setCxbz(PublicConstant.CXBZ_FCX);
                  poHcybdxxnew.setCxsj(null);
                  poHcybdxxnew.setCxrid(null);
                  poHcybdxxnew.setCxhjywid(null);
                  poHcybdxxnew.setSldw(this.getUserInfo().getDwdm());
                  poHcybdxxnew.setSlrid(this.getUserInfo().getYhid());
                  poHcybdxxnew.setSlsj(now1);
                  super.create(poHcybdxxnew);
                }
              }
            }

          } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
        } //for(int k=0;k<voBggzxx.length;k++)
      }
      //end
      //begin_修改(2004/09/28闵红斌)
      //死亡注销
      if (HjConstant.YWNR_SWZX.equals(poRybdxx.getYwnr())) {
        poRyxxNew.setSwrq(null);
        poRyxxNew.setSwzxlb(null);
        poRyxxNew.setSwzxrq(null);
      }
      //迁出注销
      else if (HjConstant.YWNR_QCZX.equals(poRybdxx.getYwnr())) {
        poRyxxNew.setQcrq(null);
        poRyxxNew.setQczxlb(null);
        poRyxxNew.setQwdgjdq(null);
        poRyxxNew.setQwdssxq(null);
        poRyxxNew.setQwdxz(null);
      }
      //户籍删除
      else if (HjConstant.YWNR_HJSC.equals(poRybdxx.getYwnr())) {
        poRyxxNew.setSwrq(null);
        poRyxxNew.setSwzxlb(null);
        poRyxxNew.setSwzxrq(null);
        poRyxxNew.setQcrq(null);
        poRyxxNew.setQczxlb(null);
        poRyxxNew.setQwdgjdq(null);
        poRyxxNew.setQwdssxq(null);
        poRyxxNew.setQwdxz(null);
      }
      //end_修改(2004/09/28闵红斌)
      poRyxxNew.setYwnr(HjConstant.YWNR_HFHK);
      poRyxxNew.setRyzt(HjConstant.RYZT_ZC);
      poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
      poRyxxNew.setJssj(null);
      poRyxxNew.setXxqysj(now);//add by hb 20061027
      poRyxxNew.setCchjywid(null);
      poRyxxNew.setCjhjywid(hjywid);
      poRyxxNew.setCxbz(PublicConstant.CXBZ_FCX);
      super.create(poRyxxNew);

      ////////////////////////////////////////
      //生成人员变动信息(四变)
      VoRybdxx voRybdxx = new VoRybdxx();
      voRybdxx.setBdbid(null);
      voRybdxx.setBdfw(null);
      voRybdxx.setBdyy(null);
      voRybdxx.setQcdssxq(null);
      voRybdxx.setQcdxz(null);
      voRybdxx.setBdrq(now.substring(0, 8));
      voRybdxx.setBdq_hhnbid(null);
      voRybdxx.setBdqhb(null);
      voRybdxx.setBdh_hhnbid(poRyxxNew.getHhnbid());
      voRybdxx.setRynbid(poRyxxNew.getRynbid());
      voRybdxx.setRzjs(new Long(0));
      voRybdxx.setHzjs(new Long(0));
      rybdxxList.add(voRybdxx);

      ////////////////////////////////////////
      //保存变更更正
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, null, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_CSDJYW,now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      /////////////////////////////////////////////////
      //重号处理
      if (this.countCHRYXX(poRyxxNew.getGmsfhm()) > 1) {
        List chxxList = new ArrayList();
        VoChxx ivoChxx = new VoChxx();
        ivoChxx.setBchryid(poRyxxNew.getRyid());
        ivoChxx.setChsfhm(poRyxxNew.getGmsfhm());
        chxxList.add(ivoChxx);
        this.saveCHXX(hjywid, chxxList,now);
      }

      ////////////////////////////////
      //校验户主数量
      Map hhnbidMap = new HashMap();
      hhnbidMap.put(poRyxxNew.getHhnbid(), null);
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_CX_HJHFYW,
          PublicConstant.HJYWLS_YWLX_GR,
          1, null, now);

      //////////////////////////////////////////
      //保存人员变动信息(四变)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //生成户籍恢复业务返回信息
      voHjhfywfhxx = new VoHjhfywfhxx();
      voHjhfywfhxx.setHjywid(hjywid);
      voHjhfywfhxx.setHhnbid(poRyxxNew.getHhnbid());
      voHjhfywfhxx.setRynbid(poRyxxNew.getRynbid());
      voHjhfywfhxx.setXm(poRyxxNew.getXm());
      voHjhfywfhxx.setGmsfhm(poRyxxNew.getGmsfhm());
      voHjhfywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voHjhfywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHjhfywfhxx;
  }

  /**
   * 户籍注销业务(only modify record)区划调整不可以冲销
   * @param rybdid - 人员变动ID
   * @param sbhjywid - 上笔户籍业务ID
   * @param voBggzxxEx - 变更更正信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjzxywfhxx processHjzxyw(Long rybdid, Long sbhjywid,
                                    VoBggzxxEx[] voBggzxxEx) throws
      ServiceException, DAOException {

    VoHjzxywfhxx voHjzxywfhxx = null;
    Long nbsfzid = null; //内部身份证ID
    Long hjywid = null;
    Long oldhjywid = null;

    String strHQL = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    boolean bFind = false;
    boolean isHbbg = false;
    String now = StringUtils.getServiceTime();
    String now1 = (Long.parseLong(now) + 1) + "";

    /////////////////////////////////////////////
    //数据校验
    if (rybdid == null || sbhjywid == null) {
      return null;
    }

    try {
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_hbdxxbDAO = DAOFactory.createHJTJ_HBDXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjyw_csdjxxbDAO = DAOFactory.createHJYW_CSDJXXBDAO();
      PojoInfo hjyw_qrdjxxbDAO = DAOFactory.createHJYW_QRDJXXBDAO();
      PojoInfo hjyw_hjblxxbDAO = DAOFactory.createHJYW_HJBLXXBDAO();
      PojoInfo hjyw_hbbgxxbDAO = DAOFactory.createHJYW_HBBGXXBDAO();
      PojoInfo hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
      PojoInfo hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
      PojoInfo hjyw_qcclxxbDAO = DAOFactory.createHJYW_QCCLXXBDAO();
      PojoInfo hjyw_hcybdxxbDAO = DAOFactory.createHJYW_HCYBDXXBDAO();

      ///////////////////////////////////////////
      //事务开始
      ////////////////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ///////////////////////////////////////
      //得到人员变动信息(四变)
      PoHJTJ_RYBDXXB poRybdxx = super.get(PoHJTJ_RYBDXXB.class, rybdid);
      if (poRybdxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到对应的人员变动信息,户籍注销业务无法完成.", null);
      }
      if (!this.checkHJZXYW(poRybdxx)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "不支持注销的业务,户籍注销业务无法完成.", null);
      }
      oldhjywid = poRybdxx.getHjywid();
      //判断户籍业务冲销时间范围
      this.checkHJYWCXSJFW(poRybdxx.getSlsj());
      //时效性处理
      super.refresh(poRybdxx, LockOptions.UPGRADE);
      if (!PublicConstant.CXBZ_FCX.equals(poRybdxx.getCxbz())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "该业务已经恢复了,户籍注销业务无法完成.", null);
      }

      ///////////////////////////////////////
      //注销人员变动信息(四变)
      strHQL = "from PoHJTJ_RYBDXXB where ryid=" + poRybdxx.getRyid() +
          " and slsj >= '" + poRybdxx.getSlsj() + "' order by slsj asc";
      List old_rybdxxList = super.findAllByHQL(strHQL);
      if (old_rybdxxList != null && old_rybdxxList.size() > 0) {
        for (int i = 0; i < old_rybdxxList.size(); i++) {
          PoHJTJ_RYBDXXB po = (PoHJTJ_RYBDXXB) old_rybdxxList.get(i);
          if (!poRybdxx.getRybdid().equals(po.getRybdid())) {
            this.checkHJZXYW(po.getYwnr(), po.getCxbz());
          }
          if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
            po.setCxbz(PublicConstant.CXBZ_SJCX);
            po.setCxsj(now);
            po.setCxrid(this.getUserInfo().getYhid());
            super.update(po);
            //注销相应的业务信息
            //出生登记
            if (HjConstant.YWNR_CSDJ.equals(po.getYwnr())) {
              PoHJYW_CSDJXXB poCsdjxx = super.get(PoHJYW_CSDJXXB.class, po.
                  getBdbid());
              if (poCsdjxx != null) {
                poCsdjxx.setCxbz(PublicConstant.CXBZ_SJCX);
                poCsdjxx.setCxsj(now);
                poCsdjxx.setCxhjywid(hjywid);
                poCsdjxx.setCxrid(this.getUserInfo().getYhid());
                super.update(poCsdjxx);
              }
            }
            //迁入登记
            else if (HjConstant.YWNR_QRDJ.equals(po.getYwnr())) {
              PoHJYW_QRDJXXB poQrdjxx = super.get(PoHJYW_QRDJXXB.class,po.
                  getBdbid());
              if (poQrdjxx != null) {
                poQrdjxx.setCxbz(PublicConstant.CXBZ_SJCX);
                poQrdjxx.setCxsj(now);
                poQrdjxx.setCxhjywid(hjywid);
                poQrdjxx.setCxrid(this.getUserInfo().getYhid());
                super.update(poQrdjxx);
              }
            }
            //户籍补录
            else if (HjConstant.YWNR_HJBL.equals(po.getYwnr())) {
              PoHJYW_HJBLXXB poHjblxx = super.get(PoHJYW_HJBLXXB.class, po.
                  getBdbid());
              if (poHjblxx != null) {
                poHjblxx.setCxbz(PublicConstant.CXBZ_SJCX);
                poHjblxx.setCxsj(now);
                poHjblxx.setCxhjywid(hjywid);
                poHjblxx.setCxrid(this.getUserInfo().getYhid());
                super.update(poHjblxx);
              }
            }
            //住址变动
            else if (HjConstant.YWNR_ZZBDQR.equals(po.getYwnr())) {
              PoHJYW_ZZBDXXB poZzbdxx = super.get(PoHJYW_ZZBDXXB.class, po.
                  getBdbid());
              if (poZzbdxx != null) {
                poZzbdxx.setCxbz(PublicConstant.CXBZ_SJCX);
                poZzbdxx.setCxsj(now);
                poZzbdxx.setCxhjywid(hjywid);
                poZzbdxx.setCxrid(this.getUserInfo().getYhid());
                super.update(poZzbdxx);
              }
            }

            //户别变更
            strHQL = "from PoHJYW_HBBGXXB where hjywid=" + po.getHjywid() +
                " and rynbid=" + po.getRynbid();
            List hbbgxxList = super.findAllByHQL(strHQL);
            if (hbbgxxList != null && hbbgxxList.size() > 0) {
              for (int a = 0; a < hbbgxxList.size(); a++) {
                PoHJYW_HBBGXXB poHbbgxx = (PoHJYW_HBBGXXB) hbbgxxList.get(a);
                poHbbgxx.setCxbz(PublicConstant.CXBZ_SJCX);
                poHbbgxx.setCxsj(now);
                poHbbgxx.setCxhjywid(hjywid);
                poHbbgxx.setCxrid(this.getUserInfo().getYhid());
                super.update(poHbbgxx);
              }
            }
          } //if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
        } //for (int i = 0; i < old_rybdxxList.size(); i++) {
      } //if (old_rybdxxList != null && old_rybdxxList.size() > 0) {

      ///////////////////////////////////////
      //注销恢复人员的户成员变动信息
      strHQL = "from PoHJYW_HCYBDXXB where hjywid=" + poRybdxx.getHjywid() +
          " and rynbid=" + poRybdxx.getRynbid();
      List hcybdxxList = super.findAllByHQL(strHQL);
      if (hcybdxxList != null && hcybdxxList.size() > 0) {
        for (int i = 0; i < hcybdxxList.size(); i++) {
          PoHJYW_HCYBDXXB poHcybdxx = (PoHJYW_HCYBDXXB) hcybdxxList.get(i);
          poHcybdxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poHcybdxx.setCxhjywid(hjywid);
          poHcybdxx.setCxrid(this.getUserInfo().getYhid());
          poHcybdxx.setCxsj(now);
          super.update(poHcybdxx);
        }
      }

      ///////////////////////////////////////
      //注销人员信息
      PoHJXX_CZRKJBXXB poRyxx = null;
      strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poRybdxx.getRyid() +
          " and qysj >= '" + poRybdxx.getSlsj() + "' order by qysj asc";
      List ryxxList = super.findAllByHQL(strHQL);
      if (ryxxList != null && ryxxList.size() > 0) {
        for (int i = 0; i < ryxxList.size(); i++) {
          PoHJXX_CZRKJBXXB po = (PoHJXX_CZRKJBXXB) ryxxList.get(i);
          //hubin 20080410
          //户别变更后，有户属性变更，则不允许冲消
          if (HjConstant.YWNR_HBBG.equals(po.getYwnr())) {
            isHbbg = true;
          }
          else if(isHbbg && HjConstant.YWNR_HSXBG.equals(po.getYwnr())){
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                 "户别变更后有户属性变更,户籍注销业务无法完成.", null);
          }
          ///////////////////////////////////////////////////
          if (!po.getRynbid().equals(poRybdxx.getRynbid())) {
            this.checkHJZXYW(po.getYwnr(), po.getCxbz());
          }
          if (po.getRynbid().equals(poRybdxx.getRynbid())) {
            poRyxx = po;
          }
          if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
            if (PublicConstant.JLBZ_ZX.equals(po.getJlbz())) {
              bFind = true;
              nbsfzid = po.getNbsfzid();
              //校验人信息的时效性
              this.checkRXX(po, hjxx_czrkjbxxbDAO, "户籍注销业务");
            }
            po.setCxbz(PublicConstant.CXBZ_SJCX);
            if (po.getJssj() == null ||
                (po.getJssj() != null && "99999999999999".equals(po.getJssj()))) {
              po.setJssj(now);
            }
            //add by hh 20051207 //modi by hh 20050105 撤消记录不应该继续撤消
            // po.setCchjywid(hjywid);
            if (po.getJlbz() != null && po.getJlbz().equals("1") && po.getCxbz() != null &&
                po.getJlbz().equals("0")) {
              po.setCchjywid(hjywid);
            }

            super.update(po);
          }
        }
      }
      if (poRyxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到对应的人员信息,户籍注销业务无法完成.", null);
      }

      //判断人信息有没有记录标志为最新并且冲销标志为非冲销的记录
      if (!bFind) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "时效性错误，" + poRyxx.getXm() + "(" +
                                   poRyxx.getGmsfhm() +
                                   ")的最新记录可能在操作过程中被其他用户操作过，户籍注销业务无法完成。", null);
      }

      //Begin_增加于闵红斌(2004/11/05 14:05:00)
      //恢复回迁前记录
      if (HjConstant.YWNR_QRDJ.equals(poRyxx.getYwnr()) &&
          !poRyxx.getRyid().equals(poRyxx.getRynbid())) {
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poRyxx.getRyid() +
            " and cchjywid=" + poRyxx.getCjhjywid() + " and jlbz='" +
            PublicConstant.JLBZ_LS + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' and jssj='" + poRyxx.getQysj() + "' ";
        List hqryxxList = super.findAllByHQL(strHQL);
        if (hqryxxList != null && hqryxxList.size() > 0) {
          PoHJXX_CZRKJBXXB poRyxxHQ = (PoHJXX_CZRKJBXXB) hqryxxList.get(0);
          poRyxxHQ.setCchjywid(null);
          poRyxxHQ.setJssj("99999999999999");
          poRyxxHQ.setJlbz(PublicConstant.JLBZ_ZX);
          super.update(poRyxxHQ);
        }
      }
      //End

      ////////////////////////////////////////////
      //得到地信息
      PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,poRyxx.
          getMlpnbid());
      if (poMlpxxxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到人员信息对应的地信息,户籍注销业务无法完成.", null);
      }

      /////////////////////////////////////////////
      //得到户信息
      PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, poRyxx.getHhnbid());
      if (poHxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到人员信息对应的户信息,户籍注销业务无法完成.", null);
      }

      //////////////////////////////////////////
      //数据范围限制
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_CX_HJZXYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "，户籍注销业务无法完成。", null);
      }

      ///////////////////////////////////////////
      //业务限制处理
      VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx, BaseContext.getUser());
      VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
          GNBH_CX_HJZXYW, voRhdxx);
      if (voLimit.getLimitflag()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "户籍注销业务受限，受限信息：" + voLimit.getLimitinfo(), null);
      }
      voRhdxx = null;

      ///////////////////////////////////////////////////////
      ////非户别变更处理
      long countHcyxx = 0;
      if (!HjConstant.YWNR_HBBG.equals(poRybdxx.getYwnr())) {
        //////////////////////////////////////////
        //统计户成员数
        strHQL = "from PoHJXX_HXXB where hhid=" + poHxx.getHhid() +
            " and jlbz='" +
            PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' ";
        List hxxList = super.findAllByHQL(strHQL);
        if (hxxList != null && hxxList.size() > 0) {
          PoHJXX_HXXB po = (PoHJXX_HXXB) hxxList.get(0);
          strHQL = "select count(*) from PoHJXX_CZRKJBXXB where hhnbid=" +
              po.getHhnbid() + " and ryzt='" + HjConstant.RYZT_ZC +
              "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
              PublicConstant.CXBZ_FCX + "' ";
          countHcyxx = super.getCount(strHQL);
        }

        ////////////////////////////////////////////////
        //注销户信息
        if (countHcyxx <= 0) {
          //注销户
          strHQL = "from PoHJXX_HXXB where hhid=" + poHxx.getHhid() +
              " and qysj >= '" + poHxx.getQysj() + "' order by qysj asc";
          hxxList = super.findAllByHQL(strHQL);
          if (hxxList != null && hxxList.size() > 0) {
            for (int i = 0; i < hxxList.size(); i++) {
              PoHJXX_HXXB po = (PoHJXX_HXXB) hxxList.get(0);
              if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
                po.setCxbz(PublicConstant.CXBZ_SJCX);
                if (po.getJssj() == null ||
                    (po.getJssj() != null && "99999999999999".equals(po.getJssj()))) {
                  po.setJssj(now);
                }
                super.update(po);
              } //if (PublicConstant.CXBZ_FCX.equals(po.getCxbz())) {
            } //for(int i=0;i<hxxList.size();i++){
          } //if (hxxList != null && hxxList.size() > 0) {
          //注销户变动(增加于2005/04/25 14:30:00 By MHB)
          strHQL = "from PoHJTJ_HBDXXB where hhnbid=" + poHxx.getHhnbid() +
              " and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
          List hbdxxList = super.findAllByHQL(strHQL);
          if (hbdxxList != null && hbdxxList.size() > 0) {
            for (int i = 0; i < hbdxxList.size(); i++) {
              PoHJTJ_HBDXXB poHbdxx = (PoHJTJ_HBDXXB) hbdxxList.get(i);
              poHbdxx.setCxbz(PublicConstant.CXBZ_SJCX);
              poHbdxx.setCxhjywid(hjywid);
              poHbdxx.setCxrid(this.getUserInfo().getYhid());
              poHbdxx.setCxsj(now);
              super.update(poHbdxx);
            }
          }
        } //if (countHcyxx <= 0) {

        //重号信息消除(注销老的重号信息主要是处理重号所在的单位不同情况)
        strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
            HjConstant.CHCLFS_WCL + "' and chsfhm='" + poRyxx.getGmsfhm() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
        List xcchxxList = super.findAllByHQL(strHQL);
        if (xcchxxList != null) {
          for (int j = 0; j < xcchxxList.size(); j++) {
            PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) xcchxxList.get(j);
            //与别人重
            if (poRyxx.getRyid().equals(poChclxx.getRyid())) {
              poChclxx.setCxbz(PublicConstant.CXBZ_SJCX);
              poChclxx.setCxhjywid(hjywid);
              poChclxx.setCxrid(this.getUserInfo().getYhid());
              poChclxx.setCxsj(now);
              super.update(poChclxx);
            }
            //别人与自己重
            if (poRyxx.getRyid().equals(poChclxx.getBchryid())) {
              poChclxx.setCxbz(PublicConstant.CXBZ_SJCX);
              poChclxx.setCxhjywid(hjywid);
              poChclxx.setCxrid(this.getUserInfo().getYhid());
              poChclxx.setCxsj(now);
              super.update(poChclxx);
            }
          } //for (int j = 0; j < xcchxxList.size(); j++) {
        }
      } //if (!HjConstant.YWNR_HBBG.equals(poRybdxx.getYwnr())) {

      //////////////////////////////////////////
      //住址变动
      PoHJXX_CZRKJBXXB poRyxxNew = null;
      if (HjConstant.YWNR_ZZBDQR.equals(poRybdxx.getYwnr())) {
        //得到住址变动迁出记录
        PoHJXX_CZRKJBXXB poRyxxQC = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poRyxx.getRyid() +
            " and ywnr='" + HjConstant.YWNR_ZZBDQC + "' and cchjywid=" +
            poRyxx.getCjhjywid();
        List ryxxqcList = super.findAllByHQL(strHQL);
        if (ryxxqcList != null && ryxxqcList.size() > 0) {
          poRyxxQC = (PoHJXX_CZRKJBXXB) ryxxqcList.get(0);
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到住址变动迁出人员信息,户籍注销业务无法完成.", null);
        }
        //保存人信息新记录
        poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxxQC);

        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        PoHJXX_HXXB poHxxREVERT = this.revertH(poRyxxQC.getHhnbid(), hjywid,
                                               now);
        if (poHxxREVERT != null) {
          poRyxxNew.setHhnbid(poHxxREVERT.getHhnbid()); //还原户
          poRyxxNew.setMlpnbid(poHxxREVERT.getMlpnbid());
        }
        //处理poRyxxNew变更更正信息begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (poRyxx.getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag(0); ////是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, null,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_CX_HJZXYW,now);
              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)
            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        poRyxxNew.setNbsfzid(nbsfzid);
        poRyxxNew.setYwnr(HjConstant.YWNR_HFHK);
        poRyxxNew.setRyzt(HjConstant.RYZT_ZC);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setCxbz(PublicConstant.CXBZ_FCX);
        super.create(poRyxxNew);
        //生成人员变动信息(四变)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(null);
        voRybdxx.setBdfw(null);
        voRybdxx.setBdyy(null);
        voRybdxx.setQcdssxq(null);
        voRybdxx.setQcdxz(null);
        voRybdxx.setBdrq(now.substring(0, 8));
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(null);
        voRybdxx.setBdh_hhnbid(poRyxxNew.getHhnbid());
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long(0));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);
        //重号处理
        if (this.countCHRYXX(poRyxxNew.getGmsfhm()) > 1) {
          List chxxList = new ArrayList();
          VoChxx ivoChxx = new VoChxx();
          ivoChxx.setBchryid(poRyxxNew.getRyid());
          ivoChxx.setChsfhm(poRyxxNew.getGmsfhm());
          chxxList.add(ivoChxx);
          this.saveCHXX(hjywid, chxxList,now);
        }

        //保存住址变动人员迁出处理信息
        PoHJYW_QCCLXXB pooldQcclxx = null;
        strHQL = " from PoHJYW_QCCLXXB where hjywid = " + oldhjywid +
            " and ryid = " + poRybdxx.getRyid();
        List old_qcclxxList = super.findAllByHQL(strHQL);
        if (old_qcclxxList != null && old_qcclxxList.size() > 0) {
          pooldQcclxx = (PoHJYW_QCCLXXB) old_qcclxxList.get(0);
          pooldQcclxx.setCxbz(PublicConstant.CXBZ_SJCX);
          super.update(pooldQcclxx);
        }

        PoHJXX_MLPXXXXB poMlpxxxxNew = super.get(PoHJXX_MLPXXXXB.class,
            poRyxxNew.getMlpnbid());
        if (! (poMlpxxxxNew.getPcs() != null &&
               poMlpxxxxNew.getPcs().equals(poMlpxxxx.getPcs()))) {

          PoHJYW_QCCLXXB poQcclxx = new PoHJYW_QCCLXXB();
          BeanUtils.copyProperties(poQcclxx, pooldQcclxx);
          poQcclxx.setQcclid( (Long) hjyw_qcclxxbDAO.getId());
          poQcclxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poQcclxx.setCzlx(HjConstant.QCCL_CZXL_RYHF);
          poQcclxx.setClbz(HjConstant.QCCL_CLBZ_WCL);
          super.create(poQcclxx);
        }

        //注销恢复人员的住址变动迁出记录对应的户成员变动信息
        strHQL = "from PoHJYW_HCYBDXXB where hjywid=" + poRybdxx.getHjywid() +
            " and rynbid=" + poRyxxQC.getRynbid();
        hcybdxxList = super.findAllByHQL(strHQL);
        if (hcybdxxList != null && hcybdxxList.size() > 0) {
          for (int i = 0; i < hcybdxxList.size(); i++) {
            PoHJYW_HCYBDXXB poHcybdxx = (PoHJYW_HCYBDXXB) hcybdxxList.get(i);
            poHcybdxx.setCxbz(PublicConstant.CXBZ_SJCX);
            poHcybdxx.setCxhjywid(hjywid);
            poHcybdxx.setCxrid(this.getUserInfo().getYhid());
            poHcybdxx.setCxsj(now);
            super.update(poHcybdxx);
          }
        }

        //add hubin 20080310
        //住址变动的同时，如果有与户主关系的变更更正，冲销时，增加户成员变动信息
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            for (int i = 0; i < voBggzxxEx[k].getBggzxxList().size(); i++) {
              VoBggzxx voBggzxx = (VoBggzxx) voBggzxxEx[k].getBggzxxList().
                  get(i);
              if (voBggzxx.getBggzxm() != null &&
                  voBggzxx.getBggzxm().equalsIgnoreCase("yhzgx")) {
                if (hcybdxxList != null && hcybdxxList.size() > 0) {
                  PoHJYW_HCYBDXXB poHcybdxx = (PoHJYW_HCYBDXXB) hcybdxxList.get(0);
                  PoHJYW_HCYBDXXB poHcybdxxnew = new PoHJYW_HCYBDXXB();
                  BeanUtils.copyProperties(poHcybdxxnew, poHcybdxx);
                  poHcybdxxnew.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                  poHcybdxxnew.setHcybdlx(HjConstant.HCYBDLX_JTGXBD);
                  poHcybdxxnew.setHcybdlb(voBggzxx.getBggzlb());
                  poHcybdxxnew.setHjywid(poRyxx.getCjhjywid());
                  poHcybdxxnew.setYgx(poHcybdxx.getXgx());
                  poHcybdxxnew.setXgx(voBggzxx.getBggzhnr());
                  poHcybdxxnew.setCxbz(PublicConstant.CXBZ_FCX);
                  poHcybdxxnew.setCxsj(null);
                  poHcybdxxnew.setCxrid(null);
                  poHcybdxxnew.setCxhjywid(null);
                  poHcybdxxnew.setSldw(this.getUserInfo().getDwdm());
                  poHcybdxxnew.setSlrid(this.getUserInfo().getYhid());
                  poHcybdxxnew.setSlsj(now1);
                  super.create(poHcybdxxnew);
                }
              }
            }
          }
        }
      }
      //户别变更
      else if (HjConstant.YWNR_HBBG.equals(poRybdxx.getYwnr())) {
        //得到户别变更前人员记录
        PoHJXX_CZRKJBXXB poRyxxBGQ = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poRyxx.getRyid() +
            " and cchjywid=" + poRyxx.getCjhjywid();
        List ryxxbgqList = super.findAllByHQL(strHQL);
        if (ryxxbgqList != null && ryxxbgqList.size() > 0) {
          poRyxxBGQ = (PoHJXX_CZRKJBXXB) ryxxbgqList.get(0);
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到户别变更前的人员信息,户籍注销业务无法完成.", null);
        }
        //保存人信息新记录
        poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxxBGQ);
        //add by hh 20051202 起用时间修改
        poRyxxNew.setQysj(poRyxx.getQysj());

        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        //处理poRyxxNew变更更正信息begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (poRyxx.getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag(0); ////是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, null,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_CX_HJZXYW,now);
              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)

              //add hubin 20080310
              for (int i = 0; i < voBggzxxEx[k].getBggzxxList().size(); i++) {
                VoBggzxx voBggzxx = (VoBggzxx) voBggzxxEx[k].getBggzxxList().
                    get(i);
                if (voBggzxx.getBggzxm() != null &&
                    voBggzxx.getBggzxm().equalsIgnoreCase("yhzgx")) {
                  if (ryxxbgqList != null && ryxxbgqList.size() > 0) {
                    PoHJYW_HCYBDXXB poHcybdxxnew = new PoHJYW_HCYBDXXB();
                    BeanUtils.copyProperties(poHcybdxxnew, poRyxxBGQ);
                    poHcybdxxnew.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                    poHcybdxxnew.setHcybdlx(HjConstant.HCYBDLX_JTGXBD);
                    poHcybdxxnew.setHcybdlb(voBggzxx.getBggzlb());
                    poHcybdxxnew.setHjywid(poRyxx.getCjhjywid());
                    poHcybdxxnew.setHcybdrq(now1.substring(0,8));
                    poHcybdxxnew.setYgx(poRyxx.getYhzgx());
                    poHcybdxxnew.setXgx(voBggzxx.getBggzhnr());
                    poHcybdxxnew.setCxbz(PublicConstant.CXBZ_FCX);
                    poHcybdxxnew.setSldw(this.getUserInfo().getDwdm());
                    poHcybdxxnew.setSlrid(this.getUserInfo().getYhid());
                    poHcybdxxnew.setSlsj(now1);
                    super.create(poHcybdxxnew);
                  }
                }
              }

            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        poRyxxNew.setNbsfzid(nbsfzid);
        poRyxxNew.setYwnr(HjConstant.YWNR_HFHK);
        poRyxxNew.setRyzt(HjConstant.RYZT_ZC);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061026
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setCxbz(PublicConstant.CXBZ_FCX);
        super.create(poRyxxNew);
        //生成人员变动信息(四变)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(null);
        voRybdxx.setBdfw(null);
        voRybdxx.setBdyy(null);
        voRybdxx.setQcdssxq(null);
        voRybdxx.setQcdxz(null);
        voRybdxx.setBdrq(now.substring(0, 8));
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(null);
        voRybdxx.setBdh_hhnbid(poRyxxNew.getHhnbid());
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long(0));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);
      }

      ////////////////////////////////////////
      //保存变更更正
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, null, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_CSDJYW,now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
          //保存迁出户变更人员迁出处理信息
          if (HjConstant.YWNR_ZZBDQR.equals(poRybdxx.getYwnr())) {
            VoBgryfhxx voBgryfhxx[] = vobEx.getVoBgryfhxx();
            if (voBgryfhxx != null) {
              for (int a = 0; a < voBgryfhxx.length; a++) {
                if (!poRyxxNew.getHhnbid().equals(voBgryfhxx[a].getHhnbid())) {
                  PoHJYW_QCCLXXB poQcclxx = new PoHJYW_QCCLXXB();
                  poQcclxx.setQcclid( (Long) hjyw_qcclxxbDAO.getId());
                  poQcclxx.setQqrynbid(voBgryfhxx[a].getOld_rynbid());
                  poQcclxx.setQhrynbid(voBgryfhxx[a].getRynbid());
                  poQcclxx.setHjywid(hjywid);
                  poQcclxx.setCzlx(HjConstant.QCCL_CZXL_RYBG);
                  poQcclxx.setClbz(HjConstant.QCCL_CLBZ_WCL);
                  super.create(poQcclxx);
                }
              } //for (int a = 0; a < voBgryfhxx.length; a++)
            } //if ( voBgryfhxx != null)
          } //if (HjConstant.YWNR_ZZBDQR.equals(poRybdxx.getYwnr())) {
        } //if(vobEx!=null)
      }

      ////////////////////////////////
      //校验户主数量
      Map hhnbidMap = new HashMap();
      if (countHcyxx > 0) {
        hhnbidMap.put(poRyxx.getHhnbid(), null);
      }
      if (poRyxxNew != null) {
        hhnbidMap.put(poRyxxNew.getHhnbid(), null);
      }
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_CX_HJZXYW,
          PublicConstant.HJYWLS_YWLX_GR,
          1, null, now);

      //////////////////////////////////////////
      //保存人员变动信息(四变)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //生成业务返回信息
      if (poRyxxNew == null) {
        poRyxxNew = poRyxx;
      }
      voHjzxywfhxx = new VoHjzxywfhxx();
      voHjzxywfhxx.setHjywid(hjywid);
      voHjzxywfhxx.setHhnbid(poRyxxNew.getHhnbid());
      voHjzxywfhxx.setRynbid(poRyxxNew.getRynbid());
      voHjzxywfhxx.setXm(poRyxxNew.getXm());
      voHjzxywfhxx.setGmsfhm(poRyxxNew.getGmsfhm());
      voHjzxywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voHjzxywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHjzxywfhxx;
  }

  /**
   * 判断户籍业务冲销时间范围
   * @param slsj - 受理时间
   * @throws ServiceException
   */
  private void checkHJYWCXSJFW(String slsj) throws DAOException,
      ServiceException {

    try {
      String stkzcs = this.getXTKZCS(PublicConstant.XTKZCS_HJYWCXSJFW);
      int rqlx = this.caclRQLX(slsj);

      //1 - 本月业务可冲销
      if ("1".equals(stkzcs)) {
        if (! (1 == rqlx)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "非本月办理的户籍业务,户籍冲销业务无法完成.", null);
        }
      }
      //2 - 本季度业务可冲销
      else if ("2".equals(stkzcs)) {
        if (! (1 == rqlx || 2 == rqlx)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "非本季度办理的户籍业务,户籍冲销业务无法完成.", null);
        }
      }
      //3 - 本年度业务可冲销
      else if ("3".equals(stkzcs)) {
        if (! (1 == rqlx || 2 == rqlx || 3 == rqlx)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "非本年度办理的户籍业务,户籍冲销业务无法完成.", null);
        }
      }
      else {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "户籍业务冲销时间范围参数设置不正确,户籍冲销业务无法完成.", null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }

    return;
  }

  /**
   * 户籍恢复检验
   * @param ywnr - 业务内容
   * @return
   */
  private boolean checkHJHFYW(PoHJTJ_RYBDXXB poRybdxx) throws DAOException,
      ServiceException {

    if (poRybdxx == null) {
      return false;
    }

    try {

      //判断是否为可恢复的业务类型
      if (HjConstant.YWNR_SWZX.equals(poRybdxx.getYwnr())
          || HjConstant.YWNR_QCZX.equals(poRybdxx.getYwnr())
          || HjConstant.YWNR_HJSC.equals(poRybdxx.getYwnr())) {
        return true;
      }

    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }

    return false;
  }

  /**
   * 户籍注销检验
   * @param ywnr - 业务内容
   * @return
   */
  private boolean checkHJZXYW(PoHJTJ_RYBDXXB poRybdxx) {

    if (poRybdxx == null) {
      return false;
    }

    if (HjConstant.YWNR_CSDJ.equals(poRybdxx.getYwnr())
        || HjConstant.YWNR_QRDJ.equals(poRybdxx.getYwnr())
        || HjConstant.YWNR_HJBL.equals(poRybdxx.getYwnr())
        || HjConstant.YWNR_ZZBDQR.equals(poRybdxx.getYwnr())
        || HjConstant.YWNR_HBBG.equals(poRybdxx.getYwnr())
        ) {
      return true;
    }

    return false;
  }

  private void checkHJZXYW(String ywnr, String cxbz) throws DAOException,
      ServiceException {

    if (PublicConstant.CXBZ_FCX.equals(cxbz) &&
        ! (HjConstant.YWNR_BGGZ.equals(ywnr) ||
           HjConstant.YWNR_ZZBDQC.equals(ywnr) ||
           HjConstant.YWNR_HSXBG.equals(ywnr) || //加入对“户属性变更”后的户籍业务冲销的支持 by MHB 2004/12/28 )9:37:00
           HjConstant.YWNR_HFHK.equals(ywnr) ||
           HjConstant.YWNR_ZXHK.equals(ywnr))) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                 "不支持注销的业务,户籍注销业务无法完成.", null);
    }

    return;
  }

  /**
   * 还原户
   * @param hhnbid - 需还原的户号内部ID
   * @return - 还原后的户信息
   */
  private PoHJXX_HXXB revertH(Long hhnbid, Long hjywid, String czsj) throws
      DAOException, ServiceException {

    String strHQL = null;
    PoHJXX_HXXB poHxxRET = null;

    if (hhnbid == null) {
      return null;
    }

    try {
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjtj_hbdxxbDAO = DAOFactory.createHJTJ_HBDXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();

      //得到户信息
      PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, hhnbid);
      if (poHxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到户号内部ID为" + hhnbid + "的户信息,无法完成还原户.", null);
      }
      strHQL = "from PoHJXX_HXXB where hhid=" + poHxx.getHhid() + " and jlbz='" +
          PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
          "' ";
      List hxxList = super.findAllByHQL(strHQL);
      if (hxxList != null && hxxList.size() > 0) {
        poHxx = (PoHJXX_HXXB) hxxList.get(0);
        //恢复注销的户
        if (HjConstant.HHZT_ZX.equals(poHxx.getHhzt())) {
          //保存户信息为历史记录
          poHxx.setCxbz(PublicConstant.CXBZ_SJCX);
          poHxx.setJssj(czsj);
          poHxx.setCchjywid(hjywid);
          poHxx.setChlb(HjConstant.CHLB_YWZX);
          poHxx.setJlbz(PublicConstant.JLBZ_LS);
          super.update(poHxx);
          //保存户信息为最新记录
          PoHJXX_HXXB poHxxNew = new PoHJXX_HXXB();
          BeanUtils.copyProperties(poHxxNew, poHxx);
          poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
          poHxxNew.setCxbz(PublicConstant.CXBZ_FCX);
          poHxxNew.setJssj(null);
          poHxxNew.setCchjywid(null);
          poHxxNew.setChlb(null);
          poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          poHxxNew.setCjhjywid(hjywid);
          poHxxNew.setJhlb(HjConstant.JHLB_YWSC);
          //poHxxNew.setQysj(czsj);//引用注销户时的启用时间
          poHxxNew.setHhzt(HjConstant.HHZT_ZC);
          super.create(poHxxNew);

          //处理恢复户变动信息
          strHQL = "from PoHJTJ_HBDXXB where hhnbid=" + poHxx.getHhnbid();
          List hbdxxList = super.findAllByHQL(strHQL);
          for (int i = 0; i < hbdxxList.size(); i++) {
            PoHJTJ_HBDXXB poHbdxx = (PoHJTJ_HBDXXB) hbdxxList.get(i);
            poHbdxx.setCxhjywid(hjywid);
            poHbdxx.setCxbz(PublicConstant.CXBZ_SJCX);
            poHbdxx.setCxrid(this.getUserInfo().getYhid());
            poHbdxx.setCxsj(czsj);
            super.update(poHbdxx);
          }

          //修改人员变动信息(注销户的这条记录)
          strHQL = "from PoHJTJ_RYBDXXB where bdqhhnbid=" + poHxx.getHhnbid() +
              " and hzjs=-1 ";
          List rybdxxList = super.findAllByHQL(strHQL);
          if (rybdxxList != null && rybdxxList.size() > 0) {
            PoHJTJ_RYBDXXB po = (PoHJTJ_RYBDXXB) rybdxxList.get(0);
            po.setHzjs(new Long(0));
            super.update(po);
          }

          //生成新户变动信息
          PoHJTJ_HBDXXB poHbdxx = new PoHJTJ_HBDXXB();
          poHbdxx.setBdfw(poHxxNew.getBdfw());
          poHbdxx.setBdsj(czsj);
          poHbdxx.setBdyy(poHxxNew.getBdyy());
          poHbdxx.setCxbz(PublicConstant.CXBZ_PDCX);
          poHbdxx.setCxrid(null);
          poHbdxx.setCxsj(null);
          poHbdxx.setHbdid( (Long) hjtj_hbdxxbDAO.getId());
          poHbdxx.setHhnbid(poHxxNew.getHhnbid());
          poHbdxx.setHzjs(new Long(0));
          poHbdxx.setYwnr(HjConstant.YWNR_HFHK);
          poHbdxx.setCxhjywid(null);
          poHbdxx.setHjywid(hjywid);
          super.create(poHbdxx);

          poHxxRET = poHxxNew;
        }
        else {
          poHxxRET = poHxx;
        }
      } //if (hxxList != null && hxxList.size() > 0) {
      else {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "找不到户号ID为" + poHxx.getHhid() +
                                   "的最新户信息记录,无法完成还原户.", null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return poHxxRET;
  }

}
