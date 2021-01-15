package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_JWZRQXXB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 警务责任区信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtjwzrqxx
    extends DefaultVO {

  //单位信息

  private String dwmc;
  private String qybzmc;

  //警务责任区信息
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String dwdm;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;

  public VoXtjwzrqxx(){
  }

  public VoXtjwzrqxx(PoXT_JWZRQXXB poXtjwzrqb,PoXT_DWXXB poXtdwxxb,PoXT_XTCSB poXtcsb){
    try {
      BeanUtils.copyProperties(this, poXtjwzrqb);
      this.setDwmc(poXtdwxxb.getMc());
      this.setQybzmc(poXtcsb.getMc());
    }
    catch (InvocationTargetException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
    catch (IllegalAccessException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
  }
  //

  public void setDwmc(String mc) {
    this.dwmc = mc;
  }

  public String getDwmc() {
    return dwmc;
  }

//警务责任区信息
  public void setDm(String dm) {
     this.dm = dm;
   }

   public String getDm() {
     return dm;
   }

   public void setMc(String mc) {
     this.mc = mc;
   }

   public String getMc() {
     return mc;
   }

   public void setZwpy(String zwpy) {
     this.zwpy = zwpy;
   }

   public String getZwpy() {
     return zwpy;
   }

   public void setWbpy(String wbpy) {
     this.wbpy = wbpy;
   }

   public String getWbpy() {
     return wbpy;
   }

   public void setDwdm(String dwdm) {
     this.dwdm = dwdm;
   }

   public String getDwdm() {
     return dwdm;
   }

   public void setBz(String bz) {
     this.bz = bz;
   }

   public String getBz() {
     return bz;
   }

   public void setQybz(String qybz) {
     this.qybz = qybz;
   }

   public String getQybz() {
     return qybz;
   }

   public void setBdlx(String bdlx) {
     this.bdlx = bdlx;
   }

   public String getBdlx() {
     return bdlx;
   }

   public void setBdsj(String bdsj) {
     this.bdsj = bdsj;
   }

   public String getBdsj() {
     return bdsj;
   }

   public void setQybzmc(String mc) {
     this.qybzmc = mc;
   }

   public String getQybzmc() {
     return qybzmc;
   }

}
