package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.exception.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.constant.HjConstant;
import com.hzjc.hz2004.constant.PublicConstant;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 四变业务修改实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class BdxgServiceImpl
    extends Hz2004BaseService
    implements BdxgService {
  /**
   * 迁入业务修改
   * @param poHJYW_QRDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List QrywXg(PoHJYW_QRDJXXB poHJYW_QRDJXXB[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();

    try {
      PojoInfo hjyw_qrdjxxbDAO = DAOFactory.createHJYW_QRDJXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_bdxgrzbDAO = DAOFactory.createHJTJ_BDXGRZBDAO();

      //修改迁入登记信息表中的信息
      if (poHJYW_QRDJXXB != null && poHJYW_QRDJXXB.length > 0) {
        PoHJYW_QRDJXXB poReturn[] = new PoHJYW_QRDJXXB[poHJYW_QRDJXXB.length];
        for (int i = 0; i < poHJYW_QRDJXXB.length; i++) {
          PoHJYW_QRDJXXB poQrdjxx = poHJYW_QRDJXXB[i];
          PoHJYW_QRDJXXB poQrdjOld = new PoHJYW_QRDJXXB();
          //根据迁入登记ID检查登记信息是否存在
          PoHJYW_QRDJXXB poQrdjNew = super.get(PoHJYW_QRDJXXB.class,
              poQrdjxx.getQrdjid());
          if (poQrdjNew == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的迁入信息," + poQrdjxx.getQrdjid(), null);
          }
          //胡斌 20060605 把迁出地省市县为国家的代码，填入迁出地国家地区
          if ("0".equals(poQrdjxx.getQcdssxq().substring(0, 1))) {
            poQrdjxx.setQcdgjdq(poQrdjxx.getQcdssxq().substring(3, 6));
          }
          else{
            //如果修改成国内省市县，把迁出地国家地区清空
            poQrdjxx.setQcdgjdq(null);
          }
          //修改迁入登记表中的数据
          BeanUtils.copyProperties(poQrdjOld, poQrdjNew);
          BeanUtils.copyProperties(poQrdjNew, poQrdjxx);
          poReturn[i] = (PoHJYW_QRDJXXB)super.update(poQrdjNew);

          //修改常表中的数据
          //替换常表中RYID为poQrdjOld.getRyid
          String sSql = " from PoHJXX_CZRKJBXXB where ryid=" +
              poQrdjOld.getRyid() + " order by rynbid for update";
          List lstCzrkOld = super.findAllByHQL(sSql);
          if (lstCzrkOld == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的人员信息," + poQrdjOld.getRyid(), null);
          }
          for (int j = 0; j < lstCzrkOld.size(); j++) {
            PoHJXX_CZRKJBXXB poCzrkOld = (PoHJXX_CZRKJBXXB) lstCzrkOld.get(j);
            //根据变动范围修改相应的信息
            //(bdfw <=32 本市)
            if (poQrdjOld.getBdfw() != null &&
                poQrdjOld.getBdfw().compareTo(HjConstant.BDFW_BSJQ) <= 0) {
              //本址
              //如果poCzrkOld的和变动范围，迁出地都相同，才修改
              if ( ( (poCzrkOld.getHylbz() != null &&
                      poCzrkOld.getHylbz().equals(poQrdjOld.getQrlb())) ||
                    (poCzrkOld.getHylbz() == null && poQrdjOld.getQrlb() == null))
                  &&
                  ( (poCzrkOld.getHslbz() != null &&
                     poCzrkOld.getHslbz().equals(poQrdjOld.getQrrq())) ||
                   (poCzrkOld.getHslbz() == null && poQrdjOld.getQrrq() == null))
                  &&
                  ( (poCzrkOld.getHgjdqlbz() != null &&
                     poCzrkOld.getHgjdqlbz().equals(poQrdjOld.getQcdgjdq())) ||
                   (poCzrkOld.getHgjdqlbz() == null && poQrdjOld.getQcdgjdq() == null))
                  &&
                  ( (poCzrkOld.getHsssqlbz() != null &&
                     poCzrkOld.getHsssqlbz().equals(poQrdjOld.getQcdssxq())) ||
                   (poCzrkOld.getHsssqlbz() == null && poQrdjOld.getQcdssxq() == null))
                  &&
                  ( (poCzrkOld.getHxzlbz() != null &&
                     poCzrkOld.getHxzlbz().equals(poQrdjOld.getQcdxz())) ||
                   (poCzrkOld.getHxzlbz() == null && poQrdjOld.getQcdxz() == null))) {
                //如果以前是本址，现在改为本市
                //则清空本址中的信息，在本市中添入新的信息
                if (poQrdjNew.getBdfw() != null &&
                    poQrdjNew.getBdfw().compareTo(HjConstant.BDFW_BSJQ) > 0) {
                  //清空本址信息
                  poCzrkOld.setHylbz("");
                  poCzrkOld.setHslbz("");
                  poCzrkOld.setHgjdqlbz("");
                  poCzrkOld.setHsssqlbz("");
                  poCzrkOld.setHxzlbz("");
                  //增加本市信息
                  poCzrkOld.setHyql(poQrdjNew.getQrlb()); //何因迁来
                  poCzrkOld.setHsql(poQrdjNew.getQrrq()); //何时迁来
                  poCzrkOld.setHgjdqql(poQrdjNew.getQcdgjdq()); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(poQrdjNew.getQcdssxq()); //何省市县（区）迁来
                  poCzrkOld.setHxzql(poQrdjNew.getQcdxz()); //何详址迁来
                }
                else {
                  poCzrkOld.setHylbz(poQrdjNew.getQrlb());
                  poCzrkOld.setHslbz(poQrdjNew.getQrrq());
                  poCzrkOld.setHgjdqlbz(poQrdjNew.getQcdgjdq());
                  poCzrkOld.setHsssqlbz(poQrdjNew.getQcdssxq());
                  poCzrkOld.setHxzlbz(poQrdjNew.getQcdxz());
                }
              }
            }
            else {
              //本市
              //如果poCzrkOld的和变动范围，迁出地都相同，才修改
              if ( ( (poCzrkOld.getHsql() != null &&
                      poCzrkOld.getHsql().equals(poQrdjOld.getQrrq())) ||
                    (poCzrkOld.getHsql() == null && poQrdjOld.getQrrq() == null))
                  &&
                  ( (poCzrkOld.getHyql() != null &&
                     poCzrkOld.getHyql().equals(poQrdjOld.getQrlb())) ||
                   (poCzrkOld.getHyql() == null && poQrdjOld.getQrlb() == null))
                  &&
                  ( (poCzrkOld.getHgjdqql() != null &&
                     poCzrkOld.getHgjdqql().equals(poQrdjOld.getQcdgjdq())) ||
                   (poCzrkOld.getHgjdqql() == null && poQrdjOld.getQcdgjdq() == null))
                  &&
                  ( (poCzrkOld.getHssxqql() != null &&
                     poCzrkOld.getHssxqql().equals(poQrdjOld.getQcdssxq())) ||
                   (poCzrkOld.getHssxqql() == null && poQrdjOld.getQcdssxq() == null))
                  &&
                  ( (poCzrkOld.getHxzql() != null &&
                     poCzrkOld.getHxzql().equals(poQrdjOld.getQcdxz())) ||
                   (poCzrkOld.getHxzql() == null && poQrdjOld.getQcdxz() == null))) {
                //如果是本市改本址
                //将本市信息清空
                if (poQrdjNew.getBdfw() != null &&
                    poQrdjNew.getBdfw().compareTo(HjConstant.BDFW_BSJQ) <= 0) {
                  poCzrkOld.setHyql(""); //何因迁来
                  poCzrkOld.setHsql(""); //何时迁来
                  poCzrkOld.setHgjdqql(""); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(""); //何省市县（区）迁来
                  poCzrkOld.setHxzql(""); //何详址迁来
                  poCzrkOld.setHylbz(poQrdjNew.getQrlb());
                  poCzrkOld.setHslbz(poQrdjNew.getQrrq());
                  poCzrkOld.setHgjdqlbz(poQrdjNew.getQcdgjdq());
                  poCzrkOld.setHsssqlbz(poQrdjNew.getQcdssxq());
                  poCzrkOld.setHxzlbz(poQrdjNew.getQcdxz());
                }
                else {
                  poCzrkOld.setHyql(poQrdjNew.getQrlb()); //何因迁来
                  poCzrkOld.setHsql(poQrdjNew.getQrrq()); //何时迁来
                  poCzrkOld.setHgjdqql(poQrdjNew.getQcdgjdq()); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(poQrdjNew.getQcdssxq()); //何省市县（区）迁来
                  poCzrkOld.setHxzql(poQrdjNew.getQcdxz()); //何详址迁来
                }
              }
            }
            //更新记录
            poCzrkOld.setBdfw(poQrdjNew.getBdfw());
            super.update(poCzrkOld);
          } //end of for j

          //更新人员变动信息表
          //保存人员变动历史
          PoHJTJ_BDXGRZB poBdxgrzb = new PoHJTJ_BDXGRZB();
          List lstRybdxxb = super.findAllByHQL(
              " from PoHJTJ_RYBDXXB where ywnr='" + HjConstant.YWNR_QRDJ +
              "' and bdbid=" + poQrdjNew.getQrdjid() +
              " order by rybdid for update");
          if (lstRybdxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的四变信息," + poQrdjNew.getQrdjid(), null);
          }
          //保存信息到日志
          PoHJTJ_RYBDXXB poRYBDXXB = (PoHJTJ_RYBDXXB) lstRybdxxb.get(0);
          BeanUtils.copyProperties(poBdxgrzb, poRYBDXXB);
          ////////////////////////////////////////////////
          poRYBDXXB.setBdfw(poQrdjNew.getBdfw()); //变动范围
          poRYBDXXB.setBdyy(poQrdjNew.getQrlb()); //变动原因
          poRYBDXXB.setBdqssxq(poQrdjNew.getQcdssxq()); //迁出地省市县（区）
          poRYBDXXB.setBdqmlxz(poQrdjNew.getQcdxz()); //迁出地详址
          //lzh modified here 20061207
          poRYBDXXB.setBdqhb(poQrdjNew.getQrqhb()); //迁入前户别
          /////
          super.update(poRYBDXXB);
          /////////////////////////
          if (poQrdjOld.getZqzbh() != null && poQrdjNew.getZqzbh() != null &&
              !poQrdjOld.getZqzbh().trim().equals(poQrdjNew.getZqzbh().trim())) {
            poBdxgrzb.setZqzbh_q(poQrdjOld.getZqzbh());
            poBdxgrzb.setZqzbh_h(poQrdjNew.getZqzbh());
          }
          if (poQrdjOld.getQyzbh() != null && poQrdjNew.getQyzbh() != null &&
              !poQrdjOld.getQyzbh().trim().equals(poQrdjNew.getQyzbh().trim())) {
            poBdxgrzb.setQyzbh_q(poQrdjOld.getQyzbh());
            poBdxgrzb.setQyzbh_h(poQrdjNew.getQyzbh());
          }
          poBdxgrzb.setCzrid(super.getUserInfo().getYhid());
          poBdxgrzb.setCzrip(BaseContext.getUser().getIp());
          poBdxgrzb.setCzsj(StringUtils.getServiceTime());
          poBdxgrzb.setCzdw(super.getUserInfo().getDwdm());
          poBdxgrzb.setBdxgrzid( (Long) hjtj_bdxgrzbDAO.getId());
          super.create(poBdxgrzb);
          //保存日志信息结束

        }
        lstReturn.add(0, poReturn);
      }
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
   * 迁出业务修改
   * @param poHJYW_QRDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List QcywXg(PoHJYW_QCZXXXB poHJYW_QCZXXXB[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();

    try {
      PojoInfo hjyw_qczxxxbDAO = DAOFactory.createHJYW_QCZXXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_bdxgrzbDAO = DAOFactory.createHJTJ_BDXGRZBDAO();

      //修改迁出注销信息表中的信息
      if (poHJYW_QCZXXXB != null && poHJYW_QCZXXXB.length > 0) {
        PoHJYW_QCZXXXB poReturn[] = new PoHJYW_QCZXXXB[poHJYW_QCZXXXB.length];
        for (int i = 0; i < poHJYW_QCZXXXB.length; i++) {
          PoHJYW_QCZXXXB poQczxxx = poHJYW_QCZXXXB[i];
          PoHJYW_QCZXXXB poQczxOld = new PoHJYW_QCZXXXB();
          //根据迁出注销ID检查迁出注销信息是否存在
          PoHJYW_QCZXXXB poQczxNew = super.get(PoHJYW_QCZXXXB.class,
              poQczxxx.getQczxid());
          if (poQczxNew == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的迁出注销信息," + poQczxxx.getQczxid(), null);
          }
          //胡斌 20060605 把迁往地省市县为国家的代码，填入迁往地国家地区
          if ("0".equals(poQczxxx.getQwdssxq().substring(0, 1))) {
            poQczxxx.setQwdgjdq(poQczxxx.getQwdssxq().substring(3, 6));
          }
          else {
            //如果修改成国内省市县，把迁往地国家地区清空
            poQczxxx.setQwdgjdq(null);
          }
          //修改迁出注销表中的数据
          BeanUtils.copyProperties(poQczxOld, poQczxNew);
          BeanUtils.copyProperties(poQczxNew, poQczxxx);
          poReturn[i] = (PoHJYW_QCZXXXB)super.update(poQczxNew);

          //修改常表中的数据
          //替换常表中RYID为poQrdjOld.getRyid
          String sSql = " from PoHJXX_CZRKJBXXB where ryid=" +
              poQczxOld.getRyid() + " order by rynbid for update ";
          List lstCzrkOld = super.findAllByHQL(sSql);
          if (lstCzrkOld == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的人员信息," + poQczxOld.getRyid(), null);
          }
          for (int j = 0; j < lstCzrkOld.size(); j++) {
            PoHJXX_CZRKJBXXB poCzrkOld = (PoHJXX_CZRKJBXXB) lstCzrkOld.get(j);
            //如果poCzrkOld的和变动范围，迁出地都相同，才修改
            if ( ( (poCzrkOld.getQcrq() != null &&
                    poCzrkOld.getQcrq().equals(poQczxOld.getQcrq())) ||
                  (poCzrkOld.getQcrq() == null && poQczxOld.getQcrq() == null))
                &&
                ( (poCzrkOld.getQczxlb() != null &&
                   poCzrkOld.getQczxlb().equals(poQczxOld.getQclb())) ||
                 (poCzrkOld.getQczxlb() == null && poQczxOld.getQclb() == null))
                &&
                ( (poCzrkOld.getQwdgjdq() != null &&
                   poCzrkOld.getQwdgjdq().equals(poQczxOld.getQwdgjdq())) ||
                 (poCzrkOld.getQwdgjdq() == null && poQczxOld.getQwdgjdq() == null))
                &&
                ( (poCzrkOld.getQwdssxq() != null &&
                   poCzrkOld.getQwdssxq().equals(poQczxOld.getQwdssxq())) ||
                 (poCzrkOld.getQwdssxq() == null && poQczxOld.getQwdssxq() == null))
                &&
                ( (poCzrkOld.getQwdxz() != null &&
                   poCzrkOld.getQwdxz().equals(poQczxOld.getQwdxz())) ||
                 (poCzrkOld.getQwdxz() == null && poQczxOld.getQwdxz() == null))) {
              poCzrkOld.setQcrq(poQczxNew.getQcrq());
              poCzrkOld.setQczxlb(poQczxNew.getQclb()); //
              poCzrkOld.setQwdgjdq(poQczxNew.getQwdgjdq()); //
              poCzrkOld.setQwdssxq(poQczxNew.getQwdssxq()); //
              poCzrkOld.setQwdxz(poQczxNew.getQwdxz()); //
            }

            //更新记录
            super.update(poCzrkOld);
          } //end of for j

          //更新人员变动信息表
          //保存人员变动历史
          PoHJTJ_BDXGRZB poBdxgrzb = new PoHJTJ_BDXGRZB();
          List lstRybdxxb = super.findAllByHQL(
              " from PoHJTJ_RYBDXXB where ywnr='" + HjConstant.YWNR_QCZX +
              "' and bdbid=" + poQczxNew.getQczxid() +
              " order by rybdid for update");
          if (lstRybdxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的四变信息," + poQczxNew.getQczxid(), null);
          }
          //保存信息到日志
          PoHJTJ_RYBDXXB poRYBDXXB = (PoHJTJ_RYBDXXB) lstRybdxxb.get(0);
          BeanUtils.copyProperties(poBdxgrzb, poRYBDXXB);
          ////////////////////////////////////////////////
          poRYBDXXB.setBdfw(poQczxNew.getBdfw()); //变动范围
          poRYBDXXB.setBdyy(poQczxNew.getQclb()); //变动原因
          poRYBDXXB.setBdhssxq(poQczxNew.getQwdssxq()); //迁出地省市县（区）
          poRYBDXXB.setBdhmlxz(poQczxNew.getQwdxz()); //迁出地详址
          /////
          super.update(poRYBDXXB);
          /////////////////////////
          if (poQczxOld.getZqzbh() != null && poQczxNew.getZqzbh() != null &&
              !poQczxOld.getZqzbh().trim().equals(poQczxNew.getZqzbh().trim())) {
            poBdxgrzb.setZqzbh_q(poQczxOld.getZqzbh());
            poBdxgrzb.setZqzbh_h(poQczxNew.getZqzbh());
          }
          if (poQczxOld.getQyzbh() != null && poQczxNew.getQyzbh() != null &&
              !poQczxOld.getQyzbh().trim().equals(poQczxNew.getQyzbh().trim())) {
            poBdxgrzb.setQyzbh_q(poQczxOld.getQyzbh());
            poBdxgrzb.setQyzbh_h(poQczxNew.getQyzbh());
          }
          poBdxgrzb.setCzrid(this.getUserInfo().getYhid());
          poBdxgrzb.setCzrip(BaseContext.getUser().getIp());
          poBdxgrzb.setCzsj(StringUtils.getServiceTime());
          poBdxgrzb.setCzdw(this.getUserInfo().getDwdm());
          poBdxgrzb.setBdxgrzid( (Long) hjtj_bdxgrzbDAO.getId());
          super.create(poBdxgrzb);
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
   * 出生业务修改
   * @param poHJYW_QRDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List CsywXg(PoHJYW_CSDJXXB poHJYW_CSDJXXB[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();

    try {
      PojoInfo hjyw_csdjxxbDAO = DAOFactory.createHJYW_CSDJXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_bdxgrzbDAO = DAOFactory.createHJTJ_BDXGRZBDAO();
      //开始事务
 
      //修改出生登记信息表中的信息
      if (poHJYW_CSDJXXB != null && poHJYW_CSDJXXB.length > 0) {
        PoHJYW_CSDJXXB poReturn[] = new PoHJYW_CSDJXXB[poHJYW_CSDJXXB.length];
        for (int i = 0; i < poHJYW_CSDJXXB.length; i++) {
          PoHJYW_CSDJXXB pocsdjxx = poHJYW_CSDJXXB[i];
          PoHJYW_CSDJXXB pocsdjOld = new PoHJYW_CSDJXXB();
          //根据出生登记ID检查登记信息是否存在
          PoHJYW_CSDJXXB pocsdjNew = super.get(PoHJYW_CSDJXXB.class,
              pocsdjxx.getCsdjid());
          if (pocsdjNew == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的出生信息," + pocsdjxx.getCsdjid(), null);
          }
          //修改出生登记表中的数据
          BeanUtils.copyProperties(pocsdjOld, pocsdjNew);
          BeanUtils.copyProperties(pocsdjNew, pocsdjxx);
          poReturn[i] = (PoHJYW_CSDJXXB)super.update(pocsdjNew);

          //修改常表中的数据
          //替换常表中RYID为poQrdjOld.getRyid
          String sSql = " from PoHJXX_CZRKJBXXB where ryid=" +
              pocsdjOld.getRyid() + " order by rynbid for update";
          List lstCzrkOld = super.findAllByHQL(sSql);
          if (lstCzrkOld == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的人员信息," + pocsdjOld.getRyid(), null);
          }
          for (int j = 0; j < lstCzrkOld.size(); j++) {
            PoHJXX_CZRKJBXXB poCzrkOld = (PoHJXX_CZRKJBXXB) lstCzrkOld.get(j);
            //如果出生类别，出生日期都相同，才修改
            if ( ( (poCzrkOld.getHylbz() != null &&
                    poCzrkOld.getHylbz().equals(pocsdjOld.getCsdjlb())) ||
                  (poCzrkOld.getHylbz() == null && pocsdjOld.getCsdjlb() == null))
                &&
                ( (poCzrkOld.getHslbz() != null &&
                   poCzrkOld.getHslbz().equals(pocsdjOld.getCsrq())) ||
                 (poCzrkOld.getHslbz() == null && pocsdjOld.getCsrq() == null))) {
              poCzrkOld.setHylbz(pocsdjNew.getCsdjlb());
              poCzrkOld.setHslbz(pocsdjNew.getCsrq());
              //poCzrkOld.setHgjdqlbz(pocsdjNew.getCsdgjdq());
              // poCzrkOld.setHsssqlbz(pocsdjNew.getCsdssxq());
              //poCzrkOld.setHxzlbz(pocsdjNew.getCsdxz());
            }
            //更新记录
            super.update(poCzrkOld);
          } //end of for j

          //更新人员变动信息表
          //保存人员变动历史
          PoHJTJ_BDXGRZB poBdxgrzb = new PoHJTJ_BDXGRZB();
          List lstRybdxxb = super.findAllByHQL(
              " from PoHJTJ_RYBDXXB where ywnr='" + HjConstant.YWNR_CSDJ +
              "' and bdbid=" + pocsdjNew.getCsdjid() +
              " order by rybdid for update");
          if (lstRybdxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的四变信息," + pocsdjNew.getCsdjid(), null);
          }
          //保存信息到日志
          PoHJTJ_RYBDXXB poRYBDXXB = (PoHJTJ_RYBDXXB) lstRybdxxb.get(0);
          BeanUtils.copyProperties(poBdxgrzb, poRYBDXXB);
          ////////////////////////////////////////////////
          poRYBDXXB.setBdyy(pocsdjNew.getCsdjlb()); //变动原因
          BeanUtils.copyProperties(poRYBDXXB, pocsdjNew);
          /////
          super.update(poRYBDXXB);
          /////////////////////////
          poBdxgrzb.setCzrid(this.getUserInfo().getYhid());
          poBdxgrzb.setCzrip(BaseContext.getUser().getIp());
          poBdxgrzb.setCzsj(StringUtils.getServiceTime());
          poBdxgrzb.setCzdw(this.getUserInfo().getDwdm());
          poBdxgrzb.setBdxgrzid( (Long) hjtj_bdxgrzbDAO.getId());
          super.create(poBdxgrzb);
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
   * 死亡业务修改
   * @param poHJYW_QRDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List SwywXg(PoHJYW_SWZXXXB poHJYW_SWZXXXB[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo hjyw_swzxxxbDAO = DAOFactory.createHJYW_SWZXXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_bdxgrzbDAO = DAOFactory.createHJTJ_BDXGRZBDAO();
      //开始事务

      //修改出生登记信息表中的信息
      if (poHJYW_SWZXXXB != null && poHJYW_SWZXXXB.length > 0) {
        PoHJYW_SWZXXXB poReturn[] = new PoHJYW_SWZXXXB[poHJYW_SWZXXXB.length];
        for (int i = 0; i < poHJYW_SWZXXXB.length; i++) {
          PoHJYW_SWZXXXB poSwzxxx = poHJYW_SWZXXXB[i];
          PoHJYW_SWZXXXB poSwzxOld = new PoHJYW_SWZXXXB();
          //根据出生登记ID检查登记信息是否存在
          PoHJYW_SWZXXXB poSwzxNew = super.get(PoHJYW_SWZXXXB.class,
              poSwzxxx.getSwzxid());
          if (poSwzxNew == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的死亡注销信息," + poSwzxxx.getSwzxid(), null);
          }
          //修改出生登记表中的数据
          BeanUtils.copyProperties(poSwzxOld, poSwzxNew);
          BeanUtils.copyProperties(poSwzxNew, poSwzxxx);
          poReturn[i] = (PoHJYW_SWZXXXB)super.update(poSwzxNew);

          //修改常表中的数据
          //替换常表中RYID为poQrdjOld.getRyid
          String sSql = " from PoHJXX_CZRKJBXXB where ryid=" +
              poSwzxOld.getRyid() + " order by rynbid for update";
          List lstCzrkOld = super.findAllByHQL(sSql);
          if (lstCzrkOld == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的人员信息," + poSwzxOld.getRyid(), null);
          }
          for (int j = 0; j < lstCzrkOld.size(); j++) {
            PoHJXX_CZRKJBXXB poCzrkOld = (PoHJXX_CZRKJBXXB) lstCzrkOld.get(j);
            //如果出生类别，出生日期都相同，才修改
            if ( ( (poCzrkOld.getSwrq() != null &&
                    poCzrkOld.getSwrq().equals(poSwzxOld.getSwrq())) ||
                  (poCzrkOld.getSwrq() == null && poSwzxOld.getSwrq() == null))
                &&
                ( (poCzrkOld.getSwzxlb() != null &&
                   poCzrkOld.getSwzxlb().equals(poSwzxOld.getSwzxlb())) ||
                 (poCzrkOld.getSwzxlb() == null && poSwzxOld.getSwzxlb() == null))) {
              poCzrkOld.setSwrq(poSwzxNew.getSwrq());
              poCzrkOld.setSwzxlb(poSwzxNew.getSwzxlb());
            }
            //更新记录
            super.update(poCzrkOld);
          } //end of for j

          //更新人员变动信息表
          //保存人员变动历史
          PoHJTJ_BDXGRZB poBdxgrzb = new PoHJTJ_BDXGRZB();
          List lstRybdxxb = super.findAllByHQL(
              " from PoHJTJ_RYBDXXB where ywnr='" + HjConstant.YWNR_SWZX +
              "' and bdbid=" + poSwzxNew.getSwzxid() +
              " order by rybdid for update");
          if (lstRybdxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的四变信息," + poSwzxNew.getSwzxid(), null);
          }
          //保存信息到日志
          PoHJTJ_RYBDXXB poRYBDXXB = (PoHJTJ_RYBDXXB) lstRybdxxb.get(0);
          BeanUtils.copyProperties(poBdxgrzb, poRYBDXXB);
          ////////////////////////////////////////////////
          poRYBDXXB.setBdyy(poSwzxNew.getSwzxlb()); //变动原因
          BeanUtils.copyProperties(poRYBDXXB, poSwzxNew);
          /////
          super.update(poRYBDXXB);
          /////////////////////////
          poBdxgrzb.setCzrid(this.getUserInfo().getYhid());
          poBdxgrzb.setCzrip(BaseContext.getUser().getIp());
          poBdxgrzb.setCzsj(StringUtils.getServiceTime());
          poBdxgrzb.setCzdw(this.getUserInfo().getDwdm());
          poBdxgrzb.setBdxgrzid( (Long) hjtj_bdxgrzbDAO.getId());
          super.create(poBdxgrzb);
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
   * 住址变动业务修改
   * @param PoHJYW_ZZBDXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ZzbdywXg(PoHJYW_ZZBDXXB poHJYW_ZZBDXXB[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();
      PojoInfo hjtj_bdxgrzbDAO = DAOFactory.createHJTJ_BDXGRZBDAO();
      //开始事务
      //修改出生登记信息表中的信息
      if (poHJYW_ZZBDXXB != null && poHJYW_ZZBDXXB.length > 0) {
        PoHJYW_ZZBDXXB poReturn[] = new PoHJYW_ZZBDXXB[poHJYW_ZZBDXXB.length];
        for (int i = 0; i < poHJYW_ZZBDXXB.length; i++) {
          PoHJYW_ZZBDXXB poZzbdxx = poHJYW_ZZBDXXB[i];
          PoHJYW_ZZBDXXB poZzbdOld = new PoHJYW_ZZBDXXB();
          //根据出生登记ID检查登记信息是否存在
          PoHJYW_ZZBDXXB poZzbdNew = super.get(PoHJYW_ZZBDXXB.class,
              poZzbdxx.getZzbdid());
          if (poZzbdNew == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的住址变动信息," + poZzbdxx.getZzbdid(), null);
          }
          //修改住址变动业务表中的数据
          BeanUtils.copyProperties(poZzbdOld, poZzbdNew);
          BeanUtils.copyProperties(poZzbdNew, poZzbdxx);
          poReturn[i] = (PoHJYW_ZZBDXXB)super.update(poZzbdNew);

          //修改常表中的数据
          //替换常表中RYID为poQrdjOld.getRyid
          String sSql = " from PoHJXX_CZRKJBXXB where ryid=" +
              poZzbdOld.getRyid() + " order by rynbid for update";
          List lstCzrkOld = super.findAllByHQL(sSql);
          if (lstCzrkOld == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的人员信息," + poZzbdOld.getRyid(), null);
          }
          for (int j = 0; j < lstCzrkOld.size(); j++) {
            PoHJXX_CZRKJBXXB poCzrkOld = (PoHJXX_CZRKJBXXB) lstCzrkOld.get(j);
            //生成地址信息
            PoHJXX_MLPXXXXB poMlp = new PoHJXX_MLPXXXXB();
            poMlp.setPcs(poZzbdOld.getPcs_q());
            poMlp.setJlx(poZzbdOld.getJlx_q());
            poMlp.setJcwh(poZzbdOld.getJcwh_q());
            poMlp.setXzjd(poZzbdOld.getXzjd_q());
            poMlp.setZrq(poZzbdOld.getZrq_q());
            poMlp.setMlph(poZzbdOld.getMlph_q());
            poMlp.setMlxz(poZzbdOld.getMlxz_q());

            HjCommon hjcom = new HjCommon();
            String sZz = hjcom.generateZZ(poMlp, PublicConstant.XTKZCS_DZPZFS,
                                          PublicConstant.XTKZCS_DZPZFS_QCDXZ);
            //根据变动范围修改相应的信息
            //(bdfw <=32 本市)
            if (poZzbdOld.getBdfw() != null &&
                poZzbdOld.getBdfw().compareTo(HjConstant.BDFW_BSJQ) <= 0) {
              //本址
              //如果poCzrkOld的和变动范围，迁出地都相同，才修改
              if ( ( (poCzrkOld.getHylbz() != null &&
                      poCzrkOld.getHylbz().equals(poZzbdOld.getZzbdlb())) ||
                    (poCzrkOld.getHylbz() == null && poZzbdOld.getZzbdlb() == null))
                  &&
                  ( (poCzrkOld.getHslbz() != null &&
                     poCzrkOld.getHslbz().equals(poZzbdOld.getZzbdrq())) ||
                   (poCzrkOld.getHslbz() == null && poZzbdOld.getZzbdrq() == null))
                  &&
                  ( (poCzrkOld.getHsssqlbz() != null &&
                     poCzrkOld.getHsssqlbz().equals(poZzbdOld.getSsxq_q())) ||
                   (poCzrkOld.getHsssqlbz() == null && poZzbdOld.getSsxq_q() == null))
                  &&
                  ( (poCzrkOld.getHxzlbz() != null &&
                     poCzrkOld.getHxzlbz().equals(sZz)) ||
                   (poCzrkOld.getHxzlbz() == null && sZz == null))) {
                //如果以前是本址，现在改为本市
                //则清空本址中的信息，在本市中添入新的信息
                if (poZzbdNew.getBdfw() != null &&
                    poZzbdNew.getBdfw().compareTo(HjConstant.BDFW_BSJQ) > 0) {
                  //清空本址信息
                  poCzrkOld.setHylbz("");
                  poCzrkOld.setHslbz("");
                  poCzrkOld.setHgjdqlbz("");
                  poCzrkOld.setHsssqlbz("");
                  poCzrkOld.setHxzlbz("");
                  //增加本市信息
                  poCzrkOld.setHyql(poZzbdNew.getZzbdlb()); //何因迁来
                  poCzrkOld.setHsql(poZzbdNew.getZzbdrq()); //何时迁来
                  poCzrkOld.setHgjdqql(null); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(poZzbdNew.getSsxq_q()); //何省市县（区）迁来
                  poCzrkOld.setHxzql(sZz); //何详址迁来
                }
                else {
                  poCzrkOld.setHylbz(poZzbdNew.getZzbdlb());
                  poCzrkOld.setHslbz(poZzbdNew.getZzbdrq());
                  poCzrkOld.setHgjdqlbz(null);
                  poCzrkOld.setHsssqlbz(poZzbdNew.getSsxq_q());
                  poCzrkOld.setHxzlbz(sZz);
                }
              }
            }
            else {
              //本市
              //如果poCzrkOld的和变动范围，迁出地都相同，才修改
              if ( ( (poCzrkOld.getHsql() != null &&
                      poCzrkOld.getHsql().equals(poZzbdOld.getZzbdlb())) ||
                    (poCzrkOld.getHsql() == null && poZzbdOld.getZzbdlb() == null))
                  &&
                  ( (poCzrkOld.getHyql() != null &&
                     poCzrkOld.getHyql().equals(poZzbdOld.getZzbdrq())) ||
                   (poCzrkOld.getHyql() == null && poZzbdOld.getZzbdrq() == null))
                  &&
                  ( (poCzrkOld.getHssxqql() != null &&
                     poCzrkOld.getHssxqql().equals(poZzbdOld.getSsxq_q())) ||
                   (poCzrkOld.getHssxqql() == null && poZzbdOld.getSsxq_q() == null))
                  &&
                  ( (poCzrkOld.getHxzql() != null &&
                     poCzrkOld.getHxzql().equals(sZz)) ||
                   (poCzrkOld.getHxzql() == null && sZz == null))) {
                //如果是本市改本址
                //将本市信息清空
                if (poZzbdNew.getBdfw() != null &&
                    poZzbdNew.getBdfw().compareTo(HjConstant.BDFW_BSJQ) <= 0) {
                  poCzrkOld.setHyql(""); //何因迁来
                  poCzrkOld.setHsql(""); //何时迁来
                  poCzrkOld.setHgjdqql(""); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(""); //何省市县（区）迁来
                  poCzrkOld.setHxzql(""); //何详址迁来
                  poCzrkOld.setHylbz(poZzbdNew.getZzbdlb());
                  poCzrkOld.setHslbz(poZzbdNew.getZzbdrq());
                  poCzrkOld.setHgjdqlbz(null);
                  poCzrkOld.setHsssqlbz(poZzbdNew.getSsxq_q());
                  poCzrkOld.setHxzlbz(sZz);
                }
                else {
                  poCzrkOld.setHyql(poZzbdNew.getZzbdlb()); //何因迁来
                  poCzrkOld.setHsql(poZzbdNew.getZzbdrq()); //何时迁来
                  poCzrkOld.setHgjdqql(null); //何国家（地区）迁来
                  poCzrkOld.setHssxqql(poZzbdNew.getSsxq_q()); //何省市县（区）迁来
                  poCzrkOld.setHxzql(sZz); //何详址迁来
                }
              }

            }
            //更新记录
            poCzrkOld.setBdfw(poZzbdNew.getBdfw());
            super.update(poCzrkOld);
          } //end of for j

          //更新人员变动信息表
          //保存人员变动历史
          //在四边表中
          PoHJTJ_BDXGRZB poBdxgrzb = new PoHJTJ_BDXGRZB();
          List lstRybdxxb = super.findAllByHQL(
              " from PoHJTJ_RYBDXXB where (ywnr='" + HjConstant.YWNR_ZZBDQR +
              "' or ywnr='" + HjConstant.YWNR_ZZBDQC +
              "') and bdbid=" + poZzbdNew.getZzbdid() +
              " order by rybdid for update");
          if (lstRybdxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的四变信息," + poZzbdNew.getZzbdid(), null);
          }
          //保存信息到日志
          PoHJTJ_RYBDXXB poRYBDXXB_qc = (PoHJTJ_RYBDXXB) lstRybdxxb.get(0);
          PoHJTJ_RYBDXXB poRYBDXXB_qr = (PoHJTJ_RYBDXXB) lstRybdxxb.get(1);
          BeanUtils.copyProperties(poBdxgrzb, poRYBDXXB_qr);
          ////////////////////////////////////////////////
          poRYBDXXB_qc.setBdyy(poZzbdNew.getZzbdlb()); //变动原因
          poRYBDXXB_qc.setBdfw(poZzbdNew.getBdfw()); //变动范围
           //BeanUtils.copyProperties(poRYBDXXB_qc, poZzbdxx);
          /////
          super.update(poRYBDXXB_qc);
          poRYBDXXB_qr.setBdyy(poZzbdNew.getZzbdlb()); //变动原因
          poRYBDXXB_qr.setBdfw(poZzbdNew.getBdfw()); //变动范围
          //BeanUtils.copyProperties(poRYBDXXB_qr, poZzbdxx);
          /////
          super.update(poRYBDXXB_qr);
          /////////////////////////
          if (poZzbdOld.getQyzbh() != null && poZzbdNew.getQyzbh() != null &&
              !poZzbdOld.getQyzbh().trim().equals(poZzbdNew.getQyzbh().trim())) {
            poBdxgrzb.setQyzbh_q(poZzbdOld.getQyzbh());
            poBdxgrzb.setQyzbh_h(poZzbdNew.getQyzbh());
          }
          poBdxgrzb.setCzrid(this.getUserInfo().getYhid());
          poBdxgrzb.setCzrip(BaseContext.getUser().getIp());
          poBdxgrzb.setCzsj(StringUtils.getServiceTime());
          poBdxgrzb.setCzdw(this.getUserInfo().getDwdm());
          poBdxgrzb.setBdxgrzid( (Long) hjtj_bdxgrzbDAO.getId());
          super.create(poBdxgrzb);
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

}
